package com.xlc.community.community.exception;

import org.omg.PortableInterceptor.INACTIVE;

public enum CustomizeErrorCode implements  ICustomizeErrorCode{


    QUSTION_NOT_FOUND(2001,"你找的问题不存在！！！！"),
    TARGET_PARAM_NOT_FOUND(2002, "未选中任何问题或评论进行回复"),
    NOT_LOGIN(2003, "未登录，请登录后再评论！"),
    ;
    private String message;
    private Integer code;

    CustomizeErrorCode(String message){
        this.message = message;
    }

    CustomizeErrorCode(Integer code,String message){
        this.code = code;
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer code() {
        return code;
    }

}
