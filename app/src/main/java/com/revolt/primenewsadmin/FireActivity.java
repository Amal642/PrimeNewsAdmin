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

public class FireActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private FireAdapter adapter;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListener;
    private List<fire> mTeachers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire);

        recyclerView = findViewById(R.id.recviewfire);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //mProgressBar = findViewById(R.id.myDataLoaderProgressBar);
        // mProgressBar.setVisibility(View.VISIBLE);
        mTeachers = new ArrayList<>();
        adapter = new FireAdapter(FireActivity.this, mTeachers);
        recyclerView.setAdapter(adapter);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("fire_upload");
        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mTeachers.clear();
                for (DataSnapshot teacherSnapshot : dataSnapshot.getChildren()) {
                    fire upload = teacherSnapshot.getValue(fire.class);
                    upload.setId(teacherSnapshot.getKey());
                    mTeachers.add(0,upload);
                    adapter.notifyItemInserted(0);
                }
                adapter.notifyDataSetChanged();
                // mProgressBar.setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(FireActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                //mProgressBar.setVisibility(View.INVISIBLE);
            }
        });

        /*recyclerView =(RecyclerView)findViewById(R.id.recviewfire);
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
*/
    }
}