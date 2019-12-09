package com.example.tisapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;



//import static com.example.zain.project.Reports.Daily_Attendance.input_date5;


public class selected_date_fragment5 extends DialogFragment {

    public static String date_conversion;
    @RequiresApi(api = Build.VERSION_CODES.N)

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        final Calendar calendar= Calendar.getInstance();
        calendar.set(year, month,day);


        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MMM-yyyy");
        final String datetime = dateFormat1.format(c.getTime());

        Log.d("date_format" , datetime);

         DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MMM-yyyy");
                calendar.set(year,month,dayOfMonth);

                 date_conversion = dateFormat1.format(calendar.getTime());
                fragment_daily_atendance.shadow.setText(date_conversion);

                Log.d("DateIs" , date_conversion);

            }
        };

        DatePickerDialog dialog=new DatePickerDialog(getActivity(),dateSetListener,year,month,day);
//        dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
//        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

        return dialog;
    }

}
