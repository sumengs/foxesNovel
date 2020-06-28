package com.foxes.book.pojo;

import javax.persistence.*;

@Table(name = "tb_category")
public class Category {
    /**
     * 类别ID
     */
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 类别名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 排序
     */
    @Column(name = "seq")
    private Integer seq;

    /**
     * 获取类别ID
     *
     * @return id - 类别ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置类别ID
     *
     * @param id 类别ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取类别名称
     *
     * @return name - 类别名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置类别名称
     *
     * @param name 类别名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取排序
     *
     * @return seq - 排序
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * 设置排序
     *
     * @param seq 排序
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}