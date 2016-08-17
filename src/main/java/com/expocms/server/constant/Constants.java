package com.expocms.server.constant;

import java.text.SimpleDateFormat;
import java.util.Properties;

import org.springframework.context.ApplicationContext;

import com.google.common.collect.HashBiMap;

/**
 * Created by DAbing on 2014-10-25.
 */
public final class Constants {

    public static boolean httpgzip = false;
    public static boolean authheard = false;
    public static String enccode = "exwings";
    public static String authseed = "the application's hashcode";

    public static final String ExtAction = "ExtRequestAction";
    public static final String ExtHandler = "ExtBaseHandler";

    public static final HashBiMap<String, String> pcfg = HashBiMap.create ();

    // -------------------------------------------------
    public static ApplicationContext applicationContext;
    public static Properties propertiesConstants;

    // --------------------------------------------------
    public static String mainCfg = "SimpleAppSrv.xml";
    // --------------------------------------------------
    public static String log4jCfg = "log4j.properties";
    public static String dbUser = "root";
    public static String dbpwd = "root";
    public static String dbUrl = "jdbc:mysql://localhost:3306/airshow?autoReconnect=true&useUnicode=true&characterEncoding=utf8";
    // --------------------------------------------------
    public static int servicePort = 38888;
    public static int serviceSslPort = 39433;
    public static int TPS_port = 13000;
    public static String sslCertificate = "SimpleCmsAPPSrv.cer";
    public static String sslStorePwd = "rdlc2015";
    public static String readlog = "ReqHandler.log";
    public static String dbpoolCfg = "druidpool.properties";

    // ---------result code------------------------------
    public static int pageItemnumber = 5;
    public static int packageMaxPercent = 2;
    
    public final static int succCode = 0;
    public static String succMsg = "成功";
    public static int failCode = -1;
    public static String failMsg = "失败";

    public static String rmiserivce = "rmi://127.0.0.1:6600/AppSrvRmiService";
    public final static String handlerPath = "com.expocms.server.core.impl";
    public final static int unsupportAPI = -1;
    public static String accountKey = "YKB4WI4GS6UDNZXN";

    public static int poundage = 100;
    public static final int SOLD_AMOUNT_FACT = 100;
    public static int RED_PACKAGE_IFX = 100;
    
    public static boolean TEST_IF_ENABLED = false;

    public static String[] productType = { "新手专享", "精品推荐", "热售产品", "火爆产品" };

    public static String registerFixedGift = "1";
    public static String purchaseFixedGift = "2";
    public static String purchase2FixedGift = "3";
    public static int fixedgiftDisabled = 2;

    public static String registerFixedGiftString  = "注册红包";
    public static String purchaseFixedGiftString  = "首次投资红包";
    public static String purchase2FixedGiftString = "二次投资红包";

    public static final SimpleDateFormat SDF_SHORT = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat SDF_LONG = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat SDF_LONG2 = new SimpleDateFormat("yyyyMMddHHmmss");
    public static final SimpleDateFormat SDF_DETAILS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
    public static final SimpleDateFormat SDF_SHORT_CHN = new SimpleDateFormat("yyyy年MM月dd日");
    public static final SimpleDateFormat SDF_LONG_CHN = new SimpleDateFormat("yyyy年MM月dd日hh:mm");
    public static final SimpleDateFormat SDF_IDCARD_15 = new SimpleDateFormat("yyMMdd");
    public static final SimpleDateFormat SDF_IDCARD_18 = new SimpleDateFormat("yyyyMMdd");
    
    public static final String pattern_ymd_hms = "yyyy-MM-dd HH:mm:ss"; // pattern_ymdtime
    
    public static String contractfilepath="C:\\WebServer\\RDLC\\uploads\\contracts";
	public static String usercontractfilepath="C:\\WebServer\\RDLC\\uploads\\usercontracts";
	
	public static int commissionPercent = 1;
	
	public static final String KQ_CODE_TICKET_IN_PROGRESS_1 = "C0";
	public static final String KQ_CODE_TICKET_IN_PROGRESS_2 = "68";
	public static final String KQ_CODE_TICKET_SUCCESS = "00";
	public static final String KQ_REFERENCE_NO_PREFIX = "REF_";

}
