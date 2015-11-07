package angrybirds;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

/**
 * l'entite oiseau
 *
 * @author youdelice
 */
public class Oiseau extends Entite {

	boolean lance = false;
	double directionY = 0.0;
	int[] px = new int[3];
	int[] py = new int[3];
	int[] px2 = new int[3];
	int[] py2 = new int[3];

	Random r = new Random();

	Coordonne coInit;

	/**
	 * constructeur de loiseau avec une taille
	 *
	 * @param taille
	 */
	public Oiseau(int taille) {
		this.taille = taille;
		super.co = new Coordonne(120, 400);
		coInit = new Coordonne(co.x, co.y);
		super.couleurPrincipale = Color.RED;
		super.couleurSecondaire = new Color(250, 224, 173);

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
	}

	/**
	 * fait bouger l'oiseau
	 *
	 * @param x
	 * @param y
	 */
	public void setCoord(Coordonne c) {
		this.co = c;

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
	}

	/**
	 * IHM de de l'oiseau
	 *
	 * @param j
	 * @param g
	 *            graphic du jeu
	 */
	public void paintComponent(Jeu j, Graphics g) {

		super.paintComponent(g);

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
		g.setColor(new Color(255, 204, 0));
		g.fillPolygon(px, py, 3);
		g.setColor(new Color(255, 172, 0));
		g.fillPolygon(px2, py2, 3);
		g.setColor(Color.BLACK);
		g.drawPolygon(px, py, 3);
		g.drawLine(px2[0], py2[0], px2[1], py2[1]);
		g.drawLine(px2[1], py2[1], px2[2], py2[2]);
	}
}
