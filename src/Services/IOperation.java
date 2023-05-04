/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.SQLException;
import java.util.List;
import Entity.Operations;

/**
 *
 * @author chahr
 */
public interface IOperation {
       public void ajouter(Operations o);
    public List<Operations> afficher();
    public void supprimer(Operations o);
     public void modifier(Operations o);


}