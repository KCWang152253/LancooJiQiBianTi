package com.lancoo.service.jiqibiantimanager;

import com.lancoo.service.dto.jiqiDao.BasicSysconfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author KCWang
 * @date 2021/8/2 21:02
 * @Email:KCWang@aliyun.com
 */


@Service
public class EnglishQuesRecognitionService {

//    static extern int initTestRecognition(String ip, String port);


//        #region 字段和属性
    private String Subject;
    private String Stage;
    private String LogPath;
    public String log;
    public String log2;
    public String log3;
    private ArrayList<BasicSysconfig> bscLst = new ArrayList<BasicSysconfig>();


    public  EnglishQuesRecognitionService(){

    }


    public EnglishQuesRecognitionService(String subject, String stage, ArrayList<BasicSysconfig> _bscLst){
        this.Subject = subject;
        this.Stage = stage;
        bscLst = new ArrayList<BasicSysconfig>();
        this.bscLst = _bscLst;

        this.LogPath = System.getProperty("user.dir")+ String.format("EnglishQuesRecognitionLog.txt");
//        log = new Logger(AppDomain.CurrentDomain.BaseDirectory, "argsLog.txt");
//        log2 = new Logger(AppDomain.CurrentDomain.BaseDirectory, "RecongsLog.txt");
//        log3 = new Logger(AppDomain.CurrentDomain.BaseDirectory, "EnglishQuesRecognitionLog.txt");
        log =System.getProperty("user.dir")+  "argsLog.txt";
        log2 = System.getProperty("user.dir")+  "RecongsLog.txt";
        log3 = System.getProperty("user.dir")+ "EnglishQuesRecognitionLog.txt";
        BasicSysconfig config2;
        for (BasicSysconfig m: bscLst) {
            if(m.getServerID().equals("NewcodeWS")){
                 config2 = m;
            }else {
                 config2 = bscLst.get(0);
            }

        }
        Init();
    }



    public Boolean Init(){
//        System.Threading.Thread.Sleep(100);

        BasicSysconfig config;
        for (BasicSysconfig m: bscLst) {
            if(m.getServerID().equals("LgRecognitionWS")){
                config = m;
            }else {
                config = bscLst.get(0);
            }

        }
//        int r = -99;
//        CallWithTimeout(() =>
//                {
//                        r = initTestRecognition("124.71.32.70", "10110/WS_AllSubjectKlgRec");
//        // r = initTestRecognition(config.ServerIP, config.ServerPort.ToString());
//            }, 60000);
//
//        if (r == -99)
//        {
//            log2.Info("识别初始卡住");
//        }
//        if (r != 0) return false;
//        return true;

        return true;
    }

}


