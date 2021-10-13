package com.lancoo.service.dto;

import lombok.Data;

/**
 * 试题分页查询入参
 */
@Data
public class QuesPageParamDto {
    //不填或填写不对，默认为1
    private Integer pageNo;
    //不填或填写不对，默认为10
    private Integer pageSize;
    //从哪个库查询,必填。教师个人库：private，学校公共库：public
    private String quesLib;
    //学科
    private String subject;
    //题目类型
    private String quesTypeCode;
    //难度
    private String diffcult;
    //知识点
    private String imporKnText;
    //作者
    private String createUser;
    //题型名称
    private String questypeName;
    //输入搜索内容，用于模糊匹配
    private String content;
}
