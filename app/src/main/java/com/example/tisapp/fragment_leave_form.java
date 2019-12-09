package com.example.tisapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.tisapp.Login.LoginActivity;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.tisapp.Settings.Settings_Activity.urls;
import static com.example.tisapp.selected_date_fragment00.daysLeft;


public class fragment_leave_form extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    DatabaseHelper db;
    MaterialStyledDialog.Builder dialogHeader_3;
    Spinner level_code;
    String ki;
    SpinnerData v;
    DatabaseHelper dbHelper;

    public ArrayList<SpinnerData> lstAnime = new ArrayList<>();
    CustomAdapter adapter;
    ArrayAdapter adapters;
    ImageView iv_back;
    int i;
    OkHttpClient client;

//    private final String JSON_URL = urls + "api/Leave/GetEntitledLeaveCodes";
//

    //    private final String JSON_URL = "api/Leave/GetEntitledLeaveCodes";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    JSONObject jsonObject;


    TextView manual_date, tvTodayTime, tvTodayDate, tvTodayTimeAMPM;
    public static EditText input_date1, days_leave;
    static Button from_date, to_date;
    ImageView home_Screen;
    static CheckBox cbx_serveform_selectionbox2, cbx_serveform_selectionbox;
    String weekday_name;


    public static String hi;
    Button form_button;
    EditText input_date, input_date2;

    String date_conversions;


    String value2;
    String ko;
    String weekday_name2, datetime, datetime3, datetime2;

    String datesssss;


    String responses, abc, def, ghi, value4;


//    public fragment_leave_form() {
//        // Required empty public constructor
//    }
//
//    public static fragment_leave_form newInstance(String param1, String param2) {
//        fragment_leave_form fragment = new fragment_leave_form();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
//        return fragment;
//    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_apply_leave);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        db = new DatabaseHelper(fragment_leave_form.this);

        client = new OkHttpClient();
        lstAnime = new ArrayList<>();

        level_code = (Spinner) findViewById(R.id.spLeaveSubject2);
        level_code.setOnItemSelectedListener(this);

//        List<SpinnerData> bdoList = null;


//        List<SpinnerData> finalBdoList = bdoList;

//        bdoList = new ArrayList<>();

        final ProgressView circularProgressBar = (ProgressView) findViewById(R.id.circular_progress);
        circularProgressBar.setVisibility(View.GONE);


        iv_back =(ImageView) findViewById(R.id.iv_back);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });


        loadSpinnerData();

//
//        if (isNetAvailable())
//        {
//            retrieveJSON();
//        }
//
//        else
//        {
//            loadSpinnerData();
//        }


        input_date2 = (EditText) findViewById(R.id.input_date2);
//        input_date1 = (EditText) view.findViewById(R.id.etLeaveReason2);
        input_date = (EditText) findViewById(R.id.input_date);
        days_leave = (EditText) findViewById(R.id.days_leave);

        days_leave.setFocusable(false);
        days_leave.setEnabled(false);
        days_leave.setInputType(InputType.TYPE_NULL);
        days_leave.setClickable(false);


        from_date = (Button) findViewById(R.id.from_date);

//        String datesssss = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
//        Log.d("Date_is", datesssss);

        to_date = (Button) findViewById(R.id.to_date);


//        int year = c.get(Calendar.YEAR);
//        int month = c.get(Calendar.MONTH);
//        int day = c.get(Calendar.DAY_OF_MONTH);
//
//        final Calendar calendar= Calendar.getInstance();
//        calendar.set(year, month,day);
//
        weekday_name = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(System.currentTimeMillis());
        Log.d("Date_Full3", weekday_name);
        from_date.setText(weekday_name);



        weekday_name = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(System.currentTimeMillis());
        Log.d("Date_Full3", weekday_name);
        to_date.setText(weekday_name);


        cbx_serveform_selectionbox = (CheckBox) findViewById(R.id.cbx_serveform_selectionbox);
        cbx_serveform_selectionbox2 = (CheckBox) findViewById(R.id.cbx_serveform_selectionbox2);


        form_button = (Button) findViewById(R.id.form_button);

        cbx_serveform_selectionbox2.setChecked(false);
        cbx_serveform_selectionbox.setChecked(true);

        from_date.setInputType(InputType.TYPE_NULL);
        to_date.setInputType(InputType.TYPE_NULL);

//        input_date1.setFocusable(false);
//        input_date1.setEnabled(false);
//        input_date1.setInputType(InputType.TYPE_NULL);
//        input_date1.setClickable(false);
//
//
//        java.util.Calendar c = java.util.Calendar.getInstance();
//        java.text.SimpleDateFormat dateformat = new java.text.SimpleDateFormat("dd-MMM-yyyy,  hh:mm:ss aa");
//        String datetime = dateformat.format(c.getTime());
//        System.out.println(datetime);
//
//        input_date1.setText(datetime);
//        Log.d("current_date", datetime);


        from_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new selected_date_fragment00();
                newFragment.show(getSupportFragmentManager(), "date picker");
            }
        });


        to_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new selected_date_fragment10();
                newFragment.show(getSupportFragmentManager(), "date picker");
            }
        });


        cbx_serveform_selectionbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    cbx_serveform_selectionbox2.setChecked(false);


