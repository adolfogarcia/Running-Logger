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
import android.widget.TextView;

import static android.R.id.message;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

/*
    This screen will be the main Opening screen. All it will show is the workout for today
    and the mileage to be completed today. It also shows the remaining mileage for the week
     and total miles completed so far.
     The user can only continue onto the main menu.
 */

public class MainActivity extends AppCompatActivity {

    /*
     *  MainUser is the variable that will hold ALL the info necessary to run the program.
     *  It will contain the username, weekly goal mileage, and all the individual workouts.
     *  If the other activities need to access the info, they will call
     *  newVariable = mainActivity.MainUser;
    */
    public static User mainUser = new User();   // BIG MONEY RIGHT HERE

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvGoalMileage = (TextView) findViewById(R.id.textView_goalMileage);

        // Contains is used to determine if the userName has been set yet. If contains is false, that means
        // this is the first time firing up the app
        boolean containsUsername = false;

        // We store the Username and Weekly goal mileage in a sharedPreference labelled with "USER"
        SharedPreferences sharedPref = getSharedPreferences("USER", Context.MODE_PRIVATE);

        // here we determine if the username exists
        containsUsername = sharedPref.contains("USERNAME");

        // If contains = false, we go to a set-up menu to set up username and weekly goal mileage
        if(!containsUsername)
        {
            goToSetUpUser();
            containsUsername = sharedPref.contains("USERNAME");
        }

        // Get username and mileage from sharedPreference. They should exist at this portion of the code
        mainUser.setUserName(sharedPref.getString("USERNAME", "NONE"));
        mainUser.setMileage(Integer.toString(sharedPref.getInt("MILEAGE GOAL", 0)));

        // Get today's Date
        DateFormat df = new SimpleDateFormat("EEE MMM dd, yyyy");
        // We use this to make sure the proper time zone is used for the date, or else we'd get EST dates
        TimeZone PST = TimeZone.getTimeZone("America/Los_Angeles");
        df.setTimeZone(PST);
        String date = df.format(Calendar.getInstance().getTime());

        setTitle(date + " - Hello, " + mainUser.getUserName()); // Set today's date as the title of the Page
        tvGoalMileage.append(String.valueOf(mainUser.getGoalMileage()));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onResume()
    {
        super.onResume();  // Always call the superclass method first

        DateFormat df = new SimpleDateFormat("EEE MMM d, yyyy");
        TimeZone PST = TimeZone.getTimeZone("America/Los_Angeles");
        df.setTimeZone(PST);

        String date = df.format(Calendar.getInstance().getTime());
        setTitle(date + " - Hello, " + mainUser.getUserName());

    }

    // Used as a onClick function
    public void goToMainMenu(View view)
    {
        Intent intent = new Intent(this, MainMenu.class);
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    // Used as a onClick function
    public void goToSetUpUser()
    {
        Intent intent = new Intent(this, setUpUser.class);
       // intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
