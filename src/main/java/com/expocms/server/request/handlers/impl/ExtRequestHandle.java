package com.expocms.server.request.handlers.impl;

import io.netty.channel.ChannelHandlerContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.expocms.server.actions.inf.IBaseRequestAction;
import com.expocms.server.constant.Constants;
import com.expocms.server.request.handlers.inf.ExtBaseRequestHandle;

@Controller
@Scope("prototype")
public class ExtRequestHandle extends ExtBaseRequestHandle {
	public ExtRequestHandle() {
	}

	public ExtRequestHandle(ChannelHandlerContext ctx, String contentStr,
			String classType_) {
		super(ctx, contentStr, classType_);
		if (className != null) {
			try {
				classType = Class.forName(className);
				requestAction = (IBaseRequestAction) Constants.applicationContext
						.getBean(Constants.ExtAction);
			} catch (Exception e) {
				logger.error(
						"ExtRequestHandle meet exception: " + e.getMessage(), e);
			}
		} else {
			logger.error("ExtRequestHandle didn't reg. the request");
		}
	}
}
