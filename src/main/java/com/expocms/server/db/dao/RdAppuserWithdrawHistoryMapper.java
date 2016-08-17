package com.expocms.server.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.expocms.server.db.model.RdAppuserWithdrawHistory;

@Repository
public interface RdAppuserWithdrawHistoryMapper {

	int insert(RdAppuserWithdrawHistory record);
	
	int updateStatusByIds(RdAppuserWithdrawHistory record);
	
	@Select ("select * from rd_appuser_withdraw_history where userId = #{0} order by submitTime desc")
    List<RdAppuserWithdrawHistory> getWithdrawHistoryByIds(final String ids);
	
	@Select ("select * from rd_appuser_withdraw_history where status = #{0}")
    List<RdAppuserWithdrawHistory> getWithdrawHistoryByStatus(final int status);
	
	@Select ("select * from rd_appuser_withdraw_history where userId = #{0} and status = #{1}")
    List<RdAppuserWithdrawHistory> getSpecificWithdrawHistory(final String ids, final int status);
	
}
