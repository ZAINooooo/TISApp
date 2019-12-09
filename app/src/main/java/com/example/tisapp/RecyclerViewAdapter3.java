package com.example.tisapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.tisapp.Login.LoginActivity;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.google.android.gms.maps.GoogleMap;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static com.example.tisapp.Settings.Settings_Activity.urls;
import static com.google.android.gms.internal.zzahn.runOnUiThread;
//import static com.google.android.gms.internal.zzagz.runOnUiThread;
//import static com.google.android.gms.internal.zzagy.runOnUiThread;
//import static com.google.android.gms.internal.zzahf.runOnUiThread;

public class RecyclerViewAdapter3 extends RecyclerView.Adapter<RecyclerViewAdapter3.MyViewHolder> {

    private Context mContext;
    public static List<PendingApproverRequests_Employee> mData;
    String responses;
    View layout;
    String value2;

    protected SweetAlertDialog pDialog;


    OkHttpClient client;
    String abc;

    Double x, y;

    MaterialStyledDialog.Builder dialogHeader_3;


    public RecyclerViewAdapter3(Context mContext, List<PendingApproverRequests_Employee> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

//        View view ;
//        LayoutInflater inflater = LayoutInflater.from(mContext);
//        view = inflater.inflate(R.layout.anime_row_item3,parent,false) ;
//        return new MyViewHolder(view);

        int index = (int) (Math.random() * 3);
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.anime_row_item3, parent, false), index);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        if (mData.get(position).getAttendanceDate().equals("") || mData.get(position).getAttendanceDate().length() < 0) {
            holder.attendace.setText("N/A");
        } else {
            holder.attendace.setText(mData.get(position).getAttendanceDate());
        }


        if (mData.get(position).getEmployeeNo().equals("") || mData.get(position).getEmployeeNo().length() < 0) {
            holder.emp_number.setText("N/A");
        } else {
            holder.emp_number.setText(mData.get(position).getEmployeeNo());
        }


        if (mData.get(position).getStatus().equals("") || mData.get(position).getStatus().length() < 0) {
            holder.status.setText("N/A");
        } else {
            holder.status.setText(mData.get(position).getStatus());
        }


//        if (mData.get(position).getCustomerInfo().equals("") || mData.get(position).getCustomerInfo().length() <0)
//        {
//            holder.customer_info.setText("N/A");
//        }
//
//        else
//        {
//            holder.customer_info.setText(mData.get(position).getCustomerInfo());
//        }


//        if (mData.get(position).getReason().equals("") || mData.get(position).getReason().length() <0)
//        {
//            holder.reason.setText("N/A");
//        }
//
//        else
//        {
//            holder.reason.setText(mData.get(position).getReason());
//        }


//        x = mData.get(position).getXcordinates();
//        y = mData.get(position).getYcordinates();


        holder.google_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isNetAvailable()) {

                    pDialog = Utilss.showSweetLoader(mContext, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (isGoogleMapsInstalled()) {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Utilss.hideSweetLoader(pDialog);
                                    }
                                });


                                x = mData.get(position).getXcordinates();
                                y = mData.get(position).getYcordinates();

                                Log.d("Xcordinate", "" + x);
                                Log.d("Ycordinate", "" + y);


                                String strUri = "http://maps.google.com/maps?q=loc:" + mData.get(position).getXcordinates() + "," + mData.get(position).getYcordinates() + " (" + "Label which you want" + ")";
//                         String uri = "http://maps.google.com/maps?saddr=" + lat + "," + lon ;
                                Uri gmmIntentUri = Uri.parse(strUri);
                                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                                mapIntent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                                mapIntent.setPackage("com.google.android.apps.maps");
                                mContext.startActivity(mapIntent);
                            } else {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Utilss.hideSweetLoader(pDialog);
                                    }
                                });

                                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                                builder.setMessage("Please install Google Maps");
                                builder.setCancelable(false);
                                builder.setPositiveButton("Install", getGoogleMapsListener());
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }

                        }
                    }, 1000);


//
//                    Bundle bundle = new Bundle();
//                    bundle.putDouble("Lat", mData.get(position).getXcordinates());
//                    bundle.putDouble("Long", mData.get(position).getXcordinates());
//
//                    Fragment newFragment = new Google_Map_Fragment();
//                    newFragment.setArguments(bundle);
//
//                    android.support.v4.app.FragmentTransaction transaction = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.fl_MainContainer, newFragment);
//                    transaction.addToBackStack(null);
//                    transaction.commit();


