package angrybirds;

import java.util.Random;

/**
 * 
 * Classe gérant la courbe lors de la progression de l'oiseau. La courbe est une
 * courbe de Bezier paramétrée avec 4 points
 * 
 * @author Thibaut
 *
 */
public class TrajectoireEnnemi {

	Random r = new Random();
	public Coordonne[] pointsBezier;

	public double t;

	int index = 0;

	Ennemi e;

	public TrajectoireEnnemi(Ennemi e) {
		this.e = e;
		pointsBezier = new Coordonne[] { e.co, new Coordonne(800, 400) };
		if (e.co.x < 600)
			pointsBezier[1].x = e.co.x + r.nextInt(200) + 50;
		else {
			pointsBezier[1].x = e.co.x - r.nextInt(200) - 50;
		}

		if (e.co.y < 200)
			pointsBezier[1].y = e.co.y + r.nextInt(200) + 50;
		else {
			pointsBezier[1].y = e.co.y - r.nextInt(200) - 50;
		}
	}

	/**
	 * 
	 * Renvoie la prochaine coordonnée de l'ennemi en suivant la courbe
	 * 
	 * @param t
	 *            Temps
	 * @return Nouvelle coordonnée
	 */

	public Coordonne coordSuivante(double t) {

		int x = (int) ((1 - t) * pointsBezier[0].x + t * pointsBezier[1].x);

		int y = (int) ((1 - t) * pointsBezier[0].y + t * pointsBezier[1].y);

		if (doitSwitch(x))
			inverserPoints();

		return new Coordonne(x, y);
	}

	public void inverserPoints() {
		Coordonne coTmp = pointsBezier[0];
		pointsBezier[0] = pointsBezier[1];
		pointsBezier[1] = coTmp;

		t = 0.005;
	}

	public boolean doitSwitch(int x) {

		if (pointsBezier[0].x < pointsBezier[1].x) {
			return (x >= pointsBezier[1].x);
		} else {
			return (x <= pointsBezier[1].x);
		}
	}

}