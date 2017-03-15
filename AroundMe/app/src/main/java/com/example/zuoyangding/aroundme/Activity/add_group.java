package com.example.zuoyangding.aroundme.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.zuoyangding.aroundme.DataModels.GroupClass;
import com.example.zuoyangding.aroundme.DataModels.User;
import com.example.zuoyangding.aroundme.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class add_group extends AppCompatActivity {
    //private FirebaseDatabase mDatabase;

    private DatabaseReference mGroupReference;
    //private DatabaseReference mUserRefernece;
    Button backButton;

    EditText groupName;
    EditText groupTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        backButton = (Button) findViewById(R.id.createGroup);

        groupName = (EditText) findViewById(R.id.enterGroupName);
        groupTopic = (EditText) findViewById(R.id.Topics);
        mGroupReference = FirebaseDatabase.getInstance().getReference().child("Group");
        //mUserRefernece = FirebaseDatabase.getInstance().getReference().child("Users");
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String key = mGroupReference.child("Group").push().getKey();
                long start_date = System.nanoTime();
                ArrayList<String> usr_ids = new ArrayList<String>();
                //User new_u;

                Global_variable global_variable = (Global_variable)getApplicationContext();
                final DatabaseReference mref = FirebaseDatabase.getInstance().getReference().child("Users");
                mref.child(global_variable.getUser_id()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArrayList<String> group_ids = (ArrayList<String>) dataSnapshot.child("groupIDs").getValue();
                        User new_u;
                        if(group_ids != null) {
                            group_ids.add(key);
                            mref.child(dataSnapshot.child("userID").getValue().toString()).child("groupIDs").setValue(group_ids);

                        }else {
                            group_ids = new ArrayList<String>();
                            group_ids.add(key);
                            new_u = new User(dataSnapshot.child("userID").getValue().toString(),
                                    dataSnapshot.child("googleAccount").getValue().toString(),
                                    dataSnapshot.child("nickName").getValue().toString(),
                                    dataSnapshot.child("birthday").getValue().toString(),
                                    dataSnapshot.child("introduction").getValue().toString(),
                                    group_ids);
                            mref.child(dataSnapshot.child("userID").getValue().toString()).setValue(new_u);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                GroupClass group = new GroupClass(groupName.getText().toString(), key, groupTopic.getText().toString(), start_date, usr_ids);
                mGroupReference.child(key).setValue(group);
                //mUserRefernece.child(global_variable.getUser_id()).setValue(new_u);
                Intent i = new Intent(add_group.this, homepage.class);
                startActivity(i);
            }
        });

    }
}
