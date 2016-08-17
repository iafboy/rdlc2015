package com.expocms.server.db.dao;

import org.springframework.stereotype.Repository;

import com.expocms.server.db.model.RdEmp;
@Repository
public interface RdEmpMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_emp
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int deleteByPrimaryKey(String ids);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_emp
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int insert(RdEmp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_emp
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int insertSelective(RdEmp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_emp
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    RdEmp selectByPrimaryKey(String ids);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_emp
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int updateByPrimaryKeySelective(RdEmp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_emp
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int updateByPrimaryKey(RdEmp record);
}