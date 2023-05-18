package com.example.sstv.user;

import java.util.Date;

public class User {
    private String userId;
    private String password;
    private String profilePhoto;
    private String userNickname;
    private String userName;
    private String dateBirth;
    private String eMail;
    private String phone;
    private Date signDate;
    private int withdrawal;
    private String roll;
    private int coin;
    private int accumulatedViewers;
    private int totalStreamingAccumulatedTime;
    private int stRoll;
    private int userType;

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", profilePhoto='" + profilePhoto + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userName='" + userName + '\'' +
                ", dateBirth='" + dateBirth + '\'' +
                ", eMail='" + eMail + '\'' +
                ", phone='" + phone + '\'' +
                ", signDate=" + signDate +
                ", withdrawal=" + withdrawal +
                ", roll='" + roll + '\'' +
                ", coin=" + coin +
                ", accumulatedViewers=" + accumulatedViewers +
                ", totalStreamingAccumulatedTime=" + totalStreamingAccumulatedTime +
                ", stRoll=" + stRoll +
                ", userType=" + userType +
                '}';
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public int getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(int withdrawal) {
        this.withdrawal = withdrawal;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getAccumulatedViewers() {
        return accumulatedViewers;
    }

    public void setAccumulatedViewers(int accumulatedViewers) {
        this.accumulatedViewers = accumulatedViewers;
    }

    public int getTotalStreamingAccumulatedTime() {
        return totalStreamingAccumulatedTime;
    }

    public void setTotalStreamingAccumulatedTime(int totalStreamingAccumulatedTime) {
        this.totalStreamingAccumulatedTime = totalStreamingAccumulatedTime;
    }

    public int getStRoll() {
        return stRoll;
    }

    public void setStRoll(int stRoll) {
        this.stRoll = stRoll;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
