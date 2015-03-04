/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibb.servlet;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import myPackage.MyBean;

/**
 *
 * @author Schulung_IBB
 */
@WebServlet(name="myPdf",urlPatterns = "/pdfdokumente/myPdf.pdf")
public class PdfServlet extends HttpServlet{
    /**
     * 
     * @param req
     * @param resp 
     */
   
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp){
        try {
            resp.setContentType("application/pdf");
            //benötigter Zugriff auf die im Sessionscope
            //abgelegte Bean
//            HttpSession sess= req.getSession();
//            MyBean sessBean =(MyBean)sess.getAttribute("myBean");
            Document document = new Document();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, bos);
            document.open();
            PdfPTable table = new PdfPTable(2);
            for(int i=1;i<10;i++){
                document.add(new Paragraph("Das ist ein Absatz  " + i));
                
                table.addCell("Beckenbauer");
		table.addCell("Franz");
		
		// Code 3
		table.addCell("3");
		table.addCell("4");
		
		// Code 4
		table.addCell("5");
		table.addCell("6");
		
		// Code 5
		document.add(table);
                document.newPage();
            }
            document.close();
            OutputStream os =resp.getOutputStream();
            bos.writeTo(os);
            os.flush();
            os.close();
            for ( PrintService s : PrintServiceLookup.lookupPrintServices( null, null ) )System.out.println( s.getName() );
             
        } catch (DocumentException ex) {
            Logger.getLogger(PdfServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PdfServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp){
        doGet(req, resp);
    }
}
