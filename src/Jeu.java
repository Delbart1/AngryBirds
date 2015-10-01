import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Jeu extends JPanel{

	Oiseau o = new Oiseau(20);
	ArrayList<Ennemi> ennemis = new ArrayList<Ennemi>();
	
	public Jeu(){
		for (int i = 0; i < 10; i++) {
			ennemis.add(new Ennemi(20));
		}
	}

    
    public void paintComponent(Graphics g) {
        g.setColor(o.couleurPrincipale);
        g.fillOval(o.co.x,o.co.y, 2*o.taille, 2*o.taille);
        g.setColor(o.couleurSecondaire);
        g.fillOval(o.co.x,o.co.y+o.taille, 2*o.taille, o.taille);
        
        for(Ennemi e : ennemis){
            g.setColor(e.couleurPrincipale);
            g.fillOval(e.co.x,e.co.y, 2*e.taille, 2*e.taille);
            g.setColor(e.couleurSecondaire);
            g.fillOval(e.co.x,e.co.y+e.taille, 2*e.taille, e.taille);
        }
    }
	
}
