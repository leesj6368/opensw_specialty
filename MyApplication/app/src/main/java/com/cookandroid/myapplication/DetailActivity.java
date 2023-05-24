package com.cookandroid.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    center selectedCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSelectedAnimal();
        setValues();
    }

    private void setValues(){
        TextView tv = findViewById(R.id.center_detail_name);
        ImageView iv = findViewById(R.id.center_detail_image);

        tv.setText(selectedCenter.getName());
        iv.setImageResource(selectedCenter.getImage());
    }

    private void getSelectedAnimal(){
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        selectedCenter = CenterListActivity.centerList.get(Integer.valueOf(id));
    }

}