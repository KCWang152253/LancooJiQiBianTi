package com.lancoo.http.utils;

import java.io.StringReader;
import java.util.*;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

public class XmlUtil {
    /**
     * xml字符串转换成Map
     * 获取标签内属性值和text值
     *
     * @param xml
     * @return
     * @throws Exception
     */
    public static List<Map<String, String>> xmlToMap(String xml) throws Exception {
        StringReader reader = new StringReader(xml);
        InputSource source = new InputSource(reader);
        SAXReader sax = new SAXReader(); // 创建一个SAXReader对象
        Document document = sax.read(source); // 获取document对象,如果文档无节点，则会抛出Exception提前结束
        Element root = document.getRootElement(); // 获取根节点
        List<Element> listElement = root.elements();
        List<Map<String, String>> maps = new ArrayList<>(listElement.size());
        for (Element element : listElement){
            maps.add(XmlUtil.getNodes(element));
        }
        return maps;
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
}
