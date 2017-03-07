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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class add_group extends AppCompatActivity {
    //private FirebaseDatabase mDatabase;

    private DatabaseReference mGroupReference;
    private DatabaseReference mUserRefernece;
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
        mUserRefernece = FirebaseDatabase.getInstance().getReference().child("Users");
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = mGroupReference.child("Group").push().getKey();
                long start_date = System.nanoTime();
                ArrayList<String> usr_ids = new ArrayList<String>();
                Global_variable global_variable = (Global_variable)getApplicationContext();
                usr_ids.add(global_variable.getUser_id());
                ArrayList<String> group_ids = new ArrayList<String>();
                group_ids.add(key);
                User new_u = new User(global_variable.getUser_id(),
                        global_variable.getEmail(),
                        global_variable.getUser_name(),
                        global_variable.getBirthday(),
                        global_variable.getIntroduction(),group_ids);
                GroupClass group = new GroupClass(groupName.getText().toString(), key, groupTopic.getText().toString(), start_date, usr_ids);
                mGroupReference.child(key).setValue(group);
                mUserRefernece.child(global_variable.getUser_id()).setValue(new_u);
                Intent i = new Intent(add_group.this, homepage.class);
                startActivity(i);
            }
        });

    }
}
