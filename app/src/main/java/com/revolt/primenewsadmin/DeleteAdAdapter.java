package com.revolt.primenewsadmin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeleteAdAdapter extends FirebaseRecyclerAdapter<Link,DeleteAdAdapter.myviewholder> {





    public DeleteAdAdapter.OnItemClickListener mListener;
    Context mContext;

    public DeleteAdAdapter(@NonNull FirebaseRecyclerOptions<Link> options, Context context) {
        super(options);
        mContext=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull final DeleteAdAdapter.myviewholder myviewholder, final int position, @NonNull Link announcements) {
        myviewholder.name.setText(announcements.getText());
        FirebaseDatabase.getInstance().getReference().child("Image")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                        for (DataSnapshot data:datasnapshot.getChildren())
                        {
                            myviewholder.link=data.child("imageurl").getValue().toString();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });;
        myviewholder.deleteAnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(myviewholder.name.getContext());
                builder.setTitle("Delete");
                builder.setMessage("Sure to proceed?");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Image")
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
    public DeleteAdAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_modellink,parent,false);
        return new DeleteAdAdapter.myviewholder(view,mListener);
    }

    public interface OnItemClickListener{
        void onItemClick(int position);

    }
    public void setOnItemClickListener(DeleteAdAdapter.OnItemClickListener listener){
        mListener=listener;
    }

    class myviewholder extends RecyclerView.ViewHolder {
        TextView name;
        public String link;
        ImageButton deleteAnn, showlink;

        public myviewholder(@NonNull final View itemView, final DeleteAdAdapter.OnItemClickListener listener) {
            super(itemView);
            name = itemView.findViewById(R.id.titleann);
            deleteAnn = itemView.findViewById(R.id.delete);
            showlink = itemView.findViewById(R.id.showlink);

            showlink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "helo"+link, Toast.LENGTH_LONG).show();
                    if(link != null && Uri.parse(link) != null) {


                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                        mContext.startActivity(browserIntent);
                    }
                }
            });


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(link != null && Uri.parse(link) != null) {
                        //Toast.makeText(itemView.getContext(), "Position: " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                        mContext.startActivity(browserIntent);
                    }

                }
            });

        }
    }
}
