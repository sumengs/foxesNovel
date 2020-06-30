package com.foxes.chapter.controller;

import com.foxes.chapter.service.ESBookSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;


@RequestMapping("/sweb")
@Controller
public class WebController {
@Autowired
private ESBookSearchService esBookSearchService;

    @RequestMapping("/search")
    public String toSearch(Model model){
        Map<String,String> map=new HashMap<>();
        map.put("keywords","");
        Map search = esBookSearchService.search(map);
        model.addAttribute("items",search);
        return "search";
    }
}
