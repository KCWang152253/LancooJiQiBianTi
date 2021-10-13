package com.lancoo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author KCWang
 * @date 2021/8/8 9:43
 * @Email:KCWang@aliyun.com
 */

public class MD5Util {

    /**
     * 1.一个运用基本类的实例
     * MessageDigest 对象开始被初始化。该对象通过使用 update 方法处理数据。
     * 任何时候都可以调用 reset 方法重置摘要。
     * 一旦所有需要更新的数据都已经被更新了，应该调用 digest 方法之一完成哈希计算。
     * 对于给定数量的更新数据，digest 方法只能被调用一次。
     * 在调用 digest 之后，MessageDigest 对象被重新设置成其初始状态。
     */
    public String encrypByMd5(String context) {
        StringBuffer buf = new StringBuffer("");
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(context.getBytes());//update处理
            byte[] encryContext = md.digest();//调用该方法完成计算
            int i;
            for (int offset = 0; offset < encryContext.length; offset++) {//做相应的转化（十六进制）
                i = encryContext[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                //字符串转化成为16进制字符串
                buf.append(Integer.toHexString(i));
            }
            System.out.println("32位加密处理结果result: " + buf.toString());// 32位的加密
            System.out.println("16位加密处理结果result: " + buf.toString().substring(8, 24));// 16位的加密
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return buf.toString();
    }
}
