package com.example.zuoyangding.aroundme.Activity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.zuoyangding.aroundme.DataModels.GroupClass;
import com.example.zuoyangding.aroundme.R;

import java.util.ArrayList;

/**
 * Created by Kenny on 3/3/2017.
 */

public class adapter extends ArrayAdapter<GroupClass> {

    private ArrayList<GroupClass> aList;
    Context context;

    private class ViewHolder {
        TextView groupList;
    }

    public adapter(ArrayList<GroupClass> aList, Context context) {
        super(context, R.layout.activity_homepage_list, aList);
        this.aList = aList;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        GroupClass dataModel = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_homepage_list, null);

            viewHolder.groupList = (TextView) convertView.findViewById(R.id.homepage_list_textView);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.groupList.setText(dataModel.groupName);

        return convertView;

    }
}
