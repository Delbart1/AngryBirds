package tests;

import java.awt.Color;
import java.awt.Graphics;

import angrybirds.Coordonne;

public class Point{

	Coordonne co;
	
	public Point(Coordonne co){
		this.co = co;
	}
	
	public void paint(Graphics g){
		g.setColor(Color.red);
		g.fillRect(co.x, co.y, 2, 2);
	}
	
}
