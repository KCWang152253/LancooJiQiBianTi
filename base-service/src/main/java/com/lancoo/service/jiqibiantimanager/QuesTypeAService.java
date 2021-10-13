package com.lancoo.service.jiqibiantimanager;


import com.lancoo.mapper.*;
import com.lancoo.pojo.LgdbBKlgOrgresource;
import com.lancoo.pojo.LgdbCKlgOrgresource;
import com.lancoo.pojo.RightAnswerDisturb;
import com.lancoo.service.dto.jiqiDao.ContentDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @author KCWang
 * @date 2021/8/2 10:36
 * @Email:KCWang@aliyun.com
 *
 */

@Slf4j
@Service
public class QuesTypeAService {
    @Autowired
    private HelperService helperService;
    @Autowired
    private LgdbBKlgOrgresourceMapper lgdbBKlgOrgresourceMapper;
    @Autowired
    private LgdbBasicKnowledgeandcodeMapper lgdbBasicKnowledgeandcodeMapper;
    @Autowired
    LgdbBasicQuestionclassifynameMapper lgdbBasicQuestionclassifynameMapper;

    @Autowired
    LgdbQuesBasicinfoCchMapper lgdbQuesBasicinfoCchMapper;

    @Autowired
    LgdbCKlgOrgresourceMapper lgdbCKlgOrgresourceMapper;

    @Autowired
    private  RightAnswerDisturbMapper rightAnswerDisturbMapper;

    //单选题 只要前两种  找干扰项的方法 ，

    //单选题，B阶段     单词题,A,13,英语单词英语词组
    public ArrayList<String> quesTypeA13word(LgdbBKlgOrgresource item, String Stage, String TaskId, MainService query) throws Exception {
        String sbXmlTemplet = ContentDao.TYPE_A;
        //解析html,保留大小写
        Parser parser = Parser.htmlParser();
        parser.settings(new ParseSettings(true, true));
        Document document = parser.parseInput(sbXmlTemplet, "utf-8");

        String Ask = null;
        String Answer = null;
        String Optionss = null;
        String getTypeCode = helperService.getQuesTypeService(item.getId(), Stage);
        /**
         * 英语词组浪费大量素材，有待改善
         */
        if (helperService.pickTargetWordOneword(item.getSentence(), item.getKnowledgename(), "") == 1 && helperService.pickUpTargetWord(item)!=null) {
            String emptyWord = helperService.pickUpTargetWord(item);
            String ask = null;
            // 造出题干
            //替换时容易与单词冲突加上标签 但给的素材标签不规整 如下替换两次
            // ask = item.getSentence().replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace(  emptyWord , "____");
            // item.getSentence().replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>");
            //  ask = item.getSentence().replace("<div>", "").replace("</div>", "").replace("<strong><u>", "").replace("</u></strong>", "").replace("<u>", "").replace("<strong>", "").replace("</u>", "").replace("</strong>", "").replace(emptyWord, "____");
            String AAA = item.getSentence().replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");
            String ask1 = helperService.getTextFromTHML(AAA).replace(emptyWord, "____");
            ask = ask1.replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");

            //有对话的情况要分开
            if (ask.contains("Ж")) {
                String[] жs = ask.split("Ж");
                for (int i = 0; i < жs.length; i++) {
                    document.getElementsByTag("QuesOptionAsk").first().appendText("<P class=tq-p>" + жs[i] + "</P>" + " ");
                }
            } else {
                document.getElementsByTag("QuesOptionAsk").first().text("<P class=tq-p>" + ask.trim() + "</P>");
            }

            // document.getElementsByTag("QuesOptionAsk").first().text("<P class=tq-p>" + ask.trim() + "</P>");
            Ask = ask.trim();
            // 核心逻辑
            int index = 0;
            String quesAnswer = "";
            //临时变量 确保不重复
            String strTemporary = "";
            //---拼接题干--及造出选项和干扰项
            // 造出题干  选项和干扰项
            ArrayList<HashMap<Integer, String>> list = new ArrayList<HashMap<Integer, String>>();
            HashMap<Integer, String> stOther = new HashMap<Integer, String>();
            //处理wemptyWord
            emptyWord.replace("<u>", "").replace("</u>ing", "").replace("</u>ed", "").replace("</u>es", "").replace("</u>s", "").replace("<strong>", "").replace("</strong>ing", "").replace("</strong>ed", "").replace("</strong>es", "").replace("</strong>s", "").trim();
            stOther.put(++index, emptyWord);
            list.add(stOther);
            strTemporary += emptyWord + "|";
            //其他干扰项
            //String knowledgetype = item.getKnowledgetype();
            //String theme = lgdbBasicKnowledgeandcodeMapper.selectThemeByKnowledeCode(item.getKnowledgecode());
            /**
             *  从[Right_Answer_Disturb]表中查找干扰项　
             *  表中知识点是否满足屏蔽的要求　
             */

            //以目标知识点为正确选项在选项库中查找
            ArrayList<RightAnswerDisturb> rightAnswerDisturbs = rightAnswerDisturbMapper.selectAnswers(item.getKnowledgename());
            ArrayList<RightAnswerDisturb> rightAnswerDisturbs1 = rightAnswerDisturbMapper.selectAnswersByDisturb(item.getKnowledgename());
            ArrayList<String> otherWordList1 = new ArrayList<>();
            ArrayList<String> otherWordList2 = new ArrayList<>();
            ArrayList<String> otherWordList5 = new ArrayList<>();
            if(rightAnswerDisturbs!=null && rightAnswerDisturbs.size()>0){
                for (RightAnswerDisturb m:rightAnswerDisturbs) {
                    otherWordList2.add(m.getDisturbb().replace(".","").replace("!","").replace("?",""));
                    otherWordList2.add(m.getDisturbc().replace(".","").replace("!","").replace("?",""));
                    boolean present = Optional.ofNullable(m.getDisturbd()).isPresent();
                    if(present){
                        otherWordList2.add(m.getDisturbd().replace(".","").replace("!","").replace("?",""));
                    }
                }
                ArrayList<String> otherWordList3 = (ArrayList<String>) otherWordList2.stream().distinct().collect(Collectors.toList());
                otherWordList1.addAll(otherWordList3);
            }
            if(!(otherWordList1.size() >4) && rightAnswerDisturbs1!=null && rightAnswerDisturbs1.size()>0){
                //以目标知识点为非正确选项在选项库中查找
                for (RightAnswerDisturb m:rightAnswerDisturbs1) {
                    if(!m.getDisturbb().equals(item.getKnowledgename())){
                        otherWordList5.add(m.getDisturbb().replace(".","").replace("!","").replace("?",""));
                    }
                    if(!m.getDisturbc().equals(item.getKnowledgename())){
                        otherWordList5.add(m.getDisturbc().replace(".","").replace("!","").replace("?",""));
                    }
                    boolean present = Optional.ofNullable(m.getDisturbd()).isPresent();
                    if(present){
                        if(!m.getDisturbd().equals(item.getKnowledgename())){
                            otherWordList5.add(m.getDisturbd().replace(".","").replace("!","").replace("?",""));
                        }
                    }
                }
                ArrayList<String> otherWordList3 = (ArrayList<String>) otherWordList5.stream().distinct().collect(Collectors.toList());
                otherWordList1.addAll(otherWordList3);
            }

            //单选题 只要前两种  找干扰项的方法 ，
//            if(!(otherWordList1.size() >4)){
//                    String knowledgetype = item.getKnowledgetype();
//                String theme = lgdbBasicKnowledgeandcodeMapper.selectThemeByKnowledeCode(item.getKnowledgecode());
//                ArrayList<String> otherWordList4 = lgdbBKlgOrgresourceMapper.findLgdbBKlgOrgresources9(theme, "B", knowledgetype);
//                //有其他需要屏蔽的选项后续再添加　　
//                ArrayList<String> otherWordList7 = (ArrayList<String>) otherWordList4.stream().filter(c -> !(c.contains("sb.") || c.contains("sth.") || c.contains("(") || c.contains("/") || c.contains("+"))).collect(Collectors.toList());
//                ArrayList<String> otherWordList6 = new ArrayList<>();
//                for (String m: otherWordList7) {
//                    String m1 = m.replace(".", "").replace("!", "").replace("?", "");
//                    otherWordList6.add(m1);
//                }
//                ArrayList<String> otherWordList3 = (ArrayList<String>) otherWordList6.stream().distinct().collect(Collectors.toList());
//                otherWordList1.addAll(otherWordList3);
//            }

            // ArrayList<String> otherWordList = lgdbBKlgOrgresourceMapper.findLgdbBKlgOrgresources9(theme,"B",knowledgetype);
            //ArrayList<String> otherWordList = helperService.getFirstNumber(emptyWord);
            if (otherWordList1.size() > 4) {
                for (String m : otherWordList1) {
                    if (!strTemporary.contains(m)) {
                        if (index > 3) {
                            break;
                        }
                        ++index;
                        HashMap<Integer, String> stChild = new HashMap<Integer, String>();
                        stChild.put(index, m);
                        strTemporary += m + "|";
                        list.add(stChild);
                    }
                }
                Optionss = strTemporary;
                //---把选项打乱顺序生成新顺序
                ArrayList list1 = helperService.randomSortList(list);
                HashMap<HashMap<Integer, String>, Integer> dicSort = new HashMap<HashMap<Integer, String>, Integer>();
                int sortCount = 0;
                for (int i = 0; i < list1.size(); i++) {
                    ++sortCount;
                    dicSort.put((HashMap<Integer, String>) list1.get(i), sortCount);
                }
                int count = 5;
                for (HashMap.Entry<HashMap<Integer, String>, Integer> entry : dicSort.entrySet()) {
                    for (Map.Entry<Integer, String> entry1 : entry.getKey().entrySet()) {
                        //创建一个<QuesOption>节点
                        Element quesOption = document.createElement("QuesOption");
                        //  quesOption.val(entry1.getValue());
                        quesOption.text(entry1.getValue());
                        quesOption.attr("index", String.valueOf(--count));
                        document.getElementsByTag("QuesAnalyze").after(quesOption.toString().replace("\n", ""));
                        if (entry1.getKey() == 1) {
                            quesAnswer = String.valueOf(count);
                        }
                    }
                }
                document.getElementsByTag("QuesAnswer").first().text(quesAnswer.replace("&lt;", "<").replace("&gt;", ">"));
                Answer = document.getElementsByTag("QuesAnswer").first().text();
                //去自动换行
                document.outputSettings().prettyPrint(false);
                //查属性对应的编码，更新NewProperty这个字段
                String newProperty = "";
                //识别和插入到原始库
                query.AutoEdit("C", Stage, document.html(), "A", 13, TaskId, item.getKnowledgename(), item.getSentence() , newProperty);
                String sdf = document.html();
                String questype = item.getQuestype();
                lgdbBKlgOrgresourceMapper.LgdbBKlgOrgresourceUpdate(Stage, "A132", item.getId());
            }
        }
        ArrayList<String> strings = new ArrayList<>();
        if (Ask != null && Answer != null && Optionss != null) {
            strings.add("题干：" + Ask);
            String[] split = Optionss.split("\\|");
            if (split.length == 4) {
                strings.add("选项:   " + "A. " + split[0] + "      " + "B. " + split[1] + "      " + "C. " + split[2] + "      " + "D. " + split[3]);
            } else if (split.length == 3) {
                strings.add("选项:   " + "A. " + split[0] + "      " + "B. " + split[1] + "      " + "C. " + split[2]);
            } else if (split.length == 2) {
                strings.add("选项:   " + "A. " + split[0] + "      " + "B. " + split[1]);
            } else if (split.length == 1) {
                strings.add("选项:  " + "A. " + split[0]);
            }
            strings.add("答案: " + Answer);
        }
        return strings;
    }


