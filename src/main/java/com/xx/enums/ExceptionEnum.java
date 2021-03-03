package com.xx.enums;

/**
 * @author xiexing
 * @discription 系统异常枚举类
 * @date 2020/3/25
 */
public enum ExceptionEnum {
    SUCCESS("200","成功"),
    SYSTEM_ERROR("500","系统异常"),
    ASSERT_ERROR("501","断言失败")
    ;

    private String code;
    private String desc;

    ExceptionEnum(String value, String desc) {
        this.code = value;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
