package com.lancoo.service.jiqibiantimanager;

import com.lancoo.mapper.LgdbBKlgOrgresourceMapper;
import com.lancoo.mapper.LgdbCKlgOrgresourceMapper;
import com.lancoo.pojo.LgdbBKlgOrgresource;
import com.lancoo.pojo.LgdbCKlgOrgresource;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author KCWang
 * @date 2021/7/30 17:50
 * @Email:KCWang@aliyun.com
 */


@Slf4j
@Service
public class HelperService {


    @Autowired
    private LgdbBKlgOrgresourceMapper lgdbBKlgOrgresourceMapper;

    @Autowired
    private LgdbCKlgOrgresourceMapper lgdbCKlgOrgresourceMapper;

    // 173 判断素材有没被编过先获取值，供每个试题使用
    public String getQuesTypeService(long id, String stage) {
        return lgdbBKlgOrgresourceMapper.findQuesTypeByIdStage(id, stage).getQuestype();
    }

    // 59 判断素材有没被编过先获取值，供每个试题使用
    public String getQuesTypeService59(long id, String stage) {
        return lgdbCKlgOrgresourceMapper.findQuesTypeByIdStage(id, stage).getQuestype();
    }

    //有些题 需要只挖一个空，这里筛选一个空的,挖英语单词
    public int pickTargetWordOneword(String strSentence1, String knowledgeName, String quesGenre) {
        int number = 0;
        //把不标准的处理标准
        String strSentence = getTextFromTHML(strSentence1);

//        strSentence = strSentence.replace("& gt;", "&gt;").replace("& lt;", "&lt;").replace("&lt;", "<").replace("&gt;", ">");
//        strSentence = strSentence.replace("< strong >", "<strong>").replace("</ strong >", "</strong>");
//        strSentence = strSentence.replace("< u >", "<u>").replace("</ u >", "</u>");
//        strSentence = strSentence.replace("<u><strong>", "<u><strong>").replace("</strong></u>", "</u></strong>");
//        strSentence = strSentence.replace("<u>", "").replace("</u>", "").replace("<strong>", "").replace("</strong>", "");
        if (quesGenre != "20" && quesGenre != "15") {
            for (String m : this.wordToOther(knowledgeName).split("\\|")) {
                if (this.TarketWordcount(strSentence, m) > 1) {
                   return number = 0;
                } else if (this.TarketWordcount(strSentence, m) == 1) {
                    return number = 1;
                }
            }
        }
        return number;
    }

    //有些题 需要只挖一个空，挖目标知识点原型
    public int pickTargetWordOriginalOneword(String strSentence1, String knowledgeName, String quesGenre) {
        int number = 0;
        //把不标准的处理标准
        String strSentence = getTextFromTHML(strSentence1).replace(".","").replace("?","").replace(",","").replace("!","");
        String[] s = strSentence.split(" ");
        if (quesGenre != "20" && quesGenre != "15") {

                if (this.TarketWordcount(strSentence, knowledgeName) > 1) {
                    return number = 0;
                } else if (this.TarketWordcount(strSentence, knowledgeName) == 1) {
                    List<String> collect = Arrays.stream(s).filter(c -> c.equals(knowledgeName)).collect(Collectors.toList());
                    if(collect!=null && collect.size()==1){
                        return number = 1;
                    }

                }
        }
        return number;
    }