//                    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
//                    layout = inflater.inflate(R.layout.dialog_change_password5, null);
//                    final Vector<AlertDialog> listOfDialogs = new Vector<android.app.AlertDialog>();
//
//
//                    SupportMapFragment supportMapFragment = (SupportMapFragment) ((FragmentActivity) mContext).getSupportFragmentManager().findFragmentById(R.id.google_map);
//                    supportMapFragment.getMapAsync((OnMapReadyCallback) mContext);
//
//
////                    SupportMapFragment supportMapFragment =(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
////                    Objects.requireNonNull(supportMapFragment).getMapAsync(this);
//
//
////
////                    Intent intent = new Intent(mContext, MapsActivity.class);
////                    intent.putExtra("xcordinate", x);
////                    intent.putExtra("ycordinate", y);
////                    mContext.startActivity(intent);
                } else {


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Utilss.hideSweetLoader(pDialog);
                        }
                    });


                    dialogHeader_3 = new MaterialStyledDialog.Builder(mContext)
                            .setHeaderDrawable(R.drawable.header)
                            .setIcon(new IconicsDrawable(mContext).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                            .withDialogAnimation(true)
                            .setTitle("Error Message")
                            .setDescription("No Internet Connected")
                            .setPositiveText("OK")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                                    input_date5.setText("");

//                                    input_date2.setText("");
//                                    from_date.setText("");
//                                    to_date.setText("");
                                }
                            });
                    dialogHeader_3.show();


//                    new TTFancyGifDialog.Builder((Activity) mContext)
//                            .setTitle("Error Message")
//                            .setMessage("Internet Is Required.")
//                            .setPositiveBtnBackground("#f4de15")
//                            .setPositiveBtnText("Ok")
//                            .setGifResource(R.drawable.g)
//                            .isCancellable(true)
//                            .OnPositiveClicked(new TTFancyGifDialogListener() {
//                                @Override
//                                public void OnClick()
//                                {
//mContext.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
//                                }
//                            })
//                            .build();


//                    dialogHeader_3 = new MaterialStyledDialog.Builder(mContext)
//                            .setHeaderDrawable(R.drawable.header)
//                            .setIcon(new IconicsDrawable(mContext).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                            .withDialogAnimation(true)
//                            .setTitle("Internet Is Required.")
//                            .setDescription("Internet Is Required.")
//                            .setPositiveText("OK")
//                            .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                @Override
//                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////
//                                    mContext.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
//
//                                }
//                            });
//                    dialogHeader_3.show();


                    AlertDialog.Builder aDialog = new AlertDialog.Builder(mContext);
                    aDialog.setView(layout);
                    final AlertDialog ad = aDialog.create();
