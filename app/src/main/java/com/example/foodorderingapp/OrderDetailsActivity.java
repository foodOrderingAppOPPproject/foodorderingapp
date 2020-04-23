package com.example.foodorderingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
//import android.se.omapi.Session;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class OrderDetailsActivity extends AppCompatActivity {
    TextView OrderedList, ShowPriceView, OrderListText;
    String BundlePrice;
    Button ConfirmOrder;
    String Email;
    String sMail, sPassword;
    String mailTo, mailSub;
    String mailcontent;
    int price;
    int DistancePrice;
    String distanceCost = RestrawActivity.getDistance();  // Get distance from restraw activity
    String FinalPrice;
    private  FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    String IfGold;
    private String userId;
    int optint;
    String OtpString;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        sMail = "testfoodbits123@gmail.com";
        sPassword = "Test@123";
       // mailTo = "karaiyaashutosh@gmail.com";

        mailSub = "test mail";

        FirebaseUser  user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            mailTo = user.getEmail();
        }
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        userId = user.getUid();
        myRef = mFirebaseDatabase.getReference();


        ConfirmOrder = findViewById(R.id.confirm_button);
        Bundle bundle = getIntent().getExtras();
        String data  = bundle.getString("choice_iteams");
        BundlePrice = bundle.getString("bdt_price");
        Email= data;








        price = Integer.parseInt(BundlePrice);
        DistancePrice = Integer.parseInt(distanceCost);



        if(MainActivity.getSubint() ==1){
            IfGold = "Discount : 20%";
            price = (price*4)/5;

        }
        else{
            IfGold = "Discount: 0%";
        }

        FinalPrice = Integer.toString(price + DistancePrice);
        optint = Otp.setOtp();
        OtpString = Integer.toString(optint);



        OrderListText = findViewById(R.id.orderList);
        OrderedList = findViewById(R.id.OrderDetails);
        ShowPriceView = findViewById(R.id.showPrice);






        ShowPriceView.setText(" Total : " + price +"+"+ " Delivery charges:"+ distanceCost+ " =" + FinalPrice+ "Rs ");
        OrderedList.setText(data + "\n"+ IfGold);
        Typeface gatholic = ResourcesCompat.getFont(this, R.font.gatholic);
        ConfirmOrder.setTypeface(gatholic);
        OrderListText.setTypeface(gatholic);


        mailcontent ="Your order is confirmed for " +data + " \n Cost: " + BundlePrice + "\n" + IfGold+ "\n Delivery Charger:" + distanceCost+"\n Total Cost:" +FinalPrice+
        "\n OTP for this order is \n" + OtpString+" Give this to the delivery person to confirm Delivery";

        ConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Properties properties=new Properties();
                properties.put("mail.smtp.auth","true");
                properties.put("mail.smtp.starttls.enable","true");
                properties.put("mail.smtp.host","smtp.gmail.com");
                properties.put("mail.smtp.port","587");

                Session session = Session.getInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                       // return super.getPasswordAuthentication();
                        return new PasswordAuthentication(sMail, sPassword);
                    }
                });

                try {
                    Message message = new MimeMessage(session);

                    message.setFrom(new InternetAddress(sMail));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));

                    message.setSubject(mailSub);
                    message.setText(mailcontent);

                    new SendEmail().execute(message);
                }
                catch (AddressException e){
                    e.printStackTrace();
                }
                catch ( MessagingException e){
                    e.printStackTrace();
                }

            }

        });

    }



    private class SendEmail extends AsyncTask<Message, String, String>{

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(OrderDetailsActivity.this,"Please Wait", "Confirming Order...", true, false);
        }

        @Override
        protected String doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
                return "Success";
            }
            catch (MessagingException e){
                e.printStackTrace();
                return "Error";
            }
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if(s.equals("Success")){
                AlertDialog.Builder builder = new AlertDialog.Builder(OrderDetailsActivity.this);
                builder.setCancelable(false);
                builder.setTitle(Html.fromHtml("<font color='#509324'>Success</font>"));
                builder.setMessage("Order Confirmed");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        mailTo = "";
                        mailSub = "";
                        mailcontent = "";


                    }
                });
                builder.show();
            }
            else{
                Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }




        }
    }
}
