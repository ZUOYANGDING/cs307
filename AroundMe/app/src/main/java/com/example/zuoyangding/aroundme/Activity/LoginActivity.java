package com.example.zuoyangding.aroundme.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.zuoyangding.aroundme.DataModels.User;
import com.example.zuoyangding.aroundme.R;
import com.firebase.client.Firebase;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private SignInButton googleBtn;
    private static final int RC_SIGN_IN = 1;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    private static final String TAG = "LoginActivity";
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mDatabase;
    //private Firebase firebase;
    private Boolean authFlag = false;
    private DatabaseReference mUserReference;


    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("jump to login3");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        googleBtn = (SignInButton) findViewById(R.id.google_btn);

        mDatabase = FirebaseDatabase.getInstance();
        mUserReference = mDatabase.getReference().child("Users");

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                //String userID = user.getUid();
                //String email = user.getEmail();
                //mUserReference = mDatabase.getReference().child(userID);
                if (user != null) {
                    if (authFlag == false) {
                        final String userID = user.getUid();
                        System.out.println("here");
                        final String email = user.getEmail();

                        //mUserReference = mDatabase.getReference().child("Users").child(userID);
                        //System.out.println("the user is " + user.getEmail());
                        //String userID = user.getUid();
                        //                    if ( == null) {
                        //                        Intent register = new Intent(LoginActivity.this, LandingActivity.class);
                        //                        register.putExtra("userIDkey", userID);
                        //                        startActivity(register);
                        //                    } else {
                        //                        Intent home = new Intent(LoginActivity.this, homepage.class);
                        //                        startActivity(home);
                        //                    }
                        System.out.println("HERE IS THE USER ID GIVEN BY GOOGLE: " + userID);

                        mUserReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                //User uCheck = dataSnapshot.getValue(User.class);
                                if (!dataSnapshot.exists()) {
                                    User u = new User(userID, email, null, new ArrayList<String>());
                                    u.setGoogleAccount(email);
                                    u.setUserID(userID);
                                    System.out.println("I am here");
                                    mUserReference.child(userID).setValue(u);
                                    Intent register = new Intent(LoginActivity.this, LandingActivity.class);
                                    register.putExtra("userKey", userID);
                                    startActivity(register);
                                } else {
                                    System.out.println("jump to homepage");
                                    Intent home = new Intent(LoginActivity.this, homepage.class);
                                    startActivity(home);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                        //Intent next = new Intent(LoginActivity.this, LandingActivity.class);
                        //startActivity(next);
                        authFlag = true;
                    } else {

                    }
                } else {
                        //erro
                }
            }
        };

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(LoginActivity.this, "You Got and Error", Toast.LENGTH_LONG).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        googleBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    @Override //make android forget the signin
    protected void onDestroy(){
        //mAuth.getInstance().signOut();

        //System.out.println("i am here");
        mAuth.getInstance().signOut();
        super.onDestroy();


        //mAuth.getInstance().signOut();
    }



    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);

                Intent i = new Intent(LoginActivity.this, homepage.class);
                LoginActivity.this.startActivity(i);

            } else {
                // Google Sign In failed, update UI appropriately
                Log.d("Firebase", " Authorization error:" + result.getStatus());
            }
        }
    }


    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // ...
                    }
                });
    }


}