//            listOfDialogs.add(ad);
                    ad.show();

                }
            }
        });



     /*   holder.table_one.setOnClickListener(new View.OnClickListener()
        {
                                                @Override
                                                public void onClick(View v)
                                                {

//                int id = Integer.parseInt(mData.get(position).getRequestID());
//
//                Intent intent = new Intent(mContext,ApproverResultActivity.class);
//                intent.putExtra("pos", id);
//                intent.putExtra("attendanceDate", mData.get(position).getAttendanceDate());
//                intent.putExtra("employeeName", mData.get(position).getEmployeeName());
//                intent.putExtra("dateIn", mData.get(position).getDateIn());
//                intent.putExtra("timeIn", mData.get(position).getTimeIn());
//                intent.putExtra("dateOut", mData.get(position).getDateOut());
//                intent.putExtra("timeOut", mData.get(position).getTimeOut());
//                intent.putExtra("status", mData.get(position).getStatus());
//                intent.putExtra("customerInfo", mData.get(position).getCustomerInfo());
//                intent.putExtra("reason", mData.get(position).getReason());
//                intent.putExtra("approvalStatus1", mData.get(position).getApprovalStatus1());
//                intent.putExtra("remarks1", mData.get(position).getRemarks1());
//                intent.putExtra("approvalStatus2", mData.get(position).getApprovalStatus2());
//                intent.putExtra("remarks2", mData.get(position).getRemarks2());
//                intent.putExtra("approvalStatus3", mData.get(position).getApprovalStatus3());
//                intent.putExtra("remarks3", mData.get(position).getRemarks3());
//                intent.putExtra("approvalStatus4", mData.get(position).getApprovalStatus4());
//                intent.putExtra("remarks4", mData.get(position).getRemarks4());
//
//                mContext.startActivity(intent);
                                                }
                                            });


    }*/


        holder.table_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                                            pDialog = Utilss.showSweetLoader(mContext, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");

//                                                            int id = Integer.parseInt(mData.get(position).getRequestID());
//
//                                                            Log.d("Id_IS" ,""+id);
//                                                            Intent intent = new Intent(mContext,ManualDetail.class);
//                                                            intent.putExtra("pos", id);
//                                                            intent.putExtra("attendanceDate", mData.get(position).getAttendanceDate());
//                                                            intent.putExtra("dateIn", mData.get(position).getDateIn());
//                                                            intent.putExtra("timeIn", mData.get(position).getTimeIn());
//                                                            intent.putExtra("dateOut", mData.get(position).getDateOut());
//                                                            intent.putExtra("timeOut", mData.get(position).getTimeOut());
//                                                            intent.putExtra("status", mData.get(position).getStatus());
//                                                            intent.putExtra("customerInfo", mData.get(position).getCustomerInfo());
//                                                            intent.putExtra("reason", mData.get(position).getReason());
//                                                            intent.putExtra("approvalStatus1", mData.get(position).getApprovalStatus1());
//                                                            intent.putExtra("remarks1", mData.get(position).getRemarks1());
//                                                            intent.putExtra("approvalStatus2", mData.get(position).getApprovalStatus2());
//                                                            intent.putExtra("remarks2", mData.get(position).getRemarks2());
//                                                            intent.putExtra("approvalStatus3", mData.get(position).getApprovalStatus3());
//                                                            intent.putExtra("remarks3", mData.get(position).getRemarks3());
//                                                            intent.putExtra("approvalStatus4", mData.get(position).getApprovalStatus4());
//                                                            intent.putExtra("remarks4", mData.get(position).getRemarks4());
//
//
//                                                            mContext.startActivity(intent);


//        TextView date_is,time_in_is,date_in_is,date_out_is,status_is,customer_info_is,reason_is,approval1_is,remarks1_is,approval2_is,remarks2_is,approval3_is,remarks3_is
//                ,approval4_is,remarks4_is,time_outs;
//        Button delete_main;


                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                final View layout = inflater.inflate(R.layout.dialog_change_password7, null);


                final Vector<AlertDialog> listOfDialogs = new Vector<AlertDialog>();

                TextView date_is = (TextView) layout.findViewById(R.id.date_is);
                TextView date_in_is = (TextView) layout.findViewById(R.id.date_in_is);
                TextView time_in_is = (TextView) layout.findViewById(R.id.time_in_is);

                Button accept_btn = (Button) layout.findViewById(R.id.accept_btn);
                Button deny_btn = (Button) layout.findViewById(R.id.deny_btn);


                TextView date_out_is = (TextView) layout.findViewById(R.id.date_out_is);
                TextView status_is = (TextView) layout.findViewById(R.id.status_is);
                TextView customer_info_is = (TextView) layout.findViewById(R.id.customer_info_is);
                TextView reason_is = (TextView) layout.findViewById(R.id.reason_is);
                TextView approval1_is = (TextView) layout.findViewById(R.id.approver1_value);
                TextView approval2_is = (TextView) layout.findViewById(R.id.approver_2_value);
                TextView remarks2_is = (TextView) layout.findViewById(R.id.approver_2_status);
                TextView remarks1 = (TextView) layout.findViewById(R.id.approver_status1_value);

                TextView approval3_is = (TextView) layout.findViewById(R.id.approver_3_value);
                TextView remarks3_is = (TextView) layout.findViewById(R.id.approver_3_status);
                TextView time_outs = (TextView) layout.findViewById(R.id.time_out_is);
                TextView approval4_is = (TextView) layout.findViewById(R.id.approver_4_value);
                TextView remarks4_is = (TextView) layout.findViewById(R.id.approver_4_status);

//        String attendace , date_in,date_out,time_in,time_out,shift,shift_timing,remarks,status,customer_info,reason,approved1,remark1,approved2,remark2
//                ,approved3,remark3,approved4,remark4,approvedDate,time_out_is;

                String attendace = mData.get(position).getAttendanceDate();
                String date_in = mData.get(position).getDateIn();
                String date_out = mData.get(position).getDateOut();
                String time_in = mData.get(position).getTimeIn();
                String time_out = mData.get(position).getTimeOut();


//                                                            String shift=mData.get(position).getSfi
//                                                            String shift_timing=
                String remarks = mData.get(position).getRemarks1();
                String status = mData.get(position).getStatus();
                String customer_info = mData.get(position).getCustomerInfo();
                String reason = mData.get(position).getReason();
                String approved1 = mData.get(position).getApprovalStatus1();
//                                                            String remark1=mData.get(position).getRemarks1();

//                                                            String approved2=mData.get(position).getApprovalStatus2();
//                                                            String remark2=mData.get(position).getRemarks2();
//                                                            String approved3=mData.get(position).getApprovalStatus3();
//                                                            String remark3=mData.get(position).getRemarks3();
//                                                            String approved4=mData.get(position).getApprovalStatus4();
//                                                            String remark4=mData.get(position).getRemarks4();
//                                                            String approvedDate=mData.get(position).getApprovalDate();
                String time_out_is = mData.get(position).getTimeOut();


                if (mData.get(position).getAttendanceDate().equals("") || mData.get(position).getAttendanceDate().length() < 0) {
                    date_is.setText("N/A");
                } else {
                    date_is.setText(attendace);
                }


                if (mData.get(position).getDateIn().equals("") || mData.get(position).getDateIn().length() < 0) {
                    date_in_is.setText("N/A");

                } else {
                    date_in_is.setText(date_in);
                }


                if (mData.get(position).getTimeIn().equals("") || mData.get(position).getTimeIn().length() < 0) {
                    time_in_is.setText("N/A");

                } else {
                    time_in_is.setText(time_in);
                }


                if (mData.get(position).getDateOut().equals("") || mData.get(position).getDateOut().length() < 0) {
                    date_out_is.setText("N/A");
                } else {
                    date_out_is.setText(date_out);

                }

                if (mData.get(position).getTimeOut().equals("") || mData.get(position).getTimeOut().length() < 0) {
                    time_outs.setText("N/A");

                } else {
                    time_outs.setText(time_out);
                }


                if (mData.get(position).getCustomerInfo().equals("") || mData.get(position).getCustomerInfo().length() < 0) {
                    customer_info_is.setText("N/A");


                } else {
                    customer_info_is.setText(customer_info);

                }


                if (mData.get(position).getStatus().equals("") || mData.get(position).getStatus().length() < 0) {
                    status_is.setText("N/A");

                } else {
                    status_is.setText(status);
                }


                if (mData.get(position).getReason().equals("") || mData.get(position).getReason().length() < 0) {
                    reason_is.setText("N/A");

                } else {
                    reason_is.setText(reason);
                }


                if (mData.get(position).getApprovalStatus1().equals("") || mData.get(position).getApprovalStatus1().length() < 0) {
                    approval1_is.setText("N/A");

                } else {
                    approval1_is.setText(approved1);
                }


                if (mData.get(position).getRemarks1().equals("") || mData.get(position).getRemarks1().length() < 0) {
                    remarks1.setText("N/A");

                } else {
                    remarks1.setText(reason);
                }


                if (mData.get(position).getApprovalStatus2().equals("") || mData.get(position).getApprovalStatus2().length() < 0) {
                    approval2_is.setText("N/A");

                } else {
                    approval2_is.setText(mData.get(position).getApprovalStatus2());
                }

                if (mData.get(position).getRemarks2().equals("") || mData.get(position).getRemarks2().length() < 0) {
                    remarks2_is.setText("N/A");

                } else {
                    remarks2_is.setText(mData.get(position).getRemarks2());
                }


                if (mData.get(position).getApprovalStatus3().equals("") || mData.get(position).getApprovalStatus3().length() < 0) {
                    approval3_is.setText("N/A");

                } else {
                    approval3_is.setText(mData.get(position).getApprovalStatus3());
                }

                if (mData.get(position).getRemarks3().equals("") || mData.get(position).getRemarks3().length() < 0) {
                    remarks3_is.setText("N/A");

                } else {
                    remarks3_is.setText(mData.get(position).getRemarks3());
                }

                if (mData.get(position).getApprovalStatus4().equals("") || mData.get(position).getApprovalStatus4().length() < 0) {
                    approval4_is.setText("N/A");

                } else {
                    approval4_is.setText(mData.get(position).getApprovalStatus4());
                }

                if (mData.get(position).getRemarks4().equals("") || mData.get(position).getRemarks4().length() < 0) {
                    remarks4_is.setText("N/A");

                } else {
                    remarks4_is.setText(mData.get(position).getRemarks4());
                }

                AlertDialog.Builder aDialog = new AlertDialog.Builder(mContext);
                aDialog.setView(layout);
                final AlertDialog ad1 = aDialog.create();
//            listOfDialogs.add(ad);
                ad1.show();


                accept_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ad1.cancel();

//                    pDialog = Utilss.showSweetLoader(mContext, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");


//                        final Context Contextx = mContext;
                        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                        final View layout = inflater.inflate(R.layout.prompt4, null);
                        final Vector<AlertDialog> listOfDialogs = new Vector<AlertDialog>();
                        Button btnCancel = (Button) layout.findViewById(R.id.btnCancel);
                        Button btnOk = (Button) layout.findViewById(R.id.btnOk);

                        final EditText ed_employee_number = layout.findViewById(R.id.ed_remarks);

                        AlertDialog.Builder aDialog = new AlertDialog.Builder(mContext);
                        aDialog.setView(layout);
                        final AlertDialog ad = aDialog.create();
                        listOfDialogs.add(ad);
                        ad.show();

                        btnOk.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                    pDialog = Utilss.showSweetLoader(mContext, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");


                                abc = ed_employee_number.getText().toString();


                                if (abc.equals("")) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                Utilss.hideSweetLoader(pDialog);
                                        }
                                    });

                                    ed_employee_number.setError("Remarks Cant Be Empty..!!");
                                } else {


//                                    runOnUiThread(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            Utilss.hideSweetLoader(pDialog);
//                                        }
//                                    });

                                    final Request request = new Request.Builder()
                                            .url(urls + "api/Manual/AcceptManualRequest?RequestID=" + mData.get(position).getRequestID() + "&Remarks=" + ed_employee_number.getText().toString())
                                            .get().addHeader("Cache-Control", "no-cache")
                                            .addHeader("Authorization", " Bearer " + LoginActivity.value)
                                            .addHeader("Postman-Token", "9c139266-f725-49a2-a13b-4f310d93742c")
                                            .build();

                                    client = new OkHttpClient();
                                    Call call = client.newCall(request);
                                    call.enqueue(new Callback() {
                                        @Override
                                        public void onFailure(Call call, IOException e) {

                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {


                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Utilss.hideSweetLoader(pDialog);
                                                        }
                                                    });
                                                }
                                            });



