package little.ant.payment.pojo;

import java.io.Serializable;

public class IndAuthVerifyEntity implements Serializable{
	private static final long serialVersionUID = -17926971958190478L;
	private String merchantId;
	private String customerId;
	private String externalRefNumber;
	private String pan;
	private String phoneNO;
	private String token;
	private String validCode;
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getExternalRefNumber() {
		return externalRefNumber;
	}
	public void setExternalRefNumber(String externalRefNumber) {
		this.externalRefNumber = externalRefNumber;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getPhoneNO() {
		return phoneNO;
	}
	public void setPhoneNO(String phoneNO) {
		this.phoneNO = phoneNO;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getValidCode() {
		return validCode;
	}
	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
