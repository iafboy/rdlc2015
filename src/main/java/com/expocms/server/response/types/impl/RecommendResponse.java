package com.expocms.server.response.types.impl;

import java.util.List;

import com.expocms.server.response.types.inf.BaseResponse;

public class RecommendResponse extends BaseResponse {
	
	private int orderCount;
    private String proId;
    private Long allProperty;
    private int allUser;
    private long redPackage;
    private List<ShowFrame> showFrame;
    private RecommandProduct recommendProduct;
    
    public int getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

    public Long getAllProperty () {
        return allProperty;
    }
    public void setAllProperty (final Long allProperty) {
        this.allProperty = allProperty;
    }

    public int getAllUser () {
        return allUser;
    }
    public void setAllUser (final int allUser) {
        this.allUser = allUser;
    }

    public long getRedPackage () {
        return redPackage;
    }
    public void setRedPackage (final long redPackage) {
        this.redPackage = redPackage;
    }

    public RecommandProduct getRecommendProduct() {
		return recommendProduct;
	}
	public void setRecommendProduct(RecommandProduct recommendProduct) {
		this.recommendProduct = recommendProduct;
	}

	public List<ShowFrame> getShowFrame () {
        return showFrame;
    }
    public void setShowFrame (final List<ShowFrame> showFrame) {
        this.showFrame = showFrame;
    }

    public String getProId () {
        return proId;
    }
    public void setProId (final String proId) {
        this.proId = proId;
    }

}
