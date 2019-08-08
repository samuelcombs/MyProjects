<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="rsi" uri="/WEB-INF/tlds/RSITags.tld" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.9.0/css/all.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.9.0/css/v4-shims.css">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="shortcut icon" href="https://www.ruralsourcing.com/wp-content/themes/ruralsourcing/dist/img/favicon.png">
        <link href="CSS/main.css" rel="stylesheet" />
        <link href="CSS/navbar.css" rel="stylesheet">
        <title>Applicant Form</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
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
                    <a class="navbar-brand">Applicant Form</a>
                </div>                
            </div>
        </div>
        <div class="main">
            <div class="icon-bar left col-sm-1">
                <a class="active" href="index.jsp" title="Logout"><i class="fa fa-sign-out"></i></a>           
            </div>            
            <div class="login">

                <form action="RSI" method="get" id="form1">
                    <input type='hidden' name='action' value='submit'>
                    <h2><c:out value='${applicant.firstName}'/>'s Applicant Form</h2>
                    <c:if test="${message != null}">
                        <p><i>${message}</i></p>
                    </c:if>
                    <p><rsi:ifEmptyMark field=""/> Marks required field.</p>

                    <hr style="margin:0px;border-color:darkgray">

                    <div class="header" style="padding-top: 6px">
                        <h3>Your Name</h3>
                    </div>
                    <div class="section">
                        <div class="form-inline">

                            <div class="form-group">
                                <label style="margin-right:30px; color:red; font-size: larger">
                                    <c:out value='${applicant.firstName} ${applicant.middleInitial} ${applicant.lastName} ${applicant.suffix}'/>
                                </label>
                            </div>
                            <div class="form-group">
                                <label>Deactivate My Form:</label>
                                <input type="checkbox" name="activeCheck" style="width: 32px" class="form-control">

                            </div>
                        </div>
                    </div>
                    <div class="header">
                        <h3>Current Work Information</h3>
                    </div>
                    <div class="section">                        
                        <div class="form-inline">

                            <div class="form-group">
                                <label>Current Work Location</label> <br>
                                <label style="color:red"><c:out value='${applicant.currentWorkLocation}'/></label>
                            </div>

                            <div class="form-group" style="margin-right: 50px; margin-left:50px">
                                <label>Current Practice Area</label><br>
                                <label style="color:red"><c:out value='${applicant.currentPracticeArea}'/></label>
                            </div>

                            <div class="form-group">
                                <label style="margin-left:20px">Active Time in Practice Area</label> <br>
                                <label style="color:red">Years: <c:out value='${applicant.yearsOnProject}'/></label>
                                <label style="margin-left: 30px; color: red">Months: <c:out value='${applicant.monthsOnProject}'/></label><br>
                            </div>
                        </div>
                    </div>                                                       

                </form>

                <form action="RSI" method="get">
                    <input type='hidden' name='action' value='addSkill'>
                    <div class="header">
                        <h3>Current Skills</h3>
                    </div>
                    <div class="section" >
                        <c:forEach var="skillList" items="${applicant.skillArrayList}">
                            <c:if test="${skillList.skillName == 'Other'}">
                                <b>${skillList.skillDescription}</b>
                            </c:if>
                            <c:if test="${skillList.skillName != 'Other'}">
                                <b>${skillList.skillName}</b>
                            </c:if>
                            <c:out value='Years: ${skillList.years} Months: ${skillList.months}'/><a href="<c:url value='RSI'>
                                   <c:param name='skillID' value='${skillList.skillID}' /> 
                                   <c:param name='skillDescription' value='${skillList.skillDescription}' />
                                   <c:param name='action' value='deleteList' />
                               </c:url>" style="color:red; margin-bottom: 10px; margin-left: 10px" class="deleteSkill"> &#10006; </a><br/>
                        </c:forEach>

                        <div class="form-inline">
                            <c:if test="${message2 != null}">
                                <p><i>${message2}</i></p>
                            </c:if>
                            <div class="form-group">
                                <select name="skillName" class="ddl" style="color:red" onchange="change(this)">
                                    <option disabled selected>Select Skill</option>
                                    <c:forEach var="item" items="${skillList}">
                                        <option><c:out value='${item}'/></option>
                                    </c:forEach>
                                </select>

                                <label style="margin-left:10px">Years: </label>
                                <input type="text" name="years" style="width: 50px">

                                <label style="margin-left:10px">Months: </label>
                                <input type="text" name="months" style="width: 50px">
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

                                    <textarea style="display:none;margin-left:20px" rows="2" id="skillsTextArea" maxlength="45" name="skillDescription"
                                              placeholder="Other Skill Name."></textarea>
                                </div>
                                <input class="addSkill" type="submit" value="+" style="margin-left:14px" id="addSkill">                                
                            </div>
                        </div>                        
                    </div>

                    <input type='hidden' name='skillName' value="<c:out value='${skillName}'/>">  
                    <input type='hidden' name='skillDescription' value="<c:out value='${skillDescription}'/>">  
                    <input type='hidden' name='months' value="<c:out value='${months}'/>">  
                    <input type='hidden' name='years' value="<c:out value='${years}'/>">  
                </form>

                <div class="header">
                    <h3>Desired Project</h3>
                </div>
                <div class="section">
                    <div class="form-inline">
                        <div class="form-group">
                            <label>Desired Location</label><br>                
                            <select name="desiredLocation" class="ddl" style="color:red" form="form1">
                                <option selected><c:if test="${applicant.desiredLocation != null}">
                                        ${applicant.desiredLocation}
                                    </c:if> 
                                </option>
                                <c:forEach var="location" items="${locationList}">
                                    <option value="<c:out value='${location}'/>"><c:out value='${location}'/></option>
                                </c:forEach>
                            </select>
                            <rsi:ifEmptyMark field="${desiredLocation}"/>
                        </div>                                
                        <div class="form-group">
                            <label>Desired Area</label><br>   
                            <select name="desiredArea" class="ddl" style="color:red" form="form1">
                                <option selected><c:if test="${applicant.desiredArea != null}">
                                        ${applicant.desiredArea}
                                    </c:if> </option>
                                    <c:forEach var="department" items="${departmentList}">
                                    <option value="<c:out value='${department}'/>"><c:out value='${department}'/></option>
                                </c:forEach>
                            </select>
                            <rsi:ifEmptyMark field="${desiredArea}"/>
                        </div>                            
                    </div>
                </div>

                <div style="padding-top: 20px">
                    <label> Attach Internal Resume:
                    </label>
                    <input type="submit" form="form1" value="Choose File" class="button" style="margin-right: 20px"/>
                    <input type='hidden' form="form1" name='desiredLocation' value="<c:out value='${desiredLocation}'/>">
                    <input type='hidden' form="form1" name='active' value="<c:out value='${activeCheck}'/>">
                    <input type='hidden' form="form1" name='desiredArea' value="<c:out value='${desiredArea}'/>">                      
                    <input type="submit" form="form1" value="Submit" class="button"/>
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