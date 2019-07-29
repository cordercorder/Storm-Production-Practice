package com.example.demo.service;

import com.example.demo.domain.DataTable;

/**
 * @Author: 金任任
 * @Class: 计科1604
 * @Number: 2016014537
 */
public interface StormService {

    public DataTable RequestData(long id);

    public long MinSubmissionId();
}
