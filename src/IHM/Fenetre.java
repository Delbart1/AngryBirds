package ihm;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import angrybirds.Controller;
import angrybirds.MVC;

@SuppressWarnings("serial")
public class Fenetre extends JFrame {

	Jeu j;

	public Fenetre(Jeu j) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		this.j = j;

		URL url = MVC.class.getResource("song.wav");
		final Clip clip = AudioSystem.getClip();
		try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(url)) {
			clip.open(audioIn);
		}

		setTitle("Angry Birds");
		setIconImage(new ImageIcon(MVC.class.getResource("favicon.png")).getImage());
		setPreferredSize(new Dimension(800, 600));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		setContentPane(j);

		ajouterRetry();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
		});

		addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_M)
					clip.stop();
				else if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
					System.exit(1);
			}

		});

		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public void ajouterRetry() {
		final JLabel retry = new JLabel(new ImageIcon(MVC.class.getResource("retry.png")));
		retry.setFocusable(false);
		retry.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) { // restart le jeu
				j.c.nouveauJeu();
				setContentPane(j);
				getContentPane().add(retry);
				revalidate();
				repaint();
			}

		});

		getContentPane().add(retry);
	}
}
