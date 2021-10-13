package com.lancoo.mapper;

import com.lancoo.mysqlmapper.MysqlMapper;
import com.lancoo.pojo.LgdbBasicKnowledgeandcode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface LgdbBasicKnowledgeandcodeMapper extends MysqlMapper<LgdbBasicKnowledgeandcode> {

    String selectThemeByKnowledeCode(@Param("knowledgecode") String knowledgecode);

    //查找同一目标知识点下的所有Theme
    ArrayList<String> selectThemeByKnowledge(@Param("Knowledge") String Knowledge);




     ArrayList<String>  selectThemes();

}