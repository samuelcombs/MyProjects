/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBconnection;

import ProgramUtil.DBUtil;
import ProgramUtil.DatesUtil;
import User.user.SkillList;
import User.user.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Caleb/Tyler
 */
public class UsersDB {

    /**
     * Method to create a user object to access data, using name and password to log in
     * @param userNumber log in name
     * @param userPassword log in password
     * @return 
     */
    public static User createUser(String userNumber, String userPassword) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        User user = null;
        String sql = "SELECT * "
                + "FROM users "
                + "WHERE userNumber=? AND userPassword=?";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, userNumber);
            statement.setString(2, userPassword);
            result = statement.executeQuery();

            while (result.next()) {
                user = new User();
                user.setUserID(result.getInt("userID"));
                user.setFirstName(result.getString("firstName"));
                user.setLastName(result.getString("lastName"));
                user.setMiddleInitial(result.getString("middleInitial"));
                user.setSuffix(result.getString("suffix"));
                user.setCurrentPracticeArea(result.getInt("currentPracticeArea"));
                user.setCurrentWorkLocation(result.getInt("currentWorkLocation"));
                user.setTimeInCurrentProject(result.getString("timeInCurrentProject"));
                user.setAccess(result.getString("access"));
                return user;
            }
        } catch (SQLException e) {
            System.err.println(e);
            user = null;
            return user;
        }finally {
            DBUtil.closeResultSet(result);
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(connection);
        }

        return user;
    }
    
    public static List<User> selectUsers() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        List<User> users = new ArrayList<>();
        String sql = "SELECT * "
                + "FROM users ";

        try {
            statement = connection.prepareStatement(sql);
            result = statement.executeQuery();
            User user = null;

            while (result.next()) {
                user = new User();
                user.setUserID(result.getInt("userID"));
                user.setFirstName(result.getString("firstName"));
                user.setLastName(result.getString("lastName"));
                user.setMiddleInitial(result.getString("middleInitial"));
                user.setSuffix(result.getString("suffix"));
                user.setCurrentPracticeArea(result.getInt("currentPracticeArea"));
                user.setCurrentWorkLocation(result.getInt("currentWorkLocation"));
                user.setTimeInCurrentProject(result.getString("timeInCurrentProject"));
                user.setAccess(result.getString("access"));
                user.setEmails(result.getString("emails"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }finally {
            DBUtil.closeResultSet(result);
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(connection);
        }
    }

    public static int getLocation(String location) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        String sql = "Select * "
                + "FROM locations "
                + "WHERE city=? AND state=?";
        try {
            statement = connection.prepareStatement(sql);
            String[] cityState = location.split(",");
            String city = cityState[0];
            String state = cityState[1].substring(1);
            statement.setString(1, city);
            statement.setString(2, state);
            result = statement.executeQuery();
            while (result.next()) {
                return result.getInt("locationID");
            }
        } catch (SQLException e) {
            System.err.println(e);
            return 0;
        }finally {
            DBUtil.closeResultSet(result);
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(connection);
        }
        return 0;
    }

    /**
     * Gets a specific location from DB based on the entered locationID, then puts into viewable String
     * @param workLocation int ID used to get a printable string
     * @return 
     */
    public static String getWorkLocation(int workLocation) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        String sql = "SELECT city, state "
                + "FROM locations WHERE "
                + "locationID=?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, workLocation);
            result = statement.executeQuery();
            while (result.next()) {
                return result.getString("city") + ", " + result.getString("state");
            }

        } catch (SQLException e) {
            System.err.println(e);
            return "";
        }finally {
            DBUtil.closeResultSet(result);
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(connection);
        }
        return "";
    }

    /**
     * Gets a specific departmentID from DB based on the entered location
     * @param department department name used to get departmentID from DB
     * @return 
     */
    public static String getDepartment(int department) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        String sql = "SELECT departmentName "
                + "FROM departments WHERE "
                + "departmentID=?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, department);
            result = statement.executeQuery();
            while (result.next()) {
                return result.getString("departmentName");
            }

        } catch (SQLException e) {
            System.err.println(e);
            return "";
        }finally {
            DBUtil.closeResultSet(result);
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(connection);
        }
        return "";
    }


    /**
     * Gets a specific location from DB based on the entered departmentID, then puts into viewable String
     * @param area
     * @return 
     */
    public static int getArea(String area) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        String sql = "Select departmentID "
                + "FROM departments "
                + "WHERE departmentName =?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, area);
            result = statement.executeQuery();
            while (result.next()) {
                return result.getInt("departmentID");
            }
        } catch (SQLException e) {
            return 0;
        } finally {
            DBUtil.closeResultSet(result);
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(connection);
        }
        return 0;
        
    } 
}
