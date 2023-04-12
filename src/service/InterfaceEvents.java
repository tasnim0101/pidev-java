/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Event;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author user
 */
public interface InterfaceEvents {
    public void AjouterEvent (Event r);
    
        public void ModifyEvent (Event r);

}
