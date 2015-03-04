package com.pizza.CMController;

import com.pizza.CMModel.PreisEintrag;
import com.pizza.CMModel.Preise;
import java.io.Serializable;
import java.util.List;

/**
 * <h1>Controls the PreisEintrag Model</h1>
 * The PreisController class implements the controller of the PreisEintrag model.
 * It contains some methods used to fetch the entries of the Preis table 
 * from the database as well as other values 
 * 
 * @author Sabah Al-Sabea
 */
public class PreisController implements Serializable {

    private PreisEintrag currentPrice;

    public PreisController() {
        this.currentPrice = new PreisEintrag();
    }

    /**
     * 
     * @return PreisEintrag This is the current price eintrag
     */
    public PreisEintrag getCurrentPrice() {
        return this.currentPrice;
    }

    /**
     * 
     * @param eintrag the current preisEintrag to be set
     */
    public void setCurrentPrice(PreisEintrag eintrag) {
        this.currentPrice = eintrag;
    }

    /**
     * This method gets all the entries from the Preis table
     * 
     * @return List<PreisEintrag> This is a list of all the Preis entries
     */
    public List<PreisEintrag> getEintraege() {
        Preise p = new Preise();
        return p.getList();
    }

    /**
     * This method gets the preisId from the Preis table for a certain Speise i.e. with
     * a specific speiseId. In this case this speise has no specified grosse.
     * 
     * @param speiseId This is the ID of the speise whose preisId we are looking for
     * @return Integer This is the preisId of the Speise with SpeiseId = {@code speiseId}
     */
    public Integer getPreisId(Integer speiseId) {
        List<PreisEintrag> eintraege = this.getEintraege();
        for (PreisEintrag pe : eintraege) {
            if (pe.getSpeiseId() == speiseId) {
                return pe.getPreisId();
            }
        }
        return -1;
    }
    
    /**
     * This method gets the preisId from the Preis table for a certain Speise i.e. with
     * a specific speiseId and grosseId. 
     * 
     * @param speiseId This is the ID of the speise whose preisId we are looking for
     * @param grosseId This is the ID of the grosse whose preisId we are looking for
     * @return Integer This is the preisId of the Speise with SpeiseId = {@code speiseId}
     * and grosseId = {@code grosseId}
     */
    public Integer getPreisId(Integer speiseId, Integer grosseId) {
        List<PreisEintrag> eintraege = this.getEintraege();
        for (PreisEintrag pe : eintraege) {
            if (pe.getSpeiseId() == speiseId && pe.getGrosseId() == grosseId) {
                return pe.getPreisId();
            }
        }
        return -1;
    }

    /**
     * This method gets the preis from the Preis table for a certain Speise i.e. with
     * a specific speiseId. In this case this speise has no specified grosse.
     * 
     * @param speiseId This is the ID of the speise whose preis we are looking for
     * @return Integer This is the preis of the Speise with SpeiseId = {@code speiseId}
     */
    public Double getPreis(Integer speiseId) {
        List<PreisEintrag> eintraege = this.getEintraege();
        for (PreisEintrag pe : eintraege) {
            if (pe.getSpeiseId() == speiseId) {
                return pe.getPreis();
            }
        }
        return -1.0;
    }
    
    /**
     * This method gets the preis from the Preis table for a certain Speise i.e. with
     * a specific speiseId and grosseId. 
     * 
     * @param speiseId This is the ID of the speise whose preis we are looking for
     * @param grosseId This is the ID of the grosse whose preis we are looking for
     * @return Double This is the preis of the Speise with speiseId = {@code speiseId}
     * and grosseId = {@code grosseId}
     */
    public Double getPreis(Integer speiseId, Integer grosseId) {
        List<PreisEintrag> eintraege = this.getEintraege();
        for (PreisEintrag pe : eintraege) {
            if (pe.getSpeiseId() == speiseId && pe.getGrosseId() == grosseId) {
                return pe.getPreis();
            }
        }
        return -1.0;
    }    
    
    /**
     * This method gets the preis from the Preis table that has a specific preisId
     * 
     * @param preisId This is the preisId whose preis we are looking for
     * @return Double This is the preis of preisId = {@code preisId}
     * and grosseId = {@code grosseId}
     */
    public Double getPreisFromPreisId(Integer preisId) {
        List<PreisEintrag> eintraege = this.getEintraege();
        for (PreisEintrag pe : eintraege) {
            if (pe.getPreisId() == preisId) {
                return pe.getPreis();
            }
        }
        return -1.0;
    }
}
