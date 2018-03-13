package com.example.epamexample.Part1;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.epamexample.R;
import com.google.gson.JsonArray;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Администратор on 06.03.2018.
 */

public class Part1 extends Fragment{
    RecyclerView recyclerView;
    List<ModelPart1> list=new ArrayList<>();
    RecyclerAdapter recyclerAdapter;
    DBAdapter dbAdapter;
    RealmList<Photo> realmList;
    Realm realm;

    List<Photo> photoList=new ArrayList<>();
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Part 3");


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_part1,container,false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview);
        realm=Realm.getDefaultInstance();
/*        if(!isOnline(getContext())){
            view=inflater.inflate(R.layout.activity_internet_connection,container,false);
        }*/

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
//        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),linearLayoutManager.getOrientation()));
//        DividerItemDecoration itemDecor = new DividerItemDecoration(getContext(), VERTICAL);
//        recyclerView.addItemDecoration(itemDecor);

        Work work=new Work();
        work.execute();

/*        AsyncTask asyncTask=new AsyncTask() {
            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                recyclerAdapter=new RecyclerAdapter(photoList,getContext());

                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(),3);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            protected Object doInBackground(Object[] objects) {
                Log.d("JSON","1");
                getResponse(loadJSONFromAsset(getContext()));
                return null;
            }
        }.execute();*/


        //        setData();

        return view;
    }
    public class Work extends AsyncTask<Void,Void,Void>{


        @Override
        protected void onPostExecute(Void aVoid) {
            Log.d("JSON","2");
            super.onPostExecute(aVoid);
            if(isOnline(getContext())){
                recyclerAdapter=new RecyclerAdapter(photoList,getContext());
                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(),3);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(recyclerAdapter);
            }else{
                dbAdapter=new DBAdapter(realm.where(Photo.class).findAll(),getContext());
                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(),3);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(dbAdapter);
            }



        }

        @Override
        protected Void doInBackground(Void... voids) {
            getResponse(loadJSONFromAsset(getContext()));
            //
            // Log.d("JSON",);
            return null;
        }
    }

    public void getResponse(String body){
        try {
            Photo photo;
            ListRealm listRealm;
            RealmList<Photo> realmListPhoto = new RealmList<>();
            Realm realmAssync=Realm.getDefaultInstance();
            JSONObject photos=new JSONObject(body);
            JSONArray jsonArray=photos.getJSONArray("photos");
            listRealm=realmAssync.where(ListRealm.class).findFirst();
            for(int i=0;i<=jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                Log.d("JSON",jsonObject.getString("title"));
                photoList.add(new Photo(jsonObject.getInt("id"),jsonObject.getString("title"),jsonObject.getString("description"),
                        jsonObject.getDouble("latitude"),jsonObject.getDouble("longitude"),jsonObject.getString("url")));

                if(isOnline(getContext())){
                    realmAssync.beginTransaction();
                    photo=realmAssync.createObject(Photo.class);
                    photo.setUrl(jsonObject.getString("url"));
                    photo.setTitle(jsonObject.getString("title"));
                    photo.setDescription(jsonObject.getString("description"));
                    photo.setId(jsonObject.getInt("id"));
                    photo.setLatitude(jsonObject.getDouble("latitude"));
                    photo.setLongitude(jsonObject.getDouble("longitude"));
                    realmListPhoto.add(photo);
                    realmAssync.commitTransaction();
                }
            }
            listRealm.setRealmList(realmListPhoto);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("getResponce","ERROR");
        }
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

    @SerializedName("photos")
    @Expose
    private List<Photo> photos = null;

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }



    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("JSON.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    static public boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

}