//                                            runOnUiThread(new Runnable() {
//                                                @Override
//                                                public void run() {
//                                                    Utilss.hideSweetLoader(pDialog);
//                                                }
//                                            });


//                                            runOnUiThread(new Runnable() {
//                                                @Override
//                                                public void run() {
////                                        Utilss.hideSweetLoader(pDialog);
////
////                                                    dialogHeader_3 = new MaterialStyledDialog.Builder(mContext)
////                                                            .setHeaderDrawable(R.drawable.header)
////                                                            .setIcon(new IconicsDrawable(mContext).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
////                                                            .withDialogAnimation(true)
////                                                            .setTitle("Error Message")
////                                                            .setDescription("Date Out Is Required")
////                                                            .setPositiveText("OK")
////                                                            .onPositive(new MaterialDialog.SingleButtonCallback() {
////                                                                @Override
////                                                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
////                                                                {
//////                                            input_dates.setText("");
//////                                            input_dates2.setText("");
////                                                                }
////                                                            });
////                                                    dialogHeader_3.show();
//                                                }
//                                            });

                                            Log.e("HttpService", "onFailure() Request was: " + call);
                                            e.printStackTrace();
                                        }

                                        @Override
                                        public void onResponse(Call call, Response response) throws IOException {


//                                            runOnUiThread(new Runnable() {
//                                                @Override
//                                                public void run() {
//                                        Utilss.hideSweetLoader(pDialog);
//                                                }
//                                            });

                                            responses = response.body().string();
                                            Log.e("response", "onResponse(): " + responses);

                                            if (response.code() == 200)
                                            {


                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Utilss.hideSweetLoader(pDialog);


                                                    }
                                                });


                                                runOnUiThread(new Runnable() {

                                                    @Override
                                                    public void run() {

                                                        dialogHeader_3 = new MaterialStyledDialog.Builder(mContext)
                                                                .setHeaderDrawable(R.drawable.header)
                                                                .setIcon(new IconicsDrawable(mContext).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                                                .withDialogAnimation(true)
                                                                .setTitle("Confirmation Message")
                                                                .setDescription(responses)
                                                                .setPositiveText("OK")
                                                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                                    @Override
                                                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                                    input_date5.setText("");

//                                                                        input_date2.setText("");
//                                                                        from_date.setText("1-Jan-2019");
//                                                                        to_date.setText("2017-06-30");
//                                                                        days_leave.setText("");

                                                                        Intent intent = new Intent(mContext, Home_Screen.class);
                                                                        mContext.startActivity(intent);
                                                                    }
                                                                });
                                                        dialogHeader_3.show();
                                                    }
                                                });
                                            }




                                            else if (response.code() == 500)
                                            {

                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Utilss.hideSweetLoader(pDialog);

//                                                        circularProgressBar.setVisibility(View.GONE);
//                                                        circularProgressBar.stop();
                                                    }
                                                });

                                                try {
                                                    JSONObject jsonObject = new JSONObject(responses);
                                                    value2 = jsonObject.getString("exceptionMessage");
                                                    Log.d("ValueResponse", value2);


                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
//

                                                            dialogHeader_3 = new MaterialStyledDialog.Builder(mContext)
                                                                    .setHeaderDrawable(R.drawable.header)
                                                                    .setIcon(new IconicsDrawable(mContext).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                                                    .withDialogAnimation(true)
                                                                    .setTitle("Error Message")
                                                                    .setDescription(value2)
                                                                    .setPositiveText("OK")
                                                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                                        @Override
                                                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//

                                                                            Intent intent = new Intent(mContext, Home_Screen.class);
                                                                            mContext.startActivity(intent);
                                                                        }
                                                                    });
                                                            dialogHeader_3.show();

                                                        }
                                                    });
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }

                                            }

                                            ad.cancel();
                                        }
                                    });
                                }
                            }
                        });


                        btnCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                ad.cancel();
                            }
                        });

                    }


