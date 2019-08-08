/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBconnection;

import ProgramUtil.DBUtil;
import ProgramUtil.DatesUtil;
import User.user.SkillList;
import ProgramUtil.SkillListUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Tyler Jurczak
 */
public class SkillListDB {

    /**
     * Gets individual skill lists arrays for all users that are active and on
     * the HR list
     *
     * @param userIDpass passing userID anchor to keep skill list based on
     * applicant
     * @return
     */
    public static ArrayList<SkillList> populateUserSkillListFromDB(int userIDpass) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        SkillList skillItem;
        ArrayList<SkillList> skillListArray = new ArrayList<>();

        String sql = "SELECT userSkills.skillID, userID, timeWithSkill, skillDescription, skillName\n"
                + "FROM userSkills\n"
                + "JOIN skills\n"
                + "ON userSkills.skillID = skills.skillID "
                + "WHERE userID = ?;";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userIDpass);
            result = statement.executeQuery();
            {
                while (result.next()) {
                    int skillID = result.getInt(1);
                    int userID = result.getInt(2);
                    String timeWithSkill = result.getString(3);
                    String skillDescription = result.getString(4);
                    String skillName = result.getString(5);
                    skillItem = SkillListUtil.populateSkillListArrayFromApp(skillID, userID, timeWithSkill, skillDescription, skillName);
                    skillListArray.add(skillItem);
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(result);
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(connection);
        }
        return skillListArray;
    }

    /**
     * Function to insert a new skillList into the respected table per
     * applicants request
     *
     * @param skillListArray list of skills for particular user being inserted
     * @return
     */
    public static boolean insertSkills(ArrayList skillListArray) {
        SkillList skillObject = new SkillList();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        String sql = "INSERT INTO userskills (userID, skillID, skillDescription, timeWithSkill)  "
                + "VALUES (?, ?, ?, ?);  ";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, skillObject.getSkillID());
            statement.setInt(2, skillObject.getUserID());
            statement.setString(3, skillObject.getSkillDescription());
            statement.setString(4, skillObject.getTimeWithSkill());
            statement.executeUpdate();
            return true;

        } catch (SQLException exception) {
            System.err.println(exception);
            return false;
        } finally {
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(connection);
        }
    }

    public static boolean insertSkill(int userID, int skillID, String skillDescription, String timeWithSkill) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        String sql = "INSERT INTO userSkills (userID, skillID, skillDescription, timeWithSkill)  "
                + "VALUES (?, ?, ?, ?)  ";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userID);
            statement.setInt(2, skillID);
            statement.setString(3, skillDescription);
            statement.setString(4, timeWithSkill);
            statement.executeUpdate();
            return true;

        } catch (SQLException exception) {
            System.err.println(exception);
            return false;
        } finally {
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(connection);
        }
    }

    public static int getSkillID(String skillName) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        String sql = "Select skillID "
                + "FROM skills "
                + "WHERE skillName = ?";
        try {
            statement = connection.prepareStatement(sql);

            statement.setString(1, skillName);
            result = statement.executeQuery();
            while (result.next()) {
                return result.getInt("skillID");
            }
        } catch (SQLException e) {
            System.err.println(e);
            return 0;
        } finally {
            DBUtil.closeResultSet(result);
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(connection);
        }
        return 0;
    }

    public static int updateUserSkills(int userID, ArrayList<SkillList> skillListArray) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ArrayList<SkillList> oldList = populateUserSkillListFromDB(userID);
        String sql = "";
        if (!oldList.isEmpty()) {
            sql = "DELETE FROM userSkills WHERE userID = ? ;";
            try {
                statement = connection.prepareStatement(sql);
                statement.setInt(1, userID);
                return statement.executeUpdate();
            } catch (SQLException e) {
                System.err.println(e);
                return 0;
            } finally {
                for (SkillList skillItem : skillListArray) {
                    int skillID = skillItem.getSkillID();
                    String skillDescription = skillItem.getSkillDescription();
                    String timeWithSkill = skillItem.getTimeWithSkill();
                    insertSkill(userID, skillID, skillDescription, timeWithSkill);
                }
                DBUtil.closePreparedStatement(statement);
                pool.freeConnection(connection);
            }
        } else if (oldList.isEmpty()) {
            for (SkillList skillItem : skillListArray) {
                int skillID = skillItem.getSkillID();
                String skillDescription = skillItem.getSkillDescription();
                String timeWithSkill = skillItem.getTimeWithSkill();
                insertSkill(userID, skillID, skillDescription, timeWithSkill);
            }
        }
        return 1;

    }
}
