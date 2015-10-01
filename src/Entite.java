import java.awt.Color;
import java.awt.Graphics;

public abstract class Entite {

	Coordonne co;
	Color couleurPrincipale;
	Color couleurSecondaire;
	int taille = 10;
	
    public void dessiner(Graphics g) {
        g.setColor(couleurPrincipale);
        g.fillOval(co.x,co.y, 2*taille, 2*taille);
        g.setColor(couleurSecondaire);
        g.fillOval(co.x,co.y+taille, 2*taille, taille);
    }
	
}
