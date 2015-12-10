/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import angrybirds.Coordonne;
import java.awt.Color;
import java.util.Observable;
import java.util.Random;

/**
 *
 * @author youdelice
 */
public class ModeleEnnemie extends Model {

    static Random r = new Random();
    private final int typeEnnemi;

    public ModeleEnnemie(int i) {
       
        this.taille = i;

        co = new Coordonne(r.nextInt(80) * 5 + 350, r.nextInt(475));
        
        // position y pour le sol 525 - taille
        this.taille = taille;
        super.couleurPrincipale = new Color(97, 223, 69);
        super.couleurSecondaire = new Color(190, 245, 116);
        typeEnnemi = r.nextInt(2) + 1;
    }
}
