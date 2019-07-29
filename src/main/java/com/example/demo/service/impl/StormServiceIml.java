package com.example.demo.service.impl;

import com.example.demo.domain.DataTable;
import com.example.demo.mapper.StormMapper;
import com.example.demo.service.StormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 金任任
 * @Class: 计科1604
 * @Number: 2016014537
 */
@Service
public class StormServiceIml implements StormService {

    @Autowired
    private StormMapper stormMapper;

    @Override
    public DataTable RequestData(long id) {
        return stormMapper.DataQuery(id);
    }

    @Override
    public long MinSubmissionId() {
        System.out.println("okokok");
        return stormMapper.MinSubmissionId();
    }
}
