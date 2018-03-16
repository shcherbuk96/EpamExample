package com.example.epamexample.Part1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.epamexample.R;
import com.squareup.picasso.Picasso;

public class Item extends AppCompatActivity implements View.OnClickListener, Constants {
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

        name = findViewById(R.id.item_name);
        image = findViewById(R.id.item_image);


        intent = getIntent();

        setTitle(intent.getStringExtra(KEY_NAME));

        name.setText(intent.getStringExtra(KEY_NAME));
        Picasso.with(this).load(intent.getStringExtra(KEY_URL)).into(image);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View view) {
        Intent in = new Intent(this, MapGoogle.class);

        in.putExtra("latitude", intent.getDoubleExtra("latitude", 0));
        in.putExtra("longitude", intent.getDoubleExtra("longitude", 0));
        in.putExtra("url", intent.getStringExtra("url"));
        startActivity(in);
    }
}
