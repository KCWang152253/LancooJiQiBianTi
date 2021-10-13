package com.lancoo.service.dto.jiqiDao;

import lombok.Data;
import lombok.ToString;

/**
 * @author KCWang
 * @date 2021/8/3 14:33
 * @Email:KCWang@aliyun.com
 */
@Data
@ToString
public class QuesBasicinfoDao {


    public String TaskId ;
    public String ResMd5Code ;
    public String Guid ;
    public String QuesContent ;
    public String QuesType ;
    public Long QuesChildnum ;
    public Long QuesChildcount;
    public String KnowledgeName ;
    public DigitQuesDao digitQues ;
    public String Subject ;
    public String Stage ;


}
