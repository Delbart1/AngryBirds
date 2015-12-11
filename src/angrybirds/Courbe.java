package angrybirds;

/**
 * 
 * Classe gérant la courbe lors de la progression de l'oiseau. La courbe est une
 * courbe de Bezier paramétrée avec 4 points
 * 
 * @author Thibaut
 *
 */
public class Courbe {

	public Coordonne[] pointsBezier = new Coordonne[] { new Coordonne(120, 400), new Coordonne(400, 100),
			new Coordonne(800, 400) };

	int index = 0;

	private Modele m;

	public Courbe(Modele m) {
		this.m = m;
	}

	/**
	 * 
	 * Renvoie le facteur utilisé pour faire pivoter l'oiseau
	 * 
	 * @param t
	 *            Temps
	 * @return facteur de l'opération de rotation
	 */

	public double directionBec(double t) {
		Coordonne c1 = coordSuivante(t);
		Coordonne c2 = coordSuivante(t + 0.01);

		return (c2.y - c1.y) / 5.0;
	}

	/**
	 * 
	 * Renvoie la prochaine coordonnée de l'oiseau en suivant la courbe
	 * 
	 * @param t
	 *            Temps
	 * @return Nouvelle coordonnée
	 */

	public Coordonne coordSuivante(double t) {

		int x = (int) (Math.pow(1 - t, 2) * pointsBezier[0].x + 2 * (1 - t) * t * pointsBezier[1].x
				+ Math.pow(t, 2) * pointsBezier[2].x);

		int y = (int) (Math.pow(1 - t, 2) * pointsBezier[0].y + 2 * (1 - t) * t * pointsBezier[1].y
				+ Math.pow(t, 2) * pointsBezier[2].y);

		return new Coordonne(x, y);
	}

}