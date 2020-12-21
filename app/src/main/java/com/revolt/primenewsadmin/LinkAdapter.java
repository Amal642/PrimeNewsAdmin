package com.revolt.primenewsadmin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


import java.security.AccessController;

public class LinkAdapter extends FirebaseRecyclerAdapter<Link,LinkAdapter.myviewholder>{
    public OnItemClickListener mListener;
    Context mContext;

    public LinkAdapter(@NonNull FirebaseRecyclerOptions<Link> options, Context context) {
        super(options);
        mContext=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull final LinkAdapter.myviewholder myviewholder, final int position, @NonNull Link announcements) {
        myviewholder.name.setText(announcements.getText());
        myviewholder.link=announcements.getLink();
        myviewholder.deleteAnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(myviewholder.name.getContext());
                builder.setTitle("Delete");
                builder.setMessage("Sure to proceed?");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Link_upload")
                                .child(getRef(position).getKey()).removeValue();

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public LinkAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_modellink,parent,false);
        return new LinkAdapter.myviewholder(view,mListener);
    }

    public interface OnItemClickListener{
        void onItemClick(int position);

    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }

    class myviewholder extends RecyclerView.ViewHolder{
        TextView name;
        String link;
        ImageButton deleteAnn,showlink;
        public myviewholder(@NonNull final View itemView, final OnItemClickListener listener) {
            super(itemView);
            name=itemView.findViewById(R.id.titleann);
            deleteAnn=itemView.findViewById(R.id.delete);
            showlink=itemView.findViewById(R.id.showlink);

            showlink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                    mContext.startActivity(browserIntent);
                    /*int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        mListener.onItemClick(position);}
                    Toast.makeText(view.getContext(), "Showing news", Toast.LENGTH_SHORT).show();*/
                }
            });



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(),"Position: "+getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                    mContext.startActivity(browserIntent);
                   /* if (listener!=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }*/
                }
            });

        }
    }
}
