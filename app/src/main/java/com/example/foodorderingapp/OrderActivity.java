package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {


    TextView menu, htv, btv, ftv, rtv, fitv, dtv;
    TextView hptv, bptv, fptv, rptv, fiptv, dptv;
    String choices = "";
    String prices, usd_price_string;
    double usd_price = 0;
    Button biriyani, haleem, fried, plain, firni, drinks;

    //removing button

    Button rm_biriyani, rm_haleem, rm_fried, rm_firni, rm_plain, rm_drinks, order;

    int bp = 0, hp = 0, pp = 0, drp = 0, frp = 0, fip = 0;
    int tbp = 0, thp = 0, tpp = 0, tdrp = 0, tfrp = 0, tfip = 0;
    int total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        menu = findViewById(R.id.menuTv);
        htv = findViewById(R.id.haleem_textView);
        btv = findViewById(R.id.biriyani_textView);
        ftv = findViewById(R.id.fried_textView);
        rtv = findViewById(R.id.plain_textView);
        fitv = findViewById(R.id.firni_textView);
        dtv = findViewById(R.id.drinks_textView);




        //button of iteams add

        biriyani = findViewById(R.id.biriyani_button);
        haleem = findViewById(R.id.haleem_button);
        fried = findViewById(R.id.fried_button);
        plain =  findViewById(R.id.plain_button);
        firni =  findViewById(R.id.firni_button);
        drinks =  findViewById(R.id.drinks_button);

        // removing button identifing

        rm_biriyani =  findViewById(R.id.biriyani_button_rm);
        rm_haleem =  findViewById(R.id.haleem_button_rm);
        rm_fried = findViewById(R.id.fried_button_rm);
        rm_firni =  findViewById(R.id.firni_button_rm);
        rm_plain =  findViewById(R.id.plain_button_rm);
        rm_drinks =  findViewById(R.id.drinks_button_rm);


        order = findViewById(R.id.order_button);


        hptv = findViewById(R.id.haleem_price);
        bptv = findViewById(R.id.biriyani_price);
        fptv = findViewById(R.id.fried_price);
        rptv = findViewById(R.id.plain_price);
        fiptv = findViewById(R.id.firni_price);
        dptv = findViewById(R.id.drinks_price);


        Typeface gatholic = ResourcesCompat.getFont(this, R.font.gatholic);



        menu.setTypeface(gatholic);

        htv.setTypeface(gatholic);
        btv.setTypeface(gatholic);
        ftv.setTypeface(gatholic);
        rtv.setTypeface(gatholic);
        fitv.setTypeface(gatholic);
        dtv.setTypeface(gatholic);


        hptv.setTypeface(gatholic);
        bptv.setTypeface(gatholic);
        fptv.setTypeface(gatholic);
        rptv.setTypeface(gatholic);
        fiptv.setTypeface(gatholic);
        dptv.setTypeface(gatholic);

        // setting order button font

        order.setTypeface(gatholic);
    }

    public void place_order(View view){

        balancesheet();
        Intent i = new Intent(OrderActivity.this, OrderDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("choice_iteams", choices);
        bundle.putString("bdt_price", prices);
        bundle.putString("usd_price", usd_price_string);
        i.putExtras(bundle);
        startActivity(i);
        choices = "";
    }

    public void add_to_list(View view) {
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //SharedPreferences.Editor editor = prefs.edit();


        if (view == findViewById(R.id.haleem_button)) {
            Toast.makeText(this, "Salad Added", Toast.LENGTH_SHORT).show();
            hp = hp + 1;

            //choices = (choices+"\nHaleem\t (1) Bowl.").toString();

            haleem.setText(Integer.toString(hp));

        } else if (view == findViewById(R.id.biriyani_button)) {
            Toast.makeText(this, "Pizza Added", Toast.LENGTH_SHORT).show();
            // = (choices+"\nBiriyani\t (1) plate.").toString();
            bp = bp + 1;

            biriyani.setText(Integer.toString(bp));

        } else if (view == findViewById(R.id.fried_button)) {
            Toast.makeText(this, "Curry Added", Toast.LENGTH_SHORT).show();
            //choices = (choices+"\nTikkas\t (1) pieces.").toString();
            frp = frp + 1;
            fried.setText(Integer.toString(frp));

        } else if (view == findViewById(R.id.plain_button)) {
            Toast.makeText(this, "Rice Added", Toast.LENGTH_SHORT).show();
            //choices = (choices+"\nBiriyani\t (1) plate.").toString();
            pp = pp + 1;

            plain.setText(Integer.toString(pp));
        } else if (view == findViewById(R.id.firni_button)) {
            Toast.makeText(this, "Firni Added", Toast.LENGTH_SHORT).show();
            //choices = (choices+"\nTikkas\t (1) pieces.").toString();
            fip = fip + 1;

            firni.setText(Integer.toString(fip));
        } else if (view == findViewById(R.id.drinks_button)) {
            Toast.makeText(this, "Juice Added", Toast.LENGTH_SHORT).show();
            // choices = (choices+"\nTikkas\t (1) pieces.").toString();

            drp = drp + 1;

            drinks.setText(Integer.toString(drp));
        }

    }

    public void rmv_from_list(View view) {
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //SharedPreferences.Editor editor = prefs.edit();


        if (view == findViewById(R.id.haleem_button_rm)) {
            if (hp > 0) {

                hp = hp - 1;
                haleem.setText(Integer.toString(hp));
                Toast.makeText(this, "Salad Removed", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, "Add Items First", Toast.LENGTH_SHORT).show();

            //choices = (choices+"\nHaleem\t (1) Bowl.").toString();

        } else if (view == findViewById(R.id.biriyani_button_rm)) {

            // = (choices+"\nBiriyani\t (1) plate.").toString();
            if (bp > 0) {
                bp = bp - 1;

                biriyani.setText(Integer.toString(bp));
                Toast.makeText(this, "Pizza Removed", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, "Add Iteams First", Toast.LENGTH_SHORT).show();

        } else if (view == findViewById(R.id.fried_button_rm)) {

            //choices = (choices+"\nTikkas\t (1) pieces.").toString();

            if (frp > 0) {

                frp = frp - 1;
                fried.setText(Integer.toString(frp));
                Toast.makeText(this, "Curry Removed", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, "Add Iteams First", Toast.LENGTH_SHORT).show();

        } else if (view == findViewById(R.id.plain_button_rm)) {

            //choices = (choices+"\nBiriyani\t (1) plate.").toString();
            if (pp > 0) {

                pp = pp - 1;
                plain.setText(Integer.toString(pp));
                Toast.makeText(this, "Rice Removed", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, "Add Items First", Toast.LENGTH_SHORT).show();


        } else if (view == findViewById(R.id.firni_button_rm)) {

            //choices = (choices+"\nTikkas\t (1) pieces.").toString();
            if (fip > 0) {

                fip = fip - 1;
                firni.setText(Integer.toString(fip));
                Toast.makeText(this, "Firni Removed", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, "Add Items First", Toast.LENGTH_SHORT).show();


        } else if (view == findViewById(R.id.drinks_button_rm)) {

            // choices = (choices+"\nTikkas\t (1) pieces.").toString();
            if (drp > 0) {
                drp = drp - 1;
                drinks.setText(Integer.toString(drp));
                Toast.makeText(this, "Juice Removed", Toast.LENGTH_SHORT).show();
            }

            Toast.makeText(this, "Add Items First", Toast.LENGTH_SHORT).show();

        }

    }




    private void balancesheet() {
        tbp = bp * 250;
        tdrp = drp * 35;
        tfip = fip * 50;
        tfrp = frp * 220;
        thp = hp * 80;
        tpp = pp * 150;
        total = tbp + tdrp + tfip + thp + tpp + tfrp;

        prices = Integer.toString(total);


        if (bp > 0) {
            choices = choices + "Pizza       (" + bp + " x 250) = " + tbp;
        }

        if (drp > 0) {
            choices = choices + "\n\nJuice  (" + drp + " x 35) = " + tdrp;
        }

        if (fip > 0) {
            choices = choices + "\n\nFirni      (" + fip + " x 50) = " + tfip;
        }

        if (frp > 0) {
            choices = choices + "\n\nCurry (" + frp + " x 220) = " + tfrp;
        }
        if (hp > 0) {
            choices = choices + "\n\nSalad    (" + hp + " x 80) = " + thp;
        }

        if (pp > 0) {
            choices = choices + "\n\nRice       (" + pp + " x 150) = " + tpp;
        }


    }

}
