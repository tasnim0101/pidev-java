/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Materiel;

import Materiel.entities.Materiel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.dbconnection;

/**
 *
 * @author sarah
 */
public class MaterielService implements IServiceMateriel<Materiel> {

    Connection cnx = dbconnection.getInstance().getConnection();

    @Override
    public void ajouter(Materiel t) {
        String qry ="INSERT INTO `materiel`(`libelle`, `type`, `prix`,`fournisseur_id`) VALUES ('"+t.getLibelle()+"','"+t.getType()+"','"+t.getPrix()+"','"+t.getFournisseur_id()+"')";
        try {
            Statement stm = cnx.createStatement();
       
        stm.executeUpdate(qry);
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }
    }
//
   //
    @Override
    public List<Materiel> afficher() throws SQLException {
        List<Materiel> Materiels = new ArrayList<>();
        String s = "select * from materiel";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Materiel e = new Materiel();
            e.setLibelle(rs.getString("libelle"));
            e.setType(rs.getString("type"));
            e.setPrix(rs.getInt("prix"));
            e.setLikes(rs.getInt("likes"));
            e.setDislikes(rs.getInt("dislikes"));
           e.setFournisseur_id(rs.getInt("fournisseur_id"));
            e.setId(rs.getInt("id"));
            Materiels.add(e);
            
        }
        return Materiels;
    }

    @Override
    public void modifier(Materiel t) {
try {
            String requete = "UPDATE materiel SET libelle = ?,type = ?,prix = ? ,fournisseur_id = ? WHERE id = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(5, t.getId());
            pst.setString(1, t.getLibelle());
            pst.setString(2, t.getType());
           
            pst.setInt(3, t.getPrix());
            pst.setInt(4, t.getFournisseur_id());
            
            
            pst.executeUpdate();
            System.out.println("Materiel modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    @Override
    public boolean supprimer(Materiel t) throws SQLException {
    boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("delete from materiel where id = ? ");
            req.setInt(1, t.getId());
            req.executeUpdate();
            ok = true;
        } catch (SQLException ex) {
            System.out.println("error in delete " + ex);
        }
        return ok;  
    }
    
     public List<Materiel> recupererById(int id) throws SQLException {
        String req = "select * from materiel where id = ?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
         List<Materiel> activitees = new ArrayList<>();
          while (rs.next()) {
            Materiel p = new Materiel();
            p.setId(rs.getInt("id"));
            p.setLibelle(rs.getString("libelle"));
            p.setType(rs.getString("type"));
           
            p.setPrix(rs.getInt("prix"));
            p.setLikes(rs.getInt("likes"));
            p.setDislikes(rs.getInt("dislikes"));

            activitees.add(p);
        }
        return activitees;
    }
    
    
}
