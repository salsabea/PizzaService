<%-- 
    Document   : dritte
    Created on : 20.01.2015, 13:21:40
    Author     : Schulung
--%>

<%@page import="mypackage.Person"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Person testPerson =(Person)session.getAttribute("myNewPerson");
            out.println("Objekt::::"+testPerson.toString());
            out.print(testPerson.getCity()+"<br />");
            out.print(testPerson.getFirstname()+"<br />");
            out.print(testPerson.getName()+"<br />");
            %>
    </body>
</html>
