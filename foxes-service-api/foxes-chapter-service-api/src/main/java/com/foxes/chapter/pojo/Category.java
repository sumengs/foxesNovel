package com.foxes.chapter.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @date: 2020/6/30 11:11
 * @author: sumeng
 */

@Data
@Table(name = "tb_category")
public class Category {


    /**
     * 分类Id
     */
    @Id
    private Integer id;


    /**
     * 分类名称
     */
    private String name;


    /**
     * 分类排序
     */
    private Integer seq;
}
