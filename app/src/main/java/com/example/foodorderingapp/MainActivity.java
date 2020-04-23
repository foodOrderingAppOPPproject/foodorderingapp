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

public class MainActivity extends AppCompatActivity {
    EditText emailId, password;
    Button btnSignUp;
    TextView textSignIn;
    FirebaseAuth mFirebaseAuth;
    EditText Mobile, Getname;
    CheckBox Subscription;
    public static boolean sub = false;
    public  static int subint  =0;
   DatabaseReference databasePerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        btnSignUp = findViewById(R.id.button);
        textSignIn = findViewById(R.id.textView);
        Mobile = findViewById(R.id.GetNumber);
        Getname = findViewById(R.id.GetName);
        Subscription = findViewById(R.id.subscription);

        databasePerson = FirebaseDatabase.getInstance().getReference();

        Subscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Subscription.isChecked())
                    sub = true;
                subint =1;
            }
        });


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();

                String name = Getname.getText().toString();
                String Phone = Mobile.getText().toString();
                Person(name , Phone, sub);


                if (email.isEmpty()) {
                    emailId.setError("Please enter email Id");
                    emailId.requestFocus();
                } else if (pwd.isEmpty()) {
                    password.setError("Please enter email Id");
                    password.requestFocus();

                }
                else if( !(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful() ){
                                Toast.makeText(MainActivity.this, "SignUp Unsuccessful Please Try again", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                startActivity(new Intent(MainActivity.this, HomeActivity.class ));
                            }
                        }
                     });
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        textSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
    private void Person(String name, String Phone, boolean sub){


        if(!name.isEmpty() && !Phone.isEmpty()){
             String id = databasePerson.push().getKey();
             PersonData personData = new PersonData(name, Phone, id, sub);
             databasePerson.child(id).setValue(personData);
             Toast.makeText(this, "signed up", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Please enter name and number", Toast.LENGTH_LONG).show();
        }

    }

    public static int getSubint() {
        return subint;
    }
}
