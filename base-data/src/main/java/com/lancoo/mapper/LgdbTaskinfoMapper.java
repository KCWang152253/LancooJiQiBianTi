package com.lancoo.mapper;

import com.lancoo.mysqlmapper.MysqlMapper;
import com.lancoo.pojo.LgdbTaskinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface LgdbTaskinfoMapper extends MysqlMapper<LgdbTaskinfo> {

    //根据TaskDate、Stage查询获取TaskId
    LgdbTaskinfo findLgdbTaskInfoByIds(@Param("TaskDate") String TaskDate, @Param("Stage") String Stage);





}