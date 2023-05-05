/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Event;

import Event.entities.Event;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.dbconnection;

/**
 *
 * @author tasnim
 */
public class EventCRUD implements InterfaceEvents {
    
    Connection cnx;
    String query;

    
      public EventCRUD() {
         cnx = dbconnection.getInstance().getConnection();
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
                 c.setLiked(res.getBoolean("likes"));
               

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
            Statement st = dbconnection.getInstance().getConnection().createStatement() ;
            st.executeUpdate(requete);
            System.out.println("event ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
     
     
     
public static void modifierEvent(Event t) {
    try {
        String requete = "UPDATE event SET nom=?, date_debut=?, date_fin=? WHERE id=?" ;
        PreparedStatement pst= dbconnection.getInstance().getConnection().prepareStatement(requete);

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

public static void modifierLikes(Event t) {
    try {
        String requete = "UPDATE event SET likes=? WHERE id=?" ;
        PreparedStatement pst= dbconnection.getInstance().getConnection().prepareStatement(requete);

    
        pst.setInt(1, t.getLikes());
        pst.setInt(2, t.getId());
        pst.executeUpdate();
        System.out.println("event modifié!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}



 

     
  public static void deleteEvent(int id) {
    try {
        String requete = "DELETE FROM don WHERE events_id=?";
        PreparedStatement pstDon = dbconnection.getInstance().getConnection().prepareStatement(requete);
        pstDon.setInt(1, id);
        pstDon.executeUpdate();
        
        requete = "DELETE FROM event WHERE id=?";
        PreparedStatement pstEvent = dbconnection.getInstance().getConnection().prepareStatement(requete);
        pstEvent.setInt(1, id);
        pstEvent.executeUpdate();
        
        System.out.println("event supprimé!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

  
public static Event FindEventByName(String eventName) {
    Event e = new Event();
    try {
        String query = "SELECT * FROM event WHERE nom = ?";
        PreparedStatement pst= dbconnection.getInstance().getConnection().prepareStatement(query);
        pst.setString(1, eventName);
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

  
public static List<Event> getEventsByMonth(int month) {
    List<Event> events = new ArrayList<>();
    try {
        String query = "SELECT * FROM event WHERE MONTH(date_debut) = ?";
        PreparedStatement pst= dbconnection.getInstance().getConnection().prepareStatement(query);
        pst.setInt(1, month);
        ResultSet result = pst.executeQuery();
        while(result.next()) {
            Event e = new Event();
            e.setId(result.getInt(1));
            e.setNom(result.getString(2));
            e.setDate_debut(result.getDate(3));
            e.setDate_fin(result.getDate(4));
            events.add(e);
        }
    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
    return events;
}

 
public Map<LocalDate, List<Event>> consulterEvents1() {
    Map<LocalDate, List<Event>> eventMap = new HashMap<>();
    String query = "select * from event";
        
    try {
        Statement st = cnx.createStatement();
        ResultSet res = st.executeQuery(query);

        while (res.next()) {
            Event c = new Event();
            c.setId(res.getInt(1));
            c.setNom(res.getString("nom"));
            c.setDate_debut(res.getDate("date_debut"));
            c.setDate_fin(res.getDate("date_fin"));
        

            LocalDate date = c.getDate_debut().toLocalDate();
            if (!eventMap.containsKey(date)) {
                eventMap.put(date, new ArrayList<>());
            }
            eventMap.get(date).add(c);
        }

    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }

    return eventMap;
}




public List<Event> getCalendarActivitiesMonth(ZonedDateTime mth) throws SQLException {
        String req = "select * from event where Month(date_debut) = ? and year(date_debut)=?";
         System.out.println(mth.getMonthValue());
        PreparedStatement st = cnx.prepareStatement(req);
        st.setObject(1, mth.getMonthValue());
        st.setObject(2, mth.getYear());
        ResultSet rs = st.executeQuery();
         List<Event> activitees = new ArrayList<>();
          while (rs.next()) {
            Event p = new Event();
            p.setId(rs.getInt("id"));
            p.setNom(rs.getString("nom"));
            p.setDate_debut(rs.getDate("date_debut"));
            p.setDate_fin(rs.getDate("date_fin"));

            activitees.add(p);
        }
        return activitees;
    }

     
}
