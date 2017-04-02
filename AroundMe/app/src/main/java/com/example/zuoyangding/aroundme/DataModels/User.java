package com.example.zuoyangding.aroundme.DataModels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zuoyangding on 2/18/17.
 */

public class User {
    private String userID;
    private String googleAccount;
    private String nickName;
    private String introduction;
    private String  birthday;
    private ArrayList<String> groupIDs;
    private String email;
    private String password;

    //image module (by Frank Hu)
    private String imgStr;

    //private String profile_image;
    //private String profile;

    public User(String userID, String email, String password, String googleAccount, String nickName, String birthday, String introduction, ArrayList<String> groupIDs, String imgStr) {
        this.email = email;
        this.password = password;
        this.userID = userID;
        this.nickName = nickName;
        this.birthday = birthday;
        this.introduction = introduction;
        this.googleAccount = googleAccount;
        this.nickName = nickName;
        this.groupIDs = groupIDs;


        //image module (by Frank Hu)
        this.imgStr = imgStr;

    }
    public String getUserID() {
        return this.userID;
    }
    public void setUserID(String userID) {
        this.userID =userID;
    }
    public String getGoogleAccount() {
        return this.googleAccount;
    }
    public void setGoogleAccount(String googleAccount){
        this.googleAccount = googleAccount;
    }
    public void setNickName(String nickName) {this.nickName = nickName;}
    public String getNickName(){
        return nickName;
    }
    public String getBirthday(){
        return birthday;
    }
    public void setBirthday(String birthday) {this.birthday = birthday;}
    public void setIntroduction (String introduction) {this.introduction = introduction;}
    public String getIntroduction(){
        return introduction;
    }
    public List<String> getGroupIDs() {
        return this.groupIDs;
    }
    public void setGroupIDs(ArrayList<String> groupIDs) {
        this.groupIDs = groupIDs;
    }
    public void setEmail(String email) {this.email = email;}
    public String getEmail() {return this.email;}
    public void setPassword(String password) {this.password = password;}
    public String getPassword() {return this.password;}


    //image module (by Frank Hu)
    public String getImgBitmap() { return this.imgStr;}
    public void setImgStr(String imgStr) { this.imgStr = imgStr;}
}

