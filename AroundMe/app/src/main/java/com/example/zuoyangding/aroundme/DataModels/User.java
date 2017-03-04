package com.example.zuoyangding.aroundme.DataModels;

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
    private List<String> groupIDs;
    //private String profile_image;
    //private String profile;

    public User(String userID, String googleAccount, String nickName, String birthday, String introduction, List<String> groupIDs) {
        this.userID = userID;
        this.nickName = nickName;
        this.birthday = birthday;
        this.introduction = introduction;
        this.googleAccount = googleAccount;
        this.nickName = nickName;
        this.groupIDs = groupIDs;
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

    public String getNickName(){
        return nickName;
    }
    public String getBirthday(){
        return birthday;
    }
    public String getIntroduction(){
        return introduction;
    }
    public List<String> getGroupIDs() {
        return this.groupIDs;
    }
    public void setGroupIDs(List<String> groupIDs) {
        this.groupIDs = groupIDs;
    }
}
