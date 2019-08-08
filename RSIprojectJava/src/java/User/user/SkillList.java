/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User.user;

import java.io.Serializable;

/**
 *
 * @author Tyler Jurczak
 */
public class SkillList implements Serializable {

    private int skillID;
    private int userID;
    private String timeWithSkill;
    private String skillName;
    private String skillDescription;
    private int years;
    private int months;

    public SkillList() {

        skillID = 0;
        userID = 0;
        timeWithSkill = "";
        skillName = "";
        skillDescription = "";
        years = 0;
        months = 0;
    }

    public int getSkillID() {
        return skillID;
    }

    public void setSkillID(int skillID) {
        this.skillID = skillID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getTimeWithSkill() {
        return timeWithSkill;
    }

    public void setTimeWithSkill(String timeWithSkill) {
        this.timeWithSkill = timeWithSkill;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillDescription() {
        return skillDescription;
    }

    public void setSkillDescription(String skillDescription) {
        this.skillDescription = skillDescription;
    }
    
    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }
}
