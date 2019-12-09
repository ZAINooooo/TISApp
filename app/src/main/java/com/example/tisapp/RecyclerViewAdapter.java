package com.example.tisapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;



import java.util.List;
import java.util.Vector;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext ;
    private List<Periodic> mData ;
    private TextView alterText ;
    View view ;

    public RecyclerViewAdapter(Context mContext, List<Periodic> mData) {
        this.mContext = mContext;
        this.mData = mData;
//        this.alterText = alterText;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.anime_row_item,parent,false) ;
        return new MyViewHolder(view);

//        int index = (int) (Math.random() * 3);
//        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.anime_row_item, parent, false), index);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        if (mData.get(position).getRemarks().equals("Absent") || mData.get(position).getRemarks().matches("Absent"))
        {
            holder.manual_enter.setVisibility(View.VISIBLE);
        }

        else
        {
            holder.manual_enter.setVisibility(View.GONE);
        }



        if (mData.get(position).getEmployeeNo().equals("") || mData.get(position).getEmployeeNo().length() <0 )
        {
            holder.employeeNo.setText("N/A");
        }

        else
        {
            holder.employeeNo.setText(mData.get(position).getEmployeeNo());
        }


        if (mData.get(position).getEmployeeName().equals("") || mData.get(position).getEmployeeName().length() <0 )
        {
            holder.emp_name.setText("N/A");
        }

        else
        {
            holder.emp_name.setText(mData.get(position).getEmployeeName());
        }







//        if (mData.get(position).getWorkHours().equals("") || mData.get(position).getWorkHours().length() <0 )
//        {
//            holder.work_hours.setText("N/A");
//        }
//
//        else
//        {
//            holder.work_hours.setText(mData.get(position).getWorkHours());
//        }







//        if (mData.get(position).getAttendanceDate().equals("") || mData.get(position).getAttendanceDate().length() <0 )
//        {
//            holder.attendace.setText("N/A");
//        }
//
//        else
//        {
//            holder.attendace.setText(mData.get(position).getAttendanceDate());
//        }
//
//        if (mData.get(position).getDateIn().equals("") || mData.get(position).getDateIn().length() <0)
//        {
//            holder.date_in.setText("N/A");
//        }
//
//        else
//        {
//            holder.date_in.setText(mData.get(position).getDateIn());
//        }


//        if (mData.get(position).getDateOut().equals("") || mData.get(position).getDateOut().length() <0)
//        {
//            holder.date_out.setText("N/A");
//        }
//
//        else
//        {
//            holder.date_out.setText(mData.get(position).getDateOut());
//        }
//
//
//
//        if (mData.get(position).getTimeIn().equals("") || mData.get(position).getTimeIn().length()<0)
//        {
//            holder.time_in.setText("N/A");
//        }
//
//        else
//        {
//            holder.time_in.setText(mData.get(position).getTimeIn());
//        }
//
//        if (mData.get(position).getTimeOut().equals("") || mData.get(position).getTimeOut().length()<0)
//        {
//            holder.time_out.setText("N/A");
//        }
//
//        else
//        {
//            holder.time_out.setText(mData.get(position).getTimeOut());
//        }
//
//
//        if (mData.get(position).getShift().equals("")  || mData.get(position).getShift().length()<0 )
//        {
//            holder.shift.setText("N/A");
//        }
//
//        else
//        {
//            holder.shift.setText(mData.get(position).getShift());
//        }





//
//        if (mData.get(position).getShiftTiming().equals("") || mData.get(position).getShiftTiming().length()<0 )
//        {
//            holder.shift_timing.setText("N/A");
//        }
//
//        else
//        {
//            holder.shift_timing.setText(mData.get(position).getShiftTiming());
//        }




        if (mData.get(position).getRemarks().equals("") || mData.get(position).getRemarks().length()<0)
        {
            holder.remarks.setText("N/A");
        }

        else
        {
            holder.remarks.setText(mData.get(position).getRemarks());
        }



        if (mData.get(position).getAttendanceDate().equals("") || mData.get(position).getAttendanceDate().length()<0)
        {
            holder.attendance_date.setText("N/A");
        }

        else
        {
            holder.attendance_date.setText(mData.get(position).getAttendanceDate());
        }




//        if (remarkss.equals("")|| remarkss.length() <0)
//        {
//            remarks.setText("N/A");
//        }
//
//        else
//        {
//            remarks.setText(remarkss);
//
//        }



        holder.table_one.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                final View layout = inflater.inflate(R.layout.dialog_change_password6, null );
                final Vector<AlertDialog> listOfDialogs = new Vector<AlertDialog>();







                TextView time_in_is= (TextView) layout.findViewById(R.id.time_in_is);
                TextView time_out_is= (TextView) layout.findViewById(R.id.time_out_is);
                TextView  start_date_is= (TextView) layout.findViewById(R.id.date_in_is);
                TextView  end_in_is= (TextView) layout.findViewById(R.id.date_out_is);
                TextView shift_timing= (TextView) layout.findViewById(R.id.shift_timing);
                TextView work_hours= (TextView) layout.findViewById(R.id.work_hours);
                TextView status_is= (TextView) layout.findViewById(R.id.status_is);
                TextView remarks= (TextView) layout.findViewById(R.id.remarks);









                if (mData.get(position).getTimeIn().equals("") || mData.get(position).getTimeIn().length() <0)
                {
                    time_in_is.setText("N/A");
                }
                else
                {
                    time_in_is.setText(mData.get(position).getTimeIn());
                }





                if (mData.get(position).getTimeOut().equals("") || mData.get(position).getTimeOut().length() <0)
                {
                    time_out_is.setText("N/A");
                }
                else
                {
                    time_out_is.setText(mData.get(position).getTimeOut());
                }



                if (mData.get(position).getDateIn().equals("") || mData.get(position).getDateIn().length() <0)
                {
                    start_date_is.setText("N/A");
                }
                else
                {
                    start_date_is.setText(mData.get(position).getDateIn());
                }





                if (mData.get(position).getDateOut().equals("") || mData.get(position).getDateOut().length() <0)
                {
                    end_in_is.setText("N/A");
                }
                else
                {
                    end_in_is.setText(mData.get(position).getDateOut());
                }



                if (mData.get(position).getShiftTiming().equals("") || mData.get(position).getShiftTiming().length() <0)
                {
                    shift_timing.setText("N/A");
                }
                else
                {
                    shift_timing.setText(mData.get(position).getShiftTiming());
                }







                if (mData.get(position).getWorkHours().equals("") || mData.get(position).getWorkHours().length() <0)
                {
                    work_hours.setText("N/A");
                }
                else
                {
                    work_hours.setText(mData.get(position).getWorkHours());
                }





                if (mData.get(position).getShift().equals("") || mData.get(position).getShift().length() <0)
                {
                    status_is.setText("N/A");
                }
                else
                {
                    status_is.setText(mData.get(position).getShift());
                }



                if (mData.get(position).getRemarks().equals("") || mData.get(position).getRemarks().length() <0)
                {
                    remarks.setText("N/A");
                }
                else
                {
                    remarks.setText(mData.get(position).getRemarks());
                }





                AlertDialog.Builder aDialog = new AlertDialog.Builder(mContext);
                aDialog.setView(layout);
                final AlertDialog ad = aDialog.create();
//            listOfDialogs.add(ad);
                ad.show();




            }
        });




        holder.manual_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(mContext ,fragment_manual_entry_form.class);
                intent.putExtra("Attendance_Date" , mData.get(position).getAttendanceDate());
                mContext.startActivity(intent);

