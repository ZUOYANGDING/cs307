package com.example.zuoyangding.aroundme.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zuoyangding.aroundme.DataModels.chatMessage;
import com.example.zuoyangding.aroundme.R;
import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.vision.text.Text;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class display_messageActivity extends AppCompatActivity {
    private Button addInto;
    private FirebaseDatabase mDatabase;
    private DatabaseReference groupReference;

    // for display
    private DatabaseReference ref;

    public TextView groupName;

    ImageButton sent;
    TextView messageField;
    ListView messages;

    static FirebaseListAdapter<String> firebaseListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);

        messageField = (TextView) findViewById(R.id.group_chat_enterMessage);

        groupName = (TextView) findViewById(R.id.group_chat_groupName);

        Button addInto = (Button) findViewById(R.id.joined_button);
        mDatabase = FirebaseDatabase.getInstance();
        groupReference = mDatabase.getReference().child("Group");
        addInto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // sent button
        sent = (ImageButton) findViewById(R.id.send_message);
        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // store into Fireabse

                FirebaseDatabase.getInstance().getReference().push().setValue(new chatMessage("test", messageField.getText().toString()));

                messageField.setText("");

                display();
            }
        });

    }

    private void display() {
        // displaying

        firebaseListAdapter = new FirebaseListAdapter<String>(this,
                String.class,
                R.layout.activity_group_chat_list,
                ref) {
            @Override
            protected void populateView(View v, final String model, int position) {
                ref = FirebaseDatabase.getInstance().getReference("-KgSqNkacD6kXfrS6b0F");


                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Map<String, String> map = dataSnapshot.getValue(Map.class);
                        TextView messageDisplay = (TextView) findViewById(R.id.group_chat_message);

                        messageDisplay.setText(map.get("message"));

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        };


//        ref = FirebaseDatabase.getInstance().getReference();
//
//
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
////
////                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
////                for (DataSnapshot child : children) {
////                    chatMessage c = child.getValue(chatMessage.class);
////                    messageDisplay.setText(c.getMessage());
////                }
////                chatMessage c = dataSnapshot.getValue(chatMessage.class);
//
//                Map<String, String> map = dataSnapshot.getValue(Map.class);
//
//                messageDisplay.setText(map.get("message"));
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


//        Log.d("test", "test");
//
//        messages = (ListView) findViewById(R.id.chat_messages);
//        Log.d("test1", "test1");
//
//        adapter = new FirebaseListAdapter<chatMessage>(this,chatMessage.class,R.layout.activity_group_chat_list,FirebaseDatabase.getInstance().getReference()) {
//
//            @Override
//            protected void populateView(View v, chatMessage model, int position) {
//
//                TextView messageText, nicknameText;
//
//                messageText = (TextView) v.findViewById(R.id.group_chat_message);
//                nicknameText = (TextView) v.findViewById(R.id.group_chat_nickname);
//                Log.d("test2", "test2");
//                messageText.setText(model.getMessage());
//                nicknameText.setText(model.getMessage());
//
//            }
//        };
    }

}
