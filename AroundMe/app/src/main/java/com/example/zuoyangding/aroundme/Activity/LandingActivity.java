package com.example.zuoyangding.aroundme.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.zuoyangding.aroundme.DataModels.User;
import com.example.zuoyangding.aroundme.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


//image module by Frank Hu
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static android.R.attr.bitmap;
import static android.R.attr.mode;


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

    private Global_variable global_variable;
    private Button logout;

    //image module by Frank Hu
    private ImageView landing_iv;
    private String landing_imgStr;
    private Switch landing_switch;


    //private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        firebaseAuth = FirebaseAuth.getInstance();
        //userId = firebaseAuth.getCurrentUser().getUid();
        final Global_variable global_variable = (Global_variable)getApplicationContext();
        userId = global_variable.getUser_id();
        landing_Edit = (Button) findViewById(R.id.landing_Edit);
        landing_Nickname = (TextView) findViewById(R.id.landing_Nickname);
        landing_Birthday = (TextView) findViewById(R.id.landing_Birthday);
        landing_info = (TextView) findViewById(R.id.landing_intro);
        landing_homepage = (Button)findViewById(R.id.button3);
        logout = (Button) findViewById(R.id.logout_bt);

        //Add by Frank
        landing_switch = (Switch) findViewById(R.id.switch_privacy);
        userId = global_variable.getUser_id();
        //image module
        //landing_iv = (ImageView) findViewById(R.id.imageButton);
        landing_iv = (ImageView) findViewById(R.id.profile_picture);
        landing_iv.setOnClickListener(this);
        landing_imgStr = null;

        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    123);

            // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
            // app-defined int constant that should be quite unique

            //return;
        }

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

                //image module by Frank Hu
                if (dataSnapshot.child("imgStr").getValue() != null) {
                    landing_imgStr = (String) dataSnapshot.child("imgStr").getValue();

                    //Bitmap way
                    byte[] imageByte = Base64.decode(landing_imgStr,Base64.DEFAULT);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte,0,imageByte.length);
                    landing_iv.setImageBitmap(bitmap);

                    //Uri way
                    //Uri imgUri = Uri.parse(landing_imgStr);
                    //landing_iv.setImageURI(imgUri);
                }

                final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");
                ref.child(global_variable.getUser_id()).addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        boolean mode = (boolean)dataSnapshot.child("privacy_mode").getValue();
                        landing_switch.setChecked(mode);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //homepage.firebaseListAdapter.cleanup();
                firebaseAuth.signOut();
                finish();
                Intent login = new Intent(LandingActivity.this, LoginActivity.class);
                startActivity(login);
            }
        });

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

        //Add by Frank
        landing_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference switch_ref = FirebaseDatabase.getInstance().getReference().child("Users");
                switch_ref.child(global_variable.getUser_id()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //DataSnapshot usnap = dataSnapshot.child(global_variable.getUser_id());
                        boolean current_mode = (boolean)dataSnapshot.child("privacy_mode").getValue();
                        System.out.print(userId + "\' Current mode is " + current_mode + ". ");
                        if( current_mode == true) {
                            switch_ref.child(userId).child("privacy_mode").setValue(false);
                            current_mode = (boolean)dataSnapshot.child("privacy_mode").getValue();
                            System.out.println("Now set to : " + current_mode + ".");
                        } else {
                            switch_ref.child(userId).child("privacy_mode").setValue(true);
                            current_mode = (boolean)dataSnapshot.child("privacy_mode").getValue();
                            System.out.println("Now set to : " + current_mode + ".");
                        }
                        //global_variable.changePrivacy_mode();
                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, 1);
    }

    @Override     //image module by Frank Hu
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Global_variable global_variable = (Global_variable)getApplicationContext();

        //crash check
//        if (requestCode == 1 && requestCode == RESULT_OK && data != null){
        if (data != null){

            Uri imgUri = data.getData();
            landing_iv.setImageURI(imgUri);

            //Bitmap way
            Bitmap myBitmap = null;
            try {
                myBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imgUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            myBitmap.compress(Bitmap.CompressFormat.PNG,100, bos);
            byte[] imgByte = bos.toByteArray();
            this.landing_imgStr = Base64.encodeToString(imgByte, Base64.DEFAULT);

            //Uri way
            //this.landing_imgStr = imgUri.toString();

                final DatabaseReference changeImg_ref = FirebaseDatabase.getInstance().getReference().child("Users");
                changeImg_ref.child(global_variable.getUser_id()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        changeImg_ref.child(dataSnapshot.child("userID").getValue().toString()).child("imgStr").setValue(landing_imgStr);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
        }
    }


}
