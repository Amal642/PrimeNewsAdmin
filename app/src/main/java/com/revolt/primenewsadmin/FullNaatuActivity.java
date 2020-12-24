package com.revolt.primenewsadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FullNaatuActivity extends AppCompatActivity {

    TextView title,price,alav,place,name,phone,desc;
    ImageView teacherDetailImageView;
    ImageButton call;
    private void initializeWidgets(){
        title=findViewById(R.id.nameDetailTextView);
        price=findViewById(R.id.price);
        alav=findViewById(R.id.alav);
        place=findViewById(R.id.place);
        name=findViewById(R.id.name);
        phone=findViewById(R.id.phoneno);
        call=findViewById(R.id.call);
        desc=findViewById(R.id.descriptionDetailTextView);
        teacherDetailImageView=findViewById(R.id.teacherDetailImageView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_naatu);

        initializeWidgets();
        //RECEIVE DATA FROM ITEMSACTIVITY VIA INTENT
        Intent i=this.getIntent();
        String title1=i.getExtras().getString("TITLE_KEY");
        String price1=i.getExtras().getString("PRICE_KEY");
        String alav1=i.getExtras().getString("ALAV_KEY");
        String place1=i.getExtras().getString("PLACE_KEY");
        String name1=i.getExtras().getString("NAME_KEY");
        final String phone1=i.getExtras().getString("PHONE_KEY");
        String description1=i.getExtras().getString("DESCRIPTION_KEY");
        String imageURL=i.getExtras().getString("IMAGE_KEY");
        //SET RECEIVED DATA TO TEXTVIEWS AND IMAGEVIEWS
        title.setText(title1);
        price.setText(price1);
        alav.setText(alav1);
        place.setText(place1);
        name.setText(name1);
        phone.setText(phone1);
        desc.setText(description1);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+phone1));
                startActivity(i);
            }
        });
        Picasso.get()
                .load(imageURL)
                //.placeholder(R.drawable.placeholder)
                .fit()
                .centerCrop()
                .into(teacherDetailImageView);
    }
}