    //单选题，C阶段     单词题,A,13,英语单词英语词组
    public ArrayList<String> quesTypeA13wordC(LgdbCKlgOrgresource item, String Stage, String TaskId, MainService query) throws Exception {
        String sbXmlTemplet = ContentDao.TYPE_A;
        //解析html,保留大小写
        Parser parser = Parser.htmlParser();
        parser.settings(new ParseSettings(true, true));
        Document document = parser.parseInput(sbXmlTemplet, "utf-8");

        String Ask = null;
        String Answer = null;
        String Optionss = null;
        String getTypeCode = helperService.getQuesTypeService(item.getId(), Stage);
        /**
         * 英语词组浪费大量素材，有待改善
         */
        if (helperService.pickTargetWordOneword(item.getSentence(), item.getKnowledgename(), "") == 1 && helperService.pickUpTargetWordC(item)!=null) {
            String emptyWord = helperService.pickUpTargetWordC(item);
            String ask = null;
            // 造出题干
            //替换时容易与单词冲突加上标签 但给的素材标签不规整 如下替换两次
            // ask = item.getSentence().replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace(  emptyWord , "____");
            // item.getSentence().replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>");
            //  ask = item.getSentence().replace("<div>", "").replace("</div>", "").replace("<strong><u>", "").replace("</u></strong>", "").replace("<u>", "").replace("<strong>", "").replace("</u>", "").replace("</strong>", "").replace(emptyWord, "____");
            String AAA = item.getSentence().replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");
            String ask1 = helperService.getTextFromTHML(AAA).replace(emptyWord, "____");
            ask = ask1.replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");

            //有对话的情况要分开
            if (ask.contains("Ж")) {
                String[] жs = ask.split("Ж");
                for (int i = 0; i < жs.length; i++) {
                    document.getElementsByTag("QuesOptionAsk").first().appendText("<P class=tq-p>" + жs[i] + "</P>" + " ");
                }
            } else {
                document.getElementsByTag("QuesOptionAsk").first().text("<P class=tq-p>" + ask.trim() + "</P>");
            }

            // document.getElementsByTag("QuesOptionAsk").first().text("<P class=tq-p>" + ask.trim() + "</P>");
            Ask = ask.trim();
            // 核心逻辑
            int index = 0;
            String quesAnswer = "";
            //临时变量 确保不重复
            String strTemporary = "";
            //---拼接题干--及造出选项和干扰项
            // 造出题干  选项和干扰项
            ArrayList<HashMap<Integer, String>> list = new ArrayList<HashMap<Integer, String>>();
            HashMap<Integer, String> stOther = new HashMap<Integer, String>();
            //处理wemptyWord
            emptyWord.replace("<u>", "").replace("</u>ing", "").replace("</u>ed", "").replace("</u>es", "").replace("</u>s", "").replace("<strong>", "").replace("</strong>ing", "").replace("</strong>ed", "").replace("</strong>es", "").replace("</strong>s", "").trim();
            stOther.put(++index, emptyWord);
            list.add(stOther);
            strTemporary += emptyWord + "|";
            //其他干扰项
            //String knowledgetype = item.getKnowledgetype();
            //String theme = lgdbBasicKnowledgeandcodeMapper.selectThemeByKnowledeCode(item.getKnowledgecode());
            /**
             *  从[Right_Answer_Disturb]表中查找干扰项　
             *  表中知识点是否满足屏蔽的要求　
             */

            //以目标知识点为正确选项在选项库中查找
            ArrayList<RightAnswerDisturb> rightAnswerDisturbs = rightAnswerDisturbMapper.selectAnswers(item.getKnowledgename());
            ArrayList<RightAnswerDisturb> rightAnswerDisturbs1 = rightAnswerDisturbMapper.selectAnswersByDisturb(item.getKnowledgename());
            ArrayList<String> otherWordList1 = new ArrayList<>();
            ArrayList<String> otherWordList2 = new ArrayList<>();
            ArrayList<String> otherWordList5 = new ArrayList<>();
            if(rightAnswerDisturbs!=null && rightAnswerDisturbs.size()>0){
                for (RightAnswerDisturb m:rightAnswerDisturbs) {
                    otherWordList2.add(m.getDisturbb().replace(".","").replace("!","").replace("?",""));
                    otherWordList2.add(m.getDisturbc().replace(".","").replace("!","").replace("?",""));
                    boolean present = Optional.ofNullable(m.getDisturbd()).isPresent();
                    if(present){
                        otherWordList2.add(m.getDisturbd().replace(".","").replace("!","").replace("?",""));
                    }
                }
                ArrayList<String> otherWordList3 = (ArrayList<String>) otherWordList2.stream().distinct().collect(Collectors.toList());
                otherWordList1.addAll(otherWordList3);
            }
            if(!(otherWordList1.size() >4) && rightAnswerDisturbs1!=null && rightAnswerDisturbs1.size()>0){
                //以目标知识点为非正确选项在选项库中查找
                for (RightAnswerDisturb m:rightAnswerDisturbs1) {
                    if(!m.getDisturbb().equals(item.getKnowledgename())){
                        otherWordList5.add(m.getDisturbb().replace(".","").replace("!","").replace("?",""));
                    }
                    if(!m.getDisturbc().equals(item.getKnowledgename())){
                        otherWordList5.add(m.getDisturbc().replace(".","").replace("!","").replace("?",""));
                    }
                    boolean present = Optional.ofNullable(m.getDisturbd()).isPresent();
                    if(present){
                        if(!m.getDisturbd().equals(item.getKnowledgename())){
                            otherWordList5.add(m.getDisturbd().replace(".","").replace("!","").replace("?",""));
                        }
                    }
                }
                ArrayList<String> otherWordList3 = (ArrayList<String>) otherWordList5.stream().distinct().collect(Collectors.toList());
                otherWordList1.addAll(otherWordList3);
            }
//            if(!(otherWordList1.size() >4)){
//                String knowledgetype = item.getKnowledgetype();
//                String theme = lgdbBasicKnowledgeandcodeMapper.selectThemeByKnowledeCode(item.getKnowledgecode());
//                //59跟173数据库数据一致，此时不需要改lgdbBKlgOrgresourceMapper
//                ArrayList<String> otherWordList4 = lgdbBKlgOrgresourceMapper.findLgdbBKlgOrgresources9(theme, "B", knowledgetype);
//                //有其他需要屏蔽的选项后续再添加　　
//                ArrayList<String> otherWordList7 = (ArrayList<String>) otherWordList4.stream().filter(c -> !(c.contains("sb.") || c.contains("sth.") || c.contains("(") || c.contains("/") || c.contains("+"))).collect(Collectors.toList());
//                ArrayList<String> otherWordList6 = new ArrayList<>();
//                for (String m: otherWordList7) {
//                    String m1 = m.replace(".", "").replace("!", "").replace("?", "");
//                    otherWordList6.add(m1);
//                }
//                ArrayList<String> otherWordList3 = (ArrayList<String>) otherWordList6.stream().distinct().collect(Collectors.toList());
//                otherWordList1.addAll(otherWordList3);
//            }

            // ArrayList<String> otherWordList = lgdbBKlgOrgresourceMapper.findLgdbBKlgOrgresources9(theme,"B",knowledgetype);
            //ArrayList<String> otherWordList = helperService.getFirstNumber(emptyWord);
            if (otherWordList1.size() > 4) {
                for (String m : otherWordList1) {
                    if (!strTemporary.contains(m)) {
                        if (index > 3) {
                            break;
                        }
                        ++index;
                        HashMap<Integer, String> stChild = new HashMap<Integer, String>();
                        stChild.put(index, m);
                        strTemporary += m + "|";
                        list.add(stChild);
                    }
                }
                Optionss = strTemporary;
                //---把选项打乱顺序生成新顺序
                ArrayList list1 = helperService.randomSortList(list);
                HashMap<HashMap<Integer, String>, Integer> dicSort = new HashMap<HashMap<Integer, String>, Integer>();
                int sortCount = 0;
                for (int i = 0; i < list1.size(); i++) {
                    ++sortCount;
                    dicSort.put((HashMap<Integer, String>) list1.get(i), sortCount);
                }
                int count = 5;
                for (HashMap.Entry<HashMap<Integer, String>, Integer> entry : dicSort.entrySet()) {
                    for (Map.Entry<Integer, String> entry1 : entry.getKey().entrySet()) {
                        //创建一个<QuesOption>节点
                        Element quesOption = document.createElement("QuesOption");
                        //  quesOption.val(entry1.getValue());
                        quesOption.text(entry1.getValue());
                        quesOption.attr("index", String.valueOf(--count));
                        document.getElementsByTag("QuesAnalyze").after(quesOption.toString().replace("\n", ""));
                        if (entry1.getKey() == 1) {
                            quesAnswer = String.valueOf(count);
                        }
                    }
                }
                document.getElementsByTag("QuesAnswer").first().text(quesAnswer.replace("&lt;", "<").replace("&gt;", ">"));
                Answer = document.getElementsByTag("QuesAnswer").first().text();
                //去自动换行
                document.outputSettings().prettyPrint(false);
                //查属性对应的编码，更新NewProperty这个字段
                String newProperty = "";
                //识别和插入到原始库
                query.AutoEditC("C", Stage, document.html(), "A", 13, TaskId, item.getKnowledgename(), item.getSentence() , newProperty);
                String sdf = document.html();
                String questype = item.getQuestype();
                lgdbCKlgOrgresourceMapper.LgdbCKlgOrgresourceUpdate(Stage, "A132", item.getId());
            }
        }
        ArrayList<String> strings = new ArrayList<>();
        if (Ask != null && Answer != null && Optionss != null) {
            strings.add("题干：" + Ask);
            String[] split = Optionss.split("\\|");
            if (split.length == 4) {
                strings.add("选项:   " + "A. " + split[0] + "      " + "B. " + split[1] + "      " + "C. " + split[2] + "      " + "D. " + split[3]);
            } else if (split.length == 3) {
                strings.add("选项:   " + "A. " + split[0] + "      " + "B. " + split[1] + "      " + "C. " + split[2]);
            } else if (split.length == 2) {
                strings.add("选项:   " + "A. " + split[0] + "      " + "B. " + split[1]);
            } else if (split.length == 1) {
                strings.add("选项:  " + "A. " + split[0]);
            }
            strings.add("答案: " + Answer);
        }
        return strings;
    }



    //单选题，B阶段　　　　　单词题,A,13,英语常用表达
    public ArrayList<String> quesTypeA13express(LgdbBKlgOrgresource item, String Stage, String TaskId, MainService query) throws Exception {
        String sbXmlTemplet = ContentDao.TYPE_A;
        //解析html
        Parser parser = Parser.htmlParser();
        parser.settings(new ParseSettings(true, true));
        Document document = parser.parseInput(sbXmlTemplet, "utf-8");
        String Ask = "";
        String Answer = null;
        String Optionss = null;
        String getTypeCode = helperService.getQuesTypeService(item.getId(), Stage);
        if (helperService.pickTargetWordOneexpress(item.getSentence(), item.getKnowledgename(), "") == 1  && helperService.pickUpTargetWord(item)!=null) {
            String emptyWord = helperService.pickUpTargetWord(item);
            //造出题干，
            String AAA = item.getSentence().replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");
            String ask1 = helperService.getTextFromTHML(AAA).replace(emptyWord, "____");
            String ask = ask1.replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");
            // String ask = helperService.getTextFromTHML(item.getSentence()).replace(emptyWord, "____");
            // String ask = item.getSentence().replace("<div>", "").replace("</div>", "").replace("&lt;", "<").replace("&gt;", ">").replace("< strong >", "<strong>").replace("</ strong >", "</strong>").replace("< u >", "<u>").replace("</ u >", "</u>").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong>", "").replace("<u>", "").replace("</u>", "").replace("</strong>", "").replace(emptyWord, "____");
            //---拼接题干--
            //有对话的情况要分开
            if (ask.contains("Ж")) {
                String[] жs = ask.split("Ж");
                for (int i = 0; i < жs.length; i++) {
                    document.getElementsByTag("QuesOptionAsk").first().appendText("<P class=tq-p>" + жs[i] + "</P>" + " ");
                    if(i+1 == жs.length){
                        Ask +=жs[i];
                    }else {
                        Ask +=жs[i]+ "|";
                    }
                }
            } else {
                document.getElementsByTag("QuesOptionAsk").first().text("<P class=tq-p>" + ask.trim() + "</P>");
                Ask = ask.trim();
            }


            org.jsoup.nodes.Element tContent = document.getElementsByTag("TContent").first();
            org.jsoup.nodes.Element quesChild = document.getElementsByTag("QuesChild").first();
            // 核心逻辑
            int index = 0;
            String quesAnswer = "";
            //临时变量 确保不重复
            String strTemporary = "";
            // 造出选项和干扰项
            ArrayList<HashMap<Integer, String>> list = new ArrayList<HashMap<Integer, String>>();
            HashMap<Integer, String> stOther = new HashMap<Integer, String>();
            //处理wemptyWord
            //emptyWord.replace("<u>", "").replace("</u>ing", "").replace("</u>ed", "").replace("</u>es", "").replace("</u>s", "").replace("<strong>", "").replace("</strong>ing", "").replace("</strong>ed", "").replace("</strong>es", "").replace("</strong>s", "").trim();
            stOther.put(++index, emptyWord);
            list.add(stOther);
            strTemporary += emptyWord + "|";
            //其他干扰项,再增加一个方法，将条件放宽，修改查询条件
            //ArrayList<String> otherWordList = helperService.getFirstNumber1(emptyWord);

            /****
             *
             * 干扰项　
             *
             * 将挖空的目标知识点随机打乱，并随机选取与目标知识点不同主题下的三个知识点作为干扰项，并以大写英语字母编号。各选项之间的单词长度小于5。
             */


            //以目标知识点为正确选项在选项库中查找
           // ArrayList<RightAnswerDisturb> rightAnswerDisturbs = rightAnswerDisturbMapper.selectAnswers(item.getKnowledgename());
           // ArrayList<RightAnswerDisturb> rightAnswerDisturbs1 = rightAnswerDisturbMapper.selectAnswersByDisturb(item.getKnowledgename());

            ArrayList<RightAnswerDisturb> rightAnswerDisturbs = rightAnswerDisturbMapper.selectAnswers(emptyWord);
            ArrayList<RightAnswerDisturb> rightAnswerDisturbs1 = rightAnswerDisturbMapper.selectAnswersByDisturb(emptyWord);

            ArrayList<String> otherWordList1 = new ArrayList<>();
            ArrayList<String> otherWordList2 = new ArrayList<>();
            ArrayList<String> otherWordList5 = new ArrayList<>();
            if(rightAnswerDisturbs!=null && rightAnswerDisturbs.size()>0){
                for (RightAnswerDisturb m:rightAnswerDisturbs) {
                    otherWordList2.add(m.getDisturbb().replace(".","").replace("!","").replace("?",""));
                    otherWordList2.add(m.getDisturbc().replace(".","").replace("!","").replace("?",""));
                    boolean present = Optional.ofNullable(m.getDisturbd()).isPresent();
                    if(present){
                        otherWordList2.add(m.getDisturbd().replace(".","").replace("!","").replace("?",""));
                    }
                }
                ArrayList<String> otherWordList3 = (ArrayList<String>) otherWordList2.stream().distinct().collect(Collectors.toList());
                otherWordList1.addAll(otherWordList3);

            }
            if(!(otherWordList1.size() >4) && rightAnswerDisturbs1!=null && rightAnswerDisturbs1.size()>0){
                //以目标知识点为非正确选项在选项库中查找
                for (RightAnswerDisturb m:rightAnswerDisturbs1) {
                    if(!m.getDisturbb().equals(item.getKnowledgename())){
                        otherWordList5.add(m.getDisturbb().replace(".","").replace("!","").replace("?",""));
                    }
                    if(!m.getDisturbc().equals(item.getKnowledgename())){
                        otherWordList5.add(m.getDisturbc().replace(".","").replace("!","").replace("?",""));
                    }
                    boolean present = Optional.ofNullable(m.getDisturbd()).isPresent();
                    if(present){
                        if(!m.getDisturbd().equals(item.getKnowledgename())){
                            otherWordList5.add(m.getDisturbd().replace(".","").replace("!","").replace("?",""));
                        }
                    }
                }
                ArrayList<String> otherWordList3 = (ArrayList<String>) otherWordList5.stream().distinct().collect(Collectors.toList());
                for (String m: otherWordList3) {
                    if(!otherWordList1.contains(m)){
                        otherWordList1.add(m);
                    }
                }
            }
//            if(!(otherWordList1.size() >4)){
//                String knowledgetype = item.getKnowledgetype();
//                String theme = lgdbBasicKnowledgeandcodeMapper.selectThemeByKnowledeCode(item.getKnowledgecode());
//                ArrayList<String> otherWordList4 = lgdbBKlgOrgresourceMapper.findLgdbBKlgOrgresources9(theme, "B", knowledgetype);
//                //有其他需要屏蔽的选项后续再添加　　
//                ArrayList<String> otherWordList7 = (ArrayList<String>) otherWordList4.stream().filter(c -> !(c.contains("sb.") || c.contains("sth.") || c.contains("(") || c.contains("/") || c.contains("+"))).collect(Collectors.toList());
//                ArrayList<String> otherWordList6 = new ArrayList<>();
//                for (String m: otherWordList7) {
//                    String m1 = m.replace(".", "").replace("!", "").replace("?", "");
//                    otherWordList6.add(m1);
//                }
//                ArrayList<String> otherWordList3 = (ArrayList<String>) otherWordList6.stream().distinct().collect(Collectors.toList());
//                otherWordList1.addAll(otherWordList3);
//            }

            // ArrayList<String> collect = (ArrayList<String>) otherWordList1.stream().filter(c -> helperService.interLengthLessFive1(item.getKnowledgename().split(" ").length, otherWordList1) == 0).collect(Collectors.toList());
            ArrayList<String> otherWordList = new ArrayList<>();
            for (String m: otherWordList1) {
                if(otherWordList.size()==0){
                    if(helperService.interLengthLessFive2(m.split(" ").length,item.getKnowledgename().split(" ").length)==0){
                        otherWordList.add(m);
                    }
                }else {
                    if(helperService.interLengthLessFive1(m.split(" ").length, otherWordList)==0 && helperService.interLengthLessFive2(m.split(" ").length,item.getKnowledgename().split(" ").length)==0){
                        otherWordList.add(m);
                    }
                }
            }

            if (otherWordList.size() > 2) {
                for (String m : otherWordList) {
                    if (!strTemporary.contains(m)) {
                        if (index > 3) {
                            break;
                        }
                        ++index;
                        HashMap<Integer, String> stChild = new HashMap<Integer, String>();
                        stChild.put(index, m);
                        if (index > 3) {
                            strTemporary += m;
                        } else {
                            strTemporary += m + "|";
                        }

                        list.add(stChild);
                    }
                }
                Optionss = strTemporary;
                //---把选项打乱顺序生成新顺序
                ArrayList list1 = helperService.randomSortList(list);
                HashMap<HashMap<Integer, String>, Integer> dicSort = new HashMap<HashMap<Integer, String>, Integer>();
                int sortCount = 0;
                for (int i = 0; i < list1.size(); i++) {
                    ++sortCount;
                    dicSort.put((HashMap<Integer, String>) list1.get(i), sortCount);
                }
                int count = 5;
                for (HashMap.Entry<HashMap<Integer, String>, Integer> entry : dicSort.entrySet()) {

                    for (Map.Entry<Integer, String> entry1 : entry.getKey().entrySet()) {
                        //创建一个<QuesOption>节点
                        Element quesOption = document.createElement("QuesOption");
                        //quesOption.val(entry1.getValue());
                        quesOption.text(entry1.getValue());
                        //int count1 = ++count;
                        quesOption.attr("index", String.valueOf(--count));
                        document.getElementsByTag("QuesAnalyze").after(quesOption.toString().replace("\n", ""));
                        if (entry1.getKey() == 1) {
                            quesAnswer = String.valueOf(count);
                        }
                    }
                }
                document.getElementsByTag("QuesAnswer").first().text(quesAnswer.replace("&lt;", "<").replace("&gt;", ">"));
                Answer = document.getElementsByTag("QuesAnswer").first().text();
                //去自动换行
                document.outputSettings().prettyPrint(false);
                //查属性对应的编码，更新NewProperty这个字段
                String newProperty = "";
                //识别和插入到原始库
                query.AutoEdit("C", Stage, document.html(), "A", 13, TaskId, item.getKnowledgename(), item.getSentence() , newProperty);
                String sdf = document.html();
                lgdbBKlgOrgresourceMapper.LgdbBKlgOrgresourceUpdate(Stage, "A131", item.getId());
            }
        }
        ArrayList<String> strings = new ArrayList<>();
        if (Ask != null && Answer != null && Optionss != null) {
            int length = Ask.split("\\|").length;
            for(int i=0;i<length;i++){
                if(i==0){
                    strings.add("题干：" + Ask.split("\\|")[i]);
                }else {
                    strings.add(Ask.split("\\|")[i] +"  ");
                }
            }

            String[] split = Optionss.split("\\|");
            if (split.length == 4) {
                strings.add("选项:   " + "A. " + split[0] + "      " + "B. " + split[1] + "      " + "C. " + split[2] + "      " + "D. " + split[3]);
            } else if (split.length == 3) {
                strings.add("选项:   " + "A. " + split[0] + "      " + "B. " + split[1] + "      " + "C. " + split[2]);
            } else if (split.length == 2) {
                strings.add("选项:   " + "A. " + split[0] + "      " + "B. " + split[1]);
            } else if (split.length == 1) {
                strings.add("选项:  " + "A. " + split[0]);
            }
            strings.add("答案: " + Answer);
        }
        return strings;
    }

