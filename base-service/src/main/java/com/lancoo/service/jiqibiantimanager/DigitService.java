package com.lancoo.service.jiqibiantimanager;

import com.lancoo.service.dto.jiqiDao.DigitQuesDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author KCWang
 * @date 2021/8/3 15:00
 * @Email:KCWang@aliyun.com
 */


@Slf4j
@Service
public class DigitService {

    EnglishQuesRecognitionService ER = RgSingletonService.instance;//初始化


    public DigitQuesDao digitalMethod(String Subject, String Stage, String Content, String QuesType, int genre){
        return null;
    }

}
