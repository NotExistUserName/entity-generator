package com.xx.asserts;

import com.xx.enums.ExceptionEnum;
import com.xx.exceptions.XXException;
import com.xx.utils.StringUtils;

import java.text.MessageFormat;

/**
 * @author xiexing
 * @discription 自定义断言类
 * @date 2020/3/30
 */
public class XXAssert {

    public static void isTrue(boolean target,String pattern,Object... arguments){
        if (!target) {
            throwsException(ExceptionEnum.SYSTEM_ERROR.getCode(),pattern,arguments);
        }
    }

    public static void notNull(Object target,String pattern,Object... arguments) {
        if (target == null) {
            throwsException(ExceptionEnum.SYSTEM_ERROR.getCode(),pattern,arguments);
        }
    }

    public static void notEmpty(String target,String pattern,Object... arguments) {
        if (StringUtils.isEmptyStr(target)) {
            throwsException(ExceptionEnum.SYSTEM_ERROR.getCode(),pattern,arguments);
        }
    }

    public static void throwsException(String code,String pattern,Object... arguments) {
        String msg = arguments != null && arguments.length > 0 ? MessageFormat.format(pattern,arguments) : pattern;
        throw new XXException(code, msg);
    }
}
