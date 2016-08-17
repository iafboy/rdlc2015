package com.expocms.server.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class CommonUtil {
	
	private static Logger log = Logger.getLogger (CommonUtil.class);

    public static final int FULL_SCALED = 6;

    public static final String SUPER_GROUP_ID = "8a40c0353fa828a6013fa898d4ac0028";

    public static final String COMMON_GROUP_ID = "8a40c0353fa828a6013fa898d4ac0029";

    public static final String PRODUCT_TABLE_NAME = "rd_product";
    
    public static String SPERATOR = ",";

    public static final String ASSIGNMENT_TABLE_NAME = "rd_loan_agreement";


    // 把POJO转换为另一个POJO
    public static <T> void getObject (final Object o, final Object t) {
    	
    	Class c1 = o.getClass();
    	Class c2 = t.getClass();
    	Method[] methods = c1.getDeclaredMethods();
    	for (Method method : methods){
    		if (method != null && method.getName().startsWith("get")){
    			try {
    			String value;
    			value = method.invoke(o).toString();				
    			String paramName = reverseMethodName (method.getName ());
    			String setMethodName = reverseParamName(paramName);
    			Method setMethod = c2.getMethod(setMethodName, method.getReturnType());
    			CommonUtil.adapter(t, setMethod, method.getReturnType (), value);
    			} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}

    }


    private static String reverseMethodName (final String methodName) {
        // remove "set" to get the parameter name
        String paramName = methodName.substring (3);
        final char firstChar = paramName.charAt (0);
        final char toLower = Character.toLowerCase (firstChar);
        paramName = String.valueOf (toLower) + paramName.substring (1);
        return paramName;
    }


    private static String reverseParamName (final String paramName) {
        final char firstChar = paramName.charAt (0);
        final char toUpper = Character.toUpperCase (firstChar);
        final String methodName = "set" + String.valueOf (toUpper) + paramName.substring (1);
        return methodName;
    }


    private static <T> void adapter (final T t, final Method method, final Class<?> paramType,
            final String value) throws IllegalAccessException, InvocationTargetException {
        if (paramType == String.class) {
            method.invoke (t, value);
        } else if (paramType == Integer.class || paramType == int.class) {
            method.invoke (t, Integer.parseInt (value));
        } else if (paramType == Long.class || paramType == long.class) {
            method.invoke (t, Long.parseLong (value));
        } else if (paramType == Boolean.class || paramType == boolean.class) {
            method.invoke (t, Boolean.parseBoolean (value));
        } else if (paramType == Short.class || paramType == short.class) {
            method.invoke (t, Short.parseShort (value));
        } else if (paramType == Float.class || paramType == float.class) {
            method.invoke (t, Float.parseFloat (value));
        } else if (paramType == Double.class || paramType == double.class) {
            method.invoke (t, Double.parseDouble (value));
        } else if (paramType == Character.class || paramType == char.class) {
            final char[] cs = value.toCharArray ();
            if (cs.length > 1) {
                throw new IllegalArgumentException ("参数长度太大");
            }
            method.invoke (t, value.toCharArray ()[0]);
        } else if (paramType == List.class) {
        	if(value != null){
           String[] valueArray = value.split(SPERATOR);
           List<String> valueList = new ArrayList<String> ();
           for (String curValue : valueArray){
        	   valueList.add(curValue);
           }
           method.invoke (t, valueList);
        	}
        }
    }


    /**
     * Concatenate string.
     *
     * @param objs the objs
     * @return the string
     */
    public static String concatenateString (final Object... objs) {

        return concatenateWithSeparator (null, objs);
    }


    /**
     * Concatenate string with separator.
     *
     * @param separator the separator
     * @param objs the objs
     * @return the string
     */
    public static String concatenateWithSeparator (final String separator, final Object... objs) {

        final StringBuilder buf = new StringBuilder ();

        for (final Object obj: objs) {
            buf.append (obj);
            if (separator != null) {
                buf.append (separator);
            }
        }

        return buf.toString ();
    }
   

}
