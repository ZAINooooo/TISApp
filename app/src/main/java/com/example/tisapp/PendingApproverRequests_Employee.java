package com.example.tisapp;


public class PendingApproverRequests_Employee {

    private String requestID;
    private String employeeNo;
    private String employeeName;
    private String attendanceDate;
    private String dateIn;
    private String timeIn;
    private String dateOut ;
    private String timeOut;
    private String status;
    private String reason;
    private String customerInfo;
    private String approvalStatus1;
    private String remarks1;
    private String approvalStatus2;
    private String remarks2;
    private String approvalStatus3;
    private String remarks3;
    private String approvalStatus4;
    private String remarks4;
    private String approvalDate;
    private String updateDateTime;
    private String posted;
//    private String gpsCoordinates;
    private Double xcordinates;
    private Double ycordinates;


    private String approverField;


    public PendingApproverRequests_Employee() {
    }

    public PendingApproverRequests_Employee(String requestID, String employeeNo, String employeeName, String attendanceDate, String dateIn, String timeIn, String dateOut, String timeOut, String status, String reason
            , String customerInfo, String approvalStatus1, String remarks1, String approvalStatus2, String remarks2, String approvalStatus3,
                                            String remarks3, String approvalStatus4, String remarks4, String approvalDate, String updateDateTime, String posted
            , Double xcordinates, Double ycordinates, String approverField) {

        this.requestID = requestID;
        this.employeeNo = employeeNo;
        this.employeeName = employeeName;
        this.attendanceDate = attendanceDate;
        this.dateIn = dateIn;
        this.timeIn = timeIn;
        this.dateOut = dateOut;
        this.timeOut = timeOut;
        this.status = status;
        this.reason = reason;
        this.customerInfo = customerInfo;
        this.approvalStatus1 = approvalStatus1;
        this.remarks1 = remarks1;
        this.approvalStatus2 = approvalStatus2;
        this.remarks2 = remarks2;
        this.approvalStatus3 = approvalStatus3;
        this.remarks3 = remarks3;
        this.approvalStatus4 = approvalStatus4;
        this.remarks4 = remarks4;
        this.approvalDate = approvalDate;
        this.updateDateTime = updateDateTime;
        this.posted = posted;
        this.xcordinates = xcordinates;
        this.ycordinates = ycordinates;
        this.approverField = approverField;



//        this.chkIn = chkIn;






//        this.description = description;



//        this.finalStatus = finalStatus;

//        this.chkOut = chkOut;

//        this.employeeName = employeeName;

    }


    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }
    public String getRequestID() {
        return requestID;
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

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }
    public String getTimeIn() {
        return timeIn;
    }




    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }
    public String getTimeOut() {
        return timeOut;
    }



//
//    public void setChkIn(String chkIn) {
//        this.chkIn = chkIn;
//    }
//    public String getChkIn() {
//        return chkIn;
//    }







    public void setDateIn(String dateIn) {
        this.dateIn = dateIn;
    }
    public String getDateIn() {
        return dateIn;
    }



    public void setDateOut(String dateOut) {
        this.dateOut = dateOut;
    }
    public String getDateOut() {
        return dateOut;
    }



    public void setAttendanceDate(String attendanceDate) {
        this.attendanceDate = attendanceDate;
    }
    public String getAttendanceDate() {
        return attendanceDate;
    }



    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }



    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getReason() {
        return reason;
    }












//    public void setDescription(String description) {
//        this.description = description;
//    }
//    public String getDescription() {
//
//        return description;
//    }



    public void setApprovalStatus1(String approvalStatus1) {
        this.approvalStatus1 = approvalStatus1;
    }
    public String getApprovalStatus1() {
        return approvalStatus1;
    }




    public void setRemarks1(String remarks1) {
        this.remarks1 = remarks1;
    }
    public String getRemarks1() {
        return remarks1;
    }




    public void setApprovalStatus2(String approvalStatus2) {
        this.approvalStatus2 = approvalStatus2;
    }
    public String getApprovalStatus2() {
        return approvalStatus2;
    }



    public void setRemarks2(String remarks2) {
        this.remarks2 = remarks2;
    }
    public String getRemarks2() {
        return remarks2;
    }







    public void setApprovalStatus3(String approvalStatus3) {
        this.approvalStatus3 = approvalStatus3;
    }
    public String getApprovalStatus3() {
        return approvalStatus3;
    }



    public void setRemarks3(String remarks3) {
        this.remarks3 = remarks3;
    }
    public String getRemarks3() {
        return remarks3;
    }







    public void setApprovalStatus4(String approvalStatus4) {
        this.approvalStatus4 = approvalStatus4;
    }
    public String getApprovalStatus4() {
        return approvalStatus4;
    }



    public void setRemarks4(String remarks4) {
        this.remarks4 = remarks4;
    }
    public String getRemarks4() {
        return remarks4;
    }














//    public void setFinalStatus(String finalStatus) {
//        this.finalStatus = finalStatus;
//    }
//    public String getFinalStatus() {
//        return finalStatus;
//    }



    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }
    public String getApprovalDate() {
        return approvalDate;
    }




    public void setUpdateDateTime(String updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
    public String getUpdateDateTime() {
        return updateDateTime;
    }



    public void setPosted(String posted) {
        this.posted = posted;
    }
    public String getPosted() {
        return posted;
    }

//
//    public void setGpsCoordinates(String gpsCoordinates) {
//        this.gpsCoordinates = gpsCoordinates;
//    }
//    public String getGpsCoordinates() {
//        return gpsCoordinates;
//    }


        public void setXcordinates(Double xcordinates) {
        this.xcordinates = xcordinates;
    }

    public Double getXcordinates() {
        return xcordinates;
    }



    public void setYcordinates(Double ycordinates) {
        this.ycordinates = ycordinates;
    }

    public Double getYcordinates() {
        return ycordinates;
    }






    public void setCustomerInfo(String customerInfo) {
        this.customerInfo = customerInfo;
    }
    public String getCustomerInfo() {
        return customerInfo;
    }



    public void setApproverField(String approverField) {
        this.approverField = approverField;
    }
    public String getApproverField() {
        return approverField;
    }



//    public void setEmployeeName(String employeeName) {
//        this.employeeName = employeeName;
//    }
//    public String getEmployeeName() {
//        return employeeName;
//    }








}
