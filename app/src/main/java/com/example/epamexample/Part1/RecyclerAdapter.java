package com.example.epamexample.Part1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.epamexample.R;

import java.util.List;

/**
 * Created by Администратор on 06.03.2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    Context context;
    private List<ModelPart1> items;
    public RecyclerAdapter(List<ModelPart1> items,Context context){
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
        final ModelPart1 modelPart1=items.get(position);
        holder.name.setText(modelPart1.getName());
        holder.description.setText(modelPart1.getDescription());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,Item.class);
                intent.putExtra("name",modelPart1.getName());
                intent.putExtra("description",modelPart1.getDescription());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, description;
        public RelativeLayout relativeLayout;
        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            description = (TextView) view.findViewById(R.id.description);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.relative);
        }
    }

}
