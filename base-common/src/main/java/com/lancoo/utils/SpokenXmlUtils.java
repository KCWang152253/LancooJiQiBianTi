package com.lancoo.utils;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.List;

public class SpokenXmlUtils {
    public static String getCompareStr(String inStr){
        try {
            Document document= DocumentHelper.parseText(inStr);//获取Document对象
            String needCompare="";

            Element root = document.getRootElement();
            System.out.println("<"+root.getName()+">");
            List<Element> elements = root.elements();
            for (Element e : elements) {
                // System.out.print("\t<1"+e.getName());
//                List<Attribute> attributes = e.attributes();
//                for (Attribute a : attributes) {
//                  //  System.out.print(" "+a.getName()+"=\""+a.getValue()+"\"");
//                }
//                System.out.println(">");
                List<Element> elements2 = e.elements();
                for (Element e2 : elements2) {
                    //  System.out.print("\t\t<2"+e2.getName());
                    List<Element> elements3 = e2.elements();
                    for (Element e3 :elements3){
                        //   System.out.print("\t<3"+e3.getName());
                        List<Element> elements4 = e3.elements();
                        for (Element e4:elements4){
                            //   System.out.print("\t<4"+e4.getName());
                            List<Element> elements5=e4.elements();
                            for (Element e5:elements5){
                                //   System.out.print("\t<5"+e5.getName());
                                if ("media".equals(e5.getName())){
                                    List<Element> elementsMedia=e5.elements();
                                    needCompare+=elementsMedia.get(0).getText();
                                    //  System.out.println(elementsMedia.get(0).getText());
                                }
                                if ("display".equals(e5.getName())){
                                    //System.out.println(e5.getText());
                                    needCompare+=e5.getText();
                                }
//                                if ("answerList".equals(e5.getName())){
//                                    List<Element> elements1Answer=e5.elements();
//                                    for (Element eAnswer:elements1Answer){
//
//                                    }
//                                    System.out.println();
//                                }
                            }
                        }
                    }


                }
            }


            needCompare=needCompare
                    .replace("<P>"," ")
                    .replace("</P>"," ")
                    .replace("<p>"," ")
                    .replace("</p>"," ")
                    .replace("<p style= text-align justify >"," ")
                    .replace("&ldquo"," ")
                    .replace("&rdquo"," ")
                    .replace("&middot"," ")
                    .replace("、"," ")
                    .replace("。"," ")
                    .replace(":"," ")
                    .replace(";"," ")
                    .replace(","," ")
                    .replace("."," ")
                    .replace("，"," ")
                    .replace("；"," ")
                    .replace("："," ")
                    .replace("("," ")
                    .replace(")"," ")
                    .replace("（"," ")
                    .replace("）"," ")
                    .replace("?"," ")
                    .replace("!"," ")
                    .replace("\""," ")
                    .replace("  "," ")
                    .replace("  "," ")
            ;
            return needCompare;
        }catch (Exception e){
            e.printStackTrace();
        }

       return "";

    }
}
