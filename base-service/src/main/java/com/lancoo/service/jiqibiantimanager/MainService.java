package com.lancoo.service.jiqibiantimanager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lancoo.http.utils.InterfaceRequest;
import com.lancoo.mapper.LgdbQuesBasicinfoCbhMapper;
import com.lancoo.mapper.LgdbQuesBasicinfoCchMapper;
import com.lancoo.pojo.LgdbQuesBasicinfoCbh;
import com.lancoo.pojo.LgdbQuesBasicinfoCch;
import com.lancoo.service.dto.jiqiDao.DigitQuesDao;
import com.lancoo.utils.DateUtils;
import com.lancoo.utils.JsonUtils;
import com.lancoo.utils.MD5Util;
import com.lancoo.utils.XmlUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;


/**
 * @author KCWang
 * @date 2021/8/2 10:28
 * @Email:KCWang@aliyun.com
 */

@Slf4j
@org.springframework.stereotype.Service
public class MainService {
    @Autowired
    private DigitService digitService;
    @Autowired
    private LgdbQuesBasicinfoCbhMapper lgdbQuesBasicinfoCbhMapper;

    @Autowired
    private LgdbQuesBasicinfoCchMapper lgdbQuesBasicinfoCchMapper;

    //识别及插题入库
    public void AutoEdit(String RSubject, String RStage, String Content, String QuesType, int Genre, String TaskId, String KnowledgeName, String Sentence, String newProperty) throws Exception {
        int ChildNum = 1;
        int ChildCount = 1;
        Sentence = Sentence.replace("<", "&lt;").replace(">", "&gt;").replace("'", "''");
        KnowledgeName = KnowledgeName.replace("'", "''");
        if (Genre == 15 || Genre == 20 || Genre == 16) {
            ChildNum = 5;
            ChildCount = 5;
        }
        if (Genre == 22) {
            ChildNum = awnserCount(Content);
            ChildCount = 1;
        }
        //加密
        String md5 = Md5Code(Content);
        //如果选项有大小写不一致处理一致。
        if (QuesType.equals("A")) {
            Content = dealOption(Content);
        }
        //标准化,先不写,后面调用陈镍写的接口进行标准化
        Content = StandardQuestions(QuesType, Integer.toString(Genre), Content);
        //识别,后面写
        DigitQuesDao digit = digitService.digitalMethod(RSubject, RStage, Content, QuesType, Genre);
        //分开两个sql 免数字化失败时，的不到字段
        if (digit != null) {
            // 可以正常识别的
            StringBuilder sb = new StringBuilder();
        } else {
            LgdbQuesBasicinfoCbh lgdbQuesBasicinfoCbh = new LgdbQuesBasicinfoCbh();

            lgdbQuesBasicinfoCbh.setResDigitizationid("");
            lgdbQuesBasicinfoCbh.setPackageid("");
            lgdbQuesBasicinfoCbh.setResstatus(0);
            lgdbQuesBasicinfoCbh.setPackagestatus(0);
            lgdbQuesBasicinfoCbh.setRespath("");
            lgdbQuesBasicinfoCbh.setRessize(0l);
            lgdbQuesBasicinfoCbh.setResfilenum(0l);
            lgdbQuesBasicinfoCbh.setQuesmediaurl("");
            lgdbQuesBasicinfoCbh.setDurationlength(0l);
            lgdbQuesBasicinfoCbh.setAudioplaytimes(0L);
            lgdbQuesBasicinfoCbh.setResLevel("");
            lgdbQuesBasicinfoCbh.setResLib("");
            lgdbQuesBasicinfoCbh.setResTheme("");
            lgdbQuesBasicinfoCbh.setResThemecode("");
            lgdbQuesBasicinfoCbh.setResImportantklg("");
            lgdbQuesBasicinfoCbh.setResImportantklgcode("");
            lgdbQuesBasicinfoCbh.setResMainklg("");
            lgdbQuesBasicinfoCbh.setResmd5code("");
            lgdbQuesBasicinfoCbh.setWordnum((long) 0.0);
            lgdbQuesBasicinfoCbh.setDatatableName("");
            lgdbQuesBasicinfoCbh.setReviewuserid("");

            lgdbQuesBasicinfoCbh.setReviewdate(null);
            lgdbQuesBasicinfoCbh.setCheckuserid("");
            lgdbQuesBasicinfoCbh.setCheckdate(null);


            lgdbQuesBasicinfoCbh.setTopicCode("");
            lgdbQuesBasicinfoCbh.setTopicText("");
            lgdbQuesBasicinfoCbh.setRelatedKnowledgeCode("");
            lgdbQuesBasicinfoCbh.setRelatedKnowledgeText("");
            lgdbQuesBasicinfoCbh.setThematicKnowledgeCode("");
            lgdbQuesBasicinfoCbh.setThematicKnowledgeText("");
            lgdbQuesBasicinfoCbh.setOutKnowledgename("");
            lgdbQuesBasicinfoCbh.setReason("");

            lgdbQuesBasicinfoCbh.setQuescontentstn("");
            lgdbQuesBasicinfoCbh.setImporKnUniquecode("");
            lgdbQuesBasicinfoCbh.setMainKnUniquecode("");
            lgdbQuesBasicinfoCbh.setTopicUniquecode("");
            lgdbQuesBasicinfoCbh.setCommitdate(new Date());


            String s1 = Sentence.replace("''", "'");

            lgdbQuesBasicinfoCbh.setSentence(s1);
            String AA = KnowledgeName;
            String KnowledgeName1 = KnowledgeName.replace("''", "'");

            lgdbQuesBasicinfoCbh.setKnowledgename(KnowledgeName1);
            lgdbQuesBasicinfoCbh.setResmd5code(md5);
            lgdbQuesBasicinfoCbh.setTaskid(TaskId);
            String s = UUID.randomUUID().toString().replace("-", "");

            lgdbQuesBasicinfoCbh.setGuid(s);


//            lgdbQuesBasicinfoCbh.setQuescontent(Content.replace("'", "''"));
            String a = Content;
            lgdbQuesBasicinfoCbh.setQuescontent(Content.replace("''", "'").replace("&nbsp;", " "));
            //查属性对应的编码，更新NewProperty这个字段
            lgdbQuesBasicinfoCbh.setNewproperty(newProperty);
            //所有机器编题 DBType=4
            lgdbQuesBasicinfoCbh.setDbtype(4);
            lgdbQuesBasicinfoCbh.setQuestype(QuesType);
            lgdbQuesBasicinfoCbh.setQueschildnum((long) ChildNum);
            lgdbQuesBasicinfoCbh.setQueschildcount((long) ChildCount);
            lgdbQuesBasicinfoCbh.setQuesgenre((long) Genre);
            lgdbQuesBasicinfoCbh.setResSub("C");
            lgdbQuesBasicinfoCbh.setResStage(RStage);
            Date date = new Date();
            String format = DateUtils.format(date);
            lgdbQuesBasicinfoCbh.setStoreDate(format);
            System.out.println(Content);
            lgdbQuesBasicinfoCbhMapper.insert(lgdbQuesBasicinfoCbh);
        }
    }


