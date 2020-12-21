package com.revolt.primenewsadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class AmbulanceActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AmbulanceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance);

        recyclerView =(RecyclerView)findViewById(R.id.recviewamb);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Emergency> options =
                new FirebaseRecyclerOptions.Builder<Emergency>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Ambulance_upload"),Emergency.class)
                        .build();

        adapter= new AmbulanceAdapter(options);
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