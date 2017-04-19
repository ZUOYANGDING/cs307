package com.example.zuoyangding.aroundme.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zuoyangding.aroundme.DataModels.User;
import com.example.zuoyangding.aroundme.R;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    private TextView registerTextView;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;
    private DatabaseReference userReference;
    private String userId;
    private String nickName;
    private String email;
    private String password;
    private String retypePassword;
    private EditText nickNameInput;
    private EditText emailInput;
    private EditText passwordInput;
    private EditText retypePasswordInput;
    private Button registration;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        registerTextView = (TextView) findViewById(R.id.textView2);
        nickNameInput = (EditText) findViewById(R.id.nickname_tx);
        emailInput = (EditText) findViewById(R.id.email_tx);
        passwordInput = (EditText) findViewById(R.id.password_tx);
        retypePasswordInput = (EditText) findViewById(R.id.retype_password_tx);
        registration  = (Button) findViewById(R.id.register_bt);

        registration.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homepage = new Intent(RegisterActivity.this, homepage.class);
                startActivity(homepage);
            }
        });
    }

    private void registerUser() {
        email = emailInput.getText().toString().trim();
        password = passwordInput.getText().toString().trim();
        retypePassword = retypePasswordInput.getText().toString().trim();
        nickName = nickNameInput.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter an email address", Toast.LENGTH_LONG).show();
            return;
        }
        if (!isValidEmail(email)) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_LONG).show();
            return;
        }
        if (!isValidNickName(nickName)) {
            Toast.makeText(this, "Please enter a valid userName", Toast.LENGTH_LONG).show();
            return;
        }
        if (!isValidNickNameLength(nickName)) {
            Toast.makeText(this, "Nick Name should between 3 to 20 characters", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(retypePassword)) {
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_LONG).show();
            return;
        } else {
            if (!isValidPasswordLength(password) || !isValidPasswordLength(retypePassword)) {
                Toast.makeText(this, "Password should between 6 to 20 characters", Toast.LENGTH_LONG).show();
                return;
            }
            if (!password.equals(retypePassword)) {
                Toast.makeText(this, "Password does not match", Toast.LENGTH_LONG).show();
                return;
            } else {
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            addUserToFirebase();
                            Toast.makeText(RegisterActivity.this, "Successfully registered, auto login", Toast.LENGTH_LONG).show();
                        } else {
                            String errorString = task.getException().toString();
                            String trunctederrorstring = errorString.substring(errorString.indexOf(":"));
                            Toast.makeText(RegisterActivity.this, "Registration Error "+trunctederrorstring, Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        }

    }

    private void addUserToFirebase() {
        userReference = mDatabase.child("Users");
        userId = firebaseAuth.getCurrentUser().getUid();

        user = new User(userId, email, password, null, nickName, null, null, new ArrayList<String>(), null, true, 0);

        user.setUserID(userId);
        user.setEmail(email);
        user.setPassword(password);
        user.setNickName(nickName);
        user.setReport(0);
        user.setPrivacy_mode(true);
        userReference.child(userId).setValue(user);
        userReference.child(userId).child("privacy_mode").setValue(true);
    }

    public boolean isValidEmail(String target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public boolean isValidNickNameLength(String nickName){
        if(nickName.length()>=3 && nickName.length()<=20){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isValidPasswordLength(String password){
        if(password.length()>=6 && password.length()<=20){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isValidNickName(String name) {
        String regx = "^[\\p{L} .'-]+$";
        return name.matches(regx);
    }
}