    //有些题 需要只挖一个空，挖目标知识点为非原型
    public int pickTargetWordNoOriginalOneword(String strSentence1, String knowledgeName, String quesGenre) {
        int number = 0;
        // String textFromTHML = getTextFromTHML("The long-eared <strong><u>jerboa</u></strong> can be distinguished from other <strong><u>jerboas</u></strong> by its enormous ears.");
        // int i = TarketWordcount(textFromTHML,"jerboa" );
        //把不标准的处理标准
        String strSentence = getTextFromTHML(strSentence1).replace(".","").replace("?","").replace(",","").replace("!","");
        String[] s = strSentence.split(" ");
        if (quesGenre != "20" && quesGenre != "15") {
            if (this.TarketWordcount(strSentence, knowledgeName) > 1) {
                return number = 0;
            } else if (this.TarketWordcount(strSentence, knowledgeName) == 1) {
                List<String> collect = Arrays.stream(s).filter(c ->  c.replaceAll("[^a-zA-Z]*", "").equals(knowledgeName)).collect(Collectors.toList());
                if( collect.size()==0){
                    List<String> collect1 = Arrays.stream(s).filter(c -> c.matches(knowledgeName + "(.*)")).collect(Collectors.toList());
                    // List<String> collect1 = Arrays.stream(s).filter(c -> c.replaceAll("[^a-zA-Z]*", "").matches(knowledgeName + "(.*)")).collect(Collectors.toList());
                    if(collect1.size()==1){
                        return number = 1;
                    }
                }
            }
        }
        return number;
    }

    //取非原型目标知识点
    public String pickNoOriginalTargetWord(String strSentence1, String knowledgeName) {
        String number = null;
        //把不标准的处理标准
        String strSentence = getTextFromTHML(strSentence1).replace(".","").replace("?","").replace(",","").replace("!","");
        String[] s = strSentence.split(" ");
        for (String m:s) {
            String s1 = m.replaceAll("[^a-zA-Z]*", "");
            boolean matches = s1.matches(knowledgeName + "(.*)");
            if(matches && !knowledgeName.equals(s1)){
                return s1;
            }
        }
        return number;
    }







    //挖英语单词
    public String pickTargetWordOneword1(String strSentence1, String knowledgeName, String quesGenre) {
        String number = "";
        //把不标准的处理标准
        String strSentence = getTextFromTHML(strSentence1);

//        strSentence = strSentence.replace("& gt;", "&gt;").replace("& lt;", "&lt;").replace("&lt;", "<").replace("&gt;", ">");
//        strSentence = strSentence.replace("< strong >", "<strong>").replace("</ strong >", "</strong>");
//        strSentence = strSentence.replace("< u >", "<u>").replace("</ u >", "</u>");
//        strSentence = strSentence.replace("<u><strong>", "<u><strong>").replace("</strong></u>", "</u></strong>");
//        strSentence = strSentence.replace("<u>", "").replace("</u>", "").replace("<strong>", "").replace("</strong>", "");
        if (quesGenre != "20" && quesGenre != "15") {
            for (String m : this.wordToOther(knowledgeName).split("\\|")) {
                if (this.TarketWordcount(strSentence, m) > 1) {
                    return number ;
                } else if (this.TarketWordcount(strSentence, m) == 1) {
                    return number = m;
                }
            }
        }
        return number;
    }


    //挖 英语常用表达英语词组
    public int pickTargetWordOneexpress(String strSentence, String knowledgeName, String quesGenre) {
        strSentence = strSentence.replace("& gt;", "&gt;").replace("& lt;", "&lt;").replace("&lt;", "<").replace("&gt;", ">");
        int number = 0;
        //把不标准的处理标准
        strSentence = strSentence.replace("< strong >", "<strong>").replace("</ strong >", "</strong>");
        strSentence = strSentence.replace("< u >", "<u>").replace("</ u >", "</u>");
        strSentence = strSentence.replace("<u><strong>", "<u><strong>").replace("</strong></u>", "</u></strong>");
        strSentence = strSentence.replace("<strong>", "").replace("<u>", "").replace("</u>", "").replace("</strong>", "");
        if (quesGenre != "20" && quesGenre != "15") {
            for (String m : this.wordToOtherexpress(knowledgeName).split("\\|")) {
                if (this.TarketWordcount(strSentence, m) > 1) {
                    return number = 0;
                } else if (this.TarketWordcount(strSentence, m) == 1) {
                    return number = 1;
                }
            }
        }
        return number;
    }




