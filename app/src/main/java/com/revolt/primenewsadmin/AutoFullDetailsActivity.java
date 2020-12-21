package com.revolt.primenewsadmin;

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

public class AutoFullDetailsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private AutoAdapter mAdapter;
    private FirebaseStorage mStorage;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListener;
    private List<AutoStatDetails> mTeachers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_full_details);

        mRecyclerView = (RecyclerView) findViewById(R.id.recviewautofull);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //mProgressBar = findViewById(R.id.myDataLoaderProgressBar);
        // mProgressBar.setVisibility(View.VISIBLE);
        mTeachers = new ArrayList<>();
        Intent i = this.getIntent();
        String name = i.getExtras().getString("NAME_KEY");
        mAdapter = new AutoAdapter(AutoFullDetailsActivity.this, mTeachers);
        mRecyclerView.setAdapter(mAdapter);
        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Auto_Stationss").child(name);
        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mTeachers.clear();
                for (DataSnapshot teacherSnapshot : dataSnapshot.getChildren()) {
                    AutoStatDetails upload = teacherSnapshot.getValue(AutoStatDetails.class);
                    upload.setId(teacherSnapshot.getKey());
                    mTeachers.add(0, upload);
                    mAdapter.notifyItemInserted(0);
                }
                mAdapter.notifyDataSetChanged();
                // mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AutoFullDetailsActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                //mProgressBar.setVisibility(View.INVISIBLE);
            }
        });

    }
}