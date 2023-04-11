/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.entities.Hospitalisation;
import tn.esprit.entities.Services;
import tn.esprit.tools.MaConnexion;



/**
 *
 * @author hamdi
 */
public class GestionHospitalisation implements IGestionHospital<Hospitalisation>{

     Connection cnx;
    String sql="";

    public GestionHospitalisation() {
        cnx=MaConnexion.getInstance().getCnx();
    }
    

@Override
public void ajouter(Hospitalisation h) throws SQLException {
   sql = "INSERT INTO hospitalisation(date_entree, date_sortie, id_service) " +
      "VALUES (?, ?, (SELECT id FROM services WHERE type = ?))";
   PreparedStatement ste = cnx.prepareStatement(sql);
    ste.setDate(1, new java.sql.Date(h.getDate_entree().getTime()));
    ste.setDate(2, new java.sql.Date(h.getDate_sortie().getTime()));
    ste.setString(3, h.getS().getType());
    ste.executeUpdate();
    System.out.println("Hospitalisation ajoutée !");
}


    

   @Override
public List<Hospitalisation> afficher() {
    List<Hospitalisation> hospitalisation = new ArrayList<>();
    try {
        sql = "SELECT h.id, h.date_entree, h.date_sortie, h.id_hospitalisation, s.id AS service_id, s.type AS type\n" +
"FROM hospitalisation h\n" +
"INNER JOIN services s ON h.service_id = s.id";
        Statement ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery(sql);
        while (rs.next()) {
            Hospitalisation hos = new Hospitalisation();
            hos.setId(rs.getInt("id"));
            hos.setDate_entree(rs.getDate("date_entree"));
            hos.setDate_sortie(rs.getDate("date_sortie"));
            hos.setId_hospitalisation(rs.getInt("id_hospitalisation"));
            Services service = new Services();
            service.setId(rs.getInt("service_id"));
            service.setType(rs.getString("type"));
            hos.setS(service);
            hospitalisation.add(hos);
        }
        System.out.println("Liste des hospitalisations :");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return hospitalisation;
}


    @Override
    public void supprimer(Hospitalisation t) {
         try {
             sql = "DELETE FROM hospitalisation WHERE id = ?";
             PreparedStatement ste = cnx.prepareStatement(sql);
             ste.setInt(1, t.getId());
             ste.executeUpdate(); 
             System.out.println("Hospitalisation supprimé !");
         } catch (SQLException ex) {
             Logger.getLogger(GestionHospitalisation.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public void modifier(Hospitalisation t) {
         try {
             sql = "UPDATE hospitalisation SET date_entree = ?, date_sortie = ?, id_hospitalisation = ?, service_id = ? WHERE id = ?";
             PreparedStatement ste = cnx.prepareStatement(sql);
             ste.setDate(1, t.getDate_entree());
             ste.setDate(2, t.getDate_sortie());
             ste.setInt(3, t.getId_hospitalisation());
             ste.setInt(4, t.getS().getId());
             ste.setInt(5, t.getId());
             ste.executeUpdate();
             System.out.println("Hospitalisation modifié !");
         } catch (SQLException ex) {
             Logger.getLogger(GestionHospitalisation.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    

   
}
