package com.revolt.primenewsadmin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BAdapter extends RecyclerView.Adapter<BAdapter.RecyclerViewHolder>{

    private Context mContext;
    private List<BusStatDetails> teachers;
    String [] entity;
    public BAdapter(Context context, List<BusStatDetails> uploads) {
        mContext = context;
        teachers = uploads;
    }

    public BAdapter(ViewbusStationsActivity viewbusStationsActivity, String[] entity1) {
        entity=entity1;


    }


    @Override
    public BAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.row_model3, parent, false);
        return new BAdapter.RecyclerViewHolder(v);
    }
    @Override
    public void onBindViewHolder(final BAdapter.RecyclerViewHolder holder, int position) {
        final BusStatDetails currentTeacher = teachers.get(position);
        holder.nameTextView.setText(currentTeacher.getBusname());
        holder.service.setText(currentTeacher.getBservice());
        holder.busclass.setText(currentTeacher.getBclass());
        holder.route.setText(currentTeacher.getBroute());
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
                        FirebaseDatabase.getInstance().getReference().child("Bus_Stationss").child(String.valueOf(entity))
                                .child(currentTeacher.getId()).removeValue();

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
        public TextView nameTextView,service,time,route,busclass;
        public ImageButton delete;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            nameTextView =itemView.findViewById ( R.id.busname);
            service=itemView.findViewById(R.id.busservice);
            time=itemView.findViewById(R.id.bustime);
            route=itemView.findViewById(R.id.busroute);
            busclass=itemView.findViewById(R.id.busclass);
            delete=itemView.findViewById(R.id.delete);

        }



    }

}
