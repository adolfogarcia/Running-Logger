package com.example.adolfo.runninglogger;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by Adolfo on 8/27/2017.
 * This class didn't end up being used so we decided to scrap it
 */

//@RequiresApi(api = Build.VERSION_CODES.N)
//public class myDatePicker implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
//    String dateChosen = null;
//    Calendar myCalendar = Calendar.getInstance();
//
//    TextView tv;
//    Context mContext;
//    int mYear = 0;
//    int day = 0;
//    int month = 0;
//
//    public myDatePicker(Context context, TextView newTV)
//    {
//        tv = newTV;
//        mContext = context;
//    }
//
//    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//        mYear = year;
//        month = monthOfYear;
//        day = dayOfMonth;
//        updateDisplay();
//    }
//
//    public void onClick(View v) {
//        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
//
//        DatePickerDialog dialog = new DatePickerDialog(mContext, this,
//                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
//                calendar.get(Calendar.DAY_OF_MONTH));
//        dialog.show();
//    }
//
//    private void updateDisplay() {
//
//        tv.setText(month + ' ' + day + ' ' + mYear);
//    }
//}
//    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
//
//        @Override
//        public void onDateSet(DatePicker view, int year, int monthOfYear,
//                              int dayOfMonth) {
//            // TODO Auto-generated method stub
//            myCalendar.set(Calendar.YEAR, year);
//            myCalendar.set(Calendar.MONTH, monthOfYear);
//            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//            updateLabel();
//        }
//
//    };
//
//    public void whenClicked(){
//
//        // TODO Auto-generated method stub
//        new DatePickerDialog(newWorkout2.this, date, myCalendar
//                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
//                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
//
//    };
//
//    public void updateLabel() {
//        String myFormat = "MM/dd/yy"; //In which you need put here
//        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
//
//        tvDateChosen.setText(sdf.format(myCalendar.getTime()));
//
//    }


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
//}
