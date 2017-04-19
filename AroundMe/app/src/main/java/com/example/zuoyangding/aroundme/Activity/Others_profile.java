package com.example.zuoyangding.aroundme.Activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zuoyangding.aroundme.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


////image module by Frank Hu
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;

//import static android.R.attr.bitmap;

/**
 * Created by Frank on 2017/4/6.
 */

public class Others_profile extends AppCompatActivity {

    private Button landing_Edit;
    private Button landing_homepage;
    private TextView landing_Nickname;
    private TextView landing_Birthday;
    private TextView landing_info;
    private FirebaseAuth firebaseAuth;
    private String Other_userId;

    private Button report;
    private FirebaseDatabase mDatabase;

    private Global_variable global_variable;
    private Button logout;

    //image module by Frank Hu
    private ImageView landing_iv;
    private String landing_imgStr;


    //private static int RESULT_LOAD_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_othersprofile);

        firebaseAuth = FirebaseAuth.getInstance();
        //userId = firebaseAuth.getCurrentUser().getUid();
        Global_variable global_variable = (Global_variable)getApplicationContext();
        Bundle b = getIntent().getExtras();
        Other_userId = b.getString("other_uid");
        landing_Edit = (Button) findViewById(R.id.landing_Edit);
        landing_Nickname = (TextView) findViewById(R.id.landing_Nickname);
        landing_Birthday = (TextView) findViewById(R.id.landing_Birthday);
        landing_info = (TextView) findViewById(R.id.landing_intro);



        //image module by Frank
        landing_iv = (ImageView) findViewById(R.id.profile_picture);
        //landing_iv.setOnClickListener(this);
        //landing_imgStr = null;

        DatabaseReference other_ref = FirebaseDatabase.getInstance().getReference().child("Users");

        other_ref.child(Other_userId).addValueEventListener(new ValueEventListener() {
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
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        report = (Button) findViewById(R.id.reportButton);
        mDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference ref = mDatabase.getReference();
        final String userID = global_variable.getUser_id();
        report.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        long reports = (long) dataSnapshot.child("Users").child(userID).child("report").getValue();

                        reports ++;

                        ref.child("Users").child(userID).child("report").setValue(reports);

                        Toast.makeText(Others_profile.this, "Thank you for your report", Toast.LENGTH_LONG).show();
                        report.setText("Reported");
                        report.setEnabled(false);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}


