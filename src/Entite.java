import java.awt.Color;
import java.awt.Graphics;

public abstract class Entite {

	Coordonne co;
	Color couleurPrincipale = Color.RED;
	Color couleurSecondaire;
	int taille = 10;
	
	public void bouger(int x, int y){
		this.co.x += x;
		this.co.y += y;
	}
	
}
