package com.foxes.gateway.service.impl;

import com.foxes.gateway.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 判断cookie中jti是否存在
     * @param request
     * @return
     */
    @Override
    public String getJtiFromCookie(ServerHttpRequest request) {
        HttpCookie cookie = request.getCookies().getFirst("uid");
        if (cookie!=null){
            return cookie.getValue();
        }
        return null;
    }

    /**
     * 判断redis中令牌是否过期
     * @param jti
     * @return
     */
    @Override
    public String getTokenFromRedis(String jti) {
        String token = stringRedisTemplate.boundValueOps(jti).get();
        return token;
    }
}