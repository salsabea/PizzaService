<%-- 
    Document   : rechnung
    Created on : 22.01.2015, 13:45:07
    Author     : Sabah Al-Sabea
--%>

<%@page import="java.util.*"%>
<%@page import="Model.Speise"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="kunde" scope="request" class="Model.Kunde" />
<jsp:useBean id="bestellung" scope="session" class="Controller.Bestellung" />
<jsp:setProperty name="kunde" property="*" />

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rechnungbetrag</title>
        <style>
            body {
                font-family: Verdana,Geneva,Tahoma,sans-serif;
            }
            #bestellList td {
                text-align: center;
            }

            #bestellList thead tbody tfoot {
                border-color: black;
                border-width: 2px;
            }
            
            #kunde *{
                text-align: left;
                padding: 2px;
            }
            .summe {
                font-weight: bold; 
                text-align: right; 
                padding-right:10px;
                font-size: 20px;
            }
        </style>
    </head>
    <body>
        <h1>Rechnung Betrag</h1>
        <%
            bestellung.setKunde(kunde);
        %> 
        <table id="kunde">
            <thead>
                <tr>
                    <th>Kundenname:</th>
                    <td><%=bestellung.getKunde().getVorname() + " " + bestellung.getKunde().getNachname()%></td>
                </tr>
                <tr>
                    <th>Anschrift: </th>
                    <td><%=bestellung.getKunde().getStrasse() + " "
                            + bestellung.getKunde().getHausNr() + ","%></td>
                </tr>
                <tr>
                    <td></td>
                    <td><%=bestellung.getKunde().getPlz() + " "
                            + bestellung.getKunde().getStadt()%>
                    </td>
                </tr>
                <tr>
                    <th>Telefon: </th>
                    <td><%=bestellung.getKunde().getTelefonnummer()%>
                    </td>
                </tr>
            </thead>
        </table>
        <br>
        <table rules="groups" id="bestellList">
            <thead>
                <tr>
                    <th style="width: 400px; text-align: left;">Speise</th>
                    <th style="width: 100px;">Preis <br>(in €)</th>
                    <th style="width: 100px;">Menge</th>
                    <th>Gesamt Preis <br>(in €)</th>
                </tr>
            </thead>
            <tbody>
                <%  
                    Double summe = 0.0;
                    for (Map.Entry<Speise, Integer> item : bestellung.getBestellList().entrySet()) {
                        out.print("<tr><td style='text-align: left;'>" + item.getKey().getName() + "</td>");
                        out.print("<td>" + item.getKey().getPreis() + "</td>");
                        out.print("<td>" + item.getValue() + "</td>");
                        out.print("<td>" + item.getKey().getPreis() * item.getValue() + "</td></tr>");
                        summe += item.getKey().getPreis() * item.getValue();
                    }                    
                %>
            </tbody>
            <tfoot>
                <% 
                    out.print("<tr><td colspan='3' class='summe'>Summe</td><td>" + summe + "</td></tr>");
                %>
            </tfoot>
        </table>

    </body>
</html>
