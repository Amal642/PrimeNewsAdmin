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

public class AmbulanceActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private AmbulanceAdapter adapter;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListener;
    private List<Emergency> mTeachers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance);

        recyclerView = findViewById(R.id.recviewamb);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //mProgressBar = findViewById(R.id.myDataLoaderProgressBar);
        // mProgressBar.setVisibility(View.VISIBLE);
        mTeachers = new ArrayList<>();
        adapter = new AmbulanceAdapter(AmbulanceActivity.this, mTeachers);
        recyclerView.setAdapter(adapter);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Ambulance_upload");
        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mTeachers.clear();
                for (DataSnapshot teacherSnapshot : dataSnapshot.getChildren()) {
                    Emergency upload = teacherSnapshot.getValue(Emergency.class);
                    upload.setId(teacherSnapshot.getKey());
                    mTeachers.add(0,upload);
                    adapter.notifyItemInserted(0);
                }
                adapter.notifyDataSetChanged();
                // mProgressBar.setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AmbulanceActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                //mProgressBar.setVisibility(View.INVISIBLE);
            }
        });

        /*recyclerView =(RecyclerView)findViewById(R.id.recviewamb);
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
        adapter.stopListening();*/
    }
}