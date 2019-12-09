package com.example.tisapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static com.example.tisapp.fragment_leave_form.cbx_serveform_selectionbox2;
import static com.example.tisapp.fragment_leave_form.days_leave;
import static com.example.tisapp.fragment_leave_form.to_date;


public class selected_date_fragment10 extends DialogFragment {

    public static String date_conversion;
    DatePickerDialog.OnDateSetListener dateSetListener;
    MaterialStyledDialog.Builder dialogHeader_3;
    SendMessage SM;
    String fromDate,weekday_name;
    @RequiresApi(api = Build.VERSION_CODES.N)



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);


        if (cbx_serveform_selectionbox2.isChecked())
        {
            days_leave.setText(""+0.5);
        }


        else
        {
            dateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
                    calendar.set(year, month, dayOfMonth);

                    date_conversion = dateFormat1.format(calendar.getTime());
                     fromDate = selected_date_fragment00.date4;
                    to_date.setText(date_conversion);



                    if (fromDate !=null && !fromDate.isEmpty())
                    {

                        Log.d("fromDate_toDate", fromDate + date_conversion);
                    }

                    else
                    {
                        weekday_name = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).format(System.currentTimeMillis());
                        Log.d("Date_Full3", weekday_name);
                    }

                    String toDate = to_date.getText().toString();
                    Log.d("fromDate_toDate", fromDate + weekday_name);
//                Log.d("toDate" , date_conversion);

                    Calendar date1Calendar = Calendar.getInstance();
                    try {

                        if (fromDate !=null && !fromDate.isEmpty())
                        {
                            date1Calendar.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(fromDate));
                        }
                        else
                        {
                            date1Calendar.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(weekday_name));

                        }

                        Calendar date2Calendar = Calendar.getInstance();
                        date2Calendar.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(toDate));

                        long test = date2Calendar.getTime().getTime() - date1Calendar.getTime().getTime();
                        String daysLeft = TimeUnit.DAYS.convert(test + 1, TimeUnit.MILLISECONDS) + 1 + "";

                        int To_From = Integer.parseInt(TimeUnit.DAYS.convert(test, TimeUnit.MILLISECONDS) + "");


                        if (To_From < 0) {


//                            new TTFancyGifDialog.Builder(getActivity())
//                                    .setTitle("Error Message")
//                                    .setMessage("From Date Should Be smaller then To date")
//                                    .setPositiveBtnBackground("#f4de15")
//                                    .setPositiveBtnText("Ok")
//                                    .setGifResource(R.drawable.g)
//                                    .isCancellable(true)
//                                    .OnPositiveClicked(new TTFancyGifDialogListener() {
//                                        @Override
//                                        public void OnClick()
//                                        {
//                                            from_date.setText("");
//                                            to_date.setText("");
//                                            days_leave.setText("");
//
//                                        }
//                                    })
//                                    .build();


                            dialogHeader_3 = new MaterialStyledDialog.Builder(getActivity())
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
                            dialogHeader_3.show();



                        }

                        else
                        {
                            Log.d("ldod", daysLeft);

                            to_date.setText(toDate);
                            days_leave.setText(daysLeft);
                        }

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
            };
        }

        DatePickerDialog dialog = new DatePickerDialog(getActivity(), dateSetListener, year, month, day);
        return dialog;
        }

    interface SendMessage {
        void sendData(String message);
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//
//        try {
//            SM = (SendMessage) getActivity();
//        } catch (ClassCastException e) {
//            throw new ClassCastException("Error in retrieving data. Please try again");
//        }
//    }
}
