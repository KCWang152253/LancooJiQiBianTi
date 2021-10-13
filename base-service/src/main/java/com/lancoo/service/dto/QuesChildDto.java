package com.lancoo.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuesChildDto {
    //试题序号
    @JsonProperty("index")
    private String index;
    //试题题目内容
    @JsonProperty("QuesAnswer")
    private String quesAnswer;
    //试题解析
    @JsonProperty("QuesAnalyze")
    private String quesAnalyze;
    //试题分值
    @JsonProperty("QuesScore")
    private String quesScore;
    //作答方式
    @JsonProperty("AnswerType")
    private String answerType;
}
