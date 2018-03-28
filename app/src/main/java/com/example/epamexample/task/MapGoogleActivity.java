package com.example.epamexample.task;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.epamexample.App;
import com.example.epamexample.R;
import com.example.epamexample.api.Api;
import com.example.epamexample.pojo.ListApi;
import com.example.epamexample.pojo.Photo;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.reactivestreams.Subscription;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class MapGoogleActivity extends MvpAppCompatActivity implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener {

    @Inject
    Api api;

    GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_google);
        App.getRetrofitComponent().inject(this);

        SupportMapFragment mapFragment = (SupportMapFragment) this.getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                callApi();
            }
        });
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(45,45));
    }

    public void callApi() {
        api.listData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FlowableSubscriber<ListApi>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(ListApi listApi) {
                        loadImage(listApi.getList());
                    }

                    @Override
                    public void onError(Throwable t) {
                        fail();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        fail();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    public void fail() {
        Toast.makeText(this, "Проверьте интернет соединение", Toast.LENGTH_SHORT).show();
    }

    public void loadImage(List<Photo> photos) {
        for (final Photo photo : photos) {
            Picasso.with(getBaseContext()).load(photo.getUrl()).centerCrop().resize(120, 120).into(new Target() {
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
