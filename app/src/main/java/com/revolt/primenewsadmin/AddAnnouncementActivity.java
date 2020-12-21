package com.revolt.primenewsadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.onesignal.OneSignal;


public class AddAnnouncementActivity extends AppCompatActivity {

    private EditText DescriptionAnn,TitleAnn;
    private Button uploadbtn,notification;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    private static final String ONESIGNAL_APP_ID = "0136dd72-b8cd-44f6-9932-924dc5a1e627";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_announcement);

        DescriptionAnn=findViewById(R.id.descann);
        TitleAnn=findViewById(R.id.titleann);
        uploadbtn=findViewById(R.id.buttonann);
        notification=findViewById(R.id.notifann);
        databaseReference = db.getReference("Ann_upload");
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri=Uri.parse("https://app.onesignal.com/apps/0136dd72-b8cd-44f6-9932-924dc5a1e627/campaigns");
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
        });


        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploaddata();
            }
        });


    }

    private void uploaddata() {
        String Title = TitleAnn.getText().toString();
        String Description = DescriptionAnn.getText().toString();
        String id = databaseReference.push().getKey();

        if(!TextUtils.isEmpty(Title)){
            Announcements announcements = new Announcements(Title,Description,id);
            databaseReference.child(id).setValue(announcements);
            Toast.makeText(this, "Upload Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddAnnouncementActivity.this,MainActivity.class);
            startActivity(intent);

        }else{
            Toast.makeText(this, "Please Enter a Title", Toast.LENGTH_SHORT).show();
        }
    }
}
