package com.sam.samcombsfinal;

public class Employee {
    private String activeStatus;
    private String cardId;
    private String locationNum;
    private String employeeNum;
    private String employeeName;

    public Employee(String activeStatus,String cardId,String locationNum,String employeeNum,String employeeName) {
        this.activeStatus = activeStatus;
        this.cardId = cardId;
        this.locationNum = locationNum;
        this.employeeNum = employeeNum;
        this.employeeName = employeeName;
    }

    public Employee() {
    }

    public String getActiveStatus() {
        return activeStatus;
    }

    public String getCardId() {
        return cardId;
    }

    public String getLocationNum() {
        return locationNum;
    }

    public String getEmployeeNum() {
        return employeeNum;
    }

    public String getEmployeeName() {
        return employeeName;
    }
}
