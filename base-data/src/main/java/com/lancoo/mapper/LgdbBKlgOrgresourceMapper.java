package com.lancoo.mapper;

import com.lancoo.mysqlmapper.MysqlMapper;
import com.lancoo.pojo.LgdbBKlgOrgresource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Mapper
@Repository
public interface LgdbBKlgOrgresourceMapper extends MysqlMapper<LgdbBKlgOrgresource> {
    //可编知识点“英语单词”
    ArrayList<LgdbBKlgOrgresource> findLgdbBKlgOrgresources(@Param("count") int count, @Param("Stage") String Stage);


    //'英语常用表达','英语词组'
    ArrayList<LgdbBKlgOrgresource> findLgdbBKlgOrgresources1(@Param("count") int count, @Param("Stage") String Stage);
    //可编知识点“英语单词英语词组”
    ArrayList<LgdbBKlgOrgresource> findLgdbBKlgOrgresources2(@Param("count") int count, @Param("Stage") String Stage);

    //可编知识点“英语单词英语词组”  连词成句
    ArrayList<LgdbBKlgOrgresource> findLgdbBKlgOrgresources22(@Param("count") int count, @Param("Stage") String Stage);

    //可编知识点“英语单词英语词组”   翻译
    ArrayList<LgdbBKlgOrgresource> findLgdbBKlgOrgresources23(@Param("count") int count, @Param("Stage") String Stage);

    //'英语常用表达'  匹配题  词汇匹配 单词
    ArrayList<LgdbBKlgOrgresource> findLgdbBKlgOrgresources24(@Param("count") int count, @Param("Stage") String Stage);

    //'英语常用表达'  匹配题  词汇匹配 词组
    ArrayList<LgdbBKlgOrgresource> findLgdbBKlgOrgresources26(@Param("count") int count, @Param("Stage") String Stage);

    //'英语常用表达'  匹配题  对话匹配
    ArrayList<LgdbBKlgOrgresource> findLgdbBKlgOrgresources25(@Param("count") int count, @Param("Stage") String Stage);

    //可编知识点“英语词组”
    ArrayList<LgdbBKlgOrgresource> findLgdbBKlgOrgresources3(@Param("count") int count, @Param("Stage") String Stage);

    //选词填空,N,20,复合属性,英语单词,屏蔽情态动词及助动词，实意动词及系动词
    ArrayList<LgdbBKlgOrgresource> findLgdbBKlgOrgresources4(@Param("count") int count, @Param("Stage") String Stage);



    //情态动词及助动词，实意动词及系动词
    ArrayList<LgdbBKlgOrgresource> findLgdbBKlgOrgresources5(@Param("count") int count, @Param("Stage") String Stage);

    //没有范围
    ArrayList<LgdbBKlgOrgresource> findLgdbBKlgOrgresources6(@Param("count") int count, @Param("Stage") String Stage);

    //根据主题查素材
    LgdbBKlgOrgresource findLgdbBKlgOrgresources7(@Param("theme") String theme, @Param("Stage") String Stage);



    //'英语常用表达'
    ArrayList<LgdbBKlgOrgresource> findLgdbBKlgOrgresources8(@Param("count") int count, @Param("Stage") String Stage);

    //查跟目标知识点不在同一主题下的干扰项
    ArrayList<String> findLgdbBKlgOrgresources9(@Param("theme") String theme,@Param("Stage") String Stage, @Param("knowledgetype") String knowledgetype);

    //可编知识点“英语词组”   单词题,A,13,英语单词英语词组
    ArrayList<LgdbBKlgOrgresource> findLgdbBKlgOrgresources10(@Param("count") int count, @Param("Stage") String Stage);

    //可编知识点“英语词组”   句子填空,N,21,无提示,英语单词
    ArrayList<LgdbBKlgOrgresource> findLgdbBKlgOrgresources11(@Param("count") int count, @Param("Stage") String Stage);

    //可编知识点“英语词组”   句子填空,N,21,首字母提示,英语单词
    ArrayList<LgdbBKlgOrgresource> findLgdbBKlgOrgresources12(@Param("count") int count, @Param("Stage") String Stage);

    //可编知识点“英语词组”   句子填空,N,21,音标提示,英语单词
    ArrayList<LgdbBKlgOrgresource> findLgdbBKlgOrgresources13(@Param("count") int count, @Param("Stage") String Stage);

    //词汇填空题，单词提示   句子填空,N,21,单词提示,英语单词
    ArrayList<LgdbBKlgOrgresource> findLgdbBKlgOrgresources14(@Param("count") int count, @Param("Stage") String Stage);


    //词汇填空题，首字母+汉语    句子填空,N,21,首字母+汉语,英语单词

    ArrayList<LgdbBKlgOrgresource> findLgdbBKlgOrgresources15(@Param("count") int count, @Param("Stage") String Stage);

   // 选词填空,N,20,动词复合属性,英语单词  包含'实意动词及系动词'

    ArrayList<LgdbBKlgOrgresource> findLgdbBKlgOrgresources16(@Param("count") int count, @Param("Stage") String Stage);

    // 选词填空,N,20,动词复合属性,英语单词  不包含'实意动词及系动词'

    ArrayList<LgdbBKlgOrgresource> findLgdbBKlgOrgresources17(@Param("count") int count, @Param("Stage") String Stage);






    LgdbBKlgOrgresource findQuesTypeByIdStage(@Param("id") long id, @Param("Stage") String Stage);

    ArrayList<String> findLgdbBKlgOrgresourcesByIsDeleteIsSelect(@Param("number") String number);


    ArrayList<String> findLgdbBKlgOrgresourcesExpressBy(@Param("number") String number);





    void LgdbBKlgOrgresourceUpdate(@Param("Stage") String Stage, @Param("QuesType") String QuesType, @Param("ID") Long ID);



    //59数据库
    ArrayList<LgdbBKlgOrgresource> selectBLgdbBKlgOrgresource();

}