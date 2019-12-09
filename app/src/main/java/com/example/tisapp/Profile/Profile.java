package com.example.tisapp.Profile;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.example.tisapp.BaseActivity;
import com.example.tisapp.DatabaseHelper;
import com.example.tisapp.Login.LoginActivity;
import com.example.tisapp.R;
import com.example.tisapp.Utilss;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.rey.material.widget.ProgressView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.tisapp.Settings.Settings_Activity.urls;
import static com.example.tisapp.Settings.Settings_Activity.urls2;


public class Profile extends BaseActivity {

    TextView tvUserNameProfile, tvUsernameProfile,tvDesigProfile, tvUserEmailProfile, tvUserSkypePr, tvUserContactProofilefile, tvUserNameHeader;

    Button btnEditPassword, btnLogout;
    OkHttpClient client;
    String value2, responses, abc, def, ghi, value4;

    DatabaseHelper objDBHelper2;

    EditText passwordInTextInputLayout, passwordInTextInputLayout2, passwordInTextInputLayout3;
    String path, path2;

    MaterialStyledDialog.Builder dialogHeader_3, dialogHeader_1;
    ProgressView circularProgressBar;

    com.mikhaellopez.circularimageview.CircularImageView ivUserProfiles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile2);
        objDBHelper2=new DatabaseHelper(this);


        tvUserNameProfile = (TextView) findViewById(R.id.tvUserNameProfile);
        tvDesigProfile = (TextView) findViewById(R.id.tvDesigProfile);
        tvUsernameProfile= (TextView) findViewById(R.id.tvUsernameProfile);
        tvUserEmailProfile = (TextView) findViewById(R.id.tvUserEmailProfile);
        tvUserSkypePr = (TextView) findViewById(R.id.tvUserSkypePr);
        tvUserContactProofilefile = (TextView) findViewById(R.id.tvUserContactProofilefile);


        btnEditPassword = (Button) findViewById(R.id.btnEditPassword);
        btnLogout = (Button) findViewById(R.id.btnLogout);

        ivUserProfiles = (com.mikhaellopez.circularimageview.CircularImageView) findViewById(R.id.ivUserProfile);

        path2 = getIntent().getStringExtra("mImageUri");
        path = getIntent().getStringExtra("mImageUriCamera");

        if (path2 != null) {
            Glide.with(Profile.this).load(path2).asBitmap().into(ivUserProfiles);
            Log.d("Path_Is2", path2);
        } else if (path != null) {
            Glide.with(Profile.this).load(path).asBitmap().into(ivUserProfiles);
            Log.d("Path_Is", path);

        } else {
            ivUserProfiles.setImageResource(R.mipmap.image_face);
        }

        client = new OkHttpClient.Builder().connectTimeout(Long.parseLong(urls2), TimeUnit.SECONDS)
                .writeTimeout(Long.parseLong(urls2), TimeUnit.SECONDS).readTimeout(Long.parseLong(urls2), TimeUnit.SECONDS).build();


        btnEditPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert();

            }
        });


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert2();
                dialogHeader_3.show();
            }
        });

        if (isNetAvailable())
        {
            GetProfileParams();
        }

        else
        {

//            dialogHeader_3 = new MaterialStyledDialog.Builder(Profile.this)
//                    .setHeaderDrawable(R.drawable.header)
//                    .setIcon(new IconicsDrawable(Profile.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                    .withDialogAnimation(true)
//                    .setTitle("Error Message")
//                    .setDescription("Network Not Connected..!!")
//                    .setPositiveText("OK")
//                    .onPositive(new MaterialDialog.SingleButtonCallback() {
//                        @Override
//                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                            startActivity(new Intent(Profile.this, LoginActivity.class));
//                            finish();
//
//
////                            _passwordText.setText("");
////                                        _loginButton.setEnabled(true);
//                        }
//                    });
//            dialogHeader_3.show();


            Cursor timeInRequestOfflineData = objDBHelper2.getDataProfile();


//            String data= objDBHelper2.getDataProfile();
//            Toast.makeText(getApplicationContext(),data,Toast.LENGTH_SHORT).show();


            if (timeInRequestOfflineData.moveToLast())
            {

                String COL13 = timeInRequestOfflineData.getString(timeInRequestOfflineData.getColumnIndex(DatabaseHelper.COL13));

                String COL14 = timeInRequestOfflineData.getString(timeInRequestOfflineData.getColumnIndex(DatabaseHelper.COL14));
                String COL15 = timeInRequestOfflineData.getString(timeInRequestOfflineData.getColumnIndex(DatabaseHelper.COL15));
                String COL16 = timeInRequestOfflineData.getString(timeInRequestOfflineData.getColumnIndex(DatabaseHelper.COL16));
                String COL17 = timeInRequestOfflineData.getString(timeInRequestOfflineData.getColumnIndex(DatabaseHelper.COL17));


                tvUserNameProfile.setText(COL14);
                tvDesigProfile.setText(COL15);
                tvUserEmailProfile.setText(COL14);
                tvUserSkypePr.setText(COL16);
                tvUserContactProofilefile.setText(COL13);
                tvUsernameProfile.setText(COL15);

                Log.d("IsApprover" , ""+COL17);


                timeInRequestOfflineData.close();

            }







        }
    }

    private void alert2() {
        dialogHeader_3 = new MaterialStyledDialog.Builder(context)
                .setHeaderDrawable(R.drawable.header)
                .setIcon(new IconicsDrawable(context).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                .withDialogAnimation(true)
                .setTitle("LogOut?")
                .setDescription("Would You Like To Be Logged Out ?")
                .setPositiveText("YES")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {


                        if (urls == null || urls.equals("")) {
                            dialogHeader_3 = new MaterialStyledDialog.Builder(Profile.this)
                                    .setHeaderDrawable(R.drawable.header)
                                    .setIcon(new IconicsDrawable(Profile.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                    .withDialogAnimation(true)
                                    .setTitle("Error Message")
                                    .setDescription("Url Can't Be Empty")
                                    .setPositiveText("OK")
                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                                            startActivity(new Intent(Profile.this, LoginActivity.class));
                                            finish();


//                            _passwordText.setText("");
//                                        _loginButton.setEnabled(true);
                                        }
                                    });
                            dialogHeader_3.show();

                        } else {
//                            pDialog = Utilss.showSweetLoader(Profile.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");

//                            final Request request = new Request.Builder()
//                                    .url(urls + "api/Account/Logout")
//                                    .get().addHeader("Cache-Control", "no-cache")
//                                    .addHeader("Authorization", " Bearer " + LoginActivity.value)
//                                    .addHeader("Postman-Token", "3a80b1ef-cb95-4422-abd6-a9fc593213f2")
//                                    .build();
//
//                            Call call = client.newCall(request);
//                            call.enqueue(new Callback() {
//                                @Override
//                                public void onFailure(Call call, IOException e) {
//                                    runOnUiThread(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            Utilss.hideSweetLoader(pDialog);
//                                        }
//                                    });
//
//                                    runOnUiThread(new Runnable() {
//                                        @Override
//                                        public void run() {
//
//                                            dialogHeader_3 = new MaterialStyledDialog.Builder(Profile.this)
//                                                    .setHeaderDrawable(R.drawable.header)
//                                                    .setIcon(new IconicsDrawable(Profile.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                                    .withDialogAnimation(true)
//                                                    .setTitle("Error Message")
//                                                    .setDescription("Cant Connect To Server")
//                                                    .setPositiveText("OK")
//                                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                                        @Override
//                                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
//                                                        {
////                                                            Intent intent = new Intent(Profile.this , LoginActivity.class);
////                                                            startActivity(intent);
//                                                        }
//                                                    });
//                                            dialogHeader_3.show();
//                                        }
//                                    });
//
//
//
//                                }
//
//
//                                @Override
//                                public void onResponse(Call call, Response response) throws IOException {
//                                    runOnUiThread(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            Utilss.hideSweetLoader(pDialog);
//                                        }
//                                    });
//
//                                    responses = response.toString();
//                                    Log.e("responseLogOut", responses);
//
//                                    if (response.code() == 500) {
//                                        runOnUiThread(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                Utilss.hideSweetLoader(pDialog);
//                                            }
//                                        });
//
//
//                                        runOnUiThread(new Runnable() {
//                                            @Override
//                                            public void run() {
//
//
//                                                dialogHeader_3 = new MaterialStyledDialog.Builder(context)
//                                                        .setHeaderDrawable(R.drawable.header)
//                                                        .setIcon(new IconicsDrawable(context).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                                        .withDialogAnimation(true)
//                                                        .setTitle("Error Message")
//                                                        .setDescription("Error In LogOut..")
//                                                        .setPositiveText("OK")
//                                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                                            @Override
//                                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                                                startActivity(new Intent(Profile.this, LoginActivity.class));
//                                                            }
//                                                        });
//                                                dialogHeader_3.show();
//
//                                            }
//                                        });
//                                    } else {
//
//                                        runOnUiThread(new Runnable() {
//                                            @Override
//                                            public void run() {
//
////                                            Toast.makeText(Profile.this, "Access Token Deleted..", Toast.LENGTH_SHORT).show();
//                                                Intent intent = new Intent(Profile.this, LoginActivity.class);
////                                            intent.putExtra("ki" , LoginActivity.urls);
//                                                startActivity(intent);
//                                            }
//                                        });
//                                    }
//                                }
//                            });



                            Intent intent = new Intent(Profile.this , LoginActivity.class);
                            startActivity(intent);

                        }

                    }

//                        else
//                        {
//
//                            dialogHeader_3 = new MaterialStyledDialog.Builder(context)
//                                    .setHeaderDrawable(R.drawable.header)
//                                    .setIcon(new IconicsDrawable(context).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                    .withDialogAnimation(true)
//                                    .setTitle("Error Message")
//                                    .setDescription("Network Required..!!")
//                                    .setPositiveText("OK")
//                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                        @Override
//                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
//                                        {
////                                            startActivity(new Intent(Profile.this , LoginActivity.class));
//                                        }
//                                    });
//                            dialogHeader_3.show();
//
                        }) .setNegativeText("Not now");;

                };
    //}

    public  boolean isNetAvailable()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo =connectivityManager.getActiveNetworkInfo();
        return networkInfo !=null && networkInfo.isConnected();
    }


    private void alert()
    {

        LayoutInflater inflater = (LayoutInflater) Profile.this.getSystemService(LAYOUT_INFLATER_SERVICE);
        final View layout = inflater.inflate(R.layout.dialog_change_password, (ViewGroup) findViewById(R.id.layout_root));


        final Vector<AlertDialog> listOfDialogs = new Vector<AlertDialog>();
        Button btnCancel = (Button) layout.findViewById(R.id.buttonCancel);
        Button btnOk = (Button) layout.findViewById(R.id.btnOk);

         final ProgressView circularProgressBar = (ProgressView) layout.findViewById(R.id.circular_progress);
         circularProgressBar.setVisibility(View.GONE);

         passwordInTextInputLayout = layout.findViewById(R.id.editOldPassword);
         passwordInTextInputLayout2 = layout.findViewById(R.id.editNewPassword);
         passwordInTextInputLayout3 = layout.findViewById(R.id.editNewPassword2);



        AlertDialog.Builder aDialog = new AlertDialog.Builder(Profile.this);
        aDialog.setView(layout);
        final AlertDialog ad = aDialog.create();
//            listOfDialogs.add(ad);
        ad.show();




        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                pDialog = Utilss.showSweetLoader(Profile.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");

                circularProgressBar.setVisibility(View.VISIBLE);
                circularProgressBar.start();


                abc = passwordInTextInputLayout.getText().toString();
                def = passwordInTextInputLayout2.getText().toString();
                ghi = passwordInTextInputLayout3.getText().toString();


                if (abc.equals("") && def.equals("") & ghi.equals(""))
                {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            Utilss.hideSweetLoader(pDialog);
                            circularProgressBar.setVisibility(View.GONE);
                            circularProgressBar.stop();
                        }
                    });

                    passwordInTextInputLayout.setError("Old Password Is Required");
                    passwordInTextInputLayout2.setError("New Password Is Required");
                    passwordInTextInputLayout3.setError(" Password Is Required");
                } else {






                    if (urls==null || urls.equals("")) {
                        dialogHeader_3 = new MaterialStyledDialog.Builder(Profile.this)
                                .setHeaderDrawable(R.drawable.header)
                                .setIcon(new IconicsDrawable(Profile.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                .withDialogAnimation(true)
                                .setTitle("Error Message")
                                .setDescription("Url Can't Be Empty")
                                .setPositiveText("OK")
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                                        startActivity(new Intent(Profile.this, LoginActivity.class));
                                        finish();


//                            _passwordText.setText("");
//                                        _loginButton.setEnabled(true);
                                    }
                                });
                        dialogHeader_3.show();

                    }


                    else {


                        RequestBody formBody = new FormBody.Builder().add("CurrentPwd", abc).add("NewPwd", def).add("ConfirmPwd", ghi).build();

                        final Request request = new Request.Builder()
                                .url(urls + "api/Account/ChangePassword?CurrentPwd=" + abc + "&NewPwd=" + def + "&ConfirmPwd=" + ghi)
                                .post(formBody).addHeader("Cache-Control", "no-cache")
                                .addHeader("Authorization", " Bearer " + LoginActivity.value)
                                .addHeader("Postman-Token", "68ad6dbb-06c7-4a35-8619-8931789fa7fb")
                                .build();


                        Call call = client.newCall(request);
                        call.enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
//                                    Utilss.hideSweetLoader(pDialog);
                                        circularProgressBar.setVisibility(View.GONE);
                                        circularProgressBar.stop();
                                    }
                                });


                                Log.e("HttpService", "onFailure() Request was: " + call);
                                e.printStackTrace();

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        dialogHeader_3 = new MaterialStyledDialog.Builder(Profile.this)
                                                .setHeaderDrawable(R.drawable.header)
                                                .setIcon(new IconicsDrawable(Profile.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                                .withDialogAnimation(true)
                                                .setTitle("Error Message")
                                                .setDescription("Cant Connect To Server")
                                                .setPositiveText("OK")
                                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                    @Override
                                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

//                                                        Intent intent = new Intent(Profile.this , LoginActivity.class);
//                                                        startActivity(intent);
                                                    }
                                                });
                                        dialogHeader_3.show();
                                    }
                                });


                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
