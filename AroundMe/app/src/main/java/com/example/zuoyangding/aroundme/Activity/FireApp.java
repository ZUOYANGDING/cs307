package com.example.zuoyangding.aroundme.Activity;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Kenny on 3/3/2017.
 */

public class FireApp extends Application{

    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}
