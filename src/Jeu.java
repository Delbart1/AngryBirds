import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javafx.scene.shape.Rectangle;
import sun.security.krb5.internal.Ticket;


/**
 * classe du jeu, avec les actions
 * 
 * @author youdelice
 */
public class Jeu extends JPanel {

	Random r = new Random();
	boolean lance = false;
        private int idDirection;

	Oiseau o = new Oiseau(r.nextInt(10) + 40);
	ArrayList<Ennemi> ennemis = new ArrayList<Ennemi>();

	Coordonne coInit = new Coordonne(o.co.x, o.co.y);

	public Jeu() {
		for (int i = 0; i < 5; i++) {
			ennemis.add(new Ennemi(r.nextInt(10) + 40));
		}

		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (!lance)
					lancerOiseau();
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		this.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getX() >= coInit.x - 50 && e.getX() < coInit.x + 50 && !lance) {
					o.co.x = e.getX();
					repaint();
				}
				if (e.getY() >= coInit.y - 50 && e.getY() < coInit.y + 50 && !lance) {
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
		g.drawImage(new ImageIcon(Main.class.getResource("fond.png")).getImage(), 0, 0, null);
		paintEntite(o, g);

		for (Ennemi e : ennemis) {
			paintEntite(e, g);
		}

	}

        /**
         * ihm d'une entité
         * 
         * @param e l'entité
         * @param g 
         */
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
			g.setColor(new Color(255,204,153));
			g.fillPolygon(o.px, o.py, 3);
		}
	}

        /**
         * geré la collision entre 2 entité
         * 
         * @param o oiseau
         * @param e un ennemi
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
