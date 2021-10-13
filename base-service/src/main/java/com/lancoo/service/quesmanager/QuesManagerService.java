package com.lancoo.service.quesmanager;

import com.lancoo.pojo.*;
import com.lancoo.service.dto.*;
import com.lancoo.utils.UuidUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

import static com.lancoo.service.constant.QuesConstant.*;

@Slf4j
@Service
public class QuesManagerService {
    @Autowired
    private LgdbRescatalogService lgdbRescatalogService;

    /**
     * 根据fixId查询私有库试题
     *
     * @param guid
     * @return
     */
    public QuesViewDto getPrivateQuesById(String guid) {
        //查询db中的试题信息
        LgdbRescatalogPrivate dbQues = lgdbRescatalogService.getPrivateQuesById(guid);
        if (dbQues == null) {
            return null;
        }
        QuesViewDto result = new QuesViewDto();
        BeanUtils.copyProperties(dbQues, result);

        return result;
    }

    /**
     * 添加个人库试题
     *
     * @param addDto
     */
    public String privateAdd(QuesAddUpdateDto addDto) {
        //TODO 1、校验，学科编码、知识点编码、难度等数据字段校验

        //2、转换为入库对象
        LgdbRescatalogPrivate addToDbQues = getAddPrivateQuesInfo(addDto);

        //2、入库
        lgdbRescatalogService.privateAdd(addToDbQues);

        //3、返回guid
        return addToDbQues.getFixedId();
    }

    /**
     * 把添加对象转换为入库的试题
     *
     * @param addDto
     * @return
     */
    private LgdbRescatalogPrivate getAddPrivateQuesInfo(QuesAddUpdateDto addDto) {
        LgdbRescatalogPrivate addToDbQues = new LgdbRescatalogPrivate();
        BeanUtils.copyProperties(addDto, addToDbQues);
        if (StringUtils.isEmpty(addToDbQues.getFixedId())) {
            addToDbQues.setFixedId(FIXID_PREFIX + UuidUtils.getUuId());
        }
        addToDbQues.setQuesContent("");
        //根据知识点编码查询每个知识点文本，用|分割
        addToDbQues.setImporKnText("");
        addToDbQues.setCreateUser(addDto.getUser().getUserId());
        addToDbQues.setQuesStatus(QUES_STATUS_UNSUBMIT);
        addToDbQues.setCreateDate(new Date());
        return addToDbQues;
    }

}
