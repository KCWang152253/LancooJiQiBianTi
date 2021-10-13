package com.lancoo.global.exception;

import com.lancoo.grace.result.IMOOCJSONResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@Slf4j
@RestControllerAdvice
public class MyExceptionHandler {
    @Value("${spring.servlet.multipart.max-file-size}")
    private String fileSizeLimit;

    /* spring默认上传大小1MB 超出大小捕获异常MaxUploadSizeExceededException */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public IMOOCJSONResult handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.error("文件大小超出限制 : " + fileSizeLimit, e);
        return IMOOCJSONResult.errorMsg("文件大小超出限制 : " + fileSizeLimit);
    }
    /* 异常全局捕获 */
    @ExceptionHandler(RuntimeException.class)
    public IMOOCJSONResult handleRuntimeException(RuntimeException e) {
        return IMOOCJSONResult.errorMsg(e.getMessage());
    }
    /* 异常全局捕获 */
    @ExceptionHandler(MyRuntimeException.class)
    public IMOOCJSONResult handleMyRuntimeException(MyRuntimeException e) {
        if (299 == e.getCode()){
            return new IMOOCJSONResult(e.getCode(), "token错误", e.getData(),"");
        } else {
            return new IMOOCJSONResult(e.getCode(), e.getMessage(), e.getData(),"");
        }
    }
}
