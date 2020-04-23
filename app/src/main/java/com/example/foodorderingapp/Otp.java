package com.example.foodorderingapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Otp {
    public static int setOtp()
    {

        // create a list of Integer type
        List<Integer> list = new ArrayList<>();
        // add 5 element in ArrayList
        list.add(0557);
        list.add(8196);
        list.add(6083);
        list.add(4171);
        list.add(5885);
        list.add(0050);
        list.add(7789);list.add(6172);list.add(2886);list.add(1021);list.add(7471);list.add(4303);list.add(4303);list.add(3616);list.add(5207);



        Otp obj = new  Otp();
        return obj.getRandomElement(list);

    }

    // Function select an element base on index
    // and return an element
    public int getRandomElement(List<Integer> list)
    {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }
    public boolean checkOtp(int otp ){
        List<Integer> list = new ArrayList<>();
        // add 5 element in ArrayList
        list.add(0557);
        list.add(8196);
        list.add(6083);
        list.add(4171);
        list.add(5885);
        list.add(0050);
        list.add(7789);list.add(6172);list.add(2886);list.add(1021);list.add(7471);list.add(4303);list.add(4303);list.add(3616);list.add(5207);
        //Otp obj = new Otp();


        if(list.contains(otp))
            return true;
        else return false;


    }


}