    public String pickUpTargetWord59(com.lancoo.pojo59.LgdbBKlgOrgresource targertitem) {

        if(targertitem.getKnowledgename().equals("a.m.") || targertitem.getKnowledgename().equals("p.m.")){
            return targertitem.getKnowledgename();
        }
        String sentence1 = targertitem.getSentence().replace(".","").replace("?","").replace(",","").replace("!","");
        String knowledgename = "";
        if(targertitem.getKnowledgename().split(" ").length>1){
            knowledgename = targertitem.getKnowledgename().replace(".","").replace(",","").replace("?","").replace("!","");
        }else if(targertitem.getKnowledgename().split(" ").length==1) {
            knowledgename = targertitem.getKnowledgename().replaceAll("[^a-zA-Z]", "");
        }
        String s1 = knowledgename.split("")[0].toLowerCase();
        String knowledgename1 = s1+knowledgename.substring(1);
        String s2 = knowledgename.split("")[0].toUpperCase();
        String knowledgename2 = s2+knowledgename.substring(1);
        ArrayList<String> knowledgename12 = new ArrayList<>();
        knowledgename12.add(knowledgename1);
        knowledgename12.add(knowledgename2);
        String word = null;
        //把不标准的处理标准 处理标准后，去标志符号，再提取
        String sentence = getTextFromTHML(sentence1);
        String[] s = sentence.split(" ");
//        sentence = sentence.replace("&lt;", "<").replace("&gt;", ">").replace("< strong >", "<strong>").replace("</ strong >", "</strong>").replace("< u >", "<u>").replace("</ u >", "</u>").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong>", "").replace("<u>", "").replace("</u>", "").replace("</strong>", "");
//        for (String m : this.wordToOther(knowledgename).split("\\|")) {
//            for (String m1:s) {
//                boolean matches = m1.matches(m + "(*.)");
//                if(m.equals(m1)){
//                   return word = m;
//
//                }else if(m.split(" ").length>1 && TarketWordcount(sentence,m)==1){
//                    return  word =m;
//                }
//            }
//        }
        for (String m2:knowledgename12) {
            for (String m1:s) {
                boolean matches = m1.matches(m2 + "(.*)");
                if(matches){
                    String m11 = m1.replaceAll("[^a-zA-Z]", "");
                    return word = m11;
                }
            }
        }
        for (String m2:knowledgename12) {
            if(m2.split(" ").length>1 && TarketWordcount(sentence,m2)==1){
                return  word =m2;
            }
        }


        return word;
    }


