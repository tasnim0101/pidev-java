/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

/**
 *
 * @author Fayechi
 */
public class Reclamation {
     private int id;
     private String message;
     
     
     public Reclamation(){}
     public Reclamation(int id,String message){
         this.id=id;
         this.message=message;
     }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
     
     
    
}
