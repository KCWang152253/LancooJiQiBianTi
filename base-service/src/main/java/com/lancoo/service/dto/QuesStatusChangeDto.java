package com.lancoo.service.dto;

import lombok.Data;

import java.util.List;

/**
 * 试题审核、驳回、通过DTO
 */
@Data
public class QuesStatusChangeDto {
    private List<String> fixedIds;

    // 试题状态（0未提交；1已提交待审核；2驳回；3通过）
    private Integer status;

    private String reason;
}
