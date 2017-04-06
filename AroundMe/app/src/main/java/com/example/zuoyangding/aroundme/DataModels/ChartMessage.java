package com.example.zuoyangding.aroundme.DataModels;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zuoyangding on 4/3/17.
 */

public class ChartMessage {
    private String message;
    private String userId;
    private String key;
    private ArrayList<String> images;

    public ChartMessage(){}
    public ChartMessage(String message, String userId) {
        this.message = message;
        this.userId = userId;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage (String message) {this.message = message;}

    public void setMessageKey (String key) {this.key = key;}
    public String getMessageKey () {return this.key;}

    public String getUid() {return this.userId;}

    public void setImage(ArrayList<String> images) {this.images = images;}
    public ArrayList<String> getImage() {return this.images;}
}
