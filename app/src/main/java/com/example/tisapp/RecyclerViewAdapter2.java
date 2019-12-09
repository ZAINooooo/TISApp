package com.example.tisapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.tisapp.Login.LoginActivity;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;

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

//import com.example.zain.project.ManualEntry.Manual_Check_Entry_Screen;
//import com.example.zain.project.ManualEntry.Manual_Entry;
//import static com.google.android.gms.internal.zzagy.runOnUiThread;
//import static com.google.android.gms.internal.zzagz.runOnUiThread;

public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.MyViewHolder> {

    private Context mContext ;
   public static List<PendingManualRequests_Employee> mData ;
    String responses;
    MaterialStyledDialog.Builder dialogHeader_3;
    String value2;
    OkHttpClient client;
    protected SweetAlertDialog pDialog;




    public RecyclerViewAdapter2(Context mContext, List<PendingManualRequests_Employee> mData) {
        this.mContext = mContext;
        this.mData = mData;


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int index = (int) (Math.random() * 3);
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.anime_row_item2, parent, false), index);

    }



    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, @SuppressLint("RecyclerView")  final int position) {

        if (mData.get(position).getEmployeeNo().equals("") || mData.get(position).getEmployeeNo().length() <0 )
        {
            holder.emp_number.setText("N/A");
        }

        else
        {
            holder.emp_number.setText(mData.get(position).getEmployeeNo());
        }


//        Glide.with(mContext).load(R.mipmap.image_face).into(holder.thumbnail);

//        if (mData.get(position).getEmployeeName().equals("") || mData.get(position).getEmployeeName().length() <0 )
//        {
//            holder.emp_name.setText("N/A");
//        }
//
//        else
//        {
//            holder.emp_name.setText(mData.get(position).getEmployeeName());
//        }




        if (mData.get(position).getAttendanceDate().equals("") || mData.get(position).getAttendanceDate().length() <0 )
        {
            holder.attendace.setText("N/A");
        }

        else
        {
            holder.attendace.setText(mData.get(position).getAttendanceDate());
        }

        if (mData.get(position).getStatus().equals("") || mData.get(position).getStatus().length() <0)
        {
            holder.status.setText("N/A");
        }

        else
        {
            holder.status.setText(mData.get(position).getStatus());
        }



//
//
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





        holder.table_one.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v)
                                                        {

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
                                                            final View layout = inflater.inflate(R.layout.dialog_change_password2, null );


                                                            final Vector<android.app.AlertDialog> listOfDialogs = new Vector<android.app.AlertDialog>();

                                                            TextView  date_is= (TextView) layout.findViewById(R.id.date_is);
                                                            TextView  date_in_is= (TextView) layout.findViewById(R.id.date_in_is);
                                                            TextView time_in_is= (TextView) layout.findViewById(R.id.time_in_is);

                                                            Button delete_main = (Button) layout.findViewById(R.id.delete_main);

                                                            TextView date_out_is= (TextView) layout.findViewById(R.id.date_out_is);
                                                            TextView status_is= (TextView) layout.findViewById(R.id.status_is);
                                                            TextView customer_info_is= (TextView) layout.findViewById(R.id.customer_info_is);
                                                            TextView reason_is= (TextView) layout.findViewById(R.id.reason_is);
                                                            TextView  approval1_is= (TextView) layout.findViewById(R.id.approver1_value);
                                                            TextView  approval2_is= (TextView) layout.findViewById(R.id.approver_2_value);
                                                            TextView  remarks2_is= (TextView) layout.findViewById(R.id.approver_2_status);
                                                            TextView  remarks1= (TextView) layout.findViewById(R.id.approver_status1_value);

                                                            TextView  approval3_is= (TextView) layout.findViewById(R.id.approver_3_value);
                                                            TextView  remarks3_is= (TextView) layout.findViewById(R.id.approver_3_status);
                                                            TextView  time_outs= (TextView) layout.findViewById(R.id.time_out_is);
                                                            TextView  approval4_is= (TextView) layout.findViewById(R.id.approver_4_value);
                                                            TextView  remarks4_is= (TextView) layout.findViewById(R.id.approver_4_status);

//        String attendace , date_in,date_out,time_in,time_out,shift,shift_timing,remarks,status,customer_info,reason,approved1,remark1,approved2,remark2
//                ,approved3,remark3,approved4,remark4,approvedDate,time_out_is;

                                                            String attendace= mData.get(position).getAttendanceDate();
                                                            String date_in=mData.get(position).getDateIn();
                                                            String date_out=mData.get(position).getDateOut();
                                                            String time_in=mData.get(position).getTimeIn();
                                                            String time_out=mData.get(position).getTimeOut();


//                                                            String shift=mData.get(position).getSfi
//                                                            String shift_timing=
                                                            String remarks=mData.get(position).getRemarks1();
                                                            String status=mData.get(position).getStatus();
                                                            String customer_info=mData.get(position).getCustomerInfo();
                                                            String reason=mData.get(position).getReason();
                                                            String approved1=mData.get(position).getApprovalStatus1();
//                                                            String remark1=mData.get(position).getRemarks1();

//                                                            String approved2=mData.get(position).getApprovalStatus2();
//                                                            String remark2=mData.get(position).getRemarks2();
//                                                            String approved3=mData.get(position).getApprovalStatus3();
//                                                            String remark3=mData.get(position).getRemarks3();
//                                                            String approved4=mData.get(position).getApprovalStatus4();
//                                                            String remark4=mData.get(position).getRemarks4();
//                                                            String approvedDate=mData.get(position).getApprovalDate();
                                                            String time_out_is=mData.get(position).getTimeOut();



     if (mData.get(position).getAttendanceDate().equals("") || mData.get(position).getAttendanceDate().length() <0)
        {
            date_is.setText("N/A");
        }
        else
     {
         date_is.setText(attendace);
     }



                                                            if (mData.get(position).getDateIn().equals("") || mData.get(position).getDateIn().length() <0)
                                                            {
                                                                date_in_is.setText("N/A");

                                                            }
                                                            else
                                                            {
                                                                date_in_is.setText(date_in);
                                                            }


                                                            if (mData.get(position).getTimeIn().equals("") || mData.get(position).getTimeIn().length() <0)
                                                            {
                                                                time_in_is.setText("N/A");

                                                            }
                                                            else
                                                            {
                                                                time_in_is.setText(time_in);
                                                            }




                                                            if (mData.get(position).getDateOut().equals("") || mData.get(position).getDateOut().length() <0)
                                                            {
                                                                date_out_is.setText("N/A");
                                                            }

                                                            else
                                                            {
                                                                date_out_is.setText(date_out);

                                                            }

                                                            if (mData.get(position).getTimeOut().equals("") || mData.get(position).getTimeOut().length() <0)
                                                            {
                                                                time_outs.setText("N/A");

                                                            }
                                                            else
                                                            {
                                                                time_outs.setText(time_out);
                                                            }










                                                            if (mData.get(position).getCustomerInfo().equals("") || mData.get(position).getCustomerInfo().length() <0)
                                                            {
                                                                customer_info_is.setText("N/A");


                                                            }
                                                            else
                                                            {
                                                                customer_info_is.setText(customer_info);

                                                            }


                                                            if (mData.get(position).getStatus().equals("") || mData.get(position).getStatus().length() <0)
                                                            {
                                                                status_is.setText("N/A");

                                                            }
                                                            else
                                                            {
                                                                status_is.setText(status);
                                                            }



                                                            if (mData.get(position).getReason().equals("") || mData.get(position).getReason().length() <0)
                                                            {
                                                                reason_is.setText("N/A");

                                                            }
                                                            else
                                                            {
                                                                reason_is.setText(reason);
                                                            }


                                                            if (mData.get(position).getApprovalStatus1().equals("") || mData.get(position).getApprovalStatus1().length() <0)
                                                            {
                                                                approval1_is.setText("N/A");

                                                            }
                                                            else
                                                            {
                                                                approval1_is.setText(approved1);
                                                            }




                                                            if (mData.get(position).getRemarks1().equals("") || mData.get(position).getRemarks1().length() <0)
                                                            {
                                                                remarks1.setText("N/A");

                                                            }
                                                            else
                                                            {
                                                                remarks1.setText(reason);
                                                            }






                                                            if (mData.get(position).getApprovalStatus2().equals("") || mData.get(position).getApprovalStatus2().length() <0)
                                                            {
                                                                approval2_is.setText("N/A");

                                                            }
                                                            else
                                                            {
                                                                approval2_is.setText(mData.get(position).getApprovalStatus2());
                                                            }




                                                            if (mData.get(position).getRemarks2().equals("") || mData.get(position).getRemarks2().length() <0)
                                                            {
                                                                remarks2_is.setText("N/A");

                                                            }
                                                            else
                                                            {
                                                                remarks2_is.setText(mData.get(position).getRemarks2());
                                                            }









                                                            if (mData.get(position).getApprovalStatus3().equals("") || mData.get(position).getApprovalStatus3().length() <0)
                                                            {
                                                                approval3_is.setText("N/A");

                                                            }
                                                            else
                                                            {
                                                                approval3_is.setText(mData.get(position).getApprovalStatus3());
                                                            }




                                                            if (mData.get(position).getRemarks3().equals("") || mData.get(position).getRemarks3().length() <0)
                                                            {
                                                                remarks3_is.setText("N/A");

                                                            }
                                                            else
                                                            {
                                                                remarks3_is.setText(mData.get(position).getRemarks3());
                                                            }











                                                            if (mData.get(position).getApprovalStatus4().equals("") || mData.get(position).getApprovalStatus4().length() <0)
                                                            {
                                                                approval4_is.setText("N/A");

                                                            }
                                                            else
                                                            {
                                                                approval4_is.setText(mData.get(position).getApprovalStatus4());
                                                            }




                                                            if (mData.get(position).getRemarks4().equals("") || mData.get(position).getRemarks4().length() <0)
                                                            {
                                                                remarks4_is.setText("N/A");

                                                            }
                                                            else
                                                            {
                                                                remarks4_is.setText(mData.get(position).getRemarks4());
                                                            }



//                                                            status_is.setText(customer_info);
//                                                            reason_is.setText(reason);
//                                                            approval1_is.setText(approved1);
//                                                            remarks1.setText(remarks);
//                                                            approval2_is.setText(approved2);
//                                                            remarks2_is.setText(remark2);
//                                                            approval3_is.setText(approved3);
//                                                            remarks3_is.setText(remark3);
//                                                            time_outs.setText(time_out_is);
//                                                            approval4_is.setText(approved4);
//                                                            remarks4_is.setText(remark4);
//                                                            time_outs.setText(time_out);






//                                                            date_is.setText("");
//                                                            date_is.setText("");
//                                                            date_is.setText("");
//                                                            date_is.setText("");
//                                                            date_is.setText("");










                                                                    android.app.AlertDialog.Builder aDialog = new android.app.AlertDialog.Builder(mContext);
                                                            aDialog.setView(layout);
                                                            final android.app.AlertDialog ad = aDialog.create();
//            listOfDialogs.add(ad);
                                                            ad.show();



                                                            delete_main.setOnClickListener(new View.OnClickListener() {
                                                                                               @Override
                                                                                               public void onClick(View v) {

ad.cancel();
                                                                                                   dialogHeader_3 = new MaterialStyledDialog.Builder(mContext)
                                                                                                           .setHeaderDrawable(R.drawable.header)
                                                                                                           .setIcon(new IconicsDrawable(mContext).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                                                                                           .withDialogAnimation(true)
                                                                                                           .setTitle("Do You Want To Delete?")
                                                                                                           .setPositiveText("OK")
                                                                                                           .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                                                                               @Override
                                                                                                               public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
                                                                                                               {


                                                                                          pDialog = Utilss.showSweetLoader(mContext, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");

                                                                                                                   final Request request = new Request.Builder()
                                                                                                                           .url(urls+"api/Manual/DeleteManualRequest?RequestID=" + mData.get(position).getRequestID())
                                                                                                                           .get().addHeader("Postman-Token", "147d359d-85b1-4784-b7ae-a5f91b3e5d87")
                                                                                                                           .addHeader("Authorization", " Bearer " + LoginActivity.value)
                                                                                                                           .addHeader("Cache-Control", "no-cache")
                                                                                                                           .build();

                                                                                                                   client = new OkHttpClient();
                                                                                                                   Call call = client.newCall(request);
                                                                                                                   call.enqueue(new Callback() {
                                                                                                                       @Override
                                                                                                                       public void onFailure(Call call, IOException e)
                                                                                                                       {
//                                                                                                                           Utilss.hideSweetLoader(pDialog);

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
                                                                                                                       public void onResponse(Call call, Response response) throws IOException
                                                                                                                       {
                                                                                                                           runOnUiThread(new Runnable() {
                                                                                                                               @Override
                                                                                                                               public void run() {
                                                                                                                                   Utilss.hideSweetLoader(pDialog);
                                                                                                                               }
                                                                                                                           });

                                                                                                                            responses = response.body().string();
                                                                                                                           Log.e("response", "onResponse(): " + responses);

                                                                                                                           final DatabaseHelper dbHelper = new DatabaseHelper(mContext);
                                                                                                                           //delete from localDB
//                                                                                                                           dbHelper.DeleteData();

//                                                                                                                           ad.cancel();
                                                                                                                           Intent intent = new Intent(mContext, Home_Screen.class);
                                                                                                                           mContext.startActivity(intent);
                                                                                                                       }
                                                                                                                   });


                                                                                                               }
                                                                                                           }).setNegativeText("Not now");
                                                                                                   dialogHeader_3.show();
                                                                                               }



                                                                                              });



























//
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
                                                    });




    }

