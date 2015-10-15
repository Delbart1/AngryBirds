
import java.applet.Applet;
import java.awt.Graphics;

public class Courbe extends Applet {

	
	private double a; // angle
	private double b;// hauteur
	private double c; //  
	
	public Courbe(double a, double b, double c){
		this.a = a;
		this.b = b;
		this.c = c;
	}

	double f(double x) {
		return  a*Math.pow(x, 2)+ b*x + c;
	}
	
	  public double getCoefD(int x) {
	        double coef1;
	        double coef2;
	        coef1 = (f(x) - f(x + 1));
	        coef2 = (x - (x + 1));
	        return coef2 / coef1;
	    }

	 
	    public double getCoefDy(int x) {
	        return f(x) - f(x - 1);
	    }

	    
	    public double angleNext(int x) {
	        double tmp = getCoefD(x);
	        if (tmp < 0) {
	            return Math.PI / 2 - Math.atan(1 / getCoefDy(x)) + Math.PI;
	        } else {
	            return Math.PI / 2 - Math.atan(1 / getCoefDy(x));
	        }
	    }
	        

	public void paint(Graphics g) {
		for (int x = 0; x < getSize().width; x++) {
			g.drawLine(x, (int) f(x), x + 1, (int) f(x + 1));
			
		}
		System.out.println(getSize());
	}
}