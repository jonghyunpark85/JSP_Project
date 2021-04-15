<%-- 
    Document   : notes
    Created on : Aug 28, 2020, 7:30:38 AM
    Author     : john
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Assignment 3</title>
    </head>
    <body bgcolor='${cookie.colour.value}'>
        <h1>Notes Page using DB</h1>
        Welcome, ${sessionScope.username}<br/>

        <h3>Add Note</h3>
        <form action='NoteOps' method='POST'>
            Enter new note: <input type='text' name='newnote' id='newnote'> 
            <input type ="hidden" name="username" value="${sessionScope.username}" >
            <input type='submit' value='Add'>
        </form>
        ${requestScope.message}

        <h3>Notes List</h3>
        <table border='1'>
            <th>Date/Time</th><th>Note Text</th><th>Delete</th>
                <c:forEach var='note' items='${sessionScope.noteList}' varStatus='i'>
                <tr>
                    <td>${note.noteDateTime}</td>
                    <td>${note.note}</td>
                    <td><a href='NoteOps?deletenote=${note.noteID}'>Delete</a></td>
                </tr>
            </c:forEach>
        </table>
        <br/>

        <a href='LoginOps?logout=true'>Logout</a><br/>

        <h4>Set Preferred Background Colour</h4>
        <form action='LoginOps' method='POST'>
            <input type='radio' name='colour' value='white' checked>White
            <input type='radio' name='colour' value='aqua'>Aqua
            <input type='radio' name='colour' value='olive'>Olive
            <input type='submit' value='Set Background Colour'>
        </form>

        <script>document.getElementById("newnote").focus();</script>
    </body>
</html>