    //单选题，C阶段　　　　　单词题,A,13,英语常用表达
    public ArrayList<String> quesTypeA13expressC(LgdbCKlgOrgresource item, String Stage, String TaskId, MainService query) throws Exception {
        String sbXmlTemplet = ContentDao.TYPE_A;
        //解析html
        Parser parser = Parser.htmlParser();
        parser.settings(new ParseSettings(true, true));
        Document document = parser.parseInput(sbXmlTemplet, "utf-8");
        String Ask = "";
        String Answer = null;
        String Optionss = null;
        String getTypeCode = helperService.getQuesTypeService(item.getId(), Stage);
        if (helperService.pickTargetWordOneexpress(item.getSentence(), item.getKnowledgename(), "") == 1  && helperService.pickUpTargetWordC(item)!=null) {
            String emptyWord = helperService.pickUpTargetWordC(item);
            //造出题干，
            String AAA = item.getSentence().replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");
            String ask1 = helperService.getTextFromTHML(AAA).replace(emptyWord, "____");
            String ask = ask1.replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");
            // String ask = helperService.getTextFromTHML(item.getSentence()).replace(emptyWord, "____");
            // String ask = item.getSentence().replace("<div>", "").replace("</div>", "").replace("&lt;", "<").replace("&gt;", ">").replace("< strong >", "<strong>").replace("</ strong >", "</strong>").replace("< u >", "<u>").replace("</ u >", "</u>").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong>", "").replace("<u>", "").replace("</u>", "").replace("</strong>", "").replace(emptyWord, "____");
            //---拼接题干--
            //有对话的情况要分开
            if (ask.contains("Ж")) {
                String[] жs = ask.split("Ж");
                for (int i = 0; i < жs.length; i++) {
                    document.getElementsByTag("QuesOptionAsk").first().appendText("<P class=tq-p>" + жs[i] + "</P>" + " ");
                    if(i+1 == жs.length){
                        Ask +=жs[i];
                    }else {
                        Ask +=жs[i]+ "|";
                    }
                }
            } else {
                document.getElementsByTag("QuesOptionAsk").first().text("<P class=tq-p>" + ask.trim() + "</P>");
                Ask = ask.trim();
            }
            org.jsoup.nodes.Element tContent = document.getElementsByTag("TContent").first();
            org.jsoup.nodes.Element quesChild = document.getElementsByTag("QuesChild").first();
            // 核心逻辑
            int index = 0;
            String quesAnswer = "";
            //临时变量 确保不重复
            String strTemporary = "";
            // 造出选项和干扰项
            ArrayList<HashMap<Integer, String>> list = new ArrayList<HashMap<Integer, String>>();
            HashMap<Integer, String> stOther = new HashMap<Integer, String>();
            //处理wemptyWord
            //emptyWord.replace("<u>", "").replace("</u>ing", "").replace("</u>ed", "").replace("</u>es", "").replace("</u>s", "").replace("<strong>", "").replace("</strong>ing", "").replace("</strong>ed", "").replace("</strong>es", "").replace("</strong>s", "").trim();
            stOther.put(++index, emptyWord);
            list.add(stOther);
            strTemporary += emptyWord + "|";
            //其他干扰项,再增加一个方法，将条件放宽，修改查询条件
            //ArrayList<String> otherWordList = helperService.getFirstNumber1(emptyWord);
            /****
             *
             * 干扰项　
             *
             * 将挖空的目标知识点随机打乱，并随机选取与目标知识点不同主题下的三个知识点作为干扰项，并以大写英语字母编号。各选项之间的单词长度小于5。
             */
            //以目标知识点为正确选项在选项库中查找
            ArrayList<RightAnswerDisturb> rightAnswerDisturbs = rightAnswerDisturbMapper.selectAnswers(item.getKnowledgename());
            ArrayList<RightAnswerDisturb> rightAnswerDisturbs1 = rightAnswerDisturbMapper.selectAnswersByDisturb(item.getKnowledgename());
            ArrayList<String> otherWordList1 = new ArrayList<>();
            ArrayList<String> otherWordList2 = new ArrayList<>();
            ArrayList<String> otherWordList5 = new ArrayList<>();
            if(rightAnswerDisturbs!=null && rightAnswerDisturbs.size()>0){
                for (RightAnswerDisturb m:rightAnswerDisturbs) {
                    otherWordList2.add(m.getDisturbb().replace(".","").replace("!","").replace("?",""));
                    otherWordList2.add(m.getDisturbc().replace(".","").replace("!","").replace("?",""));
                    boolean present = Optional.ofNullable(m.getDisturbd()).isPresent();
                    if(present){
                        otherWordList2.add(m.getDisturbd().replace(".","").replace("!","").replace("?",""));
                    }
                }
                ArrayList<String> otherWordList3 = (ArrayList<String>) otherWordList2.stream().distinct().collect(Collectors.toList());
                otherWordList1.addAll(otherWordList3);

            }
            if(!(otherWordList1.size() >4) && rightAnswerDisturbs1!=null && rightAnswerDisturbs1.size()>0){
                //以目标知识点为非正确选项在选项库中查找
                for (RightAnswerDisturb m:rightAnswerDisturbs1) {
                    if(!m.getDisturbb().equals(item.getKnowledgename())){
                        otherWordList5.add(m.getDisturbb().replace(".","").replace("!","").replace("?",""));
                    }
                    if(!m.getDisturbc().equals(item.getKnowledgename())){
                        otherWordList5.add(m.getDisturbc().replace(".","").replace("!","").replace("?",""));
                    }
                    boolean present = Optional.ofNullable(m.getDisturbd()).isPresent();
                    if(present){
                        if(!m.getDisturbd().equals(item.getKnowledgename())){
                            otherWordList5.add(m.getDisturbd().replace(".","").replace("!","").replace("?",""));
                        }
                    }
                }
                ArrayList<String> otherWordList3 = (ArrayList<String>) otherWordList5.stream().distinct().collect(Collectors.toList());
                otherWordList1.addAll(otherWordList3);
            }
//            if(!(otherWordList1.size() >4)){
//                String knowledgetype = item.getKnowledgetype();
//                String theme = lgdbBasicKnowledgeandcodeMapper.selectThemeByKnowledeCode(item.getKnowledgecode());
//                ArrayList<String> otherWordList4 = lgdbBKlgOrgresourceMapper.findLgdbBKlgOrgresources9(theme, "B", knowledgetype);
//                //有其他需要屏蔽的选项后续再添加　　
//                ArrayList<String> otherWordList7 = (ArrayList<String>) otherWordList4.stream().filter(c -> !(c.contains("sb.") || c.contains("sth.") || c.contains("(") || c.contains("/") || c.contains("+"))).collect(Collectors.toList());
//                ArrayList<String> otherWordList6 = new ArrayList<>();
//                for (String m: otherWordList7) {
//                    String m1 = m.replace(".", "").replace("!", "").replace("?", "");
//                    otherWordList6.add(m1);
//                }
//                ArrayList<String> otherWordList3 = (ArrayList<String>) otherWordList6.stream().distinct().collect(Collectors.toList());
//                otherWordList1.addAll(otherWordList3);
//            }

            // ArrayList<String> collect = (ArrayList<String>) otherWordList1.stream().filter(c -> helperService.interLengthLessFive1(item.getKnowledgename().split(" ").length, otherWordList1) == 0).collect(Collectors.toList());
            ArrayList<String> otherWordList = new ArrayList<>();
            for (String m: otherWordList1) {
                if(otherWordList.size()==0){
                    if(helperService.interLengthLessFive2(m.split(" ").length,item.getKnowledgename().split(" ").length)==0){
                        otherWordList.add(m);
                    }
                }else {
                    if(helperService.interLengthLessFive1(m.split(" ").length, otherWordList)==0 && helperService.interLengthLessFive2(m.split(" ").length,item.getKnowledgename().split(" ").length)==0){
                        otherWordList.add(m);
                    }
                }
            }

            if (otherWordList.size() > 4) {
                for (String m : otherWordList) {
                    if (!strTemporary.contains(m)) {
                        if (index > 3) {
                            break;
                        }
                        ++index;
                        HashMap<Integer, String> stChild = new HashMap<Integer, String>();
                        stChild.put(index, m);
                        if (index > 3) {
                            strTemporary += m;
                        } else {
                            strTemporary += m + "|";
                        }

                        list.add(stChild);
                    }
                }
                Optionss = strTemporary;
                //---把选项打乱顺序生成新顺序
                ArrayList list1 = helperService.randomSortList(list);
                HashMap<HashMap<Integer, String>, Integer> dicSort = new HashMap<HashMap<Integer, String>, Integer>();
                int sortCount = 0;
                for (int i = 0; i < list1.size(); i++) {
                    ++sortCount;
                    dicSort.put((HashMap<Integer, String>) list1.get(i), sortCount);
                }
                int count = 5;
                for (HashMap.Entry<HashMap<Integer, String>, Integer> entry : dicSort.entrySet()) {

                    for (Map.Entry<Integer, String> entry1 : entry.getKey().entrySet()) {
                        //创建一个<QuesOption>节点
                        Element quesOption = document.createElement("QuesOption");
                        //quesOption.val(entry1.getValue());
                        quesOption.text(entry1.getValue());
                        //int count1 = ++count;
                        quesOption.attr("index", String.valueOf(--count));
                        document.getElementsByTag("QuesAnalyze").after(quesOption.toString().replace("\n", ""));
                        if (entry1.getKey() == 1) {
                            quesAnswer = String.valueOf(count);
                        }
                    }
                }
                document.getElementsByTag("QuesAnswer").first().text(quesAnswer.replace("&lt;", "<").replace("&gt;", ">"));
                Answer = document.getElementsByTag("QuesAnswer").first().text();
                //去自动换行
                document.outputSettings().prettyPrint(false);
                //查属性对应的编码，更新NewProperty这个字段
                String newProperty = "";
                //识别和插入到原始库
                query.AutoEditC("C", Stage, document.html(), "A", 13, TaskId, item.getKnowledgename(), item.getSentence() , newProperty);
                String sdf = document.html();
                lgdbCKlgOrgresourceMapper.LgdbCKlgOrgresourceUpdate(Stage, "A131", item.getId());
            }
        }
        ArrayList<String> strings = new ArrayList<>();
        if (Ask != null && Answer != null && Optionss != null) {
            int length = Ask.split("\\|").length;
            for(int i=0;i<length;i++){
                if(i==0){
                    strings.add("题干：" + Ask.split("\\|")[i]);
                }else {
                    strings.add(Ask.split("\\|")[i] +"  ");
                }
            }

            String[] split = Optionss.split("\\|");
            if (split.length == 4) {
                strings.add("选项:   " + "A. " + split[0] + "      " + "B. " + split[1] + "      " + "C. " + split[2] + "      " + "D. " + split[3]);
            } else if (split.length == 3) {
                strings.add("选项:   " + "A. " + split[0] + "      " + "B. " + split[1] + "      " + "C. " + split[2]);
            } else if (split.length == 2) {
                strings.add("选项:   " + "A. " + split[0] + "      " + "B. " + split[1]);
            } else if (split.length == 1) {
                strings.add("选项:  " + "A. " + split[0]);
            }
            strings.add("答案: " + Answer);
        }
        return strings;
    }


    //词汇填空题， 句子填空,N,21,无提示,英语单词
    public ArrayList<String> quesTypeN211(LgdbBKlgOrgresource item, String Stage, String TaskId, MainService query) throws Exception {
        String sbXmlTemplet = ContentDao.TYPE_N;
        //解析html,保留大小写
        Parser parser = Parser.htmlParser();
        parser.settings(new ParseSettings(true, true));
        Document document = parser.parseInput(sbXmlTemplet, "utf-8");

        String Ask = null;
        String Answer = null;
        if (helperService.pickTargetWordOneword(item.getSentence(), item.getKnowledgename(), "") == 1  && helperService.pickUpTargetWord(item)!=null) {
            String emptyWord = helperService.pickUpTargetWord(item);
            String ask = null;
            // 造出题干
            //去标志符号
            String AAA = item.getSentence().replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");
            String ask1 = helperService.getTextFromTHML(AAA).replace(emptyWord, "____");
            ask = ask1.replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");


//            ask = item.getSentence().replace("<div>", "").replace("</div>", "").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong><u>", "").replace("</u></strong>", "").replace("<u>", "").replace("<strong>", "").replace("</u>", "").replace("</strong>", "").replace(emptyWord, "____");

            //有对话的情况要分开
            if (ask.contains("Ж")) {
                String[] жs = ask.split("Ж");
                for (int i = 0; i < жs.length; i++) {
                    document.getElementsByTag("QuesOptionAsk").first().appendText("<P class=tq-p>" + жs[i] + "</P>" + " ").append("\\n" + " ");
                }
            } else {
                document.getElementsByTag("QuesOptionAsk").first().text("<P class=tq-p>" + ask.trim() + "</P>");
            }

//            document.getElementsByTag("QuesOptionAsk").first().text("<P class=tq-p>" + ask.trim() + "</P>");
            Ask = ask.trim();

            Answer = emptyWord;
            document.getElementsByTag("QuesAnswer").first().text(emptyWord);
            org.jsoup.nodes.Element body = document.getElementsByTag("body").first();
            //去自动换行
            document.outputSettings().prettyPrint(false);
            String html = body.html();
            //查属性对应的编码，更新NewProperty这个字段
            String newProperty = lgdbBasicQuestionclassifynameMapper.selectNewproperty("无提示");
            //识别和插入到原始库
            query.AutoEdit("C", Stage, html, "N", 21, TaskId, item.getKnowledgename(), item.getSentence(), newProperty);
            lgdbBKlgOrgresourceMapper.LgdbBKlgOrgresourceUpdate(Stage, "N211", item.getId());
        }
        ArrayList<String> strings = new ArrayList<>();
        if (Ask != null && Answer != null) {
            strings.add("题干：" + Ask);
            strings.add("【参考答案】: " + Answer);
        }
        return strings;
    }

