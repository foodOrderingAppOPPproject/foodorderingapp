package com.example.foodorderingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DeliverySignUpActivity extends AppCompatActivity {
    EditText emailIddd, passworddd;
    Button btnSignIndd;
    TextView textSignUpdd;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_sign_up);


        mFirebaseAuth = FirebaseAuth.getInstance();
        emailIddd = findViewById(R.id.LoginEmial);
        passworddd = findViewById(R.id.LoginPassword);
        btnSignIndd = findViewById(R.id.LoginButton);        // an error might occur here, in the video
        // they copied xml of main activity to login activity
        // and were using same name for the boxes. I did otherwise.
        textSignUpdd = findViewById(R.id.tvToSignUp);


        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if(mFirebaseUser != null){
                    Toast.makeText(DeliverySignUpActivity.this, "You are logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(DeliverySignUpActivity.this, RadiusChooseActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(DeliverySignUpActivity.this, "Please login", Toast.LENGTH_SHORT).show();
                }
            }
        };
        btnSignIndd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailIddd.getText().toString();
                String pwd = passworddd.getText().toString();

                if (email.isEmpty()) {
                    emailIddd.setError("Please enter email Id");
                    emailIddd.requestFocus();
                } else if (pwd.isEmpty()) {
                    passworddd.setError("Please enter email Id");
                    passworddd.requestFocus();

                }
                else if( !(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(DeliverySignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful() ){
                                Toast.makeText(DeliverySignUpActivity.this, "Login  Unsuccessful Please Try again", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                startActivity(new Intent(DeliverySignUpActivity.this, RadiusChooseActivity.class ));
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(DeliverySignUpActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        textSignUpdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignUp = new Intent(DeliverySignUpActivity.this, DeliveryLoginActivity.class);
                startActivity(intSignUp);
            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}
