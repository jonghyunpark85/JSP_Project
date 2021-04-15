<%-- 
    Document   : login
    Created on : Aug 28, 2020, 6:43:24 AM
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
        <h1>Assign2 Login Page</h1>
        <form action='LoginOps' method='POST'>
            Username: <input type='text' name='username' id='username'><br/>
            Password: <input type='password' name='password'><br/>
            <input type='submit' value='Login'>
        </form>
        ${requestScope.message}<br/>
        <a href='NotesNavigation?register=true'>Register New User</a>
        <script>document.getElementById("username").focus();</script>
    </body>
</html>
