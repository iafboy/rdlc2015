package little.ant.sms.bo;

public enum ErrorSMSCF {
	ERR1(-1,"应用程序异常"),
	ERR3(-3,"用户名密码错误或者用户无效"),
	ERR4(-4,"短信内容和备案的模板不一样"),
	ERR5(-5,"签名不正确"),
	ERR7(-7,"余额不足"),
	ERR8(-8,"通道错误"),
	ERR9(-9,"无效号码"),
	ERR10(-10,"签名内容不符合长度"),
	ERR11(-11,"用户有效期过期"),
	ERR12(-12,"黑名单"),
	ERR13(-13,"语音验证码的Amount参数必须是整形字符串"),
	ERR14(-14,"语音验证码的内容只能为数字"),
	ERR15(-15,"语音验证码的内容最长为6位"),
	ERR16(-16,"余额请求过于频繁，5秒才能取余额一次"),
	ERR17(-17,"非法IP");
	private long code;
	private String msg;
	private ErrorSMSCF(long code_, String msg_) {
        this.code = code_;
        this.msg = msg_;
    }
	public static String getDesc(long index) {
        for (ErrorSMSCF c : ErrorSMSCF .values()) {
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
