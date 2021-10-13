package com.lancoo.utils;

import org.w3c.dom.Document;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;

/**
 * @author KCWang
 * @date 2021/8/2 20:01
 * @Email:KCWang@aliyun.com
 */

public class Xml2StringUtil {

        public static String XmlToString(Document doc, String propertyName, String progertyValue){
                Transformer transformer = null;
                try {
                transformer = TransformerFactory.newInstance().newTransformer();
                } catch (TransformerConfigurationException e) {
                e.printStackTrace();
                return null;
                } catch (TransformerFactoryConfigurationError e) {
                e.printStackTrace();
                return null;
                }
//                transformer.setOutputProperty("encoding","utf-8");
                transformer.setOutputProperty(propertyName, progertyValue);
                DOMSource domSource = new DOMSource(doc);
                StreamResult streamResult = new StreamResult();

                ByteArrayOutputStream os = new ByteArrayOutputStream();
                streamResult.setOutputStream(os);
                try {
                transformer.transform(domSource, streamResult);
                } catch (TransformerException e) {
                e.printStackTrace();
                return null;
                } finally {
                try {
                os.flush();
                os.close();
                } catch (Exception e2) {
                e2.printStackTrace();
                }
                }

                return os.toString();
                }
}



