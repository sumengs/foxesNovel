package com.foxes.book.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_book")
public class Book {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 小说名
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 作者
     */
    @Column(name = "author")
    private String author;

    /**
     * 简介
     */
    @Column(name = "abstracts")
    private String abstracts;

    /**
     * 发布时间
     */
    @Column(name = "release_time")
    private Date releaseTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 1为男频 2为女频
     */
    @Column(name = "channel")
    private Integer channel;

    /**
     * 类别
     */
    @Column(name = "category")
    private Integer category;

    /**
     * 是否完结，1为完结，0为未完结
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * 1为免费，0为付费
     */
    @Column(name = "is_free")
    private Integer isFree;

    /**
     * 订阅数
     */
    @Column(name = "subscribe_num")
    private Integer subscribeNum;

    /**
     * 总字数
     */
    @Column(name = "words_num")
    private Integer wordsNum;

    /**
     * 书籍图片地址
     */
    @Column(name = "image")
    private String image;

    /**
     * 0为未审核，1为删除，2为已审核
     */
    @Column(name = "is_delete")
    private Integer isDelete;

    /**
     * 页面地址
     */
    @Column(name = "html")
    private String html;

    /**
     * 是否通过审核，1为审核，0为未审核
     */
    @Column(name = "is_verify")
    private String isVerify;

    /**
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取小说名
     *
     * @return name - 小说名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置小说名
     *
     * @param name 小说名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取作者
     *
     * @return author - 作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置作者
     *
     * @param author 作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取简介
     *
     * @return abstracts - 简介
     */
    public String getAbstracts() {
        return abstracts;
    }

    /**
     * 设置简介
     *
     * @param abstracts 简介
     */
    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    /**
     * 获取发布时间
     *
     * @return release_time - 发布时间
     */
    public Date getReleaseTime() {
        return releaseTime;
    }

    /**
     * 设置发布时间
     *
     * @param releaseTime 发布时间
     */
    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取1为男频 2为女频
     *
     * @return channel - 1为男频 2为女频
     */
    public Integer getChannel() {
        return channel;
    }

    /**
     * 设置1为男频 2为女频
     *
     * @param channel 1为男频 2为女频
     */
    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    /**
     * 获取类别
     *
     * @return category - 类别
     */
    public Integer getCategory() {
        return category;
    }

    /**
     * 设置类别
     *
     * @param category 类别
     */
    public void setCategory(Integer category) {
        this.category = category;
    }

    /**
     * 获取是否完结，1为完结，0为未完结
     *
     * @return status - 是否完结，1为完结，0为未完结
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置是否完结，1为完结，0为未完结
     *
     * @param status 是否完结，1为完结，0为未完结
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取1为免费，0为付费
     *
     * @return is_free - 1为免费，0为付费
     */
    public Integer getIsFree() {
        return isFree;
    }

    /**
     * 设置1为免费，0为付费
     *
     * @param isFree 1为免费，0为付费
     */
    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    /**
     * 获取订阅数
     *
     * @return subscribe_num - 订阅数
     */
    public Integer getSubscribeNum() {
        return subscribeNum;
    }

    /**
     * 设置订阅数
     *
     * @param subscribeNum 订阅数
     */
    public void setSubscribeNum(Integer subscribeNum) {
        this.subscribeNum = subscribeNum;
    }

    /**
     * 获取总字数
     *
     * @return words_num - 总字数
     */
    public Integer getWordsNum() {
        return wordsNum;
    }

    /**
     * 设置总字数
     *
     * @param wordsNum 总字数
     */
    public void setWordsNum(Integer wordsNum) {
        this.wordsNum = wordsNum;
    }

    /**
     * 获取书籍图片地址
     *
     * @return image - 书籍图片地址
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置书籍图片地址
     *
     * @param image 书籍图片地址
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取0为未审核，1为删除，2为已审核
     *
     * @return is_delete - 0为未审核，1为删除，2为已审核
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 设置0为未审核，1为删除，2为已审核
     *
     * @param isDelete 0为未审核，1为删除，2为已审核
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取页面地址
     *
     * @return html - 页面地址
     */
    public String getHtml() {
        return html;
    }

    /**
     * 设置页面地址
     *
     * @param html 页面地址
     */
    public void setHtml(String html) {
        this.html = html;
    }

    /**
     * 获取是否通过审核，1为审核，0为未审核
     *
     * @return is_verify - 是否通过审核，1为审核，0为未审核
     */
    public String getIsVerify() {
        return isVerify;
    }

    /**
     * 设置是否通过审核，1为审核，0为未审核
     *
     * @param isVerify 是否通过审核，1为审核，0为未审核
     */
    public void setIsVerify(String isVerify) {
        this.isVerify = isVerify;
    }
}