package com.foxesnovel.search.pojo;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.util.Date;

@Document(indexName = "bookinfo", type = "docs")
public class BookInfo {

    /**
     * 主键ID
     */
    @Id
    @Field(index = true, store = true, type = FieldType.Keyword)
    private Long id;

    /**
     * 小说名
     */
    @Field(index = true, store = true, type = FieldType.Text,analyzer = "ik_max_word")
    private String name;

    /**
     * 作者
     */
    @Field(index = true, store = true, type = FieldType.Text,analyzer = "ik_smart")
    private String author;

    /**
     * 简介
     */
    @Field(index = true, store = true, type = FieldType.Text)
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
    @Field(index = true, store = true, type = FieldType.Integer)
    private Integer channel;

    /**
     * 类别
     */
    @Field(index = true, store = true, type = FieldType.Integer)
    private Integer category;

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 是否完结，1为完结，0为未完结
     */
    @Field(index = true, store = true, type = FieldType.Integer)
    private Integer isDelete;
    @Field(index = true, store = true, type = FieldType.Integer)
    private Integer status;
    @Field(index = true, store = true, type = FieldType.Integer)
    private Integer isFree;
    @Field(index = true, store = true, type = FieldType.Integer)
    private Integer subscribeNum;

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

    /**
     * 总字数
     */
    @Field(index = true, store = true, type = FieldType.Integer)
    private Integer wordsNum;

    /**
     * 书籍图片地址
     */
    @Field(index = true, store = true, type = FieldType.Text)
    private String image;

    /**
     * 页面地址
     */
    @Field(index = true, store = true, type = FieldType.Text)
    private String html;

    /**
     * 是否通过审核，1为审核，0为未审核
     */
    @Field(index = true, store = true, type = FieldType.Integer)
    private Integer isVerify;

    @Field(index = true, store = true, type = FieldType.Keyword)
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(Integer isVerify) {
        this.isVerify = isVerify;
    }
}


