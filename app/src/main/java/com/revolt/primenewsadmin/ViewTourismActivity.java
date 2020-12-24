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

public class ViewTourismActivity extends AppCompatActivity implements TourismAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private TourismAdapter mAdapter;
    //private ProgressBar mProgressBar;
    private FirebaseStorage mStorage;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListener;
    private List<Tourism> mTeachers;
    private void openDetailActivity(String[] data) {
        Intent intent = new Intent(this,FullTourismActivity.class);
        intent.putExtra("NAME_KEY", data[0]);
        intent.putExtra("DESCRIPTION_KEY", data[1]);
        intent.putExtra("IMAGE_KEY", data[2]);
        intent.putExtra("PHONE_KEY", data[3]);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tourism);

        mRecyclerView = findViewById(R.id.mRecyclerView3);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //mProgressBar = findViewById(R.id.myDataLoaderProgressBar);
        // mProgressBar.setVisibility(View.VISIBLE);
        mTeachers = new ArrayList<>();
        mAdapter = new TourismAdapter(ViewTourismActivity.this, mTeachers);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(ViewTourismActivity.this);
        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Tourism_uploads");
        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mTeachers.clear();
                for (DataSnapshot teacherSnapshot : dataSnapshot.getChildren()) {
                    Tourism upload = teacherSnapshot.getValue(Tourism.class);
                    upload.setKey(teacherSnapshot.getKey());
                    mTeachers.add(0,upload);
                    mAdapter.notifyItemInserted(0);
                }
                mAdapter.notifyDataSetChanged();
                // mProgressBar.setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ViewTourismActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                //mProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void onItemClick(int position) {
        Tourism clickedTeacher=mTeachers.get(position);
        String[] teacherData={clickedTeacher.getName(),clickedTeacher.getDescription(),clickedTeacher.getImageUrl(),clickedTeacher.getPhone()};
        openDetailActivity(teacherData);
    }

    @Override
    public void onShowItemClick(int position) {
        Tourism clickedTeacher=mTeachers.get(position);
        String[] teacherData={clickedTeacher.getName(),clickedTeacher.getDescription(),clickedTeacher.getImageUrl(),clickedTeacher.getPhone()};
        openDetailActivity(teacherData);
    }
    @Override
    public void onDeleteItemClick(int position) {
        Tourism selectedItem = mTeachers.get(position);
        final String selectedKey = selectedItem.getKey();
        StorageReference imageRef = mStorage.getReferenceFromUrl(selectedItem.getImageUrl());
        imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mDatabaseRef.child(selectedKey).removeValue();
                Toast.makeText(ViewTourismActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
    }
}