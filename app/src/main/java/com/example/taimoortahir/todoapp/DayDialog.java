package com.example.taimoortahir.todoapp;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.taimoortahir.todoapp.R;

public class DayDialog extends AppCompatActivity implements View.OnClickListener {

    RecyclerView dayRecycler;
    ImageView dayImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_dialog);

        dayRecycler = (RecyclerView) findViewById(R.id.recycler_day);
        dayImage = (ImageView) findViewById(R.id.day_imageview);
        dayImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
