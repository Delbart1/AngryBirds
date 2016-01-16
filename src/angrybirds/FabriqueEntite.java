package angrybirds;

import java.awt.Color;

public class FabriqueEntite {

	public FabriqueEntite() {

	}

	public Entite create(String description) {

		description = description.toLowerCase();
		if (description.contains("oiseau")) {
			Oiseau o = new Oiseau(50);
			if (description.contains("jaune"))
				o.couleurPrincipale = Color.YELLOW;
			else if (description.contains("bleu"))
				o.couleurPrincipale = Color.BLUE;
			return o;
		} else if (description.contains("cochon")) {
			Ennemi e = new Ennemi(50);
			if (description.contains("noir"))
				e.couleurPrincipale = Color.BLACK;
			else if (description.contains("rose"))
				e.couleurPrincipale = Color.PINK;
			return e;
		}

		return null;
	}

}
