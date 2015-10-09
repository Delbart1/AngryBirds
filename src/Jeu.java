import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import sun.security.krb5.internal.Ticket;

public class Jeu extends JPanel {

	Random r = new Random();

	Oiseau o = new Oiseau(r.nextInt(10) + 40);
	ArrayList<Ennemi> ennemis = new ArrayList<Ennemi>();

	public Jeu() {
		for (int i = 0; i < 5; i++) {
			ennemis.add(new Ennemi(r.nextInt(10) + 40));
		}

		this.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				lancerOiseau();
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		// lancerOiseau();

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
		g.fillOval(e.co.x, e.co.y, e.taille, e.taille);
		g.setColor(e.couleurSecondaire);
		g.fillOval(e.co.x + 3, e.co.y + e.taille / 2, e.taille - 6, e.taille / 2);
		g.setColor(Color.WHITE);
		g.fillOval(e.co.x + e.taille / 4, e.co.y + e.taille / 4, e.taille / 5, e.taille / 5);
		g.fillOval(e.co.x + e.taille / 2 + e.taille / 10, e.co.y + e.taille / 4, e.taille / 5, e.taille / 5);
		g.setColor(Color.BLACK);
		g.fillOval(e.co.x + e.taille / 4 + e.taille / 10, e.co.y + e.taille / 4 + e.taille / 15, e.taille / 10,
				e.taille / 10);
		g.fillOval(e.co.x + e.taille / 2 + e.taille / 10, e.co.y + e.taille / 4 + e.taille / 15, e.taille / 10,
				e.taille / 10);

		if (e instanceof Oiseau) {
			g.setColor(Color.WHITE);
			g.fillPolygon(o.px, o.py, 3);
		}
	}

	public int square(int a) {
		return a * a;
	}

	public boolean collision(Oiseau o, Ennemi e) {
		return (Math.sqrt(square(e.co.y - o.co.y) + square(e.co.x - o.co.x)) <= (o.taille + e.taille));
	}

	public void lancerOiseau() {
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				o.bouger(1, 0);
				// repaint(o.co.x - 5, o.co.y - 5, o.taille * 2 + 5, o.taille *
				// 2 + 5);
				repaint();
			}

		};

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, 0, 10);
	}
}
