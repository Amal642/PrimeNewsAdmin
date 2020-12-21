package com.revolt.primenewsadmin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class MediaAdapter extends FirebaseRecyclerAdapter<Media,MediaAdapter.myviewholder> {
    public MediaAdapter(@NonNull FirebaseRecyclerOptions<Media> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final MediaAdapter.myviewholder myviewholder, final int position, @NonNull Media announcements) {
        myviewholder.name.setText(announcements.getName());
        myviewholder.place.setText(announcements.getPlace());
        myviewholder.num.setText(announcements.getNum());
        myviewholder.deleteAnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(myviewholder.name.getContext());
                builder.setTitle("Delete");
                builder.setMessage("Sure to proceed?");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Media_upload")
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
    public MediaAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_model4,parent,false);
        return new MediaAdapter.myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{
        TextView name,place,num;
        ImageButton deleteAnn;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.titleann);
            place=itemView.findViewById(R.id.descann);
            num=itemView.findViewById(R.id.phoneann);
            deleteAnn=itemView.findViewById(R.id.delete);

        }
    }
}