    //提取目标单词
    public String pickUpTargetWordC(LgdbCKlgOrgresource targertitem) {

        if(targertitem.getKnowledgename().equals("a.m.") || targertitem.getKnowledgename().equals("p.m.")){
            return targertitem.getKnowledgename();
        }
        String sentence1 = targertitem.getSentence().replace(".","").replace("?","").replace(",","").replace("!","");
        String knowledgename = "";
        if(targertitem.getKnowledgename().split(" ").length>1){
            knowledgename = targertitem.getKnowledgename().replace(".","").replace(",","").replace("?","").replace("!","");
        }else if(targertitem.getKnowledgename().split(" ").length==1) {
            knowledgename = targertitem.getKnowledgename().replaceAll("[^a-zA-Z]", "");
        }
        String s1 = knowledgename.split("")[0].toLowerCase();
        String knowledgename1 = s1+knowledgename.substring(1);
        String s2 = knowledgename.split("")[0].toUpperCase();
        String knowledgename2 = s2+knowledgename.substring(1);
        ArrayList<String> knowledgename12 = new ArrayList<>();
        knowledgename12.add(knowledgename1);
        knowledgename12.add(knowledgename2);
        String word = null;
        //把不标准的处理标准 处理标准后，去标志符号，再提取
        String sentence = getTextFromTHML(sentence1);
        String[] s = sentence.split(" ");
//        sentence = sentence.replace("&lt;", "<").replace("&gt;", ">").replace("< strong >", "<strong>").replace("</ strong >", "</strong>").replace("< u >", "<u>").replace("</ u >", "</u>").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong>", "").replace("<u>", "").replace("</u>", "").replace("</strong>", "");
//        for (String m : this.wordToOther(knowledgename).split("\\|")) {
//            for (String m1:s) {
//                boolean matches = m1.matches(m + "(*.)");
//                if(m.equals(m1)){
//                   return word = m;
//
//                }else if(m.split(" ").length>1 && TarketWordcount(sentence,m)==1){
//                    return  word =m;
//                }
//            }
//        }
        for (String m2:knowledgename12) {
            for (String m1:s) {
                boolean matches = m1.matches(m2 + "(.*)");
                if(matches){
                    String m11 = m1.replaceAll("[^a-zA-Z]", "");
                    return word = m11;
                }
            }
        }
        for (String m2:knowledgename12) {
            if(m2.split(" ").length>1 && TarketWordcount(sentence,m2)==1){
                return  word =m2;
            }
        }


        return word;
    }
    //提取目标单词
    public String pickUpTargetWord(LgdbBKlgOrgresource targertitem) {

        if(targertitem.getKnowledgename().equals("a.m.") || targertitem.getKnowledgename().equals("p.m.")){
            return targertitem.getKnowledgename();
        }
        String sentence1 = targertitem.getSentence().replace(".","").replace("?","").replace(",","").replace("!","");
        String knowledgename = "";
        if(targertitem.getKnowledgename().split(" ").length>1){
             knowledgename = targertitem.getKnowledgename().replace(".","").replace(",","").replace("?","").replace("!","");
        }else if(targertitem.getKnowledgename().split(" ").length==1) {
            knowledgename = targertitem.getKnowledgename().replaceAll("[^a-zA-Z]", "");
        }
        String s1 = knowledgename.split("")[0].toLowerCase();
        String knowledgename1 = s1+knowledgename.substring(1);
        String s2 = knowledgename.split("")[0].toUpperCase();
        String knowledgename2 = s2+knowledgename.substring(1);
        ArrayList<String> knowledgename12 = new ArrayList<>();
        knowledgename12.add(knowledgename1);
        knowledgename12.add(knowledgename2);
        String word = null;
        //把不标准的处理标准 处理标准后，去标志符号，再提取
        String sentence = getTextFromTHML(sentence1);
        String[] s = sentence.split(" ");
//        sentence = sentence.replace("&lt;", "<").replace("&gt;", ">").replace("< strong >", "<strong>").replace("</ strong >", "</strong>").replace("< u >", "<u>").replace("</ u >", "</u>").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong>", "").replace("<u>", "").replace("</u>", "").replace("</strong>", "");
//        for (String m : this.wordToOther(knowledgename).split("\\|")) {
//            for (String m1:s) {
//                boolean matches = m1.matches(m + "(*.)");
//                if(m.equals(m1)){
//                   return word = m;
//
//                }else if(m.split(" ").length>1 && TarketWordcount(sentence,m)==1){
//                    return  word =m;
//                }
//            }
//        }
        for (String m2:knowledgename12) {
            for (String m1:s) {
                boolean matches = m1.matches(m2 + "(.*)");
                if(matches){
                    String m11 = m1.replaceAll("[^a-zA-Z]", "");
                    return word = m11;
                }
            }
        }
        for (String m2:knowledgename12) {
            if(m2.split(" ").length>1 && TarketWordcount(sentence,m2)==1){
                return  word =m2;
            }
        }


        return word;
    }

//    //提取目标短语或者常用表达
//    public String pickUpTargetExpress(LgdbBKlgOrgresource targertitem) {
//        String sentence1 = targertitem.getSentence().replace(".","");
//        String knowledgename = targertitem.getKnowledgename();
//        String word = null;
//        //把不标准的处理标准 处理标准后，去标志符号，再提取
//        String sentence = getTextFromTHML(sentence1);
//        for (String s1:wordToOther(knowledgename).split("\\|")) {
//            if(TarketWordcount(sentence1,s1)==1){
//                return s1
//            }
//        }
//        for (String m : this.wordToOther(knowledgename).split("\\|")) {
//            for (String m1:s) {
//                if(m.equals(m1)){
//                    return word = m;
//
//                }else if(TarketWordcount(sentence,m)==1){
//                    return  word =m;
//                }
//            }
//        }
//        return word;
//    }



