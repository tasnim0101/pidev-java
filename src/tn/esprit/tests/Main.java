/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tests;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Mac;
import tn.esprit.entities.Users;
import tn.esprit.services.UsersService;
import tn.esprit.tools.MaConnexion;

/**
 *
 * @author Fayechi
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UsersService ps = new UsersService();
        Users p = new Users(41,"amir45@esprit.tn","ROLE_USER","amirlaroussi","amir","laroussi",false);
        Users p1 = new Users(43,"amir4554@esprit.tn","ROLE_ADMIN","amirlaroussi","firs","laroussi",false);
        
   //     try {
     //       ps.ajouter(p);
     //   } catch (SQLException ex) {
    //        System.out.println(ex.getMessage());
    //    } 
        
      //  try {
      //      ps.editUser(p1);
      //  } catch (SQLException ex) {
      //      System.out.println(ex.getMessage());
      //  } 
      
      try {
            ps.BlockUser("amir4554@esprit.tn");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
        
        
   // ps.supprimer(p);
    //    System.out.println(ps.afficher());

    }

}
