package com.example.epamexample.task;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.epamexample.R;
import com.squareup.picasso.Picasso;

import static com.example.epamexample.task.Constants.KEY_LATI;
import static com.example.epamexample.task.Constants.KEY_LONGI;
import static com.example.epamexample.task.Constants.KEY_NAME;
import static com.example.epamexample.task.Constants.KEY_URL;

public class ItemActivity extends MvpAppCompatActivity implements View.OnClickListener {

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

        name = findViewById(R.id.item_name);
        image = findViewById(R.id.item_image);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);


        intent = getIntent();
        name.setText(intent.getStringExtra(KEY_NAME));
        Picasso.with(this).load(intent.getStringExtra(KEY_URL)).into(image);
        setTitle(intent.getStringExtra(KEY_NAME));

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View view) {
        Intent in = new Intent(this, MapGoogleActivity.class);

        in.putExtra(KEY_LATI, intent.getDoubleExtra(KEY_LATI, 0));
        in.putExtra(KEY_LONGI, intent.getDoubleExtra(KEY_LONGI, 0));
        in.putExtra(KEY_URL, intent.getStringExtra(KEY_URL));
        startActivity(in);
    }

}
