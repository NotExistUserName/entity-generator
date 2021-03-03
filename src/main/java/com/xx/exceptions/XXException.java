package com.xx.exceptions;

/**
 * @author xiexing
 * @discription 自定义运行时异常
 * @date 2020/3/25
 */
public class XXException extends RuntimeException {

    private String code;

    public XXException(String code,String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
