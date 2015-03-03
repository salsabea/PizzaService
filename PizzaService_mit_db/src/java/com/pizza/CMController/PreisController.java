/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizza.CMController;

import com.pizza.CMModel.PreisEintrag;
import com.pizza.CMModel.Preise;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Sabah
 */
public class PreisController implements Serializable {

    private PreisEintrag currentPrice;

    public PreisController() {
        this.currentPrice = new PreisEintrag();
    }

    public void createPreis() {
        this.currentPrice = new PreisEintrag();
    }

    public void cancel() {
        this.currentPrice = null;
    }

    public PreisEintrag getCurrentEintrag() {
        return this.currentPrice;
    }

    public void setCurrentEintrag(PreisEintrag e) {
        this.currentPrice = e;
    }

    public List<PreisEintrag> getEintraege() {
        Preise p = new Preise();
        return p.getList();
    }

    public Integer getPreisId(Integer SpeiseId, Integer GrosseId) {
        List<PreisEintrag> eintraege = this.getEintraege();
        for (PreisEintrag pe : eintraege) {
            if (pe.getSpeiseId() == SpeiseId && pe.getGrosseId() == GrosseId) {
                return pe.getPreisId();
            }
        }
        return -1;
    }

    public Integer getPreisId(Integer SpeiseId) {
        List<PreisEintrag> eintraege = this.getEintraege();
        for (PreisEintrag pe : eintraege) {
            if (pe.getSpeiseId() == SpeiseId) {
                return pe.getPreisId();
            }
        }
        return -1;
    }
    
    public Double getPreis(Integer SpeiseId, Integer GrosseId) {
        List<PreisEintrag> eintraege = this.getEintraege();
        for (PreisEintrag pe : eintraege) {
            if (pe.getSpeiseId() == SpeiseId && pe.getGrosseId() == GrosseId) {
                return pe.getPreis();
            }
        }
        return -1.0;
    }    
    
    public Double getPreis(Integer SpeiseId) {
        List<PreisEintrag> eintraege = this.getEintraege();
        for (PreisEintrag pe : eintraege) {
            if (pe.getSpeiseId() == SpeiseId) {
                return pe.getPreis();
            }
        }
        return -1.0;
    }
    
    public Double getPreisFromPreisId(Integer PreisId) {
        List<PreisEintrag> eintraege = this.getEintraege();
        for (PreisEintrag pe : eintraege) {
            if (pe.getPreisId() == PreisId) {
                return pe.getPreis();
            }
        }
        return -1.0;
    }
}
