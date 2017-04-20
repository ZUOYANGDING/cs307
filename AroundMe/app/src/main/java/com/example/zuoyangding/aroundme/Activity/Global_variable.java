package com.example.zuoyangding.aroundme.Activity;

import android.app.Application;
import android.widget.ImageView;

/**
 * Created by siyujiang on 3/3/17.
 */

public class Global_variable extends Application{

    private String user_id;
<<<<<<< HEAD
    private String other_userid;
    //private boolean privacy_mode;
=======
    //private String other_userid;

>>>>>>> master2


    public Global_variable(){
        user_id = "undefined";

        //Add by Frank
<<<<<<< HEAD
        other_userid = "undefined";
        //privacy_mode = true;
=======
        //other_userid = "undefined";
        //temp_GroupId = "undefined";

>>>>>>> master2

    }

    public void setUser_id(String user_id){
        this.user_id = user_id;
    }
    public String getUser_id(){
        return this.user_id;
    }

    //Add by Frank
<<<<<<< HEAD
    public void setother_userid(String other_userid){
        this.other_userid = other_userid;
    }
    public String getother_userid(){
        return this.other_userid;
    }

    //public boolean getPrivacy_mode(){ return this.privacy_mode;}

=======
    //public void setother_userid(String other_userid){
     //   this.other_userid = other_userid;
    //}
    //public String getother_userid(){
        //return this.other_userid;
    //}
>>>>>>> master2
}
