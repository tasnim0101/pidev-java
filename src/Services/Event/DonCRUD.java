/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Event;

import Event.entities.Don;
import Event.entities.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.dbconnection;

/**
 *
 * @author tasnim
 */
public class DonCRUD {
    
    
        Connection cnx;
         String query;
       public DonCRUD() {
         cnx = dbconnection.getInstance().getConnection();
    }
       
       
   public ObservableList<Don> consulterDon() {
    List<Don> listDon = new ArrayList<>();

    try {
        Statement st = cnx.createStatement();
        ResultSet res = st.executeQuery("SELECT don.id, event.nom, don.nom, don.montant FROM don INNER JOIN event ON don.events_id = event.id");

        while (res.next()) {
            Don c = new Don();

            c.setId(res.getInt(1));
            c.setEvents(new Event(res.getString(2)));
            c.setNom(res.getString(3));
            c.setMontant(res.getFloat(4));

            listDon.add(c);
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }

    ObservableList<Don> observableList = FXCollections.observableList(listDon);
    return observableList;
}



            
public static Don FindDon(int id) {
    Don e = new Don();
    try {
        String requete = "SELECT don.id, don.nom, don.montant, event.nom FROM don INNER JOIN event ON don.events_id = event.id WHERE don.id=?";
        PreparedStatement pst = dbconnection.getInstance().getConnection().prepareStatement(requete);
        pst.setInt(1, id);
        ResultSet result = pst.executeQuery();
        if (result.next()) {
            e.setId(result.getInt(1));
            e.setNom(result.getString(2));
            e.setMontant(result.getFloat(3));
            Event event = new Event();
            event.setNom(result.getString(4));
            e.setEvents(event);
        }
    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
    return e;
}

               
               
               

                 
                 
                 
                 
                 
                 
            public static void deleteDon(int id) {
    try {
        String requete = "DELETE FROM don WHERE id=?";
        PreparedStatement pstDon = dbconnection.getInstance().getConnection().prepareStatement(requete);
        pstDon.setInt(1, id);
        pstDon.executeUpdate();
 
        
        System.out.println("don supprimé!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}      
                 
 
         
public static void modifierDon(Don t) {
    try {
        String requete = "UPDATE don SET events_id=(SELECT id FROM event WHERE nom=?), nom=?, montant=? WHERE id=?";
        PreparedStatement pst = dbconnection.getInstance().getConnection().prepareStatement(requete);

        pst.setString(1, t.getEvents().getNom());
        pst.setString(2, t.getNom());
        pst.setFloat(3, t.getMontant());
        pst.setInt(4, t.getId());
        pst.executeUpdate();
        System.out.println("Don modifié!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}











}
