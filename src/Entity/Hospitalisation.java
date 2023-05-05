/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import User.entities.User;
import java.sql.Date;

/**
 *
 * @author Fayechi
 */
public class Hospitalisation {
    private Date date_entree,date_sortie;
    private int id;
    private int id_hospitalisation;
    Services S ;
    User u;

    public Hospitalisation(Date date_entree, Date date_sortie, int id, int id_hospitalisation, Services S, User u) {
        this.date_entree = date_entree;
        this.date_sortie = date_sortie;
        this.id = id;
        this.id_hospitalisation = id_hospitalisation;
        this.S = S;
        this.u = u;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }
    

    public Hospitalisation() {
    }

   

    public Hospitalisation(Date date_entree) {
        this.date_entree = date_entree;
    }

    public Hospitalisation(Date date_entree, Date date_sortie, int id, int id_hospitalisation, Services S) {
        this.date_entree = date_entree;
        this.date_sortie = date_sortie;
        this.id = id;
        this.id_hospitalisation = id_hospitalisation;
        this.S = S;
    }

    public Hospitalisation(Services S) {
        this.S = S;
    }

    public Hospitalisation(int id, int id_hop, int serv_id, Date valueOf, Date valueOf0) {
           }

    public Hospitalisation(int id_hospitalisation, Services op, Date valueOf, Date valueOf0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Date getDate_entree() {
        return date_entree;
    }

    public Date getDate_sortie() {
        return date_sortie;
    }

    public int getId() {
        return id;
    }

    public int getId_hospitalisation() {
        return id_hospitalisation;
    }

    public Services getS() {
        return S;
    }

    public void setDate_entree(Date date_entree) {
        this.date_entree = date_entree;
    }

    public void setDate_sortie(Date date_sortie) {
        this.date_sortie = date_sortie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_hospitalisation(int id_hospitalisation) {
        this.id_hospitalisation = id_hospitalisation;
    }

    public void setS(Services S) {
        this.S = S;
    }

    @Override
    public String toString() {
        return "Hospitalisation{" + "date_entree=" + date_entree + ", date_sortie=" + date_sortie + ", id=" + id + ", id_hospitalisation=" + id_hospitalisation + ", S=" + S + '}';
    }

    
   
}