    //词汇填空题，C阶段 句子填空,N,21,无提示,英语单词
    public ArrayList<String> quesTypeN211C(LgdbCKlgOrgresource item, String Stage, String TaskId, MainService query) throws Exception {
        String sbXmlTemplet = ContentDao.TYPE_N;
        //解析html,保留大小写
        Parser parser = Parser.htmlParser();
        parser.settings(new ParseSettings(true, true));
        Document document = parser.parseInput(sbXmlTemplet, "utf-8");

        String Ask = null;
        String Answer = null;
        if (helperService.pickTargetWordOneword(item.getSentence(), item.getKnowledgename(), "") == 1  && helperService.pickUpTargetWordC(item)!=null) {
            String emptyWord = helperService.pickUpTargetWordC(item);
            String ask = null;
            // 造出题干
            //去标志符号
            String AAA = item.getSentence().replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");
            String ask1 = helperService.getTextFromTHML(AAA).replace(emptyWord, "____");
            ask = ask1.replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");


//            ask = item.getSentence().replace("<div>", "").replace("</div>", "").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong><u>", "").replace("</u></strong>", "").replace("<u>", "").replace("<strong>", "").replace("</u>", "").replace("</strong>", "").replace(emptyWord, "____");

            //有对话的情况要分开
            if (ask.contains("Ж")) {
                String[] жs = ask.split("Ж");
                for (int i = 0; i < жs.length; i++) {
                    document.getElementsByTag("QuesOptionAsk").first().appendText("<P class=tq-p>" + жs[i] + "</P>" + " ").append("\\n" + " ");
                }
            } else {
                document.getElementsByTag("QuesOptionAsk").first().text("<P class=tq-p>" + ask.trim() + "</P>");
            }

//            document.getElementsByTag("QuesOptionAsk").first().text("<P class=tq-p>" + ask.trim() + "</P>");
            Ask = ask.trim();

            Answer = emptyWord;
            document.getElementsByTag("QuesAnswer").first().text(emptyWord);
            org.jsoup.nodes.Element body = document.getElementsByTag("body").first();
            //去自动换行
            document.outputSettings().prettyPrint(false);
            String html = body.html();
            //查属性对应的编码，更新NewProperty这个字段
            String newProperty = lgdbBasicQuestionclassifynameMapper.selectNewproperty("无提示");
            //识别和插入到原始库
            query.AutoEditC("C", Stage, html, "N", 21, TaskId, item.getKnowledgename(), item.getSentence(), newProperty);
            lgdbCKlgOrgresourceMapper.LgdbCKlgOrgresourceUpdate(Stage, "N211", item.getId());
        }
        ArrayList<String> strings = new ArrayList<>();
        if (Ask != null && Answer != null) {
            strings.add("题干：" + Ask);
            strings.add("【参考答案】: " + Answer);
        }
        return strings;
    }


    //词汇填空题， 句子填空,N,21,首字母提示,英语单词
    public ArrayList<String> quesTypeN212(LgdbBKlgOrgresource item, String Stage, String TaskId, MainService query) throws Exception {
        String sbXmlTemplet = ContentDao.TYPE_N;
        //解析html,保留大小写
        Parser parser = Parser.htmlParser();
        parser.settings(new ParseSettings(true, true));
        Document document = parser.parseInput(sbXmlTemplet, "utf-8");
        String Ask = null;
        String Answer = null;
        if (helperService.pickTargetWordOneword(item.getSentence(), item.getKnowledgename(), "") == 1  && helperService.pickUpTargetWord(item)!=null) {
            String emptyWord = helperService.pickUpTargetWord(item);
            String[] s = emptyWord.split("");
            String ask = null;
            // 造出题干
            //去标志符号
            String AAA = item.getSentence().replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");
            String ask1 = helperService.getTextFromTHML(AAA).replace(emptyWord, "(" + s[0] + "-" + ")" + "____");
            ask = ask1.replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");




//            ask = item.getSentence().replace("<div>", "").replace("</div>", "").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong><u>", "").replace("</u></strong>", "").replace("<u>", "").replace("<strong>", "").replace("</u>", "").replace("</strong>", "").replace(emptyWord, "(" + s[0] + "-" + ")" + "____");
            //有对话的情况要分开
            if (ask.contains("Ж")) {
                String[] жs = ask.split("Ж");
                for (int i = 0; i < жs.length; i++) {
                    document.getElementsByTag("QuesOptionAsk").first().appendText("<P class=tq-p>" + жs[i] + "</P>" + " ").append("\\n" + " ");
                }
            } else {
                document.getElementsByTag("QuesOptionAsk").first().text("<P class=tq-p>" + ask.trim() + "</P>");
            }
//            document.getElementsByTag("QuesOptionAsk").first().text("<P class=tq-p>" + ask.trim() + "</P>");
            Ask = ask.trim();

            Answer = emptyWord;
            document.getElementsByTag("QuesAnswer").first().text(emptyWord);
            org.jsoup.nodes.Element body = document.getElementsByTag("body").first();
            //去自动换行
            document.outputSettings().prettyPrint(false);
            String html = body.html();
            //查属性对应的编码，更新NewProperty这个字段
//            String aaa = "首字母";
//            Optional.ofNullable(aaa).isPresent((aa)->lgdbBasicQuestionclassifynameMapper.selectNewproperty(aa)).orElse(100);
            String newProperty = lgdbBasicQuestionclassifynameMapper.selectNewproperty("首字母");

            //识别和插入到原始库
            query.AutoEdit("C", Stage, html, "N", 21, TaskId, item.getKnowledgename(), item.getSentence(), newProperty);
            lgdbBKlgOrgresourceMapper.LgdbBKlgOrgresourceUpdate(Stage, "N212", item.getId());
        }
        ArrayList<String> strings = new ArrayList<>();
        if (Ask != null && Answer != null) {
            strings.add("题干：" + Ask);
            strings.add("【参考答案】: " + Answer);
        }
        return strings;
    }

    //词汇填空题， C阶段 句子填空,N,21,首字母提示,英语单词
    public ArrayList<String> quesTypeN212C(LgdbCKlgOrgresource item, String Stage, String TaskId, MainService query) throws Exception {
        String sbXmlTemplet = ContentDao.TYPE_N;
        //解析html,保留大小写
        Parser parser = Parser.htmlParser();
        parser.settings(new ParseSettings(true, true));
        Document document = parser.parseInput(sbXmlTemplet, "utf-8");
        String Ask = null;
        String Answer = null;
        if (helperService.pickTargetWordOneword(item.getSentence(), item.getKnowledgename(), "") == 1  && helperService.pickUpTargetWordC(item)!=null) {
            String emptyWord = helperService.pickUpTargetWordC(item);
            String[] s = emptyWord.split("");
            String ask = null;
            // 造出题干
            //去标志符号
            String AAA = item.getSentence().replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");
            String ask1 = helperService.getTextFromTHML(AAA).replace(emptyWord, "(" + s[0] + "-" + ")" + "____");
            ask = ask1.replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");
            // ask = item.getSentence().replace("<div>", "").replace("</div>", "").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong><u>", "").replace("</u></strong>", "").replace("<u>", "").replace("<strong>", "").replace("</u>", "").replace("</strong>", "").replace(emptyWord, "(" + s[0] + "-" + ")" + "____");
            //有对话的情况要分开
            if (ask.contains("Ж")) {
                String[] жs = ask.split("Ж");
                for (int i = 0; i < жs.length; i++) {
                    document.getElementsByTag("QuesOptionAsk").first().appendText("<P class=tq-p>" + жs[i] + "</P>" + " ").append("\\n" + " ");
                }
            } else {
                document.getElementsByTag("QuesOptionAsk").first().text("<P class=tq-p>" + ask.trim() + "</P>");
            }
           // document.getElementsByTag("QuesOptionAsk").first().text("<P class=tq-p>" + ask.trim() + "</P>");
            Ask = ask.trim();
            Answer = emptyWord;
            document.getElementsByTag("QuesAnswer").first().text(emptyWord);
            org.jsoup.nodes.Element body = document.getElementsByTag("body").first();
            //去自动换行
            document.outputSettings().prettyPrint(false);
            String html = body.html();
            //查属性对应的编码，更新NewProperty这个字段
            String newProperty = lgdbBasicQuestionclassifynameMapper.selectNewproperty("首字母");
            //识别和插入到原始库
            query.AutoEditC("C", Stage, html, "N", 21, TaskId, item.getKnowledgename(), item.getSentence(), newProperty);
            lgdbCKlgOrgresourceMapper.LgdbCKlgOrgresourceUpdate(Stage, "N212", item.getId());
        }
        ArrayList<String> strings = new ArrayList<>();
        if (Ask != null && Answer != null) {
            strings.add("题干：" + Ask);
            strings.add("【参考答案】: " + Answer);
        }
        return strings;
    }


    //词汇填空题，汉语提示  句子填空,N,21,汉语提示,英语单词
    public ArrayList<String> quesTypeN213(LgdbBKlgOrgresource item, String Stage, String TaskId, MainService query) throws Exception {
        String sbXmlTemplet = ContentDao.TYPE_N;
        //解析html,保留大小写
        Parser parser = Parser.htmlParser();
        parser.settings(new ParseSettings(true, true));
        Document document = parser.parseInput(sbXmlTemplet, "utf-8");
        String Ask = null;
        String Answer = null;
        if (helperService.pickTargetWordOneword(item.getSentence(), item.getKnowledgename(), "") == 1 && helperService.pickUpTargetWord(item)!=null) {
           // && helperService.pickUpTargetWord(item)!=null && helperService.pickUpTargetWord(item)!=""
            String emptyWord = helperService.pickUpTargetWord(item);
            String maincontext = null;
            String maincontext1 = item.getMaincontext();
            if (!(item.getMaincontext() == null)) {
                String s = item.getMaincontext().replaceAll("\\[(.*)]", "").replaceAll("（(.*)）","").replaceAll("\\((.*)\\)","");
                String maincontext2 = s.replace("modal", "情态动词").replace("vi.&amp;vt.", "").replace("vt.&amp;vi.", "").replace("vt.&amp;", "").replace("vi.&amp;", "").replace("vt.", "").replace("vi.", "").replace("&amp;", "").replace("&lt;英&gt;", "").replace("&lt;美&gt;", "").replace("&lt;", "").replace("&gt;", "").replace("&lt;美", "").replace("美&gt;", "").split(";")[0];
//                String AA = "[大纲][C]链子，链条";
//                String s = AA.replaceAll("\\[(.*)]", "").replaceAll("（(.*)）","").replaceAll("\\((.*)\\)","");
//                String maincontext2 = s.replace("modal", "情态动词").replace("vi.&amp;vt.", "").replace("vt.&amp;vi.", "").replace("vt.&amp;", "").replace("vi.&amp;", "").replace("vt.", "").replace("vi.", "").replace("&amp;", "").replace("&lt;英&gt;", "").replace("&lt;美&gt;", "").replace("&lt;", "").replace("&gt;", "").replace("&lt;美", "").replace("美&gt;", "").split(";")[0];

                if(maincontext2.contains("，")){
                    maincontext = maincontext2.split("，")[0];
                }else if(maincontext2.contains(",")){
                    maincontext = maincontext2.split(",")[0];
                }else {
                    maincontext = maincontext2;
                }
            }
            String ask = null;
            // 造出题干
            //去标志符号
            String AAA = item.getSentence().replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");
            String ask1 = helperService.getTextFromTHML(AAA).replace(emptyWord, "____" + "(" + maincontext + ")");
            ask = ask1.replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");


//            ask = item.getSentence().replace("<div>", "").replace("</div>", "").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong><u>", "").replace("</u></strong>", "").replace("<u>", "").replace("<strong>", "").replace("</u>", "").replace("</strong>", "").replace(emptyWord, "____" + "(" + maincontext + ")");

            //有对话的情况要分开
            if (ask.contains("Ж")) {
                String[] жs = ask.split("Ж");
                for (int i = 0; i < жs.length; i++) {
                    document.getElementsByTag("QuesOptionAsk").first().appendText("<P class=tq-p>" + жs[i] + "</P>" + " ").append("\\n" + " ");
                }
            } else {
                document.getElementsByTag("QuesOptionAsk").first().text("<P class=tq-p>" + ask.trim() + "</P>");
            }

//            document.getElementsByTag("QuesOptionAsk").first().text("<P class=tq-p>" + ask.trim() + "</P>");
            Ask = ask.trim();
            Answer = emptyWord;
            document.getElementsByTag("QuesAnswer").first().text(emptyWord);
            org.jsoup.nodes.Element body = document.getElementsByTag("body").first();
            //去自动换行
            document.outputSettings().prettyPrint(false);
            String html = body.html();
            //查属性对应的编码，更新NewProperty这个字段
            String newProperty = lgdbBasicQuestionclassifynameMapper.selectNewproperty("汉语");
            //识别和插入到原始库
            query.AutoEdit("C", Stage, html, "N", 21, TaskId, item.getKnowledgename(), item.getSentence() , newProperty);
            lgdbBKlgOrgresourceMapper.LgdbBKlgOrgresourceUpdate(Stage, "N213", item.getId());
        }
        ArrayList<String> strings = new ArrayList<>();
        if (Ask != null && Answer != null) {
            strings.add("题干：" + Ask);
            strings.add("【参考答案】: " + Answer);
        }
        return strings;
    }

    //词汇填空题，汉语提示  C阶段 句子填空,N,21,汉语提示,英语单词
    public ArrayList<String> quesTypeN213C(LgdbCKlgOrgresource item, String Stage, String TaskId, MainService query) throws Exception {
        String sbXmlTemplet = ContentDao.TYPE_N;
        //解析html,保留大小写
        Parser parser = Parser.htmlParser();
        parser.settings(new ParseSettings(true, true));
        Document document = parser.parseInput(sbXmlTemplet, "utf-8");
        String Ask = null;
        String Answer = null;
        if (helperService.pickTargetWordOneword(item.getSentence(), item.getKnowledgename(), "") == 1 && helperService.pickUpTargetWordC(item)!=null) {
            // && helperService.pickUpTargetWord(item)!=null && helperService.pickUpTargetWord(item)!=""
            String emptyWord = helperService.pickUpTargetWordC(item);
            String maincontext = null;
            String maincontext1 = item.getMaincontext();
            if (!(item.getMaincontext() == null)) {
                String s = item.getMaincontext().replaceAll("\\[(.*)]", "").replaceAll("（(.*)）","").replaceAll("\\((.*)\\)","");
                String maincontext2 = s.replace("modal", "情态动词").replace("vi.&amp;vt.", "").replace("vt.&amp;vi.", "").replace("vt.&amp;", "").replace("vi.&amp;", "").replace("vt.", "").replace("vi.", "").replace("&amp;", "").replace("&lt;英&gt;", "").replace("&lt;美&gt;", "").replace("&lt;", "").replace("&gt;", "").replace("&lt;美", "").replace("美&gt;", "").split(";")[0];
//                String AA = "[大纲][C]链子，链条";
//                String s = AA.replaceAll("\\[(.*)]", "").replaceAll("（(.*)）","").replaceAll("\\((.*)\\)","");
//                String maincontext2 = s.replace("modal", "情态动词").replace("vi.&amp;vt.", "").replace("vt.&amp;vi.", "").replace("vt.&amp;", "").replace("vi.&amp;", "").replace("vt.", "").replace("vi.", "").replace("&amp;", "").replace("&lt;英&gt;", "").replace("&lt;美&gt;", "").replace("&lt;", "").replace("&gt;", "").replace("&lt;美", "").replace("美&gt;", "").split(";")[0];

                if(maincontext2.contains("，")){
                    maincontext = maincontext2.split("，")[0];
                }else if(maincontext2.contains(",")){
                    maincontext = maincontext2.split(",")[0];
                }else {
                    maincontext = maincontext2;
                }
            }
            String ask = null;
            // 造出题干
            //去标志符号
            String AAA = item.getSentence().replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");
            String ask1 = helperService.getTextFromTHML(AAA).replace(emptyWord, "____" + "(" + maincontext + ")");
            ask = ask1.replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");


//            ask = item.getSentence().replace("<div>", "").replace("</div>", "").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong><u>", "").replace("</u></strong>", "").replace("<u>", "").replace("<strong>", "").replace("</u>", "").replace("</strong>", "").replace(emptyWord, "____" + "(" + maincontext + ")");

            //有对话的情况要分开
            if (ask.contains("Ж")) {
                String[] жs = ask.split("Ж");
                for (int i = 0; i < жs.length; i++) {
                    document.getElementsByTag("QuesOptionAsk").first().appendText("<P class=tq-p>" + жs[i] + "</P>" + " ").append("\\n" + " ");
                }
            } else {
                document.getElementsByTag("QuesOptionAsk").first().text("<P class=tq-p>" + ask.trim() + "</P>");
            }

//            document.getElementsByTag("QuesOptionAsk").first().text("<P class=tq-p>" + ask.trim() + "</P>");
            Ask = ask.trim();
            Answer = emptyWord;
            document.getElementsByTag("QuesAnswer").first().text(emptyWord);
            org.jsoup.nodes.Element body = document.getElementsByTag("body").first();
            //去自动换行
            document.outputSettings().prettyPrint(false);
            String html = body.html();
            //查属性对应的编码，更新NewProperty这个字段
            String newProperty = lgdbBasicQuestionclassifynameMapper.selectNewproperty("汉语");
            //识别和插入到原始库
            query.AutoEditC("C", Stage, html, "N", 21, TaskId, item.getKnowledgename(), item.getSentence() , newProperty);
            lgdbCKlgOrgresourceMapper.LgdbCKlgOrgresourceUpdate(Stage, "N213", item.getId());
        }
        ArrayList<String> strings = new ArrayList<>();
        if (Ask != null && Answer != null) {
            strings.add("题干：" + Ask);
            strings.add("【参考答案】: " + Answer);
        }
        return strings;
    }


