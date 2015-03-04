/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myPackage;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 *
 * @author Schulung
 */
public class SimpleDateTag extends BodyTagSupport{
    
    public int doStartTag(){
        try {
            JspWriter out=pageContext.getOut();
            
            out.println("<h1>" + new Date()+ "</h1>");
            
            
        } catch (IOException ex) {
            Logger.getLogger(SimpleDateTag.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SKIP_BODY;
    }
    
}
