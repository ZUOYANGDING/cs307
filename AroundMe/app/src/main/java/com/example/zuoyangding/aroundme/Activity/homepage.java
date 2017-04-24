package com.example.zuoyangding.aroundme.Activity;

import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;

import android.widget.ListView;

import com.example.zuoyangding.aroundme.Activity.Adaptor.ListAdapter;
import com.example.zuoyangding.aroundme.DataModels.GroupClass;
import com.example.zuoyangding.aroundme.R;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class homepage extends AppCompatActivity {


    private ListView listView;
    //private ImageButton addGroupButton;
    private ImageButton profileButton;
    private ImageButton sortButton;
//    private Button logout;
    private FirebaseAuth mAuth;
    private String userId;
    static FirebaseListAdapter<String> firebaseListAdapter;
    private DatabaseReference ref;
    private FirebaseAuth.AuthStateListener mAuthListener;

    //image module by Frank Hu
    private String landing_imgStr;
    private ImageButton findroomate;
    private ImageButton startButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        listView = (ListView)findViewById(R.id.group_list);
//        logout = (Button) findViewById(R.id.logout_bt);
        mAuth = FirebaseAuth.getInstance();
        /*
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    userId = user.getUid();
                } else {
                    // User is signed out
                }
            }
        };
        mAuth.addAuthStateListener(mAuthListener);
        */
        //userId = firebaseAuth.getCurrentUser().getUid();
        //addGroupButton = (ImageButton) findViewById(R.id.addGroupButton);
        profileButton = (ImageButton) findViewById(R.id.profileButton);
        sortButton = (ImageButton)findViewById(R.id.homepage_button);
        startButton = (ImageButton) findViewById(R.id.favorites_button);

        //Add by Frank
        findroomate = (ImageButton) findViewById(R.id.roommate_button);

        final Global_variable global_variable = (Global_variable)getApplicationContext();
        //ArrayList<String> group_ids;

        ////image module by Frank Hu (update homepage user's avatar from firebase )
        DatabaseReference mref = FirebaseDatabase.getInstance().getReference().child("Users");
        mref.child(global_variable.getUser_id()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //image module by Frank Hu
                if (dataSnapshot.child("imgStr").getValue() != null) {
                    landing_imgStr = (String) dataSnapshot.child("imgStr").getValue();

                    //Bitmap way
                    byte[] imageByte = Base64.decode(landing_imgStr, Base64.DEFAULT);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
                    profileButton.setImageBitmap(bitmap);

                    //Uri way
                    //Uri imgUri = Uri.parse(landing_imgStr);
                    //landing_iv.setImageURI(imgUri);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ref = FirebaseDatabase.getInstance().getReference();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> group_ids = new ArrayList<String>();
                //String[] group_array = new String[group_ids.size()];
                        //group_array = group_ids.toArray(group_array);
                    for (DataSnapshot it : dataSnapshot.child("Users").child(global_variable.getUser_id()).child("groupIDs").getChildren()){
                        if (dataSnapshot.child("Group").child(it.getValue().toString()).getValue() != null) {
                            Long start_time = dataSnapshot.child("Group").child(it.getValue().toString()).child("date").getValue(long.class);
                            long current_time = System.currentTimeMillis();
                            long time_period = current_time - start_time;
                            double second = (double) Math.abs(time_period) / 1000.0; // 1 followed by 3 0's
                            double hour = second / 3600;
                            if (hour >= 24 && (Boolean) it.child("is_permanent").getValue() == false){
                                ref.child("Group").child(it.getValue().toString()).removeValue();
                            }
                            else
                            group_ids.add(it.getValue().toString());
                        }
                    }
                    ref.child("Users").child(global_variable.getUser_id()).child("groupIDs").setValue(group_ids);
                    ListAdapter adapter = new ListAdapter(homepage.this, group_ids);
                    listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        /*
        if (ref != null) {
            firebaseListAdapter = new FirebaseListAdapter<String>(this,
                    String.class,
                    R.layout.list_element,
                    ref) {
                @Override
                protected void populateView(View v, final String model, int position) {
                    final View v1 = v;
                    DatabaseReference mref = FirebaseDatabase.getInstance().getReference().child("Group");

                        final View vi = v;

                    mref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            vi.setTag(dataSnapshot.child(model).child("key").getValue().toString());

                            TextView t = (TextView) v1.findViewById(R.id.item1);
                            t.setText(dataSnapshot.child(model).child("groupName").getValue().toString());
                            TextView subt = (TextView) v1.findViewById(R.id.sub_item1);
                            subt.setText(dataSnapshot.child(model).child("topic").getValue().toString());
                            String str = vi.getTag().toString();

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            System.out.println("fetch group failed");
                        }
                    });
                }
            };
            listView.setAdapter(firebaseListAdapter);
            //firebaseListAdapter.cleanup();
        }
        */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View v = view;
                String gid = v.getTag().toString();
                //System.out.println(uid);
                Intent i = new Intent(homepage.this, group_chat.class);
                i.putExtra("groupid",gid);
                startActivity(i);
            }
        });

//        addGroupButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent i=new Intent(homepage.this, add_group.class);
//                homepage.this.startActivity(i);
//            }
//        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(homepage.this, LandingActivity.class);
                homepage.this.startActivity(i);
            }
        });

        sortButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(homepage.this, group_aroudme.class);
                homepage.this.startActivity(i);
            }
        });

//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                firebaseAuth.signOut();
//                finish();
//                Intent login = new Intent(homepage.this, LoginActivity.class);
//                startActivity(login);
//            }
//        });

        //Add by Frank
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(homepage.this, homepage.class);
                homepage.this.startActivity(i);
            }
        });

        findroomate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(homepage.this, LandingActivity.class);
                homepage.this.startActivity(i);
            }
        });
    }
}
