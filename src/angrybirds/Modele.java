package angrybirds;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
/**
 * Modele du MVC
 * @author Thibaut
 *
 */
public class Modele extends Observable {

	public Random r = new Random();
	public int nbEnnemis;
	public boolean elastiqueTire;
	public double t;
	public Oiseau o;
	public ArrayList<Ennemi> ennemis;
	public Ennemi ennemiMort;
	public ArrayList<Coordonne> trace;
	public int cptTrace = 0;
	public Courbe courbeSuivie;
	public int rayonLancer;
	public int ySol = 525;

	Timer timer = new Timer();

	public Modele() {
		initialiser();
		demarrerEnnemis();
	}

	/**
	 * initialise le jeu.
	 * 
	 */
	public void initialiser() {
		nbEnnemis = 5;
		elastiqueTire = false;
		t = 0.0;
		o = new Oiseau(50);
		ennemis = new ArrayList<Ennemi>();
		ennemiMort = null;
		trace = new ArrayList<>();
		courbeSuivie = new Courbe();
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

		setChanged();
		notifyObservers();
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

	/**
	 * D�placement des ennemis.
	 */
	public void demarrerEnnemis() {
		TimerTask task = new TimerTask() {

			public void run() {
				for (Ennemi e : ennemis) {
					e.setCoord(e.trajectoire.coordSuivante(e.trajectoire.t));
					e.trajectoire.t += 0.005;
				}

				setChanged();
				notifyObservers();
			}
		};

		timer.scheduleAtFixedRate(task, 0, 10);
	}

	/**
	 * Lance l'oiseau et apporte les modifications.
	 */
	public void lancerOiseau() {

		elastiqueTire = true;
		o.lance = true;

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

				cptTrace++;
				if (cptTrace == 5) {
					trace.add(new Coordonne(o.co.x + o.taille / 2, o.co.y + o.taille / 2));
					cptTrace = 0;
				}

				o.directionY = courbeSuivie.directionBec(t);

				if (o.co.y >= ySol - o.taille) {
					this.cancel();
					nouveauLancer();
				}

			}
		};

		timer.scheduleAtFixedRate(task, 0, 10);

	}
	/**
	 * met � jour les coordonn�es de l'oiseau. &&&
	 */
	public void updateCoordOiseau() {
		courbeSuivie.pointsBezier[0] = new Coordonne(o.co.x, o.co.y);
		setChanged();
		notifyObservers();
	}

	/**
	 * modifie le point milieu de la courbe de bezier. &&&
	 * @param y
	 */
	public void updateCoordMilieu(int y) {
		courbeSuivie.pointsBezier[1].setY(y);
		setChanged();
		notifyObservers();
	}
    
	/**
	 * modifie le dernier point de la courbe de bezier. &&&
	 * @param co
	 */
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
