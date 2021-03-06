package com.expocms.server.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.expocms.server.db.model.RdHelpCenter;
@Repository
public interface RdHelpCenterMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_help_center
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int deleteByPrimaryKey(String ids);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_help_center
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int insert(RdHelpCenter record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_help_center
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int insertSelective(RdHelpCenter record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_help_center
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    RdHelpCenter selectByPrimaryKey(String ids);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_help_center
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    @Select(value = "select * from rd_qa")
    List<RdHelpCenter> selectAll();
    
    @Select(value = "select * from rd_qa order by seq")
    List<RdHelpCenter> selectAllSeq();
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_help_center
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int updateByPrimaryKeySelective(RdHelpCenter record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_help_center
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int updateByPrimaryKey(RdHelpCenter record);
}