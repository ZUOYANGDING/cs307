package com.example.zuoyangding.aroundme.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.zuoyangding.aroundme.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class homepage extends AppCompatActivity {

    ImageButton addGroupButton;
    ImageButton profileButton;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        listView = (ListView)findViewById(R.id.group_list);
        addGroupButton = (ImageButton) findViewById(R.id.addGroupButton);
        profileButton = (ImageButton) findViewById(R.id.profileButton);

        final Global_variable global_variable = (Global_variable)getApplicationContext();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child(global_variable.getUser_id()).child("groupIDs");
        FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>(this,
                String.class,
                R.layout.list_element,
                ref) {
            @Override
            protected void populateView(View v, final String model, int position) {
                final View v1 = v;
                DatabaseReference mref = FirebaseDatabase.getInstance().getReference().child("Group");
                mref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        TextView t = (TextView) v1.findViewById(R.id.item1);
                        t.setText(dataSnapshot.child(model).child("groupName").getValue().toString());
                        TextView subt = (TextView)v1.findViewById(R.id.sub_item1);
                        subt.setText(dataSnapshot.child(model).child("topic").getValue().toString());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("fetch group failed");
                    }
                });
            }
        };
        listView.setAdapter(firebaseListAdapter);

        addGroupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(homepage.this, add_group.class);
                homepage.this.startActivity(i);
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(homepage.this, LandingActivity.class);
                homepage.this.startActivity(i);
            }
        });
    }
}
