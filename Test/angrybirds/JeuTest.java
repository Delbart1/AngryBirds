/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package angrybirds;

import java.awt.Graphics;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author youdelice
 */
public class JeuTest {

    @Test
    public void testplacemntobstacle() {
        Jeu j = new Jeu(10);
        assertEquals(10, j.ennemis.size());
    }
    
    @Test
    public void testcollision(){
        Jeu j = new Jeu(1);
        Ennemi e = new Ennemi(new Coordonne(125, 400), 10);
        Ennemi e2 = new Ennemi(new Coordonne(200, 200), 10);
        j.ennemis.add(e);
        j.ennemis.add(e2);
        Oiseau o = new Oiseau(10);
        assertEquals(j.collision(o, e),true);
        assertEquals(j.collision(o, e2), false);
    }
    
    @Test
    public void testmouvement(){
        int i = 0;
        Jeu j = new Jeu(0);
        Ennemi e = new Ennemi(new Coordonne(150, 400), 10);
        j.ennemis.add(e);
        Oiseau o = new Oiseau(10);
        while(!j.collision(o, e)){
        o.bouger(5, 0);
        i++;
        }
        assertEquals(5, i);
}
    
    
    }