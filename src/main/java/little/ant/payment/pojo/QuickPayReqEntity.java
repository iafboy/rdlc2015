package little.ant.payment.pojo;

import java.io.Serializable;

public class QuickPayReqEntity implements Serializable{
	private static final long serialVersionUID = 2078863366425090321L;
	// 卡号
	private String cardNo;
	// 短卡号
	private String storableCardNo;
	// 卡效期,格式是：MMYY
	private String expiredDate;
	// 安全校验值,CVV2或CVC2
	// 对于银联和VISA卡，对应卡片背面的CVV2数字；对于MasterCard卡，对应卡片背面的CVC2数字
	private String cvv2;
	// 交易金额,以元为单位，小数点后最多两位
	private String amount;
	// 外部跟踪编号（商家自己的订单号）
	private String externalRefNumber;
	// 客户号
	private String customerId;
	// 客户姓名
	private String cardHolderName;
	// 客户身份证号
	private String cardHolderId;
	// 特殊交易标志
	private String spFlag;
	// 证件类型
	private String idType;
	// 保存鉴权信息
	private String savePciFlag;
	// 手机号码
	private String phone;
	// 快钱支付批次
	private String payBatch;
	// 手机验证码
	private String validCode;
	// 手机令牌码
	private String token;

	public QuickPayReqEntity(String cardNo_, String storableCardNo_, String expiredDate_, String cvv2_, String amount_,
			String externalRefNumber_, String customerId_, String cardHolderName_, String cardHolderId_, String spFlag_,
			String idType_, String savePciFlag_, String phone_, String payBatch_, String validCode_, String token_) {
		cardNo=cardNo_;
		storableCardNo=storableCardNo_;
		expiredDate=expiredDate_;
		cvv2=cvv2_;
		amount=amount_;
		externalRefNumber=externalRefNumber_;
		customerId=customerId_;
		cardHolderName=cardHolderName_;
		cardHolderId=cardHolderId_;
		spFlag=spFlag_;
		idType=idType_;
		savePciFlag=savePciFlag_;
		phone=phone_;
		payBatch=payBatch_;
		validCode=validCode_;
		token=token_;
		if (externalRefNumber == null || "".equals(externalRefNumber)) {
			externalRefNumber = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
		}
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getStorableCardNo() {
		return storableCardNo;
	}

	public void setStorableCardNo(String storableCardNo) {
		this.storableCardNo = storableCardNo;
	}

	public String getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}

	public String getCvv2() {
		return cvv2;
	}

	public void setCvv2(String cvv2) {
		this.cvv2 = cvv2;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getExternalRefNumber() {
		return externalRefNumber;
	}

	public void setExternalRefNumber(String externalRefNumber) {
		this.externalRefNumber = externalRefNumber;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getCardHolderId() {
		return cardHolderId;
	}

	public void setCardHolderId(String cardHolderId) {
		this.cardHolderId = cardHolderId;
	}

	public String getSpFlag() {
		return spFlag;
	}

	public void setSpFlag(String spFlag) {
		this.spFlag = spFlag;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getSavePciFlag() {
		return savePciFlag;
	}

	public void setSavePciFlag(String savePciFlag) {
		this.savePciFlag = savePciFlag;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPayBatch() {
		return payBatch;
	}

	public void setPayBatch(String payBatch) {
		this.payBatch = payBatch;
	}

	public String getValidCode() {
		return validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
