package com.revolt.primenewsadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.onesignal.OneSignal;

public class MainActivity extends AppCompatActivity {

    Button viewAd,addAd,viewBus,addBus,viewNews,addNews,viewAnn,addAnn,addbusdetails,addauto,viewauto,viewfullauto
            ,addblood,viewblood,addmedia,viewmedia,addemer,viewemer,addtheatre,viewtheatre,addrest,viewrest
            ,addtour,viewtour,addoff,viewoff,addabout,viewabout,addlinks,viewlinks,addfeedback,viewfeedback,viewmarket,viewtaxi,addtaxi,addtaxifull;
    private long backPressedTime = 0;
    private static final String ONESIGNAL_APP_ID = "0136dd72-b8cd-44f6-9932-924dc5a1e627";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        viewAd = findViewById(R.id.viewAd);
        addAd = findViewById(R.id.addAdv);
        viewNews = findViewById(R.id.viewNews);
        addNews = findViewById(R.id.addNews);
        viewAnn = findViewById(R.id.viewAnno);
        addAnn = findViewById(R.id.addAnno);
        addBus=findViewById(R.id.addBus);
        viewBus=findViewById(R.id.viewBus);
        addbusdetails=findViewById(R.id.addbustime);
        addauto=findViewById(R.id.addauto);
        viewauto=findViewById(R.id.viewauto);
        viewfullauto=findViewById(R.id.viewfullauto);
        addblood=findViewById(R.id.addblood);
        viewblood=findViewById(R.id.viewblood);
        addmedia=findViewById(R.id.addmedia);
        viewmedia=findViewById(R.id.viewMedia);
        addemer=findViewById(R.id.AddEmergency);
        viewemer=findViewById(R.id.viewEmergency);
        addtheatre=findViewById(R.id.addtheatre);
        viewtheatre=findViewById(R.id.viewtheatre);
        addrest=findViewById(R.id.addRestaurant);
        viewrest=findViewById(R.id.viewRestaurant);
        addtour=findViewById(R.id.addTourism);
        viewtour=findViewById(R.id.viewTourism);
        addoff=findViewById(R.id.addsthapanam);
        viewoff=findViewById(R.id.viewSthapanam);
        addabout=findViewById(R.id.addaboutus);
        viewabout=findViewById(R.id.viewaboutus);
        addlinks=findViewById(R.id.addlinks);
        viewlinks=findViewById(R.id.viewlinks);
        addfeedback=findViewById(R.id.addfeedback);
        viewfeedback=findViewById(R.id.viewfeedback);
        viewmarket=findViewById(R.id.viewmarket);
        viewtaxi=findViewById(R.id.viewtaxi);
        addtaxi=findViewById(R.id.addtaxi);
        addtaxifull=findViewById(R.id.viewfulltaxi);

        addNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddNewsActivity.class);
                startActivity(i);
            }
        });
        viewNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(MainActivity.this, ViewNewsActivity.class);
                startActivity(i1);
            }
        });
        addAnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(MainActivity.this,AddAnnouncementActivity.class);
                startActivity(i2);
            }
        });
        viewAnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(MainActivity.this,ViewAnnouncementActivity.class);
                startActivity(i3);
            }
        });
        addBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i4 = new Intent(MainActivity.this,AddBusStationsActivity.class);
                startActivity(i4);
            }
        });
        viewBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i5 = new Intent(MainActivity.this,ViewbusStationsActivity.class);
                startActivity(i5);
            }
        });
        addbusdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(MainActivity.this, AddBusDetailsActivity.class);
                startActivity(i6);

            }
        });
        addAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(MainActivity.this,AddAdvActivity.class);
                startActivity(i6);
            }
        });
        viewAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(MainActivity.this,ViewAdvActivity.class);
                startActivity(i6);

            }
        });
        addauto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(MainActivity.this,AddAutoActivity.class);
                startActivity(i6);

            }
        });
        viewauto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(MainActivity.this,ViewAutoActivity.class);
                startActivity(i6);
            }
        });
        viewfullauto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(MainActivity.this,AutoDetailsActivity.class);
                startActivity(i6);

            }
        });
        addblood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(MainActivity.this,AddBloodActivity.class);
                startActivity(i6);

            }
        });
        viewblood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(MainActivity.this,ViewBloodActivity.class);
                startActivity(i6);
            }
        });
        addmedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(MainActivity.this,AddMediaActivity.class);
                startActivity(i6);
            }
        });
        viewmedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(MainActivity.this,ViewMediaActivity.class);
                startActivity(i6);

            }
        });
        addemer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(MainActivity.this,AddEmergencyActivity.class);
                startActivity(i6);
            }
        });

        viewemer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(MainActivity.this,ViewEmergencyActivity.class);
                startActivity(i6);
            }
        });
        addtheatre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(MainActivity.this,AddTheatreActivity.class);
                startActivity(i6);
            }
        });
        viewtheatre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(MainActivity.this,ViewTheatreActivity.class);
                startActivity(i6);

            }
        });
        addrest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(MainActivity.this,AddRestaurantActivity.class);
                startActivity(i6);
            }
        });
        viewrest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(MainActivity.this,ViewRestaurantActivity.class);
                startActivity(i6);

            }
        });
        addtour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(MainActivity.this,AddTourismActivity.class);
                startActivity(i6);
            }
        });
        viewtour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(MainActivity.this,ViewTourismActivity.class);
                startActivity(i6);

            }
        });
        addoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(MainActivity.this,AddOfficeActivity.class);
                startActivity(i6);
            }
        });
        viewoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(MainActivity.this,ViewOfficeActivity.class);
                startActivity(i6);

            }
        });
        addabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(MainActivity.this,AddAboutusActivity.class);
                startActivity(i6);

            }
        });
        viewabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(MainActivity.this,ViewAboutUsActivity.class);
                startActivity(i6);

            }
        });
        addlinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(MainActivity.this,AddLinksActivity.class);
                startActivity(i6);
            }
        });
        viewlinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(MainActivity.this,ViewLinksActivity.class);
                startActivity(i6);
            }
        });
        addfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(MainActivity.this,AddFeedbackActivity.class);
                startActivity(i6);
            }
        });
        viewfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(MainActivity.this,ViewFeedbackActivity.class);
                startActivity(i6);
            }
        });
        viewmarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(MainActivity.this,ViewNattuActivity.class);
                startActivity(i6);
            }
        });
        addtaxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(MainActivity.this,AddTaxiActivity.class);
                startActivity(i6);
            }
        });
        viewtaxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(MainActivity.this,ViewTaxiActivity.class);
                startActivity(i6);
            }
        });
        addtaxifull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(MainActivity.this,AddTaxiFullActivity.class);
                startActivity(i6);
            }
        });


    }
    @Override
    public void onBackPressed()
    {
        long t = System.currentTimeMillis();
        if (t - backPressedTime > 2000) {
            backPressedTime = t;
            Toast.makeText(this, "Press back again to exit",
                    Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
        }


    }
}