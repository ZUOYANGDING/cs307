package com.example.zuoyangding.aroundme.Activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zuoyangding.aroundme.Activity.Adaptor.GroupListAdapter;
import com.example.zuoyangding.aroundme.DataModels.GroupClass;
import com.example.zuoyangding.aroundme.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by siyujiang on 4/3/17.
 */

public class group_aroudme extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks
        ,GoogleApiClient.OnConnectionFailedListener, LocationListener{
    /*
    public final Comparator<GroupClass> BY_DISTANCE = new Comparator<GroupClass>() {
        @Override
        public int compare(GroupClass g1, GroupClass g2) {
            double l1 = g1.alt;
            double a1 = g1.lon;

            double l2 = g2.alt;
            double a2 = g2.lon;

            double l0 = mLastLocation.getLongitude();
            double a0 = mLastLocation.getAltitude();

            double distance1 = Math.pow((l1-l0), 2) + Math.pow((a1-a0),2);
            double distance2 = Math.pow((l2-l0), 2) + Math.pow((a2-a0),2);
            return Double.compare(distance1,distance2);
        }
    };
    */
    private DatabaseReference mGroupReference;
    //private DatabaseReference mUserRefernece;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private Location mLastLocation;
    private ListView listView;

    //Add by Frank Hu
    private ImageButton findroommate;
    private ImageButton addGroupButton;
    private ImageButton profileButton;
    private ImageButton sortButton;
    private ImageButton searchButton;
    private ImageButton startButton;

    //private Button logout;
    private FirebaseAuth mAuth;
    private String userId;
    static FirebaseListAdapter<String> firebaseListAdapter;
    private DatabaseReference ref;
    private FirebaseAuth.AuthStateListener mAuthListener;

    //image module by Frank Hu
    private String landing_imgStr;
    private ImageButton findroomate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
        listView = (ListView)findViewById(R.id.group_list);

        //Add by Frank
        addGroupButton = (ImageButton) findViewById(R.id.addGroupButton);
        profileButton = (ImageButton) findViewById(R.id.profileButton);
        searchButton = (ImageButton) findViewById(R.id.imageButton3);
        sortButton = (ImageButton)findViewById(R.id.homepage_button);
        findroommate = (ImageButton) findViewById(R.id.roommate_button);
        startButton = (ImageButton) findViewById(R.id.favorites_button);

        final Global_variable global_variable = (Global_variable)getApplicationContext();
        //ArrayList<String> group_ids;

        ////image module by Frank Hu (update sortpage user's avatar from firebase )
        DatabaseReference mref = FirebaseDatabase.getInstance().getReference().child("Users");
        ref = FirebaseDatabase.getInstance().getReference();
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
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        int MY_PERMISSION = 0;
        if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION);
        }
        else {
            createLocationRequest();
            mGoogleApiClient.connect();
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View v = view;
                String gid = v.getTag().toString();
                //System.out.println(uid);
                Intent i = new Intent(group_aroudme.this, group_chat.class);
                i.putExtra("groupid",gid);
                startActivity(i);
            }
        });

        //Add by Frank
        addGroupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(group_aroudme.this, add_group.class);
                group_aroudme.this.startActivity(i);
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(group_aroudme.this, LandingActivity.class);
                group_aroudme.this.startActivity(i);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(group_aroudme.this, Search_input.class);
                group_aroudme.this.startActivity(i);
            }
        });

        //Add by Frank
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(group_aroudme.this, homepage.class);
                group_aroudme.this.startActivity(i);
            }
        });

        findroommate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("click profile button");
                Intent i=new Intent(group_aroudme.this, LandingActivity.class);
                group_aroudme.this.startActivity(i);
            }
        });
    }
    protected void createLocationRequest() {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(10000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(group_aroudme.this, "Connection Failed", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onConnectionSuspended(int cause){
        Toast.makeText(group_aroudme.this, "TEMPORARY disconnected", Toast.LENGTH_LONG).show();
    };
    @Override
    public void onConnected(Bundle connectionHint) {
        //Toast.makeText(add_group.this, "onConnected", Toast.LENGTH_LONG).show();

        if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            mGroupReference = FirebaseDatabase.getInstance().getReference().child("Group");
            mGroupReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ArrayList<GroupClass> temp = new ArrayList<GroupClass>();
                    for (DataSnapshot it : dataSnapshot.getChildren()) {
                        if (it.getValue() != null) {

                            Long start_time = it.child("date").getValue(long.class);
                            long current_time = System.currentTimeMillis();
                            long time_period = current_time - start_time;
                            double second = (double) Math.abs(time_period) / 1000.0; // 1 followed by 3 0's
                            double hour = second / 3600;
                            if (hour >= 24 && (Boolean) it.child("is_permanent").getValue()!= true) {
                                ref.child("Group").child(it.child("key").getValue().toString()).removeValue();
                            }else {
                                //Location temp_lo = new Location(it.child("mlocation").getValue(Location.class));
                                GroupClass group = new GroupClass(it.child("groupName").getValue().toString(), it.child("key").getValue().toString(), it.child("topic").getValue().toString(), it.child("date").getValue(Long.class), null, (ArrayList<String>) it.child("member_ids").getValue(), it.child("lat").getValue(Double.class), it.child("lon").getValue(Double.class), it.child("is_permanent").getValue(Boolean.class));
                                temp.add(group);
                            }
                        }
                    }
                    GroupClass[] groups = new GroupClass[temp.size()];
                    groups = temp.toArray(groups);
                    MergeSort sorter = new MergeSort();
                    //if (mLastLocation != null)
                    sorter.sort(groups, mLastLocation);
                    //Arrays.sort(groups);
                    temp = new ArrayList<GroupClass>(Arrays.asList(groups));
                    GroupListAdapter adapter = new GroupListAdapter(group_aroudme.this, temp);
                    listView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        mGroupReference = FirebaseDatabase.getInstance().getReference().child("Group");
        mGroupReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<GroupClass> temp = new ArrayList<GroupClass>();
                for(DataSnapshot it : dataSnapshot.getChildren()){
                    //Location temp_lo = new Location(it.child("mlocation").getValue(Location.class));
                    GroupClass group = new GroupClass(it.child("groupName").getValue().toString(), it.child("key").getValue().toString(), it.child("topic").getValue().toString(), it.child("date").getValue(Long.class), null, (ArrayList<String>) it.child("member_ids").getValue() , it.child("lat").getValue(Double.class), it.child("lon").getValue(Double.class), it.child("is_permanent").getValue(Boolean.class));

                    temp.add(group);
                }
                GroupClass[] groups = new GroupClass[temp.size()];
                groups = temp.toArray(groups);
                MergeSort sorter = new MergeSort();
                //if (mLastLocation != null)
                sorter.sort(groups,mLastLocation);
                //Arrays.sort(groups);
                temp = new ArrayList<GroupClass>(Arrays.asList(groups));
                GroupListAdapter adapter = new GroupListAdapter(group_aroudme.this, temp);
                listView.setAdapter(adapter);
                //listView.setSelection(0);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            createLocationRequest();
            mGoogleApiClient.connect();
        } else {
            Intent i = new Intent(group_aroudme.this, homepage.class);
            startActivity(i);
        }
        return;
    }

}
