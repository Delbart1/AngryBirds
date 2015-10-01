import java.awt.Color;


public class Oiseau extends Entite{
	
	public Oiseau(int taille){
		this.taille = taille;
		co = new Coordonne(10, 300);
		couleurPrincipale = Color.RED;
		couleurSecondaire = Color.ORANGE.brighter();
	}
	
	
}
