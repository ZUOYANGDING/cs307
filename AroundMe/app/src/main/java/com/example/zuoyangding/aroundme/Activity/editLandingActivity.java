package com.example.zuoyangding.aroundme.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zuoyangding.aroundme.DataModels.User;
import com.example.zuoyangding.aroundme.R;
import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kenny on 2/28/2017.
 */

public class editLandingActivity extends AppCompatActivity {

    public  String Nickname;
    public  String Birthday;
    public  String info;

    private TextView edit_landing_Nickname;
    private TextView edit_landing_Birthday;
    private TextView edit_landing_info;
    private TextView edit_landing_error;
    private DatabaseReference mDatabase;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_landing);
        mDatabase = FirebaseDatabase.getInstance().getReference();


        edit_landing_Nickname = (TextView) findViewById(R.id.edit_landing_Nickname);
        edit_landing_Birthday = (TextView) findViewById(R.id.edit_landing_Birthday);
        edit_landing_info = (TextView) findViewById(R.id.edit_landing_intro);
        edit_landing_error = (TextView) findViewById(R.id.edit_landing_error);


        saveButton = (Button) findViewById(R.id.edit_landing_Save);

        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Global_variable global_variable = (Global_variable)getApplicationContext();
                Nickname = edit_landing_Nickname.getText().toString();
                //global_variable.setUser_name(Nickname);

                Pattern p = Pattern.compile("[^0-9 ]", Pattern.CASE_INSENSITIVE);
                Matcher birthdayMatcher = p.matcher(edit_landing_Birthday.getText());
                Birthday = edit_landing_Birthday.getText().toString();

                boolean invalidBirthday = birthdayMatcher.find();

                if (invalidBirthday || Birthday.length() < 8) {
                    Toast.makeText(editLandingActivity.this, "Invalid birthday, please follow the format: 01012001" , Toast.LENGTH_LONG).show();
                }

                else {
                    //global_variable.setBirthday(Birthday);
                    info = edit_landing_info.getText().toString();
                    //global_variable.setIntroduction(info);
                    if (Nickname.length() == 0 || Birthday.length() == 0) {
                        Toast.makeText(editLandingActivity.this, "Please fill in all fields" , Toast.LENGTH_LONG).show();
                        //edit_landing_error.setText("Please fill in all fields");
                    } else {
                        final DatabaseReference edit_ref = FirebaseDatabase.getInstance().getReference().child("Users");
                        edit_ref.child(global_variable.getUser_id()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                User user = dataSnapshot.getValue(User.class);
                                if (user != null) {
                                    edit_ref.child(dataSnapshot.child("userID").getValue().toString()).child("nickName").setValue(Nickname);
                                    edit_ref.child(dataSnapshot.child("userID").getValue().toString()).child("introduction").setValue(info);
                                    edit_ref.child(dataSnapshot.child("userID").getValue().toString()).child("birthday").setValue(Birthday);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        Intent i = new Intent(editLandingActivity.this, LandingActivity.class);
                        editLandingActivity.this.startActivity(i);
                        finish();
                    }
                }
            }
        });

    }


}
