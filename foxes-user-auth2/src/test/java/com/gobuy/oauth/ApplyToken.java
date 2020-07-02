package com.gobuy.oauth;/*
 *@Author GaoZeXi
 *@Created time 2020/6/16 10:33
 *@Description:
 * Step by Step  and Stand on,  You Are The Best Investment!!!
 */

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;


public class ApplyToken {




    @Test
    public void testGetToken() {
        RestTemplate restTemplate = new RestTemplate();

        //构造请求地址,http://localhost:9200/oauth/token
        String url ="http://localhost:9200/oauth/token";

        //进行Base64编码,并将编码后的认证数据放到头文件中
        String httpbasic = httpbasic("changgou", "changgou");
        //构造请求体 body ,headers
        //1、header信息，包括了http basic认证信息
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Authorization", httpbasic);
        //2、指定认证类型、账号、密码
        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        body.add("grant_type","password");
        body.add("username","heima");
        body.add("password","");
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<>(body,headers);
        //当后端出现了401,400后端不对这两个异常编码进行处理,而是直接返回前端
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler(){
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if(response.getRawStatusCode()!=400&&response.getRawStatusCode()!=401){
                    super.handleError(response);
                }
            }
        });
        
        //发送请求
        ResponseEntity<Map> respEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Map.class);
        Map map = respEntity.getBody();
        System.out.println("map = " + map);
    }

    /***
     * base64编码
     * @param clientId
     * @param clientSecret
     * @return
     */
    private String httpbasic(String clientId,String clientSecret){
        //将客户端id和客户端密码拼接，按“客户端id:客户端密码”
        String string = clientId+":"+clientSecret;
        //进行base64编码
        byte[] encode = Base64Utils.encode(string.getBytes());
        return "Basic "+new String(encode);
    }
}
