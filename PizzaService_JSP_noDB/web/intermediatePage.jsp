<%-- 
    Document   : createClient
    Created on : 21.01.2015, 08:29:56
    Author     : Sabah Al-Sabea
--%>

<%@page import="java.util.*"%>
<%@page import="Model.Speise"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="bestellung" scope="session" class="Controller.Bestellung" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bestellung Information</title>
        <style>
            body {
                font-family: Verdana,Geneva,Tahoma,sans-serif;
            }
            .likeAButton {
                font-family: Verdana,Geneva,Tahoma,sans-serif;
                display: inline-block;
                color: #FFF;
                background-color: rgba(0, 0, 0, 0.34);
                font-weight: bold;
                font-size: 12px;
                text-align: center;
                padding: 0.5% 0.5%;
                text-decoration: none;
                margin-left: 0px;
                margin-top: 0px;
                margin-bottom: 5px;
                border-radius: 3px;
                white-space: nowrap;
            }
            table{
                padding: 3px;
            }
            td{
                padding: 2px;
                text-align: center;
            }
            thead {
                border-color: black;
                border-width: 2px;
            }
            
        </style>
    </head>
    <body>
        <%
            Map<Speise, Integer> bestellList = new HashMap<Speise, Integer>();
            ArrayList<Speise> menu = (ArrayList<Speise>) application.getAttribute("menu");
            for (Speise s : menu) {
                Integer menge = Integer.parseInt(request.getParameter("speise" + s.getId()));
                if (menge > 0) {
                    bestellList.put(s, menge);
                }
            }
            bestellung.setBestellList(bestellList);
            if (bestellList.size()>0){
        %>
        <h3>Sie haben die folgende Speise bestellt:</h3>
        <br>
        <table rules="groups">
            <thead>
                <tr>
                    <th style="width:300px">Speise</th>
                    <th style="width:100px">Preis <br>(in €)</th>
                    <th>Menge</th>
                    <th style="width: 150px;">Gesamt Preis <br>(in €)</th>
                </tr>
            </thead>
            <tbody>
                <%                    
                    for (Map.Entry<Speise, Integer> item : bestellList.entrySet()) {
                        out.print("<tr><td style='text-align: left;'>" + item.getKey().getName() + "</td>");
                        out.print("<td>" + item.getKey().getPreis() + "</td>");
                        out.print("<td>" + item.getValue() + "</td>");
                        out.print("<td>" + item.getKey().getPreis() * item.getValue() + "</td></tr>");
                    }
                %>
            </tbody>
        </table>

            <br>
        <a href="index.jsp" class="likeAButton">Bestellung Ändern</a>
        <a href="createClient.jsp" class="likeAButton">Fortsetzen</a>
        <%
            } else {
                out.print("<h3>Sie haben keine Speise bestellen. <br>Bitte wählen Sie Ihre Bestellung wieder. </h3>");
                out.print("<a href='index.jsp' class='likeAButton'>Neu Bestellung</a>");
            }
        %>
        

    </body>
</html>
