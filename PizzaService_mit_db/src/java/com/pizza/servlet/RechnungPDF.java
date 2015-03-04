/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizza.servlet;

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
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.pizza.Model.Kunde;
import com.pizza.Controller.BestellungsList;

/**
 *
 * @author Sabah Al-Sabea
 */
@WebServlet(name="rechnungPDF",urlPatterns = "/rechnung.pdf")
public class RechnungPDF extends HttpServlet{
    /**
     * 
     * @param req
     * @param resp 
     */
   
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp){
        try {
            resp.setContentType("application/pdf");
            //benötigter Zugriff auf die im Sessionscope
            //abgelegte Bean
            HttpSession sess= req.getSession();
            Kunde kundeBean =(Kunde)sess.getAttribute("kunde");
            BestellungsList bestellungsListBean =(BestellungsList)sess.getAttribute("bestellungsList");
            Document document = new Document();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, bos);
            document.open();
            document.add(new Paragraph("Rechnung Betrag"));
            document.add(new Paragraph(" "));
            PdfPTable table = new PdfPTable(2);

                // Row 1
                table.addCell("Kundenname");
		table.addCell(kundeBean.getVorname() + " " + kundeBean.getNachname());
		
		// Row 2
		table.addCell("Anschrift:");
		table.addCell(kundeBean.getStrasse() + " " + kundeBean.getHausNr() + ",");
		
		// Row 3
		table.addCell(" ");
		table.addCell(kundeBean.getPlz() + " " + kundeBean.getStadt());
				
                // Row 4
                table.addCell("Telefon:");
		table.addCell(kundeBean.getTelefonnummer());
                
		document.add(table);
                document.newPage();
  
            document.close();
            OutputStream os =resp.getOutputStream();
            bos.writeTo(os);
            os.flush();
            os.close();
            for ( PrintService s : PrintServiceLookup.lookupPrintServices( null, null ) )System.out.println( s.getName() );
             
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(RechnungPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp){
        doGet(req, resp);
    }
}
