package com.sumeng.peekshopping.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @Author GaoZeXi on 2020/6/20
 * @Description: Step by Step  and Stand on,  You Are The Best Investment!!!
 */
@Component
public class FeignInterceptor implements RequestInterceptor {
    //给所有基于feign接口调用的请求附带令牌
    @Override
    public void apply(RequestTemplate requestTemplate) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if(requestAttributes!=null){
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()){
                String headerName = headerNames.nextElement();
                if("authorization".equals(headerName )){
                    String headerValue = request.getHeader(headerName);
                    requestTemplate.header(headerName,headerValue);
                }
            }
        }
    }
}
