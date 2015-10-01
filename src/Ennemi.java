import java.awt.Color;
import java.util.Random;


public class Ennemi extends Entite{
	
	public Ennemi(int taille){
		Random r = new Random();
		this.co = new Coordonne(r.nextInt()*100+350, r.nextInt()*100+50);
		this.taille = taille;
		couleurPrincipale = Color.GREEN;
		couleurSecondaire = Color.PINK.brighter();
	}
	
	public Ennemi(Coordonne co, int taille){
		this.co = co;
		this.taille = taille;
		couleurPrincipale = Color.GREEN;
		couleurSecondaire = Color.PINK.brighter();
	}
	
}