    //得到单词干扰项
    public ArrayList<String> getFirstNumber(String str) {
        ArrayList<String> list = new ArrayList<String>();
        String number = str.substring(0, 2);
        int length = str.length();
        ArrayList<String> dt = lgdbBKlgOrgresourceMapper.findLgdbBKlgOrgresourcesByIsDeleteIsSelect(number);
        if (dt.size() > 0) {
            for (int i = 0; i < dt.size(); i++) {
                for (int j = 0; j < dt.get(i).toString().split("\\|").length; j++) {
                    String word = dt.get(i).toString().split("\\|")[j];
                    if (word.length() > 2) {
                        if (number.equals(word.substring(0, 2)) && !word.contains(" ")) {
                            int lenNow = dt.get(i).toString().split("\\|")[j].length();
                            if (length < lenNow + 2 && length > lenNow - 2 && !list.contains(dt.get(i).toString().split("\\|")[j]) && !str.contains(dt.get(i).toString().split("\\|")[j])) {
                                list.add(dt.get(i).toString().split("\\|")[j].toLowerCase());
                            }
                        }
                    }

                }
            }
        }
        Collections.shuffle(list);
        return list;
    }

    //得到短语干扰项
    public ArrayList<String> getFirstNumber1(String str) {
        ArrayList<String> list = new ArrayList<String>();
        String number = str.split(" ")[0].replace("'", "");
//        String number = str.substring(0, 2);
        int length = str.length();
        ArrayList<String> dt = lgdbBKlgOrgresourceMapper.findLgdbBKlgOrgresourcesExpressBy(number);
        if (dt.size() > 0) {
            for (int i = 0; i < dt.size(); i++) {
                for (int j = 0; j < dt.get(i).toString().split("\\|").length; j++) {
                    String word = dt.get(i).toString().split("\\|")[j];
                    if (word.length() > 2) {
                        if (number.equals(word.split(" ")[0])) {
                            if (!list.contains(dt.get(i).toString().split("\\|")[j]) && !str.equals(dt.get(i).toString().split("\\|")[j])) {
                                int lenNow = dt.get(i).toString().split("\\|")[j].length();
                                if (length < lenNow + 15 && length > lenNow) {
                                    list.add(dt.get(i).toString().split("\\|")[j].toLowerCase());
                                } else if (length + 15 > lenNow && length < lenNow) {
                                    list.add(dt.get(i).toString().split("\\|")[j].toLowerCase());
                                }
                            }
                        }
                    }
                }
            }
        }
        Collections.shuffle(list);
        return list;
    }

    public ArrayList randomSortList(ArrayList list) {
        Collections.shuffle(list);
        return list;
    }

    public String[] randomStringList(String[] list) {
        Collections.shuffle(Arrays.asList(list));
        return list;
    }


    public ArrayList<LgdbBKlgOrgresource> randomSortList1(ArrayList<LgdbBKlgOrgresource> list) {
        Collections.shuffle(list);
        return list;
    }

    public String wordToOtherexpress(String str) {
        String wordOther = str;
        wordOther += "|" + str + "</u>ing";
        wordOther += "|" + str + "</u>ed";
        wordOther += "|" + str + "</u>es";
        wordOther += "|" + str + "</u>s";
        wordOther += "|" + str + "</strong>ing";
        wordOther += "|" + str + "</strong>ed";
        wordOther += "|" + str + "</strong>es";
        wordOther += "|" + str + "</strong>s";
        wordOther += "|" + str + "</u></strong>ing";
        wordOther += "|" + str + "</u></strong>ed";
        wordOther += "|" + str + "</u></strong>es";
        wordOther += "|" + str + "</u></strong>s";
        return wordOther;
    }

