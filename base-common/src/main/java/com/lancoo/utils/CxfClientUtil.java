package com.lancoo.utils;


import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

/**
 * @author KCWang
 * @date 2021/8/17 15:24
 * @Email:KCWang@aliyun.com
 */

public class CxfClientUtil {


//    public static void main(String[] args) throws Exception {
//        webServiceTest( content, type,  gener);
//    }

    public static String webServiceTest(String content1,String type1, String gener1) throws Exception {
        String content = content1;
        String type = type1;
        String gener = gener1;
        //1.创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://60.190.136.238:32305/Do_AutoEditWS.asmx?wsdl");
        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME,PASS_WORD));

        //ZhongTai_CAL_MARA为方法名字，后面的是可变参数(invoke("方法名",参数1,参数2,参数3....);)
        Object[] objects = client.invoke("StdnQues", content,type,gener);
        String s = JsonUtils.objectToJson(objects);
        String s1 = s.substring(2,s.length()-2);

        //输出调用结果
        System.out.println(s);
        return s1;

    }
}
