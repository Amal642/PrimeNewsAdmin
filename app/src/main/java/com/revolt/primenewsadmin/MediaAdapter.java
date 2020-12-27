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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.RecyclerViewHolder> {

    private Context mContext;
    private List<Media> teachers;
    public MediaAdapter(Context context, List<Media> uploads) {
        mContext = context;
        teachers = uploads;
    }


    @Override
    public MediaAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.row_model4, parent, false);
        return new MediaAdapter.RecyclerViewHolder(v);
    }
    @Override
    public void onBindViewHolder(final MediaAdapter.RecyclerViewHolder holder, int position) {
        final Media currentTeacher = teachers.get(position);
        holder.nameTextView.setText(currentTeacher.getName());
        holder.placee.setText(currentTeacher.getPlace());
        holder.time.setText(currentTeacher.getNum());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.nameTextView.getContext());
                builder.setTitle("Delete");
                builder.setMessage("Sure to proceed?");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Media_upload")
                                .child(currentTeacher.getId()).removeValue();
                        Toast.makeText(mContext, "deleted", Toast.LENGTH_SHORT).show();

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
    @Override
    public int getItemCount() {
        return teachers.size();
    }
    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        public TextView nameTextView,placee,time;
        public ImageButton delete,call;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            nameTextView =itemView.findViewById ( R.id.titleann);
            placee=itemView.findViewById(R.id.descann);
            time=itemView.findViewById(R.id.phoneann);
            delete=itemView.findViewById(R.id.delete);
            call=itemView.findViewById(R.id.call);
            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final String pdescription = teachers.get(getAdapterPosition()).getNum();
                    Intent i = new Intent(Intent.ACTION_DIAL);
                    i.setData(Uri.parse("tel:"+pdescription));
                    mContext.startActivity(i);
                }
            });


        }



    }
    /*public MediaAdapter(@NonNull FirebaseRecyclerOptions<Media> options) {
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
    }*/
}
