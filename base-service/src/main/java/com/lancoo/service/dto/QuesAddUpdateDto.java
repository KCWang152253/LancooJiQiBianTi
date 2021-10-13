package com.lancoo.service.dto;

import lombok.Data;

@Data
public class QuesAddUpdateDto {
    private String fixedId;
    //从哪个库查询,必填。教师个人库：private，学校公共库：public
    private String quesLib;
    
    private String questionTypeCode;

    private QuesContentDto quesContent;

    private String subject;

    private String diffcult;

    private String imporKnCode;

    //对应文件路径，多个用|分隔
    private String filePath;

    //试题状态（0未提交；1已提交待审核；2驳回；3通过）
    private Integer quesStatus;

    //驳回原因
    private String rejectedReason;

    //音频视频URL
    private String mediaurl;

    private QuesUserDto user;
}
