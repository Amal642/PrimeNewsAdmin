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

public class AddMediaActivity extends AppCompatActivity {

    EditText blodname,blodnum,bluodplace;
    Button upload;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_media);

        blodname=findViewById(R.id.bldtext);
        blodnum=findViewById(R.id.blnumber);
        bluodplace=findViewById(R.id.bldplace);
        upload=findViewById(R.id.uploadbld);
        databaseReference = db.getReference("Media_upload");

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadblood();
            }
        });


    }

    private void uploadblood() {
        String name = blodname.getText().toString();
        String place = bluodplace.getText().toString();
        String num = blodnum.getText().toString();
        String id = databaseReference.push().getKey();

        if(!TextUtils.isEmpty(name)){
            Media announcements = new Media(name,place,num,id);
            databaseReference.child(id).setValue(announcements);
            Toast.makeText(this, "Upload Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddMediaActivity.this,MainActivity.class);
            startActivity(intent);

        }else{
            Toast.makeText(this, "Please Enter a Title", Toast.LENGTH_SHORT).show();
        }
    }
}