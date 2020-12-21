package com.revolt.primenewsadmin;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView name,desc,phone;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView=itemView.findViewById(R.id.picnaatu);
        name=itemView.findViewById(R.id.itemname1);
        desc=itemView.findViewById(R.id.itemDescription1);
        phone=itemView.findViewById(R.id.itemphonee1);

    }
}
