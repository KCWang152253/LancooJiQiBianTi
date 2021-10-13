package com.lancoo.mapper;

import com.lancoo.mysqlmapper.MysqlMapper;
import com.lancoo.pojo.LgdbBasicQuestionclassifyname;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LgdbBasicQuestionclassifynameMapper extends MysqlMapper<LgdbBasicQuestionclassifyname> {
    String selectNewproperty(@Param("newProperty") String newProperty );
}