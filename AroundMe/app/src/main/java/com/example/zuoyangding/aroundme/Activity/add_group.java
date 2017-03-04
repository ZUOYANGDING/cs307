package com.example.zuoyangding.aroundme.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.zuoyangding.aroundme.DataModels.GroupClass;
import com.example.zuoyangding.aroundme.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_group extends AppCompatActivity {
    //private FirebaseDatabase mDatabase;

    private DatabaseReference mGroupReference;
    Button backButton;

    EditText groupName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        backButton = (Button) findViewById(R.id.createGroup);

        groupName = (EditText) findViewById(R.id.enterGroupName);
        mGroupReference = FirebaseDatabase.getInstance().getReference().child("Group");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = mGroupReference.child("Group").push().getKey();
                GroupClass group = new GroupClass(groupName.getText().toString(), key);
                mGroupReference.child(key).setValue(group);
                Intent i = new Intent(add_group.this, homepage.class);
                startActivity(i);
            }
        });

    }
}
