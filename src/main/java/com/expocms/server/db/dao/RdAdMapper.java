package com.expocms.server.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.expocms.server.db.model.RdAd;

@Repository
public interface RdAdMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * rd_ad
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int deleteByPrimaryKey (String ids);


    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * rd_ad
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int insert (RdAd record);


    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * rd_ad
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int insertSelective (RdAd record);


    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * rd_ad
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    RdAd selectByPrimaryKey (String ids);


    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * rd_ad
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int updateByPrimaryKeySelective (RdAd record);


    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * rd_ad
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int updateByPrimaryKey (RdAd record);


    @Select (value = "select * from rd_ad")
    List<RdAd> selectAll ();
    
    @Select (value = "select * from rd_ad where status = 1")
    List<RdAd> selectAllAvail ();
}
