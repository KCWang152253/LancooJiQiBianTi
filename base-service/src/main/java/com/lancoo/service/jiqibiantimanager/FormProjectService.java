package com.lancoo.service.jiqibiantimanager;

import com.lancoo.mapper.LgdbBKlgOrgresourceMapper;
import com.lancoo.mapper.LgdbBasicKnowledgeandcodeMapper;
import com.lancoo.mapper.LgdbCKlgOrgresourceMapper;
import com.lancoo.mapper.LgdbTaskinfoMapper;
import com.lancoo.pojo.LgdbBKlgOrgresource;
import com.lancoo.pojo.LgdbCKlgOrgresource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author KCWang
 * @date 2021/7/29 11:05
 * @Email:KCWang@aliyun.com
 */

@Slf4j
@Service
public class FormProjectService {

    // private final  static  HashMap<Integer, ArrayList<LgdbBKlgOrgresource>> arrayListHashMap201 = new HashMap<Integer, ArrayList<LgdbBKlgOrgresource>>();

    //词汇填空题
    
    private final HashMap<Integer, ArrayList<LgdbBKlgOrgresource>> arrayListHashMap201 = new HashMap<>();
    private final ArrayList<LgdbBKlgOrgresource> listmN201 = new ArrayList<>();
    private final ArrayList<LgdbBKlgOrgresource> listmN202 = new ArrayList<>();
    private final ArrayList<LgdbBKlgOrgresource> listmN203 = new ArrayList<>();
    private final ArrayList<LgdbBKlgOrgresource> listmN203In = new ArrayList<>();
    private final ArrayList<LgdbBKlgOrgresource> listmN203Nin = new ArrayList<>();

    private final ArrayList<LgdbBKlgOrgresource> listmN211 = new ArrayList<>();
    private final ArrayList<LgdbBKlgOrgresource> listmN211B = new ArrayList<>();
    private final ArrayList<LgdbCKlgOrgresource> listmN211C = new ArrayList<>();
    private final ArrayList<LgdbBKlgOrgresource> listmN212B = new ArrayList<>();
    private final ArrayList<LgdbCKlgOrgresource> listmN212C = new ArrayList<>();

    private final ArrayList<LgdbBKlgOrgresource> listmN213 = new ArrayList<>();
    private final ArrayList<LgdbBKlgOrgresource> listmN213B = new ArrayList<>();
    private final ArrayList<LgdbCKlgOrgresource> listmN213C = new ArrayList<>();

    private final ArrayList<LgdbBKlgOrgresource> listmN214 = new ArrayList<>();
    private final ArrayList<LgdbBKlgOrgresource> listmN214B = new ArrayList<>();
    private final ArrayList<LgdbCKlgOrgresource> listmN214C = new ArrayList<>();
    private final ArrayList<LgdbBKlgOrgresource> listmN215B = new ArrayList<>();
    private final ArrayList<LgdbCKlgOrgresource> listmN215C = new ArrayList<>();
    private final ArrayList<LgdbBKlgOrgresource> listmN216 = new ArrayList<>();
    private final ArrayList<LgdbBKlgOrgresource> listmN216B = new ArrayList<>();
    private final ArrayList<LgdbCKlgOrgresource> listmN216C = new ArrayList<>();


    //匹配题
    private final ArrayList<LgdbBKlgOrgresource> listm15 = new ArrayList<>();
    private ArrayList<LgdbBKlgOrgresource> listm15Word = new ArrayList<>();
    private ArrayList<LgdbBKlgOrgresource> listm15Express = new ArrayList<>();
    private final ArrayList<LgdbBKlgOrgresource> listm16 = new ArrayList<>();
    private ArrayList<LgdbBKlgOrgresource> listm166 = new ArrayList<>();


    //选择题
    private final ArrayList<LgdbBKlgOrgresource> listAgV13word = new ArrayList<>();
    private final ArrayList<LgdbBKlgOrgresource> listAgV13wordB = new ArrayList<>();
    private final ArrayList<LgdbCKlgOrgresource> listAgV13wordC = new ArrayList<>();
    private final ArrayList<LgdbBKlgOrgresource> listAgV13express = new ArrayList<>();
    private final ArrayList<LgdbBKlgOrgresource> listAgV13expressB = new ArrayList<>();
    private final ArrayList<LgdbCKlgOrgresource> listAgV13expressC = new ArrayList<>();
    //连词成句题 可编知识点  英语单词英语词组
    private final ArrayList<LgdbBKlgOrgresource> listLV = new ArrayList<>();


    private final ArrayList<LgdbBKlgOrgresource> listAgV32 = new ArrayList<>();
    private final ArrayList<LgdbBKlgOrgresource> listAgV33 = new ArrayList<>();

    private final ArrayList<LgdbBKlgOrgresource> listAgVV = new ArrayList<>();
    private final ArrayList<LgdbBKlgOrgresource> listAgV38 = new ArrayList<>();
    private final ArrayList<LgdbBKlgOrgresource> listAgV40 = new ArrayList<>();
    private final ArrayList<LgdbBKlgOrgresource> listAgV0 = new ArrayList<>();

    private final ArrayList<LgdbBKlgOrgresource> listmN21 = new ArrayList<>();
    private final ArrayList<LgdbBKlgOrgresource> listmN22 = new ArrayList<>();

    private static final String DONG_CI_SHUXING ="动词属性";
    private static final String TI_CAI_A ="A";

    private final LgdbBKlgOrgresourceMapper lgdbBKlgOrgresourceMapper;
    private final LgdbBasicKnowledgeandcodeMapper lgdbBasicKnowledgeandcodeMapper;
    private final HelperService helperService;
    private final MainService mainQueryService;
    private final QuesTypeAService quesTypeAService;

    private final LgdbCKlgOrgresourceMapper lgdbCKlgOrgresourceMapper;

    private String TaskId;

    public FormProjectService(HelperService helperService, LgdbTaskinfoMapper lgdbTaskinfoMapper, LgdbBKlgOrgresourceMapper lgdbBKlgOrgresourceMapper, LgdbBasicKnowledgeandcodeMapper lgdbBasicKnowledgeandcodeMapper, MainService mainQueryService, QuesTypeAService quesTypeAService, LgdbCKlgOrgresourceMapper lgdbCKlgOrgresourceMapper) {
        this.helperService = helperService;
        this.lgdbBKlgOrgresourceMapper = lgdbBKlgOrgresourceMapper;
        this.lgdbBasicKnowledgeandcodeMapper = lgdbBasicKnowledgeandcodeMapper;
        this.mainQueryService = mainQueryService;
        this.quesTypeAService = quesTypeAService;
        this.lgdbCKlgOrgresourceMapper = lgdbCKlgOrgresourceMapper;
    }

