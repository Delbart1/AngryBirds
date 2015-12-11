package IHM;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.net.URL;

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
import angrybirds.MVC;
import angrybirds.Modele;

/**
 * 
 * Classe effectuant toutes les actions nécessaires au fonctionnement du jeu
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
	 * @param nbEnnemis
	 */

	public Jeu(Modele m, Controller c) {
		this.m = m;
		this.c = c;

		ajouterListeners();

	}

	public void ajouterListeners() {
		this.addMouseListener(new MouseAdapter() {

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

		this.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {

				if (!m.elastiqueTire) {
					m.elastiqueTire = true;
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

		m.o.paintComponent(this, g2d);

		g2d.setTransform(old);

		// Ce qui ne pivotera pas

		for (Ennemi e : m.getEnnemis()) {
			e.paintComponent(g2d);
		}

		g2d.drawImage(new ImageIcon(MVC.class.getResource("slingshot2.png")).getImage(), m.o.coInit.x + 10, 410, null);
		g2d.drawImage(new ImageIcon(MVC.class.getResource("caisse.png")).getImage(), 0, 480, null);
	}

	/**
	 * timer du jeu, retire les ennemis si touche et le lance de l'oiseau
	 * 
	 */

	/**
	 * lit le son du jeu
	 * 
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
