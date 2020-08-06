package com.xlc.community.community.exception;

import org.omg.PortableInterceptor.INACTIVE;

public enum CustomizeErrorCode implements  ICustomizeErrorCode{


    QUSTION_NOT_FOUND(2001,"你找的问题不存在！！！！"),
    TARGET_PARAM_NOT_FOUND(2002, "未选中任何问题或评论进行回复"),
    NOT_LOGIN(2003, "未登录，请登录后再评论！"),
    SYS_ERROR(2004,"服务器端异常"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"评论不存在"),
    COMMENT_NOT_EMPTY(2007,"评论内容不能为空！"),
    NOTIFICATION_NOT_FOUND(2008,"未找到通知!"),
    READ_NOTIFICATION_FAIL(2009,"通知读取失败!");

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

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
