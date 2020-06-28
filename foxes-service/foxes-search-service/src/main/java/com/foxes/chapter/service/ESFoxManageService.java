package com.foxes.chapter.service;

public interface ESFoxManageService {

    void createIndex();
    void importAll();
    void importById(String bookId);
    void deleteById(String bookId);
}
