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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @authors Calab/Tyler
 */
public class ApplicantsDB {

    private static List<Applicant> applicants = null;

    /**
     * Accessed connection pool to access DB and query to retrieve all info on
     * the applicant that is stored in the DB
     *
     * @return applicants gives an array list of the applicant object
     */
    public static List<Applicant> selectApplicants() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        applicants = new ArrayList<>();
        String sql = "SELECT firstName, middleInitial, lastName, suffix, access, us1.UserID, emails, timeInCurrentProject, \n"
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
                + "ON dep2.departmentID=app1.desiredArea \n"
                + "WHERE app1.active = 1;  ";
        try {
            statement = connection.prepareStatement(sql);
            result = statement.executeQuery();
            Applicant applicant = null;
            ArrayList<SkillList> skillListArray = null;
            while (result.next()) {
                applicant = new Applicant();
                applicant.setFirstName(result.getString("firstName"));
                applicant.setLastName(result.getString("lastName"));
                applicant.setMiddleInitial(result.getString("middleInitial"));
                applicant.setSuffix(result.getString("suffix"));
                applicant.setAccess(result.getString("access"));
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
    
    public static List<Applicant> selectAllApplicants() {
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
                + "ON dep2.departmentID=app1.desiredArea;  ";
        try {
            statement = connection.prepareStatement(sql);
            result = statement.executeQuery();
            Applicant applicant = null;
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

    public static Applicant selectApplicant(int userID, String dateAdded, boolean active, String desiredLocation, int desiredLocationID,
            String desiredArea, int desiredAreaID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        applicants = new ArrayList<>();
        String sql = "SELECT firstName, middleInitial, lastName, userID, emails, suffix, access, timeInCurrentProject, \n"
                + "CONCAT(loc1.city, ', ', loc1.state) AS currentWorkLocation, dep1.departmentName \n"
                + "FROM users us1  \n"
                + "INNER JOIN locations loc1 \n"
                + "ON loc1.locationID=us1.currentWorkLocation  \n"
                + "INNER JOIN departments dep1 \n"
                + "ON dep1.departmentID=us1.currentPracticeArea  \n"
                + "WHERE userID = ?;  ";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userID);
            result = statement.executeQuery();
            Applicant applicant = new Applicant();
            while (result.next()) {
                applicant = new Applicant();
                applicant.setFirstName(result.getString("firstName"));
                applicant.setLastName(result.getString("lastName"));
                applicant.setMiddleInitial(result.getString("middleInitial"));
                applicant.setSuffix(result.getString("suffix"));
                applicant.setCurrentWorkLocation(result.getString("currentWorkLocation"));
                applicant.setCurrentPracticeArea(result.getString("dep1.departmentName"));
                applicant.setTimeInCurrentProject(result.getString("timeInCurrentProject"));
                applicant.setEmails(result.getString("emails"));
                applicant.setUserID(userID);
                applicant.setDateAdded(dateAdded);
                applicant.setAccess(result.getString("access"));
                applicant.setActive(active);
                applicant.setDesiredLocation(desiredLocation);
                applicant.setDesiredLocationID(desiredLocationID);
                applicant.setDesiredArea(desiredArea);
                applicant.setDesiredAreaID(desiredAreaID);

            }
            return applicant;

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(result);
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(connection);
        }
    }

    /**
     * Function to insert a new applicant into the respected tables
     *
     * @param userID - ID of user, anchor to whole database
     * @param dateAdded - SQL date used to display time worked without pinging
     * DB daily
     * @param timeInCurrentProject - SQL date used to display time worked
     * without pinging DB daily
     * @param active - boolean to determine if the applicant gets populated to
     * HR list
     * @param desiredLocation - locationID used to pull forth name of location
     * @param desiredArea - departmentID used to pull forth name of area
     * @return
     */
    public static int insertApplicant(int userID, String dateAdded, boolean active, int desiredLocation, int desiredArea, String dateRemoved) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        String sql = "INSERT INTO applicant (userID, dateadded,  active, "
                + "desiredLocation, desiredArea, dateRemoved) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userID);
            statement.setString(2, dateAdded);
            statement.setBoolean(3, active);
            statement.setInt(4, desiredLocation);
            statement.setInt(5, desiredArea);
            statement.setString(6, dateRemoved);
            return statement.executeUpdate();
        } catch (SQLException exception) {
            System.err.println(exception);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(connection);
        }
    }

