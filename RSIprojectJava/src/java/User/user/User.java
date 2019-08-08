/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User.user;

import java.io.Serializable;


/**
 *
 * @author Tyler/Caleb
 */
public class User implements Serializable {

    private int userID;
    private String firstName;
    private String lastName;
    private String middleInitial;
    private String suffix;
    private int currentWorkLocation;
    private int currentPracticeArea;
    private String access;
    private String emails;
    private String timeInCurrentProject;

    public User() {
        userID = 0;
        firstName = "";
        lastName = "";
        middleInitial = "";
        suffix = "";
        currentWorkLocation = 0;
        currentPracticeArea = 0;
        access = "";
        emails = "";
        timeInCurrentProject = "";
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public int getCurrentWorkLocation() {
        return currentWorkLocation;
    }

    public void setCurrentWorkLocation(int currentWorkLocation) {
        this.currentWorkLocation = currentWorkLocation;
    }

    public int getCurrentPracticeArea() {
        return currentPracticeArea;
    }

    public void setCurrentPracticeArea(int currentPracticeArea) {
        this.currentPracticeArea = currentPracticeArea;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }
    
    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }
    
    public String getTimeInCurrentProject() {
        return timeInCurrentProject;
    }

    public void setTimeInCurrentProject(String timeInCurrentProject) {
        this.timeInCurrentProject = timeInCurrentProject;
    }
}