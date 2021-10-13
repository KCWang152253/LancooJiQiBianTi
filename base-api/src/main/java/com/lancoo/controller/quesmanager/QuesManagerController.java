package com.lancoo.controller.quesmanager;

import com.lancoo.grace.result.IMOOCJSONResult;
import com.lancoo.service.dto.QuesAddUpdateDto;
import com.lancoo.service.quesmanager.QuesManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
public class QuesManagerController {
    @Autowired
    private QuesManagerService quesManagerService;

    /**
     * 根据id查询单个私有库试题信息
     *
     * @param guid
     * @return
     */
    @GetMapping("/ques/getPrivateQuesById")
    @ResponseBody
    public IMOOCJSONResult getPrivateQuesById(String guid) {
        try {
            return IMOOCJSONResult.ok(quesManagerService.getPrivateQuesById(guid));
        } catch (Exception e) {
            log.error("根据id获取试题信息失败！", e);
            return IMOOCJSONResult.errorMsg(e.getMessage());
        }

    }


    /**
     * 添加教师个人库试题信息
     *
     * @param addDto
     * @return
     */
    @PostMapping("/ques/privateAdd")
    @ResponseBody
    public IMOOCJSONResult privateAdd(@RequestBody QuesAddUpdateDto addDto) {
        IMOOCJSONResult result;
        try {
            result = IMOOCJSONResult.ok(quesManagerService.privateAdd(addDto));
            result.setShowmsg("添加成功");
        } catch (RuntimeException e) {
            log.error("添加个人试题信息失败！", e);
            result = IMOOCJSONResult.errorMsg(e.getMessage());
            result.setShowmsg("添加失败");
        } catch (Exception e) {
            log.error("添加个人试题信息失败！", e);
            result = IMOOCJSONResult.errorMsg("添加个人试题信息失败");
            result.setShowmsg("添加失败");
        }
        return result;
    }

}
