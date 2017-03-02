package com.example.zuoyangding.aroundme.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.zuoyangding.aroundme.R;

import static com.example.zuoyangding.aroundme.Activity.editLandingActivity.Birthday;
import static com.example.zuoyangding.aroundme.Activity.editLandingActivity.Nickname;
import static com.example.zuoyangding.aroundme.Activity.editLandingActivity.info;


public class LandingActivity implements PicCutDemoActivity extends AppCompatActivity {

    Button landing_Edit;

    TextView landing_Nickname;
    TextView landing_Birthday;
    TextView landing_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        landing_Edit = (Button) findViewById(R.id.landing_Edit);

        landing_Nickname = (TextView) findViewById(R.id.landing_Nickname);
        landing_Birthday = (TextView) findViewById(R.id.landing_Birthday);
        landing_info = (TextView) findViewById(R.id.landing_intro);

        landing_Nickname.setText(Nickname);
        landing_Birthday.setText(Birthday);
        landing_info.setText(info);

        landing_Edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(LandingActivity.this, editLandingActivity.class);
                LandingActivity.this.startActivity(i);
            }
        });
    }
}
