/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.herstell.bean;

import info.herstell.model.Person;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Schulung
 */
@ManagedBean
@SessionScoped
public class Controller implements Serializable{
    private List<Person> lstPerson;
    private Person myPerson;
     private String[] carList={"Audi","Skoda","Opel","Mercedes"};
     private String[] checkedList;
     private String language;

    public Controller() {
        //myPerson=new Person();
        language="myMessages";
        lstPerson=new ArrayList();
    }
    
    

    public Person getMyPerson() {
        if(myPerson==null){
            myPerson=new Person();
        }
        return myPerson;
    }

    public void setMyPerson(Person myPerson) {
        this.myPerson = myPerson;
    }
    
    public void doSomething(){
        lstPerson.add(myPerson);
       
        System.out.println("Vornamen" + myPerson.getFirstname());
         myPerson=null;
    }
    
     public String doSomething1(){
        System.out.println("Vornamen" + myPerson.getFirstname());
        return "marke1";
    }
     
      public String doSomething2(){
        //System.out.println("Vornamen" + myPerson.getFirstname());
        //return "marke2";
        return "";
    }

    public List<Person> getLstPerson() {
        return lstPerson;
    }

    public void setLstPerson(List<Person> lstPerson) {
        this.lstPerson = lstPerson;
    }

    public String[] getCarList() {
        return carList;
    }

    public void setCarList(String[] carList) {
        this.carList = carList;
    }

    public String[] getCheckedList() {
        return checkedList;
    }

    public void setCheckedList(String[] checkedList) {
        this.checkedList = checkedList;
    }
    
    public void switchEnglish(){
        language ="myMessages_en";
        
    }    
    public void switchFarsi(){
        language ="myMessages_fa";
        
    }    

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    
    
}
