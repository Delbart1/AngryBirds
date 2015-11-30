/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import angrybirds.Coordonne;
import java.awt.Color;
import java.util.Observable;

/**
 *
 * @author youdelice
 */
public class ModeleOiseau extends Model {

    protected boolean lance = false;
    double directionY = 0.0;    
    public ModeleOiseau() {
        co = new Coordonne(120, 400);
        coInit = new Coordonne(co.x, co.y);

        taille=50;

        couleurPrincipale = Color.RED;
        couleurSecondaire = new Color(250, 224, 173);


    }

    public boolean estLance() {
        return lance;
    }

    public double getDirectionY() {
        return directionY;
    }

    public void setDirectionY(double directionY) {
        this.directionY = directionY;
    }
    
    

    public void setLance(boolean lance) {
        this.lance = lance;
    }
    
    
}
