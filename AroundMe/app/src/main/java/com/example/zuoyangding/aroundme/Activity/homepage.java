//package com.example.zuoyangding.aroundme.Activity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageButton;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import com.example.zuoyangding.aroundme.DataModels.GroupClass;
//import com.example.zuoyangding.aroundme.R;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class homepage extends AppCompatActivity {
//
//    ImageButton addGroupButton;
//    ImageButton profileButton;
//
//    ListView groupListView;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_homepage);
//
//        addGroupButton = (ImageButton) findViewById(R.id.addGroupButton);
//        profileButton = (ImageButton) findViewById(R.id.profileButton);
//
//        groupListView = (ListView) findViewById(R.id.homepage_list);
//
//
//        addGroupButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent i = new Intent(homepage.this, add_group.class);
//                homepage.this.startActivity(i);
//            }
//        });
//
//        profileButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent i = new Intent(homepage.this, LandingActivity.class);
//                homepage.this.startActivity(i);
//            }
//        });
//
//
//    }
//
//
//
//}


package com.example.zuoyangding.aroundme.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zuoyangding.aroundme.DataModels.GroupClass;
import com.example.zuoyangding.aroundme.R;
import com.firebase.client.Firebase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class homepage extends AppCompatActivity {

    private ArrayList<String> data = new ArrayList<String>();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference groupInfo;

    ListView lv;

    private class MyListAdaper extends ArrayAdapter<String> {
        private int layout;
        private List<String> mObjects;

        private MyListAdaper(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
            mObjects = objects;
            layout = resource;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mainViewholder = null;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.title = (TextView) convertView.findViewById(R.id.homepage_list_textView);
                convertView.setTag(viewHolder);
            }
            mainViewholder = (ViewHolder) convertView.getTag();

            mainViewholder.title.setText(getItem(position));

            return convertView;
        }
    }

    public class ViewHolder {

        TextView title;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        lv = (ListView) findViewById(R.id.homepage_list);
        click();
    }

    public void click() {

        groupInfo = database.getReference("Group");
        DatabaseReference ref = groupInfo.child("TEST1");

        //一直进不去这里
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                //for (DataSnapshot child: children) {
                // GroupClass group = child.getValue(GroupClass.class); // <-- do . at end here to specify which child
                data.add("3");

                //}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        data.add("1");
//        for(int i = 0; i < 2; i++) {
//            Log.d("test12", "test12");
//            data.add("This is row number " + i);
//        }

        lv.setAdapter(new MyListAdaper(this, R.layout.activity_homepage_list, data));

    }


}