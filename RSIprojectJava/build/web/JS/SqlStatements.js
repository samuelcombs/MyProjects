
document.getElementById("resetButton").addEventListener("click", clearField);
function clearField() {
    document.getElementById('sqlStatement').value = "";
}

document.getElementById("allHR").addEventListener("click", allHR);
function allHR() {
    document.getElementById('sqlStatement').value = "SELECT userID, CONCAT(firstName,' ' , middleInitial,' ',lastName,' ' ,suffix) AS Name,"
            + "userNumber AS 'User Number', userPassword AS 'Password', access FROM users WHERE access = 'hr';";
}

document.getElementById("allAdmin").addEventListener("click", allAdmin);
function allAdmin() {
    document.getElementById('sqlStatement').value = "SELECT userID, CONCAT(firstName,' ' , middleInitial,' ',lastName,' ' ,suffix) AS Name,"
            + "userNumber AS 'User Number', userPassword AS 'Password', access FROM users WHERE access = 'admin';";
}

document.getElementById("deleteFromHR").addEventListener("click", deleteFromHR);
function deleteFromHR() {
    document.getElementById('sqlStatement').value = "DELETE FROM users where access = '!!hr or admin!!' AND userID = '!!Enter UserID Here!!';"
            + "\n\n All fields must be within single quotes ' ' and left blank if null.  Delete this line!";
}

document.getElementById("addNewHR").addEventListener("click", addNewHR);
function addNewHR() {
    document.getElementById('sqlStatement').value = "INSERT INTO users (userNumber, userPassword, access, firstName, middleInitial, lastName, suffix, emails) "
            + " VALUES ('!!Log In Name!!', '!!Password!!', '!!hr or admin!!', '!!First Name!!', '!!Middle Initial!!', '!!Last Name!!', '!!Suffix!!', '!!Email Address!!'); "
            + "\n All fields must be within single quotes ' ' and left blank if null.  Delete this line!";
}
document.getElementById("addNewSkill").addEventListener("click", addNewSkill);
function addNewSkill() {
    document.getElementById('sqlStatement').value = "INSERT INTO skills (skillName) "
            + " VALUES ('!!Name of the new skill!!'); "
            + "\n\n All fields must be within single quotes ' ' and left blank if null.  Delete this line!";
}
document.getElementById("addNewArea").addEventListener("click", addNewArea);
function addNewArea() {
    document.getElementById('sqlStatement').value = "INSERT INTO departments (departmentName) "
            + " VALUES ('!!Name of the new Department!!'); "
            + "\n\n All fields must be within single quotes ' ' and left blank if null.  Delete this line!";
}
document.getElementById("addNewLocation").addEventListener("click", addNewLocation);
function addNewLocation() {
    document.getElementById('sqlStatement').value = "INSERT INTO locations (city, state) "
            + " VALUES ('!!Name of the city!!', '!!Name of the state!!'); "
            + "\n\n All fields must be within single quotes ' ' and left blank if null.  Delete this line!";
}
document.getElementById("deleteSkill").addEventListener("click", deleteSkill);
function deleteSkill() {
    document.getElementById('sqlStatement').value = "DELETE from skills "
            + " WHERE skillName = '!!Name of the skill!!'; "
            + "\n\n All fields must be within single quotes ' ' and left blank if null.  Delete this line!";
}
document.getElementById("deleteArea").addEventListener("click", deleteArea);
function deleteArea() {
    document.getElementById('sqlStatement').value = "DELETE from departments "
            + " WHERE departmentName = '!!Name of the Department!!'; "
            + "\n\n All fields must be within single quotes ' ' and left blank if null.  Delete this line!";
}
document.getElementById("deleteLocation").addEventListener("click", deleteLocation);
function deleteLocation() {
    document.getElementById('sqlStatement').value = "DELETE from locations"
            + " WHERE city = '!!Name of the city!!'; "
            + "\n\n All fields must be within single quotes ' ' and left blank if null.  Delete this line!";
}
