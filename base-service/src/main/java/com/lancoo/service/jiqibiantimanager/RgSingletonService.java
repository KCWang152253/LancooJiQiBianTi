package com.lancoo.service.jiqibiantimanager;

import com.lancoo.service.dto.jiqiDao.BasicSysconfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author KCWang
 * @date 2021/8/2 20:49
 * @Email:KCWang@aliyun.com
 */

@Slf4j
@Service
public class RgSingletonService {
    @Autowired
   private EnglishQuesRecognitionService englishQuesRecognitionService;

    public static String connStr = "http://60.190.136.238:32258/";
    private RgSingletonService() { }
    public static EnglishQuesRecognitionService instance = new RgSingletonService().RG();
    public EnglishQuesRecognitionService RG(){
        String url = String.format("{0}/QuestionsOperation.asmx/GetAllBasicSysconfig", connStr);
        HashMap<String, String> dic = new HashMap<>();
        dic.put("ServerID", "CollectResFtp");
        ArrayList<BasicSysconfig> bscLst = new ArrayList<>();
//        var bscLst = HttpPostExt.HttpRequest<List<BasicSysconfig>>(dic, url, RequestType.POST, ArgsReceiveType.Formurlencoded);/
        //数字化识别
        return new EnglishQuesRecognitionService("C", "B", bscLst);

    }

}
