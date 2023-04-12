/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Event;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DataSource;

/**
 *
 * @author aminh
 */
public class EventCRUD implements InterfaceEvents {
    
    Connection cnx;
    String query;
    
      public EventCRUD() {
         cnx = DataSource.getInstance().getCnx();
    }
      
      

      
      
          //Validation date format
    public static boolean isValidFormat(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
        }
        return date!=null;
    }
    
     public ObservableList<Event> consulterEvents() {
        List<Event> listEvents = new ArrayList<>();
        String query="";
        

            query ="select * from event";
        
        if (query !=""){
        try {

            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(query);

            while (res.next()) {
                Event c = new Event();

                c.setId(res.getInt(1));
                c.setNom(res.getString("nom"));
                c.setDate_debut(res.getDate("date_debut"));
                c.setDate_fin(res.getDate("date_fin"));
               

                listEvents.add(c);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        }
        ObservableList<Event> observableList = FXCollections.observableList(listEvents);
        return observableList;
    }

    @Override
    public void AjouterEvent(Event t) {
          try {
            String requete  = "INSERT INTO `event` ( `nom` , `date_debut`, `date_fin`) VALUES ( '"+t.getNom()+"', '"+t.getDate_debut()+"', '"+t.getDate_fin()+"') ";
            Statement st = DataSource.getInstance().getCnx().createStatement() ;
            st.executeUpdate(requete);
            System.out.println("event ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
     
     
     
public static void modifierEvent(Event t) {
    try {
        String requete = "UPDATE event SET nom=?, date_debut=?, date_fin=? WHERE id=?" ;
        PreparedStatement pst= DataSource.getInstance().getCnx().prepareStatement(requete);

        pst.setString(1, t.getNom());
        pst.setDate(2, new java.sql.Date(t.getDate_debut().getTime()));
        pst.setDate(3, new java.sql.Date(t.getDate_fin().getTime()));
        pst.setInt(4, t.getId());
        pst.executeUpdate();
        System.out.println("event modifié!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

     public boolean updateEvent(Event event) {
        boolean success = false;

        try {
            PreparedStatement statement = cnx.prepareStatement("UPDATE event SET nom=?, date_debut=?, date_fin=? WHERE id=?");
            statement.setString(1, event.getNom());
            statement.setDate(2, event.getDate_debut());
            statement.setDate(3, event.getDate_fin());
            statement.setInt(4, event.getId());
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    @Override
    public void ModifyEvent(Event r) {
        
        try {
        String requete = "UPDATE event SET nom=?, date_debut=?, date_fin=? WHERE id=?" ;
        PreparedStatement pst= DataSource.getInstance().getCnx().prepareStatement(requete);

        pst.setString(1, r.getNom());
           pst.setDate(2, r.getDate_debut());
            pst.setDate(3, r.getDate_fin());
        pst.setInt(4, r.getId());
        pst.executeUpdate();
        System.out.println("event modifié!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    } //To change body of generated methods, choose Tools | Templates.
    }


     public static Event FindEvent(int id) {
        Event e = new Event();
        try {
            String requete = "SELECT * FROM event where id=?";
           PreparedStatement pst= DataSource.getInstance().getCnx().prepareStatement(requete);
pst.setInt(1, id);
ResultSet result = pst.executeQuery();
if(result.next()) {
e.setId(result.getInt(1));
e.setNom(result.getString(2));
e.setDate_debut(result.getDate(3));
e.setDate_fin(result.getDate(4));

}
} catch (SQLException ex) {
System.out.println("Error: " + ex.getMessage());
}
return e;
}
     
     
     
     
     
     
     
     public static Event findEventById(int id) {
    Event event = null;
    try {
        String query = "SELECT * FROM event WHERE id=?";
        PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(query);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            event = new Event();
            event.setId(rs.getInt("id"));
            event.setNom(rs.getString("nom"));
            event.setDate_debut(rs.getDate("date_debut"));
            event.setDate_fin(rs.getDate("date_fin"));
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return event;
}

     

     
     
     
     
     
     
     
     
     
     
     
  public static void deleteEvent(int id) {
    try {
        String requete = "DELETE FROM don WHERE events_id=?";
        PreparedStatement pstDon = DataSource.getInstance().getCnx().prepareStatement(requete);
        pstDon.setInt(1, id);
        pstDon.executeUpdate();
        
        requete = "DELETE FROM event WHERE id=?";
        PreparedStatement pstEvent = DataSource.getInstance().getCnx().prepareStatement(requete);
        pstEvent.setInt(1, id);
        pstEvent.executeUpdate();
        
        System.out.println("event supprimé!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}


     
     
     
     
     
     
     
     
}