    //词汇填空题，音标提示  句子填空,N,21,音标提示,英语单词
    public ArrayList<String> quesTypeN214(LgdbBKlgOrgresource item, String Stage, String TaskId, MainService query) throws Exception {
        String sbXmlTemplet = ContentDao.TYPE_N;
        //解析html,保留大小写
        Parser parser = Parser.htmlParser();
        parser.settings(new ParseSettings(true, true));
        Document document = parser.parseInput(sbXmlTemplet, "utf-8");
        String Ask = null;
        String Answer = null;
        if (helperService.pickTargetWordOriginalOneword(item.getSentence(), item.getKnowledgename(), "") == 1) {
            String emptyWord = item.getKnowledgename();
            String maincontext = null;
            //使用英式音标
            String u = item.getUnPhonetic();
            String unPhonetic = StringEscapeUtils.unescapeHtml(u);
            String ask = null;
            // 造出题干
            //去标志符号
//            ask = item.getSentence().replace("<div>", "").replace("</div>", "").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong><u>", "").replace("</u></strong>", "").replace("<u>", "").replace("<strong>", "").replace("</u>", "").replace("</strong>", "").replace(emptyWord, "____" + unPhonetic  );

            String AAA = item.getSentence().replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");
            String ask1 = helperService.getTextFromTHML(AAA).replace(emptyWord, "____" + unPhonetic  );
            ask = ask1.replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");




            //有对话的情况要分开
            if (ask.contains("Ж")) {
                String[] жs = ask.split("Ж");
                for (int i = 0; i < жs.length; i++) {
                    document.getElementsByTag("QuesOptionAsk").first().appendText("<P class=tq-p>" + жs[i] + "</P>" + " ").append("\\n" + " ");
                }
            } else {
                document.getElementsByTag("QuesOptionAsk").first().text("<P class=tq-p>" + ask.trim() + "</P>");
            }

//            document.getElementsByTag("QuesOptionAsk").first().text("<P class=tq-p>" + ask.trim() + "</P>");
            Ask = ask.trim();
            Answer = emptyWord;
            document.getElementsByTag("QuesAnswer").first().text(emptyWord);
            org.jsoup.nodes.Element body = document.getElementsByTag("body").first();
            //去自动换行
            document.outputSettings().prettyPrint(false);
            String html = body.html();
            //查属性对应的编码，更新NewProperty这个字段
            String newProperty = lgdbBasicQuestionclassifynameMapper.selectNewproperty("音标");
            //识别和插入到原始库
            query.AutoEdit("C", Stage, html, "N", 21, TaskId, item.getKnowledgename(), item.getSentence() , newProperty);
            lgdbBKlgOrgresourceMapper.LgdbBKlgOrgresourceUpdate(Stage, "N214", item.getId());
        }
        ArrayList<String> strings = new ArrayList<>();
        if (Ask != null && Answer != null) {
            strings.add("题干：" + Ask);
            strings.add("【参考答案】: " + Answer);
        }
        return strings;
    }

    //词汇填空题，音标提示 C阶段  句子填空,N,21,音标提示,英语单词
    public ArrayList<String> quesTypeN214C(LgdbCKlgOrgresource item, String Stage, String TaskId, MainService query) throws Exception {
        String sbXmlTemplet = ContentDao.TYPE_N;
        //解析html,保留大小写
        Parser parser = Parser.htmlParser();
        parser.settings(new ParseSettings(true, true));
        Document document = parser.parseInput(sbXmlTemplet, "utf-8");
        String Ask = null;
        String Answer = null;
        if (helperService.pickTargetWordOriginalOneword(item.getSentence(), item.getKnowledgename(), "") == 1) {
            String emptyWord = item.getKnowledgename();
            String maincontext = null;
            //使用英式音标
            String u = item.getUnPhonetic();
            String unPhonetic = StringEscapeUtils.unescapeHtml(u);
            String ask = null;
            // 造出题干
            //去标志符号
            // ask = item.getSentence().replace("<div>", "").replace("</div>", "").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong><u>", "").replace("</u></strong>", "").replace("<u>", "").replace("<strong>", "").replace("</u>", "").replace("</strong>", "").replace(emptyWord, "____" + unPhonetic  );

            String AAA = item.getSentence().replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");
            String ask1 = helperService.getTextFromTHML(AAA).replace(emptyWord, "____" + unPhonetic  );
            ask = ask1.replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");
            //有对话的情况要分开
            if (ask.contains("Ж")) {
                String[] жs = ask.split("Ж");
                for (int i = 0; i < жs.length; i++) {
                    document.getElementsByTag("QuesOptionAsk").first().appendText("<P class=tq-p>" + жs[i] + "</P>" + " ").append("\\n" + " ");
                }
            } else {
                document.getElementsByTag("QuesOptionAsk").first().text("<P class=tq-p>" + ask.trim() + "</P>");
            }
            //   document.getElementsByTag("QuesOptionAsk").first().text("<P class=tq-p>" + ask.trim() + "</P>");
            Ask = ask.trim();
            Answer = emptyWord;
            document.getElementsByTag("QuesAnswer").first().text(emptyWord);
            org.jsoup.nodes.Element body = document.getElementsByTag("body").first();
            //去自动换行
            document.outputSettings().prettyPrint(false);
            String html = body.html();
            //查属性对应的编码，更新NewProperty这个字段
            String newProperty = lgdbBasicQuestionclassifynameMapper.selectNewproperty("音标");
            //识别和插入到原始库
            query.AutoEditC("C", Stage, html, "N", 21, TaskId, item.getKnowledgename(), item.getSentence() , newProperty);
            lgdbCKlgOrgresourceMapper.LgdbCKlgOrgresourceUpdate(Stage, "N214", item.getId());
        }
        ArrayList<String> strings = new ArrayList<>();
        if (Ask != null && Answer != null) {
            strings.add("题干：" + Ask);
            strings.add("【参考答案】: " + Answer);
        }
        return strings;
    }


    //词汇填空题，单词提示　B阶段   句子填空,N,21,单词提示,英语单词
    public ArrayList<String> quesTypeN215(LgdbBKlgOrgresource item, String Stage, String TaskId, MainService query) throws Exception {
        String sbXmlTemplet = ContentDao.TYPE_N;
        //解析html,保留大小写
        Parser parser = Parser.htmlParser();
        parser.settings(new ParseSettings(true, true));
        Document document = parser.parseInput(sbXmlTemplet, "utf-8");
        String Ask = null;
        String Answer = null;

        String emptyWord = helperService.pickNoOriginalTargetWord(item.getSentence(), item.getKnowledgename());
        if (helperService.pickTargetWordNoOriginalOneword(item.getSentence(), item.getKnowledgename(), "") == 1 && emptyWord!=null) {
            // String emptyWord = helperService.pickNoOriginalTargetWord(item.getSentence(), item.getKnowledgename());
            String ask = null;
            // 造出题干
            //去标志符号
            // String AAA = "Get out of my way, <strong><u>buddy</u></strong>!";
            String AAA = item.getSentence().replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");
            String ask1 = helperService.getTextFromTHML(AAA).replace(emptyWord, "___"+"(" + item.getKnowledgename() + ")");
            ask = ask1.replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");


            // ask = helperService.getTextFromTHML(item.getSentence()).replace(emptyWord, "___"+"(" + item.getKnowledgename() + ")");
            // ask = item.getSentence().replace("<div>", "").replace("</div>", "").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong><u>", "").replace("</u></strong>", "").replace("<u>", "").replace("<strong>", "").replace("</u>", "").replace("</strong>", "").replace(emptyWord, "____" + unPhonetic  );

            //有对话的情况要分开
            if (ask.contains("Ж")) {
                String[] жs = ask.split("Ж");
                for (int i = 0; i < жs.length; i++) {
                    document.getElementsByTag("QuesOptionAsk").first().appendText("<P class=tq-p>" + жs[i] + "</P>" + " ");
                    Ask += жs[i]+"|";
                }
            } else {
                document.getElementsByTag("QuesOptionAsk").first().text("<P class=tq-p>" + ask.trim() + "</P>");
                Ask = ask.trim();
            }


            Answer = emptyWord;
            document.getElementsByTag("QuesAnswer").first().text(emptyWord);
            org.jsoup.nodes.Element body = document.getElementsByTag("body").first();
            //去自动换行
            document.outputSettings().prettyPrint(false);
            String html = body.html();
            //查属性对应的编码，更新NewProperty这个字段
            String newProperty = "";
            String newProperty1 = lgdbBasicQuestionclassifynameMapper.selectNewproperty("单词");
            ArrayList<String> strings = lgdbBasicKnowledgeandcodeMapper.selectThemeByKnowledge(item.getKnowledgename());
            if(strings.size()==1){
                //当目标知识点是情态动词及助动词或者实意动词及系动词的时候，需要再标上动词属性
                if(strings.get(0).equals("情态动词及助动词") || strings.get(0).equals("实意动词及系动词")){
                    String s1 = lgdbBasicQuestionclassifynameMapper.selectNewproperty("动词属性");
                    newProperty = s1+"|"+newProperty1;
                }else {
                    newProperty = newProperty1;
                }
            }else {
                newProperty = newProperty1;
            }

//            String s = lgdbBasicKnowledgeandcodeMapper.selectThemeByKnowledeCode(item.getKnowledgecode());
//            if(s!=null && !(s.length()==0)){
//                if(s.equals("情态动词及助动词") || s.equals("实意动词及系动词")){
//                    String s1 = lgdbBasicQuestionclassifynameMapper.selectNewproperty("动词属性");
//                    newProperty = s1+"|"+newProperty1;
//                }else {
//                    newProperty = newProperty1;
//                }
//            }else {
//                newProperty = newProperty1;
//            }

            //识别和插入到原始库
            query.AutoEdit("C", Stage, html, "N", 21, TaskId, item.getKnowledgename(), item.getSentence() , newProperty);
            lgdbBKlgOrgresourceMapper.LgdbBKlgOrgresourceUpdate(Stage, "N215", item.getId());
        }
        ArrayList<String> strings = new ArrayList<>();
        if (Ask != null && Answer != null) {
            String[] split = Ask.split("\\|");
            for (int i=0;i<split.length;i++) {
                if(i==0){
                    strings.add("题干：" + split[i]);
                }else {
                    strings.add( "   "+split[i]);
                }
            }
            strings.add("【参考答案】: " + Answer);
        }
        return strings;
    }

    //词汇填空题，单词提示 C阶段   句子填空,N,21,单词提示,英语单词
    public ArrayList<String> quesTypeN215C(LgdbCKlgOrgresource item, String Stage, String TaskId, MainService query) throws Exception {
        String sbXmlTemplet = ContentDao.TYPE_N;
        //解析html,保留大小写
        Parser parser = Parser.htmlParser();
        parser.settings(new ParseSettings(true, true));
        Document document = parser.parseInput(sbXmlTemplet, "utf-8");
        String Ask = null;
        String Answer = null;
        if (helperService.pickTargetWordNoOriginalOneword(item.getSentence(), item.getKnowledgename(), "") == 1 && helperService.pickNoOriginalTargetWord(item.getSentence(), item.getKnowledgename())!=null) {
            String emptyWord = helperService.pickNoOriginalTargetWord(item.getSentence(), item.getKnowledgename());
            String ask = null;
//            "Come on <strong><u>boys</u></strong>!" He shouted to the sailors.

            /******************
             *
             */
//            emptyWord = "boys";
//           String SS =  "\"Come on <strong><u>boys</u></strong>!\" He shouted to the sailors.";
//            String AAA1 = SS.replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");
//            String ask11 = helperService.getTextFromTHML(AAA1).replace(emptyWord, "___"+"(" + item.getKnowledgename() + ")");
//            String ask111 = ask11.replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");

            /***
             *
             *
             */
            // 造出题干
            //去标志符号
//            String AAA = "Get out of my way, <strong><u>buddy</u></strong>!";
            String AAA = item.getSentence().replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");
            String ask1 = helperService.getTextFromTHML(AAA).replace(emptyWord, "___"+"(" + item.getKnowledgename() + ")");
            ask = ask1.replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");


//            ask = helperService.getTextFromTHML(item.getSentence()).replace(emptyWord, "___"+"(" + item.getKnowledgename() + ")");
//            ask = item.getSentence().replace("<div>", "").replace("</div>", "").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong><u>", "").replace("</u></strong>", "").replace("<u>", "").replace("<strong>", "").replace("</u>", "").replace("</strong>", "").replace(emptyWord, "____" + unPhonetic  );

            //有对话的情况要分开
            if (ask.contains("Ж")) {
                String[] жs = ask.split("Ж");
                for (int i = 0; i < жs.length; i++) {
                    document.getElementsByTag("QuesOptionAsk").first().appendText("<P class=tq-p>" + жs[i] + "</P>" + " ");
                    Ask += жs[i]+"|";
                }
            } else {
                document.getElementsByTag("QuesOptionAsk").first().text("<P class=tq-p>" + ask.trim() + "</P>");
                Ask = ask.trim();
            }


            Answer = emptyWord;
            document.getElementsByTag("QuesAnswer").first().text(emptyWord);
            org.jsoup.nodes.Element body = document.getElementsByTag("body").first();
            //去自动换行
            document.outputSettings().prettyPrint(false);
            String html = body.html();
            //查属性对应的编码，更新NewProperty这个字段
            String newProperty = "";
            String newProperty1 = lgdbBasicQuestionclassifynameMapper.selectNewproperty("单词");
            ArrayList<String> strings = lgdbBasicKnowledgeandcodeMapper.selectThemeByKnowledge(item.getKnowledgename());
            if(strings.size()==1){
                //当目标知识点是情态动词及助动词或者实意动词及系动词的时候，需要再标上动词属性
                if(strings.get(0).equals("情态动词及助动词") || strings.get(0).equals("实意动词及系动词")){
                    String s1 = lgdbBasicQuestionclassifynameMapper.selectNewproperty("动词属性");
                    newProperty = s1+"|"+newProperty1;
                }else {
                    newProperty = newProperty1;
                }
            }else {
                newProperty = newProperty1;
            }

//            String s = lgdbBasicKnowledgeandcodeMapper.selectThemeByKnowledeCode(item.getKnowledgecode());
//            if(s!=null && !(s.length()==0)){
//                if(s.equals("情态动词及助动词") || s.equals("实意动词及系动词")){
//                    String s1 = lgdbBasicQuestionclassifynameMapper.selectNewproperty("动词属性");
//                    newProperty = s1+"|"+newProperty1;
//                }else {
//                    newProperty = newProperty1;
//                }
//            }else {
//                newProperty = newProperty1;
//            }

            //识别和插入到原始库
            query.AutoEditC("C", Stage, html, "N", 21, TaskId, item.getKnowledgename(), item.getSentence() , newProperty);
            lgdbCKlgOrgresourceMapper.LgdbCKlgOrgresourceUpdate(Stage, "N215", item.getId());
        }
        ArrayList<String> strings = new ArrayList<>();
        if (Ask != null && Answer != null) {
            String[] split = Ask.split("\\|");
            for (int i=0;i<split.length;i++) {
                if(i==0){
                    strings.add("题干：" + split[i]);
                }else {
                    strings.add( "   "+split[i]);
                }
            }
            strings.add("【参考答案】: " + Answer);
        }
        return strings;
    }



