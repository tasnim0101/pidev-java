/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.User;

import User.entities.User;
import User.entities.Reclamation;
import util.dbconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.mindrot.jbcrypt.BCrypt;
import integration.Integration;
/**
 *
 * @author Asus store
 */
public class ServiceReclamation {
    
    private static ServiceReclamation instance;
     Connection cn = dbconnection.getInstance().getConnection();
    dbconnection cnx = dbconnection.getInstance();
    public ServiceReclamation(){
        
    }
    
   public static ServiceReclamation getInstance(){
        if(instance == null)
            instance = new ServiceReclamation();
        return instance;
    }
   
      private User getUser(int id) throws SQLException {
        String query = "SELECT id FROM users WHERE id = ?";
    PreparedStatement ps = cn.prepareStatement(query);
    ps.setInt(1, id);
    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
        return new User(rs.getInt("id"));
     //   return new User(rs.getInt("id"), rs.getString("email"),  rs.getString("num_telephone"),  rs.getString("roles"),  rs.getInt("score"),  rs.getInt("nb_etoile"),  rs.getString("nom"),  rs.getString("prenom"));
    } else {
        throw new SQLException("User not found with id " + id);
    }
    }
   
    public ObservableList<Reclamation> getReclamationList(){
         ObservableList<Reclamation> ReclamationList= FXCollections.observableArrayList();
       //  User user=new User();
         String query = "SELECT * from reclamation";
         Statement st ;
         ResultSet rs ;
         try {
             st = cn.createStatement();
             rs=st.executeQuery(query);
           //  Reclamation reclamations ;
             while(rs.next())
             {
                 Reclamation r=new Reclamation() ;
                 r.setId(rs.getInt(1));
              //   r.setMessage(rs.getString(2));
                 User user = new User();
                 String query2="SELECT * FROM users where id= " +rs.getString(3);
                 Statement ste1= cn.createStatement();
                 ResultSet rs1=ste1.executeQuery(query2);
              
                 if(rs1.next()){
                     user.setId(rs1.getInt(1));
                     user.setEmail(rs1.getString(3));
                 }
                 r.setUser(user);
                 
                 r.setMessage(rs.getString(2));
                 
                // reclamations=new Reclamation( rs.getInt("id"), rs.getString("message"),getUser(rs.getInt("users_id")) );
               //  System.out.println(rs.getString("id"));
           //    System.out.println(getUser(rs.getInt("id")));
           System.out.println(rs.getString("users_id"));
          //    System.out.println(getUser(rs.getInt("users_id")));
               //  System.out.println(reclamations.toString());
                 ReclamationList.add(r);
             }
         }catch(Exception ex )
         {
             ex.printStackTrace();
         }
         return  ReclamationList ;
     }
    public void addReclamation(Reclamation reclamation) throws SQLException
    {
      
         String query = "INSERT INTO reclamation (message,users_id) VALUES (?,?) ";
         PreparedStatement st = cn.prepareStatement(query);
            st.setString(1, reclamation.getMessage());
            st.setString(2,reclamation.getUser().getEmail());
            st.executeUpdate();
            System.out.println("User ajoutée");
        
    }
    public void editReclamation(Reclamation reclamation) throws SQLException{
        String req = "UPDATE reclamation SET "
                  + "message = ?"
                  + " where id=?";
        
        System.out.println(req);
        PreparedStatement pre = cn.prepareStatement(req);
        pre.setString(1, reclamation.getMessage());
        pre.setInt(2, reclamation.getId());
        pre.executeUpdate();
        
    }
    
    
    
}
