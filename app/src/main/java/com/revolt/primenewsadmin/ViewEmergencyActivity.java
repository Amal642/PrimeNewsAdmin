package com.revolt.primenewsadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewEmergencyActivity extends AppCompatActivity {

    Button Ambulance,Fire,Police;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_emergency);

        Ambulance=findViewById(R.id.ambulance);
        Fire=findViewById(R.id.fire);
        Police=findViewById(R.id.police);

        Ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(ViewEmergencyActivity.this,AmbulanceActivity.class);
                startActivity(intent);
            }
        });
        Fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(ViewEmergencyActivity.this,FireActivity.class);
                startActivity(intent);
            }
        });
        Police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(ViewEmergencyActivity.this,PoliceActivity.class);
                startActivity(intent);
            }
        });
    }
}