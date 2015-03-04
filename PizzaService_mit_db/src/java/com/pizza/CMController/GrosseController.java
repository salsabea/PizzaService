package com.pizza.CMController;

import com.pizza.CMModel.Grosse;
import com.pizza.CMModel.GrosseEintrag;
import java.io.Serializable;
import java.util.List;

/**
 * <h1>Controls the GrosseEintrag Model</h1>
 * The GrosseController class implements the controller of the GrosseEintrag model.
 * It contains some methods used to fetch the entries of the Grosse table 
 * from the database as well as other values 
 * 
 * @author Sabah Al-Sabea
 */
public class GrosseController implements Serializable {

    private GrosseEintrag currentGrosse;

    public GrosseController() {
        this.currentGrosse = new GrosseEintrag();
    }

    /**
     * 
     * @return GrosseEintrag This is the current Grosse eintrag
     */
    public GrosseEintrag getCurrentGrosse() {
        return this.currentGrosse;
    }

    /**
     * 
     * @param eintrag the current grosseEintrag to be set
     */
    public void setCurrentGrosse(GrosseEintrag eintrag) {
        this.currentGrosse = eintrag;
    }

    /**
     * This method gets all the entries from the Grosse table
     * 
     * @return List<GrosseEintrag> This is a list of all the Grosse entries
     */
    public List<GrosseEintrag> getEintraege() {
        Grosse g = new Grosse();
        return g.getList();
    }

    
    /**
     * This method gets all the entries from the Grosse table that have a specific
     * speiseId. Ex. a speise X that has speiseID id_X can have more than one 
     * size i.e. Grosse.
     * 
     * @param speiseId This is the ID of the speise whose grosse entry we are looking for
     * @return List<GrosseEintrag> This is a list of Grosse entries with SpeiseId = {@code speiseId}
     */
    public List<GrosseEintrag> getEintraege(Integer speiseId) {
        Grosse g = new Grosse();
        return g.getList(speiseId);
    }

    /**
     * This method gets the Grosse Id from the Grosse table with a specific grosseName
     * 
     * @param grosseName This is the name of the grosse whose ID we are looking for
     * @return Integer This is the grosseID whose name = {@code grosseName}
     */
    public Integer getGrosseId(String grosseName) {
        List<GrosseEintrag> eintraege = this.getEintraege();
        for (GrosseEintrag ge : eintraege) {
            if (ge.getGrosseName().equals(grosseName)) {
                return ge.getGrosseId();
            }
        }
        return -1;
    }        
}
