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

import static com.example.tisapp.fragment_manual_entry_form.input_date2;


public class selected_date_fragment3 extends DialogFragment {

    DatePicker mDatePicker;
    public static String date_conversion4;

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

         DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {


                SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MMM-yyyy");
                calendar.set(year,month,dayOfMonth);

                date_conversion4 = dateFormat1.format(calendar.getTime());
                input_date2.setText(date_conversion4);

                Log.d("DateIs" , date_conversion4);


            }
        };

        DatePickerDialog dialog=new DatePickerDialog(getActivity(),dateSetListener,year,month,day);
//        dialog.getDatePicker().setMaxDate(c.getTimeInMillis());

        return dialog;
    }

}