    //词汇填空题，首字母+汉语 B阶段    句子填空,N,21,首字母+汉语,英语单词
    public ArrayList<String> quesTypeN216(LgdbBKlgOrgresource item, String Stage, String TaskId, MainService query) throws Exception {
        String sbXmlTemplet = ContentDao.TYPE_N;
        //解析html,保留大小写
        Parser parser = Parser.htmlParser();
        parser.settings(new ParseSettings(true, true));
        Document document = parser.parseInput(sbXmlTemplet, "utf-8");
        String Ask = null;
        String Answer = null;
        if (helperService.pickTargetWordOneword(item.getSentence(), item.getKnowledgename(), "") == 1 && helperService.pickUpTargetWord(item) !=null ) {
            String emptyWord = helperService.pickUpTargetWord(item);
            //首字母
            String[] ss = emptyWord.split("");
            String  s2 =   "(" + ss[0] + "-" + ")";
            //汉语
            String maincontext = "";
            String maincontext1 = item.getMaincontext();
            if (!(item.getMaincontext() == null)) {
                String s = item.getMaincontext().replaceAll("\\[(.*)]", "").replaceAll("（(.*)）","").replaceAll("\\((.*)\\)","");
//                maincontext = s.replace("modal", "情态动词").replace("vi.&amp;vt.", "").replace("vt.&amp;vi.", "").replace("vt.&amp;", "").replace("vi.&amp;", "").replace("vt.", "").replace("vi.", "").replace("&amp;", "").split(";")[0];

                String maincontext2 = s.replace("modal", "情态动词").replace("vi.&amp;vt.", "").replace("vt.&amp;vi.", "").replace("vt.&amp;", "").replace("vi.&amp;", "").replace("vt.", "").replace("vi.", "").replace("&amp;", "").replace("&lt;英&gt;", "").replace("&lt;美&gt;", "").replace("&lt;", "").replace("&gt;", "").replace("&lt;美", "").replace("美&gt;", "").split(";")[0];
                if(maincontext2.contains("，")){
                    maincontext = maincontext2.split("，")[0];
                }else if(maincontext2.contains(",")){
                    maincontext = maincontext2.split(",")[0];
                }else {
                    maincontext = maincontext2;
                }
            }

//            String AA = "<em>War and Peace </em>is a literary <em>literary </em>  <strong><u>classic</u></strong>.";
//           //$匹配行的结尾
//            String pattern = "(\\B{1}<em>)(.*)(</em>\\B{1})";
//            // 创建 Pattern 对象
//            Pattern r = Pattern.compile(pattern);
//            // 现在创建 matcher 对象
//            Matcher m = r.matcher(AA);
//            if(m.find()){
//                String group = m.group(0);
//                String group1 = m.group(1);
//            }

//           String replace = item.getSentence().replace("<", "&lt;").replace(">", "&gt;");
            // 造出题干，去标志符号
//            ask = item.getSentence().replace("<div>", "").replace("</div>", "").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong><u>", "").replace("</u></strong>", "").replace("<u>", "").replace("<strong>", "").replace("</u>", "").replace("</strong>", "").replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;").replace(emptyWord, s2+"___"+"("+maincontext+")");
            String ask = null;
            String AAA = item.getSentence().replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");
            String ask1 = helperService.getTextFromTHML(AAA).replace(emptyWord, s2+"___"+"("+maincontext+")");
            ask = ask1.replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");
            //有对话的情况要分开
            if (ask.contains("Ж")) {
                String[] жs = ask.split("Ж");
                for (int i = 0; i < жs.length; i++) {
                    document.getElementsByTag("QuesOptionAsk").first().appendText("<P class=tq-p>" + жs[i] + "</P>" + " ").append("\\n" + " ");
                }
            } else {
                document.getElementsByTag("QuesOptionAsk").first().text("<P class=tq-p>" + ask.trim() + "</P>");
            }
            Ask = ask.trim();
            Answer = emptyWord;
            document.getElementsByTag("QuesAnswer").first().text(emptyWord);
            org.jsoup.nodes.Element body = document.getElementsByTag("body").first();
            //去自动换行
            document.outputSettings().prettyPrint(false);
            String html = body.html();
            //查属性对应的编码，更新NewProperty这个字段
            String newProperty = "";
             newProperty = lgdbBasicQuestionclassifynameMapper.selectNewproperty("首字母+汉语");

            //识别和插入到原始库
            query.AutoEdit("C", Stage, html, "N", 21, TaskId, item.getKnowledgename(), item.getSentence() , newProperty);
            lgdbBKlgOrgresourceMapper.LgdbBKlgOrgresourceUpdate(Stage, "N216", item.getId());
        }
        ArrayList<String> strings = new ArrayList<>();
        if (Ask != null && Answer != null) {
            strings.add("题干：" + Ask);
            strings.add("【参考答案】: " + Answer);
        }
        return strings;
    }

    //词汇填空题，首字母+汉语 C阶段   句子填空,N,21,首字母+汉语,英语单词
    public ArrayList<String> quesTypeN216C(LgdbCKlgOrgresource item, String Stage, String TaskId, MainService query) throws Exception {
        String sbXmlTemplet = ContentDao.TYPE_N;
        //解析html,保留大小写
        Parser parser = Parser.htmlParser();
        parser.settings(new ParseSettings(true, true));
        Document document = parser.parseInput(sbXmlTemplet, "utf-8");
        String Ask = null;
        String Answer = null;
        if (helperService.pickTargetWordOneword(item.getSentence(), item.getKnowledgename(), "") == 1 && helperService.pickUpTargetWordC(item) !=null ) {
            String emptyWord = helperService.pickUpTargetWordC(item);
            //首字母
            String[] ss = emptyWord.split("");
            String  s2 =   "(" + ss[0] + "-" + ")";
            //汉语
            String maincontext = "";
            String maincontext1 = item.getMaincontext();
            if (!(item.getMaincontext() == null)) {
                String s = item.getMaincontext().replaceAll("\\[(.*)]", "").replaceAll("（(.*)）","").replaceAll("\\((.*)\\)","");
//                maincontext = s.replace("modal", "情态动词").replace("vi.&amp;vt.", "").replace("vt.&amp;vi.", "").replace("vt.&amp;", "").replace("vi.&amp;", "").replace("vt.", "").replace("vi.", "").replace("&amp;", "").split(";")[0];

                String maincontext2 = s.replace("modal", "情态动词").replace("vi.&amp;vt.", "").replace("vt.&amp;vi.", "").replace("vt.&amp;", "").replace("vi.&amp;", "").replace("vt.", "").replace("vi.", "").replace("&amp;", "").replace("&lt;英&gt;", "").replace("&lt;美&gt;", "").replace("&lt;", "").replace("&gt;", "").replace("&lt;美", "").replace("美&gt;", "").split(";")[0];
                if(maincontext2.contains("，")){
                    maincontext = maincontext2.split("，")[0];
                }else if(maincontext2.contains(",")){
                    maincontext = maincontext2.split(",")[0];
                }else {
                    maincontext = maincontext2;
                }
            }

//            String AA = "<em>War and Peace </em>is a literary <em>literary </em>  <strong><u>classic</u></strong>.";
//           //$匹配行的结尾
//            String pattern = "(\\B{1}<em>)(.*)(</em>\\B{1})";
//            // 创建 Pattern 对象
//            Pattern r = Pattern.compile(pattern);
//            // 现在创建 matcher 对象
//            Matcher m = r.matcher(AA);
//            if(m.find()){
//                String group = m.group(0);
//                String group1 = m.group(1);
//            }

//           String replace = item.getSentence().replace("<", "&lt;").replace(">", "&gt;");
            // 造出题干，去标志符号
//            ask = item.getSentence().replace("<div>", "").replace("</div>", "").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong><u>", "").replace("</u></strong>", "").replace("<u>", "").replace("<strong>", "").replace("</u>", "").replace("</strong>", "").replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;").replace(emptyWord, s2+"___"+"("+maincontext+")");
            String ask = null;
            String AAA = item.getSentence().replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");
            String ask1 = helperService.getTextFromTHML(AAA).replace(emptyWord, s2+"___"+"("+maincontext+")");
            ask = ask1.replace("<em>", "&lt;em&gt;").replace("</em>", "&lt;/em&gt;");
            //有对话的情况要分开
            if (ask.contains("Ж")) {
                String[] жs = ask.split("Ж");
                for (int i = 0; i < жs.length; i++) {
                    document.getElementsByTag("QuesOptionAsk").first().appendText("<P class=tq-p>" + жs[i] + "</P>" + " ").append("\\n" + " ");
                }
            } else {
                document.getElementsByTag("QuesOptionAsk").first().text("<P class=tq-p>" + ask.trim() + "</P>");
            }
            Ask = ask.trim();
            Answer = emptyWord;
            document.getElementsByTag("QuesAnswer").first().text(emptyWord);
            org.jsoup.nodes.Element body = document.getElementsByTag("body").first();
            //去自动换行
            document.outputSettings().prettyPrint(false);
            String html = body.html();
            //查属性对应的编码，更新NewProperty这个字段
            String newProperty = "";
            newProperty = lgdbBasicQuestionclassifynameMapper.selectNewproperty("首字母+汉语");

            //识别和插入到原始库
            query.AutoEditC("C", Stage, html, "N", 21, TaskId, item.getKnowledgename(), item.getSentence() , newProperty);
            lgdbCKlgOrgresourceMapper.LgdbCKlgOrgresourceUpdate(Stage, "N216", item.getId());
        }
        ArrayList<String> strings = new ArrayList<>();
        if (Ask != null && Answer != null) {
            strings.add("题干：" + Ask);
            strings.add("【参考答案】: " + Answer);
        }
        return strings;
    }

    //翻译填空题，B阶段   翻译填空,N,22,无,无
    public ArrayList<String> quesTypeN22(LgdbBKlgOrgresource item, String Stage, String TaskId, MainService query) throws Exception {
        String sbXmlTemplet = ContentDao.TYPE_N;
        //解析html,保留大小写
        Parser parser = Parser.htmlParser();
        parser.settings(new ParseSettings(true, true));
        Document document = parser.parseInput(sbXmlTemplet, "utf-8");
        String Translate1 = null;
        String Ask = null;
        String Answer = null;
//        String getTypeCode = helperService.getQuesTypeService(item.getId(), Stage);
//        String word = "";
//        if (item.getKnowledgename().contains(" do sth.")) {
//            word = item.getKnowledgename().replace(" do sth.", "");
//        } else {
//            word = item.getKnowledgename();
//        }
        String knowledgename = item.getKnowledgename();


        if (helperService.pickTargetWordOneword(item.getSentence(), item.getKnowledgename(), "") == 1  && helperService.pickUpTargetWord(item)!=null ) {
            String emptyWord = helperService.pickUpTargetWord(item);
            int length = emptyWord.split(" ").length;
            String str = "";
            for(int i = 0; i<length;i++){
                if(i+1==length){
                    str+="___";
                }else {
                    str+="___"+"  ";
                }

            }
//            emptyWord = word;
            String translate = item.getTranslate();
            String ask = null;
            // 造出题干
            //去标志符号
            ask = item.getSentence().replace("<div>", "").replace("</div>", "").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong><u>", "").replace("</u></strong>", "").replace("<u>", "").replace("<strong>", "").replace("</u>", "").replace("</strong>", "").replace("<", "&lt;").replace(">", "&gt;").replace(emptyWord, str);

            if (!ask.contains("Ж") && !translate.contains("Ж")) {
                document.getElementsByTag("QuesOptionAsk").first().text("<P class=tq-p>" + translate + "</P>" + "<P class=tq-p>" + ask.trim() + "</P>");
            } else if (ask.contains("Ж") && !translate.contains("Ж")) {
                document.getElementsByTag("QuesOptionAsk").first().appendText("<P class=tq-p>" + translate + "</P>" + " ");
                String[] жs = ask.split("Ж");
                for (int i = 0; i < жs.length; i++) {
                    document.getElementsByTag("QuesOptionAsk").first().appendText("<P class=tq-p>" + жs[i] + "</P>" + " ");
                }
            } else if (!ask.contains("Ж") && translate.contains("Ж")) {
                String[] жs = translate.split("Ж");
                for (int i = 0; i < жs.length; i++) {
                    document.getElementsByTag("QuesOptionAsk").first().appendText("<P class=tq-p>" + жs[i] + "</P>" + " ");
                }
                document.getElementsByTag("QuesOptionAsk").first().appendText("<P class=tq-p>" + ask.trim() + "</P>" + " ");
            } else if (ask.contains("Ж") && translate.contains("Ж")) {
                String[] жs = translate.split("Ж");
                for (int i = 0; i < жs.length; i++) {
                    document.getElementsByTag("QuesOptionAsk").first().appendText("<P class=tq-p>" + жs[i] + "</P>" + " ");
                }

                String[] жs1 = ask.split("Ж");
                for (int i = 0; i < жs1.length; i++) {
                    document.getElementsByTag("QuesOptionAsk").first().appendText("<P class=tq-p>" + жs1[i] + "</P>" + " ");
                }
            }

            Ask = ask;
            Translate1 = translate;
            String emptyWord1 = "";
            Answer = emptyWord;
            int length1 = emptyWord.split(" ").length;
            for (int i=0;i<length1;i++){
                if(i+1==length1){
                    emptyWord1+=emptyWord.split(" ")[i];
                }else {
                    emptyWord1+=emptyWord.split(" ")[i]+"$、";
                }
            }
            document.getElementsByTag("QuesAnswer").first().text(emptyWord1);
            org.jsoup.nodes.Element body = document.getElementsByTag("body").first();
            //去自动换行
            document.outputSettings().prettyPrint(false);
            String html = body.html();
            //查属性对应的编码，更新NewProperty这个字段
            String newProperty = lgdbBasicQuestionclassifynameMapper.selectNewproperty("句子语境");
            //识别和插入到原始库
            query.AutoEdit("C", Stage, html, "N", 22, TaskId, item.getKnowledgename(), item.getSentence() , newProperty);
            lgdbBKlgOrgresourceMapper.LgdbBKlgOrgresourceUpdate(Stage, "N222", item.getId());
        }
        ArrayList<String> strings = new ArrayList<>();
        if (Ask != null && Answer != null && Translate1 != null) {
            strings.add("【翻译】: " + Translate1);
            strings.add("【题干】：" + Ask);
            strings.add("【参考答案】: " + Answer);
        }
        return strings;
    }


