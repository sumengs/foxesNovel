package com.foxes.book.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @date: 2020/6/30 9:22
 * @author: lenaminz
 */
@Data
@Table(name = "tb_category")
public class Category implements Serializable {
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


}
