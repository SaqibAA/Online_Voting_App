package com.saqibaa.onlinevotingapp;

public class UserProfile {
    public String userName;
    public String userEmail;
    public String userMobile;
    public String userPassword;

    public UserProfile() {
    }

    public UserProfile(String userName, String userEmail, String userPhone, String userPassword) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userMobile= userPhone;
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
