package com.example.zuoyangding.aroundme.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zuoyangding.aroundme.DataModels.ChartMessage;
import com.example.zuoyangding.aroundme.DataModels.GroupClass;
import com.example.zuoyangding.aroundme.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;


public class group_chat extends AppCompatActivity {

    private FirebaseListAdapter<ChartMessage> adapter;

    private ImageButton sendMessage;
    private EditText enterTheMessage;
    private TextView showGroupName;
    private FirebaseDatabase mDatabase;
    private DatabaseReference groupReference;
    private ListView listViewOfMessages;

    private String groupName;
    private String groupId;
    private String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);
        listViewOfMessages = (ListView) findViewById(R.id.chat_messages);
        showGroupName = (TextView) findViewById(R.id.group_name);
        sendMessage = (ImageButton) findViewById(R.id.send_message);
        enterTheMessage = (EditText)findViewById(R.id.enterMessage);
        mDatabase = FirebaseDatabase.getInstance();
        groupReference = mDatabase.getReference().child("Group");
        Bundle b = getIntent().getExtras();
        groupId = b.getString("groupid");

        groupReference.child(groupId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GroupClass group = dataSnapshot.getValue(GroupClass.class);
                if (group != null) {
                    if (group.getGroupName() != null) {
                        groupName = group.getGroupName();
                    }
                } else  {
                    Toast.makeText(group_chat.this, "cannot find groupName", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        showGroupName.setText(groupName);

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = enterTheMessage.getText().toString();
                if (message == null) {
                    Toast.makeText(group_chat.this, "message cannot be empty", Toast.LENGTH_LONG).show();
                } else  {
//                    Global_variable global_variable = (Global_variable)getApplicationContext();
//                    ChartMessage chartMessage = new ChartMessage(message, global_variable.getUser_id());

                    groupReference.child(groupId).addListenerForSingleValueEvent(new ValueEventListener() {
                        Global_variable global_variable = (Global_variable)getApplicationContext();
                        ChartMessage chartMessage = new ChartMessage(message, global_variable.getUser_id());
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            GroupClass group = dataSnapshot.getValue(GroupClass.class);
                            if (group.getChartMessages() == null) {
                                List<ChartMessage> chartMessages = new ArrayList<ChartMessage>();
                                chartMessages.add(chartMessage);
                                group.setChartMessages(chartMessages);
                                groupReference.setValue(chartMessages);
                            } else {
                                List<ChartMessage> chartMessages = group.getChartMessages();
                                chartMessages.add(chartMessage);
                                group.setChartMessages(chartMessages);
                                groupReference.setValue(chartMessages);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    //showGroupName.setText("");
                    display();
                }
                //display();
            }
        });
        showGroupName.setText("");
    }

    private void display() {
        //final ListView messages = (ListView) findViewById(R.id.chat_messages);
        adapter = new FirebaseListAdapter<ChartMessage>(group_chat.this, ChartMessage.class,
                                                        R.layout.activity_display_messages, groupReference.child(groupId).child("chartMessages")) {
            @Override
            protected void populateView(View v, ChartMessage model, int position) {
                String tmpMessage = model.getMessage();
                TextView showMessage =  (TextView) v.findViewById (R.id.text_message);
                showMessage.setText(tmpMessage);
            }

        };
        listViewOfMessages.setAdapter(adapter);
    }
}
