package com.lancoo.controller.answer;


import com.lancoo.encodeanddecode.CodeXml;
import com.lancoo.response.CommonCode;
import com.lancoo.response.ResponseResult;
import com.lancoo.service.answer.AmswerInsertService;
import com.lancoo.service.answer.AnswerService;
import com.lancoo.service.jiqibiantimanager.HelperService;


import io.swagger.annotations.Api;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @author KCWang
 * @date 2021/9/24 15:37
 * @Email:KCWang@aliyun.com
 */
@Api(description = "从138数据库获取答案及干扰项")
@Slf4j
@RestController
@RequestMapping("/Answer")
public class AnswerController {

    @Autowired
    HelperService helperService;

    @Autowired
    AnswerService answerService;

    @Autowired
    AmswerInsertService amswerInsertService;

    @RequestMapping("/GetAnswer")
    public ResponseResult getAnswerAndDisturb() throws UnsupportedEncodingException {
        ArrayList<String> answers = answerService.getAnswers();
/**
 *   多线程去插入数据，提高效率　
 **/
        //构建线程池设置线程池的最大数量,线程池最大存活数量,线程池最大空闲时间,时间单位,线程等待队列,线程创建工厂
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 200, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2), new ThreadPoolExecutor.DiscardOldestPolicy());
        //获取当前要操作的数据总数量
        int lenth = answers.size();
        //设置线程数量手
        int threadNumber = 10;
        //分配每个线程执行的数量
        int listSize = lenth / threadNumber;
        List<String> subList = null;
        for (int i = 0; i < threadNumber; i++) {
            //如果是最后一个线程,截取当前的执行的数量,到总数据的末尾
            if (i == threadNumber - 1) {
                subList = answers.subList(i * listSize, lenth);
            } else {
                //否则默认按照每个大小的执行
                subList = answers.subList(i * listSize, (i + 1) * listSize);
            }
            //线程池执行Task的run方法
            threadPoolExecutor.execute(new Task(subList));
        }
        threadPoolExecutor.shutdown();
        System.out.println("138的答案已经提取完毕");
/**
 *
 */

//        for (String m: answers) {
//            String decodeXml = CodeXml.DecodeXml(m);
//            insertAnswers(decodeXml);
//        }
//
//        return  new ResponseResult(CommonCode.SUCCESS);
//    }
//
//    public void  insertAnswers(String a){
//        //解析html,保留大小写
//        //      String aa = a.replace("amp;","").replace("&lt;", "<").replace("&gt;", ">");
//        Parser parser = Parser.htmlParser();
//        parser.settings(new ParseSettings(true, true));
//        Document document = parser.parseInput(a, "utf-8");
//        Elements quesOption = document.getElementsByTag("QuesOption");
//        String quesAnswer = document.getElementsByTag("QuesAnswer").text();
//        String answer = "";
//        ArrayList<String> list = new ArrayList<>();
//        for (Element m:quesOption) {
//            String index = m.attr("index");
//            if(index.equals(quesAnswer)){
//                answer = m.text();
//            }else {
//                list.add(helperService.getTextFromTHML(m.text()));
//            }
//        }
//        String answer1 = helperService.getTextFromTHML(answer).trim();
//        if(!answer1.equals("") && answer1!=null ){
//            amswerInsertService.insertAnswer(answer1,list);
//        }
//    }
        return new ResponseResult(CommonCode.SUCCESS);
    }

     class Task implements Runnable {

        List<String> list;
        //Justice justice;

         Task(List<String> list) {
            this.list = list;
            //this.justiceBureauFilterOne = justiceBureauFilterOne;
        }
        @SneakyThrows
        @Override
        public void run() {
            for (String m : list) {
                String decodeXml = CodeXml.DecodeXml(m);
                insertAnswers(decodeXml);
            }
        }
        public void  insertAnswers(String a){
            //解析html,保留大小写
            //      String aa = a.replace("amp;","").replace("&lt;", "<").replace("&gt;", ">");
            Parser parser = Parser.htmlParser();
            parser.settings(new ParseSettings(true, true));
            Document document = parser.parseInput(a, "utf-8");
            Elements quesOption = document.getElementsByTag("QuesOption");
            String quesAnswer = document.getElementsByTag("QuesAnswer").text();
            String answer = "";
            ArrayList<String> list = new ArrayList<>();
            for (Element m:quesOption) {
                if(!m.text().equals("") && m.text()!=null){
                    String index = m.attr("index");
                    if(index.equals(quesAnswer)){
                        answer = m.text();
                    }else {
                        if(helperService.getTextFromTHML(m.text())!=null && !helperService.getTextFromTHML(m.text()).equals("")){
                            list.add(helperService.getTextFromTHML(m.text()));
                        }
                    }
                }

            }
            if(!answer.equals("")){
                String answer1 = helperService.getTextFromTHML(answer).trim();
                if(!answer1.equals("") && answer1!=null ){
                    amswerInsertService.insertAnswer(answer1,list);
                }
            }
        }
    }




}
