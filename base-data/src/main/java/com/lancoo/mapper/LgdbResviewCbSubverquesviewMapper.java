package com.lancoo.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.lancoo.mysqlmapper.MysqlMapper;
import com.lancoo.pojo.LgdbResviewCbSubverquesview;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
@DS("138")
public interface LgdbResviewCbSubverquesviewMapper extends MysqlMapper<LgdbResviewCbSubverquesview> {
    ArrayList<String>  selectQuesContent();
}

//@Mapper
//public interface LgdbResviewCbSubverquesviewMapper extends tk.mybatis.mapper.common.Mapper<LgdbResviewCbSubverquesview>{
//    ArrayList<String>  selectQuesContent();
//}