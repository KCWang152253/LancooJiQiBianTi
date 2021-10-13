package com.lancoo.http.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author KCWang
 * @date 2021/8/17 21:10
 * @Email:KCWang@aliyun.com
 */

public class InterfaceRequest {


    public static String sendPost(String url,String jsonBody) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type","application/json");
        StringEntity stringEntity = new StringEntity(jsonBody, StandardCharsets.UTF_8);
        post.setEntity(stringEntity);
        HttpResponse response = httpClient.execute(post);
        return EntityUtils.toString(response.getEntity(),"UTF-8");
    }


//    public static String getresult(String QuesType1, String genre1, String Content1) throws IOException {
//        String body = "";
//        //创建httpclient对象
//        CloseableHttpClient client = HttpClients.createDefault();
//        //创建post方式请求对象
//        HttpPost httpPost = new HttpPost("http://172.16.42.59:32309/api/StdnQues/StandardQues");
//
//
//        //拼接多参数
//        JSONObject json = new JSONObject();
//        json.put("QuesContent", Content1);
//        json.put("QuesType", QuesType1);
//        json.put("QuesGenre", genre1);
//
//        //装填参数
//        StringEntity s = new StringEntity(json.toString(), "utf-8");
//        s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
//                "application/json"));
//        //设置参数到请求对象中
//        httpPost.setEntity(s);
//        System.out.println("请求地址："+"http://172.16.42.59:32309/api/StdnQues/StandardQues");
//        //System.out.println("请求参数："+nvps.toString());
//
//        //设置header信息
//        //指定报文头【Content-type】、【User-Agent】
//        //httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
//        httpPost.setHeader("Content-type", "application/json");
//        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
//
//        //执行请求操作，并拿到结果（同步阻塞）
//        CloseableHttpResponse response = client.execute(httpPost);
//        //获取结果实体
//        HttpEntity entity = response.getEntity();
//        if (entity != null) {
//            //按指定编码转换结果实体为String类型
//            body = EntityUtils.toString(entity, "UTF-8");
//        }
//        EntityUtils.consume(entity);
//        //释放链接
//        response.close();
//        return body;
//
//    }


}
