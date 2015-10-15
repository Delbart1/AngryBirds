import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
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
	boolean elastiqueTire = false;
	private int idDirection;

	Oiseau o = new Oiseau(50);
	ArrayList<Ennemi> ennemis = new ArrayList<Ennemi>();

	Coordonne coInit = new Coordonne(o.co.x, o.co.y);

	public Jeu(int nbEnnemis) {
		for (int i = 0; i < nbEnnemis; i++) {
			Ennemi ennemitmp = new Ennemi(50);
			for (Ennemi e : ennemis) {
				if (collision(e, ennemitmp)) {
					ennemitmp = new Ennemi(50);
				}
			}
			ennemis.add(ennemitmp);
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
				if (!elastiqueTire) {
					elastiqueTire = true;
					try {
						jouerSon("slingshot.wav");
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

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
					if (o.co.y <= coInit.y + 25) {
						idDirection = 1;
					} else {
						idDirection = 2;
					}
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
		g.drawImage(new ImageIcon(Main.class.getResource("slingshot.png")).getImage(), coInit.x + 10, 410, null);
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

			// Corps inf�rieur
			g.setColor(e.couleurSecondaire);
			g.fillOval(e.co.x + 5, e.co.y + e.taille / 2, e.taille - 10, e.taille / 2);

			// Yeux
			g.setColor(Color.WHITE);
			g.fillOval(e.co.x + e.taille / 2, e.co.y + e.taille / 4, e.taille / 5, e.taille / 5);
			g.fillOval(e.co.x + e.taille / 2 + e.taille / 5, e.co.y + e.taille / 4, e.taille / 5, e.taille / 5);

			// Pupilles
			g.setColor(Color.BLACK);
			g.fillOval(e.co.x + e.taille / 2 + e.taille / 15, e.co.y + e.taille / 4 + e.taille / 15, e.taille / 10,
					e.taille / 10);
			g.fillOval(e.co.x + e.taille / 2 + e.taille / 5 + e.taille / 25, e.co.y + e.taille / 4 + e.taille / 15,
					e.taille / 10, e.taille / 10);

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
			g.fillOval(e.co.x + e.taille / 15, e.co.y + e.taille / 3, e.taille / 5, e.taille / 5);
			g.fillOval(e.co.x + e.taille / 2 + e.taille / 4, e.co.y + e.taille / 3, e.taille / 5, e.taille / 5);

			// Pupilles
			g.setColor(Color.BLACK);
			g.fillOval(e.co.x + e.taille / 15, e.co.y + e.taille / 3 + e.taille / 15, e.taille / 10,
					e.taille / 10);
			g.fillOval(e.co.x + e.taille / 2 + e.taille / 3, e.co.y + e.taille / 3 + e.taille / 15, e.taille / 10,
					e.taille / 10);

			// Groin
			g.setColor(e.couleurSecondaire);
			g.fillOval(e.co.x + e.taille / 4, e.co.y + e.taille / 3, e.taille / 2, e.taille / 3 + e.taille / 10);
			g.setColor(new Color(66, 159, 107));
			g.drawOval(e.co.x + e.taille / 4, e.co.y + e.taille / 3, e.taille / 2, e.taille / 3 + e.taille / 10);
			g.setColor(new Color(0, 70, 30));
			g.fillOval(e.co.x + e.taille / 4 + e.taille / 12, e.co.y + e.taille / 3 + e.taille / 10, e.taille / 6,
					e.taille / 4);
			g.fillOval(e.co.x + e.taille / 2, e.co.y + e.taille / 3 + e.taille / 7, e.taille / 7, e.taille / 5);

			// Bouche
			g.setColor(Color.BLACK);
			g.drawOval(e.co.x + e.taille / 3 + e.taille / 12, e.co.y + e.taille / 2 + e.taille / 4 + e.taille / 18, e.taille / 8, e.taille / 10);
			g.setColor(Color.RED);
			g.fillOval(e.co.x + e.taille / 3 + e.taille / 12, e.co.y + e.taille / 2 + e.taille / 4 + e.taille / 18, e.taille / 8, e.taille / 10);
		}

		g.drawImage(new ImageIcon(Main.class.getResource("slingshot2.png")).getImage(), coInit.x + 10, 410, null);

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

	public boolean collision(Ennemi e1, Ennemi e) {
		return e1.co.x < e.co.x + e.taille && e1.co.x + e1.taille > e.co.x && e1.co.y < e.co.y + e.taille
				&& e1.co.y + e1.taille > e.co.y;
	}

	/**
	 * timer du jeu, retire les ennemi si toucher et le lancer de l'oiseau
	 * 
	 */
	public void lancerOiseau() {

		lance = true;

		try {
			jouerSon("bird.wav");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

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

	public void jouerSon(String nomFichier)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		URL url = Main.class.getResource(nomFichier);
		final Clip clip = AudioSystem.getClip();
		try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(url)) {
			clip.open(audioIn);
		}
		clip.start();
	}
}
