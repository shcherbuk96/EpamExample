package com.example.epamexample.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.epamexample.R;
import com.example.epamexample.pojo.Photo;
import com.example.epamexample.task.Constants;
import com.example.epamexample.task.ItemActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private Context context;
    private List<Photo> items;


    public RecyclerAdapter(List<Photo> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Log.i("RecyclerAdapter", "onBindViewHolder");

        final Photo photo = items.get(position);
        holder.name.setText(photo.getTitle());

        Picasso
                .with(context)
                .load(photo.getUrl())
                .error(R.drawable.error)
                .fit()
                .centerCrop()
                .into(holder.photo, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                    }
                });
        holder.relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ItemActivity.class);
                intent.putExtra(Constants.KEY_NAME, photo.getTitle());
                intent.putExtra(Constants.KEY_URL, photo.getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.i("size", String.valueOf(items.size()));
        return items.size();

    }

    public void updateData(List<Photo> viewModels) {
        items.clear();
        items.addAll(viewModels);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView photo;
        RelativeLayout relative;

        MyViewHolder(View view) {
            super(view);
            photo = view.findViewById(R.id.photo);
            name = view.findViewById(R.id.name);
            relative = view.findViewById(R.id.relative);
        }
    }
}
