package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.security.PublicKey;

public class RadiusChooseActivity extends AppCompatActivity {
    EditText radius;
    Button submiRad;
    public  static String Dis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radius_choose);

        radius = findViewById(R.id.editRadius);
        submiRad = findViewById(R.id.SubmitRadius);



        submiRad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dis = radius.getText().toString();
                Intent i  = new Intent(RadiusChooseActivity.this, DeliveryActivity.class);
                startActivity(i);
            }
        });


    }

    public static String getDis() {
        return Dis;
    }

    public static void setDis(String dis) {
        Dis = dis;
    }
}
