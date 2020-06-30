package com.foxesnovel.search.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient("search")
public interface SearchFeign {

    @GetMapping("/search/map")
    public Map findByMap(@RequestParam Map<String, String> map);
}
