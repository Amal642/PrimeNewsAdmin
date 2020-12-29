package com.revolt.primenewsadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class DeleteAdActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DeleteAdAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_ad);

        recyclerView =findViewById(R.id.recviewlink);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Link> options =
                new FirebaseRecyclerOptions.Builder<Link>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Image"),Link.class)
                        .build();

        adapter= new DeleteAdAdapter(options,this);
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