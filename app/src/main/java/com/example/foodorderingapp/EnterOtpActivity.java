package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterOtpActivity extends AppCompatActivity {
    EditText otpcheck;
    boolean boolotpcheck;
    Button btn_otp_conf;
    String OtpCheckResultString;
    int OtpCheckResultInt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_otp);

        otpcheck = findViewById(R.id.enterOtp);
        btn_otp_conf = findViewById(R.id.btn_confirmOtp);

        btn_otp_conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               OtpCheckResultString =  otpcheck.getText().toString();
               if(!OtpCheckResultString.isEmpty()){
                   OtpCheckResultInt = Integer.parseInt(OtpCheckResultString);
                   Otp obj = new Otp();
                   boolotpcheck = obj.checkOtp(OtpCheckResultInt);
                   if(boolotpcheck){
                       Toast.makeText(EnterOtpActivity.this, "Delivery Successfull", Toast.LENGTH_LONG).show();
                   }
                   else{
                       Toast.makeText(EnterOtpActivity.this, "Enter Correct OTP", Toast.LENGTH_LONG).show();
                   }
               }
               else{
                   Toast.makeText(EnterOtpActivity.this, "Enter OTP", Toast.LENGTH_LONG).show();
               }


            }
        });





    }
}
