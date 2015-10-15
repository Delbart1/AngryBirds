import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Oiseau extends Entite {

	int[] px = new int[3];
	int[] py = new int[3];
	int[] px2 = new int[3];
	int[] py2 = new int[3];
	/**
	 * constructeur de loiseau avec une taille
	 * 
	 * @param taille
	 */
	public Oiseau(int taille) {
		this.taille = taille;
		super.co = new Coordonne(120, 400);
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
	public void bouger(int x, int y) {
		super.bouger(x, y);
		for (int i = 0; i < px.length; i++) {
			px[i] += x;
			py[i] += y;
			px2[i] += x;
			py2[i] += y;
		}            
	}

}
