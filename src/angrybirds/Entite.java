package angrybirds;

import java.awt.Color;

/**
 * 
 * Classe d�finissant une entit� (objet ou personnage)
 * 
 * @author Thibaut
 *
 */

public abstract class Entite {

	public Coordonne co;
	public Color couleurPrincipale = Color.RED;
	public Color couleurSecondaire;
	public int taille = 10;

	public void bouger(int x, int y) {
		this.co.x += x;
		this.co.y += y;

	}

}
