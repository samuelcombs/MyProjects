/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User.user;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Tyler Jurczak
 */
public class Applicant implements Serializable {
    private int userID;
    private String firstName;
    private String lastName;
    private String middleInitial;
    private String suffix;
    private String currentWorkLocation;
    private int currentLocationID;
    private String currentPracticeArea;
    private int currentPracticeAreaID;
    private String dateAdded;
    private String timeInCurrentProject;
    private int desiredAreaID;
    private String desiredArea;
    private int desiredLocationID;
    private String desiredLocation;
    private boolean active;
    private ArrayList<SkillList> skillArrayList;
    private String emails;
    private String access;
    private int yearsOnList;
    private int monthsOnList;
    private int yearsOnProject;
    private int monthsOnProject;
    
    public Applicant() {
        userID = 0;
        firstName = "";
        lastName = "";
        middleInitial = "";
        suffix = "";
        currentWorkLocation = "";
        currentLocationID = 0;
        currentPracticeArea = "";
        currentPracticeAreaID = 0;
        active = false;
        dateAdded = "";
        timeInCurrentProject = "";
        desiredAreaID = 0;
        desiredArea = "";
        desiredLocationID = 0;
        desiredLocation = "";
        skillArrayList = null;
        emails = "";
        access = "";
        yearsOnList = 0;
        monthsOnList = 0;
        yearsOnProject = 0;
        monthsOnProject = 0;
       
    }
    
    public int getUserID(){
        return userID;
    }
    
    public void setUserID(int userID){
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

    public String getCurrentWorkLocation() {
        return currentWorkLocation;
    }

    public void setCurrentWorkLocation(String currentWorkLocation) {
        this.currentWorkLocation = currentWorkLocation;
    }
    
    public int getCurrentLocationID(){
        return currentLocationID;
    }
    
    public void setCurrentLocationID(int currentLocationID){
        this.currentLocationID = currentLocationID;
    }

    public String getCurrentPracticeArea() {
        return currentPracticeArea;
    }

    public void setCurrentPracticeArea(String currentPracticeArea) {
        this.currentPracticeArea = currentPracticeArea;
    }
    
    public int getCurrentPracticeID(){
        return currentPracticeAreaID;
    }
    
    public void setCurrentPracticeID(int currentPracticeAreaID){
        this.currentPracticeAreaID = currentPracticeAreaID;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getTimeInCurrentProject() {
        return timeInCurrentProject;
    }

    public void setTimeInCurrentProject(String timeInCurrentProject) {
        this.timeInCurrentProject = timeInCurrentProject;
    }
    
    public int getDesiredAreaID(){
        return desiredAreaID;
    }
    
    public void setDesiredAreaID(int desiredAreaID){
        this.desiredAreaID = desiredAreaID;
    }

    public String getDesiredArea() {
        return desiredArea;
    }

    public void setDesiredArea(String desiredArea) {
        this.desiredArea = desiredArea;
    }

    public int getDesiredLocationID(){
        return desiredLocationID;
    }
    
    public void setDesiredLocationID(int desiredLocationID){
        this.desiredLocationID = desiredLocationID;
    }
    
    public String getDesiredLocation() {
        return desiredLocation;
    }

    public void setDesiredLocation(String desiredLocation) {
        this.desiredLocation = desiredLocation;
    }
    
    public ArrayList<SkillList> getSkillArrayList(){
        return skillArrayList;
    }
    
    public void setSkillArrayList(ArrayList<SkillList> skillArrayList){
        this.skillArrayList = skillArrayList;
    }
    
    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }
    
    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }
    
    public int getYearsOnList(){
        return yearsOnList;
    }
    
    public void setYearsOnList(int yearsOnList){
        this.yearsOnList = yearsOnList;
    }
    
    public int getMonthsOnList(){
        return monthsOnList;
    }
    
    public void setMonthsOnList(int monthsOnList){
        this.monthsOnList = monthsOnList;
    }
    
    public int getYearsOnProject(){
        return yearsOnProject;
    }
    
    public void setYearsOnProject(int yearsOnProject){
        this.yearsOnProject = yearsOnProject;
    }
    
    public int getMonthsOnProject(){
        return monthsOnProject;
    }
    
    public void setMonthsOnProject(int monthsOnProject){
        this.monthsOnProject = monthsOnProject;
    }
}