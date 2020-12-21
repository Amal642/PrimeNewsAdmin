package com.revolt.primenewsadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class ViewFeedbackActivity extends AppCompatActivity {

    FloatingActionButton button;
    RecyclerView recyclerView;
    FeedbackAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feedback);

        button=findViewById(R.id.floatfeed);
        recyclerView=findViewById(R.id.recviewfeedback);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Feedback> options =
                new FirebaseRecyclerOptions.Builder<Feedback>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Feedback_upload"),Feedback.class)
                        .build();

        adapter= new FeedbackAdapter(options);
        recyclerView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ViewFeedbackActivity.this,AddFeedbackActivity.class);
                startActivity(intent);
            }
        });



    }
    @Override
    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop(){
        super.onStop();
        adapter.stopListening();


    }
}