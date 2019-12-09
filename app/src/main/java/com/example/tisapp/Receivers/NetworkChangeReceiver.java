package com.example.tisapp.Receivers;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.example.tisapp.DBHelper;
import com.example.tisapp.DatabaseHelper;
import com.example.tisapp.Login.LoginActivity;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.tisapp.Login.LoginActivity.IMEI;
import static com.example.tisapp.Login.LoginActivity.passwordsave;
import static com.example.tisapp.Settings.Settings_Activity.urls;
import static com.example.tisapp.Settings.Settings_Activity.urls2;
import static com.google.android.gms.internal.zzahn.runOnUiThread;


public class NetworkChangeReceiver extends BroadcastReceiver
{

    MaterialStyledDialog.Builder dialogHeader_3;
    String responses,value2;
    //    public static String value345,value89;
    OkHttpClient client;
    boolean request_result,request_resultss;

    @SuppressLint("LongLogTag")
    @Override
    public void onReceive(Context context, Intent intent)
    {
        try
        {
            if (isOnline(context))
            {

                final DatabaseHelper dbHelper = new DatabaseHelper(context);
                Log.e("NetworkChangeRerciver", "Online Connect Intenet ");
                request_result = true;

                Cursor timeInRequestOfflineDatas = dbHelper.getOfflineData();

                Cursor timeInRequestOfflineData11 = dbHelper.getFilteredOfflineData(DatabaseHelper.TYPE_MANUAl_TIME_IN);
                Cursor timeInRequestOfflineData2 = dbHelper.getFilteredOfflineData(DatabaseHelper.TYPE_MANUAl_TIME_OUT);
                Cursor timeInRequestOfflineData3 = dbHelper.getFilteredOfflineData(DatabaseHelper.TYPE_MANUAl_TIME_REQUEST);


                if (timeInRequestOfflineData11.getCount() > 0 && timeInRequestOfflineData2.getCount() > 0  && timeInRequestOfflineData3.getCount() > 0  )
                {
                    Toast.makeText(context, "No Data For Sync..!!", Toast.LENGTH_SHORT).show();
                    return;
                }

                else
                {

                    if (timeInRequestOfflineDatas.moveToFirst())
                    {

                        final int ID = timeInRequestOfflineDatas.getInt(timeInRequestOfflineData11.getColumnIndex(DatabaseHelper.COL1));
//                            //TODO Make Server Manual Time In Request Here
//                            String COL2 = timeInRequestOfflineDatas.getString(timeInRequestOfflineData11.getColumnIndex(DatabaseHelper.COL11));
                        String GetType = timeInRequestOfflineDatas.getString(timeInRequestOfflineData11.getColumnIndex(DatabaseHelper.COL10));



                        if (GetType.equals(DatabaseHelper.TYPE_MANUAl_TIME_IN))
                        {
                            Toast.makeText(context, "Sync1", Toast.LENGTH_SHORT).show();

                            //insert api here..!!

                        }

                        if (GetType.equals(DatabaseHelper.TYPE_MANUAl_TIME_OUT))
                        {
                            Toast.makeText(context, "Sync2", Toast.LENGTH_SHORT).show();

                            //insert api here..!!

                        }

                        if (GetType.equals(DatabaseHelper.TYPE_MANUAl_TIME_REQUEST))
                        {
                            Toast.makeText(context, "Sync3", Toast.LENGTH_SHORT).show();

                            //insert api here..!!

                        }

                        dbHelper.updateStatus(ID, "true");
                    }

                    while (timeInRequestOfflineData11.moveToNext());

                }
                timeInRequestOfflineData11.close();


////                GetprofileParams(context);
////                SendTokenParamss(context);
//                Log.e("NetworkChangeRerciver", "Online Connect Intenet ");
//
//                final DatabaseHelper dbHelper = new DatabaseHelper(context);
//                request_result = true;
//
//                Cursor timeInRequestOfflineDatas = dbHelper.getOfflineData();
//
//                Cursor timeInRequestOfflineData11 = dbHelper.getFilteredOfflineData(DatabaseHelper.TYPE_MANUAl_TIME_IN);
//                Cursor timeInRequestOfflineData2 = dbHelper.getFilteredOfflineData(DatabaseHelper.TYPE_MANUAl_TIME_OUT);
//                Cursor timeInRequestOfflineData3 = dbHelper.getFilteredOfflineData(DatabaseHelper.TYPE_MANUAl_TIME_REQUEST);
//
//                if (timeInRequestOfflineData11.getCount() > 0 && timeInRequestOfflineData2.getCount() > 0  && timeInRequestOfflineData3.getCount() > 0  ) {
//                    Toast.makeText(context, "No Data For Sync..!!", Toast.LENGTH_SHORT).show();
//                    return;
//                } else {
//
//                    Log.e("timeInRequestOffline", "COUNT >>>>> " + timeInRequestOfflineData11.getColumnCount());
//
//
//
//
//                    if (timeInRequestOfflineDatas.moveToFirst()) {
//
//
//                        while (timeInRequestOfflineData2.moveToNext())
//                        {
//
//                            final int ID = timeInRequestOfflineData2.getInt(timeInRequestOfflineData2.getColumnIndex(DatabaseHelper.COL1));
////                            //TODO Make Server Manual Time In Request Here
//                            String COL2 = timeInRequestOfflineDatas.getString(timeInRequestOfflineData11.getColumnIndex(DatabaseHelper.COL11));
//                            String GetType = timeInRequestOfflineDatas.getString(timeInRequestOfflineData11.getColumnIndex(DatabaseHelper.COL10));
//
//
//                            if (GetType.equals(DatabaseHelper.TYPE_MANUAl_TIME_IN))
//                            {
//                                Toast.makeText(context, "Sync1", Toast.LENGTH_SHORT).show();
//                                dbHelper.updateStatus(ID, "true");
//                            }
//
//
//
//                            else if (GetType.equals(DatabaseHelper.TYPE_MANUAl_TIME_OUT)) {
//
//                                Toast.makeText(context, "Sync2", Toast.LENGTH_SHORT).show();
//                                dbHelper.updateStatus(ID, "true");
//                            }
//
//
//
//                            if (GetType.equals(DatabaseHelper.TYPE_MANUAl_TIME_REQUEST)) {
//
//                                Toast.makeText(context, "Syncing3..!!", Toast.LENGTH_SHORT).show();
//                                dbHelper.updateStatus(ID, "true");
//
//                                //API hit for request status
//                            }
//
//
//
//                        }
//
//                        while (timeInRequestOfflineData11.moveToLast());
//////
//                    }
//                    timeInRequestOfflineDatas.close();
//                }
//
//
//
//
//
//
//
//
                request_resultss = true;


                Cursor timeInRequestOfflineLeaveDatass = dbHelper.getOfflineLeaveData();

                Cursor timeInRequestOfflineData1 = dbHelper.getFilteredOfflineLeaveData(DatabaseHelper.COL1);

                if (timeInRequestOfflineData1.getCount() > 0)
                {
                    Toast.makeText(context, "No Leave Data For Sync..!!", Toast.LENGTH_SHORT).show();
                    return;
                }


                else {

                    Log.e("timeInRequestOffline", "COUNT >>>>> " + timeInRequestOfflineLeaveDatass.getColumnCount());
                    if (timeInRequestOfflineLeaveDatass.moveToFirst()) {
                        do {


                            //TODO Make Server Manual Time In Request Here
                            final int IDS = timeInRequestOfflineLeaveDatass.getInt(timeInRequestOfflineLeaveDatass.getColumnIndex(DatabaseHelper.COL1));
                            String COL2 = timeInRequestOfflineLeaveDatass.getString(timeInRequestOfflineLeaveDatass.getColumnIndex(DatabaseHelper.COL222));
//                            String GetType = timeInRequestOfflineDatas.getString(timeInRequestOfflineData.getColumnIndex(DatabaseHelper.COL10));


                            if (request_resultss) {

                                Toast.makeText(context, "Syncing Leave Data..!!", Toast.LENGTH_SHORT).show();

                                //API hit for request status

                                dbHelper.updateLeaveStatus(IDS, "true");
                            }






                            Log.e("timeInRequestOfflinessssss1", "data>>>> " + COL2);
                        } while (timeInRequestOfflineLeaveDatass.moveToNext());
                    }
                    timeInRequestOfflineLeaveDatass.close();
                }


















            }


















            else
            {
                Log.e("NetworkChangeRerciver", "Internet Is Off");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
//
//    private void GetprofileParams(final Context context) {
//
//if (urls2==null)
//{
//
//}
//
//else
//{
//    client = new OkHttpClient.Builder().connectTimeout(Long.parseLong(urls2), TimeUnit.SECONDS).writeTimeout(Long.parseLong(urls2), TimeUnit.SECONDS).readTimeout(Long.parseLong(urls2), TimeUnit.SECONDS).build();
//
//    final String password = passwordsave;
//
//    RequestBody formBody = new FormBody.Builder().add("IMEI", IMEI).add("Password", password).build();
//
//    final Request request = new Request.Builder()
//            .url(urls + "register?IMEI=" + IMEI + "&Password=" + password)
//            .post(formBody).addHeader("Cache-Control", "no-cache")
//            .addHeader("Postman-Token", "7e2aa8d6-b994-49cd-bc85-a46f9e35ad1f")
//            .build();
//
//    Call call = client.newCall(request);
//    call.enqueue(new Callback() {
//        @Override
//        public void onFailure(Call call, IOException e) {
//
////                    runOnUiThread(new Runnable() {
////                        @Override
////                        public void run() {
////                            Utilss.hideSweetLoader(pDialog);
////                        }
////                    });
//
//            Log.e("HttpService", "onFailure() Request was: " + e.getMessage());
//            e.printStackTrace();
//
//
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//
////                            dialogHeader_3 = new MaterialStyledDialog.Builder(LoginActivity.this)
////                                    .setHeaderDrawable(R.drawable.header)
////                                    .setIcon(new IconicsDrawable(LoginActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
////                                    .withDialogAnimation(true)
////                                    .setTitle("Error Message")
////                                    .setDescription("Cant Connect To Server1")
////                                    .setPositiveText("OK")
////                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
////                                        @Override
////                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////
////
////                                            _passwordText.setText("");
////                                            _loginButton.setEnabled(true);
////                                        }
////                                    });
////                            dialogHeader_3.show();
//                }
//            });
//
//
//        }
//
//        @Override
//        public void onResponse(Call call, Response response) throws IOException {
//
//
////                    runOnUiThread(new Runnable() {
////                        @Override
////                        public void run() {
////                            Utilss.hideSweetLoader(pDialog);
////                        }
////                    });
//
//
//            String responses = response.body().string();
//            Log.e("response", "onResponse(): " + responses);
//
//
//            if (response.code() == 404) {
////                        runOnUiThread(new Runnable() {
////                            @Override
////                            public void run() {
////                                Utilss.hideSweetLoader(pDialog);
////                            }
////                        });
//
//
////                        runOnUiThread(new Runnable() {
////                            @Override
////                            public void run() {
////
////                                dialogHeader_3 = new MaterialStyledDialog.Builder(LoginActivity.this)
////                                        .setHeaderDrawable(R.drawable.header)
////                                        .setIcon(new IconicsDrawable(LoginActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
////                                        .withDialogAnimation(true)
////                                        .setTitle("Error Message")
////                                        .setDescription("Incorrect Url")
////                                        .setPositiveText("OK")
////                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
////                                            @Override
////                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//////
//////                                                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
////
////                                                _passwordText.setText("");
////
////                                            }
////                                        });
////                                dialogHeader_3.show();
//
//
//            } else if (response.code() == 500) {
////                        runOnUiThread(new Runnable() {
////                            @Override
////                            public void run() {
////                                Utilss.hideSweetLoader(pDialog);
////                            }
////                        });
//
//                try {
//                    JSONObject jsonObject = new JSONObject(responses);
//                    value2 = jsonObject.getString("exceptionMessage");
//                    Log.d("Exception_Message", value2);
//
//
//                    if (jsonObject.getString("exceptionMessage").equals("No user mapped with current token!")) {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//
////                                        Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
////                                        startActivity(intent);
//                            }
//                        });
//                    } else {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//
//
////                                                dialogHeader_3 = new MaterialStyledDialog.Builder(LoginActivity.this)
////                                                        .setHeaderDrawable(R.drawable.header)
////                                                        .setIcon(new IconicsDrawable(LoginActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
////                                                        .withDialogAnimation(true)
////                                                        .setTitle("Error Message")
////                                                        .setDescription(value2)
////                                                        .setPositiveText("OK")
////                                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
////                                                            @Override
////                                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////
////                                                                _passwordText.setText("");
////
////                                                            }
////                                                        });
////                                                dialogHeader_3.show();
//
//
//                                    }
//                                });
//                            }
//                        });
//                    }
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            } else {
//
//                try {
//                    JSONObject jsonObject = new JSONObject(responses);
//
//                    String value6 = jsonObject.getString("imei");
//                    value89 = jsonObject.getString("isApprover");
//
//
//                    Log.d("Value123", value89);
//
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//                SendTokenParamss(context);
//
//
//            }
//            //  }
//
//
//        }
//    });
//}
//
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    private  void SendTokenParamss(Context context)
//    {
//        client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
//                .writeTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).build();
//
//        String password= passwordsave;
//        String IMEIS= IMEI;
//
//        if (password==null && IMEIS==null )
//        {
////            Toast.makeText(, "", Toast.LENGTH_SHORT).show();
//
//            Log.d("ItsNull" , "Its Null But Still MOving");
//        }
//
//
//        else {
//            RequestBody formBody = new FormBody.Builder().add("UserName", IMEI).add("Password", password)
//                    .add("grant_type", "password").build();
//
//
//            if (urls==null)
//            {
//
//
//                try
//                {
//                    final DBHelper objDBHelper = new DBHelper(context);
////                    objDBHelper = new DBHelper(context);
//                    String query = "Select * from LogHistory";
//
//                    Cursor cursor = objDBHelper.getCursor(query);
//                    if (cursor.getCount() > 0)
//                    {
//                        if (cursor.moveToFirst())
//                        {
//                            do
//                            {
//                                urls = cursor.getString(cursor.getColumnIndex("TISURL"));
//                                Log.d("TISURL" , urls);
//
//
//                                if (urls!=null)
//                                {
//                                    Request request = new Request.Builder()
//                                            .url(urls +"token")
//                                            .post(formBody).addHeader("Content-Type", "application/x-www-form-urlencoded")
//                                            .addHeader("Cache-Control", "no-cache")
//                                            .addHeader("Postman-Token", "510b77b4-3dc4-4ab1-9c61-08ec67b82873")
//                                            .build();
//
//                                    Call call = client.newCall(request);
//                                    call.enqueue(new Callback() {
//                                        @Override
//                                        public void onFailure(Call call, IOException e) {
//
//
//                                            Log.e("HttpService", "onFailure() Request was: " + e.getMessage());
//                                            e.printStackTrace();
//
//
//                                            runOnUiThread(new Runnable() {
//                                                @Override
//                                                public void run() {
//
//
//                                                }
//                                            });
//
//
//                                        }
//
//                                        @Override
//                                        public void onResponse(Call call, Response response) throws IOException {
//
//                                            responses = response.body().string();
//                                            Log.e("responsetoken", responses);
//
//
//                                            if (response.code() == 404) {
//
//
//                                            } else if (response.code() == 500) {
//
//
//                                                try {
//                                                    JSONObject jsonObject = new JSONObject(responses);
//                                                    value2 = jsonObject.getString("exceptionMessage");
//                                                    Log.d("Exception_Message", value2);
//
//
//                                                    if (jsonObject.getString("exceptionMessage").equals("No user mapped with current token!")) {
//                                                        runOnUiThread(new Runnable() {
//                                                            @Override
//                                                            public void run() {
//
////                                        Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
////                                        startActivity(intent);
//                                                            }
//                                                        });
//                                                    } else {
//
//                                                    }
//
//
////                            runOnUiThread(new Runnable() {
////                                @Override
////                                public void run() {
////
////                                    dialogHeader_3 = new MaterialStyledDialog.Builder(LoginActivity.this)
////                                            .setHeaderDrawable(R.drawable.header)
////                                            .setIcon(new IconicsDrawable(LoginActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
////                                            .withDialogAnimation(true)
////                                            .setTitle("Error Message")
////                                            .setDescription(value2)
////                                            .setPositiveText("OK")
////                                            .onPositive(new MaterialDialog.SingleButtonCallback() {
////                                                @Override
////                                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//////
//////                                                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
////
////                                                    _passwordText.setText("");
////                                                }
////                                            });
////                                    dialogHeader_3.show();
////                                }
////                            });
//
//
//                                                } catch (JSONException e) {
//                                                    e.printStackTrace();
//                                                }
//                                            } else {
//
//
//                                                try {
//                                                    JSONObject jsonObject = new JSONObject(responses);
//                                                    value345 = jsonObject.getString("access_token");
//                                                    Log.d("token12345", value345);
//
//                                                } catch (JSONException e) {
//                                                    e.printStackTrace();
//                                                }
//                                            }
//
//
//                                        }
//
//
//                                    });
//                                }
//
//                            }
//
//                            while (cursor.moveToNext());
//                        }
//                    }
//
//
//                }
//
//                catch (Exception ex)
//                {
////                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
//                }
//            }
//
//
//
//
//
//
//
//
//
//            else
//            {
//                Request request = new Request.Builder()
//                        .url(urls +"token")
//                        .post(formBody).addHeader("Content-Type", "application/x-www-form-urlencoded")
//                        .addHeader("Cache-Control", "no-cache")
//                        .addHeader("Postman-Token", "510b77b4-3dc4-4ab1-9c61-08ec67b82873")
//                        .build();
//
//                Call call = client.newCall(request);
//                call.enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//
//
//                        Log.e("HttpService", "onFailure() Request was: " + e.getMessage());
//                        e.printStackTrace();
//
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//
//
//                            }
//                        });
//
//
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//
//                        responses = response.body().string();
//                        Log.e("responsetoken", responses);
//
//
//                        if (response.code() == 404) {
//
//
//                        } else if (response.code() == 500) {
//
//
//                            try {
//                                JSONObject jsonObject = new JSONObject(responses);
//                                value2 = jsonObject.getString("exceptionMessage");
//                                Log.d("Exception_Message", value2);
//
//
//                                if (jsonObject.getString("exceptionMessage").equals("No user mapped with current token!")) {
//                                    runOnUiThread(new Runnable() {
//                                        @Override
//                                        public void run() {
//
////                                        Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
////                                        startActivity(intent);
//                                        }
//                                    });
//                                } else {
//
//                                }
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        } else {
//
//
//                            try {
//                                JSONObject jsonObject = new JSONObject(responses);
//                                value345 = jsonObject.getString("access_token");
//                                Log.d("token123", value345);
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//
//
//                });
    //   }

    // }
    //  }






    // }
    //}






































    private boolean isOnline(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            //should check null because in airplane mode it will be null
            return (netInfo != null && netInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }
}

















//package com.example.tisapp.Receivers;
//
//import android.annotation.SuppressLint;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.database.Cursor;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.example.tisapp.DBHelper;
//import com.example.tisapp.DatabaseHelper;
//import com.example.tisapp.Login.LoginActivity;
//import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.util.concurrent.TimeUnit;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.FormBody;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//
//import static com.example.tisapp.Login.LoginActivity.IMEI;
//import static com.example.tisapp.Login.LoginActivity.passwordsave;










//import static com.example.tisapp.Settings.Settings_Activity.urls;
//import static com.example.tisapp.Settings.Settings_Activity.urls2;
//import static com.google.android.gms.internal.zzahn.runOnUiThread;
//
//
//public class NetworkChangeReceiver extends BroadcastReceiver
//{
//
//    MaterialStyledDialog.Builder dialogHeader_3;
//    String responses,value2;
//    public static String value345,value89;
//    OkHttpClient client;
//    boolean request_result,request_resultss;
//
//    @SuppressLint("LongLogTag")
//    @Override
//    public void onReceive(Context context, Intent intent)
//    {
//        try
//        {
//            if (isOnline(context))
//            {
////                GetprofileParams(context);
////                SendTokenParamss(context);
//                Log.e("NetworkChangeRerciver", "Online Connect Intenet ");
//
//                final DatabaseHelper dbHelper = new DatabaseHelper(context);
//                request_result = true;
//
//                Cursor timeInRequestOfflineDatas = dbHelper.getOfflineData();
//
//                Cursor timeInRequestOfflineData11 = dbHelper.getFilteredOfflineData(DatabaseHelper.TYPE_MANUAl_TIME_IN);
//                Cursor timeInRequestOfflineData2 = dbHelper.getFilteredOfflineData(DatabaseHelper.TYPE_MANUAl_TIME_OUT);
//                Cursor timeInRequestOfflineData3 = dbHelper.getFilteredOfflineData(DatabaseHelper.TYPE_MANUAl_TIME_REQUEST);
//
//                if (timeInRequestOfflineData11.getCount() > 0 && timeInRequestOfflineData2.getCount() > 0  && timeInRequestOfflineData3.getCount() > 0  ) {
//                    Toast.makeText(context, "No Data For Sync..!!", Toast.LENGTH_SHORT).show();
//                    return;
//                } else {
//
//                    Log.e("timeInRequestOffline", "COUNT >>>>> " + timeInRequestOfflineData11.getColumnCount());
//
//
//
//
//                    if (timeInRequestOfflineDatas.moveToFirst()) {
//
//
//                        while (timeInRequestOfflineData2.moveToNext())
//                        {
//
//                            final int ID = timeInRequestOfflineData2.getInt(timeInRequestOfflineData2.getColumnIndex(DatabaseHelper.COL1));
////                            //TODO Make Server Manual Time In Request Here
//                            String COL2 = timeInRequestOfflineDatas.getString(timeInRequestOfflineData11.getColumnIndex(DatabaseHelper.COL11));
//                            String GetType = timeInRequestOfflineDatas.getString(timeInRequestOfflineData11.getColumnIndex(DatabaseHelper.COL10));
//
//
//                            if (GetType.equals(DatabaseHelper.TYPE_MANUAl_TIME_IN))
//                            {
//                                dbHelper.updateStatus(ID, "true");
//                            }
//
//
//
//                            else if (GetType.equals(DatabaseHelper.TYPE_MANUAl_TIME_OUT)) {
//
//
//                                dbHelper.updateStatus(ID, "true");
//                            }
//
//
//
//                            if (GetType.equals(DatabaseHelper.TYPE_MANUAl_TIME_REQUEST)) {
//
//                                Toast.makeText(context, "Syncing3..!!", Toast.LENGTH_SHORT).show();
//                                dbHelper.updateStatus(ID, "true");
//
//                                //API hit for request status
//                            }
//
//
//
//                        }
//
//                        while (timeInRequestOfflineData11.moveToLast());
//////
//                    }
//                    timeInRequestOfflineDatas.close();
//                }
//
//
//
//
//
//
//
//
//                request_resultss = true;
//
//
//                Cursor timeInRequestOfflineLeaveDatass = dbHelper.getOfflineLeaveData();
//
//                Cursor timeInRequestOfflineData1 = dbHelper.getFilteredOfflineLeaveData(DatabaseHelper.COL1);
//
//                if (timeInRequestOfflineData1.getCount() > 0)
//                {
//                    Toast.makeText(context, "No Leave Data For Sync..!!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//
//                else {
//
//                    Log.e("timeInRequestOffline", "COUNT >>>>> " + timeInRequestOfflineLeaveDatass.getColumnCount());
//                    if (timeInRequestOfflineLeaveDatass.moveToFirst()) {
//                        do {
//
//
//                            //TODO Make Server Manual Time In Request Here
//                            final int IDS = timeInRequestOfflineLeaveDatass.getInt(timeInRequestOfflineLeaveDatass.getColumnIndex(DatabaseHelper.COL1));
//                            String COL2 = timeInRequestOfflineLeaveDatass.getString(timeInRequestOfflineLeaveDatass.getColumnIndex(DatabaseHelper.COL222));
////                            String GetType = timeInRequestOfflineDatas.getString(timeInRequestOfflineData.getColumnIndex(DatabaseHelper.COL10));
//
//
//                            if (request_resultss) {
//
//                                Toast.makeText(context, "Syncing Leave Data..!!", Toast.LENGTH_SHORT).show();
//
//                                //API hit for request status
//
//                                dbHelper.updateLeaveStatus(IDS, "true");
//                            }
//
//
//
//
//
//
//                            Log.e("timeInRequestOfflinessssss1", "data>>>> " + COL2);
//                        } while (timeInRequestOfflineLeaveDatass.moveToNext());
//                    }
//                    timeInRequestOfflineLeaveDatass.close();
//                }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//            }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//            else
//            {
//                Log.e("NetworkChangeRerciver", "Internet Is Off");
//            }
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//    }
////
////    private void GetprofileParams(final Context context) {
////
////if (urls2==null)
////{
////
////}
////
////else
////{
////    client = new OkHttpClient.Builder().connectTimeout(Long.parseLong(urls2), TimeUnit.SECONDS).writeTimeout(Long.parseLong(urls2), TimeUnit.SECONDS).readTimeout(Long.parseLong(urls2), TimeUnit.SECONDS).build();
////
////    final String password = passwordsave;
////
////    RequestBody formBody = new FormBody.Builder().add("IMEI", IMEI).add("Password", password).build();
////
////    final Request request = new Request.Builder()
////            .url(urls + "register?IMEI=" + IMEI + "&Password=" + password)
////            .post(formBody).addHeader("Cache-Control", "no-cache")
////            .addHeader("Postman-Token", "7e2aa8d6-b994-49cd-bc85-a46f9e35ad1f")
////            .build();
////
////    Call call = client.newCall(request);
////    call.enqueue(new Callback() {
////        @Override
////        public void onFailure(Call call, IOException e) {
////
//////                    runOnUiThread(new Runnable() {
//////                        @Override
//////                        public void run() {
//////                            Utilss.hideSweetLoader(pDialog);
//////                        }
//////                    });
////
////            Log.e("HttpService", "onFailure() Request was: " + e.getMessage());
////            e.printStackTrace();
////
////
////            runOnUiThread(new Runnable() {
////                @Override
////                public void run() {
////
//////                            dialogHeader_3 = new MaterialStyledDialog.Builder(LoginActivity.this)
//////                                    .setHeaderDrawable(R.drawable.header)
//////                                    .setIcon(new IconicsDrawable(LoginActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//////                                    .withDialogAnimation(true)
//////                                    .setTitle("Error Message")
//////                                    .setDescription("Cant Connect To Server1")
//////                                    .setPositiveText("OK")
//////                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
//////                                        @Override
//////                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//////
//////
//////                                            _passwordText.setText("");
//////                                            _loginButton.setEnabled(true);
//////                                        }
//////                                    });
//////                            dialogHeader_3.show();
////                }
////            });
////
////
////        }
////
////        @Override
////        public void onResponse(Call call, Response response) throws IOException {
////
////
//////                    runOnUiThread(new Runnable() {
//////                        @Override
//////                        public void run() {
//////                            Utilss.hideSweetLoader(pDialog);
//////                        }
//////                    });
////
////
////            String responses = response.body().string();
////            Log.e("response", "onResponse(): " + responses);
////
////
////            if (response.code() == 404) {
//////                        runOnUiThread(new Runnable() {
//////                            @Override
//////                            public void run() {
//////                                Utilss.hideSweetLoader(pDialog);
//////                            }
//////                        });
////
////
//////                        runOnUiThread(new Runnable() {
//////                            @Override
//////                            public void run() {
//////
//////                                dialogHeader_3 = new MaterialStyledDialog.Builder(LoginActivity.this)
//////                                        .setHeaderDrawable(R.drawable.header)
//////                                        .setIcon(new IconicsDrawable(LoginActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//////                                        .withDialogAnimation(true)
//////                                        .setTitle("Error Message")
//////                                        .setDescription("Incorrect Url")
//////                                        .setPositiveText("OK")
//////                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
//////                                            @Override
//////                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////////
////////                                                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
//////
//////                                                _passwordText.setText("");
//////
//////                                            }
//////                                        });
//////                                dialogHeader_3.show();
////
////
////            } else if (response.code() == 500) {
//////                        runOnUiThread(new Runnable() {
//////                            @Override
//////                            public void run() {
//////                                Utilss.hideSweetLoader(pDialog);
//////                            }
//////                        });
////
////                try {
////                    JSONObject jsonObject = new JSONObject(responses);
////                    value2 = jsonObject.getString("exceptionMessage");
////                    Log.d("Exception_Message", value2);
////
////
////                    if (jsonObject.getString("exceptionMessage").equals("No user mapped with current token!")) {
////                        runOnUiThread(new Runnable() {
////                            @Override
////                            public void run() {
////
//////                                        Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
//////                                        startActivity(intent);
////                            }
////                        });
////                    } else {
////                        runOnUiThread(new Runnable() {
////                            @Override
////                            public void run() {
////
////                                runOnUiThread(new Runnable() {
////                                    @Override
////                                    public void run() {
////
////
//////                                                dialogHeader_3 = new MaterialStyledDialog.Builder(LoginActivity.this)
//////                                                        .setHeaderDrawable(R.drawable.header)
//////                                                        .setIcon(new IconicsDrawable(LoginActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//////                                                        .withDialogAnimation(true)
//////                                                        .setTitle("Error Message")
//////                                                        .setDescription(value2)
//////                                                        .setPositiveText("OK")
//////                                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
//////                                                            @Override
//////                                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//////
//////                                                                _passwordText.setText("");
//////
//////                                                            }
//////                                                        });
//////                                                dialogHeader_3.show();
////
////
////                                    }
////                                });
////                            }
////                        });
////                    }
////
////
////                } catch (JSONException e) {
////                    e.printStackTrace();
////                }
////            } else {
////
////                try {
////                    JSONObject jsonObject = new JSONObject(responses);
////
////                    String value6 = jsonObject.getString("imei");
////                    value89 = jsonObject.getString("isApprover");
////
////
////                    Log.d("Value123", value89);
////
////
////
////                } catch (JSONException e) {
////                    e.printStackTrace();
////                }
////
////
////                SendTokenParamss(context);
////
////
////            }
////            //  }
////
////
////        }
////    });
////}
////
////    }
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////    private  void SendTokenParamss(Context context)
////    {
////        client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
////                .writeTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).build();
////
////        String password= passwordsave;
////        String IMEIS= IMEI;
////
////        if (password==null && IMEIS==null )
////        {
//////            Toast.makeText(, "", Toast.LENGTH_SHORT).show();
////
////            Log.d("ItsNull" , "Its Null But Still MOving");
////        }
////
////
////        else {
////            RequestBody formBody = new FormBody.Builder().add("UserName", IMEI).add("Password", password)
////                    .add("grant_type", "password").build();
////
////
////            if (urls==null)
////            {
////
////
////                try
////                {
////                    final DBHelper objDBHelper = new DBHelper(context);
//////                    objDBHelper = new DBHelper(context);
////                    String query = "Select * from LogHistory";
////
////                    Cursor cursor = objDBHelper.getCursor(query);
////                    if (cursor.getCount() > 0)
////                    {
////                        if (cursor.moveToFirst())
////                        {
////                            do
////                            {
////                                urls = cursor.getString(cursor.getColumnIndex("TISURL"));
////                                Log.d("TISURL" , urls);
////
////
////                                if (urls!=null)
////                                {
////                                    Request request = new Request.Builder()
////                                            .url(urls +"token")
////                                            .post(formBody).addHeader("Content-Type", "application/x-www-form-urlencoded")
////                                            .addHeader("Cache-Control", "no-cache")
////                                            .addHeader("Postman-Token", "510b77b4-3dc4-4ab1-9c61-08ec67b82873")
////                                            .build();
////
////                                    Call call = client.newCall(request);
////                                    call.enqueue(new Callback() {
////                                        @Override
////                                        public void onFailure(Call call, IOException e) {
////
////
////                                            Log.e("HttpService", "onFailure() Request was: " + e.getMessage());
////                                            e.printStackTrace();
////
////
////                                            runOnUiThread(new Runnable() {
////                                                @Override
////                                                public void run() {
////
////
////                                                }
////                                            });
////
////
////                                        }
////
////                                        @Override
////                                        public void onResponse(Call call, Response response) throws IOException {
////
////                                            responses = response.body().string();
////                                            Log.e("responsetoken", responses);
////
////
////                                            if (response.code() == 404) {
////
////
////                                            } else if (response.code() == 500) {
////
////
////                                                try {
////                                                    JSONObject jsonObject = new JSONObject(responses);
////                                                    value2 = jsonObject.getString("exceptionMessage");
////                                                    Log.d("Exception_Message", value2);
////
////
////                                                    if (jsonObject.getString("exceptionMessage").equals("No user mapped with current token!")) {
////                                                        runOnUiThread(new Runnable() {
////                                                            @Override
////                                                            public void run() {
////
//////                                        Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
//////                                        startActivity(intent);
////                                                            }
////                                                        });
////                                                    } else {
////
////                                                    }
////
////
//////                            runOnUiThread(new Runnable() {
//////                                @Override
//////                                public void run() {
//////
//////                                    dialogHeader_3 = new MaterialStyledDialog.Builder(LoginActivity.this)
//////                                            .setHeaderDrawable(R.drawable.header)
//////                                            .setIcon(new IconicsDrawable(LoginActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//////                                            .withDialogAnimation(true)
//////                                            .setTitle("Error Message")
//////                                            .setDescription(value2)
//////                                            .setPositiveText("OK")
//////                                            .onPositive(new MaterialDialog.SingleButtonCallback() {
//////                                                @Override
//////                                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////////
////////                                                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
//////
//////                                                    _passwordText.setText("");
//////                                                }
//////                                            });
//////                                    dialogHeader_3.show();
//////                                }
//////                            });
////
////
////                                                } catch (JSONException e) {
////                                                    e.printStackTrace();
////                                                }
////                                            } else {
////
////
////                                                try {
////                                                    JSONObject jsonObject = new JSONObject(responses);
////                                                    value345 = jsonObject.getString("access_token");
////                                                    Log.d("token12345", value345);
////
////                                                } catch (JSONException e) {
////                                                    e.printStackTrace();
////                                                }
////                                            }
////
////
////                                        }
////
////
////                                    });
////                                }
////
////                            }
////
////                            while (cursor.moveToNext());
////                        }
////                    }
////
////
////                }
////
////                catch (Exception ex)
////                {
//////                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
////                }
////            }
////
////
////
////
////
////
////
////
////
////            else
////            {
////                Request request = new Request.Builder()
////                        .url(urls +"token")
////                        .post(formBody).addHeader("Content-Type", "application/x-www-form-urlencoded")
////                        .addHeader("Cache-Control", "no-cache")
////                        .addHeader("Postman-Token", "510b77b4-3dc4-4ab1-9c61-08ec67b82873")
////                        .build();
////
////                Call call = client.newCall(request);
////                call.enqueue(new Callback() {
////                    @Override
////                    public void onFailure(Call call, IOException e) {
////
////
////                        Log.e("HttpService", "onFailure() Request was: " + e.getMessage());
////                        e.printStackTrace();
////
////
////                        runOnUiThread(new Runnable() {
////                            @Override
////                            public void run() {
////
////
////                            }
////                        });
////
////
////                    }
////
////                    @Override
////                    public void onResponse(Call call, Response response) throws IOException {
////
////                        responses = response.body().string();
////                        Log.e("responsetoken", responses);
////
////
////                        if (response.code() == 404) {
////
////
////                        } else if (response.code() == 500) {
////
////
////                            try {
////                                JSONObject jsonObject = new JSONObject(responses);
////                                value2 = jsonObject.getString("exceptionMessage");
////                                Log.d("Exception_Message", value2);
////
////
////                                if (jsonObject.getString("exceptionMessage").equals("No user mapped with current token!")) {
////                                    runOnUiThread(new Runnable() {
////                                        @Override
////                                        public void run() {
////
//////                                        Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
//////                                        startActivity(intent);
////                                        }
////                                    });
////                                } else {
////
////                                }
////
////                            } catch (JSONException e) {
////                                e.printStackTrace();
////                            }
////                        } else {
////
////
////                            try {
////                                JSONObject jsonObject = new JSONObject(responses);
////                                value345 = jsonObject.getString("access_token");
////                                Log.d("token123", value345);
////
////                            } catch (JSONException e) {
////                                e.printStackTrace();
////                            }
////                        }
////                    }
////
////
////                });
//    //   }
//
//    // }
//    //  }
//
//
//
//
//
//
//    // }
//    //}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    private boolean isOnline(Context context) {
//        try {
//            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo netInfo = cm.getActiveNetworkInfo();
//            //should check null because in airplane mode it will be null
//            return (netInfo != null && netInfo.isConnected());
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//}
