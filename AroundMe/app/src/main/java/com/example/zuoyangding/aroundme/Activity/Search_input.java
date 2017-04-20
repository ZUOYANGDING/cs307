package com.example.zuoyangding.aroundme.Activity;


        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.zuoyangding.aroundme.DataModels.User;
        import com.example.zuoyangding.aroundme.R;
        import com.firebase.client.Firebase;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

/**
 * Created by siyujiang on 4/6/17.
 */

public class Search_input extends AppCompatActivity {
    private TextView input;
    private Button save;
    private String topic;
    protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_search_input);
            input = (TextView)findViewById(R.id.topic_input);
            save = (Button)findViewById(R.id.submit_search);
            save.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    topic = input.getText().toString();
                    Intent i = new Intent(Search_input.this, Sort_by_topic.class);
                    i.putExtra("input_topic",topic);
                    Toast.makeText(Search_input.this, topic, Toast.LENGTH_LONG).show();
                    Search_input.this.startActivity(i);
                }
            });
    }
}
