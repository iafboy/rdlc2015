package com.expocms.server.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.expocms.server.db.model.RdAppuserRecommendHistory;

@Repository
public interface RdAppuserRecommendHistoryMapper {
	
    int insert(RdAppuserRecommendHistory record);
    
    @Select ("select * from rd_appuser_recommend_history where recommenderId = #{0} order by recommendTime desc")
    List<RdAppuserRecommendHistory> getRecommendHistory(final String ids);

}
