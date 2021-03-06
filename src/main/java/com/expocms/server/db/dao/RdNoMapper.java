package com.expocms.server.db.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.expocms.server.db.model.RdNo;
@Repository
public interface RdNoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_no
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int deleteByPrimaryKey(String ids);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_no
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int insert(RdNo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_no
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int insertSelective(RdNo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_no
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    RdNo selectByPrimaryKey(String ids);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_no
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int updateByPrimaryKeySelective(RdNo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_no
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int updateByPrimaryKey(RdNo record);


    @Select("select currentNo as no from rd_no where tableName = #{name}")
    String getNoByName(@Param("name")String name);

    @Select("select currentNo as no from rd_no where tableName = #{name} and type = #{type}")
    String getNoByNameAndType(@Param("name")String name,@Param("type") String type);
    @Update("update rd_no set currentNo = #{no} where tableName = #{name} ")
    int increaseNoByName(@Param("name")String name,@Param("no")String no);
    @Update("update rd_no set currentNo = #{no} where tableName = #{name} and type = #{type} ")
    int increaseNoByNameAndType(@Param("name")String name,@Param("no")String no,@Param("type")String type);

}