package com.expocms.server.core.impl;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;

import com.expocms.server.db.dao.RdNoMapper;
import com.expocms.server.tools.ToolNo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expocms.server.cache.AuthCodeCacheManager;
import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.db.dao.RdActivitygiftMapper;
import com.expocms.server.db.dao.RdAppuserMapper;
import com.expocms.server.db.dao.RdAppuserRecommendHistoryMapper;
import com.expocms.server.db.dao.RdContractMapper;
import com.expocms.server.db.dao.RdDictMapper;
import com.expocms.server.db.dao.RdFixedgiftMapper;
import com.expocms.server.db.model.RdActivitygift;
import com.expocms.server.db.model.RdAppuser;
import com.expocms.server.db.model.RdAppuserRecommendHistory;
import com.expocms.server.db.model.RdFixedgift;
import com.expocms.server.request.types.impl.RegistRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.RegistResponse;
import com.expocms.server.response.types.inf.BaseResponse;
import com.expocms.server.tools.ToolUtils;
import com.expocms.server.tools.ToolMoney;
import com.expocms.server.util.RandomSecurityCode;
import com.google.common.io.Files;

@Service("REGISTHandler")
@Transactional
public class REGISTHandler extends AbsBaseHandler {

	private static Logger logger = Logger.getLogger(REGISTHandler.class);

	@Autowired(required = true)
	private RdAppuserMapper rdAppuserMapper;

	@Autowired
	private RdDictMapper dictMapper;

	@Autowired
	private RdContractMapper contractMapper;

	@Autowired(required = true)
	private RdFixedgiftMapper rdFixedgiftMapper;

	@Autowired(required = true)
	private RdActivitygiftMapper rdActivitygiftMapper;

	@Autowired
	private RdNoMapper noMapper;
	
	@Autowired
	private RdAppuserRecommendHistoryMapper rdAppuserRecommendHistoryMapper;

	public REGISTHandler() {
		super();
	}

	@Override
	public BaseResponse handleRequest(final IRequest request) {
		RegistResponse response = new RegistResponse();

		final String phoneNo = ((RegistRequest) request).getPhoneNo();
		if (phoneNo == null || phoneNo.equals("")) {
			logger.error("手机号不能为空");
			response.setResultCode(1);
			response.setResultMsg("手机号不能为空");
			return response;
		}

		RdAppuser rdappuser = rdAppuserMapper.selectByPhone(phoneNo);
		if (rdappuser != null) {
			logger.error("手机号已被注册:" + phoneNo);
			response.setResultCode(1);
			response.setResultMsg("手机号已被注册");
			return response;
		}
		
		// 推荐码 ...
		RdAppuser recommendUser = null;
		final String recommendCode = ((RegistRequest) request).getRecommendCode();
		if(recommendCode != null && recommendCode.equals("") == false) {
			recommendUser = rdAppuserMapper.selectByPhone(recommendCode);
			if(recommendUser == null) {
				logger.error("推荐码（手机号）不存在:" + recommendCode);
//				response.setResultCode(5);
//				response.setResultMsg("推荐码（手机号）不存在");
//				return response;
			}
		}
		
		final Date nowDateTime = new Date(System.currentTimeMillis());

		final String pwd = ((RegistRequest) request).getPassword();
		final String newVc = ((RegistRequest) request).getVerifyCode();
		
		final String code = AuthCodeCacheManager.getInstance().findAuthCodeInCache(phoneNo);
		if (newVc != null && newVc.equals(code)) {
			AuthCodeCacheManager.getInstance().cleanCodeInCache(phoneNo);
			final RdAppuser record = new RdAppuser();

			record.setIds(ToolUtils.getUuidByJdk(true));
			record.setPhone(phoneNo);
			record.setPwd(pwd);
			record.setIsblock(false);
			record.setRecommendCode(recommendCode);

			long redPackageMoney = 0L;
			String redPackageCode = "";
			try {
				final RdActivitygift gift = getFixedgift(nowDateTime);
				if (gift != null) {
					final String currentFixedGiftIds = record.getFixedgift();
					if (currentFixedGiftIds != null && "".equals(currentFixedGiftIds.trim())) {
						record.setFixedgift(String.format("%s,%s", currentFixedGiftIds, gift.getIds()));
					} else {
						record.setFixedgift(gift.getIds());
					}

					redPackageMoney = gift.getValue() == null ? 0 : gift.getValue().longValue();
					redPackageCode = gift.getIds();
				}
			} catch (Exception e) {
				logger.error("注册失败：" + e.getMessage());
				throw new RuntimeException("注册失败");
			}

			response.setUserId(record.getIds());
			response.setRedPackageMoney(redPackageMoney);
			response.setRedPackageCode(redPackageCode);

			String no = noMapper.getNoByName("rd_appuser");
			String nextno = ToolNo.getNextNo(no);
			record.setNo(nextno);
			// 生成用户协议文件
			generateUserContract(record);

			if (noMapper.increaseNoByName("rd_appuser", nextno) != 1) {
				logger.error("注册失败：" + "noMapper.increaseNoByName(...) failed!");
				throw new RuntimeException("注册失败");
			}

			if (rdAppuserMapper.insert(record) != 1) {
				logger.error("注册失败：" + "rdAppuserMapper.insert(...) failed!");
				throw new RuntimeException("注册失败");
			}
			
			// 推荐码 ...
			if(recommendUser != null) {
				logger.info("user " + record.getIds() + " found recommend user " + recommendUser.getIds() + ", recommendCode = " + recommendCode);
				
				RdAppuserRecommendHistory recommendRecord = new RdAppuserRecommendHistory();
				recommendRecord.setIds(ToolUtils.getUuidByJdk(true));
				recommendRecord.setRecommenderId(recommendUser.getIds());
				recommendRecord.setRecommendeeId(record.getIds());
				recommendRecord.setRecommendeePhone(phoneNo);
				recommendRecord.setRecommendTime(nowDateTime);
				recommendRecord.setRecommendeeName(null);
				
				if(rdAppuserRecommendHistoryMapper.insert(recommendRecord) != 1) {
					logger.error("注册失败：" + "rdAppuserRecommendHistoryMapper.insert(...) failed!");
					throw new RuntimeException("注册失败");
				}
			}

			response.setOrderCount(0);

			String smsContent = "恭喜您完成融道新手注册，送您" + ToolMoney.F2Y(redPackageMoney) +
								"元红包，快打开融道使用吧。【融道理财】";
			try {
				QUICKPAYMENTHandler.sendSMS_via_PINGTAI(phoneNo, smsContent);
			} catch(RuntimeException e) {
				logger.error("exception happened while send SMS to customer " + phoneNo + "! " + e.getMessage());
			}

			response.setResultCode(0);
			response.setResultMsg("注册成功");
		} else {
			response.setResultCode(3);
			response.setResultMsg("验证码输入错误");
			return response;
		}
		return response;
	}

