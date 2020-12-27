package com.revolt.primenewsadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PoliceActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private PoliceAdapter  adapter;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListener;
    private List<police> mTeachers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police);

        recyclerView = findViewById(R.id.recviewpolice);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //mProgressBar = findViewById(R.id.myDataLoaderProgressBar);
        // mProgressBar.setVisibility(View.VISIBLE);
        mTeachers = new ArrayList<>();
        adapter = new PoliceAdapter(PoliceActivity.this, mTeachers);
        recyclerView.setAdapter(adapter);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("pol_upload");
        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mTeachers.clear();
                for (DataSnapshot teacherSnapshot : dataSnapshot.getChildren()) {
                    police upload = teacherSnapshot.getValue(police.class);
                    upload.setId(teacherSnapshot.getKey());
                    mTeachers.add(0,upload);
                    adapter.notifyItemInserted(0);
                }
                adapter.notifyDataSetChanged();
                // mProgressBar.setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(PoliceActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                //mProgressBar.setVisibility(View.INVISIBLE);
            }
        });

       /* recyclerView =(RecyclerView)findViewById(R.id.recviewpolice);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<police> options =
                new FirebaseRecyclerOptions.Builder<police>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("pol_upload"),police.class)
                        .build();

        adapter= new PoliceAdapter(options);
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
        adapter.stopListening();*/
    }
}