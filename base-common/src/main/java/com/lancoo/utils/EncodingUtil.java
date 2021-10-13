package com.lancoo.utils;

//import info.monitorenter.cpdetector.io.*;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class EncodingUtil {
    /**
     * 将原正确编码的字符串src，转化为编码为srcCharset的字符串
     * <p>
     * 前提是：确保原字符串的编码是无损（完整的）. 无需知道原字符串的具体编码，
     * 转化为目标编码的字符串由java库自动实现，无需自己手动实现。
     * <p>
     * 如果原编码字符串不能转化为目标编码，将会抛出UnsupportedEncodingException
     *
     * @param src
     * @param srcCharset
     * @param destCharet
     * @return 转换后的字符串
     * @throws UnsupportedEncodingException
     */
    public static String convertEncoding_Str(String src, String srcCharset, String destCharet)
            throws UnsupportedEncodingException {
        byte[] bts = src.getBytes(destCharet);
        return new String(bts, destCharet);
    }


    /**
     * 将编码为srcCharset的字节数组src转化为编码为destCharet的字节数组
     *
     * @param src
     * @param srcCharset
     * @param destCharet
     * @return
     * @throws UnsupportedEncodingException
     */
    public static byte[] convertEncoding_ByteArr(byte[] src, String srcCharset, String destCharet)
            throws UnsupportedEncodingException {
        String s = new String(src, srcCharset);
        return s.getBytes(destCharet);
    }


    /**
     * 将字节数组byteArr转化为2位16进制字符串,每个16进制之间用空格分割
     *
     * @param byteArr
     * @return
     */
    public static String byteToHex(byte... byteArr) {
        if (null == byteArr || byteArr.length == 0)
            return "";
        StringBuffer sb = new StringBuffer();
        String tmp = null;

        for (byte b : byteArr) {

            tmp = Integer.toHexString(b);
            // 切记：byte进行运算时，会自动转化为int，否则可能会出错
            if (b >>> 31 == 1) { // 最高位为1，负数
                sb.append(tmp.substring(6));
            } else { // 最高位为0，正数
                if (tmp.length() < 2)
                    sb.append('0');
                sb.append(tmp);
            }
            sb.append(' ');
        }
        sb.deleteCharAt(sb.length() - 1); // delete last space
        return sb.toString();
    }

    public static void writeUtf8File(String srcPath, String destPath) {
        BufferedReader bre = null;
        BufferedWriter bw = null;//定义一个流
        try {
            bre = new BufferedReader(new FileReader(srcPath));//此时获取到的bre就是整个文件的缓存流
            String str;
            //确认流的输出文件和编码格式，此过程创建了“test.txt”实例
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(destPath)));
            while ((str = bre.readLine()) != null) // 判断最后一行不存在，为空结束循环
            {
                bw.write(str);
            }
            bw.close();//关闭流
            bre.close();//关闭流
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void uploadToUtf8(String srcPath, String destPath) throws IOException {
        File file = new File(srcPath);

        InputStream inputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        FileWriter fileWriter = new FileWriter(destPath);
        char[] buff2 = new char[1024];
        while (bufferedReader.read(buff2) > 0) {
            fileWriter.write(buff2);
        }

        fileWriter.close();
        bufferedReader.close();

        System.out.println("上传成功");

    }

    public static String readFile(String filePathAndName) {
        StringBuilder fileContent = new StringBuilder();
        try {
            File f = new File(filePathAndName);
            if (f.isFile() && f.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(f), "GBK");
                BufferedReader reader = new BufferedReader(read);
                String line;
                while ((line = reader.readLine()) != null) {
                    fileContent.append(line).append("\n");
                }
                read.close();
            }
        } catch (Exception e) {
            System.out.println("读取文件内容操作出错");
            e.printStackTrace();
        }
        return fileContent.toString();
    }

    public static String getEncode(String filePathAndName) throws IOException {
        InputStream inputStream = new FileInputStream(filePathAndName);
        byte[] head = new byte[3];
        inputStream.read(head);
        String code = "gb2312";
        if (head[0] == -1 && head[1] == -2)
            code = "UTF-16";
        if (head[0] == -2 && head[1] == -1)
            code = "Unicode";
        if (head[0] == -17 && head[1] == -69 && head[2] == -65)
            code = "UTF-8";
        return code;
    }

    public static void writeFile(String destPath, String fileContent) {
        try {
            File f = new File(destPath);
            if (!f.exists()) {
                f.createNewFile();
            }
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
            BufferedWriter writer = new BufferedWriter(write);
            writer.write(fileContent);
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException("写文件内容操作出错");
        }
    }

    public static String getFileCharset(File file) throws Exception {
        /*CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
        detector.add(new ParsingDetector(false));
        detector.add(JChardetFacade.getInstance());
        detector.add(ASCIIDetector.getInstance());
        detector.add(UnicodeDetector.getInstance());
        Charset charset = null;
        try {
            charset = detector.detectCodepage(file.toURI().toURL());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        String charsetName = "GBK";
        if (charset != null) {
            if (charset.name().equals("US-ASCII")) {
                charsetName = "ISO_8859_1";
            } else if (charset.name().startsWith("UTF")) {
                charsetName = charset.name();// 例如:UTF-8,UTF-16BE.
            }
        }
        return charsetName;*/
        return "utf-8";
    }


    public static String getFilecharset(InputStream inputStream) {
        String charset = "GBK";
        byte[] first3Bytes = new byte[3];
        try {
            boolean checked = false;
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            bis.mark(0);
            int read = bis.read(first3Bytes, 0, 3);
            if (read == -1) {
                return charset; // 文件编码为 ANSI
            } else if (first3Bytes[0] == (byte) 0xFF
                    && first3Bytes[1] == (byte) 0xFE) {
                charset = "UTF-16LE"; // 文件编码为 Unicode
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xFE
                    && first3Bytes[1] == (byte) 0xFF) {
                charset = "UTF-16BE"; // 文件编码为 Unicode big endian
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xEF
                    && first3Bytes[1] == (byte) 0xBB
                    && first3Bytes[2] == (byte) 0xBF) {
                charset = "UTF-8"; // 文件编码为 UTF-8
                checked = true;
            }
            bis.reset();
            if (!checked) {
                int loc = 0;
                while ((read = bis.read()) != -1) {
                    loc++;
                    if (read >= 0xF0)
                        break;
                    if (0x80 <= read && read <= 0xBF) // 单独出现BF以下的，也算是GBK
                        break;
                    if (0xC0 <= read && read <= 0xDF) {
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) // 双字节 (0xC0 - 0xDF)
                            // (0x80
                            // - 0xBF),也可能在GB编码内
                            continue;
                        else
                            break;
                    } else if (0xE0 <= read && read <= 0xEF) {// 也有可能出错，但是几率较小
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) {
                            read = bis.read();
                            if (0x80 <= read && read <= 0xBF) {
                                charset = "UTF-8";
                                break;
                            } else
                                break;
                        } else
                            break;
                    }
                }
            }
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return charset;
    }
}
