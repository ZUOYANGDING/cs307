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

    ImageButton backButton;
    Button addGroup;

    EditText groupName;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Group");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        backButton = (ImageButton) findViewById(R.id.imageButton2);
        addGroup = (Button) findViewById(R.id.createGroup);

        groupName = (EditText) findViewById(R.id.enterGroupName);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GroupClass group = new GroupClass();
                group.setGroupName(String. valueOf(groupName.getText()));


                Intent i = new Intent(add_group.this, homepage.class);
                startActivity(i);
            }
        });

        addGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GroupClass group = new GroupClass();

                group.setGroupName(String.valueOf(groupName.getText()));

                DatabaseReference inst = myRef.child(groupName.getText().toString()).push();

                inst.setValue(group);
            }
        });



    }
}
