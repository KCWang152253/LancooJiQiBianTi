package com.lancoo.service.answer;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.lancoo.mapper.LgdbResviewCbSubverquesviewMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author KCWang
 * @date 2021/9/24 15:44
 * @Email:KCWang@aliyun.com
 */


@Slf4j
@Service
@DS("138")
public class AnswerService {


    @Autowired
    LgdbResviewCbSubverquesviewMapper lgdbResviewCbSubverquesviewMapper;

    public ArrayList<String> getAnswers() {
        ArrayList<String> quesContent = lgdbResviewCbSubverquesviewMapper.selectQuesContent();
        return quesContent;
    }
}