//                        ad.cancel();
//                        dialogHeader_3 = new MaterialStyledDialog.Builder(mContext)
//                                .setHeaderDrawable(R.drawable.header)
//                                .setIcon(new IconicsDrawable(mContext).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                .withDialogAnimation(true)
//                                .setTitle("Do You Want To Delete?")
//                                .setPositiveText("OK")
//                                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                    @Override
//                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
//                                    {
//
//
//                                        final Request request = new Request.Builder()
//                                                .url(urls+"api/Manual/DeleteManualRequest?RequestID=" + mData.get(position).getRequestID())
//                                                .get().addHeader("Postman-Token", "147d359d-85b1-4784-b7ae-a5f91b3e5d87")
//                                                .addHeader("Authorization", " Bearer " + LoginActivity.value)
//                                                .addHeader("Cache-Control", "no-cache")
//                                                .build();
//
//                                        client = new OkHttpClient();
//                                        Call call = client.newCall(request);
//                                        call.enqueue(new Callback() {
//                                            @Override
//                                            public void onFailure(Call call, IOException e)
//                                            {
//                                                Log.e("HttpService", "onFailure() Request was: " + call);
//                                                e.printStackTrace();
//                                            }
//
//                                            @Override
//                                            public void onResponse(Call call, Response response) throws IOException
//                                            {
//
//                                                responses = response.body().string();
//                                                Log.e("response", "onResponse(): " + responses);
//
////                                                                                                                           ad.cancel();
//                                                Intent intent = new Intent(mContext, Home_Screen.class);
//                                                mContext.startActivity(intent);
//                                            }
//                                        });
//
//
//                                    }
//                                }).setNegativeText("Not now");
//                        dialogHeader_3.show();


                });


                deny_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ad1.cancel();


