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
public abstract class Model extends Observable {

    Coordonne co;
    Coordonne coInit;
    Color couleurPrincipale = Color.RED;
    Color couleurSecondaire;
    int taille = 10;

    public Coordonne getCo() {
        return co;
    }

    public Color getCouleurPrincipale() {
        return couleurPrincipale;
    }

    public Color getCouleurSecondaire() {
        return couleurSecondaire;
    }

    public int getTaille() {
        return taille;
    }

    public Coordonne getCoInit() {
        return coInit;
    }

    public void setCo(Coordonne co) {
        this.co = co;
        notifyObservers();
    }

    
    
}
