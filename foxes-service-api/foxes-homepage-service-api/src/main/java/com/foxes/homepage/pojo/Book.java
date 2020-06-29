package com.foxes.homepage.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @date: 2020/6/27 21:41
 * @author: sumeng
 */
@Data
@Table(name = "tb_book")
public class Book {

    /**
     * 主键ID
     */
    @Id
    private String id;

    /**
     * 小说名
     */
    private String name;

    /**
     * 作者
     */
    private String author;

    /**
     * 简介
     */
    private String abstracts;

    /**
     * 发布时间
     */
    private Date releaseTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 1为男频 0为女频
     */
    private Integer channel;

    /**
     * 类别
     */
    private Integer category;

    /**
     * 是否完结，1为完结，0为未完结
     */
    private Integer status;

    /**
     * 1为免费，0为付费
     */
    private Integer isFree;

    /**
     * 订阅数
     */
    private Integer subscribeNum;

    /**
     * 总字数
     */
    private Integer wordsNum;

    /**
     * 书籍图片地址
     */
    private String image;

    /**
     * 页面地址
     */
    private String html;

    /**
     * 0为未删除，1为删除
     */
    private Integer isDelete;

    /**
     * 是否通过审核，1为审核，0为未审核
     */
    private Integer isVerify;

}
