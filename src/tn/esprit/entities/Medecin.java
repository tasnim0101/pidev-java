/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

/**
 *
 * @author chahr
 */
public class Medecin {
        public int id;
    private String nom_med;
    private String prenom_med;
    private int tel_med;
    private String mail_med;
    private String specialite;
    private String diplome;
    private String photo;

    public Medecin() {
    }

    public Medecin(String nom_med, String prenom_med, int tel_med, String mail_med, String specialite, String diplome, String photo) {
        this.nom_med = nom_med;
        this.prenom_med = prenom_med;
        this.tel_med = tel_med;
        this.mail_med = mail_med;
        this.specialite = specialite;
        this.diplome = diplome;
        this.photo = photo;
    }

    public Medecin(int id, String nom_med, String prenom_med, int tel_med, String mail_med, String specialite, String diplome, String photo) {
        this.id = id;
        this.nom_med = nom_med;
        this.prenom_med = prenom_med;
        this.tel_med = tel_med;
        this.mail_med = mail_med;
        this.specialite = specialite;
        this.diplome = diplome;
        this.photo = photo;
    }

    public Medecin(int id, int tel_med, String nom_med, String prenom_med) {
    }

    public Medecin(int id) {
      }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_med() {
        return nom_med;
    }

    public void setNom_med(String nom_med) {
        this.nom_med = nom_med;
    }

    public String getPrenom_med() {
        return prenom_med;
    }

    public void setPrenom_med(String prenom_med) {
        this.prenom_med = prenom_med;
    }

    public int getTel_med() {
        return tel_med;
    }

    public void setTel_med(int tel_med) {
        this.tel_med = tel_med;
    }

    public String getMail_med() {
        return mail_med;
    }

    public void setMail_med(String mail_med) {
        this.mail_med = mail_med;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Medecin{" + "id=" + id + ", nom_med=" + nom_med + ", prenom_med=" + prenom_med + ", tel_med=" + tel_med + ", mail_med=" + mail_med + ", specialite=" + specialite + ", diplome=" + diplome + ", photo=" + photo + '}';
    }

}
