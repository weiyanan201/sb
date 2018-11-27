package com.wei.eduyang.exception;

public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String excetionMsg ;

    public CustomException() {
        super();
    }

    public CustomException(String msg) {
        super(msg);
        this.excetionMsg = msg;
    }

    public String getExcetionMsg() {
        return excetionMsg;
    }

    public void setExcetionMsg(String excetionMsg) {
        this.excetionMsg = excetionMsg;
    }

}
