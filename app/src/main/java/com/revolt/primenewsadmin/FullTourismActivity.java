package com.revolt.primenewsadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FullTourismActivity extends AppCompatActivity {

    TextView nameDetailTextView,descriptionDetailTextView,dateDetailTextView,categoryDetailTextView;
    ImageView teacherDetailImageView;
    private void initializeWidgets(){
        nameDetailTextView= findViewById(R.id.nameDetailTextView);
        descriptionDetailTextView= findViewById(R.id.descriptionDetailTextView);
        //dateDetailTextView= findViewById(R.id.dateDetailTextView);
        //categoryDetailTextView= findViewById(R.id.categoryDetailTextView);
        teacherDetailImageView=findViewById(R.id.teacherDetailImageView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_tourism);

        initializeWidgets();
        //RECEIVE DATA FROM ITEMSACTIVITY VIA INTENT
        Intent i=this.getIntent();
        String name=i.getExtras().getString("NAME_KEY");
        String description=i.getExtras().getString("DESCRIPTION_KEY");
        String imageURL=i.getExtras().getString("IMAGE_KEY");
        //SET RECEIVED DATA TO TEXTVIEWS AND IMAGEVIEWS
        nameDetailTextView.setText(name);
        descriptionDetailTextView.setText(description);
        Picasso.get()
                .load(imageURL)
                //.placeholder(R.drawable.placeholder)
                .fit()
                .centerCrop()
                .into(teacherDetailImageView);
    }
}