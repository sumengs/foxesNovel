package com.foxes.book.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @date: 2020/6/30 9:22
 * @author: lenaminz
 */
@Data
@Table(name = "tb_category")
public class Category {
    /**
     * 主键ID
     */
    @Id
    private Integer id;

    /**
     * 类别名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer seq;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}
