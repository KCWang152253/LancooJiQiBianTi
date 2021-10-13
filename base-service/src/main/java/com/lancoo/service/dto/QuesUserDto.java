package com.lancoo.service.dto;

import lombok.Data;

@Data
public class QuesUserDto {
    private String userId;
    private String userName;
    //角色：1-管理员，2-教师
    private String role;
}
