package com.lancoo.mapper;


import com.lancoo.mysqlmapper.MysqlMapper;
import com.lancoo.pojo.LgdbRescatalogPrivate;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LgdbRescatalogPrivateMapper extends MysqlMapper<LgdbRescatalogPrivate> {

	//根据试题GUID批量删除个人库试题信息
	boolean deleteQuesInfoByFixedIds(@Param("fixedIds") String [] fixedIds);
	//根据guid集合查询
	List<LgdbRescatalogPrivate> findByIds(@Param("fixedIds") List<String> fixedIds);
}