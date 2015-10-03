import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import angryBirds.Ennemi;
import angryBirds.Oiseau;


public class Jeu extends JPanel{

	Oiseau o = new Oiseau(20);
	ArrayList<Ennemi> ennemis = new ArrayList<Ennemi>();
	
	public Jeu(){
		for (int i = 0; i < 5; i++) {
			ennemis.add(new Ennemi(20));
		}
	}

    
    public void paintComponent(Graphics g) {
        g.setColor(o.couleurPrincipale);
        g.fillOval(o.co.x,o.co.y, 2*o.taille, 2*o.taille);
        g.setColor(o.couleurSecondaire);
        g.fillOval(o.co.x,o.co.y+o.taille, 2*o.taille, o.taille);
        g.setColor(Color.WHITE);
        g.fillPolygon(o.px, o.py, 3);
        
        for(Ennemi e : ennemis){
            g.setColor(e.couleurPrincipale);
            g.fillOval(e.co.x,e.co.y, 2*e.taille, 2*e.taille);
            g.setColor(e.couleurSecondaire);
            g.fillOval(e.co.x,e.co.y+e.taille, 2*e.taille, e.taille);
        }
    }
	
    public int square(int a){
    	return a*a;
    }
    public boolean collision(Oiseau o, Ennemi e){
		return (Math.sqrt(square(e.co.y-o.co.y)+square(e.co.x-o.co.x))<=(o.taille+e.taille));   	
    }
}
