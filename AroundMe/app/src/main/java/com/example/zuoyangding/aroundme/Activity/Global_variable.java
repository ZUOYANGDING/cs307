package com.example.zuoyangding.aroundme.Activity;

import android.app.Application;
import android.widget.ImageView;

/**
 * Created by siyujiang on 3/3/17.
 */

public class Global_variable extends Application{
        private String user_id;


    public Global_variable(){
        user_id = "undefined";
    }
    public void setUser_id(String user_id){
        this.user_id = user_id;
    }
    public String getUser_id(){
        return this.user_id;
    }

}
