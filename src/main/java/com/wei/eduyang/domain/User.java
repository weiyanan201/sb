package com.wei.eduyang.domain;

public class User {
    private int id;
    private String userName;
    private String userDesc;
    private String password;
    private int userType;
    private String phone;
    private String address;
    private String company;

    public User() {
    }

    public User(int id, String userName, String userDesc, String password, int userType, String phone, String address, String company) {
        this.id = id;
        this.userName = userName;
        this.userDesc = userDesc;
        this.password = password;
        this.userType = userType;
        this.phone = phone;
        this.address = address;
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userDesc='" + userDesc + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", company='" + company + '\'' +
                '}';
    }

}
