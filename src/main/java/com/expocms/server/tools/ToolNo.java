package com.expocms.server.tools;

/**
 * Created by Administrator on 2015/9/27.
 */
public class ToolNo {

    public static final int DEF_LEN_6 = 6;
    public static final int DEF_LEN_4 = 4;
    public static final int DEF_LEN_8 = 8;
    public static final int DEF_LEN_10 = 10;
    public static final int STEP = 2;

    public static String getNextNo(String no){
        if(no == null || no.trim().length()==0){
            return "000001";
        }
        int len = no.trim().length();
        long max = getMaxByLen(len);
        if(Long.parseLong(no)==max){
            len = len + STEP;
        }
        long no_ = Long.parseLong(no);
        no_ += 1;
        String _re = no_ + "";
        for(int i = 0;i< len-(no_+"").length();i++){
            _re = "0"+_re;
        }
        return _re;
    }
    private static long getMaxByLen(int len){
        if(len==0){
            return 0;
        }
        String str = "";
        for (int i = 0;i< len;i++){
            str += "9";
        }
        return Long.parseLong(str);
    }
}
