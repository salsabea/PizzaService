<%-- 
    Document   : index
    Created on : 19.01.2015, 11:27:29
    Author     : Schulung
--%>



<%@page import="myPackage.Calculate"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3>
            <%! 
            String getMesg(){
                String txt="Hallo Welt";
                return txt;
            }
            %>    
        <%= new Date()%>
        <%  
            Calculate myCalc=new Calculate(10.1,20.3);
            myCalc.setZahl1(10.1);
            myCalc.setZahl2(15.3);
            out.println(myCalc.add());
            System.out.println("Hallo heute ist" + new Date());
        %>
        <%
            String[] valueArray = {"This", "is", "a", "rather", "dull", "example"};
            int i;
            for (i = 0; i < valueArray.length; i++) {
                out.println(valueArray[i]);
                out.print(getMesg());
            }
        %>   
    </h3>

    <p style="padding-top:10px;">Or put another way: 
        <%
            for (i = 0; i < valueArray.length; i++) {
       
            out.print("<p>valueArray["+ i + "]="+ valueArray[i]+"<br>");
      
            }
        %>
</body>
</html>
