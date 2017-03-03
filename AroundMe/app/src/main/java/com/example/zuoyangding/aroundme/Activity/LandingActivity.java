package com.example.zuoyangding.aroundme.Activity;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zuoyangding.aroundme.R;

import java.io.File;

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
    //Button landing_bUploadName;
    //EditText landing_etUploadName;
    //private static int RESULT_LOAD_IMAGE = 1;

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

        //image module
        landing_iv = (ImageView) findViewById(R.id.imageButton);
        landing_iv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //if (requestCode == 1 && requestCode == RESULT_OK && data != null){
            //Uri imgUri = Uri.parse("content://storage/emulated/0/DCIM/Camera/IMG_20160303_012710796.jpg");
            Uri imgUri = data.getData();
            landing_iv.setImageURI(imgUri);
        //}
    }
}
