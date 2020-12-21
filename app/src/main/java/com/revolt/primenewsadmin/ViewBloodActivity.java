package com.revolt.primenewsadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ViewBloodActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BloodAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_blood);

        recyclerView =(RecyclerView)findViewById(R.id.recviewblood);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Blood> options =
                new FirebaseRecyclerOptions.Builder<Blood>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Blood_upload"),Blood.class)
                        .build();

        adapter= new BloodAdapter(options);
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