//    private void alert()
//    {
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
//    }
























    public void clear() {

        notifyDataSetChanged();
    }



    //
     void addAll(List<PendingManualRequests_Employee> lst){
        mData.addAll(lst);
        notifyDataSetChanged();
    }












    @Override
    public int getItemCount()
    {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout view;
        private int backgroundIndex = 0;
        ImageView delete;
        TextView emp_number , emp_name,attendace,status;
//        ,status,customer_info,reason
        String datetime;
        OkHttpClient client;
        RelativeLayout table_one;
        String responses;
        public ImageView thumbnail;



        public MyViewHolder(View itemView, int backgroundIndex) {
            super(itemView);
            client = new OkHttpClient();
            this.backgroundIndex = backgroundIndex;
//            view = (ConstraintLayout) itemView.findViewById(R.id.item);


            thumbnail = itemView.findViewById(R.id.thumbnail);

            attendace = itemView.findViewById(R.id.attendance);
            emp_number = itemView.findViewById(R.id.ed_employee_number);
//            emp_name = itemView.findViewById(R.id.emp_name);
//            reason = itemView.findViewById(R.id.reason);
            table_one= (RelativeLayout) itemView.findViewById(R.id.view_foreground);
            status = itemView.findViewById(R.id.status);
//            customer_info = itemView.findViewById(R.id.customer_info);
        }
    }

}
