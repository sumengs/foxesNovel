package com.foxes.chapter.pojo;

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

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPre() {
        return pre;
    }

    public void setPre(String pre) {
        this.pre = pre;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public Integer getWordsNum() {
        return wordsNum;
    }

    public void setWordsNum(Integer wordsNum) {
        this.wordsNum = wordsNum;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
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
