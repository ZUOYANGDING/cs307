package com.example.zuoyangding.aroundme.Activity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.zuoyangding.aroundme.Activity.Adaptor.GroupListAdapter;
import com.example.zuoyangding.aroundme.DataModels.GroupClass;
import com.example.zuoyangding.aroundme.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by siyujiang on 4/6/17.
 */

public class Sort_by_topic extends AppCompatActivity {
    private DatabaseReference mGroupReference;
    //private DatabaseReference mUserRefernece;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private Location mLastLocation;
    private ListView listView;
    private String topic;
    private ImageButton homepageB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
        listView = (ListView)findViewById(R.id.group_list);
        homepageB = (ImageButton)findViewById(R.id.homepage_button);
        Bundle b = getIntent().getExtras();
        topic = b.getString("input_topic");
        mGroupReference = FirebaseDatabase.getInstance().getReference().child("Group");
        mGroupReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<GroupClass> temp = new ArrayList<GroupClass>();
                for (DataSnapshot it : dataSnapshot.getChildren()) {
                    //Location temp_lo = new Location(it.child("mlocation").getValue(Location.class));
                    if (it.child("topic").getValue().toString().toLowerCase().contains(topic.toLowerCase())) {
                        GroupClass group = new GroupClass(it.child("groupName").getValue().toString(),
                                it.child("key").getValue().toString(), it.child("topic").getValue().toString(),
                                it.child("date").getValue(Long.class), null, (ArrayList<String>) it.child("member_ids").getValue(),
                                it.child("lat").getValue(Double.class), it.child("lon").getValue(Double.class),
                                it.child("is_permanent").getValue(Boolean.class));
                        temp.add(group);
                    }
                }
                //GroupClass[] groups = new GroupClass[temp.size()];
                //groups = temp.toArray(groups);
                //MergeSort sorter = new MergeSort();
                //if (mLastLocation != null)
                //sorter.sort(groups, mLastLocation);
                //Arrays.sort(groups);
                //temp = new ArrayList<GroupClass>(Arrays.asList(groups));
                GroupListAdapter adapter = new GroupListAdapter(Sort_by_topic.this, temp);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        homepageB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Sort_by_topic.this, homepage.class);
                Sort_by_topic.this.startActivity(i);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View v = view;
                String gid = v.getTag().toString();
                //System.out.println(uid);
                Intent i = new Intent(Sort_by_topic.this, group_chat.class);
                i.putExtra("groupid",gid);
                startActivity(i);
            }
        });
    }
}
