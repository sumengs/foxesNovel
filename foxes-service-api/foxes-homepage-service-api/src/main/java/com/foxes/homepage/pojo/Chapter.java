package com.foxes.homepage.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @date: 2020/6/27 21:56
 * @author: sumeng
 */
@Data
@Table(name = "tb_book_chapter")
public class Chapter {

    /**
     * 章节ID
     */
    @Id
    private String id;

    /**
     * 章节名称
     */
    private String name;

    /**
     * 下一章章节ID，0为最后一章
     */
    private String next;

    /**
     * 上一章章节Id，0为第一章
     */
    private String pre;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * txt地址
     */
    private String txt;

    /**
     * 该章字数
     */
    private Integer wordsNum;

    /**
     * 页面地址
     */
    private String html;

    /**
     * 小说ID
     */
    private String bookId;

    /**
     * 0为未删除，1为删除
     */
    private Integer isDelete;

    /**
     * 是否通过审核，1为审核，0为未审核
     */
    private Integer isVerify;

}
