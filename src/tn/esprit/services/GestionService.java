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
import tn.esprit.entities.Services;
import tn.esprit.tools.MaConnexion;

public class GestionService implements IGestionService<Services>{
    Connection cnx;
    String sql="";

    public GestionService() {
        cnx=MaConnexion.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(Services t) throws SQLException {
        sql="insert into services(type, description, chef_service, prix) values (?, ?, ?, ?)";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setString(1, t.getType());
        ste.setString(2, t.getDescription());
        ste.setString(3, t.getChef_service());
        ste.setDouble(4, t.getPrix());
        ste.executeUpdate();
        System.out.println("Service ajouté !");
    }
    
    

    @Override
    public List<Services> afficher() {
        List<Services> services = new ArrayList<>();
        try {
            sql="select * from services";
            Statement ste = cnx.createStatement();
            ResultSet rs= ste.executeQuery(sql);
            while(rs.next()){
               Services ser = new Services();
               ser.setId(rs.getInt("id"));
               ser.setDescription(rs.getString("description"));
               ser.setChef_service(rs.getString("chef_service"));
               ser.setType(rs.getString("type"));
               ser.setPrix(rs.getDouble("prix"));
               services.add(ser);
            }
            System.out.println("Liste des services :");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return services;
    }

    @Override
    public void supprimer(Services t) {
        sql="delete from services where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId());
            ste.executeUpdate();
            System.out.println("Service supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void modifier(Services t){
        try {
            sql="update services set description=? ,  chef_service=? ,  type=? , prix=? where id=?";
            PreparedStatement ste = cnx.prepareStatement(sql);
             ste.setString(1, t.getDescription());
             ste.setString(3, t.getType());
             ste.setString(2, t.getChef_service());
             ste.setDouble(4, t.getPrix());
             ste.setInt(5, t.getId());
             ste.executeUpdate();
            System.out.println("Service modifié !");
        } catch (SQLException ex) {
            Logger.getLogger(GestionService.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
    }
}
