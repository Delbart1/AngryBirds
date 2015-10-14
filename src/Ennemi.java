import java.awt.Color;
import java.util.Random;


public class Ennemi extends Entite{
	
	public Ennemi(int taille){
		Random r = new Random();
		super.co = new Coordonne(r.nextInt(80)*5+350, 525 - taille);
		this.taille = taille;
		super.couleurPrincipale = Color.GREEN;
		super.couleurSecondaire = Color.WHITE;
	}
	
	public Ennemi(Coordonne co, int taille){
		this.co = co;
		this.taille = taille;
		couleurPrincipale = Color.GREEN;
		couleurSecondaire = Color.PINK.brighter();
	}
	
}
