/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import angrybirds.Coordonne;
import angrybirds.Jeu;
import controller.ControllerOiseau;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import model.ModeleOiseau;

/**
 *
 * @author youdelice
 */
public class VueOiseau extends Vue {

    int[] px = new int[3];
    int[] py = new int[3];
    int[] px2 = new int[3];
    int[] py2 = new int[3];
    Coordonne coInit = null;
    int taille;
    Coordonne co = null;
    Color couleurPrincipale = null;
    Color couleurSecondaire = null;

    public VueOiseau(ModeleOiseau m, ControllerOiseau c) {
        this.model = m;
        this.controller = c;


        coInit = model.getCoInit();
        taille = model.getTaille();
        co = model.getCo();
        couleurPrincipale = model.getCouleurPrincipale();
        couleurSecondaire = model.getCouleurSecondaire();


        
    }

    public void paintComponent(Jeu j, Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval(co.x - 3, co.y - 3, taille + 6, taille + 6);

        // Corps
        g.setColor(couleurPrincipale);
        g.fillOval(co.x, co.y, taille, taille);

        // Corps inferieur
        g.setColor(couleurSecondaire);
        g.fillOval(co.x + 5, co.y + taille / 2, taille - 10, taille / 2);

        // Corps inf�rieur
        g.setColor(couleurSecondaire);
        g.fillOval(co.x + 5, co.y + taille / 2, taille - 10, taille / 2);

        // Yeux
        g.setColor(Color.WHITE);
        g.fillOval(co.x + taille / 2, co.y + taille / 4, taille / 5, taille / 5);
        g.fillOval(co.x + taille / 2 + taille / 5, co.y + taille / 4, taille / 5, taille / 5);

        // Pupilles
        g.setColor(Color.BLACK);
        g.fillOval(co.x + taille / 2 + taille / 15, co.y + taille / 4 + taille / 15, taille / 10, taille / 10);
        g.fillOval(co.x + taille / 2 + taille / 5 + taille / 25, co.y + taille / 4 + taille / 15, taille / 10,
                taille / 10);

        // Bec
        Point[] p = new Point[3];
        // Point du bas � gauche
        p[0] = new Point(co.x + taille / 2 - taille / 8 + taille / 5, co.y + taille / 2 + taille / 8);
        // Point du haut
        p[1] = new Point(co.x + taille / 2 + taille / 5, co.y + taille / 4 + taille / 6);
        // Point de droite
        p[2] = new Point(co.x + taille / 2 + taille / 5 + taille / 3, co.y + taille / 2 + taille / 7);
        for (int i = 0; i < 3; i++) {
            px[i] = p[i].x;
            py[i] = p[i].y;
        }

        Point[] p2 = new Point[3];
        // Point du haut � gauche
        p2[0] = new Point(co.x + taille / 2 - taille / 8 + taille / 5, co.y + taille / 2 + taille / 8);
        // Point du bas
        p2[1] = new Point(co.x + taille / 2 + taille / 5, co.y + taille / 2 + taille / 8 + taille / 5);
        // Point de droite
        p2[2] = new Point(co.x + taille / 2 + taille / 5 + taille / 5, co.y + taille / 2 + taille / 7);
        for (int i = 0; i < 3; i++) {
            px2[i] = p2[i].x;
            py2[i] = p2[i].y;
        }
        
        g.setColor(new Color(255, 204, 0));
        g.fillPolygon(px, py, 3);
        g.setColor(new Color(255, 172, 0));
        g.fillPolygon(px2, py2, 3);
        g.setColor(Color.BLACK);
        g.drawPolygon(px, py, 3);
        g.drawLine(px2[0], py2[0], px2[1], py2[1]);
        g.drawLine(px2[1], py2[1], px2[2], py2[2]);
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
