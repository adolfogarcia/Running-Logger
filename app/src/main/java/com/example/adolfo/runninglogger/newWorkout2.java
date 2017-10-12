package com.example.adolfo.runninglogger;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;

import static android.R.id.message;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

// BIG ADDITION: WorkoutName button should try to guess your workout based on what day it is.
// And change the colors depending on the workout type. Shoutout to Ryan Manny for that idea

public class newWorkout2 extends AppCompatActivity {

    // These variables are all declared here because they will be needed all over the program
    public TextView tvDateChosen;
    public final Calendar myCalendar = Calendar.getInstance();
    public SimpleDateFormat sdf = null;
    public String workoutName = " ";
    public LinearLayout container;
    public int totalEditTexts = 0;
    public String FL;

    // A pretty complicated onCreate() function
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_workout2);
        myCalendar.setTimeZone(TimeZone.getTimeZone("PST"));

        setTitle("New Workout");

        Button b = (Button) findViewById(R.id.button_datePicker);
        tvDateChosen = (TextView) findViewById(R.id.textView_dateChosen);
        container = (LinearLayout) findViewById(R.id.linearLayout);


        // This code creates the DatePicker
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.setTimeZone(TimeZone.getTimeZone("PST"));
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        // This creates a click listener for when to create the datepicker
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                myCalendar.setTimeZone(TimeZone.getTimeZone("PST"));
                new DatePickerDialog(newWorkout2.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        final Button bWorkoutName = (Button) findViewById(R.id.button_workoutName);

        // This code creates a popUp menu for deciding the type of running workout
        bWorkoutName.setOnClickListener(new View.OnClickListener() {
            PopupMenu popup;
            @Override
            public void onClick(View v) {
                //HIDES THE ANDROID KEYBOARD AFTER PRESSING A SPECIFIC BUTTON. GODBLESS THIS CODE.
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                //Creating the instance of PopupMenu
                 popup = new PopupMenu(newWorkout2.this, bWorkoutName);
                //Inflating the Popup using xml file called "workout_names.xml"
                popup.getMenuInflater().inflate(R.menu.workout_names, popup.getMenu());
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        // we show a message showing what the user clicked
                        //Toast.makeText(newWorkout2.this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        bWorkoutName.setText(item.getTitle());
                        // We save the workout name for use later on
                        workoutName = item.getTitle().toString();
                        return true;
                    }
                });
                popup.show();//showing popup menu
            }
         });//closing the setOnClickListener method

        // This is the code for the Fatigue Level button. Uses similar code for the workout type menu
        final Button bFL = (Button) findViewById(R.id.button_fatigueLevel);
        bFL.setOnClickListener(new View.OnClickListener() {
            PopupMenu popup;
            @Override
            public void onClick(View v) {
                //HIDES THE ANDROID KEYBOARD AFTER PRESSING A SPECIFIC BUTTON. GODBLESS THIS CODE.
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                //Creating the instance of PopupMenu
                popup = new PopupMenu(newWorkout2.this, bFL);
                //Inflating the Popup using xml file called "fatigue_level.xml"
                popup.getMenuInflater().inflate(R.menu.fatigue_level, popup.getMenu());
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                       // Toast.makeText(newWorkout2.this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        bFL.setText("Fatigue Level: " + item.getTitle());
                        // we store the number for use later on
                        FL = item.getTitle().toString();
                        return true;
                    }
                });
                popup.show();//showing popup menu
            }
        });//closing the setOnClickListener method

    }

    // This is the start for the code for adding editTexts for the splits for workouts like VO2s
    public void addEditText(View view)
    {
        totalEditTexts++;
        EditText newEditText = new EditText(this);
        container.addView(newEditText);
        newEditText.setGravity(Gravity.LEFT);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) newEditText.getLayoutParams();
        layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
        newEditText.setLayoutParams(layoutParams);
        //if you want to identify the created editTexts, set a tag, like below
        newEditText.setTag("EditText" + totalEditTexts);
        newEditText.setHint("Split " + totalEditTexts);
    }

    //This puts the date chosen up on the screen. More specfically, on the textview tvDateChosen
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateLabel() {
        String myFormat = "EEE. MMM dd, yyyy"; //In which you need put here
        myCalendar.setTimeZone(TimeZone.getTimeZone("PST"));
        sdf = new SimpleDateFormat(myFormat, Locale.US);


        tvDateChosen.setText(sdf.format(myCalendar.getTime()));
        //HIDES THE ANDROID KEYBOARD AFTER PRESSING A SPECIFIC BUTTON. GODBLESS THIS CODE.
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    /*
     * This code stores ALL the info put in by the user
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getAllInfo(View view)
    {
        User thisUser = MainActivity.mainUser;
        Workout newWorkout = new Workout();
        Vector<String> newSplits = null;
        EditText etMileage = (EditText) findViewById(R.id.editText_mileage);
        EditText etDuration = (EditText) findViewById(R.id.editText_Time);
        EditText etLocation = (EditText) findViewById(R.id.editText_location);
        //Button btFL = (Button) findViewById(R.id.button_fatigueLevel);
        EditText etWeather = (EditText) findViewById(R.id.editTex_weather);
        EditText etSplitType = (EditText) findViewById(R.id.editText_splitType);

        newWorkout.setDate(tvDateChosen.getText().toString());

        newWorkout.setWorkoutName(workoutName);

        newWorkout.setMileage((etMileage.getText().toString()));

        newWorkout.setDuration((etDuration.getText().toString()));

        newWorkout.setLocation(etLocation.getText().toString());

        newWorkout.setFatigueLevel(FL);

        newWorkout.setWeather(etWeather.getText().toString());

        newWorkout.setSplitType(etSplitType.getText().toString());

        // Once the workout is full of all the information, we enqueue it to the Workout queue in MainUser
        thisUser.enqueueNewWorkout(newWorkout);

        Intent intent = new Intent(this, MainMenu.class);

        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }


   // public SimpleDateFormat getDate

//    public String intMonthToStringConverter(int Month)
//    {
//        String newMonth = null;
//        switch (Month)
//        {
//            case 1:
//                newMonth = "Jan";
//                break;
//            case 2:
//                newMonth = "Feb";
//                break  ;
//            case 3:
//                newMonth = "Mar";
//                break  ;
//            case 4:
//                newMonth = "Apr";
//                break  ;
//            case 5:
//                newMonth = "May";
//                break  ;
//            case 6:
//                newMonth = "Jun";
//                break  ;
//            case 7:
//                newMonth = "Jul";
//                break  ;
//            case 8:
//                newMonth = "Aug";
//                break  ;
//            case 9:
//                newMonth = "Sep";
//                break  ;
//            case 10:
//                newMonth = "Oct";
//                break  ;
//            case 11:
//                newMonth = "Nov";
//                break  ;
//            case 12:
//                newMonth = "Dec";
//                break  ;
//        }
//        return newMonth;
//    }
}
