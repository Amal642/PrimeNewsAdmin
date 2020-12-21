package com.revolt.primenewsadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ViewNattuActivity extends AppCompatActivity {


    FloatingActionButton button;
    RecyclerView recyclerView;
    FirebaseRecyclerOptions<Nattu> options;
    FirebaseRecyclerAdapter<Nattu,MyViewHolder> adapter;
    DatabaseReference Dataref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_nattu);

        button=findViewById(R.id.naatufloat);
        recyclerView=findViewById(R.id.recyclerViewnattu);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        Dataref= FirebaseDatabase.getInstance().getReference().child("Nattu");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ViewNattuActivity.this,AddNattuActivity.class);
                startActivity(intent);
            }
        });
        
        LoadData();
    }

    private void LoadData() {
        options=new FirebaseRecyclerOptions.Builder<Nattu>().setQuery(Dataref,Nattu.class).build();
        adapter=new FirebaseRecyclerAdapter<Nattu, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i, @NonNull Nattu nattu) {
                myViewHolder.name.setText(nattu.getName());
                myViewHolder.desc.setText(nattu.getDesc());
                myViewHolder.phone.setText(nattu.getPhone());
                Picasso.get()
                        .load(nattu.getImageurl())
                        .into(myViewHolder.imageView);


            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_modelnaatu,parent,false);
                return new MyViewHolder(v);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
}