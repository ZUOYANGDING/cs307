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
    public Location mlocation;
    public ArrayList<String> member_ids;
    private List<ChartMessage> chartMessages;

    public GroupClass() {}
    public GroupClass(String groupName, String key, String topic, long date, List<ChartMessage> chartMessages, ArrayList<String> member_ids, Location mlocation, Boolean is_permanent) {
        this.groupName = groupName;
        this.key = key;
        this.topic = topic;
        this.date = date;
        this.member_ids = member_ids;
        this.is_permanent = is_permanent;
        this.mlocation = mlocation;
        this.chartMessages = chartMessages;
    }

    //public GroupClass() {}

    public String getGroupName() {
        return this.groupName;
    }

    public List<ChartMessage> getChartMessages() {
        return this.chartMessages;
    }

    public void setChartMessages(List<ChartMessage> chartMessages) {
        this.chartMessages = chartMessages;
    }
}

