package com.xx.utils;

import com.xx.asserts.XXAssert;
import com.xx.enums.ExceptionEnum;
import com.xx.exceptions.XXException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xiexing
 * @discription 正则表达式工具类
 * @date 2020/3/30
 */
public class RegexUtils {

    private static final Logger logger = LoggerFactory.getLogger(RegexUtils.class);

    private static final String REGEX_PATTERN = "_\\w{1}";

    private static final Pattern PATTERN = Pattern.compile(REGEX_PATTERN);

    /**
     * 下划线转驼峰
     * @param target
     * @return
     */
    public static String underlineToCamel(String target,boolean needRemoveTitle){
        XXAssert.notEmpty(target,"转换目标值不能为空");
        //兼容表名前添加用户名的情况
        target = subUserName(target);
        Matcher matcher = PATTERN.matcher(target);
        while (matcher.find()) {
            String group = matcher.group();
            String camel = group.substring(1).toUpperCase();
            target = target.replace(group,camel);
        }
        if (needRemoveTitle) {
            //去除表名开头(例如t)
            target = target.substring(1);
        }
        return target;
    }

    /**
     * 将用户名去除,兼容用户写表名时写了数据库用户的情况
     * @param target
     * @return
     */
    public static String subUserName(String target) {
        if (StringUtils.isEmptyStr(target)) {
            return target;
        }
        int index = target.indexOf(".");
        if (index != -1) {
            String[] strings = target.split("\\.");
            try {
                target = strings[1];
            } catch (IndexOutOfBoundsException e) {
                logger.error("去除用户名失败,原因为{}",e.getMessage(),e);
                throw new XXException(ExceptionEnum.SYSTEM_ERROR.getCode(),"表名配置不合法");
            }
        }
        return target;
    }
}
