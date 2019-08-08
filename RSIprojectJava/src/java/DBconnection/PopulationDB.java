/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBconnection;

import ProgramUtil.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Tyler Jurczak
 */
public class PopulationDB {

    /**
     * method using connection from pool to get all skills from DB by name
     * @return 
     */
    public static ArrayList<String> populateSkillList() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<String> skillList = new ArrayList<>();
        String sql = "SELECT skillID, skillName "
                + "FROM skills";
        try {
            statement = connection.prepareStatement(sql);
            result = statement.executeQuery();
            while (result.next()) {
                int skillID = result.getInt(1);
                String skillName = result.getString(2);
                skillList.add(skillName);
            }
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }finally {
            DBUtil.closeResultSet(result);
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(connection);
        }
        return skillList;
    }

    /**
     * method using connection from pool to get all departments from DB by name
     * @return 
     */
    public static ArrayList<String> populateDepartmentList() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<String> departmentList = new ArrayList<>();
        String sql = "SELECT departmentID, departmentName "
                + "FROM departments";
        try {
            statement = connection.prepareStatement(sql);
            result = statement.executeQuery();
            while (result.next()) {
                int departmentID = result.getInt(1);
                String departmentName = result.getString(2);
                departmentList.add(departmentName);
            }
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }finally {
            DBUtil.closeResultSet(result);
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(connection);
        }
        return departmentList;
    }

    /**
     * method using connection from pool to get all locations from DB by name
     * @return 
     */
    public static ArrayList<String> populateLocationList() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<String> locationList = new ArrayList<>();
        String sql = "SELECT locationID, city, state "
                + "FROM locations";
        try {
            statement = connection.prepareStatement(sql);
            result = statement.executeQuery();
            while (result.next()) {
                int locationID = result.getInt(1);
                String city = result.getString(2);
                String state = result.getString(3);
                locationList.add(city + ", " + state);
            }
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }finally {
            DBUtil.closeResultSet(result);
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(connection);
        }
        return locationList;
    }
    
    public static ArrayList<Integer> populateIntList() {
    ArrayList<Integer> intList = new ArrayList<>();
    
    for (int counter = 0; counter < 13; counter++){
        intList.add(counter);
    }
    return intList;
    }
}