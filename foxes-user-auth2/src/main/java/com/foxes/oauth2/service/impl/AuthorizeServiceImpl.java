package com.foxes.oauth2.service.impl;/*
 *@Author GaoZeXi
 *@Created time 2020/6/16 15:07
 *@Description:
 * Step by Step  and Stand on,  You Are The Best Investment!!!
 */

import com.foxes.oauth2.service.AuthorizeService;
import com.foxes.oauth2.util.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author GaoZeXi
 */
@Service
public class AuthorizeServiceImpl implements AuthorizeService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${auth.ttl}")
    private Long ttl;

    @Override
    public AuthToken login(String username, String password, String clientId, String clientSecret) {

        //构造请求地址,http://localhost:9200/oauth/token
        ServiceInstance serviceInstance = loadBalancerClient.choose("USER-AUTH");

        String url = serviceInstance.getUri() + "/oauth/token";
        //构造请求体 body ,headers
        //1、header信息，包括了http basic认证信息
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        //进行Base64编码,并将编码后的认证数据放到头文件中
        String httpbasic = httpbasic(clientId, clientSecret);
        //Basic xxxxxxxx
        headers.add("Authorization", httpbasic);
        //2、指定认证类型、账号、密码
        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        body.add("grant_type", "password");
        body.add("username", username);
        body.add("password", password);
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<>(body, headers);

        //当后端出现了401,400后端不对这两个异常编码进行处理,而是直接返回前端
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode() != 400 && response.getRawStatusCode() != 401) {
                    super.handleError(response);
                }
            }
        });

        //发送请求
        ResponseEntity<Map> respEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Map.class);
        Map map = respEntity.getBody();

        if (map == null || map.get("access_token") == null || map.get("refresh_token") == null || map.get("jti") == null) {
            throw new RuntimeException("申请令牌失败");
        }


        //封装结果数据
        AuthToken authToken = new AuthToken();
        authToken.setAccessToken((String) map.get("access_token"));
        authToken.setRefreshToken((String) map.get("refresh_token"));
        authToken.setJti((String) map.get("jti"));

        //将jti 作为redis的key,jwt作为redis的value 存放数据,设置ttl
        stringRedisTemplate.opsForValue().set((String) map.get("jti"), (String) map.get("access_token")
                ,ttl, TimeUnit.SECONDS);
        return authToken;
    }

    private String httpbasic(String clientId, String clientSecret) {
        //将客户端id和客户端密码拼接，按“客户端id:客户端密码”
        String string = clientId + ":" + clientSecret;
        //进行base64编码
        byte[] encode = Base64Utils.encode(string.getBytes());
        return "Basic " + new String(encode);

    }

    public static void main(String[] args) {
        String httpbasic = new AuthorizeServiceImpl().httpbasic("changgou", "changgou");
        System.out.println(httpbasic);
    }
}
