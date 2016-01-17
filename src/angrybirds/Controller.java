package angrybirds;

public class Controller {
/**
 * Controller du MVC.
 */
	private Modele m;

	public Controller(Modele m) {
		this.m = m;
	}

	public void lancerOiseau() {
		m.lancerOiseau();
	}

	public void updateCoordDebut() {
		m.updateCoordDebut();
	}

	public void updateCoordMilieu(int y) {
		m.updateCoordMilieu(y);
	}

	public void updateCoordFin(Coordonne co) {
		m.updateCoordFin(co);
	}

	public void nouveauJeu() {
		m.nouveauJeu();
	}

	public void tirerElastique() {
		m.elastiqueTire = true;
	}

}
