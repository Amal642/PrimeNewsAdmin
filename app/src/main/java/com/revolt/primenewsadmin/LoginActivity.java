package com.revolt.primenewsadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText userid,password;
    Button loginbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userid=findViewById(R.id.userid);
        password=findViewById(R.id.password);
        loginbutton=findViewById(R.id.loginbutton);


        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userid.getText().toString().equals("primenewsadmin") && password.getText().toString().equals("Admin@prime123"))
                {
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(LoginActivity.this, "Login Failed Please Contact Admins", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}