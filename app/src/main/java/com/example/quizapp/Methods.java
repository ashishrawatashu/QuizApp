package com.example.quizapp;

import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Methods {
    private Context c;

    public Methods(Context c) {
        this.c = c;
    }

    public float getDays(String DoB, String currentDate) {
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        String dateBeforeString = DoB;
        String dateAfterString = currentDate;
        float daysBetween = 0;
        try {
            Date dateBefore = myFormat.parse(dateBeforeString);
            Date dateAfter = myFormat.parse(dateAfterString);
            long difference = dateAfter.getTime() - dateBefore.getTime();
            daysBetween = (difference / (1000 * 60 * 60 * 24));
            /* You can also convert the milliseconds to days using this method
             * float daysBetween =
             *         TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS)
             */
            System.out.println("Number of Days between dates: " + daysBetween);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return daysBetween;
    }

    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String getDate2() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }
    public String getRequestJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

}
