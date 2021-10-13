package com.lancoo.mapper;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.lancoo.mysqlmapper.MysqlMapper;
import com.lancoo.pojo.LgdbBKlgOrgresource;
import com.lancoo.pojo.LgdbCKlgOrgresource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
@DS("59")
public interface LgdbCKlgOrgresourceMapper extends MysqlMapper<LgdbCKlgOrgresource> {

    //词汇填空题，单词提示   句子填空,N,21,单词提示,英语单词
    ArrayList<LgdbCKlgOrgresource> findLgdbCKlgOrgresources14(@Param("count") int count, @Param("Stage") String Stage);

    //可编知识点“英语词组”   翻译填空,N,22,无,无
    ArrayList<LgdbCKlgOrgresource> findLgdbCKlgOrgresources3(@Param("count") int count, @Param("Stage") String Stage);

    //可编知识点“英语单词英语词组”   句子填空,N,21,汉语提示,英语单词
    ArrayList<LgdbCKlgOrgresource> findLgdbCKlgOrgresources2(@Param("count") int count, @Param("Stage") String Stage);

    //可编知识点“英语词组”   句子填空,N,21,无提示,英语单词
    ArrayList<LgdbCKlgOrgresource> findLgdbCKlgOrgresources11(@Param("count") int count, @Param("Stage") String Stage);

    //可编知识点“英语词组”   句子填空,N,21,首字母提示,英语单词
    ArrayList<LgdbCKlgOrgresource> findLgdbCKlgOrgresources12(@Param("count") int count, @Param("Stage") String Stage);

    //可编知识点“英语词组”   句子填空,N,21,音标提示,英语单词
    ArrayList<LgdbCKlgOrgresource> findLgdbCKlgOrgresources13(@Param("count") int count, @Param("Stage") String Stage);

    //词汇填空题，首字母+汉语    句子填空,N,21,首字母+汉语,英语单词
    ArrayList<LgdbCKlgOrgresource> findLgdbCKlgOrgresources15(@Param("count") int count, @Param("Stage") String Stage);


    //更新去重
    void LgdbCKlgOrgresourceUpdate(@Param("Stage") String Stage, @Param("QuesType") String QuesType, @Param("ID") Long ID);


    //对素材进行去重
    LgdbCKlgOrgresource findQuesTypeByIdStage(@Param("id") long id, @Param("Stage") String Stage);


    //可编知识点“英语词组”   C阶段  单词题,A,13,英语单词英语词组
    ArrayList<LgdbCKlgOrgresource> findLgdbCKlgOrgresources10(@Param("count") int count, @Param("Stage") String Stage);

    //'英语常用表达'
    ArrayList<LgdbCKlgOrgresource> findLgdbCKlgOrgresources8(@Param("count") int count, @Param("Stage") String Stage);


}