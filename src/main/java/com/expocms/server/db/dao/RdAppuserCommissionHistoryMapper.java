package com.expocms.server.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.expocms.server.db.model.RdAppuserCommissionHistory;

@Repository
public interface RdAppuserCommissionHistoryMapper {

	int insert(RdAppuserCommissionHistory record);
	
	@Select ("select * from rd_appuser_commission_history where recommenderId = #{0} order by investTime desc")
    List<RdAppuserCommissionHistory> getCommissionHistory(final String ids);
	
}
