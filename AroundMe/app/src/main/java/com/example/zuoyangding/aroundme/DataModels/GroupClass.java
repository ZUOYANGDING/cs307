package com.example.zuoyangding.aroundme.DataModels;

import android.location.Location;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Kenny on 3/2/2017.
 */

public class GroupClass {

    public String groupName;
    public String key;
    public String topic;
    public long date;
    public boolean is_permanent;
    //public Location mlocation;
    public ArrayList<String> member_ids;
    public double lat;
    public double lon;
    private ArrayList<String> messageId;

    public GroupClass() {}



    public GroupClass(String groupName, String key, String topic, long date,
                      ArrayList<String> messageId, ArrayList<String> member_ids, double lat, double lon,
                      Boolean is_permanent) {


        this.groupName = groupName;
        this.key = key;
        this.topic = topic;
        this.date = date;
        this.member_ids = member_ids;
        this.is_permanent = is_permanent;
        this.lat = lat;
        this.lon = lon;
        this.messageId = messageId;
        //this.chartMessages = chartMessages;
    }

    //public GroupClass() {}

    public String getGroupName() {
        return this.groupName;
    }

//    public ArrayList<ChartMessage> getChartMessages() {
//        return this.chartMessages;
//    }
    public ArrayList<String> getMessageId () {return this.messageId;}
    public void setMessageId(ArrayList<String> messageId) {this.messageId = messageId;}

//    public void setChartMessages(ArrayList<ChartMessage> chartMessages) {
//        this.chartMessages = chartMessages;
//    }
}

