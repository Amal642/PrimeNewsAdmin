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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class BloodAdapter extends FirebaseRecyclerAdapter<Blood,BloodAdapter.myviewholder> {
    private Context mcontext;
    List<Blood>teachers;
    public BloodAdapter(@NonNull FirebaseRecyclerOptions<Blood> options,Context context) {
        super(options);
        mcontext=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull final BloodAdapter.myviewholder myviewholder, final int position,@NonNull Blood blood) {
        final Blood announcements=teachers.get(position);
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
                        FirebaseDatabase.getInstance().getReference().child("Blood_upload")
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
    public BloodAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_model4,parent,false);
        return new BloodAdapter.myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{
        TextView name,place,num;
        ImageButton deleteAnn,call;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.titleann);
            place=itemView.findViewById(R.id.descann);
            num=itemView.findViewById(R.id.phoneann);
            deleteAnn=itemView.findViewById(R.id.delete);
            call=itemView.findViewById(R.id.call);
            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final String pdescription = teachers.get(getAdapterPosition()).getNum();
                    Intent i = new Intent(Intent.ACTION_DIAL);
                    i.setData(Uri.parse("tel:"+pdescription));
                    mcontext.startActivity(i);

                }
            });

        }
    }
}
