package com.example.tisapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;

import static com.example.tisapp.fragment_manual_entry_form.input_date3;

@SuppressLint("ValidFragment")
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {


    Button textView;
    Context context;
     Calendar c;
    {
        getContext();
    }

    @SuppressLint("ValidFragment")
    public TimePickerFragment(Button textView, Context context) {
        this.textView= textView;
        this.context = context;
    }



String time;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

         c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);


        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        c.set(year,month,day);


        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute)
    {
        String hour = hourOfDay+"";
        if (hourOfDay < 10) {
            hour = "0"+hourOfDay;
        }
        String min = minute+"";
        if (minute < 10)
            min = "0"+minute;
        input_date3.setText(hour + ":" + min);

        Log.d("Timeeee", hour + ":"+min);

    }
}
