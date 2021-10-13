package com.lancoo.service.thread;

import com.lancoo.encodeanddecode.CodeXml;
import com.lancoo.service.answer.AmswerInsertService;
import com.lancoo.service.jiqibiantimanager.HelperService;
import lombok.SneakyThrows;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KCWang
 * @date 2021/9/27 16:10
 * @Email:KCWang@aliyun.com
 */



//public class Task implements Runnable {
//    @Autowired
//    private HelperService helperService;
//    @Autowired
//    AmswerInsertService amswerInsertService;
//
//    List<String> list;
//    //Justice justice;
//
//    public Task(List<String> list) {
//        this.list = list;
//        //this.justiceBureauFilterOne = justiceBureauFilterOne;
//    }
//
//
//
//    @SneakyThrows
//    @Override
//    public void run() {
//        for (String m : list) {
//            String decodeXml = CodeXml.DecodeXml(m);
//            insertAnswers(decodeXml);
//        }
//    }
//
//
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
//            if(!m.text().equals("") && m.text()!=null){
//                String index = m.attr("index");
//                if(index.equals(quesAnswer)){
//                    answer = m.text();
//                }else {
//                    if(helperService.getTextFromTHML(m.text())!=null && !helperService.getTextFromTHML(m.text()).equals("")){
//                        list.add(helperService.getTextFromTHML(m.text()));
//                    }
//                }
//            }
//
//        }
//        if(!answer.equals("")){
//            String answer1 = helperService.getTextFromTHML(answer).trim();
//            if(!answer1.equals("") && answer1!=null ){
//                amswerInsertService.insertAnswer(answer1,list);
//            }
//        }
//
//    }
//
//}