//                   pDialog = Utilss.showSweetLoader(mContext, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");


//                        final Context mContext = getApplicationContext();
                        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                        final View layout = inflater.inflate(R.layout.prompt4, null);
                        final Vector<AlertDialog> listOfDialogs = new Vector<AlertDialog>();
                        Button btnCancel = (Button) layout.findViewById(R.id.btnCancel);
                        Button btnOk = (Button) layout.findViewById(R.id.btnOk);

                        final EditText ed_employee_number = layout.findViewById(R.id.ed_remarks);

                        AlertDialog.Builder aDialog = new AlertDialog.Builder(mContext);
                        aDialog.setView(layout);
                        final AlertDialog ad = aDialog.create();
                        listOfDialogs.add(ad);
                        ad.show();

                        btnOk.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                             pDialog = Utilss.showSweetLoader(mContext, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Utilss.hideSweetLoader(pDialog);
                                    }
                                });


                                abc = ed_employee_number.getText().toString();


                                if (abc.equals("")) {
                                    ed_employee_number.setError("Remarks Cant Be Empty..!!");
                                } else {



                                    final Request request = new Request.Builder()
                                            .url(urls + "api/Manual/DenyManualRequest?RequestID=" + mData.get(position).getRequestID() + "&Remarks=" + ed_employee_number.getText().toString())
                                            .get().addHeader("Cache-Control", "no-cache")
                                            .addHeader("Authorization", " Bearer " + LoginActivity.value)
                                            .addHeader("Postman-Token", "9c139266-f725-49a2-a13b-4f310d93742c")
                                            .build();

                                    client = new OkHttpClient();
                                    Call call = client.newCall(request);
                                    call.enqueue(new Callback() {
                                        @Override
                                        public void onFailure(Call call, IOException e) {


                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Utilss.hideSweetLoader(pDialog);
                                                }
                                            });


                                            Log.e("HttpService", "onFailure() Request was: " + call);
                                            e.printStackTrace();
                                        }

                                        @Override
                                        public void onResponse(Call call, Response response) throws IOException {

                                            responses = response.body().string();
                                            Log.e("response", "onResponse(): " + responses);



                                            if (response.code() == 200)
                                            {


                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                            Utilss.hideSweetLoader(pDialog);


                                                    }
                                                });


                                                runOnUiThread(new Runnable() {

                                                    @Override
                                                    public void run() {

                                                        dialogHeader_3 = new MaterialStyledDialog.Builder(mContext)
                                                                .setHeaderDrawable(R.drawable.header)
                                                                .setIcon(new IconicsDrawable(mContext).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                                                .withDialogAnimation(true)
                                                                .setTitle("Confirmation Message")
                                                                .setDescription(responses)
                                                                .setPositiveText("OK")
                                                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                                    @Override
                                                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                                    input_date5.setText("");

//                                                                        input_date2.setText("");
//                                                                        from_date.setText("1-Jan-2019");
//                                                                        to_date.setText("2017-06-30");
//                                                                        days_leave.setText("");

                                                                        Intent intent = new Intent(mContext, Home_Screen.class);
                                                                        mContext.startActivity(intent);
                                                                    }
                                                                });
                                                        dialogHeader_3.show();
                                                    }
                                                });
                                            }




                                          else if (response.code() == 500)
                                            {

                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                            Utilss.hideSweetLoader(pDialog);

//                                                        circularProgressBar.setVisibility(View.GONE);
//                                                        circularProgressBar.stop();
                                                    }
                                                });

                                                try {
                                                    JSONObject jsonObject = new JSONObject(responses);
                                                    value2 = jsonObject.getString("exceptionMessage");
                                                    Log.d("ValueResponse", value2);


                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
//

                                                            dialogHeader_3 = new MaterialStyledDialog.Builder(mContext)
                                                                    .setHeaderDrawable(R.drawable.header)
                                                                    .setIcon(new IconicsDrawable(mContext).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                                                    .withDialogAnimation(true)
                                                                    .setTitle("Error Message")
                                                                    .setDescription(value2)
                                                                    .setPositiveText("OK")
                                                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                                        @Override
                                                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//

                                                                            Intent intent = new Intent(mContext, Home_Screen.class);
                                                                            mContext.startActivity(intent);
                                                                        }
                                                                    });
                                                            dialogHeader_3.show();

                                                        }
                                                    });
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }

                                            }

                                            ad.cancel();
                                        }
                                    });
                                }
                            }
                        });









