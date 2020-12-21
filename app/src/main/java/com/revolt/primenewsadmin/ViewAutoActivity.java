package com.revolt.primenewsadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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

public class ViewAutoActivity extends AppCompatActivity implements AutoStatAdapter.OnItemClickListener{

    private RecyclerView mRecyclerView;
    private AutoStatAdapter mAdapter;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListener;
    private List<Autostations> mTeachers;
    private void openDetailActivity(String[] data) {
        Intent intent = new Intent(this,AutoFullDetailsActivity.class);
        intent.putExtra("NAME_KEY", data[0]);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_auto);

        mRecyclerView = findViewById(R.id.recviewauto);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //mProgressBar = findViewById(R.id.myDataLoaderProgressBar);
        // mProgressBar.setVisibility(View.VISIBLE);
        mTeachers = new ArrayList<>();
        mAdapter = new AutoStatAdapter(ViewAutoActivity.this, mTeachers);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener( ViewAutoActivity.this);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Auto_Stations");
        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mTeachers.clear();
                for (DataSnapshot teacherSnapshot : dataSnapshot.getChildren()) {
                    Autostations upload = teacherSnapshot.getValue(Autostations.class);
                    upload.setKey(teacherSnapshot.getKey());
                    mTeachers.add(0,upload);
                    mAdapter.notifyItemInserted(0);
                }
                mAdapter.notifyDataSetChanged();
                // mProgressBar.setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ViewAutoActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                //mProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void onItemClick(int position) {
        Autostations clickedTeacher=mTeachers.get(position);
        String[] teacherData={clickedTeacher.getName()};
        openDetailActivity(teacherData);


    }
}