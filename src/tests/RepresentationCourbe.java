package tests;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import angrybirds.Coordonne;
import angrybirds.Courbe;

@SuppressWarnings("serial")
public class RepresentationCourbe extends JFrame{
	
	ArrayList<Point> points = new ArrayList<>();

	Courbe c = new Courbe(null);
	
	public RepresentationCourbe(){
		setPreferredSize(new Dimension(1000, 800));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		c.updateCoordMilieu(300);
		c.updateCoordFin(new Coordonne(350, 600));

		for (double i = 0.0; i < 1.0; i += 0.005) {
			Coordonne co = c.coordSuivante(i);
			Point point = new Point(co);
			points.add(point);
		}

		addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
					System.exit(1);
			}

		});

		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}
	
	public void paint(Graphics g){
		g.setColor(Color.black);
		g.drawRect(0, 0, 800, 600);
		
		for(Point p : points){
			p.paint(g);
		}
		g.setColor(Color.blue);
		int index = 1; 
		for(Coordonne co : c.pointsBezier){
			g.fillOval(co.x, co.y, 10, 10);
			g.drawString("" + index, co.x - 15, co.y - 15);
			index++;
		}
	}
	
	public static void main(String[] args) {
		new RepresentationCourbe();
	}

}
