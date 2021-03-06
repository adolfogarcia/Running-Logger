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
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.R.id.message;
import static android.R.id.switch_widget;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

/*
    This screen will again have the date at the top. It will contain a section that displays the
    weather along with various button options: New Workout, Weekly View (displays all the runs that
    have been completed that week), Workouts (List explaining how all the workouts work and other terminology),
     Mileage progression (shows the Mileage Progression chart), Edit user Info, etc.
 */

public class MainMenu extends AppCompatActivity {
    User mainUser = MainActivity.mainUser;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // We make our own copy of mainUser to manipulate
        // 10-10-17: Consider creating copy constructor with reference to save time copying info
        setDate();
        setWeather();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onResume()
    {
        super.onResume();  // Always call the superclass method first

        setDate();
        setWeather();
    }

    // 8-29-17: maybe we can modify this with a switch statement so that we can have only one GoTo function
    // that goes to different menus depending on the button pressed
    // 8-29-17 Update: making a switch worked. However, it makes me wonder if a switch is slightly slower
    // because if we made every individual startActivity() its own function, there wouldn't be any
    // having look up the value of message to make a choice; the program would immediately know where to go.

    /*
     * The way this function works is by getting the button that was pressed, taking the text on that button,
     * and then using that text to determine which screen/activity to go to, using a switch statement.
     */
    public void goToNextMenu(View view)
    {
        Intent intent;
        int buttonID = view.getId(); // We get the ID of the button that we pressed
        Button choice = (Button) findViewById(buttonID); // We then make a button copy
        String message = choice.getText().toString(); // We extract the text on the button

        // Switch statement based on the text on the button
        switch(message) {
            case "New Workout":
                intent = new Intent(this, newWorkout2.class);
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
                break;
            case "Week View":
                intent = new Intent(this, weekView.class);
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
                break;
            case "Workouts":
                intent = new Intent(this, workoutExplanations.class);
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
                break;
            case "Mileage Progression":
                intent = new Intent(this, mileProgression.class);
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
                break;
            case "Settings":
                intent = new Intent(this, Settings.class);
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
                break;
            default:
                break;
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setDate()
    {
        // Getting the date and updating the time zone to make sure they are correct
        // 10-10-17: Consider Making this it's own function in like a utility class because
        //      tend to get the date a lot in this App
        DateFormat df = new SimpleDateFormat("EEE MMM d, yyyy");
        TimeZone PST = TimeZone.getTimeZone("America/Los_Angeles");
        df.setTimeZone(PST);
        String date = df.format(Calendar.getInstance().getTime());

        setTitle(date + " - Hello, " + mainUser.getUserName());
    }


    public void setWeather()
    {
        final TextView tvWeather = (TextView) findViewById(R.id.textView_weather);
        String url = "http://api.openweathermap.org/data/2.5/weather?id=5814921&APPID=744153e88cacf84c33bb2252bd6514de&units=imperial";

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONObject mainObject = response.getJSONObject("main");
                    JSONArray array = response.getJSONArray("weather");
                    JSONObject obj = array.getJSONObject(0);
                    String temperature = String.valueOf(mainObject.getDouble("temp"));
                    String description = obj.getString("description");
                    String city = response.getString("name");

                    tvWeather.setText("Temperature: " + temperature + " F , " + description);
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jor);
    }
}
