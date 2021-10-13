package com.lancoo.mapper;


import com.lancoo.mysqlmapper.MysqlMapper;
import com.lancoo.pojo.LgdbBasicQuestype;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LgdbBasicQuestypeMapper extends MysqlMapper<LgdbBasicQuestype> {



}