	private boolean generateUserContract(RdAppuser record) {
		String contractContent = null;
		String seq = dictMapper.getValueFromDict("contract.user");
		if (seq != null&&!"".equals(seq)) {
			try {
				int seq_ = Integer.parseInt(seq);
				contractContent = contractMapper.queryContentBySeq(seq_);
				final File contractfile = new File(
						Constants.usercontractfilepath + File.separatorChar + record.getIds() + ".html");
				Files.write(contractContent.getBytes("GBK"), contractfile);
				return true;
			} catch (Exception e) {
				logger.error("reg " + e.getMessage(), e);
			}
		}
		return false;
	}

	private RdActivitygift getFixedgift(final Date nowDateTime) {
		final RdFixedgift rdFixedgift = rdFixedgiftMapper.selectByType(Constants.registerFixedGift);
		if (rdFixedgift == null || rdFixedgift.getStatus() == null
				|| rdFixedgift.getStatus().intValue() == Constants.fixedgiftDisabled) {
			logger.info("no available register red package found!");
			return null;
		}

		Long result = rdFixedgift.getAmount();

		int activatedNum = rdFixedgift.getActivatednum() != null ? rdFixedgift.getActivatednum() : 0;
		rdFixedgift.setActivatednum(activatedNum + 1);

		if (rdFixedgiftMapper.updateByPrimaryKeySelective(rdFixedgift) != 1) {
			logger.error("update red package into DB failed!");
			throw new RuntimeException("更新红包失败");
		}

		final Integer expiredays = rdFixedgift.getExpiredays();
		final Date expireDate = ToolUtils.getDate(expiredays.intValue());
		final String ids = String.valueOf(RandomSecurityCode.getSecurityCodeDefault());
		final RdActivitygift record = new RdActivitygift();

		record.setIds(ids);
		record.setCreatedate(nowDateTime);
		record.setValue(result);
		record.setActivityname(Constants.registerFixedGiftString);
		record.setIsactivated(true);
		record.setIsused(false);
		record.setType("2");
		record.setExpiredate(expireDate);
		record.setActivatedate(nowDateTime);
		record.setActivateTime(new Timestamp(System.currentTimeMillis()));
		record.setRestMoney(result);
		record.setGiftId(rdFixedgift.getIds());

		if (rdActivitygiftMapper.insert(record) != 1) {
			logger.error("insert red package into DB failed!");
			throw new RuntimeException("分配红包失败");
		}

		return record;
	}

}
