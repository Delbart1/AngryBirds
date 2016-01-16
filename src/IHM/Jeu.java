package ihm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.net.URL;
import java.util.ConcurrentModificationException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import angrybirds.Controller;
import angrybirds.Coordonne;
import angrybirds.Ennemi;
import angrybirds.Modele;
import angrybirds.Oiseau;
import exec.MVC;

/**
 * 
 * Classe effectuant toutes les actions n�cessaires au fonctionnement du jeu
 * 
 * @author Thibaut
 *
 */

@SuppressWarnings("serial")
public class Jeu extends JPanel {

	Modele m;
	Controller c;

	/**
	 * 
	 * Initialise le jeu et ajoute les Listeners
	 * 
	 * @param m
	 *            Modele du jeu
	 * @param c
	 *            Controller du jeu
	 */

	public Jeu(Modele m, Controller c) {
		this.m = m;
		this.c = c;

		ajouterListeners();

	}

	public void ajouterListeners() {
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				if (!m.o.lance && m.elastiqueTire) {
					c.lancerOiseau();

					try {
						jouerSon("bird.wav");
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {

				if (!m.elastiqueTire) {
					c.tirerElastique();
					try {
						jouerSon("slingshot.wav");
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
				}

				if (e.getX() >= m.o.coInit.x - m.rayonLancer && e.getX() < m.o.coInit.x + m.rayonLancer && !m.o.lance) {
					for (int i = 0; i < m.o.px.length; i++) {
						m.o.px[i] += e.getX() - m.o.co.x;

						m.o.px2[i] += e.getX() - m.o.co.x;
					}
					m.o.co.x = e.getX();
					repaint();
				}
				if (e.getY() >= m.o.coInit.y - m.rayonLancer && e.getY() < m.o.coInit.y + m.rayonLancer && !m.o.lance) {
					for (int i = 0; i < m.o.px.length; i++) {
						m.o.py[i] += e.getY() - m.o.co.y;

						m.o.py2[i] += e.getY() - m.o.co.y;
					}
					m.o.co.y = e.getY();
					repaint();
				}

				c.updateCoordOiseau();
				c.updateCoordMilieu(m.o.coInit.y - (m.o.co.y - m.o.coInit.y) * 10);
				if ((m.o.coInit.y - m.o.co.y) > 0)
					c.updateCoordFin(new Coordonne(m.o.coInit.x - (m.o.co.x - m.o.coInit.x) * 15,
							475 + (m.o.coInit.y - m.o.co.y) * 5));
				else
					c.updateCoordFin(new Coordonne(m.o.coInit.x - (m.o.co.x - m.o.coInit.x) * 15,
							475 - (m.o.coInit.y - m.o.co.y) * 5));
			}
		});
	}

	/**
	 * 
	 * Dessine l'oiseau specifie en parametre
	 * 
	 * @param o
	 *            Oiseau concerne
	 * @param g
	 *            Graphics de l'ihm
	 */

	public void paintOiseau(Oiseau o, Graphics g) {
		// Cell-shading
		g.setColor(Color.BLACK);
		g.fillOval(o.co.x - 3, o.co.y - 3, o.taille + 6, o.taille + 6);

		// Corps
		g.setColor(o.couleurPrincipale);
		g.fillOval(o.co.x, o.co.y, o.taille, o.taille);

		// Corps inferieur
		g.setColor(o.couleurSecondaire);
		g.fillOval(o.co.x + 5, o.co.y + o.taille / 2, o.taille - 10, o.taille / 2);

		// Yeux
		g.setColor(Color.WHITE);
		g.fillOval(o.co.x + o.taille / 2, o.co.y + o.taille / 4, o.taille / 5, o.taille / 5);
		g.fillOval(o.co.x + o.taille / 2 + o.taille / 5, o.co.y + o.taille / 4, o.taille / 5, o.taille / 5);

		// Pupilles
		g.setColor(Color.BLACK);
		g.fillOval(o.co.x + o.taille / 2 + o.taille / 15, o.co.y + o.taille / 4 + o.taille / 15, o.taille / 10,
				o.taille / 10);
		g.fillOval(o.co.x + o.taille / 2 + o.taille / 5 + o.taille / 25, o.co.y + o.taille / 4 + o.taille / 15,
				o.taille / 10, o.taille / 10);

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

	public void paintEnnemi(Ennemi e, Graphics g) {

		if (e.typeEnnemi == 1) {
			// Cell-shading
			g.setColor(Color.BLACK);
			g.fillOval(e.co.x - 3, e.co.y - 3, e.taille + 6, e.taille + 6);

			// Corps
			g.setColor(e.couleurPrincipale);
			g.fillOval(e.co.x, e.co.y, e.taille, e.taille);

			// Corps inferieur
			g.setColor(e.couleurSecondaire);
			g.fillOval(e.co.x + 5, e.co.y + e.taille / 2, e.taille - 10, e.taille / 2);

			// Yeux
			g.setColor(Color.WHITE);
			g.fillOval(e.co.x + e.taille / 15, e.co.y + e.taille / 3, e.taille / 5, e.taille / 5);
			g.fillOval(e.co.x + e.taille / 2 + e.taille / 4, e.co.y + e.taille / 3, e.taille / 5, e.taille / 5);

			// Pupilles
			g.setColor(Color.BLACK);
			g.fillOval(e.co.x + e.taille / 15, e.co.y + e.taille / 3 + e.taille / 15, e.taille / 10, e.taille / 10);
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
			g.drawOval(e.co.x + e.taille / 3 + e.taille / 12, e.co.y + e.taille / 2 + e.taille / 4 + e.taille / 18,
					e.taille / 8, e.taille / 10);
			g.setColor(Color.RED);
			g.fillOval(e.co.x + e.taille / 3 + e.taille / 12, e.co.y + e.taille / 2 + e.taille / 4 + e.taille / 18,
					e.taille / 8, e.taille / 10);
		} else if (e.typeEnnemi == 2) {

			// Cell-shading
			g.setColor(Color.black);
			g.fillRoundRect(e.co.x - 3, e.co.y - 3, e.taille + e.taille / 3 + 6, e.taille + 6, 20, 20);

			// Corps
			g.setColor(e.couleurPrincipale);
			g.fillOval(e.co.x, e.co.y, e.taille, e.taille);
			g.fillRoundRect(e.co.x, e.co.y, e.taille + e.taille / 3, e.taille, 20, 20);

			// Corps inferieur
			g.setColor(e.couleurSecondaire);
			g.fillRoundRect(e.co.x + 5, e.co.y + e.taille / 2, e.taille + e.taille / 3 - e.taille / 4,
					e.taille / 2 - e.taille / 15, 20, 20);

			// Yeux
			g.setColor(Color.WHITE);
			g.fillOval(e.co.x + e.taille / 10, e.co.y + e.taille / 4, e.taille / 5, e.taille / 5);
			g.fillOval(e.co.x + e.taille, e.co.y + e.taille / 4, e.taille / 5, e.taille / 5);

			// Pupilles
			g.setColor(Color.BLACK);
			g.fillOval(e.co.x + e.taille / 10, e.co.y + e.taille / 4 + e.taille / 15, e.taille / 10, e.taille / 10);
			g.fillOval(e.co.x + e.taille + e.taille / 10, e.co.y + e.taille / 4 + e.taille / 15, e.taille / 10,
					e.taille / 10);

			// Groin
			g.setColor(e.couleurSecondaire);
			g.fillOval(e.co.x + e.taille / 3, e.co.y + e.taille / 3, e.taille / 2 + e.taille / 5,
					e.taille / 3 + e.taille / 10);
			g.setColor(new Color(66, 159, 107));
			g.drawOval(e.co.x + e.taille / 3, e.co.y + e.taille / 3, e.taille / 2 + e.taille / 5,
					e.taille / 3 + e.taille / 10);
			g.setColor(new Color(0, 70, 30));
			g.fillOval(e.co.x + e.taille / 3 + e.taille / 8, e.co.y + e.taille / 3 + e.taille / 10, e.taille / 5,
					e.taille / 4);
			g.fillOval(e.co.x + e.taille / 2 + e.taille / 5, e.co.y + e.taille / 3 + e.taille / 7, e.taille / 6,
					e.taille / 5);

			// Bouche
			g.setColor(Color.BLACK);
			g.drawOval(e.co.x + e.taille / 3 + e.taille / 4, e.co.y + e.taille / 2 + e.taille / 4 + e.taille / 18,
					e.taille / 8, e.taille / 10);
			g.setColor(Color.RED);
			g.fillOval(e.co.x + e.taille / 3 + e.taille / 4, e.co.y + e.taille / 2 + e.taille / 4 + e.taille / 18,
					e.taille / 8, e.taille / 10);
		}
	}

	/**
	 * IHM creant le fond et les ennemis
	 * 
	 * @param g
	 *            le graphique du jeu
	 */
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		g.drawImage(new ImageIcon(MVC.class.getResource("fond2.png")).getImage(), 0, 0, null);
		g.drawImage(new ImageIcon(MVC.class.getResource("slingshot.png")).getImage(), m.o.coInit.x + 10, 410, null);

		g2d.setColor(Color.red);
		for (Coordonne c : m.trace) {
			g.fillOval(c.x, c.y, m.o.taille / 5, m.o.taille / 5);
		}

		AffineTransform old = g2d.getTransform();
		AffineTransform trans = new AffineTransform();
		trans.rotate(Math.toRadians(m.o.directionY * 25), m.o.co.x + m.o.taille / 2, m.o.co.y + m.o.taille / 2);

		g2d.transform(trans);

		// Ce qui pivotera

		paintOiseau(m.o, g2d);

		g2d.setTransform(old);

		// Ce qui ne pivotera pas

		for (Ennemi e : m.getEnnemis()) {
			try {
				paintEnnemi(e, g2d);
			} catch (ConcurrentModificationException err) {

			}
		}

		g2d.drawImage(new ImageIcon(MVC.class.getResource("slingshot2.png")).getImage(), m.o.coInit.x + 10, 410, null);
		g2d.drawImage(new ImageIcon(MVC.class.getResource("caisse.png")).getImage(), 0, 480, null);

	}

	/**
	 * Charge un fichier audio et le joue
	 * 
	 * @param nomFichier
	 *            Nom du fichier audio (exemple: son.wav)
	 */
	public void jouerSon(String nomFichier)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		URL url = MVC.class.getResource(nomFichier);
		final Clip clip = AudioSystem.getClip();
		try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(url)) {
			clip.open(audioIn);
		}
		clip.start();
	}

}
