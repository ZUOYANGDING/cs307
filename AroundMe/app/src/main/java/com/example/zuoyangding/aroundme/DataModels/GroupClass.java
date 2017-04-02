package com.example.zuoyangding.aroundme.DataModels;

import android.location.Location;

import java.util.ArrayList;

/**
 * Created by Kenny on 3/2/2017.
 */

public class GroupClass {

    public String groupName;
    public String key;
    public String topic;
    public long date;
    public boolean is_permanent;
    public Location mlocation;
    public ArrayList<String> member_ids;

    public GroupClass(String groupName, String key, String topic, long date, ArrayList<String> member_ids, Location mlocation, Boolean is_permanent) {
        this.groupName = groupName;
        this.key = key;
        this.topic = topic;
        this.date = date;
        this.member_ids = member_ids;
        this.is_permanent = is_permanent;
        this.mlocation = mlocation;
    }

}
