package com.gobuy.oauth;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;

public class ParseJwtTest {

    /**
     * 校验令牌
     */
    @Test
    public void test () throws Exception {
        //基于公钥去解析JWT

        String token="eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9" +
                ".eyJwYXNzd29yZCI6IjEyMzQ1NiIsInVzZXJuYW1lIjoibWFtYW9tYW8ifQ.IVVRDuOVzI2wP5PFCXFqEPRfXc8J90eXdYhDz5TPwRUifreJoP80bXekEmpYaE-0ZNl87b_g6QXtNq1jQTbHY6rxH07T8C-JbjnGyMYK77jGtKh0zQZQYh5QJUoyfPU2CCf4gNnb35MH6wntVEb7B0tcP4s0-XfCLFfMX78T-fYIvYODV4iVYm0uup18hUkn_Ml9qJFLapfzXFLwYSDBEBKKyV6NyYpxY5xMU_mLGZkZFHY0UiYAWy7su9Gy7R8lhS-TV5oE3KCvEa1j0aBPqbTjqK_sZXJ4k-P78OGlNohjnhi0WCbYaJ-57J7NUaa3tJtabBj7zQ1cBqvIOPUs9w";

        String publickey ="-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvFsEiaLvij9C1Mz+oyAmt47whAaRkRu/8kePM+X8760UGU0RMwGti6Z9y3LQ0RvK6I0brXmbGB/RsN38PVnhcP8ZfxGUH26kX0RK+tlrxcrG+HkPYOH4XPAL8Q1lu1n9x3tLcIPxq8ZZtuIyKYEmoLKyMsvTviG5flTpDprT25unWgE4md1kthRWXOnfWHATVY7Y/r4obiOL1mS5bEa/iNKotQNnvIAKtjBM4RlIDWMa6dmz+lHtLtqDD2LF1qwoiSIHI75LQZ/CNYaHCfZSxtOydpNKq8eb1/PGiLNolD4La2zf0/1dlcr5mkesV570NxRmU1tFm8Zd3MZlZmyv9QIDAQAB-----END PUBLIC KEY-----";
        System.out.println("new RsaVerifier(publickey).algorithm() = " + new RsaVerifier(publickey).algorithm());
        //校验JWT  TODO  为什么直接就能获取内容
//        Jwt jwt = JwtHelper.decode(token);
        //校验该token 是否和该公钥是一对内容
        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(publickey));

        //获取Jwt原始内容
        String claims = jwt.getClaims();
        System.out.println(claims);
        //jwt令牌
        String encoded = jwt.getEncoded();
        System.out.println(encoded);
    }

    @Test
    public void test2 () throws Exception {
        String encode = new BCryptPasswordEncoder().encode("foxes");
        System.out.println("encode = " + encode);
    }
}
