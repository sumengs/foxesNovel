package com.foxes.chapter.service;

public interface ESFoxManageService {
    /**
     * 创建索引
     */
    void createIndex();

    /**
     *
     */
    void importAll();

    void importById(String bookId);

    void deleteById(String bookId);
}
