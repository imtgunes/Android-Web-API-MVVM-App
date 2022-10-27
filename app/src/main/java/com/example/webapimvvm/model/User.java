package com.example.webapimvvm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("userID")
    @Expose
    private Integer userID;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("userSurName")
    @Expose
    private String userSurName;
    @SerializedName("userPassword")
    @Expose
    private String userPassword;
    @SerializedName("userMail")
    @Expose
    private String userMail;
    @SerializedName("userLogin")
    @Expose
    private String userLogin;
    @SerializedName("userProfileImage")
    @Expose
    private String userProfileImage;

    public User(Integer userID, String userName, String userSurName, String userProfileImage) {
        this.userID = userID;
        this.userName = userName;
        this.userSurName = userSurName;
        this.userProfileImage = userProfileImage;
    }

    public User(Integer userID, String userName, String userSurName, String userPassword, String userMail, String userLogin, String userProfileImage) {
        this.userID = userID;
        this.userName = userName;
        this.userSurName = userSurName;
        this.userPassword = userPassword;
        this.userMail = userMail;
        this.userLogin = userLogin;
        this.userProfileImage = userProfileImage;
    }



    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurName() {
        return userSurName;
    }

    public void setUserSurName(String userSurName) {
        this.userSurName = userSurName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserProfileImage() {
        return userProfileImage;
    }

    public void setUserProfileImage(String userProfileImage) {
        this.userProfileImage = userProfileImage;
    }

}