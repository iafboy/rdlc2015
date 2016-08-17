package com.expocms.server.response.types.impl;

import com.expocms.server.response.types.inf.BaseResponse;

public class CommonResponse extends BaseResponse {
	public CommonResponse(int returncode_,String returnmsg_){
		this.resultCode=returncode_;
		this.resultMsg=returnmsg_;
	}
	public CommonResponse(){
		
	}
}
