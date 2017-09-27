package com.example.adolfo.runninglogger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static android.R.id.message;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class setUpUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_user);

        setTitle("User Set-Up");
    }

    public void getAllInfo(View view) {
        User thisUser = MainActivity.mainUser;
        EditText etName = (EditText) findViewById(R.id.editText_name);
        EditText etMileage = (EditText) findViewById(R.id.editText_mileage);

        thisUser.setUserName(etName.getText().toString());
        thisUser.setMileage(etMileage.getText().toString());

        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

}
