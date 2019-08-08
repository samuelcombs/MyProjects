/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgramUtil;

import User.user.Applicant;
import User.user.SkillList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tyler Jurczak
 */
public class ApplicantUserUtil {

    private boolean toggleActive(boolean active) {
        boolean checkBox;
        if (active = true) {
            checkBox = false;
        } else {
            checkBox = true;
        }
        return checkBox;
    }

    public static String getTimeForDisplay(int months) {
        String message;
        int years = ProgramUtil.DatesUtil.convertMonthstoYears(months);
        int month = ProgramUtil.DatesUtil.convertLeftOverMonths(months);
        message = ("Years: " + years + "  Months: " + month);
        return message;
    }
    
    public static void deactivateApplicant(Applicant applicant, ArrayList<Applicant> applicants, String dateRemoved) {
        try {
            if (applicant != null) {
                for (Applicant applicantToDeactivate : applicants) {
                    if (applicantToDeactivate.getUserID() == (applicant.getUserID())) {
                        DBconnection.ApplicantsDB.deactiveDB(applicantToDeactivate.getUserID(), dateRemoved);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}