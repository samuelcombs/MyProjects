/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgramUtil;

import User.user.SkillList;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

/**
 *
 * @author Tyler Jurczak
 */
public class SkillListUtil {

    public static SkillList populateSkillListArrayFromApp(int skillID, int userID, String timeWithSkill, String skillDescription, String skillName) {
        SkillList skillList = new SkillList();
        if (skillID != 0) {
            LocalDate then = LocalDate.parse(timeWithSkill);
            LocalDate now = LocalDate.now();
            Period period = Period.between(then, now);
            skillList.setYears(period.getYears());
            skillList.setMonths(period.getMonths());
            skillList.setSkillID(skillID);
            skillList.setUserID(userID);
            skillList.setTimeWithSkill(timeWithSkill);
            skillList.setSkillDescription(skillDescription);
            skillList.setSkillName(skillName);
        }
        return skillList;
    }
}