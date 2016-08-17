package com.expocms.server.core.impl;

import com.expocms.server.db.dao.RdDaysMapper;
import com.expocms.server.db.dao.RdDictMapper;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.db.dao.RdProductMapper;
import com.expocms.server.db.model.ProductIntroductionEntity;
import com.expocms.server.request.types.impl.ProductIntroductionRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.CommonResponse;
import com.expocms.server.response.types.impl.ProductIntroductionResponse;
import com.expocms.server.response.types.inf.BaseResponse;
import com.expocms.server.tools.ToolDays;

@Service ("PRODUCTINTRODUCTIONHandler")
@Transactional
public class PRODUCTINTRODUCTIONHandler extends AbsBaseHandler {
	
	@Autowired
	private RdProductMapper rdProductMapper;
	
	@Autowired
	private RdDictMapper dictMapper;
	
	@Autowired
	private RdDaysMapper daysMapper;

	public PRODUCTINTRODUCTIONHandler() {
		super();
	}
	
	public BaseResponse handleRequest(IRequest request) {
		BaseResponse returnResp = null;
		String productId = ((ProductIntroductionRequest)request).getProductId();
		
		final ProductIntroductionEntity entity = rdProductMapper.getProductIntroductionEntity(productId);
		if(entity != null) {
			// calculate period of interest ...
			boolean calOK = false;
			Calendar startIDate = null;
			Calendar endIDate = null;
			int investDuration = 0;
			if(entity.getStartDate() != null && entity.getStartDate().equals("") == false &&
					entity.getEndDate() != null && entity.getEndDate().equals("") == false) {
				startIDate = ToolDays.getCalendar(entity.getStartDate());
				endIDate = ToolDays.getCalendar(entity.getEndDate());
				Calendar buyDate = ToolDays.getCalendar(new Date(System.currentTimeMillis()));
				try {
					investDuration = QUICKPAYMENTHandler.calculateInterestPeriod(daysMapper, buyDate, startIDate, endIDate);
					calOK = true;
				} catch(RuntimeException e) {}
			}
			
			returnResp = new ProductIntroductionResponse();
			
			if(calOK) {
				((ProductIntroductionResponse)returnResp).setStartDate(Constants.SDF_SHORT.format(startIDate.getTime()));
				((ProductIntroductionResponse)returnResp).setEndDate(Constants.SDF_SHORT.format(endIDate.getTime()));			
				((ProductIntroductionResponse)returnResp).setInvestmentTimeLimit((long)investDuration);
			} else {
				((ProductIntroductionResponse)returnResp).setStartDate(entity.getStartDate());
				((ProductIntroductionResponse)returnResp).setEndDate(entity.getEndDate());			
				((ProductIntroductionResponse)returnResp).setInvestmentTimeLimit(entity.getInvestmentTimeLimit().longValue());
			}
			
			((ProductIntroductionResponse)returnResp).setAllMoney(entity.getAllMoney());
			((ProductIntroductionResponse)returnResp).setLowMoney(entity.getLowMoney());
			((ProductIntroductionResponse)returnResp).setHighMoney(entity.getHighMoney());
			((ProductIntroductionResponse)returnResp).setPredictYearEarnings(entity.getPredictYearEarnings().longValue());
			
			String guanyuDateStr = dictMapper.getValueFromDict("guanyuDateStr");
			if(guanyuDateStr != null && guanyuDateStr.equals("") == false) {
				guanyuDateStr = guanyuDateStr.replaceAll("\\$\\{END_DATE\\}", entity.getEndDate());
				guanyuDateStr = guanyuDateStr.replaceAll("\\$\\{DURATION\\}", "" + investDuration);
			}
			
			((ProductIntroductionResponse)returnResp).setGuanyuDateStr(guanyuDateStr);
			((ProductIntroductionResponse)returnResp).setCloseDatestr(dictMapper.getValueFromDict("closeDatestr"));
			((ProductIntroductionResponse)returnResp).setPersontaxStr(dictMapper.getValueFromDict("persontaxStr"));
			((ProductIntroductionResponse)returnResp).setRedemptionStr(dictMapper.getValueFromDict("redemptionStr"));

		} else {
			returnResp = new CommonResponse();
			returnResp.setResultCode(Constants.succCode);
			returnResp.setResultMsg(Constants.succMsg);
		}
		
		return returnResp;
	}
}
