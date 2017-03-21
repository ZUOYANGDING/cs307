package com.example.zuoyangding.aroundme.Activity;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zuoyangding.aroundme.DataModels.User;
import com.example.zuoyangding.aroundme.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//import static com.example.zuoyangding.aroundme.Activity.editLandingActivity.Birthday;
//import static com.example.zuoyangding.aroundme.Activity.editLandingActivity.Nickname;
//import static com.example.zuoyangding.aroundme.Activity.editLandingActivity.info;

public class LandingActivity extends AppCompatActivity implements View.OnClickListener{

    private Button landing_Edit;
    private Button landing_homepage;
    private TextView landing_Nickname;
    private TextView landing_Birthday;
    private TextView landing_info;
    private FirebaseAuth firebaseAuth;
    private String userId;
    //image module
    private ImageView landing_iv;
    private Button logout;
    private Global_variable global_variable;
    //private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        firebaseAuth = FirebaseAuth.getInstance();
        //userId = firebaseAuth.getCurrentUser().getUid();
        //System.out.println("this is UID" + userId);
        global_variable = (Global_variable)getApplicationContext();
        userId = global_variable.getUser_id();
        landing_Edit = (Button) findViewById(R.id.landing_Edit);
        landing_Nickname = (TextView) findViewById(R.id.landing_Nickname);
        landing_Birthday = (TextView) findViewById(R.id.landing_Birthday);
        landing_info = (TextView) findViewById(R.id.landing_intro);
        landing_homepage = (Button)findViewById(R.id.button3);
        logout = (Button) findViewById(R.id.logout_bt);
        DatabaseReference mref = FirebaseDatabase.getInstance().getReference().child("Users");

        mref.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //DataSnapshot usnap = dataSnapshot.child(global_variable.getUser_id());

                if(dataSnapshot.child("nickName").getValue() != null) {
                    landing_Nickname.setText(dataSnapshot.child("nickName").getValue().toString());
                } else {
                    landing_Nickname.setText("undefined");
                }
                if (dataSnapshot.child("birthday").getValue() != null) {
                    landing_Birthday.setText(dataSnapshot.child("birthday").getValue().toString());
                } else {
                    landing_Birthday.setText("undefined");
                }
                if (dataSnapshot.child("introduction").getValue() != null) {
                    landing_info.setText(dataSnapshot.child("introduction").getValue().toString());
                } else {
                    landing_info.setText("undefined");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homepage.firebaseListAdapter.cleanup();
                firebaseAuth.signOut();
                finish();
                Intent login = new Intent(LandingActivity.this, LoginActivity.class);
                startActivity(login);
            }
        });
        //landing_iv = (ImageView) findViewById(R.id.imageButton);
    /*
        landing_Nickname.setText(global_variable.getUser_name());
        landing_Birthday.setText(global_variable.getBirthday());
        landing_info.setText(global_variable.getIntroduction());
    */
        //landing_iv = global_variable.getProfile_pic();

        landing_Edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(LandingActivity.this, editLandingActivity.class);
                LandingActivity.this.startActivity(i);
            }
        });
        landing_homepage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(LandingActivity.this, homepage.class);
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
        Global_variable global_variable = (Global_variable)getApplicationContext();
        //if (requestCode == 1 && requestCode == RESULT_OK && data != null){
            //Uri imgUri = Uri.parse("content://storage/emulated/0/DCIM/Camera/IMG_20160303_012710796.jpg");
            Uri imgUri = data.getData();
            landing_iv.setImageURI(imgUri);
        //}
    }


}
