package com.example.zuoyangding.aroundme.Activity.Adaptor;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.zuoyangding.aroundme.DataModels.GroupClass;
import com.example.zuoyangding.aroundme.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GroupListAdapter extends ArrayAdapter<GroupClass> {
    private ArrayList<GroupClass> groups;
    private Activity context;
    public GroupListAdapter(Activity context, ArrayList<GroupClass> ids) {
        super(context, R.layout.list_element, ids);
        this.context = context;
        this.groups = ids;
    }

    public View getView(final int position, View v, ViewGroup parent) {
        if (v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.list_element,parent,false);
        }
        v.setTag(groups.get(position).key);
        final View vi = v;
                vi.setTag(groups.get(position).key);
                TextView t = (TextView) vi.findViewById(R.id.item1);
                t.setText(groups.get(position).groupName);
                TextView subt = (TextView) vi.findViewById(R.id.sub_item1);
                subt.setText("Topic: "+groups.get(position).topic);
                //String str = vi.getTag().toString();
        return v;
    }

}