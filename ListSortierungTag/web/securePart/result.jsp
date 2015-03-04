<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : result
    Created on : 05.02.2015, 09:12:13
    Author     : IBB Teilnehmer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="list" uri="/WEB-INF/tlds/listTagLibrary" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>resultat</title>
    </head>
    <body>
    <c:if test="${param.sorted == true}">
        <c:set var="sorted" value="<h1>Hier ist die geordnet Einkaufslist</h1>"></c:set>
    </c:if>
    <c:if test="${param.sorted == false}">
        <c:set var="sorted" value="<h1>Hier ist die ungeordnet Einkaufslist</h1>"></c:set>
    </c:if>
        ${sorted}
       
        <list:sortList geordnet="${param.sorted}">${param.einkaufsListe}</list:sortList>
    </body>
</html>
