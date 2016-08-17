package com.expocms.server.core.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.request.types.impl.RedPackageIFXRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.CommonResponse;
import com.expocms.server.response.types.inf.BaseResponse;

@Service("REDPACKAGEIFXHandler")
@Transactional
public class REDPACKAGEIFXHandler extends AbsBaseHandler {

	@Override
	public BaseResponse handleRequest(IRequest request) {
		returnResp = new CommonResponse();
		
		RedPackageIFXRequest rpIFX = (RedPackageIFXRequest)request;
		Constants.RED_PACKAGE_IFX = rpIFX.getValue() != null ? rpIFX.getValue() : 100;
		
		returnResp.setResultCode(Constants.succCode);
        returnResp.setResultMsg(Constants.succMsg);
		return returnResp;
	}

}
