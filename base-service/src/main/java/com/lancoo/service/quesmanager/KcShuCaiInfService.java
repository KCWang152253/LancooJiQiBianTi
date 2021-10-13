package com.lancoo.service.quesmanager;

import com.lancoo.mapper.KcShuCaiInfMapper;
import com.lancoo.pojo.LgdbFKlgOrgResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author KCWang
 * @date 2021/7/22 11:18
 * @Email:KCWang@aliyun.com
 */

@Slf4j
@Service
public class KcShuCaiInfService {

    @Autowired
    private KcShuCaiInfMapper kcShuCaiInfMapper;

/**
 * 根据ID查找知识点素材信息
 */

    public LgdbFKlgOrgResource getShuCaiInfoById(int id){
        LgdbFKlgOrgResource lgdbFKlgOrgResource = new LgdbFKlgOrgResource();
        lgdbFKlgOrgResource.setId(id);
        return kcShuCaiInfMapper.selectByPrimaryKey(lgdbFKlgOrgResource);
     }
}
