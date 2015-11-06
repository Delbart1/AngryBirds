package angrybirds;

/**
 * calcule de la courbe de l'oiseau
 * 
 * @author youdelice
 */
public class Courbe {

	Coordonne[] pointsBezier = new Coordonne[] { new Coordonne(120, 400), new Coordonne(160, 150),
			new Coordonne(450, 300), new Coordonne(750, 100) };

	int index = 0;
	
	public Courbe(){
		
	}

	public Coordonne coordSuivante(double dureeVol) {

		int x = (int) (Math.pow(1 - dureeVol, 3) * pointsBezier[0].x
				+ 3 * Math.pow(1 - dureeVol, 2) * dureeVol * pointsBezier[1].x
				+ 3 * (1 - dureeVol) * Math.pow(dureeVol, 2) * pointsBezier[2].x
				+ Math.pow(dureeVol, 3) * pointsBezier[3].x);

		int y = (int) (Math.pow(1 - dureeVol, 3) * pointsBezier[0].y
				+ 3 * Math.pow(1 - dureeVol, 2) * dureeVol * pointsBezier[1].y
				+ 3 * (1 - dureeVol) * Math.pow(dureeVol, 2) * pointsBezier[2].y
				+ Math.pow(dureeVol, 3) * pointsBezier[3].y);

		return new Coordonne(x, y);
	}

	public void courbeSuivante() {

		index++;
		switch (index) {
		case 1:
			pointsBezier = new Coordonne[] { new Coordonne(120, 400), new Coordonne(100, 550), new Coordonne(200, 30),
					new Coordonne(700, 50) };
			break;
		case 2:
			pointsBezier = new Coordonne[] { new Coordonne(120, 400), new Coordonne(400, 200), new Coordonne(200, 60),
					new Coordonne(700, 350) };
			break;
		case 3:
			pointsBezier = new Coordonne[] { new Coordonne(120, 400), new Coordonne(260, 500), new Coordonne(450, 550),
					new Coordonne(700, 350) };
			break;
		case 4:
			pointsBezier = new Coordonne[] { new Coordonne(120, 400), new Coordonne(200, 550), new Coordonne(350, 50),
					new Coordonne(700, 400) };
			break;
		case 5:
			pointsBezier = new Coordonne[] { new Coordonne(120, 400), new Coordonne(250, 200), new Coordonne(400, 25),
					new Coordonne(700, 550) };
			index = 0;
			break;
		}

	}

}