/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Vue.VueJeu;
import model.ModeleJeu;

/**
 *
 * @author youdelice
 */
public class ControlJeu {
    ModeleJeu modeleJeu = new ModeleJeu();
    VueJeu vuejeu = new Vue(modeleJeu);
}
