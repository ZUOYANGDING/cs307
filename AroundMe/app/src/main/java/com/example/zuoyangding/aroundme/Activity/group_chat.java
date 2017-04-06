package com.example.zuoyangding.aroundme.Activity;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zuoyangding.aroundme.Activity.Adaptor.ListAdapter;
//import com.example.zuoyangding.aroundme.Activity.Adaptor.MessageAdapter;
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

    private FirebaseListAdapter<String> adapter;

    private ImageButton sendMessage;
    private ImageButton sendImage;
    private EditText enterTheMessage;
    private TextView showGroupName;
    private FirebaseDatabase mDatabase;
    private DatabaseReference groupReference;
    private DatabaseReference chartMessagesReference;
    private ListView listViewOfMessages;
    private Button joinbutton;

    private Button deleteButton;
    //private String groupName;
    private String groupId;
    private String message;
    //private int message_count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);
        listViewOfMessages = (ListView) findViewById(R.id.chat_messages);
        showGroupName = (TextView) findViewById(R.id.group_name);
        sendMessage = (ImageButton) findViewById(R.id.send_message);
        sendImage = (ImageButton) findViewById (R.id.add_picture);
        enterTheMessage = (EditText)findViewById(R.id.enterMessage);
        joinbutton = (Button)findViewById(R.id.joined_button);

        deleteButton = (Button)findViewById(R.id.leave_button);

        mDatabase = FirebaseDatabase.getInstance();
        groupReference = mDatabase.getReference().child("Group");
        chartMessagesReference = mDatabase.getReference().child("ChartMessages");
        Bundle b = getIntent().getExtras();
        groupId = b.getString("groupid");

        groupReference.child(groupId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //GroupClass group = dataSnapshot.getValue(GroupClass.class);
                if (dataSnapshot.child("groupName") != null) {
                    String groupName = dataSnapshot.child("groupName").getValue().toString();
                    showGroupName.setText(groupName);
                } else  {
                    Toast.makeText(group_chat.this, "cannot find groupName", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        //showGroupName.setText(groupName);
        Global_variable global_variable = (Global_variable)getApplicationContext();
        final String uid = global_variable.getUser_id();
        final DatabaseReference ref = mDatabase.getReference();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> groupIDs = (ArrayList<String>)dataSnapshot.child("Users").child(uid).child("groupIDs").getValue();
                if ((long)dataSnapshot.child("Group").child(groupId).child("vote").getValue() >= 10 && groupIDs.contains(groupId)){
                    joinbutton.setText("permanent");
                    joinbutton.setEnabled(false);
                }
                else if (groupIDs.contains(groupId)){
                    joinbutton.setText("voted");
                    joinbutton.setEnabled(false);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        adapter = new FirebaseListAdapter<String>(group_chat.this, String.class,
                R.layout.activity_display_messages, groupReference.child(groupId).child("messageId")) {
            @Override
            protected void populateView(View v, String model, final int position) {
                //Global_variable global_variable = (Global_variable)getApplicationContext();
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                //.getReference().child("Group").child("messageId");
                //final DatabaseReference chartMessageReference = FirebaseDatabase.getInstance().getReference().child("ChartMessages");
                //final DatabaseReference userReference = FirebaseDatabase.getInstance().getReference().child("Users");
                final View v1 = v;
                final String model_1 = model;
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child("ChartMessages").getValue() != null) {
                            String messageId = dataSnapshot.child("ChartMessages").child(model_1).child("messageKey").getValue().toString();
                            String userId = dataSnapshot.child("ChartMessages").child(model_1).child("uid").getValue().toString();
                            String message = dataSnapshot.child("ChartMessages").child(model_1).child("message").getValue().toString();
                            String nickName;
                            if (dataSnapshot.child("Users").child(userId).child("nickName").getValue() == null) {
                                nickName = "anonymous";
                            } else {
                                nickName = dataSnapshot.child("Users").child(userId).child("nickName").getValue().toString();
                            }

                            v1.setTag(userId);
                            TextView showNickName = (TextView) v1.findViewById(R.id.nick_name);
                            TextView showMessage = (TextView) v1.findViewById(R.id.text_message);

                            showMessage.setText(message);
                            showNickName.setText(nickName);
                            listViewOfMessages.setSelection(position);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }

        };
        listViewOfMessages.setAdapter(adapter);

        sendImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = enterTheMessage.getText().toString();
                if (message.length() == 0){
                    Toast.makeText(group_chat.this, "message cannot be empty", Toast.LENGTH_LONG).show();
                } else  {
//                    Global_variable global_variable = (Global_variable)getApplicationContext();
//                    ChartMessage chartMessage = new ChartMessage(message, global_variable.getUser_id());

                    groupReference.child(groupId).child("messageId").addListenerForSingleValueEvent(new ValueEventListener() {
                        /*Global_variable global_variable = (Global_variable)getApplicationContext();
                        String userId = global_variable.getUser_id();
                        ChartMessage chartMessage = new ChartMessage(message, userId);*/
//                        ListAdapter adapter = new ListAdapter(group_chat.this, chartMessage);
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Global_variable global_variable = (Global_variable)getApplicationContext();
                            String userId = global_variable.getUser_id();
                            //ChartMessage chartMessage = new ChartMessage(message, userId);


                            if (dataSnapshot.getValue() == null) {
                                String messageKey = chartMessagesReference.push().getKey();
                                ChartMessage chartMessage = new ChartMessage(message, userId);
                                chartMessage.setMessageKey(messageKey);
                                chartMessagesReference.child(messageKey).setValue(chartMessage);
                                ArrayList<String> messageId = new ArrayList<String>();
                                messageId.add(messageKey);

                                groupReference.child(groupId).child("messageId").setValue(messageId);

                                //MessageAdapter adapter = new MessageAdapter(group_chat.this, messageId);
                                //listViewOfMessages.setAdapter(adapter);
                                //group.setChartMessages(chartMessages);
                                //System.out.println("groupId_1"+groupId);
                                //groupReference.child(groupId).child("chartMessages").setValue(chartMessages);
                            } else {
                                String messageKey = chartMessagesReference.push().getKey();
                                ChartMessage chartMessage = new ChartMessage(message, userId);
                                chartMessage.setMessageKey(messageKey);
                                chartMessagesReference.child(messageKey).setValue(chartMessage);

                                ArrayList<String> messageId = (ArrayList<String>) dataSnapshot.getValue();
                                messageId.add(messageKey);
                                groupReference.child(groupId).child("messageId").setValue(messageId);

                                //MessageAdapter adapter = new MessageAdapter(group_chat.this, messageId);
                                //listViewOfMessages.setAdapter(adapter);
                                //group.setChartMessages(chartMessages);
                                //System.out.println("groupId_2"+groupId);
                                //groupReference.child(groupId).child("chartMessages").setValue(chartMessages);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    //showGroupName.setText("");
                    //display();
                    enterTheMessage.setText("");
                }
                //display();
            }
        });


        listViewOfMessages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View v = view;
                String u = v.getTag().toString();
                System.out.println("userId get from tag" + u);
                //System.out.println(uid);

            }
        });


        joinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference ref = mDatabase.getReference();
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        long votes = (long)dataSnapshot.child("Group").child(groupId).child("vote").getValue();
                        votes++;
                        if (votes >= 10){
                            ref.child("is_permanent").setValue(true);
                            joinbutton.setText("permanent");
                        }
                        ref.child("Group").child(groupId).child("vote").setValue(votes);
                        ArrayList<String> groupIds = (ArrayList<String>)dataSnapshot.child("Users").child(uid).child("groupIDs").getValue();
                        groupIds.add(groupId);
                        ref.child("Users").child(uid).child("groupIDs").setValue(groupIds);
                        joinbutton.setText("voted");
                        joinbutton.setEnabled(false);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final DatabaseReference ref = mDatabase.getReference();
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArrayList<String> groupIds = (ArrayList<String>)dataSnapshot.child("Users").child(uid).child("groupIDs").getValue();
                        groupIds.remove(groupId);
                        ref.child("Users").child(uid).child("groupIDs").setValue(groupIds);

                        ArrayList<String> memberIDs = (ArrayList<String>)dataSnapshot.child("Group").child(groupId).child("member_ids").getValue();
                        memberIDs.remove(uid);
                        ref.child("Group").child(groupId).child("member_ids").setValue(memberIDs);

                        if (memberIDs.size() == 0) {
                            ref.child("Group").child(groupId).setValue(null);
                        }

                        Intent i = new Intent(group_chat.this, homepage.class);
                        group_chat.this.startActivity(i);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }


    }

