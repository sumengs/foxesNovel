package com.foxes.chapter.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @date: 2020/7/2 14:05
 * @author: sumeng
 */
@Data
@Table(name = "tb_ad")
public class Advertisement {

    @Id
    private Integer id;

    private String name;


    private String position;


    private Date startTime;


    private Date endTime;

    private String url;

    private String image;

    private String author;
}
