<%-- 
    Document   : index
    Created on : 22.01.2014, 08:26:34
    Author     : Schulung_IBB
--%>
<%@page import="myPackage.GaestebuchController"%>
<%@page import="info.herstell.grund.Eintrag"%>
<%@page import="info.herstell.grund.Gaestebuch"%>
<jsp:useBean id="myEintrag" scope="request" class="info.herstell.grund.Eintrag" />
<%--hier werden die Propertys meines "POJO" gesetzt durch setPropertyAction --%>
<jsp:setProperty name="myEintrag" property="*"  />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
        <%
            
        /*hier werden die Propertys meines "POJO" gesetzt durch Setter   
        myEintrag.setNachname("Beckenbauer");
        myEintrag.setVorname("Franz");
        myEintrag.setEmail("Franz@Beckenbauer.de");
        myEintrag.setKommentar("Hallo liebes Gaestebuch");
        myEintrag.setInet("localhost");
        myEintrag.setSessionid("1234567");
        myEintrag.setRegion("Bayern");
        //....
        //....
        //..
        //myEintrag.s();
       /* if(myEintrag.storeWeb()){
          out.print("Datensatz gespeichert");
        };
        
               */
        //hier wird mein "POJO" gespeichert
        //myEintrag.setInet(request.getRemoteAddr());
        //myEintrag.setSessionid(session.getId());  
        GaestebuchController gbc=new GaestebuchController();
        gbc.setCurrentEintrag(myEintrag);
        gbc.save(request);
        //myEintrag.store();
        //hier wird lesend auf die Datenbank zugegriffen
        Gaestebuch myGuest=new Gaestebuch();
        
        
        for(Eintrag tmp:myGuest.getList()){
            out.print("<tr><td>"+tmp.getNachname()+"</td>");
            out.print("<td>"+tmp.getVorname()+"</td>");
            out.print("<td>"+tmp.getRegion()+"</td></tr>");
        
        }    
            
            %>
        </table>
    </body>
</html>
