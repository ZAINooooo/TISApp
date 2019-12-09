package com.example.tisapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ReportsModule extends AppCompatActivity {



    Button manual_entry,manual_time_out_is,manual_time_in_is,manual_list,manual_approver_list,manual_offline_data;

    ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports_module);

        manual_time_in_is=(Button) findViewById(R.id.btnDomestic);
        manual_time_out_is=(Button) findViewById(R.id.btnCommercial);
        manual_entry=(Button) findViewById(R.id.btnIndustrial);


        iv_back =(ImageView) findViewById(R.id.iv_back);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });


        manual_time_in_is.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                startActivity(new Intent(ReportsModule.this, fragment_daily_atendance.class));
                finish();

            }
        });


        manual_time_out_is.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                startActivity(new Intent(ReportsModule.this, fragment_periodic_attendance.class));
                finish();

            }

            ;

        });



        manual_entry.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                startActivity(new Intent(ReportsModule.this, fragment_periodic_attendance_summary.class));
                finish();

            }

            ;

        });



    }







    }

