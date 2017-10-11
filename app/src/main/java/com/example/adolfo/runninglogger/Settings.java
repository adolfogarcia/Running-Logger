package com.example.adolfo.runninglogger;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.id.message;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setTitle("Settings");
    }


    public void updateUserName(View view)
    {
        updateHelper("NAME");
    }

    public void updateUserMileage(View view)
    {
        updateHelper("MILEAGE");
    }

    public void goToMainMenu(View view)
    {
        Intent intent = new Intent(this, MainMenu.class);
       // intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    private boolean updateHelper(String key)
    {
        boolean success = false;
        if(key == "NAME")
        {
            //update user name
            EditText etName = (EditText) findViewById(R.id.editText_newName);

            SharedPreferences sharedPref = getSharedPreferences("USER", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("USERNAME", etName.getText().toString());
            success = editor.commit();
        }
        else
        {
            //update user mileage goal
            EditText etMileage = (EditText) findViewById(R.id.editText_newName);

            SharedPreferences sharedPref = getSharedPreferences("USER", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("MILEAGE GOAL", Integer.parseInt(etMileage.toString()));
            success = editor.commit();
        }

        // A check for if the message was succesfully saved
        if(success)
        {
            Toast.makeText(this, "Succesfully saved.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
        return success;
    }

}
