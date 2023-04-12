/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import com.sun.scenario.effect.Merge;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import tn.esprit.tools.MaConnexion;

/**
 *
 * @author Fayechi
 */
public class PersonneService implements NewInterface<Personne>{
    Connection cnx;
    String sql="";

    public PersonneService() {
        cnx=MaConnexion.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(Personne t) throws SQLException{
//        try {
//            sql="insert into personne(nom,prenom,age) values ('"+t.getNom()+
//                    "','"+t.getPrenom()+"','"+t.getAge()+"')";
//            Statement ste = cnx.createStatement();
//            ste.executeUpdate(sql);
//            System.out.println("Personne ajoutée ");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }

    sql="insert into personne(nom,prenom,age) values (?,?,?)";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setString(1, t.getNom());
        ste.setString(2, t.getPrenom());
        ste.setInt(3, t.getAge());
        ste.executeUpdate();
        System.out.println("Personne Ajoutée !!");
        
    }

    @Override
    public List<Personne> afficher() {
        List<Personne> personnes = new ArrayList<>();
        try {
            sql="select * from personne";
            Statement ste = cnx.createStatement();
            ResultSet rs= ste.executeQuery(sql);
            while(rs.next()){
               Personne p = new Personne(rs.getInt(1),
                       rs.getInt(4), rs.getString(2),
                       rs.getString("prenom"));
               personnes.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return personnes;
    }

    
    @Override
    public void supprimer(Personne t) {
        sql="delete from personne where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId());
            ste.executeUpdate();
            System.out.println("Personne supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
