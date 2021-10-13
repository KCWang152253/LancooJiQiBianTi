package com.lancoo.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileUtil {
    /**
     * 下载项目根目录下doc下的文件
     * @param response response
     * @param fileName 文件名
     * @return 返回结果 成功或者文件不存在
     */
    public static String downloadFile(HttpServletResponse response, String fileName, boolean notIE) {
        InputStream stream = FileUtil.class.getClassLoader().getResourceAsStream("doc/" + fileName);
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        try {
            String name = java.net.URLEncoder.encode(fileName, "UTF-8");
            if (notIE) {
                name = java.net.URLDecoder.decode(name, "ISO-8859-1");
            }
            response.setHeader("Content-Disposition", "attachment;filename=" + name );
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(stream);
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (FileNotFoundException e1) {
            //e1.getMessage()+"系统找不到指定的文件";
            return "系统找不到指定的文件";
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "success";
    }
}
