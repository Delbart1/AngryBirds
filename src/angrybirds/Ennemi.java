package angrybirds;

import angrybirds.Coordonne;
import java.awt.Color;
import java.util.Random;


/**
 * l'entite ennemie
 * 
 * @author youdelice
 */
public class Ennemi extends Entite {

    /**
     * 1er construteur de l'ennemi 
     * 
     * @param taille taille de l'ennemi
     */
	public Ennemi(int taille) {
		Random r = new Random();
		super.co = new Coordonne(r.nextInt(80) * 5 + 350, r.nextInt(500));
		// position y pour le sol 525 - taille
		this.taille = taille;
		super.couleurPrincipale = new Color(97, 223, 69);
		super.couleurSecondaire = new Color(190, 245, 116);
	}

        /**
         * 2eme construteur de l'ennemi demandant ces coor
         * 
         * @param co    coor de l'ennemi 
         * @param taille taille de l'ennemi
         */
	public Ennemi(Coordonne co, int taille) {
		this.co = co;
		this.taille = taille;
		couleurPrincipale = Color.GREEN;
		couleurSecondaire = Color.PINK.brighter();
	}
}
