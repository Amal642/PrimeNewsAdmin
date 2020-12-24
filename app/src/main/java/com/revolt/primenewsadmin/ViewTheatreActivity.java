package com.revolt.primenewsadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ViewTheatreActivity extends AppCompatActivity implements TheatreAdapter.OnItemClickListener{

    private RecyclerView mRecyclerView;
    private TheatreAdapter mAdapter;
    //private ProgressBar mProgressBar;
    private FirebaseStorage mStorage;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListener;
    private List<Theatre> mTeacher;
    private void openDetailActivity(String[] data) {
        Intent intent = new Intent(this, FullTheatreActivity.class);
        intent.putExtra("NAME_KEY", data[0]);
        intent.putExtra("DESCRIPTION_KEY", data[1]);
        intent.putExtra("IMAGE_KEY", data[2]);
        intent.putExtra("PHONE_KEY", data[3]);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_theatre);

        mRecyclerView = findViewById(R.id.mRecyclerView1);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //mProgressBar = findViewById(R.id.myDataLoaderProgressBar);
        // mProgressBar.setVisibility(View.VISIBLE);
        mTeacher = new ArrayList<>();
        mAdapter = new TheatreAdapter (ViewTheatreActivity.this, mTeacher);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(ViewTheatreActivity.this);
        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Theatre_uploads");
        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mTeacher.clear();
                for (DataSnapshot teacherSnapshot : dataSnapshot.getChildren()) {
                    Theatre upload = teacherSnapshot.getValue(Theatre.class);
                    upload.setKey(teacherSnapshot.getKey());
                    mTeacher.add(0,upload);
                    mAdapter.notifyItemInserted(0);
                }
                mAdapter.notifyDataSetChanged();
                // mProgressBar.setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ViewTheatreActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                //mProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void onItemClick(int position) {
        Theatre clickedTeacher=mTeacher.get(position);
        String[] teacherData={clickedTeacher.getName(),clickedTeacher.getDescription(),clickedTeacher.getImageUrl(),clickedTeacher.getPhone()};
        openDetailActivity(teacherData);
    }

    @Override
    public void onShowItemClick(int position) {
        Theatre clickedTeacher=mTeacher.get(position);
        String[] teacherData={clickedTeacher.getName(),clickedTeacher.getDescription(),clickedTeacher.getImageUrl(),clickedTeacher.getPhone()};
        openDetailActivity(teacherData);
    }
    @Override
    public void onDeleteItemClick(int position) {
        Theatre selectedItem = mTeacher.get(position);
        final String selectedKey = selectedItem.getKey();
        StorageReference imageRef = mStorage.getReferenceFromUrl(selectedItem.getImageUrl());
        imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mDatabaseRef.child(selectedKey).removeValue();
                Toast.makeText(ViewTheatreActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
    }
}