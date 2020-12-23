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

public class ViewbusStationsActivity extends AppCompatActivity implements BusStatAdapter.OnItemClickListener{
    private RecyclerView mRecyclerView;
    private BusStatAdapter mAdapter;
    private BAdapter newadapter;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListener;
    private List<Busstations> mTeachers;
    String[] entity1;
    private void openDetailActivity(String[] data) {
        Intent intent = new Intent(this,BusFullDetailsActivity.class);
        intent.putExtra("NAME_KEY", data[0]);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewbus_stations);


        mRecyclerView = findViewById(R.id.recviewbus1);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //mProgressBar = findViewById(R.id.myDataLoaderProgressBar);
        // mProgressBar.setVisibility(View.VISIBLE);
        mTeachers = new ArrayList<>();
        mAdapter = new BusStatAdapter(ViewbusStationsActivity.this, mTeachers);
        newadapter = new BAdapter(ViewbusStationsActivity.this,entity1);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener( ViewbusStationsActivity.this);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Bus_Stations");
        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mTeachers.clear();
                for (DataSnapshot teacherSnapshot : dataSnapshot.getChildren()) {
                    Busstations upload = teacherSnapshot.getValue(Busstations.class);
                    upload.setKey(teacherSnapshot.getKey());
                    mTeachers.add(0,upload);
                    mAdapter.notifyItemInserted(0);
                }
                mAdapter.notifyDataSetChanged();
                // mProgressBar.setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ViewbusStationsActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                //mProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void onItemClick(int position) {
        Busstations clickedTeacher=mTeachers.get(position);
        String[] teacherData={clickedTeacher.getName()};
        openDetailActivity(teacherData);
        entity1=teacherData;


    }

    }