    //识别及插题入库
    public void AutoEditC(String RSubject, String RStage, String Content, String QuesType, int Genre, String TaskId, String KnowledgeName, String Sentence, String newProperty) throws Exception {
        int ChildNum = 1;
        int ChildCount = 1;
        Sentence = Sentence.replace("<", "&lt;").replace(">", "&gt;").replace("'", "''");
        KnowledgeName = KnowledgeName.replace("'", "''");
        if (Genre == 15 || Genre == 20 || Genre == 16) {
            ChildNum = 5;
            ChildCount = 5;
        }
        if (Genre == 22) {
            ChildNum = awnserCount(Content);
            ChildCount = 1;
        }
        //加密
        String md5 = Md5Code(Content);
        //如果选项有大小写不一致处理一致。
        if (QuesType.equals("A")) {
            Content = dealOption(Content);
        }
        //标准化,先不写,后面调用陈镍写的接口进行标准化
        Content = StandardQuestions(QuesType, Integer.toString(Genre), Content);
        //识别,后面写
        DigitQuesDao digit = digitService.digitalMethod(RSubject, RStage, Content, QuesType, Genre);
        //分开两个sql 免数字化失败时，的不到字段
        if (digit != null) {
            // 可以正常识别的
            StringBuilder sb = new StringBuilder();
            //sb.Append("insert into Lgdb_Ques_Basicinfo_C" + RStage + "H (TaskId,Guid,QuesContent,QuesType,QuesChildnum,QuesChildcount,QuesGenre,Res_Sub,Res_Stage,Store_Date,");
            //sb.Append("Res_DigitizationId,Res_Level,Res_Lib,Res_Theme,Res_ThemeCode,Res_Importantklg,Res_ImportantklgCode,Res_Mainklg,Res_MainklgCode,WordNum,ResMd5Code,KnowledgeName,Sentence,QuesContentStn,IMPOR_KN_UNIQUECODE,MAIN_KN_UNIQUECODE,TOPIC_UNIQUECODE) ");
            //sb.Append("values ('" + TaskId + "','" + Guid.NewGuid().ToString("N") + "',N'" + Content.Replace("'", "''") + "','" + QuesType + "','" + ChildNum + "','" + ChildCount + "','" + Genre + "','C','" + RStage + "','" + DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss:fff") + "',");
            //sb.Append("'" + digit.Res_DigitizationId + "','" + digit.Res_Level + "','" + digit.Res_Lib + "','" + digit.Res_Theme.Replace("'", "''") + "','" + digit.Res_ThemeCode + "','" + digit.Res_Importantklg.Replace("'", "''") + "','" + digit.Res_ImportantklgCode + "',");
            //sb.Append("'" + digit.Res_Mainklg.Replace("'", "''") + "','" + digit.Res_MainklgCode + "','" + digit.WordNum + "','" + md5 + "','" + KnowledgeName + "','" + Sentence + "','" + Content.Replace("'", "''") + "',");
            //sb.Append("'" + digit.IMPOR_KN_UNIQUECODE + "','" + digit.MAIN_KN_UNIQUECODE + "','" + digit.TOPIC_UNIQUECODE + "')");
            //sql = sb.ToString();
        } else {
            LgdbQuesBasicinfoCch lgdbQuesBasicinfoCbh = new LgdbQuesBasicinfoCch();
            lgdbQuesBasicinfoCbh.setResDigitizationid("");
            lgdbQuesBasicinfoCbh.setPackageid("");
            lgdbQuesBasicinfoCbh.setResstatus(0);
            lgdbQuesBasicinfoCbh.setPackagestatus(0);
            lgdbQuesBasicinfoCbh.setRespath("");
            lgdbQuesBasicinfoCbh.setRessize(0l);
            lgdbQuesBasicinfoCbh.setResfilenum(0l);
            lgdbQuesBasicinfoCbh.setQuesmediaurl("");
            lgdbQuesBasicinfoCbh.setDurationlength(0l);
            lgdbQuesBasicinfoCbh.setAudioplaytimes(0L);
            lgdbQuesBasicinfoCbh.setResLevel("");
            lgdbQuesBasicinfoCbh.setResLib("");
            lgdbQuesBasicinfoCbh.setResTheme("");
            lgdbQuesBasicinfoCbh.setResThemecode("");
            lgdbQuesBasicinfoCbh.setResImportantklg("");
            lgdbQuesBasicinfoCbh.setResImportantklgcode("");
            lgdbQuesBasicinfoCbh.setResMainklg("");
            lgdbQuesBasicinfoCbh.setResmd5code("");
            lgdbQuesBasicinfoCbh.setWordnum((long) 0.0);
            lgdbQuesBasicinfoCbh.setDatatableName("");
            lgdbQuesBasicinfoCbh.setReviewuserid("");

            lgdbQuesBasicinfoCbh.setReviewdate(null);
            lgdbQuesBasicinfoCbh.setCheckuserid("");
            lgdbQuesBasicinfoCbh.setCheckdate(null);


            lgdbQuesBasicinfoCbh.setTopicCode("");
            lgdbQuesBasicinfoCbh.setTopicText("");
            lgdbQuesBasicinfoCbh.setRelatedKnowledgeCode("");
            lgdbQuesBasicinfoCbh.setRelatedKnowledgeText("");
            lgdbQuesBasicinfoCbh.setThematicKnowledgeCode("");
            lgdbQuesBasicinfoCbh.setThematicKnowledgeText("");
            lgdbQuesBasicinfoCbh.setOutKnowledgename("");
            lgdbQuesBasicinfoCbh.setReason("");
            lgdbQuesBasicinfoCbh.setQuescontentstn("");
            lgdbQuesBasicinfoCbh.setImporKnUniquecode("");
            lgdbQuesBasicinfoCbh.setMainKnUniquecode("");
            lgdbQuesBasicinfoCbh.setTopicUniquecode("");


            lgdbQuesBasicinfoCbh.setCommitdate(new Date());
            String s1 = Sentence.replace("''", "'");
            lgdbQuesBasicinfoCbh.setSentence(s1);
            String AA = KnowledgeName;
            String KnowledgeName1 = KnowledgeName.replace("''", "'");
            lgdbQuesBasicinfoCbh.setKnowledgename(KnowledgeName1);
            lgdbQuesBasicinfoCbh.setResmd5code(md5);
            lgdbQuesBasicinfoCbh.setTaskid(TaskId);
            String s = UUID.randomUUID().toString().replace("-", "");
            lgdbQuesBasicinfoCbh.setGuid(s);
            //lgdbQuesBasicinfoCbh.setQuescontent(Content.replace("'", "''"));
            String a = Content;
            lgdbQuesBasicinfoCbh.setQuescontent(Content.replace("''", "'").replace("&nbsp;", " "));
            //查属性对应的编码，更新NewProperty这个字段
            lgdbQuesBasicinfoCbh.setNewproperty(newProperty);
            //所有机器编题 DBType=4
            lgdbQuesBasicinfoCbh.setDbtype(4);
            lgdbQuesBasicinfoCbh.setQuestype(QuesType);
            lgdbQuesBasicinfoCbh.setQueschildnum((long) ChildNum);
            lgdbQuesBasicinfoCbh.setQueschildcount((long) ChildCount);
            lgdbQuesBasicinfoCbh.setQuesgenre((long) Genre);
            lgdbQuesBasicinfoCbh.setResSub("C");
            lgdbQuesBasicinfoCbh.setResStage(RStage);
            Date date = new Date();
            String format = DateUtils.format(date);
            lgdbQuesBasicinfoCbh.setStoreDate(date);
            System.out.println(Content);
            lgdbQuesBasicinfoCchMapper.insert(lgdbQuesBasicinfoCbh);
        }
    }


