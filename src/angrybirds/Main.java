package angrybirds;

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

/**
 * le main du jeu
 * 
 * @author youdelice
 */
public class Main {

	private static int nbEnnemis = 5;
	protected static Jeu j;

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		j = new Jeu(nbEnnemis);
		URL url = Main.class.getResource("song.wav");
		final Clip clip = AudioSystem.getClip();
		try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(url)) {
			clip.open(audioIn);
		}

		final JFrame f = new JFrame("Angry Birds");
		f.setIconImage(new ImageIcon(Main.class.getResource("favicon.png")).getImage());
		f.setPreferredSize(new Dimension(800, 600));
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(null);

		final JLabel retry = new JLabel(new ImageIcon(Main.class.getResource("retry.png")));
		retry.setFocusable(false);
		retry.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) { // restart le jeu
				j = new Jeu(nbEnnemis);
				f.setContentPane(j);
				f.getContentPane().add(retry);
				f.revalidate();
				f.repaint();
			}

		});

		f.setContentPane(j);
		f.getContentPane().add(retry);

		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
		});

		f.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_M)
					clip.stop();
			}

		});

		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);

	}

}
