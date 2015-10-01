import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Jeu extends JPanel{

	Oiseau o;
	ArrayList<Ennemi> ennemis = new ArrayList<Ennemi>();
	
	public Jeu(){
		o = new Oiseau(20);
		for (int i = 0; i < 2; i++) {
			ennemis.add(new Ennemi(20));
		}
		
		o.dessiner(getGraphics());
	}

}
