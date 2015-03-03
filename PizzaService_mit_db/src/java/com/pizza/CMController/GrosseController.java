/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizza.CMController;

import com.pizza.CMModel.Grosse;
import com.pizza.CMModel.GrosseEintrag;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sabah Al-Sabea
 */
public class GrosseController implements Serializable {

    private GrosseEintrag currentGrosse;

    public GrosseController() {
        this.currentGrosse = new GrosseEintrag();
    }

    public void createGrosse() {
        this.currentGrosse = new GrosseEintrag();
    }

    public void cancel() {
        this.currentGrosse = null;
    }

    public GrosseEintrag getCurrentEintrag() {
        return this.currentGrosse;
    }

    public void setCurrentEintrag(GrosseEintrag e) {
        this.currentGrosse = e;
    }

    public List<GrosseEintrag> getEintraege() {
        Grosse g = new Grosse();
        return g.getList();
    }

    public List<GrosseEintrag> getEintraege(Integer speiseId) {
        Grosse g = new Grosse();
        return g.getList(speiseId);
    }

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
