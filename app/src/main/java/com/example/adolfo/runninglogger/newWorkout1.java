package com.example.adolfo.runninglogger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.R.id.message;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

// This screen will simply bring up all the possible run options and
// prompt the user to pick one. Then it will bring up the next screen, newWorkout2
//public class newWorkout1 extends AppCompatActivity {
//    public static final String EXTRA_MESSAGE = "Run";
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_new_workout1);
//
//        setTitle("Choose a Workout: ");
//    }
//
//    public void goToNewWorkout2(View view)
//    {
//        Intent intent = new Intent(this, newWorkout2.class);
//
//        // Here we get the text on the button clicked so we can go to the corresponding newWorkout page
//        // The text of the button is inserted into the intent class
//        int buttonID = view.getId();
//        Button workout = (Button) findViewById(buttonID);
//        String message = workout.getText().toString();
//
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);
//    }
//}
