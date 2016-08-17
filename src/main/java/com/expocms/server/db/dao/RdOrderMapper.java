package com.expocms.server.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.expocms.server.db.model.RdOrder;

@Repository
public interface RdOrderMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * rd_order
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int deleteByPrimaryKey (String ids);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * rd_order
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int insert (RdOrder record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * rd_order
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int insertSelective (RdOrder record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * rd_order
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    RdOrder selectByPrimaryKey (String ids);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * rd_order
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int updateByPrimaryKeySelective (RdOrder record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * rd_order
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    int updateByPrimaryKey (RdOrder record);
    
    /**
     * update order status ...
     * @param record
     * @return
     */
    int updateOrderStatus(RdOrder record);
    
    @Select (value="SELECT * FROM rd_order WHERE ids = #{0} and status = 2")
    RdOrder getOrderToRepaid(final String ids);

    @Select (value = "select sum(amount) as allProperty from rd_order")
    Long getAllProperty();
	
//    @Select (value = "select sum(giftamount) as allRedPackageAmount from rd_order where productIds = #{0} and status = 1")
    @Select (value = "select sum(giftamount) as allRedPackageAmount from rd_order where productIds = #{0}")
    Long getAllRedPackageAmountByProductIds(String productIds);
    
//    @Select (value="select count(1) from rd_order where appUserID = #{0}")
//    int getOrderNumOfCurrentUser(String userid);
    
//    @Select (value="select count(1) from rd_order where appUserID = #{0} and productIds= #{1}")
//    int getProductOrderNumOfCurrentUser(String userid, String productId);
    
//    @Select ("select count(1) from rd_order o where o.productName = #{0} and productSeries = #{1}")
//    int currentProductBuyUserNumber(String productName, String productSeries);
    
    @Select (value="select * from rd_order where appUserID = #{0} order by orderCreateTime desc")
    List<RdOrder> getOrdersByUser(String userId);
    
//    @Select (value="select * from rd_order where appUserID = #{0} and (status = 1 or status = 2)")
//    List<RdOrder> getNonRepaiedOrdersByUser(String userId);
    
//    @Select (value="select * from rd_order where productIds = #{0} and (status = 1 or status = 2)")
//    List<RdOrder> getNonRepaiedOrdersByProduct(String productId);
    
    @Select (value="select distinct appUserID from rd_order where productIds = #{0}")
    List<String> getUserListOfProduct(String productId);
    
    @Select (value="SELECT * FROM rd_order WHERE DATE_FORMAT(endDate, '%Y-%m-%d') = #{0}")
    List<RdOrder> getOrdersToRepaid(final String endDate);
    
}