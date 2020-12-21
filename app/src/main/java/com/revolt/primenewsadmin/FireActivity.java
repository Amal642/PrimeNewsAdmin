package com.revolt.primenewsadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class FireActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FireAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire);

        recyclerView =(RecyclerView)findViewById(R.id.recviewfire);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<fire> options =
                new FirebaseRecyclerOptions.Builder<fire>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("fire_upload"),fire.class)
                        .build();

        adapter= new FireAdapter(options);
        recyclerView.setAdapter(adapter);


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