package com.revolt.primenewsadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewLinksActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinkAdapter adapter;
    String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_links);

        recyclerView =(RecyclerView)findViewById(R.id.recviewlink);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Link> options =
                new FirebaseRecyclerOptions.Builder<Link>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Link_upload"),Link.class)
                        .build();

        adapter= new LinkAdapter(options,this);
        recyclerView.setAdapter(adapter);

        //final List<Link> mTeachers = new ArrayList<>();


       /* adapter.setOnItemClickListener(new LinkAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                FirebaseDatabase.getInstance().getReference().child("Link_upload")
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                                //mTeachers.clear();
                                for (DataSnapshot data:datasnapshot.getChildren())
                                {
                                   link=data.child("link").getValue().toString();
                                   gotourl(link);
                                }


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
            }



            private void gotourl(String link) {
                Uri uri=Uri.parse(link);
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
        });*/



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
    }
}