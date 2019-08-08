/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBconnection;

import ProgramUtil.DBUtil;
import User.user.Applicant;
import User.user.SkillList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TJurc
 */
public class searchDB {
    
    private static List<Applicant> applicants = null;

    /**
     * Accessed connection pool to access DB and query to retrieve all info on
     * the applicant that is stored in the DB
     *
     * @return applicants gives an array list of the applicant object
     */
    public static List<Applicant> selectApplicants(String SQLAddition) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        applicants = new ArrayList<>();
        String sql = "SELECT firstName, middleInitial, lastName, suffix, us1.UserID, emails, timeInCurrentProject, \n"
                + "CONCAT(loc1.city, ', ', loc1.state) AS currentWorkLocation, dep1.departmentName, \n"
                + " app1.active, app1.dateadded, dep2.departmentID, dep2.departmentName, loc2.locationID, \n"
                + " CONCAT(loc2.city, ', ', loc2.state) \n"
                + " AS desiredLocation\n"
                + "FROM users us1 \n"
                + "INNER JOIN applicant app1 \n"
                + "ON app1.userID=us1.userID  \n"
                + "INNER JOIN locations loc1 \n"
                + "ON loc1.locationID=us1.currentWorkLocation  \n"
                + "INNER JOIN locations loc2 \n"
                + "ON loc2.locationID=app1.desiredLocation  \n"
                + "INNER JOIN departments dep1 \n"
                + "ON dep1.departmentID=us1.currentPracticeArea  \n"
                + "INNER JOIN departments dep2 \n"
                + "ON dep2.departmentID=app1.desiredArea   "
                + SQLAddition + ";";
        try {
            statement = connection.prepareStatement(sql);
            result = statement.executeQuery();
            Applicant applicant;
            ArrayList<SkillList> skillListArray = null;
            while (result.next()) {
                applicant = new Applicant();
                applicant.setFirstName(result.getString("firstName"));
                applicant.setLastName(result.getString("lastName"));
                applicant.setMiddleInitial(result.getString("middleInitial"));
                applicant.setSuffix(result.getString("suffix"));
                applicant.setCurrentWorkLocation(result.getString("currentWorkLocation"));
                applicant.setCurrentPracticeArea(result.getString("dep1.departmentName"));
                applicant.setActive(result.getBoolean("app1.active"));
                applicant.setTimeInCurrentProject(result.getString("timeInCurrentProject"));
                applicant.setDateAdded(result.getString("app1.dateadded"));
                String startDateInProject = result.getString("timeInCurrentProject");
                LocalDate then = LocalDate.parse(startDateInProject);
                LocalDate now = LocalDate.now();
                Period period = Period.between(then, now);
                String dateAdded = result.getString("app1.dateadded");
                LocalDate then2 = LocalDate.parse(dateAdded);
                applicant.setDateAdded(dateAdded);
                Period period2 = Period.between(then2, now);
                applicant.setYearsOnProject(period.getYears());
                applicant.setMonthsOnProject(period.getMonths());
                applicant.setYearsOnList(period2.getYears());
                applicant.setMonthsOnList(period2.getMonths());
                applicant.setDesiredAreaID(result.getInt("dep2.departmentID"));
                applicant.setDesiredArea(result.getString("dep2.departmentName"));
                applicant.setDesiredLocationID(result.getInt("loc2.locationID"));
                applicant.setDesiredLocation(result.getString("desiredLocation"));
                applicant.setUserID(result.getInt("us1.UserID"));
                applicant.setEmails(result.getString("emails"));
                skillListArray = SkillListDB.populateUserSkillListFromDB(result.getInt("us1.UserID"));
                applicant.setSkillArrayList(skillListArray);
                applicants.add(applicant);
            }
            return applicants;

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(result);
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(connection);
        }
    }
    
}
