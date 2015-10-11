import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * classe du jeu, avec les actions
 * 
 * @author youdelice
 */
@SuppressWarnings("serial")
public class Jeu extends JPanel {

	Random r = new Random();
	boolean lance = false;
	private int idDirection;

	Oiseau o = new Oiseau(50);
	ArrayList<Ennemi> ennemis = new ArrayList<Ennemi>();

	Coordonne coInit = new Coordonne(o.co.x, o.co.y);

	public Jeu(int nbEnnemis) {
		for (int i = 0; i < nbEnnemis; i++) {
			ennemis.add(new Ennemi(50));
		}

		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				if (!lance)
					lancerOiseau();
			}
		});

		this.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getX() >= coInit.x - 50 && e.getX() < coInit.x + 50 && !lance) {
					for (int i = 0; i < o.px.length; i++) {
						o.px[i] += e.getX() - o.co.x;
						o.px2[i] += e.getX() - o.co.x;
					}
					o.co.x = e.getX();
					repaint();
				}
				if (e.getY() >= coInit.y - 50 && e.getY() < coInit.y + 50 && !lance) {
					for (int i = 0; i < o.px.length; i++) {
						o.py[i] += e.getY() - o.co.y;
						o.py2[i] += e.getY() - o.co.y;
					}
					o.co.y = e.getY();
					repaint();
				}
			}
		});
	}

	/**
	 * IHM creant le fond et les ennemis
	 * 
	 * @param g
	 */
	public void paintComponent(Graphics g) {
		g.drawImage(new ImageIcon(Main.class.getResource("fond2.png")).getImage(), 0, 0, null);
		paintEntite(o, g);

		for (Ennemi e : ennemis) {
			paintEntite(e, g);
		}

		g.drawImage(new ImageIcon(Main.class.getResource("caisse.png")).getImage(), 0, 480, null);
	}

	/**
	 * ihm d'une entité
	 * 
	 * @param e
	 *            l'entité
	 * @param g
	 */
	public void paintEntite(Entite e, Graphics g) {

		// Cell-shading
		g.setColor(Color.BLACK);
		g.fillOval(e.co.x - 3, e.co.y - 3, e.taille + 6, e.taille + 6);

		if (e instanceof Oiseau) {
			// Elastique
			if (!lance) {
				g.setColor(new Color(51, 25, 0));
				g.drawLine(o.co.x + o.taille / 2, o.co.y + o.taille / 2, 142, 435);
			}
		}

		// Corps
		g.setColor(e.couleurPrincipale);
		g.fillOval(e.co.x, e.co.y, e.taille, e.taille);

		// Corps inf�rieur
		g.setColor(e.couleurSecondaire);
		g.fillOval(e.co.x + 5, e.co.y + e.taille / 2, e.taille - 10, e.taille / 2);

		if (e instanceof Oiseau) {

			// Yeux
			g.setColor(Color.WHITE);
			g.fillOval(e.co.x + e.taille / 2, e.co.y + e.taille / 4, e.taille / 5, e.taille / 5);
			g.fillOval(e.co.x + e.taille / 2 + e.taille / 5, e.co.y + e.taille / 4, e.taille / 5, e.taille / 5);

			// Pupilles
			g.setColor(Color.BLACK);
			g.fillOval(e.co.x + e.taille / 2 + e.taille / 15, e.co.y + e.taille / 4 + e.taille / 15, e.taille / 10,
					e.taille / 10);
			g.fillOval(e.co.x + e.taille / 2 + e.taille / 5 + e.taille / 25, e.co.y + e.taille / 4 + e.taille / 15, e.taille / 10,
					e.taille / 10);
			
			// Bec
			g.setColor(new Color(255, 204, 0));
			g.fillPolygon(o.px, o.py, 3);
			g.setColor(new Color(255, 172, 0));
			g.fillPolygon(o.px2, o.py2, 3);
			g.setColor(Color.BLACK);
			g.drawPolygon(o.px, o.py, 3);
			g.drawLine(o.px2[0], o.py2[0], o.px2[1], o.py2[1]);
			g.drawLine(o.px2[1], o.py2[1], o.px2[2], o.py2[2]);

		}

		else {
			// Yeux
			g.setColor(Color.WHITE);
			g.fillOval(e.co.x + e.taille / 4, e.co.y + e.taille / 4, e.taille / 5, e.taille / 5);
			g.fillOval(e.co.x + e.taille / 2 + e.taille / 10, e.co.y + e.taille / 4, e.taille / 5, e.taille / 5);

			// Pupilles
			g.setColor(Color.BLACK);
			g.fillOval(e.co.x + e.taille / 4 + e.taille / 10, e.co.y + e.taille / 4 + e.taille / 15, e.taille / 10,
					e.taille / 10);
			g.fillOval(e.co.x + e.taille / 2 + e.taille / 10, e.co.y + e.taille / 4 + e.taille / 15, e.taille / 10,
					e.taille / 10);
		}

	}

	/**
	 * geré la collision entre 2 entité
	 * 
	 * @param o
	 *            oiseau
	 * @param e
	 *            un ennemi
	 * @return si il touche ou pas
	 */
	public boolean collision(Oiseau o, Ennemi e) {
		return o.co.x < e.co.x + e.taille && o.co.x + o.taille > e.co.x && o.co.y < e.co.y + e.taille
				&& o.co.y + o.taille > e.co.y;
	}

	/**
	 * timer du jeu, retire les ennemi si toucher et le lancer de l'oiseau
	 * 
	 */
	public void lancerOiseau() {
		idDirection = r.nextInt(2) + 1;

		lance = true;

		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				switch (idDirection) {
				case 1:
					o.bouger(2, 0);
					break;
				case 2:
					o.bouger(2, -1);
					break;
				}

				ArrayList<Ennemi> ennemisMorts = new ArrayList<Ennemi>();
				for (Ennemi e : ennemis) {
					if (collision(o, e)) {
						ennemisMorts.add(e);

					}
				}
				for (Ennemi e : ennemisMorts) {
					ennemis.remove(e);
				}
				repaint();
			}

		};

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, 0, 10);
	}
}
