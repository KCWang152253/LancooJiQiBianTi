package com.lancoo.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.*;

public class XmlUtils {
    /**
     * xml字符串转换成Map
     * 获取标签内属性值和text值
     *
     * @param xml
     * @return
     * @throws Exception
     */
    public static Map<String, String> xmlToMap(String xml) {
        StringReader reader = new StringReader(xml);
        InputSource source = new InputSource(reader);
        SAXReader sax = new SAXReader(); // 创建一个SAXReader对象
        Document document = null; // 获取document对象,如果文档无节点，则会抛出Exception提前结束
        try {
            document = sax.read(source);
        } catch (DocumentException e) {
            return null;
        }
        Element root = document.getRootElement(); // 获取根节点
        List<Element> listElement = root.elements();
        Map<String, String> map = new HashMap<>();
        for (Element element : listElement){
            map.put(element.getName(),element.getStringValue());
        }
        return map;
    }

    /**
     * 从指定节点开始,递归遍历所有子节点
     *
     * @author chenleixing
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> getNodes(Element node) {
        Map<String, String> xmlMap = new HashMap<>();
        List<Attribute> listAttr = node.attributes(); // 当前节点的所有属性的list
        for (Attribute attr : listAttr) { // 遍历当前节点的所有属性
            String name = attr.getName(); // 属性名称
            String value = attr.getValue(); // 属性的值
            xmlMap.put(name, value.trim());
        }
        xmlMap.putAll(xml2mapWithOutAttr(node));
        // 递归遍历当前节点所有的子节点
        /*List<Element> listElement = node.elements(); // 所有一级子节点的list
        for (Element e : listElement) { // 遍历所有一级子节点
            XmlUtil2.getNodes(e); // 递归
        }*/
        return xmlMap;

    }
    /**
     * xml转map 不带属性
     * @param e
     * @return
     */
    private static Map xml2mapWithOutAttr(Element e) {
        Map map = new LinkedHashMap();
        List list = e.elements();
        if (list.size() > 0) {
            for (Object o : list) {
                Element iter = (Element) o;
                List mapList = new ArrayList();

                if (iter.elements().size() > 0) {
                    Map m = xml2mapWithOutAttr(iter);
                    if (map.get(iter.getName()) != null) {
                        Object obj = map.get(iter.getName());
                        if (!(obj instanceof List)) {
                            mapList = new ArrayList();
                            mapList.add(obj);
                            mapList.add(m);
                        }
                        if (obj instanceof List) {
                            mapList = (List) obj;
                            mapList.add(m);
                        }
                        map.put(iter.getName(), mapList);
                    } else
                        map.put(iter.getName(), m);
                } else {
                    if (map.get(iter.getName()) != null) {
                        Object obj = map.get(iter.getName());
                        if (!(obj instanceof List)) {
                            mapList = new ArrayList();
                            mapList.add(obj);
                            mapList.add(iter.getText());
                        }
                        if (obj instanceof List) {
                            mapList = (List) obj;
                            mapList.add(iter.getText());
                        }
                        map.put(iter.getName(), mapList);
                    } else
                        map.put(iter.getName(), iter.getText());
                }
            }
        } else
            map.put(e.getName(), e.getText());
        return map;
    }
    /**
     * xml转json
     * @param xmlStr
     * @return
     * @throws DocumentException
     */
    public static JSONObject xml2Json(String xmlStr) throws DocumentException{
        Document doc= DocumentHelper.parseText(xmlStr);
        JSONObject json=new JSONObject();
        dom4j2Json(doc.getRootElement(), json);
        return json;
    }
    /**
     * xml转json
     * @param element
     * @param json
     */
    public static void dom4j2Json(Element element,JSONObject json){
        //如果是属性
        for(Object o:element.attributes()){
            Attribute attr=(Attribute)o;
            if(!isEmpty(attr.getValue())){
                json.put(attr.getName(), attr.getValue());
            }
        }
        List<Element> chdEl=element.elements();
        if(chdEl.isEmpty()&&!isEmpty(element.getText())){//如果没有子元素,只有一个值
            json.put(element.getName(), element.getText());
        }

        for(Element e:chdEl){//有子元素
            if(!e.elements().isEmpty()){//子元素也有子元素
                JSONObject chdjson=new JSONObject();
                dom4j2Json(e,chdjson);
                Object o=json.get(e.getName());
                if(o!=null){
                    JSONArray jsona=null;
                    if(o instanceof JSONObject){//如果此元素已存在,则转为jsonArray
                        JSONObject jsono=(JSONObject)o;
                        json.remove(e.getName());
                        jsona=new JSONArray();
                        jsona.add(jsono);
                        jsona.add(chdjson);
                    }
                    if(o instanceof JSONArray){
                        jsona=(JSONArray)o;
                        jsona.add(chdjson);
                    }
                    json.put(e.getName(), jsona);
                }else{
                    if(!chdjson.isEmpty()){
                        json.put(e.getName(), chdjson);
                    }
                }


            }else{//子元素没有子元素
                for(Object o:element.attributes()){
                    Attribute attr=(Attribute)o;
                    if(!isEmpty(attr.getValue())){
                        json.put(attr.getName(), attr.getValue());
                    }
                }
                if(!e.getText().isEmpty()){
                    json.put(e.getName(), e.getText());
                }
            }
        }
    }
    public static boolean isEmpty(String str) {

        if (str == null || str.trim().isEmpty() || "null".equals(str)) {
            return true;
        }
        return false;
    }
}
