package com.revolt.primenewsadmin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

public class AutoAdapter extends  RecyclerView.Adapter<AutoAdapter.RecyclerViewHolder>{

    private Context mContext;
    private List<AutoStatDetails> teachers;
    String entity;
    public AutoAdapter(Context context, List<AutoStatDetails> uploads,String name) {
        mContext = context;
        teachers = uploads;
        entity=name;
    }


    @Override
    public AutoAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.row_model4, parent, false);
        return new AutoAdapter.RecyclerViewHolder(v);
    }
    @Override
    public void onBindViewHolder(final AutoAdapter.RecyclerViewHolder holder, int position) {
        final AutoStatDetails currentTeacher = teachers.get(position);
        holder.nameTextView.setText(currentTeacher.getBusname());
        holder.placee.setText(currentTeacher.getBservice());
        holder.time.setText(currentTeacher.getBtime());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.nameTextView.getContext());
                builder.setTitle("Delete");
                builder.setMessage("Sure to proceed?");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Auto_Stationss").child(entity)
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
        public ImageButton delete;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            nameTextView =itemView.findViewById ( R.id.titleann);
            placee=itemView.findViewById(R.id.descann);
            time=itemView.findViewById(R.id.phoneann);
            delete=itemView.findViewById(R.id.delete);


        }



    }
}
