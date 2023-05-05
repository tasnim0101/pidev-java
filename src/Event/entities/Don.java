/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event.entities;

/**
 *
 * @author tasnim
 */
public class Don {
    
      int id;
      Event events;
    String nom;
    Float montant;


    public Don() {
    }

    public Don(int id, Event events, String nom, Float montant) {
        this.id = id;
        this.events = events;
        this.nom = nom;
        this.montant = montant;
    }

    public Don(Event events, String nom, Float montant) {
        this.events = events;
        this.nom = nom;
        this.montant = montant;
    }

    public int getId() {
        return id;
    }

    public Event getEvents() {
        return events;
    }

    public String getNom() {
        return nom;
    }

    public Float getMontant() {
        return montant;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEvents(Event events) {
        this.events = events;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setMontant(Float montant) {
        this.montant = montant;
    }

    @Override
    public String toString() {
        return "Don{" + "id=" + id + ", events=" + events + ", nom=" + nom + ", montant=" + montant + '}';
    }



 

 
    
    
  
     
     
     
}
