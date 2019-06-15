package com.nadia.totoro.util;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 字符串工具类
 */
public class StringUtil {

    //list转字符串，以;相隔
    public static String listToString(List<String> stringList) {
        if (stringList == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean flag = false;
        for (String string : stringList) {
            if (flag) {
                result.append(";");
            } else {
                flag = true;
            }
            result.append(string);
        }
        return result.toString();
    }

    //获取字符串
    public static String getString(String s) {
        String path = null;
        if (s == null)
            return "";
        for (int i = s.length() - 1; i > 0; i++) {
            s.charAt(i);
        }
        return path;
    }

    //long转字符串
    public static String longToString(Long time) {
        Date date = new Date(time); // long类型转成Date类型
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(date);

        return dateString;
    }

    public static String getPriceText(double storePrice) {
        return String.format("￥%s", storePrice);
    }


}
