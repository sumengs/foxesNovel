package com.gobuy.oauth;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author GaoZeXi on 2020/6/22
 * @Description: Step by Step  and Stand on,  You Are The Best Investment!!!
 */
public class BcryptTest {
    @Test
    public void test () throws Exception {
        String heima = new BCryptPasswordEncoder().encode("heima");
        System.out.println("heima = " + heima);
    }
}
