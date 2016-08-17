package com.expocms.server.core.impl;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.IBaseHandler;
import com.expocms.server.request.types.impl.BorrowOrderRequest;
import com.expocms.server.request.types.impl.BuyProductRequest;
import com.expocms.server.request.types.impl.CurrentPeriodBuyRequest;
import com.expocms.server.request.types.impl.ForgetLoginPwdRequest;
import com.expocms.server.request.types.impl.ForgetTradingPwdRequest;
import com.expocms.server.request.types.impl.GetOrderIDRequest;
import com.expocms.server.request.types.impl.LoginRequest;
import com.expocms.server.request.types.impl.ManageMoneyProductRequest;
import com.expocms.server.request.types.impl.ModifyBankInfoRequest;
import com.expocms.server.request.types.impl.ModifyLoginPwdRequest;
import com.expocms.server.request.types.impl.ModifyTradingPwdRequest;
import com.expocms.server.request.types.impl.MoreInfoRequest;
import com.expocms.server.request.types.impl.MyFeedbackRequest;
import com.expocms.server.request.types.impl.MyPropertyRequest;
import com.expocms.server.request.types.impl.PrepareBuyRequest;
import com.expocms.server.request.types.impl.ProductDetailsRequest;
import com.expocms.server.request.types.impl.ProductIntroductionRequest;
import com.expocms.server.request.types.impl.ProductListRequest;
import com.expocms.server.request.types.impl.RecommendRequest;
import com.expocms.server.request.types.impl.RedPackageExchangeRequest;
import com.expocms.server.request.types.impl.RedPackageRequest;
import com.expocms.server.request.types.impl.RegistRequest;
import com.expocms.server.request.types.impl.SendBankInfoRequest;
import com.expocms.server.request.types.impl.SendVerifyCodeRequest;
import com.expocms.server.request.types.impl.SetTradingPwdBoolRequest;
import com.expocms.server.request.types.impl.SharePortRequest;
import com.expocms.server.request.types.impl.VerifyPhoneRequest;
import com.expocms.server.request.types.impl.VerifyTradingPwdRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.AlRepayment;
import com.expocms.server.response.types.impl.AlSellOut;
import com.expocms.server.response.types.impl.AlSelling;
import com.expocms.server.response.types.impl.AlreadyBuyItem;
import com.expocms.server.response.types.impl.BorrowOrder;
import com.expocms.server.response.types.impl.BorrowOrderResponse;
import com.expocms.server.response.types.impl.CommonResponse;
import com.expocms.server.response.types.impl.CurrentBuyUser;
import com.expocms.server.response.types.impl.CurrentPeriodBuyResponse;
import com.expocms.server.response.types.impl.HelpCenter;
import com.expocms.server.response.types.impl.InvalidResponse;
import com.expocms.server.response.types.impl.LoginResponse;
import com.expocms.server.response.types.impl.ManageMoneyProductResponse;
import com.expocms.server.response.types.impl.MoreInfoResponse;
import com.expocms.server.response.types.impl.MyBankCard;
import com.expocms.server.response.types.impl.MyFeedbackResponse;
import com.expocms.server.response.types.impl.MyPropertyResponse;
import com.expocms.server.response.types.impl.PrepareBuyResponse;
import com.expocms.server.response.types.impl.ProductDetailInfo;
import com.expocms.server.response.types.impl.ProductDetailsResponse;
import com.expocms.server.response.types.impl.ProductIntroductionResponse;
import com.expocms.server.response.types.impl.ProductItem;
import com.expocms.server.response.types.impl.ProductListResponse;
import com.expocms.server.response.types.impl.Question;
import com.expocms.server.response.types.impl.RecommandProduct;
import com.expocms.server.response.types.impl.RecommendResponse;
import com.expocms.server.response.types.impl.RedPackageExchangeResponse;
import com.expocms.server.response.types.impl.RedPackageResponse;
import com.expocms.server.response.types.impl.RedeemItem;
import com.expocms.server.response.types.impl.RegistResponse;
import com.expocms.server.response.types.impl.SendVerifyCodeResponse;
import com.expocms.server.response.types.impl.SetTradingPwdBoolResponse;
import com.expocms.server.response.types.impl.SharePortResponse;
import com.expocms.server.response.types.impl.ShowFrame;
import com.expocms.server.response.types.impl.SupportBank;
import com.expocms.server.response.types.inf.BaseResponse;
import com.google.common.collect.Lists;

