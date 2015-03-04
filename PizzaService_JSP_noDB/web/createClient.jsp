<%-- 
    Document   : createClient
    Created on : 22.01.2015, 11:30:34
    Author     : Sabah Al-Sabea
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="bestellung" scope="session" class="Controller.Bestellung" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kunden Information</title>
        <style>
            body {
                font-family: Verdana,Geneva,Tahoma,sans-serif;
            }            
        </style>
    </head>
    <body>        
        <form action="rechnung.jsp" method="post">
            <h3>Geben Sie bitte Ihre Information:</h3>
            <table>
                <tr>
                    <td>Vorname:</td>
                    <td><input type="text" name="vorname"></td>
                </tr>
                <tr>
                    <td>Nachname:</td>
                    <td><input type="text" name="nachname"></td>
                </tr>
                <tr><td><br></td></tr>
                <tr>
                    <td style="font-weight: bold">Anschrift</td>
                </tr>
                <tr>
                    <td>Straße:</td>
                    <td><input type="text" name="strasse"></td>
                    <td>Nr.:</td>
                    <td><input type="text" name="hausNr" style="width: 25px"></td>
                </tr>
                <tr>
                    <td>PLZ.:</td>
                    <td><input type="text" name="plz"  style="width: 45px"></td>
                    <td>Stadt:</td>
                    <td><input type="text" name="stadt"></td>
                </tr>
                <tr>
                    <td>Telefon:</td>
                    <td><input type="text" name="telefonnummer"></td>
                </tr>
            </table>
            <input type="submit" value="Fortsetzen" />
        </form>

    </body>
</html>
