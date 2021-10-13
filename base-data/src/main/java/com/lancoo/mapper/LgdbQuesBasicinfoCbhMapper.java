package com.lancoo.mapper;


import com.lancoo.mysqlmapper.MysqlMapper;
import com.lancoo.pojo.LgdbQuesBasicinfoCbh;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Mapper
@Repository
public interface LgdbQuesBasicinfoCbhMapper extends MysqlMapper<LgdbQuesBasicinfoCbh> {

    ArrayList<LgdbQuesBasicinfoCbh> findLgdbQuesBasicinfoCbhByQuesType();

}