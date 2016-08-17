package com.expocms.server.core.inf;

import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.inf.BaseResponse;

public interface IBaseHandler{
	public BaseResponse handleRequest(IRequest request) ;
}
