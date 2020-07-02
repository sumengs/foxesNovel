package com.gobuy.oauth;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


public class CreateJwtTest {

   //证书文件路径
   String key_location="changgou.jks";
   //秘钥库密码
   String key_password="changgou";
   //秘钥密码
   String keypwd ="changgou";
   //秘钥别名
   String alias = "changgou";
   @Test
   public void test () throws Exception {
//      //1.指定私钥的位置
      ClassPathResource classPathResource = new ClassPathResource(key_location);
//      //指定秘钥库的密码
      String keysPass =key_password;
      KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(classPathResource, keysPass.toCharArray());
//
//      //2.创建秘钥工厂
      String alias=this.alias;
      String password=keypwd;
      KeyPair keyPair = keyStoreKeyFactory.getKeyPair(alias, password.toCharArray());
//
//      //将当期的私钥转换为rsa私钥
      RSAPrivateKey rsaPrivate = (RSAPrivateKey) keyPair.getPrivate();
      PublicKey aPublic = keyPair.getPublic();
      System.out.println("aPublic = " + Base64.getEncoder().encodeToString(aPublic.getEncoded()));
//      //3.生成jwt
      Map<String,String> jwtContent = new HashMap<>(16);
      jwtContent.put("username","mamaomao");
      jwtContent.put("password","123456");

      Jwt jwt = JwtHelper.encode(new ObjectMapper().writeValueAsString(jwtContent), new RsaSigner(rsaPrivate));
      System.out.println("jwt = " + jwt);
      System.out.println("jwt.getEncoded() = " + jwt.getEncoded());
    }
}
