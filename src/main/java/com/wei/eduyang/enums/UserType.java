package com.wei.eduyang.enums;

public enum UserType {

    ADMIN("ADMIN",1),
    USER("USER",2);

    private String name;
    private int code;

    private UserType(String name,int code){
        this.name = name;
        this.code = code;
    }

    public static String getName(int index) {
        for (UserType userType : UserType.values()) {
            if (userType.getCode() == index) {
                return userType.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
