package com.expocms.server.db.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository("RdDictMapper")
public interface RdDictMapper {
	@Select(value="SELECT value FROM rd_dict WHERE name = #{key_}")
	String getValueFromDict(String key_);
}
