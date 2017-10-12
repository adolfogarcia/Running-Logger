package com.example.adolfo.runninglogger;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Adolfo on 8/29/2017.
 */

public class User {

    User()
    {

        mWorkouts = new LinkedList<Workout>();
        mUserName = "NONE";
    };

    //SETTERS/GETTERS

    public void setUserName(String newName)
    {
        if (isStringEmpty(newName))
        {
            newName = "NONE";
        }
        mUserName = newName;
    }

    public void setMileage(String newMileage)
    {
        double newMileageDouble = 0;
        if (!isStringEmpty(newMileage)) {
            newMileageDouble = Double.parseDouble(newMileage);
        }
        mGoalMileage = newMileageDouble;
    }

    // We are gonna have two functions that deal with the manipulation of the Workout Queue

    // this one simply copies one WorkoutQ to the user one. Might wanna make it deep copy.
    public void setWorkoutQueue(LinkedList<Workout> newWorkoutLL) {mWorkouts = newWorkoutLL;}

    // This function inserts a new Workout to the queue while making sure the queue is within the size limits
    public void enqueueNewWorkout(Workout newWorkout)
    {
        //boolean success = false;

        if(mWorkouts.size() > 50)
        {
            // Remove the head and insert the new workout at the end
            Workout temp = mWorkouts.remove();
       //     success = true;
        }
        mWorkouts.add(newWorkout);
    }

    public String getUserName()
    {

        return mUserName;
    }

    public double getGoalMileage() {return mGoalMileage; }

    public Workout getWorkout()
    {
//        Workout tempWorkout = mWorkouts.remove();
//        mWorkouts.add(tempWorkout);
//        return tempWorkout;
        if(mWorkouts.isEmpty())
        {
            return null;
        }
        else
        {
            return mWorkouts.peek();
        }
    }

    public boolean isStringEmpty(String textToCheck)
    {
        boolean success = true;
        if(textToCheck.length() > 0)
        {
            success = false;
        }
        return success;
    }


    public LinkedList getWorkoutLL()
    {
        return mWorkouts;
    }


    // MEMBERS

    private String mUserName;

    // How much the user wants to run in a week; Can be changed
    private double mGoalMileage;

    //Max of 50
    private LinkedList<Workout>  mWorkouts;

  //  private boolean isEmpty = true;

    //More possible User members: Weight, Week #, School, etc
}
