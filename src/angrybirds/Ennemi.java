package angrybirds;

import java.awt.Color;
import java.util.Random;

/**
 * 
 * Classe créant les ennemis (cochons)
 * 
 * @author Thibaut
 *
 */

public class Ennemi extends Entite {

	Random r = new Random();
	public int typeEnnemi = 2;
	// 1 = cochon
	// 2 = cochon rectangle
	
	public boolean estTouche = false;
	public double t = 0.0;
	public Courbe courbeEnnemi = new Courbe();

	public TrajectoireEnnemi trajectoire;

	/**
	 * 1er construteur de l'ennemi
	 * 
	 * @param taille
	 *            taille de l'ennemi
	 */
	public Ennemi(int taille) {
		super.co = new Coordonne(r.nextInt(80) * 5 + 350, r.nextInt(475));
		trajectoire = new TrajectoireEnnemi(this);
		// position y pour le sol 525 - taille
		this.taille = taille;
		super.couleurPrincipale = new Color(97, 223, 69);
		super.couleurSecondaire = new Color(190, 245, 116);
		
		typeEnnemi = r.nextInt(2) + 1;
	}
	
	public Ennemi(int taille, Color coPrin) {
		this(taille);
		super.couleurPrincipale = coPrin;
	}

	/**
	 * 2eme construteur de l'ennemi demandant ces coordonne
	 * 
	 * @param co
	 *            coor de l'ennemi
	 * @param taille
	 *            taille de l'ennemi
	 */
	public Ennemi(Coordonne co, int taille) {
		this.co = co;
		this.taille = taille;
		super.couleurPrincipale = new Color(97, 223, 69);
		super.couleurSecondaire = new Color(190, 245, 116);
		typeEnnemi = r.nextInt(2) + 1;
	}

	public void setCoord(Coordonne c) {
		this.co = c;
	}
}
