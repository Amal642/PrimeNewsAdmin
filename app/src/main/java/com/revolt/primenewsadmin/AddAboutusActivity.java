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

public class AddAboutusActivity extends AppCompatActivity {

    EditText aboutus;
    Button upload;
    FirebaseDatabase db;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aboutus);

        aboutus=findViewById(R.id.aboutus);
        upload=findViewById(R.id.upload1);
        db=FirebaseDatabase.getInstance();
        ref=db.getReference("About_Us");
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadabt();
            }
        });
    }

    private void uploadabt() {
        String name=aboutus.getText().toString();
        //String id = ref.push().getKey();

        if(!TextUtils.isEmpty(name)){
            Aboutus announcements = new Aboutus(name);
            ref.setValue(announcements);
            Toast.makeText(this, "Upload Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddAboutusActivity.this,MainActivity.class);
            startActivity(intent);

        }else{
            Toast.makeText(this, "Please Enter a Title", Toast.LENGTH_SHORT).show();
        }
    }
}