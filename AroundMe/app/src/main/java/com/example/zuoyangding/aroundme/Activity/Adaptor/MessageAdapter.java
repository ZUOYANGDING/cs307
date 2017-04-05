package com.example.zuoyangding.aroundme.Activity.Adaptor;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
//import android.support.v7.media.MediaRouterJellybean;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.zuoyangding.aroundme.DataModels.ChartMessage;
import com.example.zuoyangding.aroundme.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zuoyangding on 4/4/17.
 */

//public class MessageAdapter extends ArrayAdapter <String> {
    /*private ArrayList<String> messageId;
    private Activity context;
    public MessageAdapter(Activity context, ArrayList<String> messageId) {
        super(context, R.layout.activity_display_messages, messageId);
        this.context = context;
        this.messageId = messageId;
    }
    public View getView (final int position, View v, ViewGroup parent) {
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.activity_display_messages, parent, false);
        } else {
            DatabaseReference chartMessageReference = FirebaseDatabase.getInstance().getReference().child("ChartMessages");
            DatabaseReference userReference = FirebaseDatabase.getInstance().getReference().child("Users");
            final View v1 = v;
            chartMessageReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String userId = dataSnapshot.child(messageId.get(position)).child("userId").getValue().toString();
                    v1.setTag(userId);

                    String message = dataSnapshot.child()
                    TextView showNickName = (TextView) v1.findViewById(R.id.nick_name);
                    TextView showMessage = (TextView) v1.findViewById(R.id.text_message);
                    showNickName.setText()
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        return v;
    }*/
//}
