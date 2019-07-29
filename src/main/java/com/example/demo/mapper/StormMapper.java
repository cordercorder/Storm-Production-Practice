package com.example.demo.mapper;

import com.example.demo.domain.DataTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: 金任任
 * @Class: 计科1604
 * @Number: 2016014537
 */

@Mapper
public interface StormMapper {


    @Select("select data.id,data.language,data.count from data where data.id=#{id}")
    public DataTable DataQuery(long id);

    @Select("select min(data.id) from data")
    public long MinSubmissionId();
}