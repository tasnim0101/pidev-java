/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author chahr
 */
public class Operations {
        private int id;
    private String date;
    private String lieu;
    private String equipe;  
    private String description;
    private String image;
private Medecin M;
    public Operations() {
    }

    public Operations(String date, String lieu, String equipe, String description, String image) {
        this.date = date;
        this.lieu = lieu;
        this.equipe = equipe;
        this.description = description;
        this.image = image;
    }

 
    public Operations(String date, String lieu, String equipe, String description, String image, Medecin M) {
        this.date = date;
        this.lieu = lieu;
        this.equipe = equipe;
        this.description = description;
        this.image = image;
        this.M = M;
    }

    public Operations(int aInt, String string, String string0, String string1, String string2, String string3, String string4) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public Medecin getM() {
        return M;
    }

    public void setM(Medecin M) {
        this.M = M;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Operations{" + "id=" + id + ", date=" + date + ", lieu=" + lieu + ", equipe=" + equipe + ", description=" + description + ", image=" + image +  ", med=" + M.getId() +  '}';
    }

  
  

}
