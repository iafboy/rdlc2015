package com.expocms.server.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.expocms.server.db.model.RdDays;

@Repository
public interface RdDaysMapper {
	
	RdDays selectByYearDay(int year, int dayOfYear);
	
	int deleteByYearDay(int year, int dayOfYear);
	
	int insert(RdDays days);
	
	@Select ("select * from rd_days where year = #{0}")
    List<RdDays> queryByYear(int year);

}
