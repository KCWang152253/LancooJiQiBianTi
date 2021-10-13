package com.lancoo.service.dto;

import lombok.Data;

import java.util.Date;

@Data
public class QuesViewDto {
    private String fixedId;

    private String questionTypeCode;

    private String questionTypeName;

    private QuesContentDto quesContent;

    private Date createDate;

    private String createUser;

    private Date updateDate;

    private String subject;

    private String diffcult;

    private String imporKnCode;

    private String imporKnText;

    private String filePath;

    //试题状态（0未提交；1已提交待审核；2驳回；3通过）
    private Integer quesStatus;     

    private String rejectedReason;

    //音频视频URL
    private String mediaurl;
}
