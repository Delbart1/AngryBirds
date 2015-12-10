/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import angrybirds.Coordonne;
import controller.ControllerEnnemie;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import model.ModeleEnnemie;

/**
 *
 * @author youdelice
 */
public class VueEnnemie extends Vue {

    Random r = new Random();
    int typeEnnemi = 1;
    int taille;
    Coordonne co;
    Color couleurPrincipale;
    Color couleurSecondaire;

    public VueEnnemie(ModeleEnnemie m, ControllerEnnemie c) {
        this.model = m;
        this.controller = c;
        co = new Coordonne(r.nextInt(80) * 5 + 350, r.nextInt(475));
        // position y pour le sol 525 - taille
        this.taille = taille;
        couleurPrincipale = new Color(97, 223, 69);
        couleurSecondaire = new Color(190, 245, 116);
        typeEnnemi = r.nextInt(2) + 1;
        this.taille = model.getTaille();
    }

    public void paintComponent(Graphics g) {

        if (typeEnnemi == 1) {
            // Cell-shading
            g.setColor(Color.BLACK);
            g.fillOval(co.x - 3, co.y - 3, taille + 6, taille + 6);

            // Corps
            g.setColor(couleurPrincipale);
            g.fillOval(co.x, co.y, taille, taille);

            // Corps inferieur
            g.setColor(couleurSecondaire);
            g.fillOval(co.x + 5, co.y + taille / 2, taille - 10, taille / 2);

            // Yeux
            g.setColor(Color.WHITE);
            g.fillOval(co.x + taille / 15, co.y + taille / 3, taille / 5, taille / 5);
            g.fillOval(co.x + taille / 2 + taille / 4, co.y + taille / 3, taille / 5, taille / 5);

            // Pupilles
            g.setColor(Color.BLACK);
            g.fillOval(co.x + taille / 15, co.y + taille / 3 + taille / 15, taille / 10, taille / 10);
            g.fillOval(co.x + taille / 2 + taille / 3, co.y + taille / 3 + taille / 15, taille / 10, taille / 10);

            // Groin
            g.setColor(couleurSecondaire);
            g.fillOval(co.x + taille / 4, co.y + taille / 3, taille / 2, taille / 3 + taille / 10);
            g.setColor(new Color(66, 159, 107));
            g.drawOval(co.x + taille / 4, co.y + taille / 3, taille / 2, taille / 3 + taille / 10);
            g.setColor(new Color(0, 70, 30));
            g.fillOval(co.x + taille / 4 + taille / 12, co.y + taille / 3 + taille / 10, taille / 6, taille / 4);
            g.fillOval(co.x + taille / 2, co.y + taille / 3 + taille / 7, taille / 7, taille / 5);

            // Bouche
            g.setColor(Color.BLACK);
            g.drawOval(co.x + taille / 3 + taille / 12, co.y + taille / 2 + taille / 4 + taille / 18, taille / 8,
                    taille / 10);
            g.setColor(Color.RED);
            g.fillOval(co.x + taille / 3 + taille / 12, co.y + taille / 2 + taille / 4 + taille / 18, taille / 8,
                    taille / 10);
        } else if (typeEnnemi == 2) {

            // Cell-shading
            g.setColor(Color.black);
            g.fillRoundRect(co.x - 3, co.y - 3, taille + taille / 3 + 6, taille + 6, 20, 20);

            // Corps
            g.setColor(couleurPrincipale);
            g.fillOval(co.x, co.y, taille, taille);
            g.fillRoundRect(co.x, co.y, taille + taille / 3, taille, 20, 20);

            // Corps inferieur
            g.setColor(couleurSecondaire);
            g.fillRoundRect(co.x + 5, co.y + taille / 2, taille + taille / 3 - taille / 4, taille / 2 - taille / 15, 20,
                    20);

            // Yeux
            g.setColor(Color.WHITE);
            g.fillOval(co.x + taille / 10, co.y + taille / 4, taille / 5, taille / 5);
            g.fillOval(co.x + taille, co.y + taille / 4, taille / 5, taille / 5);

            // Pupilles
            g.setColor(Color.BLACK);
            g.fillOval(co.x + taille / 10, co.y + taille / 4 + taille / 15, taille / 10, taille / 10);
            g.fillOval(co.x + taille + taille / 10, co.y + taille / 4 + taille / 15, taille / 10, taille / 10);

            // Groin
            g.setColor(couleurSecondaire);
            g.fillOval(co.x + taille / 3, co.y + taille / 3, taille / 2 + taille / 5, taille / 3 + taille / 10);
            g.setColor(new Color(66, 159, 107));
            g.drawOval(co.x + taille / 3, co.y + taille / 3, taille / 2 + taille / 5, taille / 3 + taille / 10);
            g.setColor(new Color(0, 70, 30));
            g.fillOval(co.x + taille / 3 + taille / 8, co.y + taille / 3 + taille / 10, taille / 5, taille / 4);
            g.fillOval(co.x + taille / 2 + taille / 5, co.y + taille / 3 + taille / 7, taille / 6, taille / 5);

            // Bouche
            g.setColor(Color.BLACK);
            g.drawOval(co.x + taille / 3 + taille / 4, co.y + taille / 2 + taille / 4 + taille / 18, taille / 8,
                    taille / 10);
            g.setColor(Color.RED);
            g.fillOval(co.x + taille / 3 + taille / 4, co.y + taille / 2 + taille / 4 + taille / 18, taille / 8,
                    taille / 10);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
