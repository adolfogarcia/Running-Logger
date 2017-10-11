package com.example.adolfo.runninglogger;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.id.message;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class setUpUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_user);

        setTitle("User Set-Up");


    }

    public void takeAllInfo(View view) {
        User thisUser = MainActivity.mainUser;
        EditText etName = (EditText) findViewById(R.id.editText_name);
        EditText etMileage = (EditText) findViewById(R.id.editText_mileage);
        boolean success = false;

        String name = (etName.getText().toString());
        String mileage  = (etMileage.getText().toString());

        // Here we make sure the user actually entered values. If they didn't the app would crash.
        // 10-10-17: Need to add a message for invalid input and a way to detect invalid input
        if(!name.isEmpty() && !mileage.isEmpty())
        {
            success = saveInfo(etName.getText().toString(), Integer.parseInt(etMileage.getText().toString()));
            if(success)
            {
                Toast.makeText(this, "Succesfully saved.", Toast.LENGTH_SHORT).show();
            }
            finish();
        }
        else {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }

    }


    private boolean saveInfo(String mUserName, int mMileage)
    {
        boolean success = false;
        String name = "USERNAME", mileageGoal = "MILEAGE GOAL";

        SharedPreferences sharedPref = getSharedPreferences("USER", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("USERNAME", mUserName);
        editor.putInt("MILEAGE GOAL", mMileage);
        success = editor.commit();

        return success;

    }


    private void goToMainMenu()
    {

    }

//    private String retrieveName()
//    {
//        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
//
//    }
}
