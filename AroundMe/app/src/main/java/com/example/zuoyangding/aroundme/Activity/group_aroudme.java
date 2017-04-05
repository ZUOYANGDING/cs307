package com.example.zuoyangding.aroundme.Activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zuoyangding.aroundme.DataModels.ChartMessage;
import com.example.zuoyangding.aroundme.DataModels.GroupClass;
import com.example.zuoyangding.aroundme.DataModels.User;
import com.example.zuoyangding.aroundme.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
        listView = (ListView)findViewById(R.id.group_list);
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
    }
    protected void createLocationRequest() {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(500);
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
                        //Location temp_lo = new Location(it.child("mlocation").getValue(Location.class));
                        GroupClass group = new GroupClass(it.child("groupName").getValue().toString(), it.child("key").getValue().toString(), it.child("topic").getValue().toString(), it.child("date").getValue(Long.class), null, (ArrayList<String>) it.child("member_ids").getValue(), it.child("lat").getValue(Double.class), it.child("lon").getValue(Double.class), it.child("is_permanent").getValue(Boolean.class));
                        temp.add(group);
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
                    GroupClass group = new GroupClass(it.child("groupName").getValue().toString(), it.child("key").getValue().toString(), it.child("topic").getValue().toString(), it.child("date").getValue(Long.class), null, (ArrayList<String>) it.child("member_ids").getValue() , it.child("alt").getValue(Double.class), it.child("lon").getValue(Double.class), it.child("is_permanent").getValue(Boolean.class));

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
