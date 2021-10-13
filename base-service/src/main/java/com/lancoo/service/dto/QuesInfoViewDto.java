package com.lancoo.service.dto;

import java.util.Date;

import lombok.Data;
@Data
public class QuesInfoViewDto {

	private String fixedId;

    private String questionTypeCode;

    private QuesContentDto quesContent;

    private Date createDate;

    private String createUser;

    private Date updateDate;

    private String subject;

    private String diffcult;

    private String imporKnCode;

    private String imporKnText;

    private String filePath;

    private Integer isMark;
    //试题状态（0未提交；1已提交待审核；2驳回；3通过）
    private Integer quesStatus;  
    
}
