package com.xx.utils;

/**
 * @author xiexing
 * @discription 字符串判断相关工具类
 * @date 2020/3/24
 */
public class StringUtils {

    public static boolean isEmptyStr(String targetUrl) {
        if (targetUrl == null || targetUrl.trim().length() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmptyStr(String targetUrl) {
        if (targetUrl != null && targetUrl.trim().length() > 0) {
            return true;
        }
        return false;
    }

    public static String toString(Object target) {
        return target == null ? "" : target.toString();
    }
}
