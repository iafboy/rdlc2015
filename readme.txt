1.9.3
feature:
- 短信和支付功能使用TPS。
- 增加推荐和佣金功能。详细请参考融道二期需求《融道二期需求.docx》，《融道二期需求分析.docx》。
- 佣金提现历史、状态查询。佣金历史中需要记录预期到达日期（arrivalTime），预期到达日期是佣金提现申请日+2个工作日。
- 使用TPS的批量付款和批量付款查询功能。
- 借款协议中增加“项目简介”字段。
- 修改sendBankInfo接口，客户端可以设置银行卡信息，用于（没有购买过产品的用户）提取佣金。
- 佣金提现历史中增加手续费字段，并且按照申请时间倒序排序。
- 增加confirmBankInfo接口，绑定银行信息前需要验证码验证。
bugfix:
- sendBankInfo已经绑定过的身份证号不能再绑定到其它账号。


1.9.2
feature:
- 自动赎回：订单赎回后，自动通过快钱接口查询赎回状态，并发送短信通知客户。


1.9.1
bugfix:
- 红包兑换时，提示红包不属于当前用户。原因是兑换前红包id不在用户的activitygift字段中，是兑换成功后写入该字段的。


1.9.0
feature:
- 同时支持HTTP和HTTPS，目前HTTPS只支持单向认证（客户端认证服务器），当前支持的协议：TLS(v1/v2/v3)。


1.8.5
bugfix:
- 红包兑换时的问题：
-- 有效期限中，起始日期大于截止日期。
-- 红包截止日期多了1天，比如11/13兑换红包，有效期10天，起始日期是11/13，截止日期应该是11/22，而不是11/23。
-- 兑换不成功的红包不应该参与红包金额的计算，并回传给客户端。
- 登录接口中返回的银行名不正确。其实问题是web修改银行名时只更新了bankName字段，没有更新issuer字段，而登录接口返回的银行名来自于issuer。


1.8.4
bugfix:
- 重新整理所有短信信息，详情请参见文档“融道理财-短信内容-2015.11.12.xlsx”。


1.8.3
feature:
- 增加注册用的短信接口，主要给推广页面使用。
- 理财产品接口中，为在售产品增加产品总额的字段。
bugfix:
- 修改注册成功的短信内容，原来的内容有错。


1.8.2
feature:
- 理财产品接口中，去掉非新手看不到新手专享产品的限制，但是还是不能买。

bugfix
- 修正红包兑换后客户端无法收到（兑换）红包的信息问题。


1.8.1
bugfix
- 用户购买的订单，购买产品的用户，查询出的列表都需要做降序排序，最新买的显示在最前。
- 当银行每笔支付限额小于1万时，显示为0，不对。比如5000元，应该显示为0.5万。
- 红包期限中“到”改成“至”。
- 当前购买用户列表中，时间显示为"年-月-日 时:分:秒"，不需要毫秒。


1.8.0
feature:
- 增加登录密码输入错误次数记录。具体需求如下：
如果一个用户登录app，连续6次输错登录密码，那么当天他、她就不能继续登录了，即使后来密码输对了。但是可以通过登录页面的忘记密码功能找回密码就可以登录了。如果不找回密码第二天才能继续尝试登录。
- 理财产品接口中，产品计息周期也需要根据当天日期计算得到，不能用预设的计息周期。同产品介绍、产品详情、精品推荐接口。
- 银行信息（单笔和单日支付限额）需要存数据库，这样可以随时根据最新数据做调整。

bugfix
- 红包接口（REDPACKAGEHandler和REDPACKAGEEXCHANGEHandler），返回给客户端的RedPackageCode应该是短id，而不是长id。
- REGISTHandler和QUICKPAYMENTHandler接口中，注册红包和购买红包需要存储activateDate（以前没有存），导致红包接口拿不到activateDate数据，客户端显示出现问题。


1.7.0
bugfix
- 到期日短信内容中是固定的“新手专享”，应该显示具体的产品名称和序号。
- 从身份证号中获取年龄和性别的方法存在隐患。


1.6.0
bugfix
- 年龄没有读取。支付时没有从身份证号中读取年龄，导致订单中年龄字段为空。
- 红包有效期只需显示到日xxxx-xx-xx，不需要显示到时间。
- 购买成功的短信内容有错别字。到期日的第二天上午10：00要发到期的短信。


1.5.0
bugfix:
- amount(F) to Chinese convert wrong.
- wrong in contact_1, need to change it in DB and in DB meta sql.


1.4.0
bugfix:
- at next day, MyProperty will not be refreshed (calculated) automatically (there's no trigger when new day arrived).


1.3.0
feature:
- add handling of purchase gift rollback.
-- delay apply purchase gift (to user) until order is committed by undoQuickPayment.
-- in undoQuickPayment, if success, commit the purchase gift to user.
-- in undoQuickPayment, if failed, remove purchase gift record.


1.2.0
feature:
- undoQuickPayment.
- myProperty cache.


1.1.0
feature:
- reduce operation on rd_order table.


1.0.0
feature:
- init REL version.






