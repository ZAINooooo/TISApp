package com.example.tisapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2/28/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "com.example.tisapp.DatabaseHelper";

    public static final String TABLE_PROFILE = "profile_info";
    public static final String TABLE_NAME = "manualin_manual_out";
    public static final String TABLE_ENTITLED_LEAVE = "leave_entitled";
    public static final String TABLE_LEAVE_FORM = "leaveform";


    public static final String COL1 = "ID";

    public static final String COL2 = "DateIn";
    public static final String COL222222 = "AttendanceDate";

    public static final String COL3 = "TimeIn";
    public static final String COL4 = "DateOut";
    public static final String COL5 = "TimeOut";
    public static final String COL6 = "Reason";
    public static final String COL7 = "CustomerInfo";
    public static final String COL8 = "xCoordinates";
    public static final String COL9 = "YCoordinates";
    public static final String COL10 = "type";
    public static final String COL11 = "IsSync";
    public static final String COL12 = "Response";
    public static final String DBVERSION = "2";
    public static final String TYPE_MANUAl_TIME_IN = "ManualTimeIn";
    public static final String TYPE_MANUAl_TIME_OUT = "ManualTimeOut";
    public static final String TYPE_MANUAl_TIME_REQUEST = "ManualTimeRequest";


    public static final String COL13 = "EMPLOYEE_NO";
    public static final String COL14 = "EMPLOYEE_NAME";
    public static final String COL15 = "DESIGNATION";
    public static final String COL16 = "DEPARTMENT";
    public static final String COL17 = "isapprover";

    public static final String COL223  = "leaveidd";

    public static final String COL23  = "leavecode";
    public static final String COL24  = "leavename";


    public static final String COL188 = "LeaveId";
    public static final String COL18 = "LeaveCode";

    public static final String COL19 = "FromDate";
    public static final String COL20 = "ToDate";
    public static final String COL21 = "FullDayLeave";
    public static final String COL22  = "Remarks";
    public static final String COL222  = "SyncLeave";
    public static final String COL2233  = "LeaveResponse";



    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " ("+COL1+" INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 +" TEXT , "  + COL3 +" TEXT , " + COL4 +" TEXT,"  + COL5 +" TEXT  , "  + COL6 +" TEXT, "  + COL7 +" TEXT, "  + COL8 +" TEXT, "  + COL9 +" TEXT, "  + COL10 +" TEXT , "  + COL11 + " TEXT,"  + COL222222 + " TEXT," + COL12 + " TEXT )";
        db.execSQL(createTable);

        String createProfileTable = "CREATE TABLE " + TABLE_PROFILE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL13 +" TEXT , "  + COL14 +" TEXT , " + COL15 +" TEXT,"  + COL16 +" TEXT  , "  + COL17 + " TEXT )";
        db.execSQL(createProfileTable);

        String createEntitledCode = "CREATE TABLE " + TABLE_ENTITLED_LEAVE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL223 +" TEXT , "    + COL23 +" TEXT  , "   + COL24 + " TEXT )";
        db.execSQL(createEntitledCode);



//        String createLEAVETable = "CREATE TABLE " + TABLE_LEAVE_FORM + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL18 +" TEXT , " + COL19 +" TEXT,"  + COL20 +" TEXT  , " + COL21 + " TEXT ," + COL22 + " TEXT , " + COL222 + " TEXT ,"  + COL2233 + " TEXT )";
//        db.execSQL(createLEAVETable);

        String createLEAVETable = "CREATE TABLE " + TABLE_LEAVE_FORM + " ("+COL1+" INTEGER PRIMARY KEY AUTOINCREMENT, " + COL18 +" TEXT , " + COL19 +" TEXT,"  + COL20 +" TEXT  , " + COL21 + " TEXT ," + COL22 + " TEXT , " + COL222 + " TEXT ,"  + COL2233 + " TEXT )";
        db.execSQL(createLEAVETable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME + DBVERSION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE + DBVERSION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENTITLED_LEAVE + DBVERSION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEAVE_FORM + DBVERSION);



        onCreate(db);
    }

