package com.expocms.server.core.impl;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.db.dao.RdAppuserMapper;
import com.expocms.server.db.dao.RdAppuserWithdrawHistoryMapper;
import com.expocms.server.db.dao.RdDaysMapper;
import com.expocms.server.db.model.RdAppuser;
import com.expocms.server.db.model.RdAppuserWithdrawHistory;
import com.expocms.server.db.model.RdDays;
import com.expocms.server.request.types.impl.CommissionWithdrawRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.CommissionWithdrawResponse;
import com.expocms.server.response.types.inf.BaseResponse;
import com.expocms.server.tools.ToolDays;
import com.expocms.server.tools.ToolUtils;
import com.expocms.server.util.SyncObjects;

@Service("COMMISSIONWITHDRAWHandler")
@Transactional
public class COMMISSIONWITHDRAWHandler extends AbsBaseHandler {
	
	private static Logger logger = Logger.getLogger(COMMISSIONWITHDRAWHandler.class);
	
	@Autowired
	private RdAppuserMapper rdAppuserMapper;
	
	@Autowired
	private RdAppuserWithdrawHistoryMapper rdAppuserWithdrawHistoryMapper;
	
	@Autowired
	private RdDaysMapper daysMapper;

	@Override
	public BaseResponse handleRequest(IRequest request) {
		returnResp = new CommissionWithdrawResponse();
		CommissionWithdrawResponse cwResp = (CommissionWithdrawResponse)returnResp;
		
		CommissionWithdrawRequest cwRequest = (CommissionWithdrawRequest)request;
		String userId = cwRequest.getUserId();
		Object recommenderSyncObj = SyncObjects.instance().getUserSyncObject(userId);
		
		synchronized(recommenderSyncObj) {
			RdAppuser rdAppuser = rdAppuserMapper.selectByUserId(userId);
			if (rdAppuser == null) {
				logger.error("user " + userId + " does not exist!");
				returnResp.setResultCode(1);
				returnResp.setResultMsg("用户不存在");
				return returnResp;
			}
			
			long amount = cwRequest.getAmount();
			long poundageAmount = cwRequest.getPoundageAmount();
			long arrivalAmount = cwRequest.getArrivalAmount();
			if(amount != (poundageAmount + arrivalAmount)) {
				logger.error("提现金额 不等于 手续费 加 到账金额!");
				returnResp.setResultCode(1);
				returnResp.setResultMsg("提现金额必须等于手续费加到账金额");
				return returnResp;
			}
			
			long commission = rdAppuser.getCommission() != null ? rdAppuser.getCommission() : 0;
			if(amount > commission) {
				logger.error("提现金额 超过 佣金!");
				returnResp.setResultCode(1);
				returnResp.setResultMsg("提现金额不能超过佣金");
				return returnResp;
			}
			
			Date nowDate = new Date();
			Calendar today = ToolDays.getCalendar(nowDate);
			Calendar after2Days = findAfter2WorkingDays(daysMapper, today);
			
			RdAppuserWithdrawHistory withdraw = new RdAppuserWithdrawHistory();
			withdraw.setIds(ToolUtils.getUuidByJdk(true));
			withdraw.setUserId(userId);
			withdraw.setAmount(amount);
			withdraw.setSubmitTime(nowDate);
			withdraw.setArrivalTime(after2Days.getTime());
			withdraw.setStatus(RdAppuserWithdrawHistory.RAWD_APPLIED);
			withdraw.setPoundageAmount(poundageAmount);
			withdraw.setArrivalAmount(arrivalAmount);
			if(rdAppuserWithdrawHistoryMapper.insert(withdraw) != 1) {
				logger.error("写提现记录到数据库失败!");
				throw new RuntimeException("提现失败");
			}

			commission -= amount;
			rdAppuser.setCommission(commission);
			if(rdAppuserMapper.updateCommissionByIds(rdAppuser) != 1) {
				logger.error("更新佣金到数据库（用户表）失败!");
				throw new RuntimeException("提现失败");
			}
			
			cwResp.setArrivalTime(Constants.SDF_SHORT.format(after2Days.getTime()));
		}
		
		returnResp.setResultCode(Constants.succCode);
		returnResp.setResultMsg(Constants.succMsg);
		return returnResp;
	}
	
	private Calendar findAfter2WorkingDays(RdDaysMapper daysMapper, Calendar today) {
		if(daysMapper == null)
			return null;
		if(today == null)
			return null;
		
		RdDays rdDay = ToolDays.instance().findClosedWorkingDay(daysMapper, today);
		if(rdDay == null)
			return null;
		
		Calendar workingDay = Calendar.getInstance();
		workingDay.set(Calendar.YEAR, rdDay.getYear());
		workingDay.set(Calendar.DAY_OF_YEAR, rdDay.getDayOfYear());
		workingDay.set(Calendar.HOUR_OF_DAY, 0);
		workingDay.set(Calendar.MINUTE, 0);
		workingDay.set(Calendar.SECOND, 0);
		workingDay.set(Calendar.MILLISECOND, 0);
		
		workingDay.add(Calendar.DAY_OF_YEAR, 1);
		if(ToolDays.instance().isWorkingDay(daysMapper, workingDay) == false) {
			rdDay = ToolDays.instance().findClosedWorkingDay(daysMapper, workingDay);
			if(rdDay == null)
				return null;
			
			workingDay.set(Calendar.YEAR, rdDay.getYear());
			workingDay.set(Calendar.DAY_OF_YEAR, rdDay.getDayOfYear());
			workingDay.set(Calendar.HOUR_OF_DAY, 0);
			workingDay.set(Calendar.MINUTE, 0);
			workingDay.set(Calendar.SECOND, 0);
			workingDay.set(Calendar.MILLISECOND, 0);
		}
		
		return workingDay;
	}

}
