package com.expocms.server.db.dao;

import org.springframework.stereotype.Repository;

import com.expocms.server.db.model.RdAdvicetemplate;
@Repository
public interface RdAdvicetemplateMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_advicetemplate
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int insert(RdAdvicetemplate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_advicetemplate
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int insertSelective(RdAdvicetemplate record);
}