//    public boolean addData(String date_in,String date_out, String time_in, String time_out,String reason , String customer_info ,double xcordinate,double ycordinate ,String type,String issync,String reponse) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL2, date_in);
//        contentValues.put(COL3, date_out);
//        contentValues.put(COL4, time_in);
//        contentValues.put(COL5, time_out);
//        contentValues.put(COL6, reason);
//        contentValues.put(COL7, customer_info);
//        contentValues.put(COL8, xcordinate);
//        contentValues.put(COL9, ycordinate);
//        contentValues.put(COL10, type);
//        contentValues.put(COL11, issync);
//        contentValues.put(COL12, reponse);
//
//        Log.d(TAG, "addData:Adding " + date_in  +  time_in+time_out+reason+customer_info+xcordinate+ycordinate+type+issync+reponse+ " to " + TABLE_NAME);
//
//        long result = db.insert(TABLE_NAME, null, contentValues);
//
//        //if date as inserted incorrectly it will return -1
//        if (result == -1) {
//            return false;
//        } else {
//            return true;
//        }
//    }




    public void insertleaveentitled(List<SpinnerData> list) {



        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (SpinnerData bdo : list) {

                values.put("leaveName", bdo.getLeaveName());
                values.put("leaveCode", bdo.getLeaveCode());

                Log.d("abc", "addData:AddingEntoitledLeave " + "" +  bdo.getLeaveCode()  +  "" + bdo.getLeaveName()+ " to " + TABLE_ENTITLED_LEAVE);

                db.insert(TABLE_ENTITLED_LEAVE, null, values);

            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
//            e("ERROR", "DB > "+TABLE_ENTITLED_LEAVE +" > ERROR :> " + e.getLocalizedMessage());
        } finally {
            db.endTransaction();
        }
    }


    public long insertProfileData(String employeeNo,String employeeName,String designation,String department,String isApprover){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL13,employeeNo);
        contentValues.put(COL14,employeeName);
        contentValues.put(COL15,designation);
        contentValues.put(COL16,department);
        contentValues.put(COL17,isApprover);

        Log.d("abc", "addData:AddingProfile " + employeeNo  +  employeeName+designation+department+isApprover+ " to " + TABLE_PROFILE);
        long id=db.insert(TABLE_PROFILE,null,contentValues);

        return id;
    }





    public boolean IsDataExistBetweenFromToDate(String from_date, String to_date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        //TODO Changes i made
        String query = "SELECT * FROM " + TABLE_LEAVE_FORM +" WHERE ((substr('"+from_date+"',7)||substr('"+from_date+"',4,2)||substr('"+from_date+"',1,2) >= substr("+COL19+",7)||substr("+COL19+",4,2)||substr("+COL19+",1,2)) AND (substr('"+to_date+"',7)||substr('"+to_date+"',4,2)||substr('"+to_date+"',1,2) <= substr("+COL20+",7)||substr("+COL20+",4,2)||substr("+COL20+",1,2))) OR (substr('"+from_date+"',7)||substr('"+from_date+"',4,2)||substr('"+from_date+"',1,2) <= substr("+COL20+",7)||substr("+COL20+",4,2)||substr("+COL20+",1,2) AND substr('"+to_date+"',7)||substr('"+to_date+"',4,2)||substr('"+to_date+"',1,2) >= substr("+COL19+",7)||substr("+COL19+",4,2)||substr("+COL19+",1,2))" ;
        Cursor c = db.rawQuery(query, null);

        if (c.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }



    public long insertleavedata(String LeaveCode,String FromDate,String ToDate,Boolean FullDayLeave,String remarks,String syncStatus){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL18,LeaveCode);
        contentValues.put(COL19,FromDate);
        contentValues.put(COL20,ToDate);
        contentValues.put(COL21,FullDayLeave);
        contentValues.put(COL22,remarks);
        contentValues.put(COL222,syncStatus);
//        contentValues.put(COL2233,leaveresponse);


        Log.d("abc", "addData:AddingLeave " + LeaveCode  +  FromDate+ToDate+FullDayLeave+remarks+ syncStatus+" to " + TABLE_LEAVE_FORM);
        long id=db.insert(TABLE_LEAVE_FORM,null,contentValues);

        return id;
    }




    public Cursor getLeaveFormData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_LEAVE_FORM;
        Log.d("DataAll" , query);

        Cursor data = db.rawQuery(query, null);