//                                    Utilss.hideSweetLoader(pDialog);
                                        circularProgressBar.setVisibility(View.GONE);
                                        circularProgressBar.stop();
                                    }
                                });

                                if (response.code() == 500) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response.body().string());
                                        value4 = jsonObject.getString("exceptionMessage");
                                        Log.d("ValueResponse", value4);


                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {


                                                dialogHeader_3 = new MaterialStyledDialog.Builder(context)
                                                        .setHeaderDrawable(R.drawable.header)
                                                        .setIcon(new IconicsDrawable(context).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                                        .withDialogAnimation(true)
                                                        .setTitle("Error Message")
                                                        .setDescription(value4)
                                                        .setPositiveText("OK")
                                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                            @Override
                                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                                passwordInTextInputLayout.setText("");
                                                                passwordInTextInputLayout2.setText("");
                                                                passwordInTextInputLayout3.setText("");
                                                            }
                                                        });
                                                dialogHeader_3.show();

                                            }
                                        });

                                    } catch (JSONException e) {
                                        e.printStackTrace();


                                        responses = response.body().string();
                                        Log.e("response", "onResponse(): " + responses);

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {

                                                dialogHeader_3 = new MaterialStyledDialog.Builder(context)
                                                        .setHeaderDrawable(R.drawable.header)
                                                        .setIcon(new IconicsDrawable(context).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                                        .withDialogAnimation(true)
                                                        .setTitle("Response Message")
                                                        .setDescription(responses)
                                                        .setPositiveText("OK")
                                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                            @Override
                                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                                Intent intent = new Intent(Profile.this, LoginActivity.class);
                                                                startActivity(intent);
                                                                finish();
//                                                            Toast.makeText(Profile.this, "Accepted Successfully", Toast.LENGTH_SHORT).show();
//
//                                                            Log.d("Responsss" , responses);
                                                            }
                                                        });
                                            }
                                        });
                                        ad.cancel();
                                    }
                                } else {


                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            ad.cancel();


                                            dialogHeader_3 = new MaterialStyledDialog.Builder(Profile.this)
                                                    .setHeaderDrawable(R.drawable.header)
                                                    .setIcon(new IconicsDrawable(Profile.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                                    .withDialogAnimation(true)
                                                    .setTitle("Confirmation Message")
                                                    .setDescription("Password Changed")
                                                    .setPositiveText("OK")
                                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                        @Override
                                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                                    input_date5.setText("");


                                                            Intent intent = new Intent(Profile.this, LoginActivity.class);
                                                            startActivity(intent);
                                                            finish();

//                                                        input_date2.setText("");
//                                                        from_date.setText("");
//                                                        to_date.setText("");
                                                        }
                                                    });
                                            dialogHeader_3.show();


                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                runOnUiThread(new Runnable() {
                    @Override
                    public void run()
                    {
//                        Utilss.hideSweetLoader(pDialog);
                        circularProgressBar.setVisibility(View.GONE);
                        circularProgressBar.stop();
                    }
                });

                ad.cancel();
            }
        });


























    }

    
    

    private void GetProfileParams() {

//        pDialog = Utilss.showSweetLoader(Profile.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");

//        circularProgressBar.setVisibility(View.VISIBLE);
//        circularProgressBar.start();

        if (urls.equals(""))
        {
            dialogHeader_3 = new MaterialStyledDialog.Builder(context)
                    .setHeaderDrawable(R.drawable.header)
                    .setIcon(new IconicsDrawable(context).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                    .withDialogAnimation(true)
                    .setTitle("Error Message")
                    .setDescription("Url Can't Be Null")
                    .setPositiveText("OK")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
                        {
//                                            startActivity(new Intent(Profile.this , LoginActivity.class));
                        }
                    });
            dialogHeader_3.show();
        }

        else
        {



        Request request = new Request.Builder()
                .url(urls+"api/Employee/GetEmployeeInfo")
                .get().addHeader("Cache-Control", "no-cache")
                .addHeader("Authorization", " Bearer " + LoginActivity.value)
                .addHeader("Postman-Token", "4c066075-1378-4e06-9ee9-98fd460924f9")
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Utilss.hideSweetLoader(pDialog);
//                        circularProgressBar.setVisibility(View.GONE);
//                        circularProgressBar.stop();
                    }

                });

                Log.e("HttpService", "onFailure() Request was: " + call);
                e.printStackTrace();


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        dialogHeader_3 = new MaterialStyledDialog.Builder(Profile.this)
                                .setHeaderDrawable(R.drawable.header)
                                .setIcon(new IconicsDrawable(Profile.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                .withDialogAnimation(true)
                                .setTitle("Error Message")
                                .setDescription("Cant Connect To Server")
                                .setPositiveText("OK")
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {


//                                        Intent intent = new Intent(Profile.this , LoginActivity.class);
//                                        startActivity(intent);
                                    }
                                });
                        dialogHeader_3.show();
                    }
                });



            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Utilss.hideSweetLoader(pDialog);

