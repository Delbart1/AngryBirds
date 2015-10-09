import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import sun.security.krb5.internal.Ticket;

public class Jeu extends JPanel {

	Oiseau o = new Oiseau(20);
	ArrayList<Ennemi> ennemis = new ArrayList<Ennemi>();

	public Jeu() {
		for (int i = 0; i < 5; i++) {
			ennemis.add(new Ennemi(20));
		}
		
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				o.bouger(1, 0);
				repaint(o.co.x-5, o.co.y-5, o.taille*2+5, o.taille*2+5);
			}
			 
		};
		

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, 0, 10);
		
	}

	public void paintComponent(Graphics g) {
		g.drawImage(new ImageIcon(Main.class.getResource("fond.png")).getImage(), 0, 0, null);
		paintEntite(o, g);

		for (Ennemi e : ennemis) {
			paintEntite(e, g);
		}
		
	}

	public void paintEntite(Entite e, Graphics g) {
		g.setColor(e.couleurPrincipale);
		g.fillOval(e.co.x, e.co.y, 2 * e.taille, 2 * e.taille);
		g.setColor(e.couleurSecondaire);
		g.fillOval(e.co.x, e.co.y + e.taille, 2 * e.taille, e.taille);
		if (e instanceof Oiseau) {
			g.setColor(Color.BLACK);
			g.fillPolygon(o.px, o.py, 3);
		}
	}

	public int square(int a) {
		return a * a;
	}

	public boolean collision(Oiseau o, Ennemi e) {
		return (Math.sqrt(square(e.co.y - o.co.y) + square(e.co.x - o.co.x)) <= (o.taille + e.taille));
	}
}
