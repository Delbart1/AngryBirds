package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import angrybirds.Coordonne;

public class TestCoordonne {

	@Test
	public void testAddCoord() {
		Coordonne c1 = new Coordonne(100, 100);
		Coordonne c2 = new Coordonne(50, 50);
		c1.addCoord(c2);
		assertEquals(150, c1.getX());
		assertEquals(150, c1.getY());
	}
	
	@Test
	public void testCompare() {
		Coordonne c1 = new Coordonne(100, 100);
		Coordonne c2 = new Coordonne(50, 50);
		Coordonne c3 = new Coordonne(100, 100);
		assertTrue(c1.compare(c3));
		assertFalse(c1.compare(c2));
	}

}