//                        circularProgressBar.setVisibility(View.GONE);
//                        circularProgressBar.stop();
                    }

                });

                String responses = response.body().string();
                Log.e("response", "onResponse(): " + responses);


                if (response.code() == 500) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            Utilss.hideSweetLoader(pDialog);

//                            circularProgressBar.setVisibility(View.GONE);
//                            circularProgressBar.stop();
                        }
                    });

                    try {
                        JSONObject jsonObject = new JSONObject(responses);
                        value2 = jsonObject.getString("exceptionMessage");
                        Log.d("Exception_Message", value2);





                        if (jsonObject.getString("exceptionMessage").equals("No user mapped with current token!")) {
                            runOnUiThread(new Runnable()
                            {
                                @Override
                                public void run() {

                                    Intent intent = new Intent(Profile.this, LoginActivity.class);
                                    startActivity(intent);
                                }
                            });
                        }


                        else
                        {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    dialogHeader_3 = new MaterialStyledDialog.Builder(context)
                                            .setHeaderDrawable(R.drawable.header)
                                            .setIcon(new IconicsDrawable(context).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                            .withDialogAnimation(true)
                                            .setTitle("Error Message")
                                            .setDescription(value2)
                                            .setPositiveText("OK")
                                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                @Override
                                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                                    input_date5.setText("");

//                                                moveTaskToBack(true);
//                                                android.os.Process.killProcess(android.os.Process.myPid());
//                                                System.exit(1);
                                                }
                                            });
                                    dialogHeader_3.show();
                                }
                            });
                        }



