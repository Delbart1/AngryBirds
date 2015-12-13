package angrybirds;

public class Controller {

	private Modele m;

	public Controller(Modele m) {
		this.m = m;
	}

	public void lancerOiseau() {
		m.lancerOiseau();
	}

	public void updateCoordOiseau() {
		m.updateCoordOiseau();
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
