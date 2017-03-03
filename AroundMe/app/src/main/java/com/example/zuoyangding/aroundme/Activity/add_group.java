package com.example.zuoyangding.aroundme.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.zuoyangding.aroundme.DataModels.GroupClass;
import com.example.zuoyangding.aroundme.R;

public class add_group extends AppCompatActivity {

    ImageButton backButton;

    EditText groupName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        backButton = (ImageButton) findViewById(R.id.imageButton2);

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

    }
}
