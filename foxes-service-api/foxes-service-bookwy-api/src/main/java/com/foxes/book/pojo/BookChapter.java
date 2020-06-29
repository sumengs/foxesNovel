package com.foxes.book.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_book_chapter")
public class BookChapter {
    /**
     * 章节ID
     */
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 章节名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 下一章章节ID，0为最后一章
     */
    @Column(name = "`next`")
    private String next;

    /**
     * 上一章章节Id，0为第一章
     */
    @Column(name = "pre")
    private String pre;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 该章字数
     */
    @Column(name = "words_num")
    private Integer wordsNum;

    /**
     * txt地址
     */
    @Column(name = "txt")
    private String txt;

    /**
     * 页面地址
     */
    @Column(name = "html")
    private String html;

    /**
     * 小说ID
     */
    @Column(name = "book_id")
    private String bookId;

    /**
     * 是否逻辑删除，1为删除，0为未删除
     */
    @Column(name = "is_delete")
    private Integer isDelete;

    /**
     * 是否通过审核，1为审核，0为未审核
     */
    @Column(name = "is_verify")
    private Integer isVerify;

    /**
     * 获取章节ID
     *
     * @return id - 章节ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置章节ID
     *
     * @param id 章节ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取章节名称
     *
     * @return name - 章节名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置章节名称
     *
     * @param name 章节名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取下一章章节ID，0为最后一章
     *
     * @return next - 下一章章节ID，0为最后一章
     */
    public String getNext() {
        return next;
    }

    /**
     * 设置下一章章节ID，0为最后一章
     *
     * @param next 下一章章节ID，0为最后一章
     */
    public void setNext(String next) {
        this.next = next;
    }

    /**
     * 获取上一章章节Id，0为第一章
     *
     * @return pre - 上一章章节Id，0为第一章
     */
    public String getPre() {
        return pre;
    }

    /**
     * 设置上一章章节Id，0为第一章
     *
     * @param pre 上一章章节Id，0为第一章
     */
    public void setPre(String pre) {
        this.pre = pre;
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
     * 获取该章字数
     *
     * @return words_num - 该章字数
     */
    public Integer getWordsNum() {
        return wordsNum;
    }

    /**
     * 设置该章字数
     *
     * @param wordsNum 该章字数
     */
    public void setWordsNum(Integer wordsNum) {
        this.wordsNum = wordsNum;
    }

    /**
     * 获取txt地址
     *
     * @return txt - txt地址
     */
    public String getTxt() {
        return txt;
    }

    /**
     * 设置txt地址
     *
     * @param txt txt地址
     */
    public void setTxt(String txt) {
        this.txt = txt;
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
     * 获取小说ID
     *
     * @return book_id - 小说ID
     */
    public String getBookId() {
        return bookId;
    }

    /**
     * 设置小说ID
     *
     * @param bookId 小说ID
     */
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    /**
     * 获取是否逻辑删除，1为删除，0为未删除
     *
     * @return is_delete - 是否逻辑删除，1为删除，0为未删除
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 设置是否逻辑删除，1为删除，0为未删除
     *
     * @param isDelete 是否逻辑删除，1为删除，0为未删除
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取是否通过审核，1为审核，0为未审核
     *
     * @return is_verify - 是否通过审核，1为审核，0为未审核
     */
    public Integer getIsVerify() {
        return isVerify;
    }

    /**
     * 设置是否通过审核，1为审核，0为未审核
     *
     * @param isVerify 是否通过审核，1为审核，0为未审核
     */
    public void setIsVerify(Integer isVerify) {
        this.isVerify = isVerify;
    }
}