    //翻译填空题，C阶段   翻译填空,N,22,无,无
    public ArrayList<String> quesTypeN22C(LgdbCKlgOrgresource item, String Stage, String TaskId, MainService query) throws Exception {
        String sbXmlTemplet = ContentDao.TYPE_N;
        //解析html,保留大小写
        Parser parser = Parser.htmlParser();
        parser.settings(new ParseSettings(true, true));
        Document document = parser.parseInput(sbXmlTemplet, "utf-8");
        String Translate1 = null;
        String Ask = null;
        String Answer = null;
//        String getTypeCode = helperService.getQuesTypeService(item.getId(), Stage);
//        String word = "";
//        if (item.getKnowledgename().contains(" do sth.")) {
//            word = item.getKnowledgename().replace(" do sth.", "");
//        } else {
//            word = item.getKnowledgename();
//        }
        String knowledgename = item.getKnowledgename();


        if (helperService.pickTargetWordOneword(item.getSentence(), item.getKnowledgename(), "") == 1  && helperService.pickUpTargetWordC(item)!=null ) {
            String emptyWord = helperService.pickUpTargetWordC(item);
            int length = emptyWord.split(" ").length;
            String str = "";
            for(int i = 0; i<length;i++){
                if(i+1==length){
                    str+="___";
                }else {
                    str+="___"+"  ";
                }

            }
//            emptyWord = word;
            String translate = item.getTranslate();
            String ask = null;
            // 造出题干
            //去标志符号
            ask = item.getSentence().replace("<div>", "").replace("</div>", "").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong><u>", "").replace("</u></strong>", "").replace("<u>", "").replace("<strong>", "").replace("</u>", "").replace("</strong>", "").replace("<", "&lt;").replace(">", "&gt;").replace(emptyWord, str);

            if (!ask.contains("Ж") && !translate.contains("Ж")) {
                document.getElementsByTag("QuesOptionAsk").first().text("<P class=tq-p>" + translate + "</P>" + "<P class=tq-p>" + ask.trim() + "</P>");
            } else if (ask.contains("Ж") && !translate.contains("Ж")) {
                document.getElementsByTag("QuesOptionAsk").first().appendText("<P class=tq-p>" + translate + "</P>" + " ");
                String[] жs = ask.split("Ж");
                for (int i = 0; i < жs.length; i++) {
                    document.getElementsByTag("QuesOptionAsk").first().appendText("<P class=tq-p>" + жs[i] + "</P>" + " ");
                }
            } else if (!ask.contains("Ж") && translate.contains("Ж")) {
                String[] жs = translate.split("Ж");
                for (int i = 0; i < жs.length; i++) {
                    document.getElementsByTag("QuesOptionAsk").first().appendText("<P class=tq-p>" + жs[i] + "</P>" + " ");
                }
                document.getElementsByTag("QuesOptionAsk").first().appendText("<P class=tq-p>" + ask.trim() + "</P>" + " ");
            } else if (ask.contains("Ж") && translate.contains("Ж")) {
                String[] жs = translate.split("Ж");
                for (int i = 0; i < жs.length; i++) {
                    document.getElementsByTag("QuesOptionAsk").first().appendText("<P class=tq-p>" + жs[i] + "</P>" + " ");
                }

                String[] жs1 = ask.split("Ж");
                for (int i = 0; i < жs1.length; i++) {
                    document.getElementsByTag("QuesOptionAsk").first().appendText("<P class=tq-p>" + жs1[i] + "</P>" + " ");
                }
            }

            Ask = ask;
            Translate1 = translate;
            String emptyWord1 = "";
            Answer = emptyWord;
            int length1 = emptyWord.split(" ").length;
            for (int i=0;i<length1;i++){
                if(i+1==length1){
                    emptyWord1+=emptyWord.split(" ")[i];
                }else {
                    emptyWord1+=emptyWord.split(" ")[i]+"$、";
                }
            }
            document.getElementsByTag("QuesAnswer").first().text(emptyWord1);
            org.jsoup.nodes.Element body = document.getElementsByTag("body").first();
            //去自动换行
            document.outputSettings().prettyPrint(false);
            String html = body.html();
            //查属性对应的编码，更新NewProperty这个字段
            String newProperty = lgdbBasicQuestionclassifynameMapper.selectNewproperty("句子语境");
            //识别和插入到原始库
            query.AutoEditC("C", Stage, html, "N", 22, TaskId, item.getKnowledgename(), item.getSentence() , newProperty);
            lgdbCKlgOrgresourceMapper.LgdbCKlgOrgresourceUpdate(Stage, "N222", item.getId());
        }
        ArrayList<String> strings = new ArrayList<>();
        if (Ask != null && Answer != null && Translate1 != null) {
            strings.add("【翻译】: " + Translate1);
            strings.add("【题干】：" + Ask);
            strings.add("【参考答案】: " + Answer);
        }
        return strings;
    }


    //选词填空,N,20,复合属性,英语单词
    public ArrayList<String> quesTypeN201(ArrayList<LgdbBKlgOrgresource> listmN201, String Stage, String TaskId, MainService query) throws Exception {

        int count = 0;
        String Ask = "";
        String Answer = "";
        String Optionss = "";

        String strQuesbody = "";
        String knownNameList1 = "";
        String sentenceList = "";


        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<HashMap<String, String>> listSort = new ArrayList<>();


        if (listmN201.size() == 6 ) {
            for (LgdbBKlgOrgresource m : listmN201) {
                strQuesbody += m.getKnowledgename() + ";";
                if (m.getKnowledgename() != null) {
                    Optionss += "   " + m.getKnowledgename() + "   ";
                }
                String awnser = helperService.pickUpTargetWord(m);
                String str = "___";
                String sentenceUseEmpty = m.getSentence().replace("<div>", "").replace("</div>", "").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong><u>", "").replace("</u></strong>", "").replace("<u>", "").replace("<strong>", "").replace("</u>", "").replace("</strong>", "").replace("<", "&lt;").replace(">", "&gt;").replace(awnser, str);
                HashMap<String, String> stringStringHashMap = new HashMap<>();
                stringStringHashMap.put(sentenceUseEmpty, awnser);
                listSort.add(stringStringHashMap);
                lgdbBKlgOrgresourceMapper.LgdbBKlgOrgresourceUpdate(Stage, "N201", m.getId());
                knownNameList1 += m.getKnowledgename() + "|";
                sentenceList += m.getSentence() + "|";
                ++count;
            }
        }
        if (!listSort.isEmpty()) {
            //region TContent
            stringBuilder.append("<TContent>");
            stringBuilder.append("<Quesbody>&lt;DIV&gt;&lt;TABLE class=Ques-table&gt;&lt;TBODY&gt;&lt;TR&gt;&lt;TD class=Ques-td&gt;" + helperService.stringArrayRandom(strQuesbody) + "&lt;/TD&gt;&lt;/TR&gt;&lt;/TBODY&gt;&lt;/TABLE&gt;&lt;/DIV&gt;</Quesbody>");
            stringBuilder.append(" <QuesArticle audio=\"\" orgname=\"\" />");
            listSort = helperService.randomSortList(listSort);

            for (HashMap<String, String> m1 : listSort) {
                if (count == 1) {
                    break;
                }
                for (Map.Entry<String, String> n : m1.entrySet()) {
                    String key = n.getKey();
                    String value = n.getValue();
                    if (key != null && value != null) {
                        Ask += key + "|";
                        Answer += value + "    ";
                    }
                    String key1 = "";
                    if (key.contains("Ж")) {
                        key1 = key.replace("Ж", " ");
                    } else {
                        key1 = key;
                    }
                    stringBuilder.append("<QuesChild index=\"1\" answertype=\"2\">");
                    stringBuilder.append("<QueStem /><QuesScore>0</QuesScore>");
                    stringBuilder.append("<QuesOptionAsk audio=\"\" orgname=\"\">" + key1 + "</QuesOptionAsk>");
                    stringBuilder.append(" <QuesAnswer>" + helperService.fileterMark(value) + "</QuesAnswer><QuesAnalyze/> ");
                    stringBuilder.append("</QuesChild>");
                }
                --count;
            }
            stringBuilder.append("</TContent>");
            //查属性对应的编码，更新NewProperty这个字段
            String newProperty1 = lgdbBasicQuestionclassifynameMapper.selectNewproperty("复合");
            String newProperty2 = lgdbBasicQuestionclassifynameMapper.selectNewproperty("句子语境");
            String newProperty = newProperty2 +"|"+newProperty1;
            query.AutoEdit("C", Stage, stringBuilder.toString(), "N", 20, TaskId, knownNameList1, sentenceList, newProperty);//识别和插入到原始库
        }
        ArrayList<String> strings = new ArrayList<>();
        if (!Ask.equals("") && !Answer.equals("") && !Optionss.equals("")) {
            strings.add(Optionss);
            String[] split = Ask.split("\\|");
            for (String m : split) {
                strings.add("题干: " + m);
            }
            strings.add("答案: " + Answer);
        }
        return strings;
    }


    //选词填空,N,202,动词属性,英语单词
    public ArrayList<String> quesTypeN202(ArrayList<LgdbBKlgOrgresource> listmN, String Stage, String TaskId, MainService query) throws Exception {
        int index = 0;
        int count = 0;
        String Ask = "";
        String Answer = "";
        String Optionss = "";

        String strQuesbody = "";
        String knownNameList = "";
        String sentenceList = "";

        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<HashMap<String, String>> listSort = new ArrayList<>();
        for (LgdbBKlgOrgresource m : listmN) {
            if (index > 5) {
                break;
            }
            if (!knownNameList.contains(m.getKnowledgename())) {

                strQuesbody += m.getKnowledgename() + ";";
                if (m.getKnowledgename() != null) {
                    Optionss += "   " + m.getKnowledgename() + "   ";
                }
                String awnser = helperService.pickUpTargetWord(m);
                String str = "___";
//                for (int i = 0; i < awnser.split(" ").length; i++) {
//                    if (awnser.contains("?")) {
//                        if ((i + 1) == awnser.split(" ").length) {
//                            str += ("____" + "____");
//                        } else {
//                            str += ("____" + " ");
//                        }
//                    } else {
//                        if ((i + 1) == awnser.split(" ").length) {
//                            str += ("____");
//                        } else {
//                            str += ("____" + " ");
//                        }
//                    }
//                }
                String sentenceUseEmpty = m.getSentence().replace("<div>", "").replace("</div>", "").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong><u>", "").replace("</u></strong>", "").replace("<u>", "").replace("<strong>", "").replace("</u>", "").replace("</strong>", "").replace("<", "&lt;").replace(">", "&gt;").replace(awnser, str);
                HashMap<String, String> stringStringHashMap = new HashMap<>();
                stringStringHashMap.put(sentenceUseEmpty, awnser);
                listSort.add(stringStringHashMap);

                lgdbBKlgOrgresourceMapper.LgdbBKlgOrgresourceUpdate(Stage, "N202", m.getId());

                knownNameList += m.getKnowledgename() + "|";
                sentenceList += m.getSentence() + "|";
                ++count;
                ++index;
            }

        }
        if (!listSort.isEmpty()) {
            stringBuilder.append("<TContent>");
            stringBuilder.append("<Quesbody>&lt;DIV&gt;&lt;TABLE class=Ques-table&gt;&lt;TBODY&gt;&lt;TR&gt;&lt;TD class=Ques-td&gt;" + helperService.stringArrayRandom(strQuesbody) + "&lt;/TD&gt;&lt;/TR&gt;&lt;/TBODY&gt;&lt;/TABLE&gt;&lt;/DIV&gt;</Quesbody>");
            stringBuilder.append(" <QuesArticle audio=\"\" orgname=\"\" />");
            listSort = helperService.randomSortList(listSort);

            for (HashMap<String, String> m : listSort) {
                if (count == 1) {
                    break;
                }
                for (Map.Entry<String, String> n : m.entrySet()) {
                    String key = n.getKey();
                    String value = n.getValue();
                    if (key != null && value != null) {
                        Ask += key + "|";
                        Answer += value + "    ";
                    }
                    String key1 = "";
                    if (key.contains("Ж")) {
                        key1 = key.replace("Ж", " ");
                    } else {
                        key1 = key;
                    }

                    stringBuilder.append("<QuesChild index=\"1\" answertype=\"2\">");
                    stringBuilder.append("<QueStem /><QuesScore>0</QuesScore>");
                    stringBuilder.append("<QuesOptionAsk audio=\"\" orgname=\"\">" + key1 + "</QuesOptionAsk>");
                    stringBuilder.append(" <QuesAnswer>" + helperService.fileterMark(value) + "</QuesAnswer><QuesAnalyze/> ");
                    stringBuilder.append("</QuesChild>");
                }
                --count;
            }
            stringBuilder.append("</TContent>");
            //查属性对应的编码，更新NewProperty这个字段

            String newProperty1 = lgdbBasicQuestionclassifynameMapper.selectNewproperty("动词");
            String newProperty2 = lgdbBasicQuestionclassifynameMapper.selectNewproperty("句子语境");
            String newProperty = newProperty2 +"|"+newProperty1;
            query.AutoEdit("C", Stage, stringBuilder.toString(), "N", 20, TaskId, knownNameList, sentenceList, newProperty);//识别和插入到原始库
        }
        ArrayList<String> strings = new ArrayList<>();
        if (!Ask.equals("") && !Answer.equals("") && !Optionss.equals("")) {
            strings.add(Optionss);
            String[] split = Ask.split("\\|");
            for (String m : split) {
                strings.add("题干: " + m);
            }
            strings.add("答案: " + Answer);
        }
        return strings;
    }


    //  选词填空,N,20,动词复合属性,英语单词
    public ArrayList<String> quesTypeN203(ArrayList<LgdbBKlgOrgresource> listmIn, ArrayList<LgdbBKlgOrgresource> listmNin, String Stage, String TaskId, MainService query) throws Exception {
        int index = 0;
        int count = 0;
        String Ask = "";
        String Answer = "";
        String Optionss = "";

        String strQuesbody = "";
        String knownNameList = "";
        String knownNameList1 = "";
        String sentenceList = "";
        String themeLIst = "";

        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<HashMap<String, String>> listSort = new ArrayList<>();

        ArrayList<LgdbBKlgOrgresource> lgdbBKlgOrgresources = new ArrayList<>();

        LgdbBKlgOrgresource In = listmIn.get(0);
        lgdbBKlgOrgresources.add(In);

        for (LgdbBKlgOrgresource m : listmNin) {
            if (!knownNameList.contains(m.getKnowledgename())) {
                knownNameList += m.getKnowledgename() + "|";
                //同一道题内各素材之间的长度差距不超过5个单词
                int mlength = m.getSentence().replace("&lt;", "<").replace("&gt;", ">").replace("< strong >", "<strong>").replace("</ strong >", "</strong>").replace("< u >", "<u>").replace("</ u >", "</u>").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong>", "").replace("<u>", "").replace("</u>", "").replace("</strong>", "").split(" ").length;
                int countnm = helperService.interLengthLessFive(mlength, lgdbBKlgOrgresources);
                if (countnm == 0) {
                    lgdbBKlgOrgresources.add(m);
                    ++index;
                }
            }
            if (index > 4) {
                int size = lgdbBKlgOrgresources.size();
                break;
            }

        }
        if (lgdbBKlgOrgresources.size() == 6) {
            ArrayList<LgdbBKlgOrgresource> lgdbBKlgOrgresources1 = helperService.randomSortList1(lgdbBKlgOrgresources);
            for (LgdbBKlgOrgresource m : lgdbBKlgOrgresources1) {
                if(m.getKnowledgename().split(" ").length>1){
                    System.out.println(m.getKnowledgename());
                }
                String questype = m.getQuestype();

                if (!knownNameList1.contains(m.getKnowledgename())) {
                    strQuesbody += m.getKnowledgename() + ";";
                    if (m.getKnowledgename() != null) {
                        Optionss += "   " + m.getKnowledgename() + "   ";
                    }
                    String awnser = helperService.pickUpTargetWord(m);
                    if(awnser.split(" ").length>1){
                        System.out.println(awnser);
                    }
                    String str = "___";
                    String sentenceUseEmpty = m.getSentence().replace("<div>", "").replace("</div>", "").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong><u>", "").replace("</u></strong>", "").replace("<u>", "").replace("<strong>", "").replace("</u>", "").replace("</strong>", "").replace("<", "&lt;").replace(">", "&gt;").replace(awnser, str);
                    HashMap<String, String> stringStringHashMap = new HashMap<>();
                    stringStringHashMap.put(sentenceUseEmpty, awnser);
                    listSort.add(stringStringHashMap);
                    lgdbBKlgOrgresourceMapper.LgdbBKlgOrgresourceUpdate(Stage, "N203", m.getId());
                    knownNameList1 += m.getKnowledgename() + "|";
                    sentenceList += m.getSentence() + "|";
                    ++count;
                }
            }
        }
        if (!listSort.isEmpty()) {
            stringBuilder.append("<TContent>");
            stringBuilder.append("<Quesbody>&lt;DIV&gt;&lt;TABLE class=Ques-table&gt;&lt;TBODY&gt;&lt;TR&gt;&lt;TD class=Ques-td&gt;" + helperService.stringArrayRandom(strQuesbody) + "&lt;/TD&gt;&lt;/TR&gt;&lt;/TBODY&gt;&lt;/TABLE&gt;&lt;/DIV&gt;</Quesbody>");
            stringBuilder.append(" <QuesArticle audio=\"\" orgname=\"\" />");
            listSort = helperService.randomSortList(listSort);

            for (HashMap<String, String> m : listSort) {
                if (count == 1) {
                    break;
                }
                for (Map.Entry<String, String> n : m.entrySet()) {
                    String key = n.getKey();
                    String value = n.getValue();
                    if (key != null && value != null) {
                        Ask += key + "|";
                        Answer += value + "    ";
                    }

                    String key1 = "";
                    if (key.contains("Ж")) {
                        key1 = key.replace("Ж", " ");
                    } else {
                        key1 = key;
                    }
                    stringBuilder.append("<QuesChild index=\"1\" answertype=\"2\">");
                    stringBuilder.append("<QueStem /><QuesScore>0</QuesScore>");
                    stringBuilder.append("<QuesOptionAsk audio=\"\" orgname=\"\">" + key1 + "</QuesOptionAsk>");
                    stringBuilder.append(" <QuesAnswer>" + helperService.fileterMark(value) + "</QuesAnswer><QuesAnalyze/> ");
                    stringBuilder.append("</QuesChild>");
                }
                --count;
            }
            stringBuilder.append("</TContent>");
            String s = stringBuilder.toString();
            String replace = stringBuilder.toString().replace("''", "'");
            //查属性对应的编码，更新NewProperty这个字段
            String newProperty1 = lgdbBasicQuestionclassifynameMapper.selectNewproperty("动词+复合");
            String newProperty2 = lgdbBasicQuestionclassifynameMapper.selectNewproperty("句子语境");
            String newProperty = newProperty2 +"|"+newProperty1;
            query.AutoEdit("C", Stage, stringBuilder.toString().replace("''", "'"), "N", 20, TaskId, knownNameList1, sentenceList, newProperty);//识别和插入到原始库
        }

        ArrayList<String> strings = new ArrayList<>();
        if (!Ask.equals("") && !Answer.equals("") && !Optionss.equals("")) {
            strings.add(Optionss);
            String[] split = Ask.split("\\|");
            for (String m : split) {
                strings.add("题干: " + m);
            }
            strings.add("答案: " + Answer);
        }
        return strings;
    }

