package com.lancoo.service.dto;

import lombok.Data;
import lombok.ToString;

/**
 *
 *
 *
 * 题型，题材数量等选择参数
 * @author KCWang
 * @date 2021/7/28 16:09
 * @Email:KCWang@aliyun.com
 */

@ToString
@Data
public class JiQiChoseDao {

    // 单选题(选择题 中学阶段目前不造)
    private String danxuan;
    //匹配
    private String pipei;
    //词汇填空
    private  String cihuitiankong;
    //连词成句
    private  String liancichengju;
    //翻译题
    private String fanyiti;
    //素材数
    private  int shucaishu;
    //阶段
    private String stage;
}
