import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Oiseau extends Entite {

	int[] px = new int[3];
	int[] py = new int[3];    
        
        /**
         * constructeur de loiseau avec une taille
         * 
         * @param taille 
         */
	public Oiseau(int taille) {
		this.taille = taille;
		super.co = new Coordonne(120, 400);
		super.couleurPrincipale = Color.RED;
		super.couleurSecondaire = Color.ORANGE.brighter();

		Point[] p = new Point[3];
		// Point du bas
		p[0] = new Point(co.x + taille - 2, co.y + taille / 2 + taille / 4);
		// Point du haut
		p[1] = new Point(co.x + taille - 2, co.y + taille / 4);
		// Point de droite
		p[2] = new Point(co.x + taille + taille / 4, co.y + taille / 2);
		for (int i = 0; i < 3; i++) {
			px[i] = p[i].x;
			py[i] = p[i].y;
		}
	}

        /**
         * fait bouger l'oiseau 
         * 
         * @param x
         * @param y 
         */
	public void bouger(int x, int y){
		super.bouger(x, y);
		for(int i = 0 ; i < px.length ; i++){
			px[i] += x;
			py[i] += y;
		}
	}
	
}
