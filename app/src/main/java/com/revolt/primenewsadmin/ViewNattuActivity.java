package com.revolt.primenewsadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ViewNattuActivity extends AppCompatActivity implements NattuAdapter.OnItemClickListener{

    private RecyclerView mRecyclerView;
    private NattuAdapter mAdapter;
    FloatingActionButton button;
    private FirebaseStorage mStorage;
    private DatabaseReference mDatabaseRef ;
    private ValueEventListener mDBListener;
    private List<Nattu> mTeachers;
    private void openDetailActivity(String[] data) {
        Intent intent = new Intent(this, FullNaatuActivity.class);
        intent.putExtra("TITLE_KEY", data[0]);
        intent.putExtra("PRICE_KEY", data[1]);
        intent.putExtra("ALAV_KEY", data[2]);
        intent.putExtra("PLACE_KEY", data[3]);
        intent.putExtra("NAME_KEY", data[4]);
        intent.putExtra("PHONE_KEY", data[5]);
        intent.putExtra("DESCRIPTION_KEY", data[6]);
        intent.putExtra("IMAGE_KEY", data[7]);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_nattu);

        button=findViewById(R.id.naatufloat);
        mRecyclerView=findViewById(R.id.recyclerViewnattu);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setHasFixedSize(true);
        mDatabaseRef= FirebaseDatabase.getInstance().getReference().child("Nattu");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ViewNattuActivity.this,AddNattuActivity.class);
                startActivity(intent);
            }
        });


        mTeachers = new ArrayList<>();
        mAdapter = new NattuAdapter(ViewNattuActivity.this, mTeachers);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(ViewNattuActivity.this);
        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Nattu");
        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mTeachers.clear();
                for (DataSnapshot teacherSnapshot : dataSnapshot.getChildren()) {
                    Nattu upload = teacherSnapshot.getValue(Nattu.class);
                    upload.setId(teacherSnapshot.getKey());
                    mTeachers.add(0,upload);
                    mAdapter.notifyItemInserted(0);
                }
                mAdapter.notifyDataSetChanged();
                // mProgressBar.setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ViewNattuActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                //mProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void onItemClick(int position) {
        Nattu clickedTeacher=mTeachers.get(position);
        String[] teacherData={clickedTeacher.getSadhanam(),clickedTeacher.getPrice(),clickedTeacher.getAlav(),
                clickedTeacher.getPlace(),clickedTeacher.getName(),clickedTeacher.getPhone(),
                clickedTeacher.getDesc(),clickedTeacher.getImageurl()};
        openDetailActivity(teacherData);
    }

    @Override
    public void onShowItemClick(int position) {
        Nattu clickedTeacher=mTeachers.get(position);
        String[] teacherData={clickedTeacher.getSadhanam(),clickedTeacher.getPrice(),clickedTeacher.getAlav(),
                clickedTeacher.getPlace(),clickedTeacher.getName(),clickedTeacher.getPhone(),
                clickedTeacher.getDesc(),clickedTeacher.getImageurl()};
        openDetailActivity(teacherData);
    }
    @Override
    public void onDeleteItemClick(int position) {
        Nattu selectedItem = mTeachers.get(position);
        final String selectedKey = selectedItem.getId();
        StorageReference imageRef = mStorage.getReferenceFromUrl(selectedItem.getImageurl());
        imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mDatabaseRef.child(selectedKey).removeValue();
                Toast.makeText(ViewNattuActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
    }


    }