    //连词成句题   连词成句,V,7,无,英语单词英语词组   quesTypeV0
    public ArrayList<String> quesTypeV0(LgdbBKlgOrgresource item, String Stage, String TaskId, MainService query) throws Exception {
        String Ask = null;
        String Answer = null;
        String sbXmlTemplet = ContentDao.TYPE_V;
        //解析html,保留大小写
        Parser parser = Parser.htmlParser();
        parser.settings(new ParseSettings(true, true));
        Document document = parser.parseInput(sbXmlTemplet, "utf-8");
        //获取去掉标签的句子
        String sentence = item.getSentence();
        String textSentence = helperService.getTextFromTHML(sentence);


        String lastword = textSentence.substring(textSentence.length() - 1);
        String textSentence2 = textSentence.substring(0, textSentence.length() - 1);
        // 造出题干
        String ask = "";
        if (!textSentence2.contains("Ж")) {
            String[] s = textSentence2.split(" ");
            String askstr = "";
            // ➢ I/I’m/I’d/I’ve/I’ll不还原
            if (!(s[0].equals("I") || s[0].equals("I’m") || s[0].equals("I’d") || s[0].equals("I’ve") || s[0].equals("I’ll"))) {
                String a = s[0].toLowerCase();
                s[0] = a;
                //分割打乱作为题干
                //待测试
                String[] randomStringList = helperService.randomStringList(s);

                for (int i = 0; i < randomStringList.length; i++) {
                    if (i == (randomStringList.length - 1)) {
                        askstr += "__" + lastword;
                        ;
                    } else {
                        askstr += "__" + " ";
                        ;
                    }

                    if (i == (randomStringList.length - 1)) {
                        ask += randomStringList[i];
                    } else {
                        ask += randomStringList[i] + "," + "  ";
                    }
                }
            } else {
                String[] randomStringList = helperService.randomStringList(s);
                for (int i = 0; i < randomStringList.length; i++) {
                    if (i == (randomStringList.length - 1)) {
                        ask += randomStringList[i] + lastword;
                    } else {
                        ask += randomStringList[i] + "," + "  ";
                    }
                }
            }
            document.getElementsByTag("QuesOptionAsk").first().text("<P class=tq-p>" + ask.trim() + "</P>" + "<P class=tq-p>" + askstr.trim() + "</P>");

            document.getElementsByTag("QuesAnswer").first().text(textSentence2);

            org.jsoup.nodes.Element body = document.getElementsByTag("body").first();
            //去自动换行
            document.outputSettings().prettyPrint(false);
            String html = body.html();
            //查属性对应的编码，更新NewProperty这个字段
            String newProperty = lgdbBasicQuestionclassifynameMapper.selectNewproperty("连词成句");
            //识别和插入到原始库
            query.AutoEdit("C", Stage, html, "V", 7, TaskId, item.getKnowledgename(), item.getSentence() , newProperty);
            lgdbBKlgOrgresourceMapper.LgdbBKlgOrgresourceUpdate(Stage, "V0", item.getId());

        }
        Ask = ask;
        Answer = textSentence;
        ArrayList<String> strings = new ArrayList<>();
        if (Ask != null && Answer != null) {
            strings.add("【题干】：" + Ask);
            strings.add("【参考答案】: " + Answer);
        }
        return strings;
    }


    //英译中,g,38,句子无,英语单词英语词组
    public ArrayList<String> quesTypegV38(LgdbBKlgOrgresource item, String Stage, String TaskId, MainService query) throws Exception {
        String Ask = null;
        String Answer = null;
        String sbXmlTemplet = ContentDao.TYPE_V;
        //解析html,保留大小写
        Parser parser = Parser.htmlParser();
        parser.settings(new ParseSettings(true, true));
        Document document = parser.parseInput(sbXmlTemplet, "utf-8");
        //获取去掉标签的句子
        String sentence = item.getSentence();
        String translate = item.getTranslate();
        String textSentence = helperService.getTextFromTHML(sentence);
        // 造出题干
        String ask = "";
        if (!textSentence.contains("Ж") && translate != null) {
            ask = textSentence;

            document.getElementsByTag("QuesOptionAsk").first().text("<P class=tq-p>" + ask.trim() + "</P>");

            document.getElementsByTag("QuesAnswer").first().text(translate);

            org.jsoup.nodes.Element body = document.getElementsByTag("body").first();
            //去自动换行
            document.outputSettings().prettyPrint(false);
            String html = body.html();
            //查属性对应的编码，更新NewProperty这个字段
            String newProperty = lgdbBasicQuestionclassifynameMapper.selectNewproperty("英译中");
            //识别和插入到原始库
            query.AutoEdit("C", Stage, html, "g", 38, TaskId, item.getKnowledgename(), item.getSentence() , newProperty);
            lgdbBKlgOrgresourceMapper.LgdbBKlgOrgresourceUpdate(Stage, "g38", item.getId());

        }
        Ask = ask;
        Answer = translate;
        ArrayList<String> strings = new ArrayList<>();
        if (Ask != null && Answer != null) {
            strings.add("【题干】：" + Ask);
            strings.add("【参考答案】: " + Answer);
        }
        return strings;
    }


    //句子 中译英,g,40,无,英语单词英语词组
    public ArrayList<String> quesTypegV40(LgdbBKlgOrgresource item, String Stage, String TaskId, MainService query) throws Exception {
        String Ask = null;
        String Answer = null;
        String sbXmlTemplet = ContentDao.TYPE_V;
        //解析html,保留大小写
        Parser parser = Parser.htmlParser();
        parser.settings(new ParseSettings(true, true));
        Document document = parser.parseInput(sbXmlTemplet, "utf-8");
        //获取去掉标签的句子
        String sentence = item.getSentence();

        Optional<LgdbBKlgOrgresource> item1 = Optional.ofNullable(item);

        String translate1 = Optional.ofNullable(item).map(c -> c.getTranslate()).orElse("translate为空值");
        String translate = item.getTranslate();
        String textSentence = helperService.getTextFromTHML(sentence);
        // 造出题干
        String ask = "";
        if (!textSentence.contains("Ж") && translate != null) {
            ask = translate+"("+item.getKnowledgename()+")";

            document.getElementsByTag("QuesOptionAsk").first().text("<P class=tq-p>" + ask.trim() + "</P>" + "<P class=tq-p>");

            document.getElementsByTag("QuesAnswer").first().text(textSentence);

            org.jsoup.nodes.Element body = document.getElementsByTag("body").first();
            //去自动换行
            document.outputSettings().prettyPrint(false);
            String html = body.html();
            //查属性对应的编码，更新NewProperty这个字段
            String newProperty = lgdbBasicQuestionclassifynameMapper.selectNewproperty("中译英");
            //识别和插入到原始库
            query.AutoEdit("C", Stage, html, "g", 40, TaskId, item.getKnowledgename(), item.getSentence() , newProperty);
            lgdbBKlgOrgresourceMapper.LgdbBKlgOrgresourceUpdate(Stage, "g40", item.getId());

        }
        Ask = ask;
        Answer = textSentence;
        ArrayList<String> strings = new ArrayList<>();
        if (Ask != null && Answer != null) {
            strings.add("【题干】：" + Ask);
            strings.add("【参考答案】: " + Answer);
        }
        return strings;
    }


    //匹配题  对话匹配,m,16,无,英语常用表达
    public ArrayList<String> quesTypem16(ArrayList<LgdbBKlgOrgresource> list, String Stage, String TaskId, MainService query) throws Exception {

        String Ask = "";
        String Option = "";
        String Answer = "";
        StringBuilder sb = new StringBuilder();
        ArrayList<String> strings = new ArrayList<>();
        int flag = 0;
        String knownNameList = "";
        String sentenceList = "";
        HashMap<String, String> dic = new HashMap<>();
        ArrayList<LgdbBKlgOrgresource> listNew = new ArrayList<>();

        try {
            for (LgdbBKlgOrgresource m2 : list) {
                if (listNew.size() < 5) {
                    listNew.add(m2);
                }
            }
            for (LgdbBKlgOrgresource item : listNew) {
                if (flag > 4) {
                    break;
                }
                //获取去掉标签的句子
                String textSentence = Optional.ofNullable(item).map(c -> c.getSentence()).orElse("sentence为空值");
                String sentence = helperService.getTextFromTHML(textSentence);
                //考虑重复情况
                if (dic.containsKey(sentence.split("Ж")[0]) || dic.containsValue(sentence.split("Ж")[1])) {
                    continue;
                } else {
                    dic.put("" + sentence.split("Ж")[0] + "____" + "", sentence.split("Ж")[1]);
                    lgdbBKlgOrgresourceMapper.LgdbBKlgOrgresourceUpdate(Stage, "m16", item.getId());
                    knownNameList += item.getKnowledgename() + "|";
                    sentenceList += sentence + "|";
                    ++flag;
                }
            }
            if (dic != null) {
                sb.append("<TContent>");
                sb.append("<Quesbody></Quesbody>");
                sb.append("<QuesArticle audio=\"\" orgname=\"\" />");
                sb.append("<QuesChild index=\"1\" answertype=\"32\">");
                sb.append("" + helperService.m16DealQues(dic) + "");
                sb.append("<QuesAnalyze/> ");
                sb.append("<QueStem /><QuesScore>0</QuesScore>");
                sb.append("</QuesChild>");
                sb.append("</TContent>");

                // 识别和插入到原始库
                //查属性对应的编码，更新NewProperty这个字段
                String newProperty = lgdbBasicQuestionclassifynameMapper.selectNewproperty("对话匹配");
                query.AutoEdit("C", Stage, sb.toString(), "m", 16, TaskId, knownNameList.substring(0, knownNameList.length() - 1), sentenceList.substring(0, sentenceList.length() - 1), newProperty);
                //解析html,保留大小写
                Parser parser = Parser.htmlParser();
                parser.settings(new ParseSettings(true, true));
                Document document = parser.parseInput(sb.toString(), "utf-8");
                Elements quesOption = document.getElementsByTag("QuesOption");
                for (Element m3 : quesOption) {
                    Option += m3.text() + "|";
                }
                Answer = document.getElementsByTag("QuesAnswer").text();
                 Ask = document.getElementsByTag("QuesOptionAsk").text();

                if (Ask != "" && Answer != "" && Option != "") {
                    String[] split = Option.substring(0, Option.length() - 1).split("\\|");
                    for(int i=0; i<split.length;i++){
                        strings.add(split[i]);
                    }
                    String ask = Ask;
                    String[] split1 = Ask.substring(3, Ask.length() - 4).split("</p><p>");
                    for(int i=0; i<split1.length;i++){
                        strings.add(split1[i]);
                    }
                    String an = Answer.replace("$", "");
                    strings.add("【参考答案】: " + an );
                }
            }
        } catch (Exception ex) {
            System.out.println("题型m16使用素材失败的ID=" + ex.toString() + "QuesTypem16CreateQuesError");
        }
        return strings;
    }


    //匹配题  词汇匹配,m,15,无,英语单词英语词组
    public ArrayList<String> quesTypem15(ArrayList<LgdbBKlgOrgresource> listm15Express,ArrayList<LgdbBKlgOrgresource> listm15Word, String Stage, String TaskId, MainService query) throws Exception {

        String Ask = "";
        String Option = "";
        String Answer = "";
        StringBuilder sb = new StringBuilder();
        ArrayList<String> strings = new ArrayList<>();
        int flag = 0;
        String knownNameList = "";
        String sentenceList = "";
        HashMap<String, String> dic = new HashMap<>();
        ArrayList<LgdbBKlgOrgresource> listNew = new ArrayList<>();

        //单词和词组比例3:2    词组
        try {
            if(listm15Word.size()>2){
                for(int i=0;i<3;i++){
                    listNew.add(listm15Word.get(i));
                }
            }
            if(listm15Express.size()>1){
                for(int i=0;i<2;i++){
                    listNew.add(listm15Express.get(i));
                }
            }
            if(listNew.size()==5){
                for (LgdbBKlgOrgresource item : listNew) {
                    if (flag > 4) {
                        break;
                    }
                    //挖掉的知识点
                    String word = helperService.pickUpTargetWord(item);
                    String textSentence = Optional.ofNullable(item).map(c -> c.getSentence()).orElse("sentence为空值");
                    String sentence = helperService.getTextFromTHML(textSentence);
                    //题干
                    String asksentence = sentence.replace(word, "___");
                    //考虑重复情况
                    if (dic.containsKey(asksentence) || dic.containsValue(word)) {
                        continue;
                    } else {
                        dic.put(asksentence, word);
                        lgdbBKlgOrgresourceMapper.LgdbBKlgOrgresourceUpdate(Stage, "m15", item.getId());
                        knownNameList += item.getKnowledgename() + "|";
                        sentenceList += sentence + "|";
                        ++flag;
                    }
                }
            }
            if (dic != null) {
                sb.append("<TContent>");
                sb.append("<Quesbody></Quesbody>");
                sb.append("<QuesArticle audio=\"\" orgname=\"\" />");
                sb.append("<QuesChild index=\"1\" answertype=\"32\">");
                sb.append("" + helperService.dealWordMatch(dic) + "");
                sb.append("<QuesAnalyze/> ");
                sb.append("<QueStem /><QuesScore>0</QuesScore>");
                sb.append("</QuesChild>");
                sb.append("</TContent>");
                // 识别和插入到原始库
                //查属性对应的编码，更新NewProperty这个字段
                String newProperty = lgdbBasicQuestionclassifynameMapper.selectNewproperty("词汇匹配");
                query.AutoEdit("C", Stage, sb.toString(), "m", 15, TaskId, knownNameList.substring(0, knownNameList.length() - 1), sentenceList.substring(0, sentenceList.length() - 1), newProperty);
                //解析html,保留大小写
                Parser parser = Parser.htmlParser();
                parser.settings(new ParseSettings(true, true));
                Document document = parser.parseInput(sb.toString(), "utf-8");
                Elements quesOption = document.getElementsByTag("QuesOption");
                for (Element m3 : quesOption) {
                    Option += m3.text() + "|";
                }
                Answer = document.getElementsByTag("QuesAnswer").text();
                Ask = document.getElementsByTag("QuesOptionAsk").text();

                if (Ask != "" && Answer != "" && Option != "") {
                    String[] split = Option.substring(0, Option.length() - 1).split("\\|");
                    String option = "";
                    for(int i=0; i<split.length;i++){
                        if(i+1==split.length){
                            option+=split[i];
                        }else {
                            option+=split[i]+"  ";
                        }
                    }
                    strings.add(option);
                    String ask = Ask;
                    String[] split1 = Ask.substring(3, Ask.length() - 4).split("</p><p>");
                    for(int i=0; i<split1.length;i++){
                        strings.add(split1[i]);
                    }
                    String an = Answer.replace("$", "");
                    strings.add("【参考答案】: " + an );
                }
            }
        } catch (Exception ex) {
            System.out.println("题型m15使用素材失败的ID=" + ex.toString() + "QuesTypem15CreateQuesError");
        }
        return strings;
    }

}










