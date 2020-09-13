package com.morrow.common.exception;

import com.morrow.common.util.MessageUtils;

/**
 * 自定义异常
 *
 * @Author Tomorrow
 * @Date 2020/9/6 1:25 上午
 */
public class CdException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private int code;
    private String msg;

    public CdException(int code) {
        this.code = code;
        this.msg = MessageUtils.getMessage(code);
    }

    public CdException(int code, String... params) {
        this.code = code;
        this.msg = MessageUtils.getMessage(code, params);
    }

    public CdException(int code, Throwable e) {
        super(e);
        this.code = code;
        this.msg = MessageUtils.getMessage(code);
    }

    public CdException(int code, Throwable e, String... params) {
        super(e);
        this.code = code;
        this.msg = MessageUtils.getMessage(code, params);
    }

    public CdException(String msg) {
        super(msg);
        this.code = ErrorCode.INTERNAL_SERVER_ERROR;
        this.msg = msg;
    }

    public CdException(String msg, Throwable e) {
        super(msg, e);
        this.code = ErrorCode.INTERNAL_SERVER_ERROR;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
