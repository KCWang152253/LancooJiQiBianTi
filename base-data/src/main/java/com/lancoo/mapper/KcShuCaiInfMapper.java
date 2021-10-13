package com.lancoo.mapper;

import com.lancoo.mysqlmapper.MysqlMapper;
import com.lancoo.pojo.LgdbFKlgOrgResource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author KCWang
 * @date 2021/7/22 11:26
 * @Email:KCWang@aliyun.com
 */

@Mapper
@Repository
public interface KcShuCaiInfMapper extends MysqlMapper<LgdbFKlgOrgResource>{
}
