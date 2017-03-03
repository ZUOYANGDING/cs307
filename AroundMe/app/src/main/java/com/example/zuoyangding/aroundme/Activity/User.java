package com.example.zuoyangding.aroundme.Activity;

import android.widget.ImageView;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by siyujiang on 3/3/17.
 */
@IgnoreExtraProperties
public class User {

        public String user_id;
        public String user_name;
        public String birthday;
        public String email;
        public String introduction;
        public ImageView profile_pic;

        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String user_id, String username, String email, String birthday, String introduction) {
            this.user_name = username;
            this.email = email;
            this.user_id = user_id;
            this.birthday = birthday;
            this.introduction = introduction;
        }
}
