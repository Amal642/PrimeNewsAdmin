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

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class WorkerFullAdapter  extends RecyclerView.Adapter<WorkerFullAdapter.RecyclerViewHolder>{

    private Context mContext;
    private List<Taxifull> teachers;
    String entity;
    public WorkerFullAdapter(Context context, List<Taxifull> uploads,String name) {
        mContext = context;
        teachers = uploads;
        entity=name;
    }


    @Override
    public WorkerFullAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.row_model4, parent, false);
        return new WorkerFullAdapter.RecyclerViewHolder(v);
    }
    @Override
    public void onBindViewHolder(final WorkerFullAdapter.RecyclerViewHolder holder, int position) {
        final Taxifull currentTeacher = teachers.get(position);
        holder.nameTextView.setText(currentTeacher.getBusname());
        holder.place.setText(currentTeacher.getBservice());
        holder.phone.setText(currentTeacher.getBtime());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.nameTextView.getContext());
                builder.setTitle("Delete");
                builder.setMessage("Sure to proceed?");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Torilaliss").child(entity)
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
        public TextView nameTextView,place,phone;
        public ImageButton delete;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            nameTextView =itemView.findViewById(R.id.titleann);
            place=itemView.findViewById(R.id.descann);
            phone=itemView.findViewById(R.id.phoneann);
            delete=itemView.findViewById(R.id.delete);

        }



    }
}