    //在前端加一个点击开始编题的按钮
    public ArrayList<ArrayList<String>> ClickEditStart(String TypeCode, String Stage, ArrayList<String> listQuesGenre, int count, String property, String quesPoint) {
        System.out.println("开始编题******ClickEditStart******开始编题***********ClickEditStart**************");
        ArrayList<ArrayList<String>> queryResponseResult1 = null;
        //获取发布的任务
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        Date date = new Date(System.currentTimeMillis());
        String sysDate = formatter.format(date);
        // LgdbTaskinfo lgdbTaskinfo = lgdbTaskinfoMapper.findLgdbTaskInfoByIds(sysDate, Stage);
        //LgdbTaskinfo lgdbTaskinfo = lgdbTaskinfoMapper.findLgdbTaskInfoByIds("2021-03", Stage);
        //TaskId = lgdbTaskinfo.getTaskid();
        //每次编题的任务id,测试阶段先赋值
        TaskId = "20210922170924";
        //题型
        System.out.println(Stage);
        if (TI_CAI_A.equals(TypeCode)) {
            //A  单词辨音（３２） 词义辨析（同义单词转换）（３３） 单选题（１３）
            // 体裁
            if (listQuesGenre.contains("13")) {
                //可编知识点   英语单词英语词组
                if ("英语单词英语词组".equals(quesPoint)) {
                    listAgV13wordB.clear();
                    listAgV13wordC.clear();
                    ArrayList<LgdbBKlgOrgresource> lgdbBKlgOrgresourcesB;
                    ArrayList<LgdbCKlgOrgresource> lgdbBKlgOrgresourcesC;
                    if("B".equals(Stage)){
                        lgdbBKlgOrgresourcesB = lgdbBKlgOrgresourceMapper.findLgdbBKlgOrgresources10(count, Stage);
                        for (LgdbBKlgOrgresource m : lgdbBKlgOrgresourcesB) {
                            String knowledgename1 = m.getKnowledgename();
                            ArrayList<String> strings = lgdbBasicKnowledgeandcodeMapper.selectThemeByKnowledge(knowledgename1);
                            if (strings != null && strings.size() == 1) {
                                int mlength = m.getSentence().replace("&lt;", "<").replace("&gt;", ">").replace("< strong >", "<strong>").replace("</ strong >", "</strong>").replace("< u >", "<u>").replace("</ u >", "</u>").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong>", "").replace("<u>", "").replace("</u>", "").replace("</strong>", "").split(" ").length;
                                if (5 < mlength && mlength < 31) {
                                    listAgV13wordB.add(m);
                                }
                            }
                        }
                    }else if("C".equals(Stage)){
                        lgdbBKlgOrgresourcesC = lgdbCKlgOrgresourceMapper.findLgdbCKlgOrgresources10(count, Stage);
                        for (LgdbCKlgOrgresource m : lgdbBKlgOrgresourcesC) {
                            String knowledgename1 = m.getKnowledgename();
                            ArrayList<String> strings = lgdbBasicKnowledgeandcodeMapper.selectThemeByKnowledge(knowledgename1);
                            if (strings != null && strings.size() == 1) {
                                int mlength = m.getSentence().replace("&lt;", "<").replace("&gt;", ">").replace("< strong >", "<strong>").replace("</ strong >", "</strong>").replace("< u >", "<u>").replace("</ u >", "</u>").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong>", "").replace("<u>", "").replace("</u>", "").replace("</strong>", "").split(" ").length;
                                if (5 < mlength && mlength < 31) {
                                    listAgV13wordC.add(m);
                                }
                            }
                        }
                    }
                } else {
                    //可编知识点“英语常用表达”
                    listAgV13expressB.clear();
                    listAgV13expressC.clear();
                    ArrayList<LgdbBKlgOrgresource> lgdbBKlgOrgresourcesB;
                    ArrayList<LgdbCKlgOrgresource> lgdbBKlgOrgresourcesC;
                    if("B".equals(Stage)){
                        lgdbBKlgOrgresourcesB = lgdbBKlgOrgresourceMapper.findLgdbBKlgOrgresources8(count, Stage);
                        for (LgdbBKlgOrgresource m : lgdbBKlgOrgresourcesB) {
                            String knowledgename1 = m.getKnowledgename();
                            ArrayList<String> strings = lgdbBasicKnowledgeandcodeMapper.selectThemeByKnowledge(knowledgename1);
                            if (strings != null && strings.size() == 1) {
                                int mlength = m.getSentence().replace("&lt;", "<").replace("&gt;", ">").replace("< strong >", "<strong>").replace("</ strong >", "</strong>").replace("< u >", "<u>").replace("</ u >", "</u>").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong>", "").replace("<u>", "").replace("</u>", "").replace("</strong>", "").split(" ").length;
                                if (5 < mlength && mlength < 20) {
                                    listAgV13expressB.add(m);
                                }
                            }
                        }
                    }else if("C".equals(Stage)){
                        lgdbBKlgOrgresourcesC =  lgdbCKlgOrgresourceMapper.findLgdbCKlgOrgresources8(count, Stage);
                        for (LgdbCKlgOrgresource m : lgdbBKlgOrgresourcesC) {
                            String knowledgename1 = m.getKnowledgename();
                            ArrayList<String> strings = lgdbBasicKnowledgeandcodeMapper.selectThemeByKnowledge(knowledgename1);
                            if (strings != null && strings.size() == 1) {
                                int mlength = m.getSentence().replace("&lt;", "<").replace("&gt;", ">").replace("< strong >", "<strong>").replace("</ strong >", "</strong>").replace("< u >", "<u>").replace("</ u >", "</u>").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong>", "").replace("<u>", "").replace("</u>", "").replace("</strong>", "").split(" ").length;
                                if (5 < mlength && mlength < 20) {
                                    listAgV13expressC.add(m);
                                }
                            }
                        }
                    }

                }
            } else if (listQuesGenre.contains("32")) {
                ArrayList<LgdbBKlgOrgresource> listAgV32 = lgdbBKlgOrgresourceMapper.findLgdbBKlgOrgresources(count, Stage);
            } else if (listQuesGenre.contains("33")) {
                ArrayList<LgdbBKlgOrgresource> listAgV33 = lgdbBKlgOrgresourceMapper.findLgdbBKlgOrgresources(count, Stage);
            }
        } else if ("g".equals(TypeCode)) {
            //翻译 句子英译中,g,38,无,英语单词英语词组  句子中译英,g,40,无,英语单词英语词组
            listAgVV.clear();
            ArrayList<LgdbBKlgOrgresource> lgdbBKlgOrgresources2 = lgdbBKlgOrgresourceMapper.findLgdbBKlgOrgresources23(count, Stage);
            for (LgdbBKlgOrgresource m : lgdbBKlgOrgresources2) {
                String textSentence = helperService.getTextFromTHML(m.getSentence());
                // int mlength = m.getSentence().replace("&lt;", "<").replace("&gt;", ">").replace("< strong >", "<strong>").replace("</ strong >", "</strong>").replace("< u >", "<u>").replace("</ u >", "</u>").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong>", "").replace("<u>", "").replace("</u>", "").replace("</strong>", "").split(" ").length;
                int mlength = textSentence.split(" ").length;
                if (5 < mlength && mlength < 18) {
                    listAgVV.add(m);
                }
            }
        } else if ("V".equals(TypeCode)) {
            //连词成句
            //可编知识点“英语单词英语词组”
            ArrayList<LgdbBKlgOrgresource> lgdbBKlgOrgresources2 = lgdbBKlgOrgresourceMapper.findLgdbBKlgOrgresources22(count, Stage);
            for (LgdbBKlgOrgresource m : lgdbBKlgOrgresources2) {
                int mlength = m.getSentence().replace("&lt;", "<").replace("&gt;", ">").replace("< strong >", "<strong>").replace("</ strong >", "</strong>").replace("< u >", "<u>").replace("</ u >", "</u>").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong>", "").replace("<u>", "").replace("</u>", "").replace("</strong>", "").split(" ").length;
                if (3 < mlength && mlength < 12) {
                    //	不选用含有阿拉伯数字的例句素材。
                    boolean matches = m.getSentence().matches("(.*)(\\d+)(.*)");
                    if(!matches){
                        if(!(m.getKnowledgename().split(" ").length>2)){
                            String sentence = m.getSentence();
                            if(!m.getSentence().contains("\"")){
                                listLV.add(m);
                            }

                        }

                    }

                }
            }
        } else if ("m".equals(TypeCode)) {
            //匹配题
            if (listQuesGenre.contains("16")) {
                listm16.clear();
                //匹配题  对话匹配,m,16,无,英语常用表达
                ArrayList<LgdbBKlgOrgresource> lgdbBKlgOrgresources2 = lgdbBKlgOrgresourceMapper.findLgdbBKlgOrgresources25(count, Stage);
                String str = "";
                StringBuilder str1 = new StringBuilder();
                for (LgdbBKlgOrgresource m : lgdbBKlgOrgresources2) {
                    // Optional.ofNullable(m).map(c ->helperService.getTextFromTHML(c.getSentence())).orElse("getSentence为空").va;
                    String textSentence = helperService.getTextFromTHML(m.getSentence());
                    int mlength = textSentence.split(" ").length;
                    if (4 < mlength && mlength < 16 && textSentence.contains("Ж")) {
                        if (StringUtils.isEmpty(str)) {
                            str += textSentence.split("Ж")[0] + "|";
                            str1.append(textSentence.split("Ж")[1]).append("|");
                            listm16.add(m);
                        } else {
                            if (str.contains(textSentence.split("Ж")[0]) || str1.toString().contains(textSentence.split("Ж")[1])) {
                                continue;
                            } else {
                                str += textSentence.split("Ж")[0] + "|";
                                str1.append(textSentence.split("Ж")[1]).append("|");
                                listm16.add(m);
                            }
                        }
                    }
                    if (listm16.size() == 5) {
                        listm166.addAll(listm16);
                        listm16.clear();
                        str = "";
                        str1 = new StringBuilder();
                    }
                }
            } else if (listQuesGenre.contains("15")) {
                //配题  词汇匹配,m,15,无,英语单词英语词组
                //单词和词组比例3:2    单词
                listm15Word.clear();
                String wodr1 = "";
                String wodr2 = "";
                ArrayList<LgdbBKlgOrgresource> word = lgdbBKlgOrgresourceMapper.findLgdbBKlgOrgresources24((count / 5) * 3, Stage);
//                for (LgdbBKlgOrgresource m : word) {
//                    if (helperService.pickTargetWordOneword(m.getSentence(), m.getKnowledgename(), "") == 1) {
//                        if ("".equals(wodr1)) {
//                            wodr1 += helperService.pickUpTargetWord(m) + "|";
//                            //单个小题素材的长度不超过15个单词
//                            String textFromTHML = helperService.getTextFromTHML(m.getSentence());
//                            int mlength = textFromTHML.split(" ").length;
//                            if (helperService.pickTargetWordOneword(m.getSentence(), m.getKnowledgename(), "") == 1) {
//                                if (mlength < 15) {
//                                    listm15Word.add(m);
//                                }
//                            }
//                        } else {
//                            if (helperService.pickUpTargetWord(m)!=null && !wodr1.contains(helperService.pickUpTargetWord(m))) {
//                                wodr1 += helperService.pickUpTargetWord(m) + "|";
//                                //单个小题素材的长度不超过15个单词
//                                String textFromTHML = helperService.getTextFromTHML(m.getSentence());
//                                int mlength = textFromTHML.split(" ").length;
//                                if (helperService.pickTargetWordOneword(m.getSentence(), m.getKnowledgename(), "") == 1) {
//                                    if (mlength < 15) {
//                                        listm15Word.add(m);
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }


                listm15ExpressAdd(wodr1, word, listm15Word);
                //单词和词组比例3:2    词组
                listm15Express.clear();
                ArrayList<LgdbBKlgOrgresource> express = lgdbBKlgOrgresourceMapper.findLgdbBKlgOrgresources26((count / 5) * 2, Stage);
//                for (LgdbBKlgOrgresource m : express) {
//                    if (helperService.pickTargetWordOneword(m.getSentence(), m.getKnowledgename(), "") == 1) {
//                        if ("".equals(wodr2)) {
//                            wodr2 += helperService.pickUpTargetWord(m) + "|";
//                            //单个小题素材的长度不超过15个单词
//                            String textFromTHML = helperService.getTextFromTHML(m.getSentence());
//                            int mlength = textFromTHML.split(" ").length;
//                            if (helperService.pickTargetWordOneword(m.getSentence(), m.getKnowledgename(), "") == 1) {
//                                if (mlength < 15) {
//                                    listm15Express.add(m);
//                                }
//                            }
//                        } else {
//                            if (helperService.pickUpTargetWord(m)!=null && !wodr2.contains(helperService.pickUpTargetWord(m))) {
//                                wodr2 += helperService.pickUpTargetWord(m) + "|";
//                                //单个小题素材的长度不超过15个单词
//                                String textFromTHML = helperService.getTextFromTHML(m.getSentence());
//                                int mlength = textFromTHML.split(" ").length;
//                                if (helperService.pickTargetWordOneword(m.getSentence(), m.getKnowledgename(), "") == 1) {
//                                    if (mlength < 15) {
//                                        listm15Express.add(m);
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }



                listm15ExpressAdd(wodr2, express, listm15Express);
            }
        } else if ("N".equals(TypeCode)) {
            if (listQuesGenre.contains("20")) {
                //选词填空
                //选词填空,N,20,复合属性,英语单词
                if ("复合属性".equals(property)) {
                    int count1 = count / 6;
                    for (int i = 0; i < count1; i++) {
                        StringBuilder knownNameList = new StringBuilder();
                        int nullcount = 0;
                        int nullcount1 = 0;
                        //茶主题
                        ArrayList<String> themes = lgdbBasicKnowledgeandcodeMapper.selectThemes();


                        /**
                         * 因为同一主题下不超过两个，这样进行筛选很费时
                         */
                        for (String M : themes) {
                            //同一主题连续取空次数
                            if (nullcount > 3) {
                                break;
                            }
                            //同一主题不满足条件次数
                            if (nullcount1 > 50) {
                                break;
                            }
                            LgdbBKlgOrgresource lgdbBKlgOrgresources7 = lgdbBKlgOrgresourceMapper.findLgdbBKlgOrgresources7(M, Stage);
                            while (true) {
                                if (lgdbBKlgOrgresources7 == null) {
                                    ++nullcount1;
                                    if (nullcount1 > 50) {
                                        break;
                                    }
                                    lgdbBKlgOrgresources7 = lgdbBKlgOrgresourceMapper.findLgdbBKlgOrgresources7(M, Stage);
                                    if (lgdbBKlgOrgresources7 == null) {
                                        ++nullcount;
                                        if (nullcount > 3) {
                                            break;
                                        }
                                    }
                                } else {
                                    String knowledgename = lgdbBKlgOrgresources7.getKnowledgename();

                                    ArrayList<String> strings = lgdbBasicKnowledgeandcodeMapper.selectThemeByKnowledge(knowledgename);
                                    //查找在一Theme下的知识点是否存在'实意动词及系动词','情态动词及助动词'主题
                                    List<String> list = strings.stream().filter(c -> "实意动词及系动词".equals(c) || "情态动词及助动词".equals(c)).collect(Collectors.toList());
                                    if (list.size() == 0) {
                                        if (helperService.pickUpTargetWord(lgdbBKlgOrgresources7) != null) {
                                            if (helperService.TarketWordcount(lgdbBKlgOrgresources7.getSentence(), lgdbBKlgOrgresources7.getKnowledgename()) == 1) {
                                                int mlength = lgdbBKlgOrgresources7.getSentence().replace("&lt;", "<").replace("&gt;", ">").replace("< strong >", "<strong>").replace("</ strong >", "</strong>").replace("< u >", "<u>").replace("</ u >", "</u>").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong>", "").replace("<u>", "").replace("</u>", "").replace("</strong>", "").split(" ").length;
                                                if (mlength > 6 && mlength < 16) {
                                                    int i1 = helperService.interLengthLessFive(mlength, listmN201);
                                                    if (i1 == 0) {
                                                        if (!knownNameList.toString().contains(lgdbBKlgOrgresources7.getKnowledgename())) {
                                                            knownNameList.append(lgdbBKlgOrgresources7.getKnowledgename()).append("|");
                                                            listmN201.add(lgdbBKlgOrgresources7);
                                                            break;
                                                        } else {
                                                            lgdbBKlgOrgresources7 = null;
                                                        }
                                                    } else {
                                                        lgdbBKlgOrgresources7 = null;
                                                    }
                                                } else {
                                                    lgdbBKlgOrgresources7 = null;
                                                }
                                            } else {
                                                lgdbBKlgOrgresources7 = null;
                                            }
                                        } else {
                                            lgdbBKlgOrgresources7 = null;
                                        }
                                    } else {
                                        lgdbBKlgOrgresources7 = null;
                                    }
                                }
                            }
                        }
                        if (listmN201.size() == 6) {
                            ArrayList<LgdbBKlgOrgresource> lgdbBKlgOrgresources = new ArrayList<>(listmN201);
                            arrayListHashMap201.put(i, lgdbBKlgOrgresources);
                            int size = listmN201.size();
                        }
                        listmN201.clear();

                    }

                } else if (DONG_CI_SHUXING.equals(property)) {
                    listmN202.clear();
                    ArrayList<LgdbBKlgOrgresource> lgdbBKlgOrgresources5 = lgdbBKlgOrgresourceMapper.findLgdbBKlgOrgresources5(count, Stage);
                    for (LgdbBKlgOrgresource m : lgdbBKlgOrgresources5) {
                        String knowledgename = m.getKnowledgename();
                        ArrayList<String> strings = lgdbBasicKnowledgeandcodeMapper.selectThemeByKnowledge(knowledgename);

                        if (strings != null && strings.size() == 1) {
                            if (helperService.pickUpTargetWord(m) != null) {
                                //单个小题素材的长度不超过15个单词
                                int mlength = m.getSentence().replace("&lt;", "<").replace("&gt;", ">").replace("< strong >", "<strong>").replace("</ strong >", "</strong>").replace("< u >", "<u>").replace("</ u >", "</u>").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong>", "").replace("<u>", "").replace("</u>", "").replace("</strong>", "").split(" ").length;
                                if (mlength < 16) {
                                    int countnm = helperService.interLengthLessFive(mlength, listmN202);
                                    if (countnm == 0) {
                                        listmN202.add(m);
                                    }
                                }
                            }
                        }
                    }
                } else if ("动词复合属性".equals(property)) {
                    StringBuilder knowledgelist = new StringBuilder();
                    ArrayList<LgdbBKlgOrgresource> listmN203NinAndIn = new ArrayList<>();
                    //包含'实意动词及系动词'
                    listmN203Nin.clear();

                    ArrayList<LgdbBKlgOrgresource> lgdbBKlgOrgresources5 = lgdbBKlgOrgresourceMapper.findLgdbBKlgOrgresources16(count / 6, Stage);
                    for (LgdbBKlgOrgresource m : lgdbBKlgOrgresources5) {
                        String knowledgename1 = m.getKnowledgename();
                        ArrayList<String> strings = lgdbBasicKnowledgeandcodeMapper.selectThemeByKnowledge(knowledgename1);
                        if (strings != null && strings.size() == 1) {
                            if (helperService.pickUpTargetWord(m) != null) {
                                //单个小题素材的长度不超过15个单词
                                int mlength = m.getSentence().replace("&lt;", "<").replace("&gt;", ">").replace("< strong >", "<strong>").replace("</ strong >", "</strong>").replace("< u >", "<u>").replace("</ u >", "</u>").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong>", "").replace("<u>", "").replace("</u>", "").replace("</strong>", "").split(" ").length;
                                if (5 < mlength && mlength < 16) {
                                    if (listmN203NinAndIn.size() == 0) {
                                        knowledgelist.append(m.getKnowledgename()).append("|");
                                        listmN203NinAndIn.add(m);
                                        listmN203In.add(m);
                                    } else {
                                        exit01(knowledgelist, listmN203NinAndIn, m, mlength, listmN203In);
                                    }
                                }
                            }
                        }
                    }
                    //不包含'实意动词及系动词'
                    int a = (count / 6) * 5;
                    ArrayList<LgdbBKlgOrgresource> lgdbBKlgOrgresources4 = lgdbBKlgOrgresourceMapper.findLgdbBKlgOrgresources17((count / 6) * 5, Stage);
                    for (LgdbBKlgOrgresource m : lgdbBKlgOrgresources4) {
                        if (m != null && helperService.pickUpTargetWord(m) != null) {
                            //单个小题素材的长度不超过15个单词
                            int mlength = m.getSentence().replace("&lt;", "<").replace("&gt;", ">").replace("< strong >", "<strong>").replace("</ strong >", "</strong>").replace("< u >", "<u>").replace("</ u >", "</u>").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong>", "").replace("<u>", "").replace("</u>", "").replace("</strong>", "").split(" ").length;
                            if (5 < mlength && mlength < 16) {
                                if (listmN203NinAndIn.size() == 0 && !knowledgelist.toString().contains(m.getKnowledgename())) {
                                    knowledgelist.append(m.getKnowledgename()).append("|");
                                    listmN203NinAndIn.add(m);
                                    listmN203Nin.add(m);
                                } else {
                                    exit01(knowledgelist, listmN203NinAndIn, m, mlength, listmN203Nin);
                                }
                            }
                        }
                    }
                }
            } else if (listQuesGenre.contains("21")) {
                //句子填空
                if ("汉语提示".equals(property)) {
                    //可编知识点“英语单词”
                    listmN211B.clear();
                    listmN211C.clear();
                    ArrayList<LgdbBKlgOrgresource> lgdbBKlgOrgresourcesB;
                    ArrayList<LgdbCKlgOrgresource> lgdbBKlgOrgresourcesC;
                    if ("B".equals(Stage)) {
                        lgdbBKlgOrgresourcesB = lgdbBKlgOrgresourceMapper.findLgdbBKlgOrgresources2(count, Stage);
                        for (LgdbBKlgOrgresource m : lgdbBKlgOrgresourcesB) {
                            int mlength = m.getSentence().replace("&lt;", "<").replace("&gt;", ">").replace("< strong >", "<strong>").replace("</ strong >", "</strong>").replace("< u >", "<u>").replace("</ u >", "</u>").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong>", "").replace("<u>", "").replace("</u>", "").replace("</strong>", "").split(" ").length;
                            if (4 < mlength && mlength < 15) {
                                listmN211B.add(m);
                            }
                        }
                    }
                    if ("C".equals(Stage)) {
                        lgdbBKlgOrgresourcesC = lgdbCKlgOrgresourceMapper.findLgdbCKlgOrgresources2(count, Stage);
                        for (LgdbCKlgOrgresource m : lgdbBKlgOrgresourcesC) {
                            int mlength = m.getSentence().replace("&lt;", "<").replace("&gt;", ">").replace("< strong >", "<strong>").replace("</ strong >", "</strong>").replace("< u >", "<u>").replace("</ u >", "</u>").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong>", "").replace("<u>", "").replace("</u>", "").replace("</strong>", "").split(" ").length;

                            if (4 < mlength && mlength < 15) {
                                listmN211C.add(m);
                            }
                        }
                    }
                } else if ("无提示".equals(property)) {

                    listmN212B.clear();
                    listmN212C.clear();
                    ArrayList<LgdbBKlgOrgresource> lgdbBKlgOrgresourcesB;
                    ArrayList<LgdbCKlgOrgresource> lgdbBKlgOrgresourcesC;
                    if ("B".equals(Stage)) {
                        lgdbBKlgOrgresourcesB = lgdbBKlgOrgresourceMapper.findLgdbBKlgOrgresources11(count, Stage);
                        for (LgdbBKlgOrgresource m : lgdbBKlgOrgresourcesB) {
                            int mlength = m.getSentence().replace("&lt;", "<").replace("&gt;", ">").replace("< strong >", "<strong>").replace("</ strong >", "</strong>").replace("< u >", "<u>").replace("</ u >", "</u>").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong>", "").replace("<u>", "").replace("</u>", "").replace("</strong>", "").split(" ").length;
                            if (4 < mlength && mlength < 15) {
                                listmN212B.add(m);
                            }
                        }
                    }
                    if ("C".equals(Stage)) {
                        lgdbBKlgOrgresourcesC = lgdbCKlgOrgresourceMapper.findLgdbCKlgOrgresources11(count, Stage);
                        for (LgdbCKlgOrgresource m : lgdbBKlgOrgresourcesC) {
                            int mlength = m.getSentence().replace("&lt;", "<").replace("&gt;", ">").replace("< strong >", "<strong>").replace("</ strong >", "</strong>").replace("< u >", "<u>").replace("</ u >", "</u>").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong>", "").replace("<u>", "").replace("</u>", "").replace("</strong>", "").split(" ").length;
                            if (4 < mlength && mlength < 15) {
                                listmN212C.add(m);
                            }
                        }
                    }


                } else if ("首字母提示".equals(property)) {
                    listmN213B.clear();
                    listmN213C.clear();
                    ArrayList<LgdbBKlgOrgresource> lgdbBKlgOrgresourcesB;
                    ArrayList<LgdbCKlgOrgresource> lgdbBKlgOrgresourcesC;

                    if ("B".equals(Stage)) {
                        lgdbBKlgOrgresourcesB = lgdbBKlgOrgresourceMapper.findLgdbBKlgOrgresources12(count, Stage);
                        for (LgdbBKlgOrgresource m : lgdbBKlgOrgresourcesB) {
                            int mlength = m.getSentence().replace("&lt;", "<").replace("&gt;", ">").replace("< strong >", "<strong>").replace("</ strong >", "</strong>").replace("< u >", "<u>").replace("</ u >", "</u>").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong>", "").replace("<u>", "").replace("</u>", "").replace("</strong>", "").split(" ").length;
                            if (4 < mlength && mlength < 15) {
                                listmN213B.add(m);
                            }
                        }
                    }
                    if ("C".equals(Stage)) {
                        lgdbBKlgOrgresourcesC = lgdbCKlgOrgresourceMapper.findLgdbCKlgOrgresources12(count, Stage);
                        for (LgdbCKlgOrgresource m : lgdbBKlgOrgresourcesC) {
                            int mlength = m.getSentence().replace("&lt;", "<").replace("&gt;", ">").replace("< strong >", "<strong>").replace("</ strong >", "</strong>").replace("< u >", "<u>").replace("</ u >", "</u>").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong>", "").replace("<u>", "").replace("</u>", "").replace("</strong>", "").split(" ").length;
                            if (4 < mlength && mlength < 15) {
                                listmN213C.add(m);
                            }
                        }
                    }

                } else if ("音标提示".equals(property)) {


                    listmN214B.clear();
                    listmN214C.clear();
                    ArrayList<LgdbBKlgOrgresource> lgdbBKlgOrgresourcesB;
                    ArrayList<LgdbCKlgOrgresource> lgdbBKlgOrgresourcesC;
                    if ("B".equals(Stage)) {
                        lgdbBKlgOrgresourcesB = lgdbBKlgOrgresourceMapper.findLgdbBKlgOrgresources13(count, Stage);
                        for (LgdbBKlgOrgresource m : lgdbBKlgOrgresourcesB) {
                            int mlength = m.getSentence().replace("&lt;", "<").replace("&gt;", ">").replace("< strong >", "<strong>").replace("</ strong >", "</strong>").replace("< u >", "<u>").replace("</ u >", "</u>").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong>", "").replace("<u>", "").replace("</u>", "").replace("</strong>", "").split(" ").length;
                            if (4 < mlength && mlength < 15) {
                                listmN214B.add(m);
                            }
                        }
                    }
                    if ("C".equals(Stage)) {
                        lgdbBKlgOrgresourcesC = lgdbCKlgOrgresourceMapper.findLgdbCKlgOrgresources13(count, Stage);
                        for (LgdbCKlgOrgresource m : lgdbBKlgOrgresourcesC) {
                            int mlength = m.getSentence().replace("&lt;", "<").replace("&gt;", ">").replace("< strong >", "<strong>").replace("</ strong >", "</strong>").replace("< u >", "<u>").replace("</ u >", "</u>").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong>", "").replace("<u>", "").replace("</u>", "").replace("</strong>", "").split(" ").length;
                            if (4 < mlength && mlength < 15) {
                                listmN214C.add(m);
                            }
                        }
                    }

                } else if ("单词提示".equals(property)) {
                    //词汇填空题，单词提示   句子填空,N,21,单词提示,英语单词
                    listmN215B.clear();
                    listmN215C.clear();
                    ArrayList<LgdbBKlgOrgresource> lgdbBKlgOrgresourcesB;
                    ArrayList<LgdbCKlgOrgresource> lgdbBKlgOrgresourcesC;
                    if ("B".equals(Stage)) {
                        lgdbBKlgOrgresourcesB = lgdbBKlgOrgresourceMapper.findLgdbBKlgOrgresources14(count, Stage);
                        if (lgdbBKlgOrgresourcesB != null && lgdbBKlgOrgresourcesB.size() != 0) {
                            for (LgdbBKlgOrgresource m : lgdbBKlgOrgresourcesB) {
                                int mlength = helperService.getTextFromTHML(m.getSentence()).split(" ").length;
                                if (3 < mlength && mlength < 15) {
                                    listmN215B.add(m);
                                }
                            }
                        }
                    } else if ("C".equals(Stage)) {
                        lgdbBKlgOrgresourcesC = lgdbCKlgOrgresourceMapper.findLgdbCKlgOrgresources14(count, Stage);
                        if (lgdbBKlgOrgresourcesC != null && lgdbBKlgOrgresourcesC.size() != 0) {
                            for (LgdbCKlgOrgresource m : lgdbBKlgOrgresourcesC) {
                                int mlength = helperService.getTextFromTHML(m.getSentence()).split(" ").length;
                                if (3 < mlength && mlength < 15) {
                                    listmN215C.add(m);
                                }
                            }
                        }
                    }
                } else if ("首字母+汉语".equals(property)) {
                    //词汇填空题，首字母+汉语    句子填空,N,21,首字母+汉语,英语单词
                    listmN216B.clear();
                    listmN216C.clear();
                    ArrayList<LgdbBKlgOrgresource> lgdbBKlgOrgresourcesB;
                    ArrayList<LgdbCKlgOrgresource> lgdbBKlgOrgresourcesC;
                    if ("B".equals(Stage)) {
                        lgdbBKlgOrgresourcesB = lgdbBKlgOrgresourceMapper.findLgdbBKlgOrgresources15(count, Stage);
                        for (LgdbBKlgOrgresource m : lgdbBKlgOrgresourcesB) {
                            int mlength = helperService.getTextFromTHML(m.getSentence()).split(" ").length;
                            if (3 < mlength && mlength < 15) {
                                listmN216B.add(m);
                            }
                        }
                    }
                    if ("C".equals(Stage)) {
                        lgdbBKlgOrgresourcesC = lgdbCKlgOrgresourceMapper.findLgdbCKlgOrgresources15(count, Stage);
                        for (LgdbCKlgOrgresource m : lgdbBKlgOrgresourcesC) {
                            int mlength = helperService.getTextFromTHML(m.getSentence()).split(" ").length;
                            if (3 < mlength && mlength < 15) {
                                listmN216C.add(m);
                            }
                        }
                    }
                }
            } else if (listQuesGenre.contains("22")) {
                //翻译填空
                //可编知识点“英语词组”
                listmN213B.clear();
                listmN213C.clear();
                ArrayList<LgdbBKlgOrgresource> lgdbBKlgOrgresourcesB;
                ArrayList<LgdbCKlgOrgresource> lgdbBKlgOrgresourcesC;
                if ("B".equals(Stage)) {
                    lgdbBKlgOrgresourcesB = lgdbBKlgOrgresourceMapper.findLgdbBKlgOrgresources3(count, Stage);
                    for (LgdbBKlgOrgresource m : lgdbBKlgOrgresourcesB) {
                        int mlength = m.getSentence().replace("&lt;", "<").replace("&gt;", ">").replace("< strong >", "<strong>").replace("</ strong >", "</strong>").replace("< u >", "<u>").replace("</ u >", "</u>").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong>", "").replace("<u>", "").replace("</u>", "").replace("</strong>", "").split(" ").length;
                        if (mlength > 5 && mlength < 15) {
                            listmN213B.add(m);
                        }
                    }
                }
                if ("C".equals(Stage)) {
                    lgdbBKlgOrgresourcesC = lgdbCKlgOrgresourceMapper.findLgdbCKlgOrgresources3(count, Stage);
                    for (LgdbCKlgOrgresource m : lgdbBKlgOrgresourcesC) {
                        int mlength = m.getSentence().replace("&lt;", "<").replace("&gt;", ">").replace("< strong >", "<strong>").replace("</ strong >", "</strong>").replace("< u >", "<u>").replace("</ u >", "</u>").replace("<u><strong>", "<strong><u>").replace("</strong></u>", "</u></strong>").replace("<strong>", "").replace("<u>", "").replace("</u>", "").replace("</strong>", "").split(" ").length;
                        if (mlength > 5 && mlength < 15) {
                            listmN213C.add(m);
                        }
                    }
                }

            }
        }
        //多素材编一道题，后面编写，体裁类型进入不同逻辑的核心控制方法
        //m,N题型  体裁为15或16或20
        if (listQuesGenre.contains("15") || listQuesGenre.contains("16") || listQuesGenre.contains("20")) {

            queryResponseResult1 = this.spliceMNContent(Stage, listQuesGenre, quesPoint, property);
        }
        //AgNV m,M题型(体裁15,16,20除外)
        if (!listQuesGenre.contains("15") && !listQuesGenre.contains("16") && !listQuesGenre.contains("20")) {
            queryResponseResult1 = this.EditStart(Stage, listQuesGenre, quesPoint, property);
        }
        return queryResponseResult1;
    }

    private void listm15ExpressAdd(String wodr2, ArrayList<LgdbBKlgOrgresource> express, ArrayList<LgdbBKlgOrgresource> listm15Express) {
        for (LgdbBKlgOrgresource m : express) {
            if (helperService.pickTargetWordOneword(m.getSentence(), m.getKnowledgename(), "") == 1) {
                if ("".equals(wodr2)) {
                    wodr2 += helperService.pickUpTargetWord(m) + "|";
                    //单个小题素材的长度不超过15个单词
                    String textFromTHML = helperService.getTextFromTHML(m.getSentence());
                    int mlength = textFromTHML.split(" ").length;
                    if (helperService.pickTargetWordOneword(m.getSentence(), m.getKnowledgename(), "") == 1) {
                        if (mlength < 15) {
                            listm15Express.add(m);
                        }
                    }
                } else {
                    if (helperService.pickUpTargetWord(m)!=null && !wodr2.contains(helperService.pickUpTargetWord(m))) {
                        wodr2 += helperService.pickUpTargetWord(m) + "|";
                        //单个小题素材的长度不超过15个单词
                        String textFromTHML = helperService.getTextFromTHML(m.getSentence());
                        int mlength = textFromTHML.split(" ").length;
                        if (helperService.pickTargetWordOneword(m.getSentence(), m.getKnowledgename(), "") == 1) {
                            if (mlength < 15) {
                                listm15Express.add(m);
                            }
                        }
                    }
                }
            }
        }
    }

    private void exit01(StringBuilder knowledgelist, ArrayList<LgdbBKlgOrgresource> listmN203NinAndIn, LgdbBKlgOrgresource m, int mlength, ArrayList<LgdbBKlgOrgresource> listmN203Nin) {
        if (helperService.interLengthLessFive(mlength, listmN203NinAndIn) == 0 && !knowledgelist.toString().contains(m.getKnowledgename())) {
            knowledgelist.append(m.getKnowledgename()).append("|");
            String knowledgename = m.getKnowledgename();
            if (knowledgename.split(" ").length > 1) {
                System.out.println(knowledgename);
            }
            listmN203NinAndIn.add(m);
            listmN203Nin.add(m);
        }
    }

    //多条素材编一道试题
    public ArrayList<ArrayList<String>> spliceMNContent(String Stage, ArrayList<String> listQuesGenre, String quesPoint, String property) {
        System.out.println("开始编题******spliceMNContent******开始编题***********spliceMNContent**************");
        ArrayList<ArrayList<String>> queryResponseResults = new ArrayList<>();
        for (String aItem : listQuesGenre) {
            switch (aItem) {
                case "15":
                    //匹配题  词汇匹配,m,15,无,英语单词英语词组
                    if ("英语单词英语词组".equals(quesPoint)) {
                        while (true) {
                            if (listm15Express.size() > 2 && listm15Word.size() > 3) {
                                ArrayList<String> strings = null;
                                try {
                                    strings = quesTypeAService.quesTypem15(listm15Express, listm15Word, Stage, TaskId, mainQueryService);
                                    listm15Express = (ArrayList<LgdbBKlgOrgresource>) listm15Express.stream().filter(s -> "".equals(helperService.getQuesTypeService(s.getId(), Stage))).collect(Collectors.toList());
                                    listm15Word = (ArrayList<LgdbBKlgOrgresource>) listm15Word.stream().filter(s -> "".equals(helperService.getQuesTypeService(s.getId(), Stage))).collect(Collectors.toList());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                if (strings!=null &&  strings.size() != 0) {
                                    queryResponseResults.add(strings);
                                }
                            } else {
                                break;
                            }
                        }
                    }
                    break;
                case "16":
                    //匹配题  对话匹配,m,16,无,英语常用表达
                    if ("英语常用表达".equals(quesPoint)) {
                        while (true) {
                            if (listm166.size() != 0) {
                                ArrayList<String> strings = null;
                                try {
                                    strings = quesTypeAService.quesTypem16(listm166, Stage, TaskId, mainQueryService);
                                    listm166 = (ArrayList<LgdbBKlgOrgresource>) listm166.stream().filter(s -> !helperService.getQuesTypeService(s.getId(), Stage).contains("m16")).collect(Collectors.toList());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                if (strings!=null && strings.size() != 0) {
                                    queryResponseResults.add(strings);
                                }
                            } else {
                                break;
                            }
                        }
                    }
                    break;
                case "20":
                    //'选词填空,N,20,复合属性,英语单词'
                    if ("复合属性".equals(property)) {
                        ArrayList<String> strings = null;
                        for (ArrayList<LgdbBKlgOrgresource> m : arrayListHashMap201.values()) {
                            try {
                                strings = quesTypeAService.quesTypeN201(m, Stage, TaskId, mainQueryService);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (strings!=null &&  strings.size() != 0) {
                                queryResponseResults.add(strings);
                            }
                        }
                    } else if ("动词属性".equals(property)) {
                        //选词填空,N,20,动词属性,英语单词
                        String QuesType = "N202";
                        ArrayList<LgdbBKlgOrgresource> n202 = (ArrayList<LgdbBKlgOrgresource>) listmN202.stream().filter(s -> !helperService.getQuesTypeService(s.getId(), Stage).contains("N201"))
                                .filter(s -> helperService.pickTargetWordOneword(s.getSentence(), s.getKnowledgename(), "") == 1)
                                .filter(s -> "".equals(helperService.getQuesTypeService(s.getId(), Stage))).collect(Collectors.toList());
                        while (true) {
                            if (n202.size() > 5) {
                                ArrayList<String> strings = null;
                                try {
                                    strings = quesTypeAService.quesTypeN202(n202, Stage, TaskId, mainQueryService);
                                    n202 = (ArrayList<LgdbBKlgOrgresource>) n202.stream().filter(s -> !helperService.getQuesTypeService(s.getId(), Stage).contains("N201"))
                                            .filter(s -> helperService.pickTargetWordOneword(s.getSentence(), s.getKnowledgename(), "") == 1)
                                            .filter(s -> "".equals(helperService.getQuesTypeService(s.getId(), Stage))).collect(Collectors.toList());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                if (strings!=null && strings.size() != 0) {
                                    queryResponseResults.add(strings);
                                }
                            } else {
                                break;
                            }
                        }
                    } else if ("动词复合属性".equals(property)) {
                        //选词填空,N,20,动词复合属性,英语单词
                       // String QuesType = "N203";
                        ArrayList<LgdbBKlgOrgresource> listIn = helperService.randomSortList1(listmN203In);
                        ArrayList<LgdbBKlgOrgresource> n203In = (ArrayList<LgdbBKlgOrgresource>) listIn.stream().filter(s -> !helperService.getQuesTypeService(s.getId(), Stage).contains("N203"))
                                .filter(s -> helperService.pickTargetWordOneword(s.getSentence(), s.getKnowledgename(), "") == 1)
                                .filter(s -> "".equals(helperService.getQuesTypeService(s.getId(), Stage))).collect(Collectors.toList());

                        ArrayList<LgdbBKlgOrgresource> listNin = helperService.randomSortList1(listmN203Nin);
                        ArrayList<LgdbBKlgOrgresource> n203Nin = (ArrayList<LgdbBKlgOrgresource>) listNin.stream().filter(s -> !helperService.getQuesTypeService(s.getId(), Stage).contains("N203"))
                                .filter(s -> helperService.pickTargetWordOneword(s.getSentence(), s.getKnowledgename(), "") == 1)
                                .filter(s -> "".equals(helperService.getQuesTypeService(s.getId(), Stage))).collect(Collectors.toList());


                        while (true) {
                            if (n203In.size() != 0 && n203Nin.size() > 4) {
                                ArrayList<String> strings = null;
                                try {
                                    strings = quesTypeAService.quesTypeN203(n203In, n203Nin, Stage, TaskId, mainQueryService);

                                    n203In = (ArrayList<LgdbBKlgOrgresource>) n203In.stream().filter(s -> !helperService.getQuesTypeService(s.getId(), Stage).contains("N203"))
                                            .filter(s -> helperService.pickTargetWordOneword(s.getSentence(), s.getKnowledgename(), "") == 1)
                                            .filter(s -> "".equals(helperService.getQuesTypeService(s.getId(), Stage))).collect(Collectors.toList());

                                    n203Nin = (ArrayList<LgdbBKlgOrgresource>) n203Nin.stream().filter(s -> !helperService.getQuesTypeService(s.getId(), Stage).contains("N203"))
                                            .filter(s -> helperService.pickTargetWordOneword(s.getSentence(), s.getKnowledgename(), "") == 1)
                                            .filter(s -> "".equals(helperService.getQuesTypeService(s.getId(), Stage))).collect(Collectors.toList());


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                assert strings != null;
                                if (strings.size() != 0) {
                                    queryResponseResults.add(strings);
                                }
                            } else {
                                break;
                            }
                        }
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + aItem);
            }
        }
        return queryResponseResults;
    }

    //一条素材编一道试题
    public ArrayList<ArrayList<String>> EditStart(String Stage, ArrayList<String> listQuesGenre, String quesPoint, String property) {
        System.out.println("开始编题******EditStart******开始编题***********EditStart**************");

        ArrayList<ArrayList<String>> queryResponseResults = new ArrayList<>();
        for (String aItem : listQuesGenre) {
            switch (aItem) {
                case "7":
                    //连词成句,V,7,无,英语单词英语词组
                    listLV.forEach(m -> {
                        ArrayList<String> strings = null;
                        try {
                            strings = quesTypeAService.quesTypeV0(m, Stage, TaskId, mainQueryService);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        assert strings != null;
                        if (strings.size() != 0) {
                            queryResponseResults.add(strings);
                        }
                    });
                    break;

                case "13":
                    if ("英语单词英语词组".equals(quesPoint)) {
                        if("B".equals(Stage)){
                            listAgV13wordB.forEach(m -> {
                                //if (!helperService.getQuesTypeService(m.getId(), Stage).contains("A132")) {
                                    ArrayList<String> strings = null;
                                    try {
                                        strings = quesTypeAService.quesTypeA13word(m, Stage, TaskId, mainQueryService);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                assert strings != null;
                                if (strings.size() != 0) {
                                        queryResponseResults.add(strings);
                                    }
                                //}
                            });
                        } else if("C".equals(Stage)){
                            listAgV13wordC.forEach(m -> {
                                ArrayList<String> strings = null;
                                try {
                                    strings = quesTypeAService.quesTypeA13wordC(m, Stage, TaskId, mainQueryService);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                assert strings != null;
                                if (strings.size() != 0) {
                                    queryResponseResults.add(strings);
                                }
                            });
                        }

                    } else if ("英语常用表达".equals(quesPoint)) {
                        if("B".equals(Stage)){
                            listAgV13expressB.forEach(m -> {
                                if (!helperService.getQuesTypeService(m.getId(), Stage).contains("A131")) {
                                    ArrayList<String> strings = null;
                                    try {
                                        strings = quesTypeAService.quesTypeA13express(m, Stage, TaskId, mainQueryService);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    if (strings!=null && strings.size() != 0) {
                                        queryResponseResults.add(strings);
                                    }
                                }
                            });
                        }else if("C".equals(Stage)){
                            listAgV13expressC.forEach(m -> {
                                //if (!helperService.getQuesTypeService(m.getId(), Stage).contains("A131")) {
                                    ArrayList<String> strings = null;
                                    try {
                                        strings = quesTypeAService.quesTypeA13expressC(m, Stage, TaskId, mainQueryService);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    if (strings!=null && strings.size() != 0) {
                                        queryResponseResults.add(strings);
                                    }
                               // }
                            });
                        }
                    }
                    break;

                case "21":
                    if ("无提示".equals(property)) {
                        if ("B".equals(Stage)) {
                            listmN212B.forEach(m -> {
                                if (!helperService.getQuesTypeService(m.getId(), Stage).contains("N211")) {
                                    ArrayList<String> strings = null;
                                    try {
                                        strings = quesTypeAService.quesTypeN211(m, Stage, TaskId, mainQueryService);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    if (strings!=null && strings.size() != 0) {
                                        queryResponseResults.add(strings);
                                    }
                                }
                            });
                        } else if ("C".equals(Stage)) {
                            listmN212C.forEach(m -> {
                                //if (!helperService.getQuesTypeService(m.getId(), Stage).contains("N211")) {
                                    ArrayList<String> strings = null;
                                    try {
                                        strings = quesTypeAService.quesTypeN211C(m, Stage, TaskId, mainQueryService);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    if (strings!=null && strings.size() != 0) {
                                        queryResponseResults.add(strings);
                                    }
                               // }
                            });
                        }

                    } else if ("首字母提示".equals(property)) {
                        if ("B".equals(Stage)) {
                            listmN213B.forEach(m -> {
                                if (!helperService.getQuesTypeService(m.getId(), Stage).contains("N212")) {
                                    ArrayList<String> strings = null;
                                    try {
                                        strings = quesTypeAService.quesTypeN212(m, Stage, TaskId, mainQueryService);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    if (strings!=null && strings.size() != 0) {
                                        queryResponseResults.add(strings);
                                    }
                                }
                            });
                        } else if ("C".equals(Stage)) {
                            listmN213C.forEach(m -> {
                                //if (!helperService.getQuesTypeService59(m.getId(), Stage).contains("N212")) {
                                    ArrayList<String> strings = null;
                                    try {
                                        strings = quesTypeAService.quesTypeN212C(m, Stage, TaskId, mainQueryService);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    if (strings!=null && strings.size() != 0) {
                                        queryResponseResults.add(strings);
                                    }
                               // }
                            });
                        }
                    } else if ("汉语提示".equals(property)) {
                        //句子填空,N,21,汉语提示,英语单词
                        if ("B".equals(Stage)) {
                            listmN211B.forEach(m -> {
                                if (!helperService.getQuesTypeService(m.getId(), Stage).contains("N213")) {
                                    ArrayList<String> strings = null;
                                    try {
                                        strings = quesTypeAService.quesTypeN213(m, Stage, TaskId, mainQueryService);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    if (strings!=null && strings.size() != 0) {
                                        queryResponseResults.add(strings);
                                    }
                                }
                            });
                        } else if ("C".equals(Stage)) {
                            listmN211C.forEach(m -> {
                              //  if (!helperService.getQuesTypeService(m.getId(), Stage).contains("N213")) {
                                    ArrayList<String> strings = null;
                                    try {
                                        strings = quesTypeAService.quesTypeN213C(m, Stage, TaskId, mainQueryService);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    if (strings!=null && strings.size() != 0) {
                                        queryResponseResults.add(strings);
                                    }
                               // }
                            });
                        }
                    } else if ("音标提示".equals(property)) {
                        if ("B".equals(Stage)) {
                            listmN214B.forEach(m -> {
                                if (!helperService.getQuesTypeService(m.getId(), Stage).contains("N214")) {
                                    ArrayList<String> strings = null;
                                    try {
                                        strings = quesTypeAService.quesTypeN214(m, Stage, TaskId, mainQueryService);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    if (strings!=null && strings.size() != 0) {
                                        queryResponseResults.add(strings);
                                    }
                                }
                            });
                        } else if ("C".equals(Stage)) {
                            listmN214C.forEach(m -> {
                                if (!helperService.getQuesTypeService(m.getId(), Stage).contains("N214")) {
                                    ArrayList<String> strings = null;
                                    try {
                                        strings = quesTypeAService.quesTypeN214C(m, Stage, TaskId, mainQueryService);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    if (strings!=null && strings.size() != 0) {
                                        queryResponseResults.add(strings);
                                    }
                                }
                            });
                        }
                    } else if ("单词提示".equals(property)) {
                        //词汇填空题，单词提示   句子填空,N,21,单词提示,英语单词
                        if ("B".equals(Stage)) {
                            listmN215B.forEach(m -> {
                                if (!helperService.getQuesTypeService(m.getId(), Stage).contains("N215")) {
                                    ArrayList<String> strings = null;
                                    try {
                                        strings = quesTypeAService.quesTypeN215(m, Stage, TaskId, mainQueryService);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    if (strings!=null && strings.size() != 0) {
                                        queryResponseResults.add(strings);
                                    }
                                }
                            });
                        } else if ("C".equals(Stage)) {
                            listmN215C.forEach(m -> {
                               // if (!helperService.getQuesTypeService(m.getId(), Stage).contains("N215")) {
                                    ArrayList<String> strings = null;
                                    try {
                                        strings = quesTypeAService.quesTypeN215C(m, Stage, TaskId, mainQueryService);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    if (strings!=null && strings.size() != 0) {
                                        queryResponseResults.add(strings);
                                    }
                                //}
                            });
                        }
                    } else if ("首字母+汉语".equals(property)) {
                        //词汇填空题，首字母+汉语    句子填空,N,21,首字母+汉语,英语单词
                        if ("B".equals(Stage)) {
                            listmN216B.forEach(m -> {
                                if (!helperService.getQuesTypeService(m.getId(), Stage).contains("N216")) {
                                    ArrayList<String> strings = null;
                                    try {
                                        strings = quesTypeAService.quesTypeN216(m, Stage, TaskId, mainQueryService);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    if (strings!=null && strings.size() != 0) {
                                        queryResponseResults.add(strings);
                                    }
                                }
                            });
                        } else if ("C".equals(Stage)) {
                            listmN216C.forEach(m -> {
                                //if (!helperService.getQuesTypeService(m.getId(), Stage).contains("N216")) {
                                    ArrayList<String> strings = null;
                                    try {
                                        strings = quesTypeAService.quesTypeN216C(m, Stage, TaskId, mainQueryService);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    if (strings!=null && strings.size() != 0) {
                                        queryResponseResults.add(strings);
                                    }
                               // }
                            });
                        }
                    }

                    break;

                case "22":
                    //翻译填空,N,22,无,无
                    if ("B".equals(Stage)) {
                        listmN213B.forEach(m -> {
                            if (!helperService.getQuesTypeService(m.getId(), Stage).contains("N222")) {
                                ArrayList<String> strings = null;
                                try {
                                    strings = quesTypeAService.quesTypeN22(m, Stage, TaskId, mainQueryService);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                if (strings!=null && strings.size() != 0) {
                                    queryResponseResults.add(strings);
                                }
                            }
                        });
                    } else if ("C".equals(Stage)) {
                        listmN213C.forEach(m -> {
                           // if (!helperService.getQuesTypeService(m.getId(), Stage).contains("N222")) {
                                ArrayList<String> strings = null;
                                try {
                                    strings = quesTypeAService.quesTypeN22C(m, Stage, TaskId, mainQueryService);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                if (strings!=null && strings.size() != 0) {
                                    queryResponseResults.add(strings);
                                }
                          // }
                        });
                    }

                    break;

                case "38":
                    //英译中,g,38,句子无,英语单词英语词组
                    listAgVV.forEach(m -> {
                        ArrayList<String> strings = null;
                        try {
                            strings = quesTypeAService.quesTypegV38(m, Stage, TaskId, mainQueryService);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (strings!=null && strings.size() != 0) {
                            queryResponseResults.add(strings);
                        }
                    });
                    break;

                case "40":
                    //句子中译英,g,40,无,英语单词英语词组
                    listAgVV.forEach(m -> {
                        ArrayList<String> strings = null;
                        try {
                            strings = quesTypeAService.quesTypegV40(m, Stage, TaskId, mainQueryService);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (strings!=null && strings.size() != 0) {
                            queryResponseResults.add(strings);
                        }
                    });
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + aItem);
            }
        }
        return queryResponseResults;
    }
}








