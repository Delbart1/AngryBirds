import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		JFrame f = new JFrame("Angry Birds");
		f.setIconImage(new ImageIcon(Main.class.getResource("favicon.png")).getImage());
		f.setPreferredSize(new Dimension(800, 600));
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.setContentPane(new Jeu(1));
		
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);

	}

}
