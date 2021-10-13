package com.lancoo.service.quesmanager;


import com.lancoo.mapper.LgdbBasicQuestypeMapper;
import com.lancoo.pojo.LgdbBasicQuestype;

import com.lancoo.response.CommonCode;
import com.lancoo.response.QueryResponseResult;
import com.lancoo.response.QueryResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TiXingService {

    @Autowired
    private LgdbBasicQuestypeMapper lgdbBasicQuestypeMapper;



    /**
     * 根据提醒ID查题型
     */




    public QueryResponseResult findAllTiXingList(){
//        LgdbBasicQuesType lgdbBasicQuesType = new LgdbBasicQuesType();
//        lgdbBasicQuesType.setRecid(id);
//        return tiXingMapper.selectByPrimaryKey(lgdbBasicQuesType);


        List<LgdbBasicQuestype> all = lgdbBasicQuestypeMapper.selectAll();
        QueryResult<LgdbBasicQuestype> tiXingQueryResult = new QueryResult<>();
        tiXingQueryResult.setList(all);
        tiXingQueryResult.setTotal(all.size());
        return new QueryResponseResult(CommonCode.SUCCESS, tiXingQueryResult);
    }




}
