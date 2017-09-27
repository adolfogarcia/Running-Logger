package com.example.adolfo.runninglogger;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.Vector;

/**
 * Created by Adolfo on 8/24/2017.
 */

// A queue of this class will exist in User.java, representing all the workouts that have been run
public class Workout {

    //Constructors
    Workout()
    {
        mWorkoutName = "Run";
        mMileage = 0.0;
        mDuration = 0;
        mDate = " ";
        mLocation = "No Location";
        mFatigueLevel = 0;
        mWeather = "N/A";

    };

    // SETTERS/GETTERS

    public void setWorkoutName(String newWorkout)
    {
        if(newWorkout.isEmpty())
        {
            newWorkout = "Run";
        }
        mWorkoutName = newWorkout;
    }

    public void setMileage(String newMileage)
    {
        double newMileageDouble = 0;
        if (!isStringEmpty(newMileage)) {
            newMileageDouble = Double.parseDouble(newMileage);
        }
        mMileage = newMileageDouble;
    }

    public void setDuration(String newDuration)
    {
        double newDurationDouble = 0;
        if (!isStringEmpty(newDuration)) {
            newDurationDouble = Double.parseDouble(newDuration);
        }
        mDuration = newDurationDouble;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setDate(String newDate)
    {
        if(isStringEmpty(newDate))
        {
            DateFormat dateFormat = new SimpleDateFormat("EEE. MMM dd, yyyy");
            Date date = new Date();
            newDate = date.toString();
        }
        mDate = newDate;
    }

    public void setLocation(String newLocation)
    {
        if (isStringEmpty(newLocation))
        {
            newLocation = "No Location provided";
        }
        mLocation = newLocation;
    }

    public void setFatigueLevel(String newFL)
    {
        int newFLint = 0;
        if (newFL != null)
        {
            newFLint = Integer.parseInt(newFL);
        }
        mFatigueLevel = newFLint;
    }

    public void setWeather(String newWeather)
    {
        if (isStringEmpty(newWeather))
        {
            newWeather = "N/A";
        }
        mWeather = newWeather;
    }

    // This function will probably be expanded on a lot because  mSplits is a list
    // so we're gonna need to add in every split in individually
    public void setSplits(Vector<String> newSplits)
    {
       mSplits = newSplits;
    }

    public void setSplitType(String newSplitType)
    {
        if (isStringEmpty(newSplitType))
        {
            newSplitType = "N/A";
        }
        mSplitType = newSplitType;
    }

    public String getWorkoutName () {return mWorkoutName;}

    public double getMileage() {return mMileage;}

    public double getDuration() {return mDuration;}

    public String getDate()
    {
        return mDate;
    }

    public String getLocation() {return mLocation;}

    public int getFatigueLevel() {return mFatigueLevel;}

    public String getWeather() {return mWeather;}

    public Vector<String> getSplits() {return mSplits;}

    public String getSplitType() {return mSplitType;}

    public boolean isStringEmpty(String textToCheck)
    {
        boolean success = true;
        if(textToCheck.length() > 0)
        {
            success = false;
        }
        return success;
    }

    // MEMBERS

    // The type of Workout
    private String mWorkoutName;

    // The amount of miles run in that day
    private double mMileage;

    // How long the run lasted time-wise
    private double mDuration;

    // The day the workout was
    private String mDate;

    // Where it was run
    private String mLocation;

    // 1-10 value
    private int mFatigueLevel;

    private String mWeather;

    // The splits for a workout like VO2's and threshold
    private Vector<String> mSplits;

    /*
        8-29-17:
        How are we gonna include split type? Here are some ideas:
        1) In mSplits, since its a String list, the splits are gonna be separated by some sort
            of token (probably a comma) with a set of splits being separated by something else
            (probs a /). So what if we include the split type at the beginning?
            Ex: 8x2,1:20,1:21,1:23/10x1,:45,:40/ etc
        2) A string array that matches the # of split sets and contains the split type
        8-29-17 Update:
        Just realized I'm an idiot. This is the Workout class. Thus it should only have the splits for a SINGLE workout.
        Thus we can just have a string value with the split type.
     */
    private String mSplitType;

}
