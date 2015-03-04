<%-- 
    Document   : start
    Created on : 25.11.2014, 11:21:52
    Author     : Schulung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <form action="liste.jsp">
            <table>
                <tr><td>Vorname:</td><td><input type="text" name="vorname" value="" /></td></tr>
                <tr><td>Nachname:</td><td><input type="text" name="nachname" value="" /></td></tr>
                <tr><td>Email</td><td><input type="text" name="email" value="" /></td></tr>
                <tr><td>Kommentar:</td><td><textarea name="kommentar" rows="4" cols="20"></textarea></td></tr>

                <tr><td>Region:</td><td><input type="text" name="region" value="" /></td></tr>
            </table>
            <input type="submit" value="OK" />
        </form>

    </body>
</html>
