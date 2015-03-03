<%-- 
    Document   : contentManager
    Created on : 28.01.2015, 11:00:37
    Author     : Sabah Al-Sabea
--%>


<%@page import="java.util.List"%>
<%@page import="com.pizza.CMModel.GrosseEintrag"%>
<%@page import="com.pizza.CMModel.Grosse"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Content Manager</title>        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script>
            $(function (ready) {
                $("#grosseName").change(function () {
                    if ($("#grosseName").val() === "other") {
                        $("#neueGrosse").show().find('input').focus();
                        $("#neueBeschreibung").show();
                    } else {
                        $("#neueGrosse").hide();
                        $("#neueBeschreibung").hide();
                    }
                });
            });
        </script>
        <style>
            body {
                font-family: Verdana,Geneva,Tahoma,sans-serif;
            }
        </style>
    </head>
    <body>

        <h3>Bitte benutzen Sie dieses Formular um neue Speise in die Speisekarte hinzufügen</h3>
        <form id="speise-form" action="speiseView.jsp" method="POST">

            <table>
                <tr>
                    <td>Speise Name *</td>
                    <td><input type="text" id="name" name="name" placeholder="z.B. Pizza Margherita" required /></td>  
                </tr>
                <tr>
                    <td>Beschreibung</td>
                    <td><input type="text" name="beschreibung" placeholder="z.B. mit Tomaten und Mozzarella gebacken." /></td> 
                </tr>
                <tr>
                    <td>Typ *</td>
                    <td><select name="typ" required>
                            <option value="" disabled="" selected="">- auswählen -</option>
                            <option>Pizza Gerichte</option>
                            <option>Pasta Gerichte</option>
                            <option>Salat</option>
                            <option value="Getraenke">Getränke</option>
                            <option>Burger</option>
                            <option>Desserts</option>
                            <option>Extra Zutaten</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Image</td>
                    <td><input type="text" name="image" /></td>
                </tr> 
                <tr>
                    <td>Grosse *</td>
                    <td>
                        <select id="grosseName" name="grosseName" required>
                            <option value="" disabled selected>- Grösse Wählen ggf. -</option>
                            <%
                                Grosse grosse = new Grosse();
                                List<GrosseEintrag> grosseList = grosse.getList();
                                if (grosse != null) {
                                    for (GrosseEintrag currentGE : grosseList) {
                                        out.print("<option value='" + currentGE.getGrosseName() + "'>" + currentGE.getGrosseName() + "</option>");
                                    }
                                }

                            %>
                            <option value="other">- Andere -</option>
                        </select>                            
                    </td>
                </tr>
                <tr id="neueGrosse" hidden>
                    <td>Grosse Name *</td>
                    <td style="font-size: 0.7em"><input type="text" name="neueGrosse" placeholder="z.B. Klein, 400ml" />
                            * Mindestens ein Grossename muss hinzufügt werden</td>
                </tr>
                <tr id="neueBeschreibung" hidden>
                    <td>Grosse Beschreibung</td>
                    <td><input type="text" name="neueBeschreibung" placeholder="z.B. 18cm" /></td>
                </tr> 
                <tr>
                    <td>Preis in € *</td>
                    <td><input type="text" name="preis" required /></td>
                </tr>
            </table>
            <p style="font-size: 0.7em">* Zwingend</p>
            
            <input type="submit" name="speichern" value="Hinzufügen" />
        </form>
    </body>
</html>
