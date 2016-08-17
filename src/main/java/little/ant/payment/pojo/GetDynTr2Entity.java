package little.ant.payment.pojo;

import java.io.Serializable;

public class GetDynTr2Entity implements Serializable{
	private static final long serialVersionUID = -1162871185884428784L;
	private String userId;
	private String bankName;
	//卡号
	private String pan;
	//卡效期
	//格式是：MMYY
	private String expiredDate;
	//安全校验值,CVV2或CVC2
	//对于银联和VISA卡，对应卡片背面的CVV2数字；对于MasterCard卡，对应卡片背面的CVC2数字
	private String  cvv2;
	//交易金额
	//以元为单位，小数点后最多两位
	private String amount;
	//外部跟踪编号（商家自己的订单号）
	private String externalRefNumber;
	//客户号
	private String customerId;
	//客户姓名
	private String cardHolderName;
	//客户身份证号
	private String cardHolderId;
	//证件类型
	private String idType;
	//手机号码
	private String phoneNO;
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
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
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getPhoneNO() {
		return phoneNO;
	}
	public void setPhoneNO(String phoneNO) {
		this.phoneNO = phoneNO;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