//                        days_leave.setText(daysLeft);

                    if (days_leave.getText().toString().equals("0.5")) {
                        days_leave.setText("" + 1);
                    } else {
                        days_leave.setText(daysLeft);

                    }

//                    days_leave.setText(daysLeft);

                    to_date.setFocusable(true);
                    to_date.setEnabled(true);
                    to_date.setClickable(true);

                    from_date.setFocusable(true);
                    from_date.setEnabled(true);
                    from_date.setClickable(true);
                } else {
//Dp Nothing
                    cbx_serveform_selectionbox2.setChecked(true);
                }
            }
        });

        cbx_serveform_selectionbox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbx_serveform_selectionbox.setChecked(false);
                    to_date.setFocusable(false);
                    to_date.setEnabled(false);
                    days_leave.setText("" + 0.5);
                    to_date.setText(from_date.getText().toString());
                    to_date.setClickable(false);
                } else {

                    cbx_serveform_selectionbox.setChecked(true);
                    to_date.setFocusable(false);
                    to_date.setEnabled(false);
                    to_date.setClickable(false);

                    from_date.setFocusable(false);
                    from_date.setEnabled(false);
                    from_date.setClickable(false);
                }
            }
        });


        form_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (from_date.getText().toString().equals("") && to_date.getText().toString().equals("")) {
                    from_date.setError("Start Date Is Required");
                    to_date.setError("Start Date Is Required");
                } else if (input_date2.getText().toString().equals("")) {
                    input_date2.setError("Remarks Is Required..!");
                } else {


                    AddDataleaves();
                }

            }
        });
    }


//    boolean insertData = false;
    private void AddDataleaves()
    {
        if(!db.IsDataExistBetweenFromToDate(from_date.getText().toString(),to_date.getText().toString()))
        {

//            String LeaveCode,String FromDate,String ToDate,Boolean FullDayLeave,String remarks,String syncStatus

            long id=  db.insertleavedata(ki , from_date.getText().toString(),to_date.getText().toString(),cbx_serveform_selectionbox.isChecked(),input_date2.getText().toString(), "false");

            if(id<0) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "UnSuccessfullLeave", Toast.LENGTH_SHORT).show();
                        Log.e("UnSuccessfullLeave", "unsuc");
                    }
                });

            }else {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_leave_form.this)
                                .setHeaderDrawable(R.drawable.header)
                                .setIcon(new IconicsDrawable(fragment_leave_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                .withDialogAnimation(true)
                                .setTitle("Confirmation Message")
                                .setDescription("Entry Added Successfully..!!")
                                .setPositiveText("OK")
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {


                                        days_leave.setText("");
                                        startActivity(new Intent(fragment_leave_form.this , Home_Screen.class));


                                    }
                                });
                        dialogHeader_3.show();


//                        Toast.makeText(getApplicationContext(), "SuccessfullLeave", Toast.LENGTH_SHORT).show();
//                        Log.e("SuccessfullLeave", "succ");
                    }
                });
            }
        }


        else
        {
            dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_leave_form.this)
                    .setHeaderDrawable(R.drawable.header)
                    .setIcon(new IconicsDrawable(fragment_leave_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                    .withDialogAnimation(true)
                    .setTitle("Error Message")
                    .setDescription("Entry Already Exist..!!")
                    .setPositiveText("OK")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {


                        }
                    });
            dialogHeader_3.show();

        }



//        insertData = db.insertleavedata(ki , from_date.getText().toString(),to_date.getText().toString(),cbx_serveform_selectionbox.isChecked(),input_date2.getText().toString());

    }


    private void loadSpinnerData()
    {

//        DatabaseHandler db = new DatabaseHandler(getApplicationContext());

        // Spinner Drop down elements

       ArrayList<SpinnerData> lables = db.getSpinnerData();

        adapters = new CustomAdapter2(this, R.layout.spinner_rows,lables );
        adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        level_code.setAdapter(adapters);

    }


//       List<SpinnerData> lables = db.getSpinnerData();
//
//        adapters = new CustomAdapter2(getApplicationContext(), R.layout.spinner_rows,lables );
//        adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        // attaching data adapter to spinner
//        level_code.setAdapter(adapters);

    public  boolean isNetAvailable()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) fragment_leave_form.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo =connectivityManager.getActiveNetworkInfo();
        return networkInfo !=null && networkInfo.isConnected();
    }

