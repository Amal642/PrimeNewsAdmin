package com.revolt.primenewsadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AutoDetailsActivity extends AppCompatActivity {

    Spinner spinner;
    EditText busname,service,time,busclass,route;
    Button uploadbtn;
    FirebaseDatabase db;
    DatabaseReference databaseReference,dataa;
    ValueEventListener listener;
    ArrayAdapter<String> adapter;
    ArrayList<String> spinnerdatalist;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_details);


        busname = findViewById(R.id.busname);
        service=findViewById(R.id.service);
        time=findViewById(R.id.time);
        uploadbtn = findViewById(R.id.uploadbus);

        databaseReference=db.getInstance().getReference("Auto_Stations");
        dataa=db.getInstance().getReference("Auto_Stationss");


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final BusStatDetails busStatDetails = snapshot.getValue(BusStatDetails.class);

                uploadbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String Busname = busname.getText().toString().trim();
                        String BusService = service.getText().toString().trim();
                        String BusTym = time.getText().toString().trim();
                        String id = databaseReference.push().getKey();

                        //FoodDetails info = new FoodDetails(dishes,quantity,price,descrption,String.valueOf(uri),RandomUID,ChefId);
                        /*firebaseDatabase.getInstance().getReference("FoodDetails").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID)
                                .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                progressDialog.dismiss();
                                Toast.makeText(chef_postDish.this,"Dish Posted Successfully!",Toast.LENGTH_SHORT).show();
                            }
                        });*/


                        AutoStatDetails busStatDetails1 = new AutoStatDetails( Busname, BusService, BusTym,id);
                        data= spinner.getSelectedItem().toString();
                        dataa.child(data).child(id).setValue(busStatDetails1).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(AutoDetailsActivity.this, "Upload Successful", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(AutoDetailsActivity.this, MainActivity.class);
                                startActivity(i);

                            }
                        });



                    }
                });


                spinner = findViewById(R.id.busspinner);
                spinnerdatalist = new ArrayList<>();
                adapter = new ArrayAdapter<String>(AutoDetailsActivity.this,
                        android.R.layout.simple_spinner_dropdown_item, spinnerdatalist);
                spinner.setAdapter(adapter);
                retrievedata();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


            public void retrievedata() {
                listener = databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot item : snapshot.getChildren()) {
                            spinnerdatalist.add(item.child("name").getValue().toString());
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

    }
}