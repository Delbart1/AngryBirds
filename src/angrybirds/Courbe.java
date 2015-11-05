package angrybirds;


import java.applet.Applet;
import java.awt.Graphics;
import java.util.Random;

/**
 * calcule de la courbe de l'oiseau
 * 
 * @author youdelice
 */
public class Courbe {

	private double a = -0.00005; // angle
	private double b = 0.0001;// hauteur
	private double c = 0;
        private Oiseau o;
        private Jeu j;
        
	public Courbe(Oiseau o) {
            this.o = o;
	}
        
        
        /**
         * cacul d'une fonction du second degrée
         * 
         * @param x coord X
         * @return Y selon X
         */
	double f(double x) {
            double r = a * Math.pow(x, 2) + b * x + c;
            System.out.println("x="+x+"; r="+r);
		return r;
	}

        /**
         * return le coef directeur de l'oiseau 
         * 
         * @param x coord X
         * @return le coef directeur
         */
	public double getCoefD(int x) {
		double coef1;
		double coef2;
		coef1 = (f(x) - f(x + 1));
		coef2 = (x - (x + 1));
		return coef2 / coef1;
	}

        /**
         * 
         * return le coef directeur de la courbe
         * 
         * @param x
         * @return 
         */
	public double getCoefDy(int x) {
		return f(x) - f(x - 1);
	}

        /**
         * 
         * 
         * 
         * @param x
         * @return 
         */
	public double angleNext(int x) {
		double tmp = getCoefD(x);
		if (tmp < 0) {
			return Math.PI / 2 - Math.atan(1 / getCoefDy(x)) + Math.PI;
		} else {
			return Math.PI / 2 - Math.atan(1 / getCoefDy(x));
		}
	}

	public void paint(Graphics g) {
		for (int x = 0; x < j.getSize().width; x++) {
			g.drawLine(x, (int) f(x), x + 1, (int) f(x + 1));

		}
		System.out.println(j.getSize());
	}
}