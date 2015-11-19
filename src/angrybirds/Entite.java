package angrybirds;

import java.awt.Color;

/**
 * 
 * Classe définissant une entité (objet ou personnage)
 * 
 * @author Thibaut
 *
 */

public abstract class Entite {

	Coordonne co;
	Color couleurPrincipale = Color.RED;
	Color couleurSecondaire;
	int taille = 10;

	public void bouger(int x, int y) {
		this.co.x += x;
		this.co.y += y;

	}

}
