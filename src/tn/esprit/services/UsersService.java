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
import tn.esprit.entities.Users;
import tn.esprit.tools.MaConnexion;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Fayechi
 */
public class UsersService implements NewInterface<Users>{
    Connection cnx;
    String sql="";

    public UsersService() {
        cnx=MaConnexion.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(Users t) throws SQLException{
//        try {
//            sql="insert into personne(nom,prenom,age) values ('"+t.getNom()+
//                    "','"+t.getPrenom()+"','"+t.getAge()+"')";
//            Statement ste = cnx.createStatement();
//            ste.executeUpdate(sql);
//            System.out.println("Users ajoutée ");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
String pw_hash = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt());
    sql="insert into users(email,password,nom_user,prenom_user,is_blocked) values (?,?,?,?,?)";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setString(1, t.getEmail());
     //   ste.setString(2, "ROLE_USER");
   //     ste.setString(2, t.getPassword());
        ste.setString(2, pw_hash);
        ste.setString(3, t.getNom());
        ste.setString(4, t.getPrenom());
        ste.setInt(5,0);//isBlocked
        
        // ste.setInt(3, t.getAge());
        ste.executeUpdate();
        System.out.println("utilisateur Ajoutée !!");
        
    }

    @Override
    public List<Users> afficher() {
        List<Users> users = new ArrayList<>();
        try {
            sql="select * from users";
            Statement ste = cnx.createStatement();
            ResultSet rs= ste.executeQuery(sql);
            while(rs.next()){
             /*  Users p = new Users(rs.getInt(1),
                       rs.getInt(4), rs.getString(2),
                       rs.getString("prenom")); */
             Users p= new Users(rs.getInt("id"),rs.getString("email"),rs.getString("roles"),rs.getString("password"),
                     rs.getString("nom_user"),rs.getString("prenom_user"),rs.getBoolean("is_blocked"));
               users.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }

    @Override
    public void supprimer(Users t) {
        sql="delete from users where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId());
            ste.executeUpdate();
            System.out.println("utilisateur supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void editUser(Users user) throws SQLException{
        String req = "UPDATE users SET "
                  + "email = ?,"
                  + "roles = ?,"
                  + "nom_user = ?,"
                  + "prenom_user = ?,"
                  + "is_blocked = ? "
                  + " where id=?";
        
        System.out.println(req);
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.setString(1, user.getEmail());
        pre.setString(2, user.getRoles());
        pre.setString(3, user.getNom());
        pre.setString(4, user.getPrenom());
        pre.setBoolean(5, user.getIsBlocked());
        pre.setInt(6, user.getId() );
        pre.executeUpdate();
        
    }
    
    public void BlockUser(String email) throws SQLException{
        String req = "UPDATE users SET "
                  + "is_blocked = ?"        
                    + " where email=?";
        
        System.out.println(req);
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.setInt(1, 1);
        pre.setString(2, email);
        pre.executeUpdate();
        
    }
    
}
