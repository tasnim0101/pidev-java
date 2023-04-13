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
public class Users {
    private int id;
    private String nom_user,prenom_user,email,password,roles;
    Boolean is_blocked ;
    Reclamation r;

    public Users(int id,String email,String roles,String password,String nom_user, String prenom_user,Boolean is_blocked) {
        this.id = id;
         this.email = email;
         this.roles = roles; 
        this.password =password;   
        this.nom_user = nom_user;
        this.prenom_user = prenom_user;
        this.is_blocked = is_blocked;
    }
    public Users(String email,String password,String nom_user, String prenom_user,Boolean is_blocked) {
     //   this.is_blocked = false;
        
        this.email = email;
        this.password = password;
        this.nom_user = nom_user;
        this.prenom_user = prenom_user;
        this.is_blocked = is_blocked;
        
      
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom_user;
    }

    public void setNom(String nom_user) {
        this.nom_user = nom_user;
    }

    public String getPrenom() {
        return prenom_user;
    }

    public void setPrenom(String prenom_user) {
        this.prenom_user = prenom_user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Boolean getIsBlocked() {
        return is_blocked;
    }

    public void setIsBlocked(Boolean is_blocked) {
        this.is_blocked = is_blocked;
    }

    @Override
    public String toString() {
        return "Users{" + "id=" + id + ", email=" + email  + ", roles=" + roles +  ", nom_user=" + nom_user + ", prenom_user=" + prenom_user +  ", password=" + password + ", isBlocked=" + is_blocked + '}';
    }

    
  

   
    
}
