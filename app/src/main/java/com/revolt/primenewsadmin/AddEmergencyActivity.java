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

public class AddEmergencyActivity extends AppCompatActivity {

    EditText ambname,ambplace,ambphn,firename,fireplace,firephn,polname,polplace,polphn;
    Button upload,uploadfire,uploadpol;
    FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference,datafire,datapol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emergency);

        ambname=findViewById(R.id.ambname);
        ambplace=findViewById(R.id.ambplace);
        ambphn=findViewById(R.id.ambphn);
        upload=findViewById(R.id.upload);
        firename=findViewById(R.id.firename);
        fireplace=findViewById(R.id.fireplace);
        firephn=findViewById(R.id.firephn);
        polname=findViewById(R.id.polname);
        polplace=findViewById(R.id.polplace);
        polphn=findViewById(R.id.polphn);
        uploadfire=findViewById(R.id.fireupload);
        uploadpol=findViewById(R.id.polupload);
        databaseReference = db.getReference("Ambulance_upload");
        datafire=db.getReference("fire_upload");
        datapol=db.getReference("pol_upload");

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadamb();
            }
        });
        uploadpol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadl();
            }
        });
        uploadfire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadfiree();
            }
        });

    }

    private void uploadfiree() {
        String name =firename.getText().toString();
        String place = fireplace.getText().toString();
        String num= firephn.getText().toString();
        String id = datafire.push().getKey();

        if(!TextUtils.isEmpty(name)){
            fire announcements = new fire(name,place,num,id);
            datafire.child(id).setValue(announcements);
            Toast.makeText(this, "Upload Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddEmergencyActivity.this,MainActivity.class);
            startActivity(intent);

        }else{
            Toast.makeText(this, "Please Enter a Title", Toast.LENGTH_SHORT).show();
        }
    }

    private void uploadl() {
        String name =polname.getText().toString();
        String place = polplace.getText().toString();
        String num= polphn.getText().toString();
        String id = datapol.push().getKey();

        if(!TextUtils.isEmpty(name)){
            police announcements = new police(name,place,num,id);
            datapol.child(id).setValue(announcements);
            Toast.makeText(this, "Upload Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddEmergencyActivity.this,MainActivity.class);
            startActivity(intent);

        }else{
            Toast.makeText(this, "Please Enter a Title", Toast.LENGTH_SHORT).show();
        }
    }

    private void uploadamb() {

        String name =ambname.getText().toString();
        String place = ambplace.getText().toString();
        String num= ambphn.getText().toString();
        String id = databaseReference.push().getKey();

        if(!TextUtils.isEmpty(name)){
            Emergency announcements = new Emergency(name,place,num,id);
            databaseReference.child(id).setValue(announcements);
            Toast.makeText(this, "Upload Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddEmergencyActivity.this,MainActivity.class);
            startActivity(intent);

        }else{
            Toast.makeText(this, "Please Enter a Title", Toast.LENGTH_SHORT).show();
        }
    }
}