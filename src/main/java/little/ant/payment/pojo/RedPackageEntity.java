package little.ant.payment.pojo;

public class RedPackageEntity implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3604694986801184754L;
	
	private String redPackageCode;
	private String active;
	private Long money;
	private String validDate;
	private Long restMoney;

	public String getRedPackageCode() {
		return redPackageCode;
	}
	public void setRedPackageCode(String redPackageCode) {
		this.redPackageCode = redPackageCode;
	}
	
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	
	public Long getMoney() {
		return money;
	}
	public void setMoney(Long money) {
		this.money = money;
	}
	
	public String getValidDate() {
		return validDate;
	}
	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	public Long getRestMoney() {
		return restMoney;
	}

	public void setRestMoney(Long restMoney) {
		this.restMoney = restMoney;
	}
	
}
