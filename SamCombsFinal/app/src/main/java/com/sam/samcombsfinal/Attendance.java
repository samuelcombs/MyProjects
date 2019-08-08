package com.sam.samcombsfinal;

public class Attendance {
    private String eventAddress;
    private String eventName;
    private String employeeNum;
    private String eventCheckIn;
    private String eventDate;
    private String eventPhone;
    private String eventLocation;

    public Attendance(String eventAddress,String eventName,String employeeNum,String eventCheckIn,String eventDate,String eventPhone,String eventLocation) {
        this.eventAddress = eventAddress;
        this.eventName = eventName;
        this.employeeNum = employeeNum;
        this.eventCheckIn = eventCheckIn;
        this.eventDate = eventDate;
        this.eventPhone = eventPhone;
        this.eventLocation = eventLocation;
    }

    public Attendance() {
    }

    public String getEventAddress() {
        return eventAddress;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public String getEmployeeNum() {
        return employeeNum;
    }

    public String getEventCheckIn() {
        return eventCheckIn;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getEventPhone() {
        return eventPhone;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    public void setEventCheckIn(String eventCheckIn) {
        this.eventCheckIn = eventCheckIn;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public void setEventPhone(String eventPhone) {
        this.eventPhone = eventPhone;
    }
}
