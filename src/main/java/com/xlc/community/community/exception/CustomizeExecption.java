package com.xlc.community.community.exception;

/**
 *@创建人 xlc
 *@创建时间 2020-7-9
 *@描述 自定义异常类
 **/
public class CustomizeExecption extends RuntimeException{
    private String message;
    private Integer code;

    public CustomizeExecption(String message) {
        this.message = message;
    }

    public CustomizeExecption(ICustomizeErrorCode code) {
       this.code = code.code();
       this.message = code.getMessage();

    }

    @Override
    public String getMessage() {
        return message;
    }


    public Integer getCode() {
        return code;
    }

}
