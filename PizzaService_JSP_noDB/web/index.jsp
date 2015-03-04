<%-- 
    Document   : index
    Created on : 20.01.2015, 10:48:58
    Author     : Sabah Al-Sabea
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Model.Speise"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pizza Bestellservice</title>
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
        <%
            // Create and fill the menu
            ArrayList<Speise> pizzaVarianten = new ArrayList<Speise>();
            pizzaVarianten.add(new Speise(1, "Pizza Margherita", 4.90, "mit Tomaten und Mozzarella gebacken. Nach dem Backen mit frischem Basilikum verfeinert. "));
            pizzaVarianten.add(new Speise(2, "Pizza Vegetaria", 7.50, "mit frischen Champignons, Blattspinat, Broccoli-Röschen, Paprikastreifen, sonnengetrockneten Tomaten und in den Teig eingebackenen Sonnenblumenkernen."));
            pizzaVarianten.add(new Speise(3, "Pizza Funghi", 5.90, "mit frischen Champignons und Basilikum-Pesto"));
            pizzaVarianten.add(new Speise(4, "Pizza Mediterrana", 6.0, "mit Tomaten und Mozzarella gebacken. Anschließend belegt mit Rucola, Serranoschinken, Balsamico-Creme und gehobeltem Grana Padano"));
            pizzaVarianten.add(new Speise(5, "Pizza Salami", 5.90, "mit leckerer Salami"));
            pizzaVarianten.add(new Speise(6, "Pizza Tuna", 7.50, "mit extra viel Thunfisch und roten Zwiebeln"));

            application.setAttribute("menu", pizzaVarianten);
        %>
        <form action="intermediatePage.jsp" method="POST">
            <h1>Herzlich Willkommen bei Pizza La Gondola </h1>
            <table rules="groups">
                <thead>
                    <tr>                  
                        <th></th>
                        <th style="width: 300px">Pizza Variante</th>
                        <th style="width: 75px">Preis</th>
                        <th style="width: 75px">Menge</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (Speise pizza : pizzaVarianten) {
                            out.print("<tr>");
                            out.print("<td>" + pizza.getId() + ". </td>");
                            out.print("<td>" + pizza.getName() + "</td>");
                            out.print("<td style='text-align: center'>" + pizza.getPreis() + " €</td>");
                            out.print("<td  style='text-align: center'><input type='text' name='speise" + pizza.getId() + "' value='0' size='1px' /></td>");
                            out.print("</tr>");
                            out.print("<tr>");
                            out.print("<td></td>");
                            out.print("<td class='smaller-td'>" + pizza.getBeschreibung() + "</td>");
                            out.print("</tr>");
                            out.print("<tr><td><br></td></tr>");
                        }
                    %>
                </tbody>
            </table>
            <input type="submit" value="Bestellen" name="next" />
        </form>

    </body>
</html>
