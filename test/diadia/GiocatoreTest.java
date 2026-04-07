package diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.giocatore.Giocatore;

public class GiocatoreTest {
	
	private Giocatore giocatore;
	
	@Before
	public void setUp() {
		this.giocatore = new Giocatore();
	}

	
	@Test
	public void testCfuIniziali() {
		assertEquals(20, giocatore.getCfu());
	}
	
	@Test
	public void testBorsaEsiste() {
		assertNotNull("Il giocatore deve avere una borsa", giocatore.getBorsa());
	}
}