import little.ant.payment.pojo.RedPackageEntity;
import little.ant.rmi.service.RMIServiceInvoker;
import little.ant.sms.bo.SMSMsgEntity;

@Controller("TestHandler")
public class TestHandler implements IBaseHandler {

	private static long count = 1;

	@Transactional(timeout = 3000)
	public BaseResponse handleRequest(final IRequest request) {
		BaseResponse returnResp = null;
		// check the type of request and handle the business logic
		if (Constants.pcfg.containsValue(request.getClass().getName())) {
			if (request instanceof BuyProductRequest) {
				returnResp = new CommonResponse();
				returnResp.setResultCode(Constants.succCode);
				returnResp.setResultMsg(Constants.succMsg);
			} else if (request instanceof ForgetLoginPwdRequest) {
				returnResp = new CommonResponse();
				returnResp.setResultCode(Constants.succCode);
				returnResp.setResultMsg(Constants.succMsg);
			} else if (request instanceof ForgetTradingPwdRequest) {
				returnResp = new CommonResponse();
				returnResp.setResultCode(Constants.succCode);
				returnResp.setResultMsg(Constants.succMsg);
			} else if (request instanceof VerifyPhoneRequest) {
				returnResp = new CommonResponse();
				returnResp.setResultCode(Constants.succCode);
				returnResp.setResultMsg(Constants.succMsg);
			} else if (request instanceof LoginRequest) {
				returnResp = new LoginResponse();
				((LoginResponse) returnResp).setIdCard("IdCard");
				final List<MyBankCard> cardls = Lists.newArrayList();
				// for (int i = 0, size = 5; i < size; i++) {
				// final MyBankCard card = new MyBankCard ();
				// card.setBankCard ("bankCard" + i);
				// card.setCPPhone ("cPPhone" + i);
				// cardls.add (card);
				// }
				final MyBankCard card = new MyBankCard();
				card.setBankCardId("00113133234324");
				card.setSupportPhone("010－87654321");
				card.setBankQuota(100000L);
				card.setIssuer("建设银行");
				final MyBankCard card2 = new MyBankCard();
				card2.setBankCardId("6222022000034218765");
				card2.setSupportPhone("010－59373499");
				card2.setBankQuota(50000L);
				card2.setIssuer("招商银行");
				cardls.add(card);
				((LoginResponse) returnResp).setMyBankCard(card);
				((LoginResponse) returnResp).setName("闫海通");
				((LoginResponse) returnResp).setUserId("1");
				((LoginResponse) returnResp).setIdCard("130533198706021765");
				((LoginResponse) returnResp).setLoginCount((int)count);
				returnResp.setResultCode(Constants.succCode);
				returnResp.setResultMsg(Constants.succMsg);
			} else if (request instanceof PrepareBuyRequest) {
				returnResp = new PrepareBuyResponse();
				((PrepareBuyResponse) returnResp).setBankInfo1("招商银行（尾号6789）");
				((PrepareBuyResponse) returnResp).setBankInfo2("单笔限额5万");
				((PrepareBuyResponse) returnResp).setRedPackageAll(Long.valueOf(1000000));
				returnResp.setResultCode(Constants.succCode);
				returnResp.setResultMsg(Constants.succMsg);
				((PrepareBuyResponse) returnResp).setProtocolURL("http://yjbys.com/xieyishu/fanben/462677.html");
			} else if (request instanceof ManageMoneyProductRequest) {
				returnResp = new ManageMoneyProductResponse();
				((ManageMoneyProductResponse) returnResp).setAlreadyRepaymentNumber(200);
				((ManageMoneyProductResponse) returnResp).setAlreadyRepaymentMoney(5000000);
				((ManageMoneyProductResponse) returnResp).setAlreadySellOutNumber(500);
				((ManageMoneyProductResponse) returnResp).setAlreadypaidMoney(7000000);
				final List<AlRepayment> alreadyRepayment = Lists.newArrayList();
				// for (int i = 0; i < 10; i++) {
				// final AlRepayment arp = new AlRepayment ();
				// arp.setProductName ("productname" + i);
				// arp.setAllEarnings (Long.valueOf (2000 + i));
				// arp.setPredictYearEarnings (100 + i);
				// alreadyRepayment.add (arp);
				// }
				final AlRepayment arp = new AlRepayment();
				arp.setProductName("房贷1005");
				arp.setAllEarnings(20000l);
				arp.setPredictYearEarnings(800);
				alreadyRepayment.add(arp);
				final AlRepayment arp2 = new AlRepayment();
				arp2.setProductName("车贷0023");
				arp2.setAllEarnings(50000l);
				arp2.setPredictYearEarnings(760);
				alreadyRepayment.add(arp2);
				final AlRepayment arp3 = new AlRepayment();
				arp3.setProductName("工薪0150");
				arp3.setAllEarnings(30000l);
				arp3.setPredictYearEarnings(110);
				alreadyRepayment.add(arp3);
				((ManageMoneyProductResponse) returnResp).setAlreadyRepayment(alreadyRepayment);
				final List<AlSellOut> alreadySellOut = Lists.newArrayList();
				// for (int i = 0; i < 10; i++) {
				// final AlSellOut ast = new AlSellOut ();
				// ast.setProductName ("productname" + i);
				// ast.setPredictYearEarnings (100 + i);
				// alreadySellOut.add (ast);
				// }
				final AlSellOut ast = new AlSellOut();
				ast.setProductName("房贷1004");
				ast.setProductId("13242341");
				ast.setPredictYearEarnings(85);
				ast.setAllEarnings(100L);
				alreadySellOut.add(ast);
				final AlSellOut ast2 = new AlSellOut();
				ast2.setProductName("车贷0022");
				ast2.setProductId("13242342");
				ast2.setPredictYearEarnings(95);
				ast2.setAllEarnings(120L);
				alreadySellOut.add(ast2);
				final AlSellOut ast3 = new AlSellOut();
				ast3.setProductName("工薪0149");
				ast2.setProductId("13242342");
				ast3.setPredictYearEarnings(80);
				ast3.setAllEarnings(150L);
				alreadySellOut.add(ast3);
				((ManageMoneyProductResponse) returnResp).setAlreadySellOut(alreadySellOut);
				final List<AlSelling> selling = Lists.newArrayList();
				// for (int i = 0; i < 10; i++) {
				// final AlSelling sel = new AlSelling ();
				// sel.setSellType (1);
				// sel.setProductName ("productname" + i);
				// sel.setAlreadySell (10);
				// sel.setPredictYearEarnings (100 + i);
				// sel.setInvestmentTimeLimit (13);
				// selling.add (sel);
				// }
				final AlSelling sel = new AlSelling();
				sel.setSellType(1);
				sel.setProductName("房贷1003");
				sel.setAlreadySell(10);
				sel.setPredictYearEarnings(72);
				sel.setInvestmentTimeLimit(13);
				selling.add(sel);
				final AlSelling sel2 = new AlSelling();
				sel2.setSellType(1);
				sel2.setProductName("车贷0021");
				sel2.setAlreadySell(10);
				sel2.setPredictYearEarnings(72);
				sel2.setInvestmentTimeLimit(13);
				selling.add(sel2);
				final AlSelling sel3 = new AlSelling();
				sel3.setSellType(1);
				sel3.setProductName("工薪0148");
				sel3.setAlreadySell(10);
				sel3.setPredictYearEarnings(72);
				sel3.setInvestmentTimeLimit(13);
				selling.add(sel3);
				((ManageMoneyProductResponse) returnResp).setSelling(selling);
			} else if (request instanceof ProductListRequest) {
				final int page = ((ProductListRequest) request).getPage();
				final int size = ((ProductListRequest) request).getPageCount();
				returnResp = new ProductListResponse();
				final List<ProductItem> list = Lists.newArrayList();
				// for (int i = 0 * page; i < 0 * page + size; i++) {
				// final ProductItem productItem = new ProductItem ();
				// productItem.setProductId ("pd" + i);
				// productItem.setProductName ("pdn" + i);
				// productItem.setAllEarnings (2000L + i);
				// productItem.setPredictYearEarnings (100L + i);
				// list.add (productItem);
				// }
				final ProductItem productItem = new ProductItem();
				productItem.setProductId("100001023");
				productItem.setProductName("房贷1006");
				productItem.setAllEarnings(4000L);
				productItem.setPredictYearEarnings(920l);
				list.add(productItem);
				final ProductItem productItem2 = new ProductItem();
				productItem2.setProductId("100002035");
				productItem2.setProductName("车贷0024");
				productItem2.setAllEarnings(5000L);
				productItem2.setPredictYearEarnings(750l);
				list.add(productItem2);
				final ProductItem productItem3 = new ProductItem();
				productItem3.setProductId("100003076");
				productItem3.setProductName("工薪0151");
				productItem3.setAllEarnings(20000L);
				productItem3.setPredictYearEarnings(800l);
				list.add(productItem3);
				((ProductListResponse) returnResp).setList(list);
				((ProductListResponse) returnResp).setPage(page);
				((ProductListResponse) returnResp).setPageCount(size);
			} else if (request instanceof ProductDetailsRequest) {
				returnResp = new ProductDetailsResponse();
				final ProductDetailInfo info = new ProductDetailInfo();
				info.setProductName("123");
				info.setAllMoney(12300L);
				info.setAlreadySell(10L);
				info.setMaySellMoney(12300L);
				info.setPredictYearEarnings(100L);
				info.setInvestmentTimeLimit(13L);
				info.setLowMoney(12300L);
				info.setBuyNumber(123L);
				info.setStartDate("2015-06-07");
				info.setEndDate("2016-12-17");
				info.setPoundage(10000L);
				info.setCurrentBuyUserNumber(59L);
				info.setRedPackageMoney(10000L);
				((ProductDetailsResponse) returnResp).setInfo(info);
				returnResp.setResultCode(Constants.succCode);
				returnResp.setResultMsg(Constants.succMsg);
			} else if (request instanceof ModifyBankInfoRequest) {
				returnResp = new CommonResponse();
				returnResp.setResultCode(Constants.succCode);
				returnResp.setResultMsg(Constants.succMsg);

			} else if (request instanceof ModifyLoginPwdRequest) {
				returnResp = new CommonResponse();
				returnResp.setResultCode(Constants.succCode);
				returnResp.setResultMsg(Constants.succMsg);

			} else if (request instanceof ModifyTradingPwdRequest) {
				returnResp = new CommonResponse();
				returnResp.setResultCode(Constants.succCode);
				returnResp.setResultMsg(Constants.succMsg);

			} else if (request instanceof MyFeedbackRequest) {
				returnResp = new MyFeedbackResponse();
				final List<Question> questionList = Lists.newArrayList();
				final Question question = new Question();
				final Question question1 = new Question();
				questionList.add(question);
				questionList.add(question1);
				((MyFeedbackResponse) returnResp).setQuestionList(questionList);
				returnResp.setResultCode(Constants.succCode);
				returnResp.setResultMsg(Constants.succMsg);
			} else if (request instanceof MyPropertyRequest) {
				returnResp = new MyPropertyResponse();
				((MyPropertyResponse) returnResp).setAllEarnings(Long.valueOf(80000));
				((MyPropertyResponse) returnResp).setYesterdayEarnings(Long.valueOf(10000));
				((MyPropertyResponse) returnResp).setAllProperty(Long.valueOf(80000));
				final List<RedeemItem> redeemItems = Lists.newArrayList();
				final RedeemItem redeemItem = new RedeemItem();
				redeemItem.setCurrentPoperty(Long.valueOf(12300));
				redeemItem.setAllEarnings(Long.valueOf(200));
				redeemItem.setPrincipal(Long.valueOf(10000));
				redeemItem.setPredictEarnings(Long.valueOf(10000));
				redeemItem.setValueDate("2015-05-06");
				redeemItem.setExpireDate("2016-07-09");
				redeemItem.setPredictGetTime("2015-09-08");
				redeemItem.setPredictYearEarnings(100);
				redeemItem.setBuyTime("2015-09-07");
				redeemItem.setBank("建设银行(尾号0987)");
				redeemItem.setProductId("13242432342");
				redeemItem.setNumccieURL("http://www.roadoor.com/");
				redeemItem.setProtocolUrl("http://www.roadoor.com/");
				redeemItems.add(redeemItem);
				final RedeemItem redeemItem1 = new RedeemItem();
				redeemItem1.setCurrentPoperty(Long.valueOf(12300));
				redeemItem1.setAllEarnings(Long.valueOf(200));
				redeemItem1.setPrincipal(Long.valueOf(10000));
				redeemItem1.setPredictEarnings(Long.valueOf(10000));
				redeemItem1.setValueDate("2015-05-06");
				redeemItem1.setExpireDate("2016-07-05");
				redeemItem1.setPredictGetTime("2015-09-08");
				redeemItem1.setPredictYearEarnings(100);
				redeemItem1.setBuyTime("2015-03-07");
				redeemItem1.setBank("建设银行(尾号0787)");
				redeemItem1.setProductId("13242432343");
				redeemItem1.setNumccieURL("http://www.roadoor.com/");
				redeemItem1.setProtocolUrl("http://www.roadoor.com/");
				redeemItems.add(redeemItem1);
				((MyPropertyResponse) returnResp).setRedeemItem(redeemItems);
				final List<AlreadyBuyItem> alreadybuyItems = Lists.newArrayList();
				final AlreadyBuyItem abi = new AlreadyBuyItem();
				abi.setAllEarnings(10000L);
				abi.setBank("招商银行");
				abi.setBuyTime("2015-08-09");
				abi.setCurrentPoperty(1000L);
				abi.setExpireDate("2016-08-09");
				abi.setNumccieURL("http://weibo.com/p/1001603885229780668278");
				abi.setPredictEarnings(100L);
				abi.setPredictGetTime("2016-08-09");
				abi.setPredictYearEarnings(100);
				abi.setProductId("123");
				abi.setProtocolUrl(
						"http://baike.baidu.com/link?url=212Kmk5qxMfFrhLdPKnQanQxAkslhwiUagtdnv3HEm_4yfuajCyVluZr0uFigdVlMOtfJT4pplbFBy2xp4cx5q");
				abi.setValueDate("2016-08-09");
				alreadybuyItems.add(abi);
				final AlreadyBuyItem abi1 = new AlreadyBuyItem();
				abi1.setAllEarnings(10000L);
				abi1.setBank("建设银行");
				abi1.setBuyTime("2015-08-09");
				abi1.setCurrentPoperty(1000L);
				abi1.setExpireDate("2016-08-09");
				abi1.setNumccieURL("http://weibo.com/p/1001603885229780668278");
				abi1.setPredictEarnings(100L);
				abi1.setPredictGetTime("2016-08-09");
				abi1.setPredictYearEarnings(100);
				abi1.setProductId("124");
				abi1.setProtocolUrl("http://www.diyifanwen.com/fanwen/jishuhetong/201010742208038706151.htm");
				abi1.setValueDate("2016-08-09");
				alreadybuyItems.add(abi1);
				((MyPropertyResponse) returnResp).setAlreadybuyItem(alreadybuyItems);
				final List<BorrowOrder> borrowOrders = Lists.newArrayList();
				final BorrowOrder borrowOrder = new BorrowOrder();
				borrowOrder.setNumber1("cf23");
				borrowOrder.setStartDate("2015-08-09");
				borrowOrder.setInvestType("零投");
				borrowOrder.setApplyMoney(Long.valueOf(1000));
				borrowOrder.setRealityMoney(Long.valueOf(500));
				borrowOrder.setPredictEarnings(98);
				borrowOrder.setNumber2("ac123");
				borrowOrder.setName("张三");
				borrowOrder.setIdCard("123456789");
				borrowOrder.setIdentity("工薪");
				borrowOrder.setLendMoney("500");
				borrowOrder.setPurpose("个人资金周转");
				borrowOrder.setStartRepayment("2015-12-19");
				borrowOrders.add(borrowOrder);
				// ((MyPropertyResponse)
				// returnResp).setBorrowOrder(borrowOrders);
				returnResp.setResultCode(Constants.succCode);
				returnResp.setResultMsg(Constants.succMsg);
			} else if (request instanceof RecommendRequest) {
				returnResp = new RecommendResponse();
				((RecommendResponse) returnResp).setAllProperty(Long.valueOf(10000));
				((RecommendResponse) returnResp).setAllUser(100);
				((RecommendResponse) returnResp).setProId("1324234");
				final List<ShowFrame> showframe = Lists.newArrayList();
				final ShowFrame sf = new ShowFrame();
				sf.setImageURL("http://i3.sinaimg.cn/cj/2012/0727/U7198P31DT20120727110434.jpg");
				sf.setWebURL("http://finance.sina.com.cn/money/lczx/20120727/110512688881.shtml");
				showframe.add(sf);
				final ShowFrame sf1 = new ShowFrame();
				sf1.setImageURL("http://www.wdzx.com/data/attachment/portal/201508/21/144617y5yymnhsfy5myuz9.jpg");
				sf1.setWebURL("http://www.wdzx.com/article-10655-1.html");
				showframe.add(sf1);
				final ShowFrame sf2 = new ShowFrame();
				sf2.setImageURL("http://www.yirendai.com/uploads/allimg/141117/28-14111G23426210.jpg");
				sf2.setWebURL("http://www.yirendai.com/caifuwenku/licaizhishi/201411171763.html");
				showframe.add(sf2);
				((RecommendResponse) returnResp).setShowFrame(showframe);
				final RecommandProduct rp = new RecommandProduct();
				rp.setProductName("生意通");
				rp.setWhetherSellOut("NO");
				rp.setAllMoney(123);
				rp.setAlreadySell(123);
				rp.setMaySellMonty(123);
				rp.setPredictYearEarnings(100);
				rp.setInvestmentTimeLimit(13);
				rp.setLowMoney(123);
				rp.setBuyNumber(123);
				rp.setStartDate("2015-06-07");
				rp.setEndDate("2016-12-17");
				rp.setPoundage(100);
				rp.setCurrentBuyUserNumber(59);
				final List<CurrentBuyUser> currentBuyUsers = Lists.newArrayList();
				final CurrentBuyUser cbu = new CurrentBuyUser();
				cbu.setPhoneNo("13811442903");
				cbu.setBuyTime("2015-06-07");
				cbu.setBuyMoney(Long.valueOf(100000));
				currentBuyUsers.add(cbu);
				final CurrentBuyUser cbu2 = new CurrentBuyUser();
				cbu2.setPhoneNo("13712443452");
				cbu2.setBuyTime("2015-06-08");
				cbu2.setBuyMoney(Long.valueOf(50000));
				currentBuyUsers.add(cbu2);
				// rp.setCurrentBuyUser (currentBuyUsers);
				final List<SupportBank> supportBanks = Lists.newArrayList();
				final SupportBank sb = new SupportBank();
				sb.setBank("工商银行");
				sb.setQuota(Long.valueOf(100000));
				sb.setIcon("www.icbc.com.cn");
				supportBanks.add(sb);
				rp.setSupportBank(supportBanks);
				((RecommendResponse) returnResp).setRecommendProduct(rp);
				returnResp.setResultCode(Constants.succCode);
				returnResp.setResultMsg(Constants.succMsg);
			} else if (request instanceof RedPackageExchangeRequest) {
				returnResp = new RedPackageExchangeResponse();
				((RedPackageExchangeResponse) returnResp).setRedPackageMoney(Long.valueOf(1200));
				final List<RedPackageEntity> redPackageList = Lists.newArrayList();
				final RedPackageEntity redPackage = new RedPackageEntity();
				redPackage.setActive("NO");
				redPackage.setMoney(Long.valueOf(2000));
				redPackage.setRedPackageCode("2E3ED5338DA4E");
				redPackage.setValidDate("2015-04-05 至 2016-09-07");
				redPackageList.add(redPackage);
				final RedPackageEntity redPackage1 = new RedPackageEntity();
				redPackage1.setActive("NO");
				redPackage1.setMoney(Long.valueOf(2000));
				redPackage1.setRedPackageCode("2E3ED5338DA4F");
				redPackage1.setValidDate("2015-04-05 至 2016-10-07");
				redPackageList.add(redPackage1);
				final RedPackageEntity redPackage2 = new RedPackageEntity();
				redPackage2.setActive("NO");
				redPackage2.setMoney(Long.valueOf(2000));
				redPackage2.setRedPackageCode("2E3ED5338DA40");
				redPackage2.setValidDate("2015-06-05 至 2016-10-07");
				redPackageList.add(redPackage2);
//				((RedPackageExchangeResponse) returnResp).setRedPackageList(redPackageList);
				returnResp.setResultCode(Constants.succCode);
				returnResp.setResultMsg(Constants.succMsg);
			} else if (request instanceof RedPackageRequest) {
				returnResp = new RedPackageResponse();
				((RedPackageResponse) returnResp).setRedPackageMoney(Long.valueOf(1200));
				final List<RedPackageEntity> redPackageList = Lists.newArrayList();
				final RedPackageEntity redPackage = new RedPackageEntity();
				redPackage.setActive("NO");
				redPackage.setMoney(Long.valueOf(1000));
				redPackage.setRedPackageCode("1E3ED5338DA4E");
				redPackage.setValidDate("2014-04-05 至 2016-09-07");
				redPackageList.add(redPackage);
				final RedPackageEntity redPackage1 = new RedPackageEntity();
				redPackage1.setActive("NO");
				redPackage1.setMoney(Long.valueOf(2000));
				redPackage1.setRedPackageCode("5E3ED5338DA4E");
				redPackage1.setValidDate("2015-04-05 至 2016-09-07");
				redPackageList.add(redPackage1);
				final RedPackageEntity redPackage2 = new RedPackageEntity();
				redPackage2.setActive("NO");
				redPackage2.setMoney(Long.valueOf(5000));
				redPackage2.setRedPackageCode("3E3ED5338DA4E");
				redPackage2.setValidDate("2013-04-05 至 2016-09-07");
				redPackageList.add(redPackage2);
				((RedPackageResponse) returnResp).setRedPackageList(redPackageList);
				returnResp.setResultCode(Constants.succCode);
				returnResp.setResultMsg(Constants.succMsg);
			} else if (request instanceof RegistRequest) {
				returnResp = new RegistResponse();
				((RegistResponse) returnResp).setUserId("1111");
				((RegistResponse) returnResp).setRedPackageMoney(6000L);
				((RegistResponse) returnResp).setRedPackageCode("exxx2xxx1xxx353");
				returnResp.setResultCode(Constants.succCode);
				returnResp.setResultMsg(Constants.succMsg);
			} else if (request instanceof VerifyTradingPwdRequest) {
				returnResp = new CommonResponse();
				returnResp.setResultCode(Constants.succCode);
				returnResp.setResultMsg(Constants.succMsg);
			} else if (request instanceof SendVerifyCodeRequest) {
				returnResp = new SendVerifyCodeResponse();
				((SendVerifyCodeResponse) returnResp).setVerifyCode("123456");
				returnResp.setResultCode(Constants.succCode);
				returnResp.setResultMsg(Constants.succMsg);
			} else if (request instanceof SharePortRequest) {
				returnResp = new SharePortResponse();
				((SharePortResponse) returnResp).setShareTitle("分享标题");
				((SharePortResponse) returnResp).setShareContent("分享内容");
				((SharePortResponse) returnResp).setShareimageUrl("www.baidu.com");
				returnResp.setResultCode(Constants.succCode);
				returnResp.setResultMsg(Constants.succMsg);
			} else if (request instanceof BorrowOrderRequest) {
				returnResp = new BorrowOrderResponse();
				((BorrowOrderResponse) returnResp).setLoanNumber("cf23");
				((BorrowOrderResponse) returnResp).setStartDate("2015-08-09 15:00");
				((BorrowOrderResponse) returnResp).setInvestType("零投");
				((BorrowOrderResponse) returnResp).setApplyMoney(1000L);
				((BorrowOrderResponse) returnResp).setRealityMoney(500L);
				((BorrowOrderResponse) returnResp).setSurplusMoney(500L);
				((BorrowOrderResponse) returnResp).setPredictEarnings(98L);
				((BorrowOrderResponse) returnResp).setBorrowingNumber("ac123");
				((BorrowOrderResponse) returnResp).setName("张三");
				((BorrowOrderResponse) returnResp).setIdCard("123456789");
				((BorrowOrderResponse) returnResp).setIdentity("工薪");
				((BorrowOrderResponse) returnResp).setLendMoney(500L);
				((BorrowOrderResponse) returnResp).setPurpose("个人资金周转");
				((BorrowOrderResponse) returnResp).setStartRepayment("2015-12-19");
				((BorrowOrderResponse) returnResp).setStartDate("2015-12-19");
			} else if (request instanceof GetOrderIDRequest) {
				returnResp = new CommonResponse();
				returnResp.setResultCode(Constants.succCode);
				returnResp.setResultMsg(Constants.succMsg);
			} else if (request instanceof MoreInfoRequest) {
				returnResp = new MoreInfoResponse();
				((MoreInfoResponse) returnResp).setAppidURL("http://123.57.152.218:8888/jf/platform/login");
				((MoreInfoResponse) returnResp).setGuanwangURL("http://123.57.152.218:8888/jf/platform/login");
				List<HelpCenter> helpCentre = Lists.newArrayList();
				HelpCenter hc1 = new HelpCenter();
				hc1.setText("收益稳定");
				hc1.setTitle("收益稳定吗");
				helpCentre.add(hc1);
				HelpCenter hc2 = new HelpCenter();
				hc2.setText("收益稳定");
				hc2.setTitle("是否保本");
				helpCentre.add(hc2);
				HelpCenter hc3 = new HelpCenter();
				hc3.setText("收益稳定");
				hc3.setTitle("收益快吗");
				helpCentre.add(hc3);
				HelpCenter hc4 = new HelpCenter();
				hc4.setText("绝对靠谱");
				hc4.setTitle("是否靠谱");
				helpCentre.add(hc4);
				HelpCenter hc5 = new HelpCenter();
				hc5.setText("扫描二维码");
				hc5.setTitle("如何推荐");
				helpCentre.add(hc5);
				((MoreInfoResponse) returnResp).setHelpCentre(helpCentre);
			} else if (request instanceof SendBankInfoRequest) {
				returnResp = new CommonResponse();
				returnResp.setResultCode(Constants.succCode);
				returnResp.setResultMsg(Constants.succMsg);
			} else if (request instanceof ProductIntroductionRequest) {
				returnResp = new ProductIntroductionResponse();
				((ProductIntroductionResponse) returnResp).setAllMoney(100000L);
				((ProductIntroductionResponse) returnResp).setPredictYearEarnings(100L);
				((ProductIntroductionResponse) returnResp).setInvestmentTimeLimit(13L);
				((ProductIntroductionResponse) returnResp).setStartDate("2015-06-07");
				((ProductIntroductionResponse) returnResp).setEndDate("2016-12-17");
				((ProductIntroductionResponse) returnResp).setGuanyuDateStr("到期日(7月7号)不计息,共13个计息日");
				((ProductIntroductionResponse) returnResp).setLowMoney(12300L);
				((ProductIntroductionResponse) returnResp).setPoundage(100L);
				((ProductIntroductionResponse) returnResp).setCloseDatestr("封闭期资产不可赎回");
				((ProductIntroductionResponse) returnResp).setPersontaxStr("个人所得税按照国家要求进行缴纳");
				((ProductIntroductionResponse) returnResp).setRedemptionStr("到期后系统自动发起赎回流程");
				returnResp.setResultCode(Constants.succCode);
				returnResp.setResultMsg(Constants.succMsg);
			} else if (request instanceof SetTradingPwdBoolRequest) {
				returnResp = new SetTradingPwdBoolResponse();
				((SetTradingPwdBoolResponse) returnResp).setSetTradingPwd(0);
				returnResp.setResultCode(Constants.succCode);
				returnResp.setResultMsg(Constants.succMsg);
			} else if (request instanceof CurrentPeriodBuyRequest) {
				returnResp = new CurrentPeriodBuyResponse();
				List<CurrentBuyUser> currentBuyUser = Lists.newArrayList();
				CurrentBuyUser cbu = new CurrentBuyUser();
				cbu.setBuyMoney(100000L);
				cbu.setBuyTime("2015-06-07 11:30");
				cbu.setPhoneNo("159****8650");
				currentBuyUser.add(cbu);
				CurrentBuyUser cbu1 = new CurrentBuyUser();
				cbu1.setBuyMoney(100000L);
				cbu1.setBuyTime("2015-09-23 11:30");
				cbu1.setPhoneNo("138****1980");
				currentBuyUser.add(cbu1);
				((CurrentPeriodBuyResponse) returnResp).setCurrentBuyUser(currentBuyUser);
				returnResp.setResultCode(Constants.succCode);
				returnResp.setResultMsg(Constants.succMsg);
			}

		} else {
			returnResp = new InvalidResponse(Constants.unsupportAPI,
					"API " + request.getClass().getName() + " is not found in system");
		}
		return returnResp;
	}

	private void sendSms(String phone,String msg) {
		try {
			RMIServiceInvoker smsServiceInvoker = new RMIServiceInvoker();
			SMSMsgEntity smsMsgEntity = new SMSMsgEntity();
			smsMsgEntity.setMsisdn(phone);
			smsMsgEntity.setMessage("" + msg + "（融道验证码），有效期5分钟，千万不要告诉别人哦！。【融道理财】");
			if (smsServiceInvoker.SendSMS(0, smsMsgEntity)) {
				System.out.println("sended");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
