<%-- 
    Document   : Normal
    Created on : Nov. 23, 2020, 10:54:45 p.m.
    Author     : Jonghyun Park
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <h1>Main Page</h1>
            <%@include file="Welcome.jspf" %>
            <p><a href="UserServices?setpassword=true">Set Password</a></p>
            <br/>
            <c:if test="${setPassword == true}">
                <h2>Set Password</h2>

                <form action='UserServices' method='POST'>
                    <table>
                        <tr>
                            <td>Enter password:</td>
                            <td><input type='text' name='setPass'></td>
                        </tr>
                        <tr>
                            <td>Confirm password:</td>
                            <td><input type='text' name='conPass'></td>
                        </tr>
                        <tr>
                            <td><input type='hidden' name='username' value='${sessionScope.username}'></td>
                        </tr>
                        <tr>
                            <td><input type='submit' value='Set Password'></td>
                        </tr>
                    </table>
                </form>
            </c:if>
            <p>${requestScope.message}</p>
        </div>
    </body>
</html>
