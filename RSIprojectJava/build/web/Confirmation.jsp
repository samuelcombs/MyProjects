<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : Thankyou
    Created on : Jul 1, 2019, 11:11:56 AM
    Author     : TJurc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.9.0/css/all.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.9.0/css/v4-shims.css">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="shortcut icon" href="https://www.ruralsourcing.com/wp-content/themes/ruralsourcing/dist/img/favicon.png">
        <link href="CSS/main.css" rel="stylesheet" />
        <link href="CSS/navbar.css" rel="stylesheet">
        <title>Confirmation</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">    
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
                    <a class="navbar-brand">Confirmation</a>
                </div>
            </div>
        </div>
        <div class="main">
            <div class="icon-bar left col-sm-1">
                <a class="active" href="index.jsp" title="Logout"><i class="fa fa-sign-out"></i></a>                
            </div>
            <div class="login">                

                <h2>Are all of of these fields correct?</h2>               
                <br/>
                <label>Name:</label> <label style="color:red; margin-left:10px"><c:out value='${applicant.firstName}'/> <c:out value='${applicant.middleInitial}'/> <c:out value='${applicant.lastName}'/> 
                    <c:out value='${applicant.suffix}'/> </label> <br>
                <label>Date Added:</label> <label style="color:red; margin-left:10px"> <c:out value='${applicant.dateAdded}'/></label> <br>
                <label>Current Location:</label> <label style="color:red; margin-left:10px"> <c:out value='${applicant.currentWorkLocation}'/></label> <br>
                <label>Current Practice Area:</label> <label style="color:red; margin-left:10px"> <c:out value='${applicant.currentPracticeArea}'/></label> <br>
                <label>Time In Current Area:</label> <label style="color:red; margin-left:10px"> <c:out value='${applicant.timeInCurrentProject}'/></label> <br>
                <label>Desired Location:</label> <label style="color:red; margin-left:10px"> <c:out value='${applicant.desiredLocation}'/></label> <br>
                <label>Desired Area:</label> <label style="color:red; margin-left:10px"> <c:out value='${applicant.desiredArea}'/></label> <br>
                <c:forEach var="skillList" items="${applicant.skillArrayList}"> 
                        <label>Skill: <c:if test="${skillList.skillName == 'Other'}">
                            ${skillList.skillDescription}
                        </c:if>
                        <c:if test="${skillList.skillName != 'Other'}">
                            ${skillList.skillName}</label>
                        <label style="color:red; margin-left:10px"></c:if></label> <label style="color:red; margin-left:10px"><c:out value='${skillList.timeWithSkill}'/></label>  <br>
                    </c:forEach> <br>

                <div style="display: inline-block;padding:10px">
                    <form method="post" action="<c:url value='RSI' />">                     
                        <input type="hidden" name="action" value="submit">
                        <input type="submit" value="Yes" class="button" style="width:80px">       
                    </form>
                </div>
                <div style="display: inline-block">
                    <form method="get" action="<c:url value='RSI' />">                       
                        <input type="hidden" name="applicantID" value="<c:out value='${applicant.userID}'/>">
                        <input type="hidden" name="action" value="loginUser">
                        <input type="submit" value="Back" class="button" style="width:80px">
                    </form>
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
</html>
