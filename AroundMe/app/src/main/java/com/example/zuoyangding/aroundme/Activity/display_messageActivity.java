package com.example.zuoyangding.aroundme.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zuoyangding.aroundme.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class display_messageActivity extends AppCompatActivity {
    private Button addInto;
    private FirebaseDatabase mDatabase;
    private DatabaseReference groupReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_messages);


        Button addInto = (Button) findViewById(R.id.join_group);
        mDatabase = FirebaseDatabase.getInstance();
        groupReference = mDatabase.getReference().child("Group");
        addInto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
