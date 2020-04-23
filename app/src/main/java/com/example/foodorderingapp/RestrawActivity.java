package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RestrawActivity extends AppCompatActivity {
            CardView Alankrita, Santosh, Tandoor;
            public static String distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restraw);
        Alankrita = findViewById(R.id.cvOne);
        Santosh = findViewById(R.id.cvTwo);
        Tandoor = findViewById(R.id.cvThree);



        Tandoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RestrawActivity.this, OrderActivity.class);
                startActivity(i);

                distance= "80";





            }
        });
        Alankrita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RestrawActivity.this, OrderActivity.class);
                startActivity(i);
                distance = "50";

            }
        });

        Santosh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RestrawActivity.this, OrderActivity.class);
                startActivity(i);

                distance = "30";
            }
        });
    }

    public static String getDistance() {
        return distance;
    }


}
