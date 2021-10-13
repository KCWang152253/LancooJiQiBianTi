package com.lancoo.service.answer;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.lancoo.mapper.RightAnswerDisturbMapper;
import com.lancoo.pojo.RightAnswerDisturb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author KCWang
 * @date 2021/9/26 16:20
 * @Email:KCWang@aliyun.com
 */

@DS("173")
@Slf4j
@Service
public class AmswerInsertService {
    @Autowired
    RightAnswerDisturbMapper rightAnswerDisturbMapper;

    public void  insertAnswer(String answer, ArrayList<String> list){
        RightAnswerDisturb rightAnswerDisturb = new RightAnswerDisturb();
        if(list.size()==3){
            rightAnswerDisturb.setRigha(answer);
            rightAnswerDisturb.setDisturbb(list.get(0));
            rightAnswerDisturb.setDisturbc(list.get(1));
            rightAnswerDisturb.setDisturbd(list.get(2));
            String s = UUID.randomUUID().toString().replace("-", "");
            rightAnswerDisturb.setGuid(s);
            rightAnswerDisturbMapper.insert(rightAnswerDisturb);
        }else if(list.size()==2){
            rightAnswerDisturb.setRigha(answer);
            rightAnswerDisturb.setDisturbb(list.get(0));
            rightAnswerDisturb.setDisturbc(list.get(1));
            String s = UUID.randomUUID().toString().replace("-", "");
            rightAnswerDisturb.setGuid(s);
            rightAnswerDisturbMapper.insert(rightAnswerDisturb);
        }

    }

}
