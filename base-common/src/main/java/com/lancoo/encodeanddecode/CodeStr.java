package com.lancoo.encodeanddecode;

import java.io.UnsupportedEncodingException;

/**
 * @author KCWang
 * @date 2021/9/24 17:05
 * @Email:KCWang@aliyun.com
 */

public class CodeStr {

    public static String Lg_DecodeString(String SourceString, int iFlag) throws UnsupportedEncodingException {
        //字符串解密
        String SourceStr = "";
        String DestStr = "";
        SourceStr = SourceString;
        int nLen = SourceStr.length();
        int i;
        int c, c1;
        int cc, cc1;
        if (nLen == 0) return DestStr;
        if (iFlag != 798989868) return SourceString;
        //byte[] array = new byte[1];   //定义一组数组array
        //array = System.Text.Encoding.ASCII.GetBytes(SourceString); //string转换的字母
        //c = 'L' + 88;
        //c1 = 'G' + 88;
        c = '';
        if (nLen == 1) return SourceString;
        if (nLen >= 2) {
            cc = SourceString.charAt(0);
            //cc1 = SourceString[1];
            if (cc == c) {
                //源串已经加密过
                cc = 30;
                for (i = 1; i < nLen; i++) {
                    c = SourceString.charAt(i);
                    if (c <= 255) {
                        if (c != 13) {
                            if (i == (nLen - 1) && c == 255) {
                                c1 = 255;
                                DestStr += (char)c1;
                                break;
                            }
                            //第78位与第56位交换，第12位与第34位交换
                            //c1=((c & 48)<<2)|((c & 192)>>2)|((c & 3)<<2)|((c & 12)>>2);
                            c1 = (c & 0xf0) | ((c & 3) << 2) | ((c & 12) >> 2);
                            //c1=(c & 0xC0)|((c & 0x10)<<1)|((c & 0x20)>>1)|((c & 3)<<2)|((c & 12)>>2);
                            //c1= (c1+cc)%256;	//和前1位已加密字符相加取模
                            //cc=c1;	//记住前1位已加密字符
                            DestStr += (char)c1;
                        }
                    } else {
                        //第78位与第56位交换，第12位与第34位交换
//                        System.Text.Encoding GB2312 = System.Text.Encoding.GetEncoding("GB2312");
                        String ss;
                        ss = "" + (char)c;
                        byte[] gb = ss.getBytes("GB2312");
//                        byte[] gb = GB2312.GetBytes(ss);
                        //cc = (c & 0x0ff);
                        cc = gb[0];
                        gb[0] = (byte)((cc & 0xf0) + ((cc & 3) << 2) + ((cc & 12) >> 2));
                        //cc = (c & 0xff00)/256;
                        if(gb.length>1){
                            cc = gb[1];
                            gb[1] = (byte)((cc & 0xf0) + ((cc & 3) << 2) + ((cc & 12) >> 2));
                        }

                        //c1 = c1 + (cc1 << 8);
//                        DestStr += GB2312.GetString(gb);
//                        gb.toString().getBytes("GB2312");
                        DestStr += new String(gb,"GB2312");
                    }
                }
            } else {
                DestStr = SourceString;
            }    //未加密的返回源串
        }
        return DestStr;
    }

    //字符串加密
//    public static string Lg_EncodeString(string SourceString)
//    {
//        string SourceStr;
//        string DestStr;
//        SourceStr = SourceString;
//        int nLen = SourceStr.Length;
//        int i;
//        int c, c1;
//        int cc, cc1;
//        DestStr = "";
//        if (nLen == 0) return DestStr;
//        //byte[] array = new byte[1];   //定义一组数组array
//        //array = System.Text.Encoding.ASCII.GetBytes(SourceString); //string转换的字母
//        //c = 'L' + 88;
//        //c1 = 'G' + 88;
//        //if (nLen >= 2)
//        //{
//        //    cc = SourceString[0];
//        //    cc1 = SourceString[1];
//        //    if (cc == c && cc1 == c1)
//        //    {	//源串已经加密过
//        //        return SourceString;	//返回源串
//        //    }
//        //}
//        c = '';
//        try
//        {
//            if (nLen >= 1)
//            {
//                cc = SourceString[0];
//                if (cc == c)
//                {   //源串已经加密过
//                    return SourceString;    //返回源串
//                }
//            }
//            //DestStr += (char)c;
//            //DestStr += (char)c1;
//            DestStr += "";
//            cc = 30;
//            for (i = 0; i < nLen; i++)
//            {
//                //if (i>0 ) cc=SourceStr.GetAt(i-1);
//                c = SourceString[i];
//                if (c <= 255)
//                {
//                    //第78位与第56位交换，第12位与第34位交换
//                    //c1=((c & 48)<<2)|((c & 192)>>2)|((c & 3)<<2)|((c & 12)>>2);
//                    c1 = (c & 0xf0) | ((c & 3) << 2) | ((c & 12) >> 2);
//                    //c1=(c & 0xC0)|((c & 0x10)<<1)|((c & 0x20)>>1)|((c & 3)<<2)|((c & 12)>>2);
//                    //c1= (c1+cc)%256;	//和前1位已加密字符相加取模
//                    //cc=c1;	//记住前1位已加密字符
//                    DestStr += (char)c1;
//                }
//                else
//                {
//                    //第78位与第56位交换，第12位与第34位交换
//                    System.Text.Encoding GB2312 = System.Text.Encoding.GetEncoding("GB2312");
//                    string ss;
//                    ss = "" + (char)c;
//                    byte[] gb = GB2312.GetBytes(ss);
//                    //cc = (c & 0x0ff);
//                    cc = gb[0];
//                    gb[0] = (byte)((cc & 0xf0) + ((cc & 3) << 2) + ((cc & 12) >> 2));
//                    //cc = (c & 0xff00)/256;
//                    cc = gb[1];
//                    gb[1] = (byte)((cc & 0xf0) + ((cc & 3) << 2) + ((cc & 12) >> 2));
//                    //c1 = c1 + (cc1 << 8);
//                    DestStr += GB2312.GetString(gb);
//
//                }
//            }
//            DestStr.Replace("&", "&amp;");
//            DestStr.Replace("<", "&lt;");
//            DestStr.Replace(">", "&gt;");
//
//            return DestStr;
//        }
//        catch (Exception ex)
//        {
//            return SourceString;
//        }
//    }


}
