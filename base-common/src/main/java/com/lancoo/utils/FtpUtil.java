package com.lancoo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.net.SocketException;
import java.util.List;

@Slf4j
public class FtpUtil {
    //ftp服务器地址
    private static String hostname;
    //ftp服务器端口号
    private static Integer port;
    //ftp登录账号
    private static String username;
    //ftp登录密码
    private static String password;

    private static FTPClient ftpClient = null;

    public static void initFtpInfo(String hostname, Integer port, String username, String password) {
        FtpUtil.hostname = hostname;
        FtpUtil.port = port;
        FtpUtil.username = username;
        FtpUtil.password = password;
    }

    /**
     * 初始化ftp服务器
     */
    private static void initFtpClient() {
        ftpClient = new FTPClient();
        //ftpClient.setControlEncoding("utf-8");
        ftpClient.setControlEncoding("GBK");
        try {
            log.info("connecting...ftp服务器:" + hostname + ":" + port);
            ftpClient.connect(hostname, port); //连接ftp服务器
            ftpClient.login(username, password); //登录ftp服务器
            int replyCode = ftpClient.getReplyCode(); //是否成功登录服务器
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                log.info("connect failed...ftp服务器:" + hostname + ":" + port);
            }
            log.info("connect successfu...ftp服务器:" + hostname + ":" + port);
        } catch (IOException e) {
            log.error("连接到FTP服务器失败", e);
        }
    }

    /**
     * 上传文件
     *
     * @param pathname        ftp服务保存地址
     * @param originfilenames 待上传文件的名称（绝对地址） *
     * @return
     */
    public static boolean uploadFile(String pathname, List<String> originfilenames) {
        InputStream inputStream = null;
        try {
            log.info("开始上传文件");
            connectFtp();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            CreateDirecroty(pathname);
            ftpClient.makeDirectory(pathname);
            ftpClient.changeWorkingDirectory(pathname);
            for (String originfilename : originfilenames) {
                inputStream = new FileInputStream(new File(originfilename));
                String ftpFileName = originfilename.substring(originfilename.lastIndexOf("\\") + 1);
                boolean saveFlag = ftpClient.storeFile(ftpFileName, inputStream);
                inputStream.close();
                if (!saveFlag) {
                    log.warn("文件上传ft失败！file：{}", originfilename);
                }
            }

            ftpClient.logout();
            return true;
        } catch (Exception e) {
            log.error("上传文件失败", e);
            return false;
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    log.error("", e);
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("", e);
                }
            }
        }
    }

    /**
     * 上传文件
     *
     * @param pathname    ftp服务保存地址
     * @param fileName    上传到ftp的文件后缀名 ： xxxxx.jpg
     * @param inputStream 输入文件流
     * @return
     */
    public static boolean uploadFile(String pathname, String fileName, InputStream inputStream) {
        boolean storeResult;
        try {
            log.info("开始上传文件");
            connectFtp();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            CreateDirecroty(pathname);
            ftpClient.makeDirectory(pathname);
            storeResult = ftpClient.storeFile(fileName, inputStream);
            inputStream.close();
            ftpClient.logout();
            log.info("上传文件成功");
        } catch (Exception e) {
            log.error("上传文件失败", e);
            return false;
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    log.error("", e);
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("", e);
                }
            }
        }
        return storeResult;
    }

    //改变目录路径
    public static boolean changeWorkingDirectory(String directory) {
        boolean flag = true;
        try {
            flag = ftpClient.changeWorkingDirectory(directory);
            if (flag) {
                log.info("进入文件夹" + directory + " 成功！");

            } else {
                log.info("进入文件夹" + directory + " 失败！开始创建文件夹");
            }
        } catch (IOException e) {
            log.error("", e);
        }
        return flag;
    }

    //创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建
    public static boolean CreateDirecroty(String remote) throws IOException {
        boolean success = true;
        String directory = remote + "/";
        // 如果远程目录不存在，则递归创建远程服务器目录
        if (!directory.equalsIgnoreCase("/") && !changeWorkingDirectory(new String(directory))) {
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            }
            end = directory.indexOf("/", start);
            String path = "";
            StringBuilder paths = new StringBuilder();
            do {
                //String subDirectory = new String(remote.substring(start, end).getBytes("GBK"), "iso-8859-1");
                String subDirectory = new String(remote.substring(start, end).getBytes("utf-8"), "iso-8859-1");
                path = path + "/" + subDirectory;
                if (!existFile(path)) {
                    if (makeDirectory(subDirectory)) {
                        changeWorkingDirectory(subDirectory);
                    } else {
                        log.info("创建目录[" + subDirectory + "]失败");
                        changeWorkingDirectory(subDirectory);
                    }
                } else {
                    changeWorkingDirectory(subDirectory);
                }

                paths.append("/").append(subDirectory);
                start = end + 1;
                end = directory.indexOf("/", start);
                // 检查所有目录是否创建完毕
            } while (end > start);
        }
        return success;
    }

    //判断ftp服务器文件是否存在
    public static boolean existFile(String path) throws IOException {
        boolean flag = false;
        FTPFile[] ftpFileArr = ftpClient.listFiles(path);
        if (ftpFileArr.length > 0) {
            flag = true;
        }
        return flag;
    }

    //创建目录
    public static boolean makeDirectory(String dir) {
        boolean flag = true;
        try {
            flag = ftpClient.makeDirectory(dir);
            if (flag) {
                log.info("创建文件夹" + dir + " 成功！");

            } else {
                log.info("创建文件夹" + dir + " 失败！");
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return flag;
    }

    /**
     * 下载文件 *
     *
     * @param pathname  FTP服务器文件目录 *
     * @param filename  文件名称 *
     * @param localpath 下载后的文件路径 *
     * @return
     */
    public boolean downloadFile(String pathname, String filename, String localpath) {
        boolean flag = false;
        OutputStream os = null;
        try {
            log.info("开始下载文件");
            connectFtp();
            //切换FTP目录
            ftpClient.changeWorkingDirectory(pathname);
            FTPFile[] ftpFiles = ftpClient.listFiles();
            for (FTPFile file : ftpFiles) {
                if (filename.equalsIgnoreCase(file.getName())) {
                    File localFile = new File(localpath + "/" + file.getName());
                    os = new FileOutputStream(localFile);
                    ftpClient.retrieveFile(file.getName(), os);
                    os.close();
                }
            }
            ftpClient.logout();
            flag = true;
            log.info("下载文件成功");
        } catch (Exception e) {
            log.info("下载文件失败");
            log.error("", e);
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    log.error("", e);
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    log.error("", e);
                }
            }
        }
        return flag;
    }

    /**
     * 删除文件 *
     *
     * @param pathname FTP服务器保存目录 *
     * @param filename 要删除的文件名称 *
     * @return
     */
    public boolean deleteFile(String pathname, String filename) {
        boolean flag = false;
        try {
            log.info("开始删除文件");
            connectFtp();
            //切换FTP目录
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.dele(filename);
            ftpClient.logout();
            flag = true;
            log.info("删除文件成功");
        } catch (Exception e) {
            log.info("删除文件失败");
            log.error("", e);
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    log.error("", e);
                }
            }
        }
        return flag;
    }

    private static void connectFtp() {
        if (ftpClient == null || !ftpClient.isConnected()) {
            initFtpClient();
        }
    }

    public static void uploadToFtp(String path, String fileName, InputStream ins) {
        //创建客户端对象
        FTPClient ftp = new FTPClient();
        try {
            //连接ftp服务器
            ftp.connect(hostname, port);
            //登录
            ftp.login(username, password);
            //设置上传路径
            //检查上传路径是否存在 如果不存在返回false
            boolean flag = ftp.changeWorkingDirectory(path);
            if (!flag) {
                //创建上传的路径  该方法只能创建一级目录，在这里如果/home/ftpuser存在则可创建image
                ftp.makeDirectory(path);
            }
            //指定上传路径
            ftp.changeWorkingDirectory(path);
            //指定上传文件的类型  二进制文件
            ftp.setFileType(ftpClient.BINARY_FILE_TYPE);
            ftpClient.changeWorkingDirectory(path);//切换到工作的文件路径
            //第一个参数是文件名
            ftp.storeFile(fileName, ins);
        } catch (SocketException e) {
            log.error("", e);
        } catch (IOException e) {
            log.error("", e);
        } finally {
            try {
                //关闭文件流
                ins.close();
                //退出
                ftp.logout();
                //断开连接
                ftp.disconnect();
            } catch (IOException e) {
                log.error("", e);
            }
        }
    }
}