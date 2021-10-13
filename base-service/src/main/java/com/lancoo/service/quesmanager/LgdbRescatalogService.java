package com.lancoo.service.quesmanager;

import com.lancoo.mapper.LgdbRescatalogPrivateMapper;
import com.lancoo.pojo.LgdbRescatalogPrivate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LgdbRescatalogService {
    @Autowired
    private LgdbRescatalogPrivateMapper rescatalogPrivateMapper;

    public LgdbRescatalogPrivate getPrivateQuesById(String guid){
        LgdbRescatalogPrivate lgdbRescatalogPrivate = new LgdbRescatalogPrivate();
        lgdbRescatalogPrivate.setFixedId(guid);
        return rescatalogPrivateMapper.selectByPrimaryKey(lgdbRescatalogPrivate);
    }

    /**
     * 添加个人库试题
     * @param rescatalog
     */
    public void privateAdd(LgdbRescatalogPrivate rescatalog) {
        rescatalogPrivateMapper.insert(rescatalog);
    }

}
