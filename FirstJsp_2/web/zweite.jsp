<%-- 
    Document   : zweite
    Created on : 20.01.2015, 13:14:42
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
        <% Person myPerson = new Person();
        
           myPerson.setFirstname(request.getParameter("vorname"));
           myPerson.setName(request.getParameter("nachname"));
           myPerson.setCity(request.getParameter("ort"));
           session.setAttribute("myNewPerson",myPerson);
            
            
            
            
          %>  
          <a href="dritte.jsp">Zur Dritten Seite</a>
    </body>
</html>
