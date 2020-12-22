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

public class AddHospitalFullActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_add_hospital_full);

        busname = findViewById(R.id.taxiname);
        service=findViewById(R.id.taxiservice);
        time=findViewById(R.id.taxitime);
        busclass = findViewById(R.id.taxiclass);
        route=findViewById(R.id.taxiroute);
        uploadbtn = findViewById(R.id.uploadtaxifull);

        databaseReference=db.getInstance().getReference("Hospital");
        dataa=db.getInstance().getReference("Hospitals");


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
                        String Busclass = busclass.getText().toString().trim();
                        String BusRoute = route.getText().toString().trim();
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


                        BusStatDetails busStatDetails1 = new BusStatDetails( Busname, BusService, BusTym, Busclass, BusRoute, id);
                        data= spinner.getSelectedItem().toString();
                        dataa.child(data).child(id).setValue(busStatDetails1).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(AddHospitalFullActivity.this, "Upload Successful", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(AddHospitalFullActivity.this, MainActivity.class);
                                startActivity(i);

                            }
                        });



                    }
                });


                spinner = findViewById(R.id.taxispinner);
                spinnerdatalist = new ArrayList<>();
                adapter = new ArrayAdapter<String>(AddHospitalFullActivity.this,
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