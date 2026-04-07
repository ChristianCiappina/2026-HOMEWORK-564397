package diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;

public class LabirintoTest {

	private Labirinto labirinto;
	
	@Before
	public void setUp() {
		this.labirinto = new Labirinto();
	}

	@Test
	public void testGetStanzaIniziale() {
		assertNotNull(labirinto.getStanzaIniziale());
		assertEquals("Atrio", labirinto.getStanzaIniziale().getNome());
	}
	
	@Test
	public void testGetStanzaVincente() {
		assertNotNull(labirinto.getStanzaVincente());
		assertEquals("Biblioteca", labirinto.getStanzaVincente().getNome());
	}
}
