package com.foxes.gateway.filter;


import com.foxes.gateway.service.AuthService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * @author Gaozexi on 2020/6/4 17:13
 * Step by Step  and Stand on,  You Are The Best Investment!
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {
    public static final String AUTHORIZATION = "Authorization";

    public Logger  logger =LoggerFactory.getLogger(this.getClass());

    public static final String LOGIN_URL="http://localhost:9200/oauth/toLogin";

    public  String redirectURL="";

    @Autowired
    private AuthService authService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取当前请求对象
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        redirectURL=request.getURI().getPath();
        String path = request.getURI().getPath();
        URI uri = exchange.getRequest().getURI();
        //如果路径是登录,或者不需要传递令牌的路径,直接放行
        if ("/oauth/login".equals(path)||!UrlFilter.hasAuthorize(path)){
            //放行
            logger.info(uri+"    [该请求不需要令牌,直接放行]");
             return chain.filter(exchange);
        }

        //未在UrlFilter中配置放行路径的 路径 将会走下面流程,进行令牌判断
        //判断cookie上是否存在jti
        String jti = authService.getJtiFromCookie(request);
        if (StringUtils.isEmpty(jti)){
            logger.warn(uri+"    [该请求 cookie中没有jti,跳转登录]");
            //未登录,跳转登录
            logger.warn("跳转路径:"+LOGIN_URL+"?redirectURL="+redirectURL);
            return  this.toLoginPage(LOGIN_URL+"?redirectURL="+redirectURL,exchange);

        }


        //判断redis中token是否存在
        String redisToken = authService.getTokenFromRedis(jti);
        if (StringUtils.isEmpty(redisToken)){
            //未登录,跳转登录
            logger.warn(uri+"    [该请求的jti对应的令牌不存在或已失效,跳转登录],如有问题,请核实该jti是否在redis中存在token"+jti);
            logger.warn("跳转路径:"+LOGIN_URL+"?redirectURL="+redirectURL);
            return  this.toLoginPage(LOGIN_URL+"?redirectURL="+redirectURL,exchange);

        }

        //校验通过 , 请求头增强，放行
        logger.info(uri+"    [该请求需要令牌,且携带了正确的令牌,放行]");
        request.mutate().header(AUTHORIZATION,"Bearer "+redisToken);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }

    public Mono<Void> toLoginPage(String loginUrl, ServerWebExchange exchange){
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.SEE_OTHER);
        response.getHeaders().set("location",loginUrl);
        return response.setComplete();
    }
}
