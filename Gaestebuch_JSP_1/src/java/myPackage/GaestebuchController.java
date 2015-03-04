package myPackage;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import info.herstell.grund.Gaestebuch;
import info.herstell.grund.Eintrag;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Polichronis Tsolakidis
 */

public class GaestebuchController implements Serializable{

    
    private Eintrag currentEintrag;
   

    public GaestebuchController() {
        currentEintrag=new Eintrag();
    }
    
    
    
    public void createEintrag() {
        this.currentEintrag = new Eintrag();
       
    }
    public String getCountry()
    {
        return "resources.gaestebuch";
    }
    public void cancel() {
        this.currentEintrag = null;
        
    }

    public void save(HttpServletRequest req) {
        this.currentEintrag.setSessionid(req.getSession().getId());
        this.currentEintrag.setInet(req.getRemoteAddr());
        this.currentEintrag.store();
        this.currentEintrag = null;
       
    }

    public Eintrag getCurrentEintrag() {
        return this.currentEintrag;
    }

    public void setCurrentEintrag(Eintrag e) {
        this.currentEintrag = e;
    }

    public List<Eintrag> getEintraege() {
        Gaestebuch g = new Gaestebuch();
        return g.getList();
    }
}
