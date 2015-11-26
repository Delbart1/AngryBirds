/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import angrybirds.Jeu;
import angrybirds.Main;
import controller.ControlJeu;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observer;
import javafx.beans.InvalidationListener;
import java.util.Observable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import model.ModeleJeu;

/**
 *
 * @author youdelice
 */
public class VueJeu implements Observer{
    JFrame f;
    JLabel retry;
    public VueJeu(VueEnnemie nbEnnemis, ControlJeu j, ModeleJeu mJ){
        
        final JFrame f = new JFrame("Angry Birds");
                f.setIconImage(new ImageIcon(Main.class.getResource("favicon.png")).getImage());
		f.setPreferredSize(new Dimension(800, 600));
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(null);
        JLabel retry = new JLabel(new ImageIcon(Main.class.getResource("retry.png")));
        retry.setFocusable(false);
	retry.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) { // restart le jeu
				new VueJeu(nbEnnemis);
				f.setContentPane(j);
				f.getContentPane().add(retry);
				f.revalidate();
				f.repaint();
			}

		});

		f.setContentPane(j);
		f.getContentPane().add(retry);
    }

    @Override
    public void update(Observable o, Object arg) {
        
    }


}
