package com.example.zuoyangding.aroundme.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.zuoyangding.aroundme.R;

/**
 * Created by Kenny on 2/28/2017.
 */

public class editLandingActivity extends AppCompatActivity {

    public static String Nickname;
    public static String Birthday;
    public static String info;

    TextView edit_landing_Nickname;
    TextView edit_landing_Birthday;
    TextView edit_landing_info;
    TextView edit_landing_error;

    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_landing);


        edit_landing_Nickname = (TextView) findViewById(R.id.edit_landing_Nickname);
        edit_landing_Birthday = (TextView) findViewById(R.id.edit_landing_Birthday);
        edit_landing_info = (TextView) findViewById(R.id.edit_landing_intro);
        edit_landing_error = (TextView) findViewById(R.id.edit_landing_error);


        saveButton = (Button) findViewById(R.id.edit_landing_Save);

        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Nickname = edit_landing_Nickname.getText().toString();
                Birthday = edit_landing_Birthday.getText().toString();
                info = edit_landing_info.getText().toString();

                if (Nickname.length() == 0 || Birthday.length() == 0) {
                    edit_landing_error.setText("Please fill in all fields");
                } else {
                    Intent i=new Intent(editLandingActivity.this, LandingActivity.class);
                    editLandingActivity.this.startActivity(i);
                }
            }
        });
    }
}
