package angrybirds;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import ihm.Fenetre;
import ihm.Jeu;

public class Vue implements Observer {

	Modele m;
	Controller c;

	Jeu jeu;
	Fenetre f;

	public Vue(Modele m, Controller c) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		this.m = m;
		this.c = c;

		jeu = new Jeu(m, c);
		f = new Fenetre(jeu);
		
		m.addObserver(this);
	}

	public void update(Observable arg0, Object arg1) {
		jeu.repaint();
	}

}
