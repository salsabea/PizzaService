<%-- 
    Document   : speiseView
    Created on : 29.01.2015, 13:21:54
    Author     : IBB Teilnehmer
--%>

<%@page import="com.pizza.controller.PreisController"%>
<%@page import="com.pizza.controller.GrosseController"%>
<%@page import="java.util.List"%>
<%@page import="com.pizza.model.SpeiseEintrag"%>
<%@page import="com.pizza.model.Speise"%>
<%@page import="com.pizza.controller.SpeiseController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id = "mySpeiseEntry" scope="request" class="com.pizza.model.SpeiseEintrag" />
<jsp:setProperty name="mySpeiseEntry" property="*" />
<jsp:useBean id = "myGrosseEntry" scope="request" class="com.pizza.model.GrosseEintrag" />
<jsp:useBean id = "myPreisEntry" scope="request" class="com.pizza.model.PreisEintrag" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Speise Karte</title>
        <style>
            body {
                font-family: Verdana,Geneva,Tahoma,sans-serif;
            }
        </style>
    </head>
    <body>
        <%
            SpeiseController sc = new SpeiseController();
            if (mySpeiseEntry != null) {
                sc.setCurrentEintrag(mySpeiseEntry);
                sc.getCurrentEintrag().store();
            }

            GrosseController gc = new GrosseController();
            if (!request.getParameter("neueGrosse").equals("")) {
                myGrosseEntry.setGrosseName(request.getParameter("neueGrosse"));
                myGrosseEntry.setGrosseBeschreibung(request.getParameter("neueBeschreibung"));
                gc.setCurrentEintrag(myGrosseEntry);
                gc.getCurrentEintrag().store();
            } else {
                myGrosseEntry.setGrosseName(request.getParameter("grosseName"));
                gc.setCurrentEintrag(myGrosseEntry);
            }
            myGrosseEntry.setGrosseId(gc.getGrosseId(myGrosseEntry.getGrosseName()));

            myPreisEntry.setSpeiseId(sc.getSpeiseId(mySpeiseEntry.getName()));
            myPreisEntry.setGrosseId(myGrosseEntry.getGrosseId());
            myPreisEntry.setPreis(Double.parseDouble(request.getParameter("preis")));

            if (myPreisEntry.getPreis() != 0) {
                PreisController pc = new PreisController();
                pc.setCurrentEintrag(myPreisEntry);
                pc.getCurrentEintrag().store();
            }
        %>
        <table border="1px">
            <tr>
                <td>Speise Name</td>
                <td style="width: 500px">Beschreibung</td>
                <td>Typ</td>
                <td>Image</td>
            </tr>
            <%
                Speise speise = new Speise();
                List<SpeiseEintrag> speiseKarte = speise.getList();
                if (speiseKarte != null) {
                    for (SpeiseEintrag currentSE : speiseKarte) {
                        out.print("<tr><td>" + currentSE.getName() + "</td>");
                        out.print("<td>" + currentSE.getBeschreibung() + "</td>");
                        out.print("<td>" + currentSE.getTyp() + "</td>");
                        out.print("<td>" + currentSE.getImage() + "</td></tr>");
                    }
                }

            %>
        </table>

        <a href="contentManager.jsp">Neue Speise hinzufugen</a>
    </body>
</html>
