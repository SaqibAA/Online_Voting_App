package com.saqibaa.onlinevotingapp;

public class UserProfile {
    String userName, userEmail, userMobile, voterImage, aadhaarImage, date, time, aadhaar_num, voter_num, role;

    public UserProfile() {
    }

    public UserProfile(String userName, String userEmail, String userMobile, String voterImage, String aadhaarImage, String date, String time, String aadhaar_num, String voter_num, String role) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userMobile = userMobile;
        this.voterImage = voterImage;
        this.aadhaarImage = aadhaarImage;
        this.date = date;
        this.time = time;
        this.aadhaar_num = aadhaar_num;
        this.voter_num = voter_num;
        this.role = role;
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

    public String getVoterImage() {
        return voterImage;
    }

    public void setVoterImage(String voterImage) {
        this.voterImage = voterImage;
    }

    public String getAadhaarImage() {
        return aadhaarImage;
    }

    public void setAadhaarImage(String aadhaarImage) {
        this.aadhaarImage = aadhaarImage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAadhaar_num() {
        return aadhaar_num;
    }

    public void setAadhaar_num(String aadhaar_num) {
        this.aadhaar_num = aadhaar_num;
    }

    public String getVoter_num() {
        return voter_num;
    }

    public void setVoter_num(String voter_num) {
        this.voter_num = voter_num;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}

