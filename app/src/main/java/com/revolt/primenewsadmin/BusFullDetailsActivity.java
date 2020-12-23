package com.revolt.primenewsadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class BusFullDetailsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private BAdapter mAdapter;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListener;
    private List<BusStatDetails> mTeachers;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_full_details);

        mRecyclerView = findViewById(R.id.recviewbus);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //mProgressBar = findViewById(R.id.myDataLoaderProgressBar);
        // mProgressBar.setVisibility(View.VISIBLE);
        mTeachers = new ArrayList<>();
        Intent i=this.getIntent();
        String name=i.getExtras().getString("NAME_KEY");
        mAdapter = new BAdapter(BusFullDetailsActivity.this, mTeachers,name);
        mRecyclerView.setAdapter(mAdapter);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Bus_Stationss").child(name);
        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mTeachers.clear();
                for (DataSnapshot teacherSnapshot : dataSnapshot.getChildren()) {
                    BusStatDetails upload = teacherSnapshot.getValue(BusStatDetails.class);
                    upload.setId(teacherSnapshot.getKey());
                    mTeachers.add(0,upload);
                    mAdapter.notifyItemInserted(0);
                }
                mAdapter.notifyDataSetChanged();
                // mProgressBar.setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(BusFullDetailsActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                //mProgressBar.setVisibility(View.INVISIBLE);
            }
        });

    }
}
