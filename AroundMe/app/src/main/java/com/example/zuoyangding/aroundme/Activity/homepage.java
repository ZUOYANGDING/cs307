package com.example.zuoyangding.aroundme.Activity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zuoyangding.aroundme.DataModels.GroupClass;
import com.example.zuoyangding.aroundme.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class homepage extends AppCompatActivity {

    ImageButton addGroupButton;
    ImageButton profileButton;

    ListView groupList;

    final ArrayList<GroupClass> nameList = new ArrayList<GroupClass>();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference().child("Group");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

//                GroupClass g = dataSnapshot.getValue(GroupClass.class);
//                Log.d("test", "test: " + g);

                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    Log.d("test", "test: " + child);
                    GroupClass g = child.getValue(GroupClass.class);
                    String groupName = g.groupName;
                    Log.d("test1", "grouName: " + groupName);

                    nameList.add(g);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        addGroupButton = (ImageButton) findViewById(R.id.addGroupButton);
        profileButton = (ImageButton) findViewById(R.id.profileButton);

        groupList = (ListView) findViewById(R.id.group_list);

        addGroupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(homepage.this, add_group.class);
                homepage.this.startActivity(i);
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(homepage.this, LandingActivity.class);
                homepage.this.startActivity(i);
            }
        });

        ListAdapter adapter = new ArrayAdapter<GroupClass>(this, android.R.layout.simple_list_item_1, nameList);
        groupList.setAdapter(adapter);
    }
}
