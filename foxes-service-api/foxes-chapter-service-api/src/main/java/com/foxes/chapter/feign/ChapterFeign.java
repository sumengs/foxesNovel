package com.foxes.chapter.feign;

import com.foxes.chapter.pojo.Chapter;
import com.sumeng.peekshopping.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @date: 2020/6/30 13:14
 * @author: sumeng
 */
@FeignClient(name = "chapter-service")
public interface ChapterFeign {

    /**
     * 根据章节Id查询章节信息
     *
     * @param chapterId 章节Id
     * @return 章节信息
     */
    @GetMapping("/chapter/findById/{id}")
    public Result<Chapter> findChapterById(@PathVariable("id") String chapterId);
}
