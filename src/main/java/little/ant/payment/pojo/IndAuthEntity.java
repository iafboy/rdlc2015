package little.ant.payment.pojo;

import java.io.Serializable;

public class IndAuthEntity implements Serializable{
	private static final long serialVersionUID = 1377871749582783778L;
	private String pan;
	private String expiredDate;
	private String merchantId;
	private String terminalId;
	private String cvv2;
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
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getCvv2() {
		return cvv2;
	}
	public void setCvv2(String cvv2) {
		this.cvv2 = cvv2;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
