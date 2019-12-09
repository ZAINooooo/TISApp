package com.example.tisapp;


public class PendingLeaveApprover {

    private String requestID;
    private String leaveCode;
    private String employeeNo;
    private String employeeName;
    private String reason;
    private String startDate;
    private String endDate ;
    private String noofdays ;
    private String status;



    public PendingLeaveApprover() {
    }

    public PendingLeaveApprover(String requestID, String leaveCode, String employeeNo,String employeeName,String reason,String startDate, String endDate, String noofdays,  String status) {

        this.requestID = requestID;
        this.leaveCode = leaveCode;
        this.employeeNo = employeeNo;
        this.employeeName = employeeName;
        this.reason = reason;
        this.startDate = startDate;
        this.endDate = endDate;
        this.noofdays = noofdays;
        this.status = status;
    }


    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }
    public String getRequestID() {
        return requestID;
    }



    public void setLeaveCode(String leaveCode) {
        this.leaveCode = leaveCode;
    }
    public String getLeaveCode() {
        return leaveCode;
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


    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getReason() {
        return reason;
    }


    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getStartDate() {
        return startDate;
    }



    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getEndDate() {
        return endDate;
    }


    public void setNoofdays(String noofdays) {
        this.noofdays = noofdays;
    }
    public String getNoofdays() {
        return noofdays;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}
