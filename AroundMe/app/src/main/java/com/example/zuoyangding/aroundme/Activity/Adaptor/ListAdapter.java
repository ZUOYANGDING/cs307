package com.example.zuoyangding.aroundme.Activity.Adaptor;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.zuoyangding.aroundme.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends ArrayAdapter<String> {
    private ArrayList<String> group_ids;
    private Activity context;
    public ListAdapter(Activity context, ArrayList<String> ids) {
        super(context, R.layout.list_element, ids);
        this.context = context;
        this.group_ids = ids;
    }

    public View getView(final int position, View v, ViewGroup parent) {
        if (v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.list_element,parent,false);
        }

        DatabaseReference mref = FirebaseDatabase.getInstance().getReference().child("Group");
        final View v1 = v;
        mref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                v1.setTag(dataSnapshot.child(group_ids.get(position)).child("key").getValue().toString());

                TextView t = (TextView) v1.findViewById(R.id.item1);
                t.setText(dataSnapshot.child(group_ids.get(position)).child("groupName").getValue().toString());
                TextView subt = (TextView) v1.findViewById(R.id.sub_item1);
                subt.setText(dataSnapshot.child(group_ids.get(position)).child("topic").getValue().toString());
                //String str = vi.getTag().toString();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("fetch group failed");
            }
        });
        return v;
    }

}