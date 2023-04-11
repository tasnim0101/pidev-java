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
public class Services {
   private int id;
   private String type,description,chef_service;
   private Double prix;

    public Services(int id, String type, String description, String chef_service, Double prix) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.chef_service = chef_service;
        this.prix = prix;
    }

    public Services(String type, String description, String chef_service, Double prix) {
        this.type = type;
        this.description = description;
        this.chef_service = chef_service;
        this.prix = prix;
    }

    public Services() {
       
    }

    public Services(int id) {
        this.id = id;
    }

    public Services(String type) {
       
    }

    public Services(int id, String type) {
    }

  

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getChef_service() {
        return chef_service;
    }

    public Double getPrix() {
        return prix;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setChef_service(String chef_service) {
        this.chef_service = chef_service;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Services{" + "id=" + id + ", type=" + type + ", description=" + description + ", chef_service=" + chef_service + ", prix=" + prix + '}';
    }

   
}
