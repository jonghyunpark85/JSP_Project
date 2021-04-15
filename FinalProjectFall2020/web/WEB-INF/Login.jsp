<%-- 
    Document   : Login
    Created on : Nov. 23, 2020, 5:49:49 p.m.
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
            <h1>Order Application</h1>

            <form action="UserServices" method="POST">
                <table>
                    <tr>
                        <td>Username:</td>
                        <td> <input type="text" name="username" id="username"> </td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="text" name="password"></td>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" value="Login">
                        </td>
                    </tr>
                </table>
            </form>
            <p>${requestScope.message}</p> 
            <p><a href="ProjectNavigation?register=true"> Register new Account </a></p>
        </div>
    </body>
</html>
