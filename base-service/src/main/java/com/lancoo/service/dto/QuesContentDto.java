package com.lancoo.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 试题内容DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuesContentDto {
	//试题文章内容
    @JsonProperty("QuesBody")
    private String quesBody;

    private List<QuesChildDto> quesChild;
}
