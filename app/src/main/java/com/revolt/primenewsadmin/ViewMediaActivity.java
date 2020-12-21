package com.revolt.primenewsadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ViewMediaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MediaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_media);

        recyclerView =(RecyclerView)findViewById(R.id.recviewmedia);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Media> options =
                new FirebaseRecyclerOptions.Builder<Media>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Media_upload"),Media.class)
                        .build();

        adapter= new MediaAdapter(options);
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