//        data.moveToFirst();
//        String t = data.getString(0);

        return data;
    }





    public boolean addData(String AttendanceDate, String date_in,String time_in,                             String date_out,String time_out,String reason , String customer_info ,String xcordinate,String ycordinate ,String type,String issync,String reponse)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL222222, AttendanceDate);
        contentValues.put(COL2, date_in);
        contentValues.put(COL3, time_in);
        contentValues.put(COL4, date_out);
        contentValues.put(COL5, time_out);
        contentValues.put(COL6, reason);
        contentValues.put(COL7, customer_info);
        contentValues.put(COL8, xcordinate);
        contentValues.put(COL9, ycordinate);
        contentValues.put(COL10, type);
        contentValues.put(COL11, issync);
        contentValues.put(COL12, reponse);

        Log.d(TAG, "addData:Adding " +AttendanceDate  +  "      "     +    date_in  +     "      "       +  time_in    +        "      "    +      "           " +  date_out        +         time_out      +              "              "          +            reason            +            "          "            +             "           "      +        "            "        +       customer_info+xcordinate+ycordinate+type+issync+reponse+ " to " + TABLE_NAME);

        //Check if data is already exists



        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }




    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getOfflineData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL11+ "='false'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    public Cursor getOfflineLeaveData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_LEAVE_FORM + " WHERE " + COL222+ "='false'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    public Cursor getFilteredOfflineData(String type){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL11+ "='false' AND "+COL10+"='"+type+"'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }



    public Cursor getFilteredOfflineLeaveData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_LEAVE_FORM + " WHERE " + COL222+ "='false' AND "+COL1+"='"+id+"'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }




    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME + " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }







    public void delAllRecord() {
        delTableByName(TABLE_ENTITLED_LEAVE);
    }



    private void delTableByName(String TABLE_NAME)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);
//        db.close();
    }


    public void updateName(String newName, int id, String oldName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 + " = '" + newName + "' WHERE " + COL1 + " = '" + id + "'" + " AND " + COL2 + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }

    public void updateStatus(int id, String status)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL11 + " = '" + status + "' WHERE " + COL1 + " = '" + id + "'";
        db.execSQL(query);
    }



    public void updateLeaveStatus(int id, String status)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_LEAVE_FORM + " SET " + COL222 + " = '" + status + "' WHERE " + COL1 + " = '" + id + "'";
        db.execSQL(query);
    }



    /**
     * Delete from database
     * @param id
     * @param name
     */
    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }
    public void delete(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'";
        Log.d(TAG, "deleteName: query: " + query);
        db.execSQL(query);
    }

    @SuppressLint("LongLogTag")
    public void deleteleave(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_LEAVE_FORM + " WHERE " + COL1 + " = '" + id + "'";
        Log.d(TAG, "deleteName: query: " + query);
        db.execSQL(query);
    }


    public boolean IsDataExist(String date_In){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT Count(*) FROM " + TABLE_NAME + " WHERE " + COL2+ "='"+date_In+"'";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        if(c.getInt(0) > 0){
            return true;
        }else{
            return false;
        }
    }


    public boolean IsDataExist2(String date_out){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT Count(*) FROM " + TABLE_NAME + " WHERE " + COL4+ "='"+date_out+"'";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        if(c.getInt(0) > 0){
            return true;
        }else{
            return false;
        }
    }




    public boolean IsDataExistAttendance(String date_in){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT Count(*) FROM " + TABLE_NAME + " WHERE " + COL2+ "='"+date_in+"'";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        if(c.getInt(0) > 0){
            return true;
        }else{
            return false;
        }
    }



    public Cursor getDataProfile()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PROFILE;
        return db.rawQuery(query, null);
    }

    public ArrayList<SpinnerData> getSpinnerData() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<SpinnerData> list = new ArrayList<>();
        if (db != null) {
            String Query = "SELECT leaveCode,leaveName" + " FROM "+TABLE_ENTITLED_LEAVE;

            Cursor cursor = db.rawQuery(Query, null);
            try {
                if (cursor.moveToFirst()) {
                    do {
                        SpinnerData bdo = new SpinnerData();
                        bdo.setLeaveCode(cursor.getString(0));
                        bdo.setLeaveName(cursor.getString(1));

                        list.add(bdo);
                    } while (cursor.moveToNext());
                }
            } catch (Exception ignored) {
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
//                db.close();
            }
        }
        return list;
    }




  /*  public boolean IsDataExistAttendance(String attendance_date){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT Count(*) FROM " + TABLE_NAME + " WHERE " + COL222222+ "='"+attendance_date+"'";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        if(c.getInt(0) > 0){
            return true;
        }else{
            return false;
        }
    }*/

}
























