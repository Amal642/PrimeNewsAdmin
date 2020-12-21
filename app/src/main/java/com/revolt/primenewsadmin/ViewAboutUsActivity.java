package com.revolt.primenewsadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewAboutUsActivity extends AppCompatActivity implements ValueEventListener {

    TextView aboutustext;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference("About_Us");
    DatabaseReference firstDatabase = databaseReference.child("name");
    String id,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_about_us);

        aboutustext=findViewById(R.id.aboutustext);




    }


   @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if(snapshot.getValue(String.class)!=null){
            String key = snapshot.getKey();
            if(key.equals("name")){
            String first=snapshot.getValue(String.class);
            aboutustext.setText(first);}
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        firstDatabase.addValueEventListener(this);
    }


}