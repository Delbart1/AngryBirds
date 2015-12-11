package angrybirds;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * 
 * Classe creant la fenetre : doit etre lance pour executer le jeu
 * 
 * @author Thibaut
 *
 */

public class MVC {

	public MVC() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		Modele m = new Modele();
		Controller c = new Controller(m);
		Vue v = new Vue(m, c);
	}

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		new MVC();
	}

}
