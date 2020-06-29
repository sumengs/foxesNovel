package com.foxes.chapter.controller;


import com.foxes.chapter.service.ESBookSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/search")
@RestController
public class ESSearchServiceController {
    @Autowired
    private ESBookSearchService esBookSearchService;

    @GetMapping("/map")
    public Map findByMap(@RequestParam Map<String, String> map) {

        if (map==null||map.size()==0){
            return null;
        }
        Map search = esBookSearchService.search(map);

        return search;
    }
}
