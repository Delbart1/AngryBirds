package angrybirds;

import angrybirds.Coordonne;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
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
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * classe du jeu, avec les actions
 * 
 * @author youdelice
 */

public class Jeu extends JPanel {

	protected JFrame f; 

	Random r = new Random();
	boolean elastiqueTire = false;
	private int idDirection;

	private int nbLancers = 1;

	Oiseau o = new Oiseau(50);
	ArrayList<Ennemi> ennemis = new ArrayList<Ennemi>(); //liste avec les ennemis 

	Coordonne coInit = new Coordonne(o.co.x, o.co.y);

        
        /**
         * lancement du jeu avec le mouselistener
         * 
         * @param nbEnnemis     nmb d'ennemi voulu
         */
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
				if (!o.lance)
					lancerOiseau();
			}
		});

		this.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) { //tire diffrent selon la coordonné du laché 
				if (!elastiqueTire) {
					elastiqueTire = true;
					/*try {
						jouerSon("slingshot.wav");
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}*/
				}

				// TODO Auto-generated method stub
				if (e.getX() >= coInit.x - 50 && e.getX() < coInit.x + 50 && !o.lance) {
					for (int i = 0; i < o.px.length; i++) {
						o.px[i] += e.getX() - o.co.x;

						o.px2[i] += e.getX() - o.co.x;
					}
					o.co.x = e.getX();
					repaint();
				}
				if (e.getY() >= coInit.y - 50 && e.getY() < coInit.y + 50 && !o.lance) {
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
	 * @param g le graphique du jeu
	 */
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		g.drawImage(new ImageIcon(Main.class.getResource("fond2.png")).getImage(), 0, 0, null);
		g.drawImage(new ImageIcon(Main.class.getResource("slingshot.png")).getImage(), coInit.x + 10, 410, null);

		AffineTransform old = g2d.getTransform();
		AffineTransform trans = new AffineTransform();
		trans.rotate(Math.toRadians(o.directionY * 25), o.co.x + o.taille / 2, o.co.y + o.taille / 2);

		g2d.transform(trans);

		// Ce qui pivotera

		o.paintComponent(this, g2d);

		g2d.setTransform(old);

		// Ce qui ne pivotera pas

		for (Ennemi e : ennemis) {
			e.paintComponent(g2d);
		}

		g2d.drawImage(new ImageIcon(Main.class.getResource("slingshot2.png")).getImage(), coInit.x + 10, 410, null);
		g2d.drawImage(new ImageIcon(Main.class.getResource("caisse.png")).getImage(), 0, 480, null);
	}

	/**
	 * geré la collision entre 2 entité
	 * 
	 * @param e1
	 *            une entite
	 * @param e2
	 *            une entite
	 * @return si il touche ou pas
	 */

	public boolean collision(Entite e1, Entite e2) {
		return e1.co.x < e2.co.x + e2.taille && e1.co.x + e1.taille > e2.co.x && e1.co.y < e2.co.y + e2.taille
				&& e1.co.y + e1.taille > e2.co.y;
	}

	/**
	 * timer du jeu, retire les ennemi si toucher et le lancer de l'oiseau
	 * 
	 */
	public void lancerOiseau() {

		o.lance = true;

		/*try {
			jouerSon("bird.wav");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/

		idDirection = r.nextInt(2) + 1;

		TimerTask task = new TimerTask() {

			@Override
                        // animation du jeu
			public void run() {
				switch (idDirection) {
				case 1:
					o.bouger(2, 0);
					break;
				case 2:
					o.bouger(2, -1);
					break;
				}

				ArrayList<Ennemi> ennemisMorts = new ArrayList<Ennemi>();
				for (Ennemi e : ennemis) {  // regarde si l'oiseau touche un ennemie
					if (collision(o, e)) {
						o = new Oiseau(o.taille);
						elastiqueTire = false;
						this.cancel();
						ennemisMorts.add(e);
						if (nbLancers < 5) {
							nbLancers++;
							lancerOiseau();
						}

					}
				}
				for (Ennemi e : ennemisMorts) { //retire l'ennemie de la liste 
					ennemis.remove(e);
				}
				repaint();

				if (o.co.x > 800) {
					o = new Oiseau(o.taille);
					elastiqueTire = false;
					this.cancel();
					if (nbLancers < 5) {
						nbLancers++;
						lancerOiseau();
					}
				}
			}

		};

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, 0, 10);
	}

        /**
         * lit le son du jeu
         * 
         */
	/*public void jouerSon(String nomFichier)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		URL url = Main.class.getResource(nomFichier);
		final Clip clip = AudioSystem.getClip();
		try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(url)) {
			clip.open(audioIn);
		}
		clip.start();
	}*/
}
