package com.foxesnovel.search.pojo;


import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.Date;

/**
 * @date: 2020/6/27 21:41
 * @author: sumeng
 */

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    public Integer getSubscribeNum() {
        return subscribeNum;
    }

    public void setSubscribeNum(Integer subscribeNum) {
        this.subscribeNum = subscribeNum;
    }

    public Integer getWordsNum() {
        return wordsNum;
    }

    public void setWordsNum(Integer wordsNum) {
        this.wordsNum = wordsNum;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(Integer isVerify) {
        this.isVerify = isVerify;
    }
}
