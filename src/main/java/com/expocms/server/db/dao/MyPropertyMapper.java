package com.expocms.server.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


import com.expocms.server.response.types.impl.AlreadyBuyItem;
import com.expocms.server.response.types.impl.RedeemItem;

@Repository
public interface MyPropertyMapper {
	
	Long getAllPropertyValue(@Param("no_")String no_);

    Long getYesterdayEarnings(@Param("no_")String no_);

    Long getAllEarnings(@Param("no_")String no_);

    List<RedeemItem> getRedeemItemEntityList(@Param("userId_")String userId_);

    List<AlreadyBuyItem> getAlreadybuyItemEntityList(@Param("userId_")String userId_);
    
}
