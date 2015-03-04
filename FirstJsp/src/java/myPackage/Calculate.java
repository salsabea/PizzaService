/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myPackage;

/**
 *
 * @author Schulung
 */
public class Calculate {
    private Double zahl1;
    private Double zahl2;

    public Calculate() {
    }

    
    
    public Calculate(Double zahl1, Double zahl2) {
        this.zahl1 = zahl1;
        this.zahl2 = zahl2;
    }

    
    
    
    public Double getZahl1() {
        return zahl1;
    }

    public void setZahl1(Double zahl1) {
        this.zahl1 = zahl1;
    }

    public Double getZahl2() {
        return zahl2;
    }

    public void setZahl2(Double zahl2) {
        this.zahl2 = zahl2;
    }
    
    public Double add(){
        
        return zahl1+zahl2;
    }
    
}
