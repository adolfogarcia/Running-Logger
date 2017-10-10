package com.example.adolfo.runninglogger;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static android.R.id.message;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

/*
    This screen will be the main Opening screen. All it will show is the workout for today
    and the mileage to be completed today. It also shows the remaining mileage for the week
     and total miles completed so far.
     The user can only continue onto the main menu.
 */

public class MainActivity extends AppCompatActivity {

    public static User mainUser = new User();   // BIG MONEY RIGHT HERE

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean contains = false;
        SharedPreferences sharedPref = getSharedPreferences("USER", Context.MODE_PRIVATE);

        contains = sharedPref.contains("USERNAME");

        if(!contains)
        {
            goToSetUpUser();
            contains = sharedPref.contains("USERNAME");
        }



        // Get username and mileage from sharedPreference
        mainUser.setUserName(sharedPref.getString("USERNAME", "NONE"));
        mainUser.setMileage(Integer.toString(sharedPref.getInt("MILEAGE GOAL", 0)));


        DateFormat df = new SimpleDateFormat("EEE MMM dd, yyyy");  // Get today's Date
        TimeZone PST = TimeZone.getTimeZone("America/Los_Angeles");
        df.setTimeZone(PST);
        String date = df.format(Calendar.getInstance().getTime());

        setTitle(date + " - Hello, " + mainUser.getUserName()); // Set today's date as the title of the Page

    }

    public void goToMainMenu(View view)
    {
        Intent intent = new Intent(this, MainMenu.class);

        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void goToSetUpUser()
    {
        Intent intent = new Intent(this, setUpUser.class);

        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
