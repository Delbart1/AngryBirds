import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


public class Oiseau extends Entite{
	
	int[] px = new int[3];
	int[] py = new int[3];
	
	public Oiseau(int taille){
		this.taille = taille;
		super.co = new Coordonne(10, 300);
		super.couleurPrincipale = Color.RED;
		super.couleurSecondaire = Color.ORANGE.brighter();
		
		Point[] p = new Point[3];
		p[0] = new Point(co.x+taille*2, co.y+taille/2);
		p[1] = new Point(co.x+taille*2, co.y-taille/2);
		p[2] = new Point(co.x+taille*3, co.y);
		for (int i = 0; i < 3; i++) {
			px[i] = p[i].x;
			py[i] = p[i].y;
		}
	}
	
}
