package com.example.zuoyangding.aroundme.Activity;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Application;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zuoyangding.aroundme.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.FirebaseApp;


public class group_chat extends AppCompatActivity {

    private FirebaseListAdapter<chatMessage> adapter;

    ImageButton sendMessage;

    TextView enterMessage;
    TextView messageText;
    TextView messageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);

        Log.d("test1", "test1");
        sendMessage = (ImageButton) findViewById(R.id.imageButton3);
        enterMessage = (TextView)findViewById(R.id.enterMessage);

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FirebaseDatabase.getInstance().getReference().push().setValue(new chatMessage(enterMessage.getText().toString(),
//                        FirebaseAuth.getInstance().getCurrentUser().getEmail()));
//                enterMessage.setText("");
//                enterMessage.requestFocus();

                Log.d("test", "test");
                display();
            }
        });
    }

    private void display() {
        final ListView messages = (ListView) findViewById(R.id.chat_messages);

        adapter = new FirebaseListAdapter<chatMessage>(group_chat.this, chatMessage.class, R.layout.activity_display_messages, FirebaseDatabase.getInstance().getReference()) {
            @Override
            protected void populateView(View v, chatMessage model, int position) {

                messageText = (TextView) findViewById(R.id.display_messages_message);
                messageName = (TextView) findViewById(R.id.display_messages_User);

                messageText.setText(model.getMessage());
                messageName.setText(model.getNickname());

            }
        };
        messages.setAdapter(adapter);
    }
}
