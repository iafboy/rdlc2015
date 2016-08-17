package com.expocms.server.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expocms.server.db.dao.RdActivityMapper;
import com.expocms.server.db.dao.RdActivitygiftMapper;
import com.expocms.server.db.dao.RdAdMapper;
import com.expocms.server.db.dao.RdAdvertisementMapper;
import com.expocms.server.db.dao.RdAdviceMapper;
import com.expocms.server.db.dao.RdAdvicetemplateMapper;
import com.expocms.server.db.dao.RdAppuserBonusMapper;
import com.expocms.server.db.dao.RdAppuserMapper;
import com.expocms.server.db.dao.RdAppuserOrdersMapper;
import com.expocms.server.db.dao.RdAppuserinformationmodlogMapper;
import com.expocms.server.db.dao.RdAppuserlogMapper;
import com.expocms.server.db.dao.RdConsultantMapper;
import com.expocms.server.db.dao.RdContractMapper;
import com.expocms.server.db.dao.RdCouponGroupMapper;
import com.expocms.server.db.dao.RdCouponMapper;
import com.expocms.server.db.dao.RdDepartmentMapper;
import com.expocms.server.db.dao.RdEmpMapper;
import com.expocms.server.db.dao.RdFeedbackMapper;
import com.expocms.server.db.dao.RdHelpCenterMapper;
import com.expocms.server.db.dao.RdLoanAgreementMapper;
import com.expocms.server.db.dao.RdNoMapper;
import com.expocms.server.db.dao.RdOperationLogMapper;
import com.expocms.server.db.dao.RdOperatorMapper;
import com.expocms.server.db.dao.RdOrderMapper;
import com.expocms.server.db.dao.RdPaymentMapper;
import com.expocms.server.db.dao.RdPaymentRecordMapper;
import com.expocms.server.db.dao.RdPaymentRefoundMapper;
import com.expocms.server.db.dao.RdPrivilegeMapper;
import com.expocms.server.db.dao.RdProductMapper;
import com.expocms.server.db.dao.RdPushmessageMapper;
import com.expocms.server.db.dao.RdQaMapper;
import com.expocms.server.db.dao.RdRoleMapper;
import com.expocms.server.db.dao.RdSmsMapper;
import com.expocms.server.db.dao.RdSystemlogMapper;
import com.expocms.server.db.dao.RdUserMapper;
import com.expocms.server.db.model.RdAppuser;
import com.expocms.server.db.model.RdPaymentRecord;
import com.expocms.server.db.model.RdUser;
import com.expocms.server.exceptions.CmsException;
import com.expocms.server.request.types.impl.BuyProductRequest;
import com.expocms.server.request.types.impl.ForgetLoginPwdRequest;
import com.expocms.server.request.types.impl.ForgetTradingPwdRequest;
import com.expocms.server.request.types.impl.ModifyBankInfoRequest;
import com.expocms.server.request.types.impl.ModifyLoginPwdRequest;
import com.expocms.server.request.types.impl.ModifyTradingPwdRequest;
import com.expocms.server.request.types.impl.RegistRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.LoginResponse;
import com.expocms.server.response.types.inf.BaseResponse;

@Service("dboperatorservice")
@Transactional
public class DBOperatorService {
	@Autowired
	private RdActivitygiftMapper rdActivitygiftMapper;
	@Autowired
	private RdActivityMapper rdActivityMapper;
	@Autowired
	private RdAdMapper rdAdMapper;
	@Autowired
	private RdAdvertisementMapper rdAdvertisementMapper;
	@Autowired
	private RdAdviceMapper rdAdviceMapper;
	@Autowired
	private RdAdvicetemplateMapper rdAdvicetemplateMapper;
	@Autowired
	private RdAppuserBonusMapper rdAppuserBonusMapper;
	@Autowired
	private RdAppuserinformationmodlogMapper rdAppuserinformationmodlogMapper;
	@Autowired
	private RdAppuserlogMapper rdAppuserlogMapper;
	@Autowired
	private RdAppuserMapper rdAppuserMapper;
	@Autowired
	private RdAppuserOrdersMapper rdAppuserOrdersMapper;
	@Autowired
	private RdConsultantMapper rdConsultantMapper;
	@Autowired
	private RdContractMapper rdContractMapper;
	@Autowired
	private RdCouponGroupMapper rdCouponGroupMapper;
	@Autowired
	private RdCouponMapper rdCouponMapper;
	@Autowired
	private RdDepartmentMapper rdDepartmentMapper;
	@Autowired
	private RdEmpMapper rdEmpMapper;
	@Autowired
	private RdFeedbackMapper rdFeedbackMapper;
	@Autowired
	private RdHelpCenterMapper rdHelpCenterMapper;
	@Autowired
	private RdLoanAgreementMapper rdLoanAgreementMapper;
	@Autowired
	private RdNoMapper rdNoMapper;
	@Autowired
	private RdOperationLogMapper rdOperationLogMapper;
	@Autowired
	private RdOperatorMapper rdOperatorMapper;
	@Autowired
	private RdOrderMapper rdOrderMapper;
	@Autowired
	private RdPaymentMapper rdPaymentMapper;
	@Autowired
	private RdPaymentRecordMapper rdPaymentRecordMapper;
	@Autowired
	private RdPaymentRefoundMapper rdPaymentRefoundMapper;
	@Autowired
	private RdPrivilegeMapper rdPrivilegeMapper;
	@Autowired
	private RdProductMapper rdProductMapper;
	@Autowired
	private RdPushmessageMapper rdPushmessageMapper;
	@Autowired
	private RdQaMapper rdQaMapper;
	@Autowired
	private RdRoleMapper rdRoleMapper;
	@Autowired
	private RdSmsMapper rdSmsMapper;
	@Autowired
	private RdSystemlogMapper rdSystemlogMapper;
	@Autowired
	private RdUserMapper rdUserMapper;

	public RdAppuser getAppUser(String phone, String password) {
		return rdAppuserMapper.selectByPhone(phone);
	}
}
