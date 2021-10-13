package com.lancoo.mapper;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.lancoo.mysqlmapper.MysqlMapper;
import com.lancoo.pojo.LgdbQuesBasicinfoCch;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
@DS("59")
public interface LgdbQuesBasicinfoCchMapper extends MysqlMapper<LgdbQuesBasicinfoCch> {

}