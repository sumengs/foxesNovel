package com.foxes.oauth2.service;/*
 *@Author GaoZeXi
 *@Created time 2020/6/16 15:06
 *@Description:
 * Step by Step  and Stand on,  You Are The Best Investment!!!
 */

import com.foxes.oauth2.util.AuthToken;

/**
 * @author GaoZeXi
 */
public interface AuthorizeService {
    AuthToken login(String username, String password, String clientId, String clientSecret);
}
