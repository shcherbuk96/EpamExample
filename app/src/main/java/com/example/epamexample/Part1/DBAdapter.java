package com.example.epamexample.Part1;

/**
 * Created by Yauheni_Shcharbuk on 3/12/2018.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.epamexample.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;



/**
 * Created by Администратор on 06.03.2018.
 */

public class DBAdapter extends RecyclerView.Adapter<DBAdapter.MyViewHolder> implements RealmChangeListener {
    Context context;
    private RealmResults<Photo> items;


    public DBAdapter(RealmResults<Photo> items, Context context){
        this.items=items;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Log.i("DBAdapter","Work");
        final Photo modelPart1=items.get(position);
        Log.i("OnBind",modelPart1.getTitle());
        holder.name.setText(modelPart1.getTitle());
        //holder.cardView.setBackground(modelPart1.getUrl());
        Picasso.with(context).load(modelPart1.getUrl()).into(holder.photo);
        //holder.description.setText(modelPart1.getDescription());


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,Item.class);
                intent.putExtra("name",modelPart1.getTitle());
                intent.putExtra("url",modelPart1.getUrl());
                intent.putExtra("latitude",String.valueOf(modelPart1.getLatitude()));
                intent.putExtra("longitude",String.valueOf(modelPart1.getLongitude()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.i("Adapter", String.valueOf(items.size()));
        Log.i("Adapter", String.valueOf(items.get(0)));
        return items.size();
    }


    @Override
    public void onChange(Object o) {
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView photo;
        CardView cardView;
        //RelativeLayout relativeLayout;
//        public CardView cardView;
        public MyViewHolder(View view) {
            super(view);
            photo=(ImageView)view.findViewById(R.id.photo);
            name = (TextView) view.findViewById(R.id.name);
            cardView = (CardView) view.findViewById(R.id.card);
        }
    }

}

