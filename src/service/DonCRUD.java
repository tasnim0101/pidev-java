/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Don;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DataSource;

/**
 *
 * @author aminh
 */
public class DonCRUD {
    
    
        Connection cnx;
         String query;
       public DonCRUD() {
         cnx = DataSource.getInstance().getCnx();
    }
       
       
            public ObservableList<Don> consulterDon() {
        List<Don> listDon = new ArrayList<>();
        String query="";
        

            query ="SELECT * FROM don";
        
        if (query !=""){
        try {

            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(query);

            while (res.next()) {
                Don c = new Don();

                c.setId(res.getInt(1));
                 c.setEvents(res.getString("events_id"));
                c.setNom(res.getString("nom"));
                
                c.setMontant(res.getFloat("montant"));
               
               

                listDon.add(c);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        }
        ObservableList<Don> observableList = FXCollections.observableList(listDon);
        return observableList;
    }
            
            
               public static Don FindDon(int id) {
        Don e = new Don();
        try {
            String requete = "SELECT * FROM don where id=?";
           PreparedStatement pst= DataSource.getInstance().getCnx().prepareStatement(requete);
pst.setInt(1, id);
ResultSet result = pst.executeQuery();
if(result.next()) {
e.setId(result.getInt(1));
e.setNom(result.getString(2));
e.setMontant(result.getFloat(3));
e.setEvents(result.getString(4));

}
} catch (SQLException ex) {
System.out.println("Error: " + ex.getMessage());
}
return e;
}
               
               
               
                 public static Don findDonById(int id) {
    Don don = null;
    try {
        String query = "SELECT * FROM don WHERE id=?";
        PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(query);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            don = new Don();
            don.setId(rs.getInt("id"));
            don.setNom(rs.getString("nom"));
            don.setMontant(rs.getFloat("montant"));
            don.setEvents(rs.getString("events_id"));
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return don;
}
                 
                 
                 
                 
                 
                 
            public static void deleteDon(int id) {
    try {
        String requete = "DELETE FROM don WHERE id=?";
        PreparedStatement pstDon = DataSource.getInstance().getCnx().prepareStatement(requete);
        pstDon.setInt(1, id);
        pstDon.executeUpdate();
 
        
        System.out.println("don supprimé!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}      
                 
 
    
}