//                                            runOnUiThread(new Runnable() {
//                                                @Override
//                                                public void run() {
//                                                    Utilss.hideSweetLoader(pDialog);
//                                                }
//                                            });
//
//                                            runOnUiThread(new Runnable() {
//                                                @Override
//                                                public void run() {
////                                                                 Toast.makeText(mContext, "Denied Successfully", Toast.LENGTH_SHORT).show();
////                                                                 Intent intent = new Intent(mContext, Manual_Entry.class);
////                                                                 mContext.startActivity(intent);
//
//
////                                                    new TTFancyGifDialog.Builder(mContext)
////                                                            .setTitle("Response Message")
////                                                            .setMessage(responses)
////                                                            .setPositiveBtnBackground("#f4de15")
////                                                            .setPositiveBtnText("OK")
////                                                            .setGifResource(R.drawable.g)
////                                                            .isCancellable(true)
////                                                            .OnPositiveClicked(new TTFancyGifDialogListener() {
////                                                                @Override
////                                                                public void OnClick() {
////
////                                                                    Intent intent = new Intent(mContext, Home_Screen.class);
////                                                                    mContext.startActivity(intent);
////
////
////
////                                                                }
////
////                                                            })
////                                                            .build();
//
//
//                                                    dialogHeader_3 = new MaterialStyledDialog.Builder(mContext)
//                                                            .setHeaderDrawable(R.drawable.header)
//                                                            .setIcon(new IconicsDrawable(mContext).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                                            .withDialogAnimation(true)
//                                                            .setTitle("Response Message")
//                                                            .setDescription(responses)
//                                                            .setPositiveText("OK")
//                                                            .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                                                @Override
//                                                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////                                                    input_date5.setText("");
//
////                                                                    input_date2.setText("");
//////                                                                    from_date.setText("");
//////                                                                    to_date.setText("");
//
//                                                                    Intent intent = new Intent(mContext, Home_Screen.class);
//                                                                    mContext.startActivity(intent);
//                                                                }
//                                                            });
//                                                    dialogHeader_3.show();
//                                                }
//                                            });




                        btnCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                pDialog = Utilss.showSweetLoader(mContext, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Utilss.hideSweetLoader(pDialog);
                                    }
                                });

                                ad.cancel();
                            }
                        });

                    }
                });
            }
        });


//                                                            btnCancel.setOnClickListener(new View.OnClickListener() {
//                                                                @Override
//                                                                public void onClick(View v) {
//
//
////                runOnUiThread(new Runnable() {
////                    @Override
////                    public void run()
////                    {
////                        Utilss.hideSweetLoader(pDialog);
////                    }
////                });
//
//                                                                    ad.cancel();
//                                                                }
//                                                            });


    }

    public boolean isGoogleMapsInstalled()
    {
        try
        {
            ApplicationInfo info = mContext.getPackageManager().getApplicationInfo("com.google.android.apps.maps", 0 );
            return true;
        }
        catch(PackageManager.NameNotFoundException e)
        {
            return false;
        }
    }

    public DialogInterface.OnClickListener getGoogleMapsListener()
    {
        return new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.android.apps.maps"));
                mContext.startActivity(intent);
//                finish();
            }
        };
    }


