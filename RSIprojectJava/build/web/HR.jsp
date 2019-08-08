<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.9.0/css/all.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.9.0/css/v4-shims.css">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="shortcut icon" href="https://www.ruralsourcing.com/wp-content/themes/ruralsourcing/dist/img/favicon.png">
        <link href="CSS/table.css" rel="stylesheet" />
        <link href="CSS/navbar.css" rel="stylesheet">
        <title>HR page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">   
        <script src="JS/SortTable.js"></script>
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
                    <a class="navbar-brand">Applicant List</a>
                </div>
            </div>
        </div>

        <div class="main">
            <div class="icon-bar left col-sm-1">
                <a class="active" href="index.jsp" title="Logout"><i class="fa fa-sign-out"></i></a>
                <a class="active" href="dashboard.jsp" title="Dashboard"><i class="fas fa-chart-pie"></i></a>
                    <c:if test="${user.access == 'Admin'}">
                    <a class="active" href="Admin.jsp" title="Database"><i class="fa fa-database"></i></a>
                    </c:if>
            </div>
            <h2 style="padding-left:100px">Hello <c:out value='${user.firstName}'/></h2>

            <div style="padding-left:100px; padding-bottom: 20px">
                <form method="post"  action="<c:url value='RSI' />">
                    <input type="hidden" name ="action" value = "search">
                    <select name="currentLocationSearch" class="ddl">
                        <option selected disabled>Current Location Search</option>
                        <c:forEach var="location" items="${locationList}">
                            <option value="<c:out value='${location}'/>"><c:out value='${location}'/></option>
                        </c:forEach>
                    </select>
                     <select name="timeOnProjectSearch" class="ddl">
                        <option selected disabled>Years on current project</option>
                        <c:forEach var="number" items="${intList}">
                            <option value="<c:out value='${number}'/>"><c:out value='${number}'/></option>
                        </c:forEach>
                    </select>
                    <select name="skillNameSearch" class="ddl" onchange="change(this)">
                        <option selected disabled>Select Skill</option>
                        <c:forEach var="skill" items="${skillList}">
                            <option value="<c:out value='${skill}'/>"><c:out value='${skill}'/></option>
                        </c:forEach>
                    </select>
                     <select name="timeOnSkillSearch" class="ddl">
                        <option selected disabled>Years of experience with skill</option>
                        <c:forEach var="number" items="${intList}">
                            <option value="<c:out value='${number}'/>"><c:out value='${number}'/></option>
                        </c:forEach>
                    </select>
                    <select name="desiredLocationSearch" class="ddl">
                        <option selected disabled>Desired Location Search</option>
                        <c:forEach var="location" items="${locationList}">
                            <option value="<c:out value='${location}'/>"><c:out value='${location}'/></option>
                        </c:forEach>
                    </select>
                    <select name="desiredProjectSearch" class="ddl">
                        <option selected disabled>Desired Project Search</option>
                        <c:forEach var="department" items="${departmentList}">
                            <option value="<c:out value='${department}'/>"><c:out value='${department}'/></option>
                        </c:forEach>
                    </select>
                    <div class="form-group">
                                    <script> function change(obj) {
                                        var selectBox = obj;
                                        var selected = selectBox.options[selectBox.selectedIndex].value;
                                        var textarea = document.getElementById("skillsTextArea");
                                        
                                        if (selected !== 'Other') {
                                            textarea.style.display = "none";
                                        } else {
                                            textarea.style.display = "block";
                                        }
                                    }</script>
                                    
                                    <textarea style="display:none" rows="1" id="skillsTextArea" maxlength="100" name="skillDescription"
                                              placeholder="Other Skill Name."></textarea>
                                </div>
                    <input type="hidden" name="userID" value="<c:out value='${user.userID}'/>" />
                    <input type="submit" class="button" value="Search" style="margin:5px"/>                
                </form>

            </div>
            <div class="table-responsive" style="padding-left: 70px; padding-right: 20px">
                <table class="table table-striped table-bordered table-hover" id="HR">
                    <thead class="tablehead" style=" color:red">
                        <tr>
                            <th onClick="sortTable(0)">Name</th>
                            <th onClick="sortTable(1)">Time on List</th>
                            <th onClick="sortTable(2)">Current Location</th>
                            <th onClick="sortTable(3)">Current Project</th>
                            <th onClick="sortTable(4)">Time in Project</th>
                            <th onClick="sortTable(5)">Desired Location</th>
                            <th onClick="sortTable(6)">Desired Project</th>
                            <th onClick="sortTable(7)">Skills</th>
                            <th onClick="sortTable(8)">Internal Resume</th>
                            <th>Edit</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="applicant" items="${applicants}"> 
                            <tr>
                                <td> <c:out value='${applicant.firstName} ${applicant.middleInitial} ${applicant.lastName} ${applicant.suffix}'/></td>
                                <td><c:out value='Years: ${applicant.yearsOnList} Months: ${applicant.monthsOnList}'/></td>
                                <td><c:out value='${applicant.currentWorkLocation}'/></td>
                                <td><c:out value='${applicant.currentPracticeArea}'/></td>
                                <td><c:out value='Years: ${applicant.yearsOnProject} Months: ${applicant.monthsOnProject}'/></td>
                                <td><c:out value='${applicant.desiredLocation}'/></td>
                                <td><c:out value='${applicant.desiredArea}'/></td>
                                <td style="min-width:200px; max-width:240px">
                                    <c:forEach var="skillList" items="${applicant.skillArrayList}">
                                        <c:if test="${skillList.skillName == 'Other'}">
                                            <b>${skillList.skillDescription}</b>
                                        </c:if>
                                        <c:if test="${skillList.skillName != 'Other'}">
                                            <b>${skillList.skillName}</b>
                                        </c:if><br/>
                                        <c:out value='Years: ${skillList.years} Months: ${skillList.months}'/><br/>
                                    </c:forEach>
                                </td>

                                <td>Resume</td>
                                <td style="min-width:200px"><a href="<c:url value='RSI'>
                                                                   <c:param name='selectedApplicantID' value='${applicant.userID}' /> 
                                                                   <c:param name='userID' value='${user.userID}' /> 
                                                                   <c:param name='action' value='deactivate' />
                                                               </c:url>" class="button">Deactivate Applicant</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
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
