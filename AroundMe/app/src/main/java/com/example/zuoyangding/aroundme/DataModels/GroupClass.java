package com.example.zuoyangding.aroundme.DataModels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kenny on 3/2/2017.
 */

public class GroupClass {

    public String groupName;
    public String key;
    public String topic;
    public long date;
    public ArrayList<String> member_ids;
    public GroupClass(String groupName, String key, String topic, long date, ArrayList<String> member_ids) {
        this.groupName = groupName;
        this.key = key;
        this.topic = topic;
        this.date = date;
        this.member_ids = member_ids;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public long getDate() {
        return this.date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public void setMember_ids(ArrayList<String> member_ids) {
        this.member_ids = member_ids;
    }

    public List<String> getMemberIDs() {
        return this.member_ids;
    }
}
