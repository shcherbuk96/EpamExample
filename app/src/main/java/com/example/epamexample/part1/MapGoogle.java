package com.example.epamexample.part1;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.epamexample.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MapGoogle extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener {
    GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_google);

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com/bins/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Api api = retrofit.create(Api.class);

        Call<ListApi> listApiCall = api.listData();
        listApiCall.enqueue(new Callback<ListApi>() {
            @Override
            public void onResponse(Call<ListApi> call, Response<ListApi> response) {
                if (response.isSuccessful()) {
                    loadImage(response.body().getList());
                    Log.i("if", "response " + response.body().getList().size());

                } else {
                    Toast.makeText(getApplicationContext(), "Ошибка загрузки данных", Toast.LENGTH_SHORT).show();
                    Log.i("else", "response code " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ListApi> call, Throwable t) {
                Log.i("onFailure", t.toString());
                Toast.makeText(getApplicationContext(), "Ошибка загрузки данных", Toast.LENGTH_SHORT).show();
            }
        });


        SupportMapFragment mapFragment = (SupportMapFragment) this.getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);
    }


    public void loadImage(List<Photo> photos) {
        Log.i("photoList", String.valueOf(photos.size()));
        if (photos != null) {
            for (final Photo photo : photos) {

                Picasso.with(getBaseContext()).load(photo.getUrl()).resize(120, 120).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                        mMap.addMarker(new MarkerOptions().position(new LatLng(photo.getLatitude(), photo.getLongitude())).title(photo.getTitle())
                                .icon(BitmapDescriptorFactory.fromBitmap(bitmap)));
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });

            }
        }


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        //mMap.moveCamera(CameraUpdateFactory.newLatLng(45,45));

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}
