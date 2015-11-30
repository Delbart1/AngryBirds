/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import controller.Controller;
import java.util.Observer;
import javax.swing.JPanel;
import model.Model;

/**
 *
 * @author youdelice
 */
public abstract class Vue extends JPanel implements Observer{
    public Controller controller;
    public Model model;

    public Controller getController() {
        return controller;
    }

    public Model getModel() {
        return model;
    }
    
   
}
