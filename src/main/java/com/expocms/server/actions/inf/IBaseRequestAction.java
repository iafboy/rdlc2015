package com.expocms.server.actions.inf;

import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.inf.BaseResponse;

public interface IBaseRequestAction {
	public BaseResponse handleRequest(IRequest request) ;
}
