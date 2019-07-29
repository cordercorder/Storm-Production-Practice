package com.example.demo.domain;

/**
 * @Author: 金任任
 * @Class: 计科1604
 * @Number: 2016014537
 */
public class DataTable {

    private long id;

    private String language;

    private long count;

    public DataTable(long id, String language, long count) {
        this.id = id;
        this.language = language;
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public String getLanguage() {
        return language;
    }

    public long getCount() {
        return count;
    }
}
