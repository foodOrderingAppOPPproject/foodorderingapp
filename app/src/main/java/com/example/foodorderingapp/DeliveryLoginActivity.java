package com.example.foodorderingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DeliveryLoginActivity extends AppCompatActivity {

    EditText emailIdd, passwordd;
    Button btnSignUpd;
    TextView textSignInd;
    FirebaseAuth mFirebaseAuth;
    EditText Mobile, Getname;
    DatabaseReference databasePerson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailIdd = findViewById(R.id.GetEmailt);
        passwordd = findViewById(R.id.GetPassword);
        btnSignUpd = findViewById(R.id.DeliverySignUpButton);
        textSignInd = findViewById(R.id.tvDeliveryToLogin);
        Mobile = findViewById(R.id.GetNumber2);
        Getname = findViewById(R.id.GetName2);

        databasePerson = FirebaseDatabase.getInstance().getReference();
        btnSignUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailIdd.getText().toString();
                String pwd = passwordd.getText().toString();

                String name = Getname.getText().toString();
                String Phone = Mobile.getText().toString();
                Person(name , Phone);


                if (email.isEmpty()) {
                    emailIdd.setError("Please enter email Id");
                    emailIdd.requestFocus();
                } else if (pwd.isEmpty()) {
                    passwordd.setError("Please enter email Id");
                    passwordd.requestFocus();

                }
                else if( !(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(DeliveryLoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful() ){
                                Toast.makeText(DeliveryLoginActivity.this, "SignUp Unsuccessful Please Try again", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                startActivity(new Intent(DeliveryLoginActivity.this, RadiusChooseActivity.class ));
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(DeliveryLoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        textSignInd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DeliveryLoginActivity.this, DeliverySignUpActivity.class);
                startActivity(i);
            }
        });

    }

    private void Person(String name, String Phone){


        if(!name.isEmpty() && !Phone.isEmpty()){
            String id = databasePerson.push().getKey();
            PersonData personData = new PersonData(name, Phone, id);
            databasePerson.child(id).setValue(personData);
            Toast.makeText(this, "signed up", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Please enter name and number", Toast.LENGTH_LONG).show();
        }

    }
}
