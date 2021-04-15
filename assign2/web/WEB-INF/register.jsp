<%-- 
    Document   : register
    Created on : Aug 28, 2020, 7:05:17 AM
    Author     : john
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Assignment 2</title>
    </head>
    <body bgcolor='${cookie.colour.value}'>
        <h1>Register New User</h1>
        <form action='LoginOps' method='POST'>
            Enter username: <input type='text' name='newusername' id='newusername'><br/>
            Enter password: <input type='password' name='newpassword'><br/>
            <input type='submit' value='Register'>
        </form>
        ${requestScope.message}<br/>
        <a href='NotesNavigation'>Back to Login Page</a>
        
        <script>document.getElementById("newusername").focus();</script>
    </body>
</html>
