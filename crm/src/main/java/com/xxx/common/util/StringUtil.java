package com.xxx.common.util;

import java.util.Map;

/**
 * String工具类
 */
public class StringUtil {

    /** 将逗号隔开的字符串转换成Long[]数组 */
    public static Long[] toLongArray(String strs){
        String[] strArray = strs.split(",");
        Long[] longArray = new Long[strArray.length];
        for(int i = 0 ; i < strArray.length ; i++){
            longArray[i] = Long.valueOf(strArray[i]);
        }
        return longArray;
    }

    /** 首字母大写 */
    public static String upperCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    /** 判断字符串是否为空 */
    public static boolean isEmpty(String str){
        if(str == null||str.length()<= 0){
            return true;
        }
        return false;
    }

    /** 判断字符串是否为空 */
    public static boolean notEmpty(String str){
        if(str == null||str.length()<= 0){
            return false;
        }
        return true;
    }

    /** 为URL添加参数 */
    public static String addParams(Map<String, Object> map){
        StringBuilder url = new StringBuilder();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            url.append(entry.getKey());
            url.append("=");
            url.append(entry.getValue());
            url.append("&");
        }
        return url.toString();
    }
}
