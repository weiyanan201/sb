package com.wei.eduyang.bean;

public class ResultEntity {

    public static int SUCCESS = 0 ;
    public static int ERROR = 1;

    private int returnCode ;
    private String returnMessage;
    private Object data;

    public ResultEntity(){
        this.returnCode = ERROR;
        this.returnMessage = "";
        this.data = null;
    }

    public ResultEntity(int returnCode, String returnMessage, Object data) {
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
        this.data = data;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "returnCode=" + returnCode +
                ", returnMessage='" + returnMessage + '\'' +
                ", data=" + data +
                '}';
    }
}
