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

public class BusStatAdapter extends RecyclerView.Adapter<BusStatAdapter.RecyclerViewHolder> {

    private Context mContext;
    private List<Busstations> teachers;
    private BusStatAdapter.OnItemClickListener mListener;


    public BusStatAdapter(Context context, List<Busstations> uploads) {
        mContext = context;
        teachers = uploads;
    }


    @Override
    public BusStatAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.row_modellink, parent, false);
        return new BusStatAdapter.RecyclerViewHolder(v);
    }
    @Override
    public void onBindViewHolder(final BusStatAdapter.RecyclerViewHolder holder, int position) {
        final Busstations currentTeacher = teachers.get(position);
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
                        FirebaseDatabase.getInstance().getReference().child("Bus_Stations")
                                .child(currentTeacher.getKey()).removeValue();
                        FirebaseDatabase.getInstance().getReference().child("Bus_Stationss")
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
        public ImageButton view,delete;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            nameTextView =itemView.findViewById ( R.id.titleann);
            view=itemView.findViewById(R.id.showlink);
            delete=itemView.findViewById(R.id.delete);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);

                        }
                    }
                    Toast.makeText(view.getContext(), "Showing", Toast.LENGTH_SHORT).show();
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
    public void setOnItemClickListener(BusStatAdapter.OnItemClickListener listener) {
        mListener = listener;
    }
}
