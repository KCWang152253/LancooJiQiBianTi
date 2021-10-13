package com.lancoo.utils;

import java.io.UnsupportedEncodingException;

/**
 * url转码、解码
 *
 * 
 * @date 2021-7-7 下午04:09:35
 */
public class UrlUtil {
    private final static String ENCODE = "utf-8";

    /**
     * URL 解码
     *
     * @return String
     * 
     * @date 2021-7-7 下午04:09:51
     */
    public static String getURLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * URL 转码
     *
     * @return String
     * 
     * @date 2021-7-7 下午04:10:28
     */
    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}