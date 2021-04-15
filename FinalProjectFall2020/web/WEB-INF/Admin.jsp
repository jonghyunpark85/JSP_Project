<%-- 
    Document   : Admin.jsp
    Created on : Nov. 23, 2020, 11:43:54 p.m.
    Author     : Jonghyun Park
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <h1>Administration Page</h1>
            <%@include file="Welcome.jspf" %>
            <h2>User Account</h2>
            <div>
                <table border="1" style="background-color: white;">
                <th>Username</th><th>User Type</th><th>Toggle Account Type</th><th>Delete</th><th>Reset Password</th>
                    <c:forEach var='user' items='${sessionScope.userList}'>
                        <c:choose>
                            <c:when test="${user.usertype=='0'}">
                                <c:set var="usertype" value="Normal"/>
                            </c:when>
                            <c:when test="${user.usertype=='1'}">
                                <c:set var="usertype" value="Admin"/>
                            </c:when>
                        </c:choose>   
                    <tr>
                        <td class="${usertype}">${user.username}</td>
                        <td class="${usertype}">${usertype}</td>
                        <td><a href='AdminServices?username=${user.username}&usertype=${usertype}&toggle=true'>Toggle</a></td>
                        <td><a href='AdminServices?username=${user.username}&usertype=${usertype}&delete=true' onclick="return delchk();">Delete</a></td>
                        <td><a href='AdminServices?username=${user.username}&reset=true'>Reset</a></td>
                    </tr>
                </c:forEach>
            </table>
            </div>
            <p> ${requestScope.message}</p> 
        </div>
    </body>

    <script type="text/javascript">
        function delchk() {
         return confirm ("Delete Account?");
        }
    </script>
</html>
