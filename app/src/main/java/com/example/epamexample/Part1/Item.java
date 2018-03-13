package com.example.epamexample.Part1;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.epamexample.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import static com.example.epamexample.Part1.Part1.isOnline;
import static java.security.AccessController.getContext;

public class Item extends AppCompatActivity implements View.OnClickListener{
    TextView name;
    ImageView image;
    Toolbar toolbar;
    Intent intent;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btn=(Button)findViewById(R.id.btn);
        btn.setOnClickListener(this);
/*        if(!isOnline(this)){
            ImageView imageView=(ImageView)findViewById(R.id.image_internet);
            TextView textVie=(TextView)findViewById(R.id.text_internet);
            imageView.setVisibility(View.VISIBLE);
            textVie.setVisibility(View.VISIBLE);
        }
        if(isOnline(this)){
            recyclerView.setVisibility(View.VISIBLE);
        }*/
/*        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });*/
        name=(TextView)findViewById(R.id.item_name);
        image=(ImageView)findViewById(R.id.item_image);


        intent=getIntent();

        setTitle(intent.getStringExtra("name"));

        Log.i("latitude",intent.getStringExtra("latitude"));
        Log.i("longitude",intent.getStringExtra("longitude"));
        name.setText(intent.getStringExtra("name"));
        Picasso.with(this).load(intent.getStringExtra("url")).into(image);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View view) {
        Intent in=new Intent(this,MapGoogle.class);

        in.putExtra("latitude",intent.getStringExtra("latitude"));
        in.putExtra("longitude",intent.getStringExtra("longitude"));
        in.putExtra("url",intent.getStringExtra("url"));
        startActivity(in);
    }
}