//                Intent intent = new Intent(mContext ,Manual_Entry_Screen.class);
//                intent.putExtra("Attendance_Date" , mData.get(position).getAttendanceDate());
//                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
//        if (mData.size()==0)
//        {
//            alterText.setVisibility(View.GONE);
//            Toast.makeText(mContext, "No Empty", Toast.LENGTH_SHORT).show();
////            Periodic_attendance_result periodic_attendance_result=new Periodic_attendance_result();
////            periodic_attendance_result.alterText.setVisibility(View.GONE);
//        }
//
//        else
//        {
//            alterText.setVisibility(View.VISIBLE);
////            Periodic_attendance_result periodic_attendance_result=new Periodic_attendance_result();
////            periodic_attendance_result.alterText.setVisibility(View.VISIBLE);
//        }

        return mData.size();
    }

    public void clear()
    {
        final int size = mData.size();
        mData.clear();
        notifyItemRangeRemoved(0,size);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

//        private ConstraintLayout view;
//        private int backgroundIndex = 0;

        TextView employeeNo,emp_name,remarks,attendance_date ;
        //        ImageView img_thumbnail;
//        LinearLayout view_container;
        Button   manual_enter;

        RelativeLayout table_one;

        public MyViewHolder(View itemView) {


            super(itemView);
//            client = new OkHttpClient();
//            this.backgroundIndex = backgroundIndex;
//            view = (ConstraintLayout) itemView.findViewById(R.id.item2);

            table_one= (RelativeLayout) itemView.findViewById(R.id.view_foreground);

            employeeNo = itemView.findViewById(R.id.ed_employee_number);
            emp_name = itemView.findViewById(R.id.emp_name);
            remarks = itemView.findViewById(R.id.remarks);

            attendance_date = itemView.findViewById(R.id.attendance_date);


//            work_hours = itemView.findViewById(R.id.work_hours);
//
////            view_container = itemView.findViewById(R.id.container);
//            attendace = itemView.findViewById(R.id.attendance);
//            date_in = itemView.findViewById(R.id.date_in);
//            time_in = itemView.findViewById(R.id.time_in);
//            date_out = itemView.findViewById(R.id.date_out);
//            time_out = itemView.findViewById(R.id.time_out);
//            shift = itemView.findViewById(R.id.shift);
//            shift_timing = itemView.findViewById(R.id.shift_time);
//            remarks = itemView.findViewById(R.id.remarks);


            manual_enter = (Button) itemView.findViewById(R.id.manual_enter);
            manual_enter.setVisibility(View.GONE);


//            img_thumbnail = itemView.findViewById(R.id.thumbnail);

        }
    }

}
