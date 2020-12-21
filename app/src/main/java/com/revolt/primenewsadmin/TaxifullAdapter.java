package com.revolt.primenewsadmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaxifullAdapter extends RecyclerView.Adapter<TaxifullAdapter.RecyclerViewHolder>{

    private Context mContext;
    private List<Taxifull> teachers;
    public TaxifullAdapter(Context context, List<Taxifull> uploads) {
        mContext = context;
        teachers = uploads;
    }


    @Override
    public TaxifullAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.row_model3, parent, false);
        return new TaxifullAdapter.RecyclerViewHolder(v);
    }
    @Override
    public void onBindViewHolder(TaxifullAdapter.RecyclerViewHolder holder, int position) {
        Taxifull currentTeacher = teachers.get(position);
        holder.nameTextView.setText(currentTeacher.getBusname());

    }
    @Override
    public int getItemCount() {
        return teachers.size();
    }
    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        public TextView nameTextView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            nameTextView =itemView.findViewById ( R.id.busname);

        }



    }
}
