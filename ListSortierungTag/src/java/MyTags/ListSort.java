/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyTags;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 *
 * @author IBB Teilnehmer
 */
public class ListSort extends BodyTagSupport {

    private Boolean ordered;

    /**
     *
     * @return
     */
    @Override
    public int doAfterBody() {

        BodyContent bc = getBodyContent();
        JspWriter out = bc.getEnclosingWriter();
        
        List<String> myList = Arrays.asList(bc.getString().split("\\r?\\n"));
        
        String openTag = "<ul>";
        String closeTag = "</ul>";

        System.out.println("the ordered attribute is: " + this.ordered);
        if (this.ordered) {
            Collections.sort(myList);
            openTag = "<ol>";
            closeTag = "</ol>";
        }
        try {
            out.print(openTag);
            for (String listElement : myList) {
                out.print("<li>" + listElement.trim() + "</li>");
                System.out.println(".." + listElement.trim());
            }
            out.print(closeTag);
        } catch (IOException ex) {
            Logger.getLogger(ListSort.class.getName()).log(Level.SEVERE, null, ex);
        }

        return SKIP_BODY;
    }

    public void setGeordnet(String ordered) {
        this.ordered = ordered.contains("true");
    }
}
