package com.example.tisapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;

import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.example.tisapp.fragment_leave_form.cbx_serveform_selectionbox;
import static com.example.tisapp.fragment_leave_form.cbx_serveform_selectionbox2;
import static com.example.tisapp.fragment_leave_form.days_leave;
import static com.example.tisapp.fragment_leave_form.from_date;
import static com.example.tisapp.fragment_leave_form.to_date;


public class selected_date_fragment00 extends DialogFragment {

    public static String date_conversions;
    Date date;
    String strtext;
   public String date3;
           public static String date4 ;

           MaterialStyledDialog.Builder dialogHeader_3;

//   SendMessage2 SM2;
    static String daysLeft;




    public static String displayReceivedData(String message)
    {

        date4 =message;
        Log.d("Date4",date4);
        return message;
    }

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

                SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
                calendar.set(year,month,dayOfMonth);

                date_conversions = dateFormat1.format(calendar.getTime());
                String date1 = date_conversions;
                String date2 = date_conversions;

                Calendar date1Calendar = Calendar.getInstance();
                try {

                    int compare = date1.compareToIgnoreCase(displayReceivedData(date2));


                    if (compare == 0) {
                        date1Calendar.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(date1));
                        Calendar date2Calendar = Calendar.getInstance();
                        date2Calendar.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(date2));


                        if (cbx_serveform_selectionbox2.isChecked()) {
                            from_date.setText(date_conversions);
                            to_date.setText(date_conversions);
                            days_leave.setText("" + 0.5);
                        }

                        else if (cbx_serveform_selectionbox.isChecked())
                        {

                            long test = date2Calendar.getTime().getTime() - date1Calendar.getTime().getTime();
                            int To_From = Integer.parseInt(TimeUnit.DAYS.convert(test, TimeUnit.MILLISECONDS) + "");

                            if (To_From < 0) {


//                                new TTFancyGifDialog.Builder(getActivity())
//                                        .setTitle("Error Message")
//                                        .setMessage("From Date Should Be smaller then To date")
//                                        .setPositiveBtnBackground("#f4de15")
//                                        .setPositiveBtnText("Ok")
//                                        .setGifResource(R.drawable.header)
//                                        .isCancellable(true)
//                                        .OnPositiveClicked(new TTFancyGifDialogListener() {
//                                            @Override
//                                            public void OnClick() {
//
//                                            }
//                                        })
//                                        .build();

                                /*dialogHeader_3 = new MaterialStyledDialog.Builder(getActivity())
                                        .setHeaderDrawable(R.drawable.header)
                                        .setIcon(new IconicsDrawable(getActivity()).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                        .withDialogAnimation(true)
                                        .setTitle("Error Message")
                                        .setDescription("From Date Should Be smaller then To date")
                                        .setPositiveText("OK")
                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                                            @Override
                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                                            }
                                        });
                                dialogHeader_3.show();*/

                            }

                            else
                            {
                                daysLeft = ""+(date2Calendar.get(Calendar.DAY_OF_MONTH) - (date1Calendar.get(Calendar.DAY_OF_MONTH))+1);
                                Log.d("ldod" , daysLeft);

                                from_date.setText(date_conversions);
                                to_date.setText(date2);

                                days_leave.setText(daysLeft);
                            }
                        }
                    }

                   else if (compare <0)
                    {
                        date1Calendar.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(date1));
                        Calendar date2Calendar = Calendar.getInstance();
                        date2Calendar.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(date3));

                        daysLeft = ""+(date2Calendar.get(Calendar.DAY_OF_MONTH) - (date1Calendar.get(Calendar.DAY_OF_MONTH))+1);
                        Log.d("ldod" , daysLeft);


                        from_date.setText(date_conversions);
                        to_date.setText(date3);
                        days_leave.setText(daysLeft);
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }
        };

        DatePickerDialog dialog=new DatePickerDialog(getActivity(),dateSetListener,year,month,day);

        return dialog;
    }
}
