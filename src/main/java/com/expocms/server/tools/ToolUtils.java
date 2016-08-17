package com.expocms.server.tools;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.expocms.server.constant.Constants;

/**
 * 公共工具类
 *
 * @author 董华健 2012-9-7 下午2:20:06
 */
public class ToolUtils {

    @SuppressWarnings ("unused")
    private static Logger log = Logger.getLogger (ToolUtils.class);


    /**
     * double精度调整
     *
     * @param doubleValue 需要调整的值123.454
     * @param format 目标样式".##"
     * @return
     */
    public static String decimalFormatToString (final double doubleValue, final String format) {
        final DecimalFormat myFormatter = new DecimalFormat (format);
        final String formatValue = myFormatter.format (doubleValue);
        return formatValue;
    }


    /**
     * 获取UUID by jdk
     *
     * @author 董华健 2012-9-7 下午2:22:18
     * @return
     */
    public static String getUuidByJdk (final boolean is32bit) {
        final String uuid = UUID.randomUUID ().toString ();
        if (is32bit) {
            return uuid.toString ().replace ("-", "");
        }
        return uuid;
    }


    public static String convertProductTypeIdToString (final String index) {
        int realIndex = 0;
        
        try {
            realIndex = Integer.parseInt(index);
        } catch(Exception e) {
            log.warn (String.format("product type index is not a valid numeric value."));
            return "产品";
        }
        
        if(realIndex <= 0 || realIndex > Constants.productType.length)
        	return "产品";

        return Constants.productType[realIndex - 1];
    }


    public static void main (final String[] args) {
        System.out.println (getUuidByJdk (true));
        System.out.println (convertProductTypeIdToString ("fds"));
    }


    public static boolean verifyPwd (final String realPwd, final String pwd) {
        if (realPwd == null) {
            return false;
        }
        return realPwd.equals (pwd);
    }


    public static Date getDate (final int days) {
        final SimpleDateFormat sdf = new SimpleDateFormat (Constants.pattern_ymd_hms);
        java.util.Date time = null;
        try {
            time = sdf.parse (sdf.format (new Date ()));
        } catch (final ParseException e) {
            e.printStackTrace ();
        }
        final Calendar cal = Calendar.getInstance ();
        cal.setTime (time);
        cal.add (Calendar.DAY_OF_MONTH, days);
        final Date calculatedDate = cal.getTime ();
        return calculatedDate;
    }

}
