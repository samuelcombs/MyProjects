<%-- 
    Document   : dashboard
    Created on : Jun 25, 2019, 5:47:22 PM
    Author     : salee
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="DBconnection.ApplicantsDB"%>
<%@page import="java.util.*" %>
<%@page import="Utils.JSONUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.9.0/css/all.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.9.0/css/v4-shims.css">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="shortcut icon" href="https://www.ruralsourcing.com/wp-content/themes/ruralsourcing/dist/img/favicon.png">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
        <link rel="stylesheet" href="CSS/temp.css">
        <link href="CSS/navbar.css" rel="stylesheet">
    </head>
    <body>        
        <div class="image">
            <div class="container">
                <a href="index.jsp" />
                <image src="Images/RS.jpg"></a>
            </div>
        </div>
        <div class="navbar">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand">Dashboard</a>
                </div>
                
            </div>
        </div>
        <div class="main">
            <div class="icon-bar left col-sm-1">
                <a class="active" href="index.jsp" title="Logout"><i class="fa fa-sign-out"></i></a>                
                <a class="active" href="HR.jsp" title="Applicant List"><i class="fas fa-th-list"></i></a>                
                <c:if test="${user.access == 'Admin'}">
                    <a class="active" href="Admin.jsp" title="Database"><i class="fa fa-database"></i></a>
                    </c:if>
            </div>

            <div class="admin">
                <div class="body-container">
                    <div class="info-card">
                        <h3>CURRENT ACTIVE APPLICANTS</h3>
                        <p></p>
                    </div>
                    <div class="info-card">
                        <h3>CURRENT PLACED APPLICANTS</h3>
                        <p></p>
                    </div>
                    <div class="info-card">
                        <h3>PLACEMENT RATE OF ACTIVE APPLICANTS</h3>
                        <p></p>
                    </div>
                    <div class="info-card">
                        <h3>AVERAGE ACTIVE TIME OF APPLICANTS</h3>
                        <p></p>
                    </div>
                    <div class="info-card">
                        <h3>TOTAL APPLICANTS</h3>
                        <p></p>
                    </div>
                </div>

                <div class="chart-container">
                    <div class="chart-wrapper">
                        <canvas id="current-area"></canvas>
                    </div>
                    <div class="chart-wrapper">
                        <canvas id="desired-area"></canvas>
                    </div>
                </div>
            </div>
        </div>
        <div class="swoosh" align="right">
            <svg class="hero-swoop" width="659px" height="318px" viewBox="0 0 659 318" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
            <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
            <g transform="translate(-781.000000, -329.000000)" fill="#EB0029">
            <g transform="translate(1179.243783, 757.527748) scale(-1, 1) rotate(-345.000000) translate(-1179.243783, -757.527748) translate(582.743783, 409.527748)">
            <path d="M808.540785,417.998571 L733.619626,314.654475 L908.61347,383.209044 L942,343.222694 C822.925072,298.065276 692.828184,263.370185 572.090074,261.095144 C231.080578,254.667795 0,576.02378 0,576.02378 L0,696 L172.749296,696 C172.749296,696 369.190712,339.874521 808.540785,417.998571"></path>
            <path d="M726.564225,169.615169 C864.232242,215.14019 1029.04746,226.125831 1193,219.014782 L1193,99.7666394 C1000.24081,145.792439 812.406045,159.536652 668.259004,105.346738 C183.883853,-76.7504866 0,32.5049867 0,32.5049867 L0,169.615169 C0,169.615169 260.12845,15.3697909 726.564225,169.615169"></path>
            <path d="M0,253.464931 L0,451 C0,451 210.794945,163.254143 632.381839,221.242695 C758.769543,238.625781 873.343408,270.131012 972.005735,305.680154 L1000.15047,271.883373 L1000.78849,316.326254 C1075.66438,344.768436 1140.49671,375.077701 1193,402.490263 L1193,269.90157 C1082.00563,264.174128 898.375926,243.426851 663.776576,176.168849 C266.856761,62.3714415 0,253.464931 0,253.464931"></path>
            </g> </g> </g>
            </svg>
        </div>  
        <hr/>
        <footer style="text-align: center">
            <p>&copy; 2019 - RSI HR App</p>
        </footer>
    </body>
    <script src="JS/Chart.js"></script>
    <script src="JS/main.js"></script>
    <script src="JS/ChartJs-Plugin-Labels.js"></script>
    <script type="text/javascript">
        // variables pull in data from java 
        var currentAreaData = <%out.print(JSONUtils.hashMapToJson(ApplicantsDB.applicantsCurrentArea()));%>;
        var desiredAreaData = <%out.print(JSONUtils.hashMapToJson(ApplicantsDB.applicantsDesiredArea()));%>;

        // function in main.js
        buildChart(currentAreaEl, "TOP CURRENT PRACTICE OF APPLICANTS",
                getDepartments(currentAreaData), getCount(currentAreaData), color);

        buildChart(desiredAreaEl, "TOP DESIRED PRACTICES OF APPLICANTS",
                getDepartments(desiredAreaData), getCount(desiredAreaData), color);
    </script>
</html>
