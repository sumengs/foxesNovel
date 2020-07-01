package com.foxes.chapter.controller;

import com.foxes.chapter.service.ESBookSearchService;
import com.foxesnovel.search.pojo.BookInfo;
import com.sumeng.peekshopping.entity.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;


@RequestMapping("/sweb")
@Controller
public class WebController {
@Autowired
private ESBookSearchService esBookSearchService;

    @RequestMapping("/search")
    public String toSearch(@RequestParam Map<String,String> map, Model model){

        if (map==null||map.size()==0){
            map.put("keywords","");
        }
        Map search = esBookSearchService.search(map);
        model.addAttribute("searchMap",map);
        model.addAttribute("items",search);

        Page<BookInfo> bookInfoPage = new Page<BookInfo>(
                Long.parseLong(String.valueOf(search.get("total"))),
                Integer.parseInt(String.valueOf(search.get("pageNum"))),
                Integer.parseInt(String.valueOf(search.get("pageSize")))
        );
        model.addAttribute("page",bookInfoPage);
        StringBuilder url = new StringBuilder("/sweb/search?");
        for (String s : map.keySet()) {
            if (!s.equals("sortField")&&!s.equals("sortRule")&&!s.equals("pageNum")&&!s.equals("pageSize")&&StringUtils.isNotEmpty(map.get(s))){
                url.append(s).append("=").append(map.get(s)).append("&");
            }
        }
        String urls = url.toString().substring(0, url.length() - 1);
        model.addAttribute("url",urls);
        return "search";
    }
}
