<%-- 
    Document   : rechnung
    Created on : Feb 2, 2015, 9:09:17 PM
    Author     : Sabah Al-Sabea
--%>


<%@page import="com.pizza.Model.Bestellung"%>
<%@page import="com.pizza.CMModel.GrosseEintrag"%>
<%@page import="com.pizza.CMModel.Grosse"%>
<%@page import="com.pizza.CMController.PreisController"%>
<%@page import="com.pizza.CMModel.Speise"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="kunde" scope="session" class="com.pizza.Model.Kunde" />
<jsp:setProperty name="kunde" property="*" />
<jsp:useBean id="bestellungsList" scope="session" class="com.pizza.Controller.BestellungsList" />
<jsp:useBean id="bestellung" scope="session" class="com.pizza.Model.Bestellung" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rechnungbetrag</title>
    </head>
    <body>
        <%                    
            kunde.store();
            bestellung.setKundeId(kunde.getKundeId());
            bestellung.store(request);
            bestellungsList.setBestellungId(bestellung.getBestellungId());           
            
            // Data should be saved in table BestelungsItems            
            bestellungsList.store();
        %>
        <h1>Ihre Bestellung</h1>
        <table id="kunde">
            <thead>
                <tr>
                    <th>Kundenname:</th>
                    <td><%=kunde.getVorname() + " " + kunde.getNachname()%></td>
                </tr>
                <tr>
                    <th>Anschrift: </th>
                    <td><%=kunde.getStrasse() + " "
                            + kunde.getHausNr() + ","%></td>
                </tr>
                <tr>
                    <td></td>
                    <td><%=kunde.getPlz() + " "
                            + kunde.getStadt()%>
                    </td>
                </tr>
                <tr>
                    <th>Telefon: </th>
                    <td><%=kunde.getTelefonnummer()%>
                    </td>
                </tr>
            </thead>
        </table>
        <br>
        <table border="1px">
            <thead>
                <tr>
                    <th style="width: 400px; text-align: left;">Speise</th>
                    <th style="width: 75px;">Größe</th>
                    <th style="width: 100px;">Preis (in €)</th>
                    <th style="width: 100px;">Menge</th>                    
                    <th style="width: 150px;">Gesamt Preis (in €)</th>
                </tr>
            </thead>
            <tbody>
                <%  
                    Double summe = 0.0;
                    for (Map.Entry<Integer, Integer> item : bestellungsList.getBestellList().entrySet()) {
                        Speise speise = new Speise();
                        String speiseName = speise.getSpeiseFromPreisId(item.getKey()).getName();
                        out.print("<tr><td>" + speiseName + "</td>");
                        
                        // Grosse
                        Grosse grosse = new Grosse();
                        GrosseEintrag ge = grosse.getGrosseFromPreisId(item.getKey());
                        if (ge.getGrosseId() != -1){
                        out.print("<td>" + ge.getGrosseName()+ "</td>");
                        } else {
                            out.print("<td> </td>");
                        }
                        
                        // Preis
                        PreisController pc = new PreisController();
                        out.print("<td>" + pc.getPreisFromPreisId(item.getKey()) + "</td>");                        
                        out.print("<td>" + item.getValue() + "</td>");
                        out.print("<td>" + pc.getPreisFromPreisId(item.getKey()) * item.getValue() + "</td></tr>");
                        summe += pc.getPreisFromPreisId(item.getKey()) * item.getValue();
                    }                    
                %>
            </tbody>
            <tfoot>
                <% 
                    bestellungsList.setTotalPreis(summe);
                    // update the totalPreis in the bestellung table
                    bestellung.storeTotalPreis(summe);
                    out.print("<tr><td colspan='4' class='summe'>Summe</td><td>" + summe + "</td></tr>");
                %>
            </tfoot>
        </table>
            <a href="../rechnung.pdf">Zur Kasse</a>
    </body>
</html>
