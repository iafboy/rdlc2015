package little.ant.payment.pojo;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import com.expocms.server.constant.Constants;
import com.expocms.server.util.MD5Util;


public class Pay2AccountEntity implements Serializable{
	private static final long serialVersionUID = -6036533078166172554L;
	// 客户编号所对应的密钥。。在账户邮箱中获取
	private String key;
	// 收款方姓名
	private String creditName;
	// 收款方快钱用户名(Email或手机号)
	private String creditContact;
	// 货币类型
	private String currencyCode = "1";
	// 交易金额，以元为单位，
	private String amount;
	// 交易备注
	private String description = "pay2account";
	// 验证收款方姓名标志
	private String correctName = "n";
	// 收款方还不是快钱用户时是否付款标志
	private String tempAccount = "y";
	// 订单号
	private String orderId;
	private String macVal;
	private String mac;

	public Pay2AccountEntity(String creditName_, String creditContact_, String currencyCode_,
			String amount_, String description_, String correctName_, String tempAccount_, String orderId_)
					throws UnsupportedEncodingException {
		this.key = Constants.accountKey;
		creditName = creditName_;
		creditContact = creditContact_;
		currencyCode = currencyCode_;
		amount = amount_;
		description = description_;
		correctName = correctName_;
		tempAccount = tempAccount_;
		orderId = orderId_;
		// 组合字符串。。必须按照此顺序组串
		macVal = creditContact + currencyCode + amount + orderId + key;
		mac = MD5Util.MD5(macVal).toUpperCase();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCreditName() {
		return creditName;
	}

	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}

	public String getCreditContact() {
		return creditContact;
	}

	public void setCreditContact(String creditContact) {
		this.creditContact = creditContact;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCorrectName() {
		return correctName;
	}

	public void setCorrectName(String correctName) {
		this.correctName = correctName;
	}

	public String getTempAccount() {
		return tempAccount;
	}

	public void setTempAccount(String tempAccount) {
		this.tempAccount = tempAccount;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getMacVal() {
		return macVal;
	}

	public void setMacVal(String macVal) {
		this.macVal = macVal;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}
	
}