    public String wordToOther(String str) {
        String s1 = str.replace(str.split("")[0], str.split("")[0].toUpperCase());
        String s2 = str.replace(str.split("")[0], str.split("")[0].toLowerCase());
        String wordOther = str + "s";;

        wordOther += "|" + str + "ing";
        wordOther += "|" + str + "ed";
        wordOther += "|" + str + "d";
        wordOther += "|" + str + "es";
        wordOther += "|" + s2;
        wordOther += "|" + s1;
        wordOther += "|" + str;
//        wordOther += "|" + "<u>"+ str + "</u>ing";
//        wordOther += "|" +"<u>"+  str + "</u>ed";
//        wordOther += "|" + "<u>"+ str + "</u>es";
//        wordOther += "|" + "<u>"+ str + "</u>s";
//        wordOther += "|" +"<strong>"+str + "</strong>ing";
//        wordOther += "|" + "<strong>"+str + "</strong>ed";
//        wordOther += "|" + "<strong>"+str + "</strong>es";
//        wordOther += "|" +"<strong>"+ str + "</strong>s";
        return wordOther;
    }

    public int TarketWordcount(String Sentence, String word) {
        int count = 0;
        int index = 0;
        while (true) {
            index = Sentence.indexOf(word, index);
            if (index >= 0) {
                index++;
                count++;
            } else {
                break;
            }
        }
        return count;

    }

    public int TarketWordcount01(String Sentence, String word) {
        int count = 0;
        String[] s = Sentence.split(" ");
        for (String m:s) {
            boolean matches = m.matches(word + "(.*)");
            if(matches){
                ++count;
            }
        }
        return count;
    }







    //同一道题内各素材之间的长度差距不超过5个单词
    public int interLengthLessFive(int mlength, ArrayList<LgdbBKlgOrgresource> arrayList) {
        int countnm = 0;
        if (arrayList == null) {
            return countnm = 0;
        } else {
            for (LgdbBKlgOrgresource n : arrayList) {
                int nlength = n.getSentence().replace("&lt;", "<").replace("&gt;", ">").replace("< strong >", "<strong>").replace("</ strong >", "</strong>").replace("< u >", "<u>").replace("</ u >", "</u>").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong>", "").replace("<u>", "").replace("</u>", "").replace("</strong>", "").split(" ").length;
                if (mlength < nlength + 5 && mlength > nlength || mlength==nlength) {
                    countnm = 0;
                } else if (mlength < nlength && mlength + 5 > nlength || mlength==nlength) {
                    countnm = 0;
                } else {
                   return  ++countnm;
                }
            }
        }
        return countnm;
    }


    public int interLengthLessFive1(int mlength, ArrayList<String> arrayList) {
        int countnm = 0;
        if (arrayList == null) {
            return countnm = 0;
        } else {
            for (String n : arrayList) {
                int nlength = n.split(" ").length;
                if (mlength < (nlength + 5) && mlength > nlength || mlength==nlength) {
                    countnm = 0;
                } else if (mlength < nlength && (mlength + 5) > nlength || mlength==nlength) {
                    countnm = 0;
                } else {
                   return  ++countnm;
                }
            }
        }
        return countnm;
    }



    public int interLengthLessFive2(int mlength, int nlength) {
        int countnm = 0;
        if (mlength < nlength + 5 && mlength > nlength || mlength==nlength) {
            countnm = 0;
        } else if (mlength < nlength && mlength + 5 > nlength || mlength==nlength) {
            countnm = 0;
        } else {
            ++countnm;
        }
        return countnm;
    }






    //选词填空打乱顺序
    public String stringArrayRandom(String quesBody) {
        quesBody = quesBody.substring(0, quesBody.length() - 1);
        String returnStr = "";
        ArrayList<String> lst = new ArrayList();
        for (int i = 0; i < quesBody.split(";").length; i++) {
            lst.add(quesBody.split(";")[i]);
        }
        Collections.shuffle(lst);
        for (String m : lst) {
            returnStr += m + "; ";
        }
        returnStr = returnStr.substring(0, returnStr.length() - 2);
        return returnStr;
    }

    //过滤标点
    public String fileterMark(String str) {

        return str.replace(",", "").replace(".", "").replace(";", "").replace("，", "").replace("；", "").replace("。", "");
    }


