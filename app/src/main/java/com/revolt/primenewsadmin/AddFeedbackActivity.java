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

public class AddFeedbackActivity extends AppCompatActivity {

    EditText feedname,feedback;
    Button upload;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feedback);

        feedback=findViewById(R.id.feedtext);
        feedname=findViewById(R.id.feedname);
        upload=findViewById(R.id.uploadfeed);
        databaseReference = db.getReference("Feedback_upload");

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadblood();
            }
        });
    }

    private void uploadblood() {
        String name = feedname.getText().toString();
        String text = feedback.getText().toString();
        String id = databaseReference.push().getKey();

        if(!TextUtils.isEmpty(name)){
            Feedback announcements = new Feedback(name,text,id);
            databaseReference.child(id).setValue(announcements);
            Toast.makeText(this, "Upload Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddFeedbackActivity.this,MainActivity.class);
            startActivity(intent);

        }else{
            Toast.makeText(this, "Please Enter a Title", Toast.LENGTH_SHORT).show();
        }
    }
}