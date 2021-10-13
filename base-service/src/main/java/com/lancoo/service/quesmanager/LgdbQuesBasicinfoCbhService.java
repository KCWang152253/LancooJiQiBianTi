package com.lancoo.service.quesmanager;

import com.lancoo.mapper.LgdbQuesBasicinfoCbhMapper;
import com.lancoo.pojo.LgdbQuesBasicinfoCbh;
import com.lancoo.response.CommonCode;
import com.lancoo.response.QueryResponseResult;
import com.lancoo.response.QueryResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author KCWang
 * @date 2021/7/27 11:53
 * @Email:KCWang@aliyun.com
 */


@Slf4j
@Service
public class LgdbQuesBasicinfoCbhService {
    @Autowired
    private LgdbQuesBasicinfoCbhMapper lgdbQuesBasicinfoCbhMapper;



    public QueryResponseResult findsBasicInfoById(String id){
        LgdbQuesBasicinfoCbh lgdbQuesBasicinfoCbh = new LgdbQuesBasicinfoCbh();
        lgdbQuesBasicinfoCbh.setGuid(id);
        LgdbQuesBasicinfoCbh lgdbQuesBasicinfoCbh1 = lgdbQuesBasicinfoCbhMapper.selectByPrimaryKey(lgdbQuesBasicinfoCbh);
        System.out.println(lgdbQuesBasicinfoCbh1);
        ArrayList<LgdbQuesBasicinfoCbh> lgdbQuesBasicinfoCbhs = new ArrayList<LgdbQuesBasicinfoCbh>();
        lgdbQuesBasicinfoCbhs.add(lgdbQuesBasicinfoCbh1);
        QueryResult<LgdbQuesBasicinfoCbh> objectQueryResult = new QueryResult<>();
        objectQueryResult.setList(lgdbQuesBasicinfoCbhs);
        objectQueryResult.setTotal(lgdbQuesBasicinfoCbhs.size());
        return new QueryResponseResult(CommonCode.SUCCESS,objectQueryResult);






    }


}
