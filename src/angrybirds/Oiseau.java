package angrybirds;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

/**
 * l'entite oiseau
 *
 * @author Thibaut
 */
public class Oiseau extends Entite {

	public boolean lance = false;
	public double directionY = 0.0;
	public int[] px = new int[3];
	public int[] py = new int[3];
	public int[] px2 = new int[3];
	public int[] py2 = new int[3];

	public boolean roule = false;

	Random r = new Random();

	public Coordonne coInit;

	/**
	 * constructeur de l'oiseau avec une taille
	 *
	 * @param taille
	 */
	public Oiseau(int taille) {
		this.taille = taille;
		super.co = new Coordonne(120, 400);
		coInit = new Coordonne(co.x, co.y);
		super.couleurPrincipale = Color.RED;
		super.couleurSecondaire = new Color(250, 224, 173);
		lance = false;
		directionY = 0.0;
		px = new int[3];
		py = new int[3];
		px2 = new int[3];
		py2 = new int[3];

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

	public Oiseau(int taille, Color coPrin) {
		this(taille);
		super.couleurPrincipale = coPrin;
	}

	/**
	 * Modifie les coordones de l'oiseau et de son bec
	 *
	 * @param c
	 *            Nouvelles coordonnes de l'oiseau
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
}
