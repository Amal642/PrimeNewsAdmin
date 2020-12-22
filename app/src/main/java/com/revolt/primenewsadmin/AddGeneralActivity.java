package com.revolt.primenewsadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddGeneralActivity extends AppCompatActivity {

    private EditText addbus;
    private Button uploadbus;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_general);

        addbus=findViewById(R.id.taxiadd);
        uploadbus=findViewById(R.id.uploadtaxi);
        databaseReference = db.getReference("General");

        uploadbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadBus();
            }
        });
    }
    private void uploadBus() {
        String Addbus=addbus.getText().toString();
        String id=databaseReference.push().getKey();

        if(!TextUtils.isEmpty(Addbus)){
            Taxi busstations = new Taxi(Addbus);
            databaseReference.child(id).setValue(busstations);
            Toast.makeText(this, "Upload Successful", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(AddGeneralActivity.this,MainActivity.class);
            startActivity(i);

        }else{
            Toast.makeText(this, "Please Enter a Taxi Station", Toast.LENGTH_SHORT).show();
        }
    }
}