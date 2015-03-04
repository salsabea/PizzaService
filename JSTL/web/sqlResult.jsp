<%-- 
    Document   : index
    Created on : Feb 2, 2015, 1:17:41 PM
    Author     : Sabah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <sql:setDataSource driver="com.mysql.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/pizzalagondola"
                           user="root"
                           password="1234"
                           var="aufgabeJSTL"
                           scope="request" />

        <c:set var="value" value="${param.value}"/>
        <c:set var="column" value="${param.column}"/>
        <sql:query dataSource="${aufgabeJSTL}"
                   maxRows="10"
                   startRow="0"
                   var="resultSet">
            SELECT * FROM speise WHERE ? = ?
            <sql:param value="${column}" />
            <sql:param value="${value}" />            
        </sql:query>

        <table cellspacing="0" cellpadding="0" style="border-collapse: collapse;">
            <tr>            
                <c:forEach items="${resultSet.columnNames}" var="column">
                    <th style="border: 1px solid #000000; padding: 5px;">${column}</th>
                    </c:forEach>
            </tr>
            <c:forEach items="${resultSet.rows}" var="currRow">
                <tr>
                    <c:forEach items="${resultSet.columnNames}" var="column">
                        <td style="border: 1px solid #000000; padding: 5px;">${currRow[column]}</td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
