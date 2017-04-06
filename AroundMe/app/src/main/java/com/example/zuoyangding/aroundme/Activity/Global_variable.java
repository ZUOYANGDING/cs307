package com.example.zuoyangding.aroundme.Activity;

import android.app.Application;
import android.widget.ImageView;

/**
 * Created by siyujiang on 3/3/17.
 */

public class Global_variable extends Application{

    private String user_id;
    private String other_userid;
    private boolean privacy_mode;


    public Global_variable(){
        user_id = "undefined";

        //Add by Frank
        other_userid = "undefined";
        privacy_mode = true;

    }

    public void setUser_id(String user_id){
        this.user_id = user_id;
    }
    public String getUser_id(){
        return this.user_id;
    }

    //Add by Frank
    public void setother_userid(String other_userid){
        this.other_userid = other_userid;
    }
    public String getother_userid(){
        return this.other_userid;
    }

    public void changePrivacy_mode(){

        System.out.println("Mode is start with "+this.privacy_mode);

        if (this.privacy_mode == true) {
            this.privacy_mode = false;
        }
        else {
            this.privacy_mode = true;
        }

        System.out.println("Set to "+this.privacy_mode);
    }

    public boolean getPrivacy_mode(){ return this.privacy_mode;}

}
