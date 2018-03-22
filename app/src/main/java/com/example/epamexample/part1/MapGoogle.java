package com.example.epamexample.part1;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.epamexample.R;
import com.example.epamexample.presenters.GetBodyPresent;
import com.example.epamexample.views.GetBodyView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;


public class MapGoogle extends MvpAppCompatActivity implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener, GetBodyView {
    GoogleMap mMap;
    @InjectPresenter
    GetBodyPresent getBodyPresent;
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


    @Override
    public void showRetrofit(List<Photo> list) {
        if (mMap != null) {
            loadImage(list);
        } else fail();
    }

    @Override
    public void fail() {
        Toast.makeText(this, "Проверьте интернет соединение", Toast.LENGTH_SHORT).show();
    }
}