    //去除所有HTML的标签
    public String getTextFromTHML(String htmlStr) {
        Document doc = Jsoup.parse(htmlStr);
        String text = doc.text();
        // remove extra white space
        StringBuilder builder = new StringBuilder(text);
        int index = 0;
        while (builder.length() > index) {
            char tmp = builder.charAt(index);
            if (Character.isSpaceChar(tmp) || Character.isWhitespace(tmp)) {
                builder.setCharAt(index, ' ');
            }
            index++;
        }
        text = builder.toString().replaceAll(" +", " ").trim();
        return text;
    }

    //处理答案
    public String handleAnswer(String aw) {
        String Aws;
        switch (aw) {
            case "1":
                Aws = "A";
                break;
            case "2":
                Aws = "B";
                break;
            case "3":
                Aws = "C";
                break;
            case "4":
                Aws = "D";
                break;
            case "5":
                Aws = "E";
                break;
            case "6":
                Aws = "F";
                break;
            case "7":
                Aws = "G";
                break;
            case "8":
                Aws = "H";
                break;
            case "9":
                Aws = "I";
                break;
            case "10":
                Aws = "J";
                break;
            case "11":
                Aws = "K";
                break;
            case "12":
                Aws = "L";
                break;
            case "13":
                Aws = "M";
                break;
            case "14":
                Aws = "N";
                break;
            case "15":
                Aws = "O";
                break;
            case "16":
                Aws = "P";
                break;
            case "17":
                Aws = "Q";
                break;
            case "18":
                Aws = "R";
                break;
            case "19":
                Aws = "S";
                break;
            case "20":
                Aws = "T";
                break;
            //---如果还有可以写到 Z,通常是ABCD
            default:
                Aws = aw;
                break;
        }
        return Aws;
    }


    //对话匹配
    public String m16DealQues(HashMap<String, String> sDic) {
        //本地是五个题干 六个选项个
        String option = "";
        String awser = "";
        String quesOptionAsk = "";
        String quesAnswer = "";
        int index = 0;
        String strSelect = "";
        //--拼接题干--及造出选项和干扰项
        //选项
        ArrayList<HashMap<Integer, String>> list = new ArrayList<>();
        //题干
        HashMap<Integer, String> listAsk = new HashMap<>();

        for (Map.Entry<String, String> st : sDic.entrySet()) {
            ++index;
            HashMap<Integer, String> stChild = new HashMap<>();
            //Option
            stChild.put(index, st.getValue());
            strSelect += index + "|";
            list.add(stChild);
            listAsk.put(index, st.getKey());//Ask
        }
        HashMap<Integer, String> stOther = new HashMap<>();
//        index = index + 1;
//        stOther.put(index, getOtherItemM16());
//        list.Add(stOther);

        //--把选项打乱顺序生成新顺序
        list = randomSortList(list);//打乱顺序
        int sortCount = 0;
        HashMap<HashMap<Integer, String>, Integer> dicSort = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            ++sortCount;
            dicSort.put(list.get(i), sortCount);
        }
        //--遍历Option 选项
        for (Map.Entry<HashMap<Integer, String>, Integer> item : dicSort.entrySet()) {
            for (Map.Entry<Integer, String> itemChild1 : item.getKey().entrySet()) {
                option += "<QuesOption index=\"" + item.getValue() + "\">" + itemChild1.getValue() + "</QuesOption>";
            }
        }
        //--筛选掉干扰项--拼接答案
        for (Map.Entry<Integer, String> itemChild : listAsk.entrySet()) {
            for (Map.Entry<HashMap<Integer, String>, Integer> item : dicSort.entrySet()) {
                for (Map.Entry<Integer, String> itemChild1 : item.getKey().entrySet()) {
                    if (itemChild.getKey().equals(itemChild1.getKey())) {
                        awser += handleAnswer(item.getValue().toString()) + "$、";
                    }
                }
            }
        }

        //--选项+干扰项拼接
        for (Map.Entry<Integer, String> itemChild : listAsk.entrySet()) {
            quesOptionAsk += "&lt;p&gt;" + "" + itemChild.getKey() + "." + itemChild.getValue() + "&lt;/p&gt;";
        }

