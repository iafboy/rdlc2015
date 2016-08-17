package little.ant.sms.bo;

public enum ErrorSMSYES {
	ERR1(-1,"应用程序异常"),
	ERR3(-3,"用户名密码错误或者用户无效"),
	ERR5(-5,"签名不正确"),
	ERR6(-6,"含有敏感内容"),
	ERR7(-7,"余额不足"),
	ERR8(-8,"没有可用通道，或不在时间范围内"),
	ERR9(-9,"发送号码一次不能超过1000个"),
	ERR10(-10,"号码数量大于允许上限"),
	ERR11(-11,"号码数量小于允许下限"),
	ERR12(-12,"模板不匹配"),
	ERR13(-13,"Invalid Ip ip绑定用户，未绑定该ip"),
	ERR14(-14,"用户黑名单"),
	ERR15(-15,"系统黑名单"),
	ERR16(-16,"号码格式错误"),
	ERR17(-17,"无效号码"),
	ERR18(-18,"没有设置用户的固定下发扩展号，不能自定义扩展"),
	ERR19(-19,"强制模板通道，不能使用个性化接口"),
	ERR20(-20,"包含非法字符"),
	ERR21(-21,"没有找到对应的SubmitID设置"),
	ERR22(-22,"解密失败"),
	ERR23(-23,"查询余额过频繁");
	private long code;
	private String msg;
	private ErrorSMSYES(long code_, String msg_) {
        this.code = code_;
        this.msg = msg_;
    }
	public static String getDesc(long index) {
        for (ErrorSMSYES c : ErrorSMSYES .values()) {
            if (c.getCode() == index) {
                return c.getMsg();
            }
        }
        return null;
    }
    @Override
    public String toString() {
        return this.msg;
    }
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
    
}
