package com.expocms.server.db.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.expocms.server.db.model.RdContract;
@Repository
public interface RdContractMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_contract
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int insert(RdContract record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_contract
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int insertSelective(RdContract record);


    String queryUrlBySeq(@Param("seq")int seq);
    
    String queryContentBySeq(@Param("seq")int seq);
}
