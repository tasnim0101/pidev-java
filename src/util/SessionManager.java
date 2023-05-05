/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import User.entities.User;
import java.util.HashSet;
import java.util.Set;
import java.util.prefs.Preferences;

/**
 *
 * @author user
 */

public final class SessionManager {

    private static SessionManager instance;

    private static int id;
    private static String email;
    private static String roles;
    private static String nom_user;
    private static String password;
    private User user;
    
    
   
    
   

    //SessionManager.getInstace(rs.getInt("id"),rs.getInt("cin"),rs.getString("user_name"),rs.getInt("numero"),rs.getString("email"),rs.getString("adresse"),rs.getString("roles"));
    private SessionManager(int id, String email, String nom_user, String roles) {
        SessionManager.id = id;
        SessionManager.email = email;
        SessionManager.nom_user = nom_user;
        SessionManager.roles = roles;
    }

    public static SessionManager getInstace(int id, String email, String nom_user, String roles) {
        if (instance == null) {
            instance = new SessionManager(id, email, nom_user, roles);
        }
        return instance;
    }

    public static SessionManager getInstance() {
        return instance;
    }

    public static void setInstance(SessionManager instance) {
        SessionManager.instance = instance;
    }

    public static int getId() {
        return id;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        SessionManager.email = email;
    }

    public static String getRoles() {
        return roles;
    }

    public static void setRoles(String roles) {
        SessionManager.roles = roles;
    }

    public static String getNom_user() {
        return nom_user;
    }

    public static void setNom_user(String nom_user) {
        SessionManager.nom_user = nom_user;
    }

    public static void setId(int id) {
        SessionManager.id = id;
    }

    public static void cleanUserSession() {
        id = 0;
        email = "";
        nom_user = "";
        roles = "";
        password = "";

    }

    @Override
    public String toString() {
        return "UserSession{"
                + "nom_user='" + nom_user + '\''
                + "email='" + email + '\''
                + "id = '" + id + '\''
                + ", privileges=" + roles
                + '}';
    }

    static class cleanUserSession {

        public cleanUserSession() {
            id = 0;
            email = "";
            nom_user = "";
            roles = "";
        }
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        SessionManager.password = password;
    }

    public User getUser() {
        User user = null;
        return user;
    }

    // Set the currently logged in user
    public void setUser(User user) {
        this.user = user;
    }

    public static SessionManager getSession() {
        SessionManager session = null;
        User user = getInstance().getUser();
        if (user != null) {
            session = new SessionManager(user.getId(), user.getEmail(), user.getUsername(), user.getRoles());
        }
        return session;
    }
}