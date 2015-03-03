/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizza.CMController;

import com.pizza.CMModel.Speise;
import com.pizza.CMModel.SpeiseEintrag;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Sabah Al-Sabea
 */
public class SpeiseController implements Serializable{
    private SpeiseEintrag currentSpeise;
    private Speise speise;
    
    public SpeiseController(){
        this.currentSpeise = new SpeiseEintrag();
    }
    
    public void createSpeise(){
        this.currentSpeise = new SpeiseEintrag();
    }
    
    public void cancel(){
        this.currentSpeise = null;
    }
    
    
    public SpeiseEintrag getCurrentEintrag() {
        return this.currentSpeise;
    }

    public void setCurrentEintrag(SpeiseEintrag e) {
        this.currentSpeise = e;
    }

    public List<SpeiseEintrag> getEintraege() {
        Speise g = new Speise();
        return g.getList();
    }
    
    public Integer getSpeiseId(String speiseName){
        List<SpeiseEintrag> eintraege = this.getEintraege();
        for (SpeiseEintrag se : eintraege){
            if (se.getName().equals(speiseName)) 
                return se.getSpeiseId();
        }
        return -1;
    }
    
    public Boolean contains(String speiseName){
        List<SpeiseEintrag> eintraege = this.getEintraege();
        for (SpeiseEintrag se : eintraege){
            if (se.getName().equals(speiseName)) 
                return true;
        }
        return false;
    }

    public Speise getSpeise() {
        return speise;
    }

    public void setSpeise(Speise speise) {
        this.speise = speise;
    }
    
}