//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//
//
//
//                                dialogHeader_3 = new MaterialStyledDialog.Builder(Profile.this)
//                                        .setHeaderDrawable(R.drawable.header)
//                                        .setIcon(new IconicsDrawable(Profile.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                        .withDialogAnimation(true)
//                                        .setTitle("Error Message")
//                                        .setDescription(value2)
//                                        .setPositiveText("OK")
//                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                            @Override
//                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                                            }
//                                        });
//                                dialogHeader_3.show();
//                            }
//                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {


                    try {
                        JSONObject jsonObject = new JSONObject(responses);
                        final String value = jsonObject.getString("employeeNo");
                        final String valu2 = jsonObject.getString("employeeName");
                        final String value3 = jsonObject.getString("department");
                        final String value4 = jsonObject.getString("designation");

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run()
                            {


//                                tvUserEmailProfile.setText(valu2);
//                                tvUserSkypePr.setText(value3);
//                                tvUserContactProofilefile.setText(value);
//                                tvUserNameProfile.setText(value4);

                                tvUserNameProfile.setText(valu2);
                                tvDesigProfile.setText(value4);


                                tvUserEmailProfile.setText(valu2);
                                tvUserSkypePr.setText(value3);
                                tvUserContactProofilefile.setText(value);
                                tvUsernameProfile.setText(value4);





//
//
//
//
//
//                                tvDesigProfile.setText(value4);
//
//                                tvUserEmailProfile.setText(valu2);


//                                tvUserNameProfile.setText(value4);

//                                tvUserNameHeader.setText("Hi "+ valu2);
                            }
                        });

                        Log.d("Value3", "name is" + valu2 + "employee number is " + value + "designtion is" + value3 + "department is" + value4);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();

        //Display alert message when back button has been pressed
//        backButtonHandler();
    }
}
















//}



