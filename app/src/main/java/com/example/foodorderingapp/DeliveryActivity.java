package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DeliveryActivity extends AppCompatActivity {

    CardView user1, user2, user3;
    Button dir1, dir2, dir3;
    Button conf1, conf2, conf3;

     String StringDistance;
     int IntDis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        user1 = findViewById(R.id.User1);
        user2 = findViewById(R.id.User2);
        user3 = findViewById(R.id.User3);

        dir1 = findViewById(R.id.direction1);
        dir2 = findViewById(R.id.direction2);
        dir3 = findViewById(R.id.direction3);

        conf1 = findViewById(R.id.cond1);
        conf2 = findViewById(R.id.cond2);
        conf3 = findViewById(R.id.cond3);

        StringDistance = RadiusChooseActivity.getDis().toString();
        if(!StringDistance.isEmpty())
            IntDis = Integer.parseInt(StringDistance);
        else
            IntDis = 0;

        if(IntDis>3)
        {
            user2.setVisibility(View.VISIBLE);
            dir2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create a Uri from an intent string. Use the result to create an Intent.
                    Uri gmmIntentUri = Uri.parse("google.navigation:q=Gwalior+ fort,+Gwalior+India");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }
            });

            conf2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(DeliveryActivity.this, EnterOtpActivity.class);
                    startActivity(i);
                }
            });

        }
        else{
            user2.setVisibility(View.GONE);
        }

        if(IntDis>5)
        {
            user1.setVisibility(View.VISIBLE);
            dir1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create a Uri from an intent string. Use the result to create an Intent.
                    Uri gmmIntentUri = Uri.parse("google.navigation:q=Railway+station,+Gwalior+India");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }
            });
            conf1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(DeliveryActivity.this, EnterOtpActivity.class);
                    startActivity(i);
                }
            });

        }
        else{
            user1.setVisibility(View.GONE);
        }

        if(IntDis>8)
        {
            user3.setVisibility(View.VISIBLE);
            dir3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create a Uri from an intent string. Use the result to create an Intent.
                    Uri gmmIntentUri = Uri.parse("google.navigation:q=Railway+station,+Gwalior+India");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }
            });

            conf3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(DeliveryActivity.this, EnterOtpActivity.class);
                    startActivity(i);
                }
            });

        }
        else{
            user3.setVisibility(View.GONE);
        }



    }
}
