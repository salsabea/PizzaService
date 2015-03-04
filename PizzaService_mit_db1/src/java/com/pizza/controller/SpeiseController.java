/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizza.controller;

import com.pizza.model.Speise;
import com.pizza.model.SpeiseEintrag;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Sabah Al-Sabea
 */
public class SpeiseController implements Serializable{
    private SpeiseEintrag currentSpeise;
    
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
}