//    private void retrieveJSON()
//    {
//        request = new JsonArrayRequest(JSON_URL, new com.android.volley.Response.Listener<JSONArray>()
//        {
//            @Override
//            public void onResponse(JSONArray response)
//            {
//
//                for (int i = 0; i < response.length(); i++)
//                {
//
//                    try {
//                        jsonObject = response.getJSONObject(i);
//
//                        String levName=jsonObject.getString("leaveName");
//                        String levCode=jsonObject.getString("leaveCode");
//
//                        Log.d("LeaveNamee" , levName);
//                        Log.d("LeaveCode2" , levCode);
//
//                        lstAnime.add(new SpinnerData(levCode,levName));
//
//
////                        ArrayList<SpinnerData> timeRequestOfflineData = db.getSpinnerData();
////
////                        if (timeRequestOfflineData.isEmpty())
////                        {
////
////                            db.insertleaveentitled(lstAnime);
////                            Log.d("InsertedInLeave" , "NAME" + levCode    + "CODE" + levName);
//////                            Toast.makeText(context, "No offline data available", Toast.LENGTH_SHORT).show();
//////                            layNoInternet.setVisibility(View.VISIBLE);
////                        }
////
////                        else
////                        {
//////                            db.DeleteLeavesTable(levName , levCode);
////
////                             db.delAllRecord();
////                             db.insertleaveentitled(lstAnime);
//////                            Log.d("InsertedInLeave" , "NAME" + levCode    + "CODE" + levName);
////                        }
//
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                    adapter = new CustomAdapter(getApplicationContext(), R.layout.spinner_rows,lstAnime );
//                    level_code.setAdapter(adapter);
//                }
//
//            }
//        }, new com.android.volley.Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                Log.d("Error12", error.toString());
//
//                                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_leave_form.this)
//                                .setHeaderDrawable(R.drawable.header)
//                                .setIcon(new IconicsDrawable(fragment_leave_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                .withDialogAnimation(true)
//                                .setTitle("Error Message")
//                                .setDescription("Cant Connect To Server")
//                                .setPositiveText("OK")
//                                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                    @Override
//                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//
////                                        Intent intent = new Intent(fragment_leave_form.this , LoginActivity.class);
////                                        startActivity(intent);
//                                    }
//                                });
//                        dialogHeader_3.show();
//                    }
//                });
//
//
//            }
//        })
//
//        {
//            @Override
//            public Map<String, String> getHeaders() {
//                Map<String, String> params = new HashMap<>();
//                params.put("Authorization", " Bearer " + LoginActivity.value);
//                params.put("Cache-Control", "no-cache");
//                params.put("Postman-Token", "afbf7fff-330c-4e4b-a6d1-9bb49be91eb8");
//                Log.d("AuthKey", LoginActivity.value);
//
//                return params;
//            }
//        };
//
//
//       int socketTimeout = 3000;//3 seconds - change to what you want
//       RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//       request.setRetryPolicy(policy);
//
//        requestQueue = Volley.newRequestQueue(fragment_leave_form.this);
//        requestQueue.add(request);
//
//    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        v =  (SpinnerData) level_code.getSelectedItem();
        ki = v.getLeaveCode();
        Log.d("spinnervalue" ,ki);
        Log.d("spinnerName" , v.getLeaveName());

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }











    public class CustomAdapter2  extends ArrayAdapter<String> {

        private Context activity;
        private ArrayList data;
        public Resources res;
        SpinnerData tempValues=null;
        LayoutInflater inflater;

        public CustomAdapter2(Context activitySpinner, int textViewResourceId, ArrayList objects)
        {
            super(activitySpinner, textViewResourceId, objects);
            activity = activitySpinner;
            data= objects;

            inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getDropDownView(int position, View convertView,ViewGroup parent) {
            return getCustomView(position, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, parent);
        }

        public View getCustomView(int position, ViewGroup parent) {

            View row = inflater.inflate(R.layout.spinner_rows, parent, false);
            tempValues = null;
            tempValues = (SpinnerData) data.get(position);

            TextView label = (TextView)row.findViewById(R.id.company);
            TextView sub= (TextView)row.findViewById(R.id.sub);

            if(position==0)
            {

                label.setText(tempValues.getLeaveName());
                sub.setText("");
            }
            else
            {
                label.setText(tempValues.getLeaveName());
            }
            return row;
        }
    }















    public class CustomAdapter  extends ArrayAdapter<String> {

        private Context activity;
        private ArrayList data;
        public Resources res;
        SpinnerData tempValues=null;
        LayoutInflater inflater;

        public CustomAdapter(Context activitySpinner, int textViewResourceId, ArrayList objects)
        {
            super(activitySpinner, textViewResourceId, objects);
            activity = activitySpinner;
            data= objects;

            inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getDropDownView(int position, View convertView,ViewGroup parent) {
            return getCustomView(position, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, parent);
        }

        public View getCustomView(int position, ViewGroup parent) {

            View row = inflater.inflate(R.layout.spinner_rows, parent, false);
            tempValues = null;
            tempValues = (SpinnerData) data.get(position);

            TextView label = (TextView)row.findViewById(R.id.company);
            TextView sub= (TextView)row.findViewById(R.id.sub);

            if(position==0)
            {

                label.setText(tempValues.getLeaveName());
                sub.setText("");
            }
            else
            {
                label.setText(tempValues.getLeaveName());
            }
            return row;
        }
    }






















}
