<%-- 
    Document   : index
    Created on : 27.01.2015, 14:42:37
    Author     : Sabah Al-Sabea
--%>

<%@page import="com.pizza.CMController.PreisController"%>
<%@page import="com.pizza.CMModel.GrosseEintrag"%>
<%@page import="com.pizza.CMModel.Grosse"%>
<%@page import="com.pizza.CMModel.SpeiseEintrag"%>
<%@page import="com.pizza.CMModel.Speise"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kunden Seite</title>
        <style>
            body {
                font-family: Verdana,Geneva,Tahoma,sans-serif;
            }

            table {
                border-collapse: collapse;
            }

            thead tbody {
                border-color: black;
                border-width: 2px;
            }
            .smaller-td{
                font-size: 0.6em;
                padding-top: 0px;
            }

            th {
                padding: 5px;
            }

            td {
                text-align: left;
                padding-top: 3px;
            }
        </style>
    </head>
    <body>
        <form action="kundenInfo.jsp" method="POST">
            <h1>Herzlich Wilkommen bei Pizza La Gondola</h1>            
            <table border="1px">                

                <%
                    String speiseTyp = "";
                    Integer counter = 1;
                    Speise speise = new Speise();
                    List<SpeiseEintrag> speiseList = speise.getList();
                    for (SpeiseEintrag se : speiseList) {
                        String neueTyp = se.getTyp();

                        if (!speiseTyp.equals(neueTyp)) {
                            speiseTyp = neueTyp;
                            out.print("<tr>");
                            out.print("<td colspan = '5' style='background-color: beige'>");
                            out.print("<h4>" + speiseTyp + "</h4>");
                            out.print("<td>");
                            out.print("<tr>");
                %>

                <tr>    
                    <th></th>
                    <th style="width: 300px">Gericht</th>
                    <th style="width: 120px">Grösse</th>
                    <th style="width: 120px">Preis (in €)</th>
                    <th style="width: 75px">Menge</th>
                </tr>
                <%
                        }
                        out.print("<tr valign='top'>");
                        out.print("<td>" + counter++ + ".  </td>");
                        out.print("<td>" + se.getName() + "<br>"
                                + se.getBeschreibung() + "</td>");

                        // Get entsprechend Großen
                        Grosse g = new Grosse();
                        List<GrosseEintrag> grosseList = g.getList(se.getSpeiseId());
                        out.print("<td>");
                        for (GrosseEintrag ge : grosseList) {
                            out.print(ge.getGrosseName() + " ("
                                    + ge.getGrosseBeschreibung() + ")<br>");
                        }
                        out.print("</td>");
                        
                        PreisController pc = new PreisController();
                        if (!grosseList.isEmpty()) {
                        // Get entsprechend Preis                            
                            out.print("<td>");
                            for (GrosseEintrag ge : grosseList) {
                                out.print(pc.getPreis(se.getSpeiseId(), ge.getGrosseId()) + "<br>");
                            }
                            out.print("</td>");

                            // input boxes für Menge
                            out.print("<td>");
                            for (GrosseEintrag ge : grosseList) {
                                out.print("<input type='text' name='menge" + pc.getPreisId(se.getSpeiseId(), ge.getGrosseId()) + "' value='0'><br>");
                            }
                            out.print("</td>");
                        } else {
                            // Get entsprechend Preis
                            out.print("<td>" + pc.getPreis(se.getSpeiseId()) + "</td>");
                            out.print("<td><input type='text' name='menge" + pc.getPreisId(se.getSpeiseId()) + "' value='0'></td>");
                        }
                        
                        out.print("</tr>");                        
                    }
                %>
            </table>
            <input type="submit" value="Bestellen" name="next" />
        </form>

    </body>
</html>