package com.example.tisapp.Settings;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.tisapp.BaseActivity;
import com.example.tisapp.DBHelper;
import com.example.tisapp.Login.LoginActivity;
import com.example.tisapp.R;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Settings_Activity extends BaseActivity {

    EditText eturl, input_password3;
    Button btnSave;
    public static String urls;
    public static String urls2;

        DBHelper objDBHelper;
    Cursor cursor;
    String deviceName, datetime;
    TextView day, date, times, location;

    MaterialStyledDialog.Builder dialogHeader_3;
    Integer number_length;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings_);

        eturl = (EditText) findViewById(R.id.input_password);
        input_password3 = (EditText) findViewById(R.id.input_password3);

        btnSave = (Button) findViewById(R.id.singupbtn);

        day = (TextView) findViewById(R.id.day);
        date = (TextView) findViewById(R.id.date);
//        times = (TextView) findViewById(R.id.times);

        String weekday_name = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(System.currentTimeMillis());  //Fruday
//        LocalDate.now().getDayOfWeek().name();
        Log.d("Day_is", weekday_name);
        day.setText("Its " + weekday_name);


        String datesssss = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        Log.d("Date_is", datesssss);
        date.setText("Its " + datesssss);

        objDBHelper=new DBHelper(this);
        getUrl();
        getTimeOut();


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urls = eturl.getText().toString();
                urls2 = input_password3.getText().toString();

                if (urls.equals("")) {

                    dialogHeader_3 = new MaterialStyledDialog.Builder(context)
                            .setHeaderDrawable(R.drawable.header)
                            .setIcon(new IconicsDrawable(context).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                            .withDialogAnimation(true)
                            .setTitle("Error Message")
                            .setDescription("URL Cant Be Empty.. ")
                            .setPositiveText("OK")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                }
                            });
                    dialogHeader_3.show();

                } else if (urls2.equals("")) {
//                    Toast.makeText(Settings_Activity.this, "Length Cant Be Empty..", Toast.LENGTH_SHORT).show();

                    dialogHeader_3 = new MaterialStyledDialog.Builder(context)
                            .setHeaderDrawable(R.drawable.header)
                            .setIcon(new IconicsDrawable(context).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                            .withDialogAnimation(true)
                            .setTitle("Error Message")
                            .setDescription("Server Time Out Is Required..!! ")
                            .setPositiveText("OK")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

//                        Intent intent = new Intent(Settings_Activity.this , LoginActivity.class);
//                        startActivity(intent);
//
//                        Intent callGPSSettingIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                        startActivity(callGPSSettingIntent);
                                }
                            });
                    dialogHeader_3.show();


                }

//                else
//                {
//                    number_length=Integer.parseInt(urls2);
//                }


                else {

                    Log.d("llk", urls);
                    eturl.setText(urls);

                    input_password3.setText(urls2);
                    Log.d("llk2", urls2);

                    insertLog(urls);
                    insertLog2(urls2);


                    Intent intent = new Intent(Settings_Activity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


    private void getTimeOut() {
        try
        {
            objDBHelper = new DBHelper(this);
            String query = "Select * from LogHistory2";

            cursor = objDBHelper.getCursor(query);
            if (cursor.getCount() > 0)
            {
                if (cursor.moveToFirst())
                {
                    do
                    {
                        deviceName = cursor.getString(cursor.getColumnIndex("TISTIMEOUT"));
                        input_password3.setText(deviceName);
                        Log.d("DeviceTimeOut" , deviceName);
                    }

                    while (cursor.moveToNext());
                }
            }

            else
            {
                Toast.makeText(getApplicationContext(), "No Data To Show", Toast.LENGTH_LONG).show();
            }

        }

        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    private void getUrl() {
        try
        {
            objDBHelper = new DBHelper(this);
            String query = "Select * from LogHistory";

            cursor = objDBHelper.getCursor(query);
            if (cursor.getCount() > 0)
            {
                if (cursor.moveToFirst())
                {
                    do
                {
                        deviceName = cursor.getString(cursor.getColumnIndex("TISURL"));
                        eturl.setText(deviceName);
                        Log.d("DeviceUrl" , deviceName);
                }

                    while (cursor.moveToNext());
                }
            }

            else
            {
                Toast.makeText(getApplicationContext(), "No Data To Show", Toast.LENGTH_LONG).show();
            }

        }

        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    public void insertLog2(String deviceName) {
        try
        {
            String query = "Insert into LogHistory2(TISTIMEOUT)values('"+deviceName+"')";
            objDBHelper.command(query);
            Toast.makeText(getApplicationContext(), "Log Created", Toast.LENGTH_LONG).show();
        }

        catch(Exception ex)
        {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    public void insertLog(String deviceName) {
        try
        {
            String query = "Insert into LogHistory(TISURL)values('"+deviceName+"')";
            objDBHelper.command(query);
            Toast.makeText(getApplicationContext(), "Log Created", Toast.LENGTH_LONG).show();
        }

        catch(Exception ex)
        {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LoginActivity._passwordText.setText("");
    }
}
