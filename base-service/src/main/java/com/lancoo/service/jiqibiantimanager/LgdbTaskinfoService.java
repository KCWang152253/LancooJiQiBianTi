package com.lancoo.service.jiqibiantimanager;

import com.lancoo.mapper.LgdbTaskinfoMapper;
import com.lancoo.pojo.LgdbTaskinfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author KCWang
 * @date 2021/7/29 16:59
 * @Email:KCWang@aliyun.com
 */

@Slf4j
@Service
public class LgdbTaskinfoService {
    @Autowired
    private LgdbTaskinfoMapper lgdbTaskinfoMapper;

    public void findTaskinfobyContion(){
        LgdbTaskinfo lgdbTaskinfo = new LgdbTaskinfo();
//        lgdbTaskinfo.setTaskdate();
//        lgdbTaskinfoMapper.selectOneByExample()
////        lgdbTaskinfoMapper.s();


    }
}

