package com.foxes.user.feign;

import com.foxes.user.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author GaoZeXi on 2020/7/2
 * @Description: Step by Step  and Stand on,  You Are The Best Investment!!!
 */
@FeignClient(name = "user")
public interface UserFeign {

    /***
     * 根据username查询用户数据
     * @param username
     * @return
     */
    @GetMapping("/user/{username}")
    User findByUsername(@PathVariable("username") String username);
}