        //--返回造出的题干选项答案
        quesOptionAsk = "<QuesOptionAsk audio=\"\" orgname=\"\">" + quesOptionAsk + "</QuesOptionAsk>";
        quesAnswer = "<QuesAnswer> " + fileterMark(awser.substring(0, awser.length() - 2)) + " </QuesAnswer>";
        return quesOptionAsk + option + quesAnswer;
    }

    /*******************************************************************
     */

    //处理词汇匹配 题干 答案 选项
    public String dealWordMatch(HashMap<String, String> sDic) {
        String option = "";
        String awser = "";
        String quesOptionAsk = "";
        String quesAnswer = "";
        int index = 0;
        String strSelect = "";
        HashMap<Integer, String> listAsk = new HashMap<>();
        //----拼接题干--及造出选项和干扰项
        ArrayList<HashMap<Integer, String>> list = new ArrayList<>();
        for (Map.Entry<String, String> st : sDic.entrySet()) {
            ++index;
            HashMap<Integer, String> stChild = new HashMap<>();
            // Option 选项
            stChild.put(index, st.getValue());
            strSelect += st.getValue() + "|";
            list.add(stChild);
            //Ask 题干
            listAsk.put(index, st.getKey());
        }
        //干扰项
        while (true) {
            ArrayList<LgdbBKlgOrgresource> b = lgdbBKlgOrgresourceMapper.findLgdbBKlgOrgresources(1, "B");
            if (!strSelect.contains(b.get(0).getKnowledgename())) {
                HashMap<Integer, String> stOther = new HashMap<>();
                index = index + 1;
                stOther.put(index, b.get(0).getKnowledgename());
                list.add(stOther);
                break;
            } else {
                continue;
            }
        }
        while (true) {
            ArrayList<LgdbBKlgOrgresource> b = lgdbBKlgOrgresourceMapper.findLgdbBKlgOrgresources3(1, "B");
            if (!strSelect.contains(b.get(0).getKnowledgename())) {
                HashMap<Integer, String> stOther = new HashMap<>();
                index = index + 1;
                stOther.put(index, b.get(0).getKnowledgename());
                list.add(stOther);
                break;
            } else {
                continue;
            }
        }
        //----把选项打乱顺序生成新顺序
        list = randomSortList(list);//打乱顺序
        int sortCount = 0;
        HashMap<HashMap<Integer, String>, Integer> dicSort = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            ++sortCount;
            dicSort.put(list.get(i), sortCount);
        }
        //----遍历Option
        for (Map.Entry<HashMap<Integer, String>, Integer> item : dicSort.entrySet()) {
            for (Map.Entry<Integer, String> itemChild1 : item.getKey().entrySet()) {
                option += "<QuesOption index=\"" + item.getValue() + "\">" + itemChild1.getValue() + "</QuesOption>";
            }
        }
        //----筛选掉干扰项拼接答案
        for (Map.Entry<Integer, String> itemChild : listAsk.entrySet()) {
            for (Map.Entry<HashMap<Integer, String>, Integer> item : dicSort.entrySet()) {
                for (Map.Entry<Integer, String> itemChild1 : item.getKey().entrySet()) {
                    if (itemChild.getKey().equals(itemChild1.getKey())) {
                        awser += handleAnswer(item.getValue().toString()) + "$、";
                    }
                }
            }
        }
        //----题干
        for (Map.Entry<Integer, String> itemChild : listAsk.entrySet()) {
            quesOptionAsk += "&lt;p&gt;" + "" + itemChild.getKey() + "." + itemChild.getValue() + "&lt;/p&gt;";
        }
        quesOptionAsk = "<QuesOptionAsk audio=\"\" orgname=\"\">" + quesOptionAsk + "</QuesOptionAsk>";

        quesAnswer = "<QuesAnswer> " + fileterMark(awser.substring(0, awser.length() - 2)) + " </QuesAnswer>";
        return quesOptionAsk + option + quesAnswer;
    }

}
