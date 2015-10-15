package angrybirds;

import java.applet.Applet;
import java.awt.Graphics;

public class Courbe extends Applet {

	
	private double t=100;
	private double b=50;
	private double c=450;
	

	double f(double x) {
		return t*Math.cos(x/b)+c;
	}

	public void paint(Graphics g) {
		for (int x = 0; x < getSize().width; x++) {
			g.drawLine(x, (int) f(x), x + 1, (int) f(x + 1));
			
		}
		System.out.println(getSize());
	}
}