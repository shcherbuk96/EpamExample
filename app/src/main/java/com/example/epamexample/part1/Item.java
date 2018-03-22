package com.example.epamexample.part1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.epamexample.R;
import com.example.epamexample.presenters.ActionBarPresenter;
import com.example.epamexample.presenters.ItemPresenter;
import com.example.epamexample.views.ActionBarView;
import com.example.epamexample.views.ItemView;
import com.squareup.picasso.Picasso;

import static com.example.epamexample.part1.Constants.KEY_LATI;
import static com.example.epamexample.part1.Constants.KEY_LONGI;
import static com.example.epamexample.part1.Constants.KEY_NAME;
import static com.example.epamexample.part1.Constants.KEY_URL;

public class Item extends MvpAppCompatActivity implements View.OnClickListener, ItemView, ActionBarView {

    @InjectPresenter
    ItemPresenter itemPresenter;

    @InjectPresenter
    ActionBarPresenter actionBarPresenter;

    TextView name;
    ImageView image;
    Intent intent;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);


        intent = getIntent();

        itemPresenter.getViewState().showName(intent.getStringExtra(KEY_NAME));
        itemPresenter.getViewState().showPhoto(intent.getStringExtra(KEY_URL));

        actionBarPresenter.getViewState().showActionBar(intent.getStringExtra(KEY_NAME));

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View view) {
        Intent in = new Intent(this, MapGoogle.class);

        in.putExtra(KEY_LATI, intent.getDoubleExtra(KEY_LATI, 0));
        in.putExtra(KEY_LONGI, intent.getDoubleExtra(KEY_LONGI, 0));
        in.putExtra(KEY_URL, intent.getStringExtra(KEY_URL));
        startActivity(in);
    }

    @Override
    public void showName(String namePhoto) {
        name = findViewById(R.id.item_name);
        name.setText(namePhoto);
    }

    @Override
    public void showPhoto(String urlPhoto) {
        image = findViewById(R.id.item_image);
        Picasso.with(this).load(urlPhoto).into(image);
    }

    @Override
    public void showActionBar(String actionBar) {
        setTitle(actionBar);
    }
}
