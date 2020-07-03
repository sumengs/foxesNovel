package com.foxes.chapter.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @date: 2020/7/3 15:46
 * @author: sumeng
 */
@Data
public class Leaderboard implements Serializable {


    private String bookId;
    private String category;
    private String bookName;
    private String bookAuthor;
    private Integer subscribeNum;
    private Integer readNum;
    private String bookImage;


}
