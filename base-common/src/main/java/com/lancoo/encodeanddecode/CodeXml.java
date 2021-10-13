package com.lancoo.encodeanddecode;

import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;


import java.io.UnsupportedEncodingException;

/**
 * @author KCWang
 * @date 2021/9/24 17:04
 * @Email:KCWang@aliyun.com
 */

public class CodeXml {


    public static String DecodeXml(String orgXml) throws UnsupportedEncodingException {
        if (orgXml == null ||  orgXml=="") {
            return null;
        }
        //解析html,保留大小写
        Parser parser = Parser.htmlParser();
        parser.settings(new ParseSettings(true, true));
        org.jsoup.nodes.Document gbk = parser.parseInput(orgXml, "utf-8");
        String text = gbk.text();
        String s = DecodeField(text);

//        org.jsoup.nodes.Document document = parser.parseInput(s, "utf-8");
        return s;
    }

    public static String DecodeField(String orgStr) throws UnsupportedEncodingException {
        return CodeStr.Lg_DecodeString(orgStr, 798989868);
    }

    //xml加密　　
//    public XmlDocument EncodeXml(XmlDocument orgXml)
//    {
//        if (orgXml == null)
//        {
//            return null;
//        }
//        var nXml = new XmlDocument();
//        var root = orgXml.DocumentElement.Name;
//        var orgStr = orgXml.InnerXml;
//        var nStr = EncodeField(orgStr);
//        var el = nXml.CreateElement("lgtke");
//        el.InnerText = nStr;
//        nXml.AppendChild(el);
//        return nXml;
//    }


//    public string EncodeField(string orgStr)
//    {
//        return Code.Lg_EncodeString(orgStr);
//    }

}
