import java.awt.Color;
import java.util.Random;


public class Ennemi extends Entite{
	
	public Ennemi(int taille){
		Random r = new Random();
		super.co = new Coordonne(r.nextInt(80)*5+350, r.nextInt(500));
		this.taille = taille;
		super.couleurPrincipale = new Color(97, 223, 69);
		super.couleurSecondaire = new Color(190, 245, 116);
	}
	
	public Ennemi(Coordonne co, int taille){
		this.co = co;
		this.taille = taille;
		couleurPrincipale = Color.GREEN;
		couleurSecondaire = Color.PINK.brighter();
	}
	
}