//    public void alert2() {
//        try {
////            pDialog = Utilss.showSweetLoader(mContext, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");
//
//
//
//
//    }


    @Override
    public int getItemCount() {
        return mData.size();
    }


    public boolean isNetAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//
//
//        if (ActivityCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        googleMap.setMyLocationEnabled(true);
//
//        LatLng city = new LatLng(x, y);
//        googleMap.addMarker(new MarkerOptions().position(city).title("Hi I am here"));
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(city, 23));
//    }


//    public static class MyViewHolder extends RecyclerView.ViewHolder {
//
//       ImageView delete;
//        TextView attendace,emp_name,status,customer_info,reason;
//        LinearLayout view_container;
//        OkHttpClient client;
//        String responses;
//        Button google_map;
//        TableLayout table_one;
//
//
//
//
//
//        public MyViewHolder(View itemView) {
//            super(itemView);
//
//
//            client = new OkHttpClient();
//            attendace = itemView.findViewById(R.id.attendance);
//            emp_name= itemView.findViewById(R.id.emp_name);
//            reason = itemView.findViewById(R.id.reason);
//            table_one= itemView.findViewById(R.id.table_one);
//
//
//            google_map= itemView.findViewById(R.id.google_maps);
//
//            status = itemView.findViewById(R.id.status);
//            customer_info = itemView.findViewById(R.id.cus_infos);
//
//        }
//    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
//        private ConstraintLayout view;
//        private int backgroundIndex = 0;
//               ImageView delete;
//        TextView attendace,emp_name,status,customer_info,reason;
//        LinearLayout view_container;
//        OkHttpClient client;
//        String responses;

        Button google_map;
        GoogleMap map;

//        TableLayout table_one;

        private ConstraintLayout view;
        private int backgroundIndex = 0;
        ImageView delete;
        TextView emp_number, emp_name, attendace, status;
        //        ,status,customer_info,reason
        String datetime;
        OkHttpClient client;
        RelativeLayout table_one;
        String responses;

        public ImageView thumbnail;

        public MyViewHolder(View itemView, int backgroundIndex) {
            super(itemView);
//            client = new OkHttpClient();
//            this.backgroundIndex = backgroundIndex;
//            view = (ConstraintLayout) itemView.findViewById(R.id.item2);
//
//            client = new OkHttpClient();
//            attendace = itemView.findViewById(R.id.attendance);
//            emp_name= itemView.findViewById(R.id.emp_name);
//            reason = itemView.findViewById(R.id.reason);
//            table_one= itemView.findViewById(R.id.table_one);
//
//
            google_map = itemView.findViewById(R.id.google_maps);

//            if (google_map != null) {
//                // Initialise the MapView
//                google_map.onCreate(null);
//                // Set the map ready callback to receive the GoogleMap object
//                google_map.getMapAsync(this);
//            }


//
//            status = itemView.findViewById(R.id.status);
//            customer_info = itemView.findViewById(R.id.cus_infos);

            client = new OkHttpClient();
            this.backgroundIndex = backgroundIndex;
//            view = (ConstraintLayout) itemView.findViewById(R.id.item);


            thumbnail = itemView.findViewById(R.id.thumbnail);

            attendace = itemView.findViewById(R.id.attendance);
            emp_number = itemView.findViewById(R.id.ed_employee_number);
//            emp_name = itemView.findViewById(R.id.emp_name);
//            reason = itemView.findViewById(R.id.reason);
            table_one = (RelativeLayout) itemView.findViewById(R.id.view_foreground);
            status = itemView.findViewById(R.id.status);
//            customer_info = itemView.findViewById(R.id.customer_info);


        }

//        @Override
////        public void onMapReady(GoogleMap googleMap) {
////            MapsInitializer.initialize(mContext);
////            map = googleMap;
////            setMapLocation();
////        }
////
////    }
////
////    private void setMapLocation()
////    {
////        if (map == null) return;
////
//////        NamedLocation data = (NamedLocation) mapView.getTag();
////        if (data == null) return;
////
////        // Add a marker for this item and set the camera
////        map.moveCamera(CameraUpdateFactory.newLatLngZoom(data.location, 13f));
////        map.addMarker(new MarkerOptions().position(data.location));
////
////        // Set the map type back to normal.
////        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
////    }


    }
}
