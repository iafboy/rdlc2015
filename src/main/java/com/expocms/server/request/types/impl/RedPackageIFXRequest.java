package com.expocms.server.request.types.impl;

import com.expocms.server.request.types.inf.IRequest;

public class RedPackageIFXRequest implements IRequest {
	
	private Integer value;
	
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}

	public boolean validate() {
		if(value == null)
			return false;
		return true;
	}

}
