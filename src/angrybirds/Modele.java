package angrybirds;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Modele extends Observable {

	public Random r = new Random();
	public int nbEnnemis;
	public boolean elastiqueTire;
	public double t;
	public Oiseau o;
	public ArrayList<Ennemi> ennemis;
	public Ennemi ennemiMort;
	public ArrayList<Coordonne> trace;
	public Courbe courbeSuivie;
	public int rayonLancer;

	public Modele() {
		initialiser();
	}

	public void initialiser() {
		nbEnnemis = 5;
		elastiqueTire = false;
		t = 0.0;
		o = new Oiseau(50);
		ennemis = new ArrayList<Ennemi>();
		ennemiMort = null;
		trace = new ArrayList<>();
		courbeSuivie = new Courbe(this);
		rayonLancer = 75;

		for (int i = 0; i < nbEnnemis; i++) {
			Ennemi ennemitmp = new Ennemi(50);
			for (Ennemi e : getEnnemis()) {
				while (collision(e, ennemitmp)) {
					ennemitmp = new Ennemi(50);
				}
			}
			getEnnemis().add(ennemitmp);
		}
	}

	/**
	 * gere la collision entre 2 entites
	 * 
	 * @param e1
	 *            une entite
	 * @param e2
	 *            une entite
	 * @return si il touche ou pas
	 */

	public boolean collision(Entite e1, Entite e2) {
		return e1.co.x < e2.co.x + e2.taille && e1.co.x + e1.taille > e2.co.x && e1.co.y < e2.co.y + e2.taille
				&& e1.co.y + e1.taille > e2.co.y;
	}

	/**
	 * 
	 * Reinisialise l'oiseau et effectue un nouveau lancer
	 * 
	 */
	public void nouveauLancer() {
		t = 0.0;
		o = new Oiseau(o.taille);
		elastiqueTire = false;
		trace = new ArrayList<>();
		setChanged();
		notifyObservers();
	}

	public void lancerOiseau() {

		elastiqueTire = true;
		o.lance = true;

		Timer timer = new Timer();

		TimerTask task = new TimerTask() {

			// animation du jeu
			public void run() {

				o.setCoord(courbeSuivie.coordSuivante(t));

				for (Ennemi e : getEnnemis()) {

					if (collision(o, e)) {
						this.cancel();
						ennemiMort = e;
					}

				}

				if (ennemiMort != null) {
					getEnnemis().remove(ennemiMort);
					ennemiMort = null;
					nouveauLancer();
				}

				if (o.co.x > 800 || o.co.x < -o.taille || o.co.y > 700) {
					this.cancel();
					nouveauLancer();
				}

				t += 0.005;
				if (t * 2 >= 7) {
					this.cancel();
					nouveauLancer();
				}

				if ((o.co.x % 20) == 0)
					trace.add(new Coordonne(o.co.x + o.taille / 2, o.co.y + o.taille / 2));

				o.directionY = courbeSuivie.directionBec(t);

				setChanged();
				notifyObservers();
			}

		};

		timer.scheduleAtFixedRate(task, 0, 10);

	}

	public void updateCoordOiseau() {
		courbeSuivie.pointsBezier[0] = new Coordonne(o.co.x, o.co.y);
		setChanged();
		notifyObservers();
	}

	public void updateCoordMilieu(int y) {
		courbeSuivie.pointsBezier[1].setY(y);
		setChanged();
		notifyObservers();
	}

	public void updateCoordFin(Coordonne co) {
		if (co.y < 475)
			co.y = 475;
		courbeSuivie.pointsBezier[2] = co;
		courbeSuivie.pointsBezier[1].setX(courbeSuivie.pointsBezier[0].x + (co.x - courbeSuivie.pointsBezier[0].x) / 2);
		setChanged();
		notifyObservers();
	}

	public ArrayList<Ennemi> getEnnemis() {
		return ennemis;
	}

	public void nouveauJeu() {
		initialiser();
	}

}
