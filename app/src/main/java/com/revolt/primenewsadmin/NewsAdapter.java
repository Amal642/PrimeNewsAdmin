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

import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.RecyclerViewHolder> {
    private Context mContext;
    private List<News> teachers;
    private OnItemClickListener mListener;

    public NewsAdapter(Context context, List<News> uploads) {
        mContext = context;
        teachers = uploads;
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.row_model, parent, false);
        return new RecyclerViewHolder(v);
    }
    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        final News currentTeacher = teachers.get(position);
        holder.nameTextView.setText(currentTeacher.getName());
        //holder.descriptionTextView.setText(currentTeacher.getDescription());
        holder.dateTextView.setText(currentTeacher.getDate());
        Picasso.get()
                .load(currentTeacher.getImageUrl())
                .fit()
                .centerCrop()
                .into(holder.teacherImageView);
    }
    @Override
    public int getItemCount() {
        return teachers.size();
    }
    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        public TextView nameTextView,descriptionTextView,dateTextView;
        public ImageView teacherImageView;
        public ImageButton imageButton,viewnews;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            nameTextView =itemView.findViewById ( R.id.nameTextView );
            //descriptionTextView=itemView.findViewById(R.id.descriptionDetailTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            teacherImageView = itemView.findViewById(R.id.teacherImageView);
            imageButton=itemView.findViewById(R.id.newsshare1);
            viewnews=itemView.findViewById(R.id.shownews);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final String pTitle = teachers.get(getAdapterPosition()).getName();
                    final String pdescription = teachers.get(getAdapterPosition()).getDescription();
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    String shareBody = pTitle + "\n" + pdescription + "\n" /*+ "To download the app from playstore"+"\n"+"http://play.google.com/store/apps/details?id=" + view.getContext().getPackageName()*/;
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Prime News");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    mContext.startActivity(Intent.createChooser(sharingIntent, "Share news via"));
                    Toast.makeText(view.getContext(), "Sharing", Toast.LENGTH_SHORT).show();
                }
            });
            viewnews.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                    mListener.onShowItemClick(position);}
                    Toast.makeText(view.getContext(), "Showing news", Toast.LENGTH_SHORT).show();
                }
            });
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
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
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select Action");
            MenuItem showItem = menu.add( Menu.NONE, 1, 1, "Show");
            MenuItem deleteItem = menu.add(Menu.NONE, 2, 2, "Delete");
            showItem.setOnMenuItemClickListener(this);
            deleteItem.setOnMenuItemClickListener(this);
        }
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    switch (item.getItemId()) {
                        case 1:
                            mListener.onShowItemClick(position);
                            return true;
                        case 2:
                            mListener.onDeleteItemClick(position);
                            return true;
                    }
                }
            }
            return false;
        }

    }


    public interface OnItemClickListener {
        void onItemClick(int position);
        void onShowItemClick(int position);
        void onDeleteItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

}
