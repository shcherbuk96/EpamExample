package com.example.epamexample.Part1;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.epamexample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Администратор on 06.03.2018.
 */

public class Part1 extends Fragment{
    RecyclerView recyclerView;
    List<ModelPart1> list=new ArrayList<>();
    RecyclerAdapter recyclerAdapter;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Part 1");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_part1,container,false);

        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),linearLayoutManager.getOrientation()));
//        DividerItemDecoration itemDecor = new DividerItemDecoration(getContext(), VERTICAL);
//        recyclerView.addItemDecoration(itemDecor);
        recyclerAdapter=new RecyclerAdapter(list,getContext());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(recyclerAdapter);

        setData();

        return view;
    }

    private void setData(){
        ModelPart1 modelPart1=new ModelPart1(1,"Item 1", "This is item 1");
        list.add(modelPart1);

        modelPart1=new ModelPart1(2,"Item 2", "This is item 2");
        list.add(modelPart1);

        modelPart1=new ModelPart1(3,"Item 3", "This is item 3");
        list.add(modelPart1);

        modelPart1=new ModelPart1(4,"Item 4", "This is item 4");
        list.add(modelPart1);

        modelPart1=new ModelPart1(5,"Item 5", "This is item 5");
        list.add(modelPart1);

        modelPart1=new ModelPart1(6,"Item 6", "This is item 6");
        list.add(modelPart1);

        modelPart1=new ModelPart1(7,"Item 7", "This is item 7");
        list.add(modelPart1);

        modelPart1=new ModelPart1(8,"Item 8", "This is item 8");
        list.add(modelPart1);

        modelPart1=new ModelPart1(9,"Item 9", "This is item 9");
        list.add(modelPart1);

        modelPart1=new ModelPart1(10,"Item 10", "This is item 10");
        list.add(modelPart1);

        recyclerAdapter.notifyDataSetChanged();
    }

}
