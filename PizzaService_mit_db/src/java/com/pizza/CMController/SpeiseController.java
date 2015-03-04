package com.pizza.CMController;

import com.pizza.CMModel.Speise;
import com.pizza.CMModel.SpeiseEintrag;
import java.io.Serializable;
import java.util.List;

/**
 * <h1>Controls the SpeiseEintrag Model</h1>
 * The SpeiseController class implements the controller of the SpeiseEintrag model.
 * It contains some methods used to fetch the entries of the Speise table 
 * from the database as well as other values 
 * 
 * @author Sabah Al-Sabea
 */
public class SpeiseController implements Serializable{
    private SpeiseEintrag currentSpeise;
    private Speise speise;
    
    public SpeiseController(){
        this.currentSpeise = new SpeiseEintrag();
    }  
    
    /**
     * 
     * @return SpeiseEintrag This is the current Speise eintrag
     */
    public SpeiseEintrag getCurrentSpeise() {
        return this.currentSpeise;
    }

    /**
     * 
     * @param eintrag the current speiseEintrag to be set
     */
    public void setCurrentSpeise(SpeiseEintrag eintrag) {
        this.currentSpeise = eintrag;
    }
    
    /**
     * 
     * @return Speise This is the current Speise
     */
    public Speise getSpeise() {
        return speise;
    }

    /**
     * 
     * @param speise the current speise to be set
     */
    public void setSpeise(Speise speise) {
        this.speise = speise;
    }

    /**
     * This method gets all the entries from the Speise table
     * 
     * @return List<SpeiseEintrag> This is a list of all the Speise entries
     */
    public List<SpeiseEintrag> getEintraege() {
        Speise g = new Speise();
        return g.getList();
    }
    
    /**
     * This method gets the speiseId from the Speise table that has a specific speiseName
     * 
     * @param speiseName This is the name of the speise whose ID we are looking for
     * @return Integer This is the ID of the speise whose speiseName = {@code speiseName}
     */    
    public Integer getSpeiseId(String speiseName){
        List<SpeiseEintrag> eintraege = this.getEintraege();
        for (SpeiseEintrag se : eintraege){
            if (se.getName().equals(speiseName)) 
                return se.getSpeiseId();
        }
        return -1;
    }
    
    /**
     * This method checks if a speise with the same name already exists in the database. 
     * This is used to prevent duplication.
     * 
     * @param speiseName This is the name of the speise we are looking for
     * @return Boolean true if already exists
     */ 
    public Boolean contains(String speiseName){
        List<SpeiseEintrag> eintraege = this.getEintraege();
        for (SpeiseEintrag se : eintraege){
            if (se.getName().equals(speiseName)) 
                return true;
        }
        return false;
    }    
}
