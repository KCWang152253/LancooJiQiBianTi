package com.lancoo.utils;

import org.springframework.stereotype.Component;

@Component
public class WordToHtmlUtil {
    /*public FileUpload upLoad(MultipartFile file) {
        FileUpload fu = null;
        try {
            FileObject fileObject = new FileObject(file);

            String filename = file.getOriginalFilename();
            String fileInfoId = UUIDUtils.randReplacedLower() + filename.substring(filename.lastIndexOf('.'));
            String date = DateFormatUtils.format(new Date(), "yyyyMMdd");
            String relativePath = "/" + fileConfig.getSysName() + "/" + date + "/" + fileInfoId;

            fu = new FileUpload();
            fu.setFileName(filename);
            fu.setFilePath(fileConfig.getServerUrl() + relativePath);

            // 保存文件
            String filePath = fileConfig.getFileBasePath() + relativePath;
            filePath = FilenameUtils.normalize(filePath, true);
            File fileDir = new File(FilenameUtils.getFullPath(filePath));
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            File saved = new File(filePath);
            FileUtils.writeByteArrayToFile(saved, fileObject.getBytes());
            fileUploadDao.insert(fu);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileUpload fileUpload = fileUploadDao.selectById(fu.getId());
        return fileUpload;
    }*/
}
