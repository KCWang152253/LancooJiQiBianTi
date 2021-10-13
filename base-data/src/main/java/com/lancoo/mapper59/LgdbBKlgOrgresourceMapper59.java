package com.lancoo.mapper59;


import com.lancoo.mysqlmapper.MysqlMapper;
import com.lancoo.pojo59.LgdbBKlgOrgresource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Mapper
@Repository
public interface LgdbBKlgOrgresourceMapper59 extends MysqlMapper<LgdbBKlgOrgresource> {

      ArrayList<LgdbBKlgOrgresource> selectBLgdbBKlgOrgresource();
}