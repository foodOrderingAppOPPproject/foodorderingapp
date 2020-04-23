package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomepageActivity extends AppCompatActivity {


    Button ToCostumer;
    Button ToRider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        ToCostumer = findViewById(R.id.btn_client);
        ToRider =findViewById(R.id.btn_rider);


        ToCostumer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomepageActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        ToRider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(HomepageActivity.this, DeliveryLoginActivity.class);
                startActivity(i);
            }
        });
    }
}
