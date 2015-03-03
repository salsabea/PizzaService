<%-- 
    Document   : speiseView
    Created on : 29.01.2015, 13:21:54
    Author     : Sabah Al-Sabea
--%>

<%@page import="com.pizza.CMModel.SpeiseEintrag"%>
<%@page import="com.pizza.CMModel.Speise"%>
<%@page import="com.pizza.CMController.PreisController"%>
<%@page import="com.pizza.CMController.SpeiseController"%>
<%@page import="com.pizza.CMController.GrosseController"%>
<%@page import="com.pizza.CMModel.GrosseEintrag"%>
<%@page import="com.pizza.CMModel.Grosse"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id = "mySpeiseEntry" scope="request" class="com.pizza.CMModel.SpeiseEintrag" />
<jsp:setProperty name="mySpeiseEntry" property="*" />
<jsp:useBean id = "myGrosseEntry" scope="request" class="com.pizza.CMModel.GrosseEintrag" />
<jsp:useBean id = "myPreisEntry" scope="request" class="com.pizza.CMModel.PreisEintrag" />
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
            if (mySpeiseEntry != null) {
                // Save Speise Entry
                SpeiseController sc = new SpeiseController();
                sc.setCurrentEintrag(mySpeiseEntry);
                // Store if Speise is not already in database
                if (!sc.contains(mySpeiseEntry.getName())) {
                    sc.getCurrentEintrag().store();
                }
                
                // Save Grosse Entry
                GrosseController gc = new GrosseController();
                if (!request.getParameter("neueGrosse").equals("")) {
                    myGrosseEntry.setGrosseId(gc.getGrosseId(myGrosseEntry.getGrosseName()));
                    myGrosseEntry.setGrosseName(request.getParameter("neueGrosse"));
                    myGrosseEntry.setGrosseBeschreibung(request.getParameter("neueBeschreibung"));
                    gc.setCurrentEintrag(myGrosseEntry);
                    
                    // Before storing, make sure it is not already there
                    if (gc.getGrosseId(myGrosseEntry.getGrosseName()) != -1){
                        gc.getCurrentEintrag().store();
                    }
                } else {
                    myGrosseEntry.setGrosseName(request.getParameter("grosseName"));
                    gc.setCurrentEintrag(myGrosseEntry);
                }
                

                // Save Preis Entry
                myPreisEntry.setSpeiseId(sc.getSpeiseId(mySpeiseEntry.getName()));
                myPreisEntry.setGrosseId(myGrosseEntry.getGrosseId());
                myPreisEntry.setPreis(Double.parseDouble(request.getParameter("preis")));
                
                if (myPreisEntry.getPreis() != 0) {
                    PreisController pc = new PreisController();
                    pc.setCurrentEintrag(myPreisEntry);
                    
                    // TODO: prevent double entries in table "preis"
                    pc.getCurrentEintrag().store();
                }
            }


        %>
        <table border="1">
            <tr>
                <td>Speise Name</td>
                <td style="width: 500px">Beschreibung</td>
                <td>Typ</td>
                <td>Image</td>
                <td>Preis in €</td>
                <td>Grosse</td>
                <td>Grosse Beschreibung</td>               
            </tr>
            <%                Speise speise = new Speise();
                List<SpeiseEintrag> speiseKarte = speise.getList();
                if (speiseKarte != null) {
                    for (SpeiseEintrag currentSE : speiseKarte) {
                        Grosse grosse = new Grosse();
                        List<GrosseEintrag> grosseList = grosse.getList(currentSE.getSpeiseId());

                        out.print("<tr>");
                        out.print("<td rowspan='" + grosseList.size() + "'>" + currentSE.getName() + "</td>");
                        out.print("<td rowspan='" + grosseList.size() + "'>" + currentSE.getBeschreibung() + "</td>");
                        out.print("<td rowspan='" + grosseList.size() + "'>" + currentSE.getTyp() + "</td>");
                        out.print("<td rowspan='" + grosseList.size() + "'>" + currentSE.getImage() + "</td>");

                        PreisController currentPC = new PreisController();
                        if (!grosseList.isEmpty()) {
                            for (GrosseEintrag currentGE : grosseList) {
                                out.print("<td>" + currentPC.getPreis(currentSE.getSpeiseId(), currentGE.getGrosseId()) + "</td>");
                                out.print("<td>" + currentGE.getGrosseName() + "</td>");
                                out.print("<td>" + currentGE.getGrosseBeschreibung() + "</td>");
                                out.print("</tr><tr>");
                            }
                        } else {
                            out.print("<td>" + currentPC.getPreis(currentSE.getSpeiseId()) + "</td>");
                            out.print("<td colspan='2'></td>");
                            out.print("</tr>");
                        }
                    }
                }

            %>
        </table>

        <a href="contentManager.jsp">Neue Speise hinzufugen</a>
    </body>
</html>
