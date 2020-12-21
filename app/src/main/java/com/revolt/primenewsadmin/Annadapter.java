package com.revolt.primenewsadmin;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class Annadapter extends FirebaseRecyclerAdapter<Announcements,Annadapter.myviewholder> {

    public Annadapter(@NonNull FirebaseRecyclerOptions<Announcements> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder myviewholder, final int position, @NonNull Announcements announcements) {
        myviewholder.title.setText(announcements.getTitle());
        myviewholder.description.setText(announcements.getDescription());
        myviewholder.deleteAnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(myviewholder.title.getContext());
                builder.setTitle("Delete");
                builder.setMessage("Sure to proceed?");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Ann_upload")
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
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_model2,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{
    TextView title,description;
    ImageButton deleteAnn;
    public myviewholder(@NonNull View itemView) {
        super(itemView);
        title=itemView.findViewById(R.id.titleann);
        description=itemView.findViewById(R.id.descann);
        deleteAnn=itemView.findViewById(R.id.delete);

    }
}
}
