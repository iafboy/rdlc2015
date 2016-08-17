package little.ant.payment.pojo;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import com.expocms.server.constant.Constants;
import com.expocms.server.util.MD5Util;


public class Pay2BankEntity implements Serializable{
	private static final long serialVersionUID = 5387729862022173532L;
	// 客户编号所对应的密钥。。在账户邮箱中获取
	private String key;
	// 城市,中文字符 主要只需要城市名，不需要省份名。也不要带"市""自治区（县）"等
	private String province_city;
	// 银行名称 请填写银行的标准名,详见接口文档
	private String bankName;
	// 银行卡开户行的名称
	private String kaihuhang;
	// 收款人姓名,收款人的姓名，必须与银行卡账户名相同
	private String creditName;
	// 银行卡号
	private String bankCardNumber;
	// 交易金额 整数或小数 小数为两位以内 但小数点的最后一位不能为0 如：0.10不行。单位为人民币元
	private String amount;
	// 交易备注
	private String description;
	// 订单号
	private String orderId;
	private String macVal;
	private String mac;

	public Pay2BankEntity(String province_city_, String bankName_, String kuaihuhang_, String creditName_,
			String bankCardName_, String amount_, String description_, String orderId_)
					throws UnsupportedEncodingException {
		this.key = Constants.accountKey;
		this.province_city = province_city_;
		this.bankName = bankName_;
		this.kaihuhang = kuaihuhang_;
		this.creditName = creditName_;
		this.bankCardNumber=bankCardName_;
		this.amount=amount_;
		this.description=description_;
		this.orderId=orderId_;
		if (orderId == null || "".equals(orderId.trim()))
			orderId = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
		// 组合字符串。。必须按照此顺序组串
		macVal = bankCardNumber + amount + orderId + key;
		mac = MD5Util.MD5(macVal).toUpperCase();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getProvince_city() {
		return province_city;
	}

	public void setProvince_city(String province_city) {
		this.province_city = province_city;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getKaihuhang() {
		return kaihuhang;
	}

	public void setKaihuhang(String kaihuhang) {
		this.kaihuhang = kaihuhang;
	}

	public String getCreditName() {
		return creditName;
	}

	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}

	public String getBankCardNumber() {
		return bankCardNumber;
	}

	public void setBankCardNumber(String bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
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
