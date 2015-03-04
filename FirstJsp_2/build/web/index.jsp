<%-- 
    Document   : index
    Created on : 20.01.2015, 13:04:01
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
        <form action="zweite.jsp" method="post">
            <table >
               
                <tbody>
                    <tr>
                        <td>Vorname: </td>
                        <td> <input type="text" name="vorname" value="" /></td>
                    </tr>
                    <tr>
                        <td>Nachname: </td>
                        <td><input type="text" name="nachname" value="" /></td>
                    </tr>
                    <tr>
                        <td>Ort:</td>
                        <td><input type="text" name="ort" value="" /></td>
                    </tr>
                </tbody>
            </table>
 
            <input type="submit" value="OK" />
        </form>
    </body>
</html>
