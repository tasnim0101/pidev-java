/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.User;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import User.entities.User;
import util.dbconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;


/**
 *
 * @author Souid
 */
public class ServiceUser {
     private static ServiceUser instance;
     Connection cn = dbconnection.getInstance().getConnection();
    dbconnection cnx = dbconnection.getInstance();
    
     public static final String ACCOUNT_SID = "ACbbd80e3375a31b235a5edf2917b46a48";
    public static final String AUTH_TOKEN = "1f0a526af9396537e8881acd1eebd297";
    
    public ServiceUser(){
        
    }
    
   public static ServiceUser getInstance(){
        if(instance == null)
            instance = new ServiceUser();
        return instance;
    }
    public ObservableList<User> getUserList(){
         ObservableList<User> UserList= FXCollections.observableArrayList();
         
         String query = "SELECT * from users";
         Statement st ;
         ResultSet rs ;
         try {
             st = cn.createStatement();
             rs=st.executeQuery(query);
             User users ;
             while(rs.next())
             {
                 users=new User( rs.getInt("id"), rs.getString("email"), rs.getString("roles"), rs.getString("nom_user"));
                 System.out.println(rs.getString("id"));
                 UserList.add(users);
             }
         }catch(Exception ex )
         {
             ex.printStackTrace();
         }
         return UserList ;
     }
    public void addUser(User user) throws SQLException
    {
        
         
         
        String pw_hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
         String query = "INSERT INTO users (email,roles,password,nom_user,is_blocked) VALUES (?,?,?,?,?) ";
         PreparedStatement st = cn.prepareStatement(query);
            st.setString(1, user.getEmail());
            st.setString(2, "ROLE_USER");
            st.setString(3, pw_hash);
            st.setString(4, user.getUsername());;
            st.setInt(5, 0);
            
            st.executeUpdate();
            System.out.println("User ajoutée");
        
    }
    public void editUser(User user) throws SQLException{
        String req = "UPDATE users SET "
                  + "email = ?,"
                    + "nom_user = ?,"  
                    + " where id=?";
        
        System.out.println(req);
        PreparedStatement pre = cn.prepareStatement(req);
        pre.setString(1, user.getEmail());
        pre.setString(2, user.getUsername().toLowerCase());
 
        pre.setInt(7, integration.Integration.user.getId());
        pre.executeUpdate();
        
    }
    
    public void editUserProfile(User user) throws SQLException{
       String req = "UPDATE users SET "
          + "email = ?,"
          + "nom_user = ?"  
          + "WHERE id=?";
        System.out.println(req);
        PreparedStatement pre = cn.prepareStatement(req);
        pre.setString(1, user.getEmail());
        pre.setString(2, user.getUsername().toLowerCase());
                  pre.setInt(3, integration.Integration.user.getId());

   pre.executeUpdate();
        
    }
    
 /*  public void sms(String msg) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(
                        new PhoneNumber("+21621293360"),
                        new PhoneNumber("+15746269792"),
                        msg
                )
                .create();

        System.out.println(message.getSid());
    } */
    public void BlockUser(String email) throws SQLException{
        String req = "UPDATE users SET "
                  + "is_blocked = ?"        
                    + " where email=?";
        
        System.out.println(req);
        PreparedStatement pre = cn.prepareStatement(req);
        pre.setInt(1, 1);
        pre.setString(2, email);
        pre.executeUpdate();
        
             
              
       JOptionPane.showMessageDialog(null, "This account has been temporarily blocked !");  
            
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                    Message message = Message.creator(new PhoneNumber("+21621293360"),
        new PhoneNumber("+15746269792"), 
        "This account has been now blocked " + email ).create();   
        
    }
    
     public void DeblockUser(String email) throws SQLException{
        String req = "UPDATE users SET "
                  + "is_blocked = ?"        
                    + " where email=?";
        
        System.out.println(req);
        PreparedStatement pre = cn.prepareStatement(req);
        pre.setInt(1, 0);
        pre.setString(2, email);
        pre.executeUpdate();      
              
        JOptionPane.showMessageDialog(null, "This account is currently active and not blocked !");  
            
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                    Message message = Message.creator(new PhoneNumber("+21621293360"),
        new PhoneNumber("+15746269792"), 
        "Your account is currently active and not blocked").create();
        
    }
      public User searchUserByEmail(String pseudo, String password) throws SQLException {
        User user = null;
      String req="SELECT (password) FROM users where (nom_user=? OR email=?)";
      PreparedStatement st1 = cn.prepareStatement(req);
        st1.setString(1, pseudo.toLowerCase());
        st1.setString(2, pseudo.toLowerCase());
        ResultSet rs1 = st1.executeQuery();
        while (rs1.next()){
            if(BCrypt.checkpw(password,"$2a$"+rs1.getString("password").substring(4, rs1.getString("password").length()))){
                String requete = "SELECT * FROM users where (nom_user=? OR email=?)";
                PreparedStatement st = cn.prepareStatement(requete);
                st.setString(1, pseudo.toLowerCase());
                st.setString(2, pseudo.toLowerCase());


                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setRoles(rs.getString("roles"));
                    user.setPassword(rs.getString("password"));  
                    user.setBlocked(rs.getBoolean("is_blocked")); 
                    user.setNom_user(rs.getString("nom_user"));
                    System.out.println("User found");

                }
            }else{
                System.out.println("User not found");
            }
        }
        
        return user;
    }
       public void UpdateUser(String email) throws SQLException{
        String req = "UPDATE users SET "
                  + "is_blocked = ?"        
                    + " where email=?";
        
        System.out.println(req);
        PreparedStatement pre = cn.prepareStatement(req);
        pre.setInt(1, 1);
        pre.setString(2, email);
        pre.executeUpdate();
        
    }
       
       public boolean isEmailRegistered(String email) throws SQLException {
        boolean result = false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "SELECT COUNT(*) FROM users WHERE email = ?";
            ps = cn.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            if (count > 0) {
                result = true;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return result;
    }
      
      
 

    
}
