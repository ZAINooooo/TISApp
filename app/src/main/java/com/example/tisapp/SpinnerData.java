package com.example.tisapp;


public class SpinnerData {

    private String leaveCode, leaveName;

    public SpinnerData() {
    }

    public SpinnerData(String leaveCode, String leaveName)
    {

        this.leaveCode = leaveCode;
        this.leaveName = leaveName;
    }


    public void setLeaveCode(String leaveCode) {
        this.leaveCode = leaveCode;
    }
    public String getLeaveCode() {
        return leaveCode;
    }

    public void setLeaveName(String leaveName) {
        this.leaveName = leaveName;
    }
    public String getLeaveName() {
        return leaveName;
    }
}