    public static int updateApplicant(int userID, String dateAdded, boolean active, int desiredLocation, int desiredArea, String dateRemoved) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        String sql = "UPDATE applicant SET  "
                + "dateadded = ?, active = ?, desiredLocation = ?, desiredArea = ?, dateRemoved = ?  "
                + "WHERE userID = ?;";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, dateAdded);
            statement.setBoolean(2, active);
            statement.setInt(3, desiredLocation);
            statement.setInt(4, desiredArea);
            statement.setString(5, dateRemoved);
            statement.setInt(6, userID);
            return statement.executeUpdate();
        } catch (SQLException exception) {
            System.err.println(exception);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(connection);
        }
    }

    public static int deactiveDB(int userID, String dateRemoved) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        String sql = "UPDATE applicant  "
                + "SET active = 0, dateRemoved = ?  "
                + "WHERE userID=?;";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, dateRemoved);
            statement.setInt(2, userID);
            return statement.executeUpdate();
        } catch (SQLException exception) {
            System.err.println(exception);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(connection);
        }
    }
    
    /**
     * Select desired Practice areas of applicant the number of applicants
     * for each area. Pushes data to ArrayList of Hashmap objects.
     * @return ArrayList resultList
     */
    public static List<HashMap<String, Object>> applicantsDesiredArea(){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        String sql = "select departments.departmentName as departmentName, count(departments.departmentName) as count\n"
                + "from applicant\n"
                + "join departments on departments.departmentID = applicant.desiredArea\n"
                + "group by departments.departmentName\n"
                + "order by count desc \n" 
                + "limit 5;";
        try {
            statement = connection.prepareStatement(sql);
            result = statement.executeQuery();
            ResultSetMetaData metaData = result.getMetaData();
            int columns = metaData.getColumnCount();
            List<HashMap<String, Object>> resultList = new ArrayList<>();
            
            while(result.next()){
                HashMap<String, Object> row = new HashMap<>(columns);
                for (int i = 1; i <= columns; i++){
                    row.put(metaData.getColumnLabel(i), result.getObject(i));
                }
                resultList.add(row);
            }
            return resultList;
            
        }catch (SQLException exception) {
            System.err.println(exception);
            return null;
        } finally {
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(connection);
        }
    }
    
    /**
     * Select current practice area of applicants and the number of applicants 
     * in each area. Pushes data to ArrayList of Hashmap objects.
     * @return ArrayList resultList
     */
    public static List<HashMap<String, Object>> applicantsCurrentArea(){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        String sql = "select departments.departmentName as departmentName, count(departments.departmentName) as count\n"
                + "from users\n"
                + "join departments on departments.departmentID = users.currentPracticeArea\n"
                + "where access = \"app\"\n"
                + "group by departments.departmentName\n"
                + "order by count desc \n" 
                + "limit 5;";
        try {
            statement = connection.prepareStatement(sql);
            result = statement.executeQuery();
            ResultSetMetaData metaData = result.getMetaData();
            int columns = metaData.getColumnCount();
            List<HashMap<String, Object>> resultList = new ArrayList<>();
            
            while(result.next()){
                HashMap<String, Object> row = new HashMap<>(columns);
                for (int i = 1; i <= columns; i++){
                    row.put(metaData.getColumnLabel(i), result.getObject(i));
                }
                resultList.add(row);
            }
            return resultList;
            
        }catch (SQLException exception) {
            System.err.println(exception);
            return null;
        } finally {
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(connection);
        }
    }   
}
