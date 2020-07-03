package com.foxes.oauth2.controller;

import com.foxes.oauth2.service.AuthorizeService;
import com.foxes.oauth2.util.AuthToken;
import com.foxes.oauth2.util.CookieUtil;
import com.sumeng.peekshopping.constant.StatusCode;
import com.sumeng.peekshopping.entity.Result;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @Author GaoZeXi on 2020/6/16
 * @Description: Step by Step  and Stand on,  You Are The Best Investment!!!
 */
@Controller
@RequestMapping("/oauth")
public class AuthController {

    @Autowired
    private AuthorizeService authorizeService;

    @Value("${auth.clientId}")
    private String clientId;
    @Value("${auth.clientSecret}")
    private String clientSecret;
    @Value("${auth.cookieDomain}")
    private String cookieDomain;
    @Value("${auth.cookieMaxAge}")
    private int cookieMaxAge;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @RequestMapping("/login")
    @ResponseBody
    public Result<AuthToken> login(@RequestBody HashMap<String,String> map, HttpServletResponse response,
                                   String redirectURL){

        if(StringUtils.isEmpty(redirectURL)){
            redirectURL="index.html";
        }
        String username = map.get("username");
        String password = map.get("password");
        //校验参数
        if (StringUtils.isEmpty(username)){
            throw new RuntimeException("请输入用户名");
        }
        if (StringUtils.isEmpty(password)){
            throw new RuntimeException("请输入密码");
        }
        //申请令牌 authtoken
        AuthToken authToken = authorizeService.login(username, password, clientId, clientSecret);

        //将jti的值存入cookie中
        this.saveJtiToCookie(authToken.getJti(),response);
        HashMap<String, String> resultData = new HashMap<>(16);
        resultData.put("jti",authToken.getJti());
        resultData.put("redirectURL",redirectURL);
        resultData.put("loginUsername",username);

        //返回结果
        return new Result(true, StatusCode.OK,"登录成功",resultData);

    }
    @RequestMapping("/toLogin")
    public String toLogin(@RequestParam(value = "redirectURL",required = false,defaultValue = "")
                                      String redirectURL, Model model){
        //将原始url返回给登录页面
        model.addAttribute("redirectURL",redirectURL);
        return "login";
    }

    //将令牌的断标识jti存入到cookie中
    private void saveJtiToCookie(String jti, HttpServletResponse response) {
        CookieUtil.addCookie(response,cookieDomain,"/","uid",jti,cookieMaxAge,false);
//        logger.info("jti写入cookie成功  "+jti);
    }



}
