package com.revolt.primenewsadmin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class GeneralAdapter extends RecyclerView.Adapter<GeneralAdapter.RecyclerViewHolder>{

    private Context mContext;
    private List<Taxi> teachers;
    private GeneralAdapter.OnItemClickListener mListener;

    public GeneralAdapter(Context context, List<Taxi> uploads) {
        mContext = context;
        teachers = uploads;
    }


    @Override
    public GeneralAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.row_modellink, parent, false);
        return new GeneralAdapter.RecyclerViewHolder(v);
    }
    @Override
    public void onBindViewHolder(final GeneralAdapter.RecyclerViewHolder holder, int position) {
        final Taxi currentTeacher = teachers.get(position);
        holder.nameTextView.setText(currentTeacher.getName());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.nameTextView.getContext());
                builder.setTitle("Delete");
                builder.setMessage("Sure to proceed?");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("General")
                                .child(currentTeacher.getKey()).removeValue();
                        FirebaseDatabase.getInstance().getReference().child("Generals")
                                .child(currentTeacher.getKey()).removeValue();

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



    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView nameTextView;
        public ImageButton delete,show;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            nameTextView =itemView.findViewById ( R.id.titleann);
            delete=itemView.findViewById(R.id.delete);
            show=itemView.findViewById(R.id.showlink);
            show.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);

                        }
                    }
                }
            });
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onItemClick(position);

                }
            }

        }


    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(GeneralAdapter.OnItemClickListener listener) {
        mListener = listener;
    }
}
