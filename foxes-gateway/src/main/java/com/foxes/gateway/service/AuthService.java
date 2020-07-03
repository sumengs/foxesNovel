package com.foxes.gateway.service;

import org.springframework.http.server.reactive.ServerHttpRequest;

/**
 * @Author GaoZeXi on 2020/6/18
 * @Description: Step by Step  and Stand on,  You Are The Best Investment!!!
 */
public interface AuthService {

    /**
     * 判断cookie中jti是否存在
     * @param request
     * @return
     */
    String getJtiFromCookie(ServerHttpRequest request);

    /**
     * 判断redis中令牌是否过期
     * @param jti
     * @return
     */
    String getTokenFromRedis(String jti);
}
