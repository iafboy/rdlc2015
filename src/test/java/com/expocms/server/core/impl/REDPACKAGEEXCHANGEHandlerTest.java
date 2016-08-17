package com.expocms.server.core.impl;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.expocms.server.request.types.impl.RedPackageExchangeRequest;
import com.expocms.server.response.types.inf.BaseResponse;

public class REDPACKAGEEXCHANGEHandlerTest extends BaseTest {

    REDPACKAGEEXCHANGEHandler handler;


    @Test
    public void testHandleRequest () {
        handler = (REDPACKAGEEXCHANGEHandler)ac.getBean ("REDPACKAGEEXCHANGEHandler");
        final RedPackageExchangeRequest request = new RedPackageExchangeRequest ();
        request.setRedPackage ("DMUVNR2G");
        request.setUserId ("5ab08c6dad2b49a3b3fe16b48f8f0321");
        final BaseResponse response = handler.handleRequest (request);
        System.out.println (JSON.toJSONString (response));
    }

}
