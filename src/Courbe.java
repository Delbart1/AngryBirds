
import java.applet.Applet;
import java.awt.Graphics;

public class Courbe extends Applet {

	
	private double a=0.001;   // angle
	private double b=-0.02;  // hauteur
	private double c=100;   // 
	
	/*public Courbe(double a, double b, double c){
		this.a = a;
		this.b = b;
		this.c = c;
	}*/

	double f(double x) {
		return  a*Math.pow(x, 2)+ b*x + c;
	}

	public void paint(Graphics g) {
		for (int x = 0; x < getSize().width; x++) {
			g.drawLine(x, (int) f(x), x + 1, (int) f(x + 1));
			
		}
		System.out.println(getSize());
	}
}