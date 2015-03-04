<%-- 
    Document   : index
    Created on : 04.02.2015, 15:32:10
    Author     : IBB Teilnehmer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List fühlen</title>
    </head>
    <body>
        <h1>Bitte fühlen Sie Ihre Einkaufliste</h1>
        <form action="result.jsp">
            <textarea name="einkaufsListe" rows="10" cols="30"></textarea><br>
            <input type="radio" name="sorted" value="true">Geordnet<br>
            <input type="radio" name="sorted" value="false">Ungeordnet<br>
            <input type="submit" name="done" value="OK" />
        </form>
        
    </body>
</html>
