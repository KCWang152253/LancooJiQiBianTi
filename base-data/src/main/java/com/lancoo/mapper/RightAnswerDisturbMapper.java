package com.lancoo.mapper;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.lancoo.mysqlmapper.MysqlMapper;
import com.lancoo.pojo.RightAnswerDisturb;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Mapper
@Repository
@DS("173")
public interface RightAnswerDisturbMapper extends MysqlMapper<RightAnswerDisturb> {

    //以目标知识点为正确选项在选项库中查找
    ArrayList<RightAnswerDisturb> selectAnswers(@Param("knowledgename") String knowledgename);

    //以目标知识点为非正确选项在选项库中查找
    ArrayList<RightAnswerDisturb> selectAnswersByDisturb(@Param("knowledgename") String knowledgename);

}