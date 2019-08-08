/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DBconnection.ConnectionPool;
import DBconnection.UsersDB;
import User.user.Applicant;
import User.user.SkillList;
import User.user.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Caleb/Tyler
 */
@WebServlet(name = "RSI", urlPatterns = {"/RSI"})
public class RSI extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/index.jsp";
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        switch (action) {
            case "loginUser":
                url = logInMethod(request);
                break;
            case "logOut":
                url = "/index.jsp";
                break;
            case "deactivate":
                url = "/deactivate.jsp";
                viewApplicant(request);
                User user = viewUser(request);
                break;
            case "submit":
                url = finalCheckApplicant(request);
                break;
            case "deleteList":
                deleteSkillListSelection(request);
                url = "/UserPage.jsp";
                break;
            case "addSkill":
                url = addSkillToList(request);
                break;

        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/index.jsp";
        String action = request.getParameter("action");
        switch (action) {
            case "submit":
                url = postMethod(request);
                break;
            case "confirmDeactive":
                url = deactivation(request);
                break;
            case "sqlGateway":
                url = AdminSQL(request);
                break;
            case "search":
                url = searchStatement(request);
                break;

        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /**
     * Method to build a user object, and use it for logging in. Object is left
     * open ended for later editing if needed.
     *
     * @param request
     * @return
     */
    private String logInMethod(HttpServletRequest request) {
        HttpSession session = request.getSession();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<Applicant> applicants;
        applicants = (ArrayList<Applicant>) session.getAttribute("applicants");
        if (applicants == null) {
            applicants = DBconnection.ApplicantsDB.selectApplicants();
        }
        String url = "";
        Applicant applicant = null;
        User user = null;
        String userIDStr = request.getParameter("userID");
        if (userIDStr != null) {
            user = viewUser(request);
        }
        String applicantIDStr = request.getParameter("applicantID");
        if (applicantIDStr != null) {
            applicant = (Applicant) session.getAttribute("applicant");
            session.setAttribute("applicant", applicant);
        }
        if (applicant == null) {
            if (user == null) {
                String employeeNumber = request.getParameter("employeeNumber");
                String password = request.getParameter("password");
                user = UsersDB.createUser(employeeNumber, password);
                session.setAttribute("user", user);
                if (user == null && applicant == null) {
                    url = "/index.jsp";
                    String logInMessage = "Password or User ID is incorrect";
                    session.setAttribute("logInMessage", logInMessage);
                } else {
                    List<Applicant> allApplicants = DBconnection.ApplicantsDB.selectAllApplicants();
                    for (Applicant app : allApplicants) {
                        if (user.getUserID() == app.getUserID()) {
                            applicant = app;
                            String startDateInProject = applicant.getTimeInCurrentProject();
                            LocalDate then = LocalDate.parse(startDateInProject);
                            LocalDate now = LocalDate.now();
                            Period period = Period.between(then, now);
                            String dateAdded;
                            if (applicant.getActive() == false) {
                                dateAdded = dtf.format(now);
                                applicant.setDateAdded(dateAdded);
                            } else {
                                dateAdded = applicant.getDateAdded();
                            }
                            LocalDate then2 = LocalDate.parse(dateAdded);
                            Period period2 = Period.between(then2, now);
                            applicant.setYearsOnProject(period.getYears());
                            applicant.setMonthsOnProject(period.getMonths());
                            applicant.setYearsOnList(period2.getYears());
                            applicant.setMonthsOnList(period2.getMonths());
                            applicant.setAccess(user.getAccess());
                            applicant.setCurrentLocationID(DBconnection.UsersDB.getLocation(applicant.getCurrentWorkLocation()));
                            applicant.setCurrentPracticeID(DBconnection.UsersDB.getArea(applicant.getCurrentPracticeArea()));
                            url = "/UserPage.jsp";
                            session.setAttribute("applicant", applicant);
                            setDropDowns(request);
                            session.setAttribute("allApplicants", allApplicants);
                            break;
                        }
                    }
                    if (user.getAccess().equalsIgnoreCase("hr") && applicant == null) {
                        url = "/HR.jsp";
                        applicants = DBconnection.ApplicantsDB.selectApplicants();
                        session.setAttribute("applicants", applicants);
                        session.setAttribute("user", user);
                        setDropDowns(request);

                    } else if (user.getAccess().equalsIgnoreCase("admin") && applicant == null) {
                        applicants = DBconnection.ApplicantsDB.selectApplicants();
                        session.setAttribute("applicants", applicants);
                        session.setAttribute("user", user);
                        url = "/Admin.jsp";
                        setDropDowns(request);
                    } else if (user.getAccess().equalsIgnoreCase("app") && applicant == null) {
                        applicant = new Applicant();
                        url = "/UserPage.jsp";
                        applicant.setCurrentLocationID(user.getCurrentWorkLocation());
                        applicant.setCurrentPracticeID(user.getCurrentPracticeArea());
                        applicant.setFirstName(user.getFirstName());
                        applicant.setMiddleInitial(user.getMiddleInitial());
                        applicant.setLastName(user.getLastName());
                        applicant.setSuffix(user.getSuffix());
                        applicant.setUserID(user.getUserID());
                        applicant.setEmails(user.getEmails());
                        applicant.setAccess(user.getAccess());
                        applicant.setCurrentWorkLocation(UsersDB.getWorkLocation(user.getCurrentWorkLocation()));
                        applicant.setCurrentPracticeArea(UsersDB.getDepartment(user.getCurrentPracticeArea()));
                        applicant.setTimeInCurrentProject(user.getTimeInCurrentProject());
                        applicant.setActive(true);
                        String startDateInProject = user.getTimeInCurrentProject();
                        LocalDate then = LocalDate.parse(startDateInProject);
                        LocalDate now = LocalDate.now();
                        Period period = Period.between(then, now);
                        String dateAdded = dtf.format(now);
                        LocalDate then2 = LocalDate.parse(dateAdded);
                        applicant.setDateAdded(dateAdded);
                        Period period2 = Period.between(then2, now);
                        applicant.setYearsOnProject(period.getYears());
                        applicant.setMonthsOnProject(period.getMonths());
                        applicant.setYearsOnList(period2.getYears());
                        applicant.setMonthsOnList(period2.getMonths());
                        applicants.add(applicant);
                        session.setAttribute("applicants", applicants);
                        session.setAttribute("applicant", applicant);
                        setDropDowns(request);
                    }
                }

            } else if (user != null && user.getAccess().equalsIgnoreCase("hr")) {
                url = "/HR.jsp";
                session.setAttribute("applicants", applicants);
                session.setAttribute("applicant", applicant);
                session.setAttribute("user", user);
                setDropDowns(request);
            }
        } else if (applicant.getAccess().equalsIgnoreCase("app")) {
            url = "/UserPage.jsp";
            session.setAttribute("applicants", applicants);
            session.setAttribute("applicant", applicant);
            setDropDowns(request);
        }

        return url;
    }

    private String postMethod(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String url = "/ThankYou.jsp";
        Applicant applicant = (Applicant) session.getAttribute("applicant");
        ArrayList<SkillList> skillListArray = applicant.getSkillArrayList();
        int userID = applicant.getUserID();
        String dateAdded = applicant.getDateAdded();
        boolean active = true;
        int desiredLocationID = applicant.getDesiredLocationID();
        int desiredAreaID = applicant.getDesiredAreaID();
        int counter = 0;
        String dateRemoved = null;
        List<Applicant> applicants = DBconnection.ApplicantsDB.selectAllApplicants();
        for (Applicant app : applicants) {
            if (userID == app.getUserID()) {
                DBconnection.ApplicantsDB.updateApplicant(userID, dateAdded, active, desiredLocationID, desiredAreaID, dateRemoved);
                DBconnection.SkillListDB.updateUserSkills(userID, skillListArray);
                break;
            } else {
                counter++;
            }
        }
        if (applicants.size() == counter) {
            DBconnection.ApplicantsDB.insertApplicant(userID, dateAdded, active, desiredLocationID, desiredAreaID, dateRemoved);
            DBconnection.SkillListDB.updateUserSkills(userID, skillListArray);
        }
        return url;
    }

    private User viewUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            session.setAttribute("user", user);
            return user;
        }
        return null;
    }

    private void viewApplicant(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int applicantID = Integer.parseInt(request.getParameter("selectedApplicantID"));
        ArrayList<Applicant> applicants = (ArrayList<Applicant>) session.getAttribute("applicants");
        for (Applicant app : applicants) {
            if (applicantID == (app.getUserID())) {
                Applicant applicant = app;
                session.setAttribute("applicant", applicant);
            }
        }
    }

    private String finalCheckApplicant(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Applicant applicant = (Applicant) session.getAttribute("applicant");
        String desiredLocation = request.getParameter("desiredLocation");
        String desiredArea = request.getParameter("desiredArea");
        String url;
        String message = "";
        if (desiredLocation.isEmpty()) {
            message += "Must pick a desired location. \n";
        }
        if (desiredArea.isEmpty()) {
            message += "Pust pick a desired area. \n";
        }
        if (message.equals("")) {

            int desiredLocationID = DBconnection.UsersDB.getLocation(desiredLocation);
            int desiredAreaID = DBconnection.UsersDB.getArea(desiredArea);
            applicant.setDesiredLocationID(desiredLocationID);
            applicant.setDesiredAreaID(desiredAreaID);
            applicant.setDesiredArea(desiredArea);
            applicant.setDesiredLocation(desiredLocation);
            session.setAttribute("applicant", applicant);
            url = "/Confirmation.jsp";
        } else {
            if (!desiredArea.isEmpty()) {
                applicant.setDesiredArea(desiredArea);
            }
            if (!desiredLocation.isEmpty()) {
                applicant.setDesiredLocation(desiredLocation);
            }
            session.setAttribute("message", message);
            session.setAttribute("applicant", applicant);
            setDropDowns(request);
            url = "/UserPage.jsp";
        }
        return url;
    }

    private String deactivation(HttpServletRequest request) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        String dateRemoved = dtf.format(now);
        HttpSession session = request.getSession();
        ArrayList<Applicant> applicants = (ArrayList<Applicant>) session.getAttribute("applicants");
        Applicant applicant = (Applicant) session.getAttribute("applicant");
        int userID = applicant.getUserID();
        for (Applicant app : applicants) {
            if (userID == app.getUserID()) {
                ProgramUtil.ApplicantUserUtil.deactivateApplicant(applicant, applicants, dateRemoved);
                applicants.remove(app);
                break;
            }
        }
        request.setAttribute("applicants", applicants);
        String url = "/HR.jsp";
        return url;
    }

    private String AdminSQL(HttpServletRequest request) {
        String sqlStatement = request.getParameter("sqlStatement");
        String sqlResult = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            ConnectionPool pool = ConnectionPool.getInstance();
            try (Connection connection = pool.getConnection()) {
                ResultSet resultSet;
                try (
                        Statement statement = connection.createStatement()) {
                    sqlStatement = sqlStatement.trim();
                    if (sqlStatement.length() >= 6) {
                        String sqlType = sqlStatement.substring(0, 6);
                        if (sqlType.equalsIgnoreCase("select")) {
                            resultSet
                                    = statement.executeQuery(sqlStatement);
                            sqlResult = ProgramUtil.SQLUtil.getHtmlTable(resultSet);
                            resultSet.close();
                        } else {
                            int i = statement.executeUpdate(sqlStatement);
                            if (i == 0) {
                                sqlResult
                                        = "<p>The statement executed successfully.</p>";
                            } else {
                                sqlResult
                                        = "<p>The statement executed successfully.<br>"
                                        + i + " row(s) affected.</p>";
                            }
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            sqlResult = "<p>Error loading the databse driver: <br>"
                    + e.getMessage() + "</p>";
        } catch (SQLException e) {
            sqlResult = "<p>Error executing the SQL statement: <br>"
                    + e.getMessage() + "</p>";
        }
        HttpSession session = request.getSession();
        session.setAttribute("sqlResult", sqlResult);
        session.setAttribute("sqlStatement", sqlStatement);

        String url = "/Admin.jsp";
        return url;
    }

    private String searchStatement(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Applicant> applicants;
        String SQLAddition = "";
        String url = "/HR.jsp";
        User user = (User) session.getAttribute("user");
        Applicant applicant = (Applicant) session.getAttribute("applicant");
        request.setAttribute("user", user);
        String currentLocationSearch = request.getParameter("currentLocationSearch");
        if (currentLocationSearch == null) {
            currentLocationSearch = "";
        }
        String timeOnProjectSearch = request.getParameter("timeOnProjectSearch");
        if (timeOnProjectSearch == null) {
            timeOnProjectSearch = "";
        }
        String skillNameSearch = request.getParameter("skillNameSearch");
        if (skillNameSearch == null) {
            skillNameSearch = "";
        }
        if (skillNameSearch.equals("Other")){
            String skillDescriptionSearch = request.getParameter("skillDescription");
            if (skillDescriptionSearch == null) {
                skillDescriptionSearch = "";
            } else {
                skillNameSearch = skillDescriptionSearch;
            }
        }
        String timeOnSkillSearch = request.getParameter("timeOnSkillSearch");
        if (timeOnSkillSearch == null) {
            timeOnSkillSearch = "";
        }
        String desiredLocationSearch = request.getParameter("desiredLocationSearch");
        if (desiredLocationSearch == null) {
            desiredLocationSearch = "";
        }
        String desiredProjectSearch = request.getParameter("desiredProjectSearch");
        if (desiredProjectSearch == null) {
            desiredProjectSearch = "";
        }
        SQLAddition += "WHERE active = 1  ";
        if (currentLocationSearch.equals("") && timeOnProjectSearch.equals("") && skillNameSearch.equals("")
                && timeOnSkillSearch.equals("") && desiredLocationSearch.equals("") && desiredProjectSearch.equals("")) {
            applicants = DBconnection.searchDB.selectApplicants(SQLAddition);
            session.setAttribute("applicants", applicants);
            return "/HR.jsp";
        } else {
            if (!currentLocationSearch.equals("")) {
                int currentLocationID = DBconnection.UsersDB.getLocation(currentLocationSearch);
                SQLAddition += " AND currentWorkLocation = " + currentLocationID;
            }

            if (!timeOnProjectSearch.equals("")) {
                int timeInt = isInt(timeOnProjectSearch) ? Integer.parseInt(timeOnProjectSearch) : 0;
                LocalDate now = LocalDate.now();
                LocalDate newDate = now.minus(Period.ofYears(timeInt));
                SQLAddition += " AND timeInCurrentProject < " + "'" + newDate + "'";
            }

            if (!desiredLocationSearch.equals("")) {
                int desiredLocationID = DBconnection.UsersDB.getLocation(desiredLocationSearch);
                SQLAddition += " AND app1.desiredLocation = " + desiredLocationID;
            }

            if (!desiredProjectSearch.equals("")) {
                int desiredProjectID = DBconnection.UsersDB.getArea(desiredProjectSearch);
                SQLAddition += " AND app1.desiredArea = " + desiredProjectID;
            }
            applicants = DBconnection.searchDB.selectApplicants(SQLAddition);
            List<Applicant> applicants2 = new ArrayList<>();
            List<Applicant> applicants3 = new ArrayList<>();

            if (!skillNameSearch.equals("")) {
                for (Applicant app : applicants) {
                    for (SkillList skillList : app.getSkillArrayList()) {
                        if (skillList.getSkillDescription() == null) {
                            skillList.setSkillDescription("");
                        }
                        if (skillList.getSkillName().contains(skillNameSearch) || skillList.getSkillDescription().contains(skillNameSearch)
                                || skillList.getSkillName().equalsIgnoreCase(skillNameSearch) || skillList.getSkillDescription().equalsIgnoreCase(skillNameSearch)) {
                            applicants2.add(app);
                        }
                    }
                }
                applicants = applicants2;
            }

            if (!timeOnSkillSearch.equals("")) {
                int timeInt = isInt(timeOnSkillSearch) ? Integer.parseInt(timeOnSkillSearch) : 0;
                LocalDate now = LocalDate.now();
                LocalDate newDate = now.minus(Period.ofYears(timeInt));
                if (!skillNameSearch.equals("")) {
                    for (Applicant app : applicants) {
                        for (SkillList skillList : app.getSkillArrayList()) {
                            LocalDate timeWithSkill = LocalDate.parse(skillList.getTimeWithSkill());
                            if (timeWithSkill.isBefore(newDate) && (skillList.getSkillName().contains(skillNameSearch)
                                    || skillList.getSkillDescription().contains(skillNameSearch) || skillList.getSkillName().equalsIgnoreCase(skillNameSearch)
                                    || skillList.getSkillDescription().equalsIgnoreCase(skillNameSearch))) {
                                applicants3.add(app);
                            }
                        }
                    }
                    applicants = applicants3;
                }
            }
            session.setAttribute("applicants", applicants);
            return url;
        }

    }

    public boolean isInt(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private void deleteSkillListSelection(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Applicant> applicants = (ArrayList<Applicant>) session.getAttribute("applicants");
        Applicant app = (Applicant) session.getAttribute("applicant");
        int applicantID = app.getUserID();
        for (Applicant applicant : applicants) {
            if (applicantID == applicant.getUserID()) {
                applicants.remove(app);
                break;
            }
        }
        int skillID = Integer.parseInt(request.getParameter("skillID"));
        String skillDescription = request.getParameter("skillDescription");

        ArrayList<SkillList> skillListArray = null;
        skillListArray = app.getSkillArrayList();
        for (SkillList skillItem : skillListArray) {
            if (skillID == skillItem.getSkillID() && !skillItem.getSkillName().equals("Other")) {
                skillListArray.remove(skillItem);
                break;
            } else if (skillItem.getSkillName().equals("Other")) {
                if (skillDescription.equals(skillItem.getSkillDescription())) {
                    skillListArray.remove(skillItem);
                    break;
                }
            }
        }
        session.setAttribute("applicant", app);
        applicants.add(app);
        session.setAttribute("applicants", applicants);
        setDropDowns(request);
    }

    private String addSkillToList(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Applicant app = (Applicant) session.getAttribute("applicant");
        List<Applicant> allApplicants = (ArrayList<Applicant>) session.getAttribute("allApplicants");
        if (allApplicants == null) {
            allApplicants = (ArrayList<Applicant>) session.getAttribute("applicants");
        }
        String skillName = request.getParameter("skillName");
        String skillDescription = request.getParameter("skillDescription");
        String message = "";
        int years = isInt(request.getParameter("years")) ? Integer.parseInt(request.getParameter("years")) : -1;
        int months = isInt(request.getParameter("months")) ? Integer.parseInt(request.getParameter("months")) : -1;
        if (years < 0) {
            message += "You must pick valid years. \n";
        }
        if (months < 0) {
            message += "You must pick valid months. \n";
        }
        if (skillName.equals("")) {
            message += "Pick a skill name. \n";
        }
        if (skillName.equals("Other") && skillDescription.isEmpty()) {
            message += "Pick a skill name. \n";
        }
        if (message.equals("")) {

        }
        if (message.isEmpty()) {
            int skillID = DBconnection.SkillListDB.getSkillID(skillName);
            int userID = app.getUserID();
            LocalDate now = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            int allMonths = (years * 12) + months;
            LocalDate skillStartDate = now.minus(allMonths, ChronoUnit.MONTHS);
            String timeWithSkill = skillStartDate.format(formatter);
            SkillList skillList = new SkillList();
            skillList.setSkillName(skillName);
            skillList.setSkillDescription(skillDescription);
            skillList.setSkillID(skillID);
            skillList.setUserID(userID);
            skillList.setYears(years);
            skillList.setMonths(months);
            skillList.setTimeWithSkill(timeWithSkill);
            ArrayList<SkillList> newArray = new ArrayList<>();
            newArray.add(skillList);
            message = "";
            for (Applicant applicant : allApplicants) {
                if (userID == (applicant.getUserID())) {
                    ArrayList<SkillList> oldArray = applicant.getSkillArrayList();
                    if (oldArray == null) {
                        applicant.setSkillArrayList(newArray);
                    } else {
                        oldArray.add(skillList);
                    }
                    session.setAttribute("applicant", applicant);
                }
            }
        } else {
            session.setAttribute("message2", message);
        }
        String url = "/UserPage.jsp";
        session.setAttribute("message2", message);
        session.setAttribute("allApplicants", allApplicants);
        setDropDowns(request);
        return url;
    }

    public void setDropDowns(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Integer> intList;
        List<String> skillList;
        List<String> departmentList;
        List<String> locationList;
        intList = DBconnection.PopulationDB.populateIntList();
        skillList = DBconnection.PopulationDB.populateSkillList();
        departmentList = DBconnection.PopulationDB.populateDepartmentList();
        locationList = DBconnection.PopulationDB.populateLocationList();
        session.setAttribute("intList", intList);
        session.setAttribute("skillList", skillList);
        session.setAttribute("departmentList", departmentList);
        session.setAttribute("locationList", locationList);
    }
}
