package com.expocms.server.util;

import org.apache.log4j.Logger;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class ResultUtil {
    private static Logger logger = Logger.getLogger(ResultUtil.class);
	public static boolean getData(ResultSet rs, Object o) {
		Class<? extends Object> cls = o.getClass();
		Field[] flds = cls.getDeclaredFields();
		for(Field f : flds) {
			f.setAccessible(true);
			try {
				PropertyDescriptor pd = new PropertyDescriptor(f.getName(), cls);
				Method wM = pd.getWriteMethod();
				if(f.getType() == String.class) {
					wM.invoke(o,rs.getString(f.getName()));
				}else if(f.getType().toString().equals("int")){
                    wM.invoke(o,rs.getInt(f.getName()));
                }else if(f.getType().toString().equals("long")){
                    wM.invoke(o,rs.getLong(f.getName()));
				}else if(f.getType().toString().equals("double")) {
                    wM.invoke(o, rs.getDouble(f.getName()));
               }else if(f.getType() == Timestamp.class){
					wM.invoke(o,rs.getTimestamp(f.getName()));
				}
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
				return false;
			}
		}
		return true;
	}

}
