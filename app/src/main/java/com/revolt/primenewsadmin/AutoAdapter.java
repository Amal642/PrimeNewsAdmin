package com.revolt.primenewsadmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class AutoAdapter extends  RecyclerView.Adapter<AutoAdapter.RecyclerViewHolder>{

    private Context mContext;
    private List<AutoStatDetails> teachers;
    public AutoAdapter(Context context, List<AutoStatDetails> uploads) {
        mContext = context;
        teachers = uploads;
    }


    @Override
    public AutoAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.row_model3, parent, false);
        return new AutoAdapter.RecyclerViewHolder(v);
    }
    @Override
    public void onBindViewHolder(AutoAdapter.RecyclerViewHolder holder, int position) {
        AutoStatDetails currentTeacher = teachers.get(position);
        holder.nameTextView.setText(currentTeacher.getBusname());
        holder.placee.setText(currentTeacher.getBservice());
        holder.time.setText(currentTeacher.getBtime());

    }
    @Override
    public int getItemCount() {
        return teachers.size();
    }
    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        public TextView nameTextView,placee,time;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            nameTextView =itemView.findViewById ( R.id.busname);
            placee=itemView.findViewById(R.id.busservice);
            time=itemView.findViewById(R.id.bustime);


        }



    }
}
