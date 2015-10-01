import java.awt.Color;


public class Oiseau extends Entite{
	
	public Oiseau(int taille){
		this.taille = taille;
		super.co = new Coordonne(10, 300);
		super.couleurPrincipale = Color.RED;
		super.couleurSecondaire = Color.ORANGE.brighter();
	}
	
	
}
