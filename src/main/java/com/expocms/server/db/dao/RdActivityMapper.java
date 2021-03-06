package com.expocms.server.db.dao;

import org.springframework.stereotype.Repository;

import com.expocms.server.db.model.RdActivity;
@Repository
public interface RdActivityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_activity
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int deleteByPrimaryKey(String ids);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_activity
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int insert(RdActivity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_activity
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int insertSelective(RdActivity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_activity
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    RdActivity selectByPrimaryKey(String ids);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_activity
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int updateByPrimaryKeySelective(RdActivity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_activity
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int updateByPrimaryKey(RdActivity record);
}