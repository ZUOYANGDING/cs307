package com.example.zuoyangding.aroundme.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zuoyangding.aroundme.R;

import static com.example.zuoyangding.aroundme.Activity.editLandingActivity.Birthday;
import static com.example.zuoyangding.aroundme.Activity.editLandingActivity.Nickname;
import static com.example.zuoyangding.aroundme.Activity.editLandingActivity.info;

public class LandingActivity extends AppCompatActivity implements View.OnClickListener{

    Button landing_Edit;

    TextView landing_Nickname;
    TextView landing_Birthday;
    TextView landing_info;

    //image module
    ImageView landing_iv;
    Button landing_change;
    //private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        Global_variable global_variable = (Global_variable)getApplicationContext();
        //global_variable.getUser_name();
        //global_variable.getIntroduction();
        landing_Edit = (Button) findViewById(R.id.landing_Edit);

        landing_Nickname = (TextView) findViewById(R.id.landing_Nickname);
        landing_Birthday = (TextView) findViewById(R.id.landing_Birthday);
        landing_info = (TextView) findViewById(R.id.landing_intro);
        //landing_iv = (ImageView) findViewById(R.id.imageButton);

        landing_Nickname.setText(global_variable.getUser_name());
        landing_Birthday.setText(global_variable.getBirthday());
        landing_info.setText(global_variable.getIntroduction());
        //landing_iv = global_variable.getProfile_pic();

        landing_Edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(LandingActivity.this, editLandingActivity.class);
                LandingActivity.this.startActivity(i);
            }
        });

        //image module
        landing_iv = (ImageView) findViewById(R.id.imageButton);
        landing_change = (Button) findViewById(R.id.change);
        landing_iv = global_variable.getProfile_pic();
        landing_change.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Global_variable global_variable = (Global_variable)getApplicationContext();
        //if (requestCode == 1 && requestCode == RESULT_OK && data != null){
            Uri imgUri = data.getData();
            landing_iv.setImageURI(imgUri);
            //global_variable.setImageURI_Pic(imgUri);
        //}
    }
}
