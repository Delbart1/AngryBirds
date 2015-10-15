import java.awt.Color;
import java.awt.Graphics;

public abstract class Entite {

	Coordonne co;
	Color couleurPrincipale = Color.RED;
	Color couleurSecondaire;
	int taille = 10;

	public void bouger(int x, int y) {
		this.co.x += x;
		// if(y >= )
		this.co.y += y;
	}

	/**
	 * ihm d'une entité
	 * 
	 * @param e
	 *            l'entité
	 * @param g
	 */
	public void paintComponent(Graphics g) {

		// Cell-shading
		g.setColor(Color.BLACK);
		g.fillOval(co.x - 3, co.y - 3, taille + 6, taille + 6);

		// Corps
		g.setColor(couleurPrincipale);
		g.fillOval(co.x, co.y, taille, taille);

		// Corps inferieur
		g.setColor(couleurSecondaire);
		g.fillOval(co.x + 5, co.y + taille / 2, taille - 10, taille / 2);

		if (this instanceof Ennemi) {

			// Yeux
			g.setColor(Color.WHITE);
			g.fillOval(co.x + taille / 15, co.y + taille / 3, taille / 5, taille / 5);
			g.fillOval(co.x + taille / 2 + taille / 4, co.y + taille / 3, taille / 5, taille / 5);

			// Pupilles
			g.setColor(Color.BLACK);
			g.fillOval(co.x + taille / 15, co.y + taille / 3 + taille / 15, taille / 10, taille / 10);
			g.fillOval(co.x + taille / 2 + taille / 3, co.y + taille / 3 + taille / 15, taille / 10, taille / 10);

			// Groin
			g.setColor(couleurSecondaire);
			g.fillOval(co.x + taille / 4, co.y + taille / 3, taille / 2, taille / 3 + taille / 10);
			g.setColor(new Color(66, 159, 107));
			g.drawOval(co.x + taille / 4, co.y + taille / 3, taille / 2, taille / 3 + taille / 10);
			g.setColor(new Color(0, 70, 30));
			g.fillOval(co.x + taille / 4 + taille / 12, co.y + taille / 3 + taille / 10, taille / 6, taille / 4);
			g.fillOval(co.x + taille / 2, co.y + taille / 3 + taille / 7, taille / 7, taille / 5);

			// Bouche
			g.setColor(Color.BLACK);
			g.drawOval(co.x + taille / 3 + taille / 12, co.y + taille / 2 + taille / 4 + taille / 18, taille / 8,
					taille / 10);
			g.setColor(Color.RED);
			g.fillOval(co.x + taille / 3 + taille / 12, co.y + taille / 2 + taille / 4 + taille / 18, taille / 8,
					taille / 10);
		}

	}
}