    //标准化
    public String StandardQuestions(String QuesType1, String genre1, String Content1) throws Exception {
        Map<String, String> map = new HashMap<>(3);
        // map.put("QuesContent","<TContent><Quesbody /><QuesArticle audio=\"\" orgname=\"\" time=\"\" /><QuesChild index=\"1\" answertype=\"2\"><QueStem position=\"0\" /><QuesOptionAsk audio=\"\" orgname=\"\">&lt;P class=tq-p&gt;你们应该感到惭愧，都是你们惹的祸。&lt;/P&gt;&lt;P class=tq-p&gt;You null____ ____ be ashamed of yourselves. You've created this problem.&lt;/P&gt;</QuesOptionAsk><QuesAnswer>ought to</QuesAnswer><QuesAnalyze /><QuesOption /></QuesChild></TContent>");
        // map.put("QuesType","D");
        //map.put("QuesGenre","28");
        map.put("QuesContent", Content1);
        map.put("QuesType", QuesType1);
        map.put("QuesGenre", genre1);
        String param = JsonUtils.objectToJson(map);
        String uri = "http://172.16.42.59:32309/api/StdnQues/StandardQues";
        String result = InterfaceRequest.sendPost(uri, param);
        JSONObject jsonObject = JSON.parseObject(result);
        String content = jsonObject.getString("Result");
        System.out.println(content);
        return content;
    }


