/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event.entities;

import java.sql.Date;

/**
 *
 * @author tasnim
 */
public class Event {
    private int id ;   
    private String nom;
    private Date date_debut;
    private Date date_fin;
     private boolean liked;
  

    public Event() {
    }

    public Event(int id, String nom, Date date_debut, Date date_fin) {
        this.id = id;
        this.nom = nom;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Event(String nom, Date date_debut, Date date_fin) {
        this.nom = nom;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Event(Date date_debut, Date date_fin) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
   
    }

 


    public Event(String nom) {
        this.nom = nom;
    }

    
        public boolean isLiked() {
        return liked;
    }
   public void setLiked(boolean liked) {
        this.liked = liked;
    }
    public int getId() {
        return id;
    }
    
      public int getLikes() {
        return liked ? 1 : 0;
    }
    

    public String getNom() {
        return nom;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

  

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", nom=" + nom + ", date_debut=" + date_debut + ", date_fin=" + date_fin + '}';
    }
    
    
    
}
