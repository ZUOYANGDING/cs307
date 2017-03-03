package com.example.zuoyangding.aroundme.Activity;

import android.app.Application;
import android.widget.ImageView;

/**
 * Created by siyujiang on 3/3/17.
 */

public class Global_variable extends Application{
        private String user_id;
        private String user_name;
        private String birthday;
        private String email;
        private String introduction;
        private ImageView profile_pic;

    public Global_variable(){
        user_id = "undefined";
        user_name = "307";
        birthday = "0 0 0";
        introduction = "This is a default profile";
    }
    public void setUser_id(String user_id){
        this.user_id = user_id;
    }
    public void setUser_name(String user_name){
        this.user_name = user_name;
    }
    public void setBirthday(String birthday){
        this.birthday = birthday;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setIntroduction(String introduction){
        this.introduction = introduction;
    }
    public void setProfile_pic(ImageView profile_pic){
        this.profile_pic = profile_pic;
    }
    public String getUser_id(){
        return this.user_id;
    }
    public String getUser_name(){
        return this.user_name;
    }
    public String getBirthday(){
        return this.birthday;
    }
    public String getIntroduction(){
        return this.introduction;
    }
    public String getEmail(){
        return this.email;
    }
    public ImageView getProfile_pic(){
        return this.profile_pic;
    }
}
