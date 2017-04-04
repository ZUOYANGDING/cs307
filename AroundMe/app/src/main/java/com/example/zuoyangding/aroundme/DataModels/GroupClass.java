package com.example.zuoyangding.aroundme.DataModels;

import android.location.Location;

import java.util.ArrayList;
import java.util.Comparator;

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
    public double alt;
    public double lon;


    public GroupClass(String groupName, String key, String topic, long date, ArrayList<String> member_ids, double alt, double lon, Boolean is_permanent) {
        this.groupName = groupName;
        this.key = key;
        this.topic = topic;
        this.date = date;
        this.member_ids = member_ids;
        this.is_permanent = is_permanent;
        this.alt = alt;
        this.lon = lon;
        //this.mlocation = mlocation;
    }

}
