package com.example.tisapp;


public class Periodic {

    private String employeeNo;
    private String employeeName;
    private String attendanceDate;
    private String dateIn;
    private String timeIn;
    private String  WorkHours;
    private String dateOut;
    private String timeOut ;
    private String shift;

    private String shiftTiming;
    private String remarks;


    public Periodic() {
    }

    public Periodic(String employeeNo, String employeeName, String attendanceDate, String WorkHours, String dateIn, String timeIn, String dateOut, String timeOut, String shift, String shiftTiming, String remarks) {
        this.employeeNo = employeeNo;
        this.employeeName = employeeName;
        this.dateIn = dateIn;
        this.timeIn = timeIn;
        this.WorkHours=WorkHours;
        this.dateOut = dateOut;
        this.timeOut = timeOut;
        this.shift = shift;
        this.shiftTiming = shiftTiming;
        this.remarks = remarks;

    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }
    public String getEmployeeNo() {
        return employeeNo;
    }


    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    public String getEmployeeName() {
        return employeeName;
    }


    public void setWorkHours(String WorkHours) {
        this.WorkHours = WorkHours;
    }
    public String getWorkHours() {
        return WorkHours;
    }

    public void setAttendanceDate(String attendanceDate) {
        this.attendanceDate = attendanceDate;
    }
    public String getAttendanceDate() {
        return attendanceDate;
    }

    public void setDateIn(String dateIn) {
        this.dateIn = dateIn;
    }
    public String getDateIn() {
        return dateIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }
    public String getTimeIn() {
        return timeIn;
    }

    public void setDateOut(String dateOut) {
        this.dateOut = dateOut;
    }
    public String getDateOut() {
        return dateOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }
    public String getTimeOut() {
        return timeOut;
    }

    public String getShift() {
        return shift;
    }
    public void setShift(String shift) {
        this.shift = shift;
    }

    public void setShiftTiming(String shiftTiming) {
        this.shiftTiming = shiftTiming;
    }
    public String getShiftTiming() {
        return shiftTiming;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public String getRemarks() {
        return remarks;
    }
}
