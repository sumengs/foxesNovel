package com.foxes.chapter.controller;


import com.foxes.chapter.service.ESBookSearchService;
import com.sumeng.peekshopping.constant.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sumeng.peekshopping.entity.Result;
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
    @GetMapping("/keywords")
    public Result<Map<String,Object>> findByKeywors(@RequestParam Map<String, String> map) {

        if (map==null||map.size()==0){
            return null;
        }
        Map search = esBookSearchService.search(map);


        return new Result<>(true, StatusCode.OK,"查询成功",map);
    }
}
