package com.example.adolfo.runninglogger;

import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class weekView extends AppCompatActivity {
    User thisUser = MainActivity.mainUser;
    TextView tvInfo;
    final Calendar myCalendar = Calendar.getInstance();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_view);
        tvInfo = (TextView) findViewById(R.id.textView_UserInfo);

        setTitle("Week View");

        // Currently this would print out ALL the workouts in the workout queue
        // Need to add functionaility to only print out the workout of a certain week
        for(int i = 0; i < thisUser.getWorkoutLL().size(); i++)
        {
            Workout temp = (Workout) thisUser.getWorkoutLL().get(i);
            tvInfo.append("Workout Name:  " + temp.getWorkoutName()+ '\n');
            tvInfo.append("Date:  " + temp.getDate() + '\n');
            tvInfo.append("Mileage:  " + temp.getMileage() + " miles" + '\n');
            tvInfo.append("Duration:  " + temp.getDuration() + " minutes" + '\n');
            tvInfo.append("Location:  " + temp.getLocation() + '\n');
            tvInfo.append("Fatigue Level: " + temp.getFatigueLevel() + '\n');
            tvInfo.append("Weather:  " + temp.getWeather() + '\n');
            tvInfo.append("SplitType:  " + temp.getSplitType() + '\n' + '\n');
        }
    }


}
