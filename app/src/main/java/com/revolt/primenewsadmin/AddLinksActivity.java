package com.revolt.primenewsadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddLinksActivity extends AppCompatActivity {

    EditText linkname,link;
    Button upload;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_links);

        linkname=findViewById(R.id.linktext);
        link=findViewById(R.id.link);
        upload=findViewById(R.id.uploadlink);

        databaseReference=db.getReference("Link_upload");
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadlink();
            }
        });
    }

    private void uploadlink() {

        String name=linkname.getText().toString();
        String links=link.getText().toString();
        String id = databaseReference.push().getKey();

        if (!links.startsWith("http://") && !links.startsWith("https://")) {
            //Uri name1 = Uri.parse("http://" + name);
            links="http://"+links;
        }

        if(!TextUtils.isEmpty(name)){
            Link announcements = new Link(name,links,id);
            databaseReference.child(id).setValue(announcements);
            Toast.makeText(this, "Upload Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(
                    AddLinksActivity.this,MainActivity.class);
            startActivity(intent);

        }else{
            Toast.makeText(this, "Please Enter a Title", Toast.LENGTH_SHORT).show();
        }
    }
}