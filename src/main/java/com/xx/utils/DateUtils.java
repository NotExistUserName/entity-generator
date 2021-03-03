package com.xx.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xiexing
 * @discription 时间格式转换工具类
 * @date 2020/3/31
 */
public class DateUtils {

    public static final String TIME_24_WITH_RUNG = "yyyy-mm-dd HH:mm:ss";

    public static final String TIME_DAY_WITH_RUNG = "yyyy-mm-dd";

    public static final String TIME_24_WITH_SLASH = "yyyy/mm/dd HH:mm:ss";

    public static final String TIME_DAY_WITH_SLASH = "yyyy/mm/dd";

    public static final String TIME_24_WITH_CHINESE = "yyyy年mm月dd日 HH时mm分ss秒";

    public static final String TIME_DAY_WITH_CHINESE = "yyyy年mm月dd日";

    public static String format(Date target,String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(target);
    }
}
