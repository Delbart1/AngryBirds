import java.awt.Container;
import java.awt.Dimension;
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

public class Main {

	private static Jeu j = new Jeu(1);
	
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		URL url = Main.class.getResource("song.wav");
		final Clip clip = AudioSystem.getClip();
		try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(url)) {
			clip.open(audioIn);
		}

		JFrame f = new JFrame("Angry Birds");
		f.setIconImage(new ImageIcon(Main.class.getResource("favicon.png")).getImage());
		f.setPreferredSize(new Dimension(800, 600));
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(null);

		JLabel retry = new JLabel(new ImageIcon(Main.class.getResource("retry.png")));
		retry.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				j = new Jeu(1);
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

		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);

	}

}
