<%-- 
    Document   : Register
    Created on : Nov. 23, 2020, 10:19:29 p.m.
    Author     : Jonghyun Park
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Final Project Fall 2020</title>
 <link href="style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div name="container">
            <h1>Register New Account</h1>

            <form action="UserServices" method="POST">
                <table>
                    <tr>
                        <td>Username:</td>
                        <td> <input type="text" name="newUsername"> </td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="text" name="newPassword"></td>
                    </tr>
                    <tr>
                        <td>Confirm password:</td>
                        <td><input type="text" name="conPassword"></td>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" value="Register">
                        </td>
                    </tr>
                </table>
            </form>
            <p>${requestScope.message}</p>
            <p><a href="ProjectNavigation?Login=true">Login </a></p>
    </body>
</html>