    //加密
    public String Md5Code(String str) {
        String s = str.replace("&amp;", "&").replace("&lt;", "<").replace("&gt;", ">").replace("&nbsp;", " ");
        s = s.replaceAll("<[^>]+>", " ");
        s = s.replace("\r\n", " ").replace("  ", " ").replace("  ", " ");
        String s1 = new MD5Util().encrypByMd5(s);
        return s1;
    }

    // 使选项的大小写一致
    public String dealOption(String str) {
        String strTemporary = "";
        //解析HTML
        Parser parser = Parser.htmlParser();
        parser.settings(new ParseSettings(true, true));
        Document document = parser.parseInput(str, "utf-8");
        //去自动换行
        document.outputSettings().prettyPrint(false);
        //获取所有QuesOption节点的集合
        Elements quesOptionList = document.getElementsByTag("QuesOption");
        for (int i = 0; i < quesOptionList.size(); i++) {
            Element item = quesOptionList.get(i);
            if (item.text() != null && item.text() != "") {
                strTemporary += item.text().substring(0, 1);
            }
        }
        String regex = ".*[A-Z]+.*";
        boolean matches = Pattern.compile(regex).matcher(strTemporary).matches();
        if (matches) {
            for (int i = 0; i < quesOptionList.size(); i++) {
                Element item = quesOptionList.get(i);
                if (item.text() != null && item.text() != "") {
                    item.text(item.text().substring(0, 1).toUpperCase() + item.text().substring(1));
                }
            }
        }
        org.jsoup.nodes.Element body = document.getElementsByTag("body").first();
        String html = body.html();
        return html;
    }

    //分析答题点个数
    public int awnserCount(String QuestContent) {
        int count = 1;
        Map<String, String> xmlToMap = XmlUtils.xmlToMap(QuestContent);
        for (String kk : xmlToMap.keySet()) {
            if (kk.equals("QuesAnswer")) {
                String vv = xmlToMap.get(kk);
                if (vv.contains("$")) {
                    count = vv.replace("、", "").split("$").length;
                } else {
                    count = 1;
                }
            }
        }
        return count;
    }

    public void find12() {
        System.out.println("测试成功");
    }


}
