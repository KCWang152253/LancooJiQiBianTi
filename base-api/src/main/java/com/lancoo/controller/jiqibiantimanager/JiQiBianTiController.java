package com.lancoo.controller.jiqibiantimanager;

import com.lancoo.response.CommonCode;
import com.lancoo.response.QueryResponseResult;
import com.lancoo.response.QueryResult;
import com.lancoo.service.dto.JiQiChoseDao;
import com.lancoo.service.jiqibiantimanager.FormProjectService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @author KCWang
 * @date 2021/7/28 15:27
 * @Email:KCWang@aliyun.com
 */


@Slf4j
@RestController
@Api(description = "机器编题的入口")
@RequestMapping("/JiQiBianTi")
public class JiQiBianTiController {

    @Autowired
    private FormProjectService formProjectService;

    //单选题
    private String danxuan;
    //匹配题
    private String pipei;
    //词汇填空题
    private String cihuitiankong;
    //连词成句题
    private String liancichengju;
    //翻译题
    private String fanyiti;
    //选择的素材个数
    private int shucaishu;

    ArrayList<ArrayList<String>> result = null;

    //同时编多种类别的题型
    ArrayList<String> tiXing = new ArrayList<String>();

    //素材已用到第   40

    @PostMapping("/Begin")
    public QueryResponseResult jiQiBianTi(@RequestBody JiQiChoseDao jiQiChoseDao) throws Exception {
        System.out.println("开始编题******JiQiBianTiController******开始编题***********JiQiBianTiController**************");

        System.out.println(jiQiChoseDao);
        System.out.println(jiQiChoseDao.getCihuitiankong());
        //素材数
        shucaishu = jiQiChoseDao.getShucaishu();

        //单选题
        danxuan = jiQiChoseDao.getDanxuan();
        tiXing.add(danxuan);
        if (!danxuan.isEmpty()) {
            String TypeCode = danxuan.split(",")[1];
            String QuesGenre = danxuan.split(",")[2];
            String QuesPoint = danxuan.split(",")[3];
            String Stage = jiQiChoseDao.getStage();
            ArrayList<String> listQuesGenre = new ArrayList<>();
            listQuesGenre.add(QuesGenre);
            //开始编题
            result = formProjectService.ClickEditStart(TypeCode, Stage, listQuesGenre, shucaishu,"",QuesPoint);
        }

        //词汇填空题
        cihuitiankong = jiQiChoseDao.getCihuitiankong();
        tiXing.add(cihuitiankong);
        if (!cihuitiankong.isEmpty()) {
            String TypeCode  = cihuitiankong.split(",")[1];
            String QuesGenre = cihuitiankong.split(",")[2];
            String Property  = cihuitiankong.split(",")[3];
            String QuesPoint = cihuitiankong.split(",")[4];
            String Stage = jiQiChoseDao.getStage();
            ArrayList<String> listQuesGenre = new ArrayList<>();
            listQuesGenre.add(QuesGenre);
            //开始编题
            result = formProjectService.ClickEditStart(TypeCode, Stage, listQuesGenre, shucaishu,Property,QuesPoint);
        }

        //连词成句题   连词成句,V,7,无,英语单词英语词组
        liancichengju = jiQiChoseDao.getLiancichengju();
        tiXing.add(liancichengju);
        if (!liancichengju.isEmpty()) {
            String TypeCode  = liancichengju.split(",")[1];
            String QuesGenre = liancichengju.split(",")[2];
            String Property  = liancichengju.split(",")[3];
            String QuesPoint = liancichengju.split(",")[4];
            String Stage = "B";
            ArrayList<String> listQuesGenre = new ArrayList<>();
            listQuesGenre.add(QuesGenre);
            //开始编题
            result = formProjectService.ClickEditStart(TypeCode, Stage, listQuesGenre, shucaishu,Property,QuesPoint);
        }


        //翻译题  句子英译中,g,38,无,英语单词英语词组
        fanyiti = jiQiChoseDao.getFanyiti();
        tiXing.add(fanyiti);
        if (!fanyiti.isEmpty()) {
            String TypeCode  = fanyiti.split(",")[1];
            String QuesGenre = fanyiti.split(",")[2];
            String Property  = fanyiti.split(",")[3];
            String QuesPoint = fanyiti.split(",")[4];
            String Stage = "B";
            ArrayList<String> listQuesGenre = new ArrayList<>();
            listQuesGenre.add(QuesGenre);
            //开始编题
            result = formProjectService.ClickEditStart(TypeCode, Stage, listQuesGenre, shucaishu,Property,QuesPoint);
        }


        //匹配题  对话匹配,m,16,无,英语常用表达   词汇匹配,m,15,无,英语单词英语词组
        pipei = jiQiChoseDao.getPipei();
        tiXing.add(pipei);
        if (!pipei.isEmpty()) {
            String TypeCode  = pipei.split(",")[1];
            String QuesGenre = pipei.split(",")[2];
            String Property  = pipei.split(",")[3];
            String QuesPoint = pipei.split(",")[4];
            String Stage = "B";
            ArrayList<String> listQuesGenre = new ArrayList<>();
            listQuesGenre.add(QuesGenre);
            //开始编题
            result = formProjectService.ClickEditStart(TypeCode, Stage, listQuesGenre, shucaishu,Property,QuesPoint);
        }
        QueryResult<ArrayList<String>> arrayListQueryResult = new QueryResult<>();
        arrayListQueryResult.setList(result);
        arrayListQueryResult.setTotal(result.size());
        return new QueryResponseResult(CommonCode.SUCCESS,arrayListQueryResult);
    }
}
