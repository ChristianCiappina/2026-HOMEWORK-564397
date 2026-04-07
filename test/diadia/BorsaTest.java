package diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class BorsaTest {

	private Borsa borsa;
	private Attrezzo attrezzoPesante;
	private Attrezzo piombo;
	
	@Before
	public void setUp() {
		this.borsa = new Borsa(10);
		this.attrezzoPesante = new Attrezzo("incudine", 11);
		this.piombo = new Attrezzo("piombo", 5);
	}
	
	@Test
	public void testAddAttrezzoTroppoPesante() {
		assertFalse("Non dovrebbe aggiungere un attrezzo che eccede il peso max", borsa.addAttrezzo(attrezzoPesante));
	}

	@Test
	public void testGetAttrezzoEsistente() {
		borsa.addAttrezzo(piombo);
		assertEquals(piombo, borsa.getAttrezzo("piombo"));
	}
	
	@Test
	public void testRemoveAttrezzo() {
		borsa.addAttrezzo(piombo);
		assertNotNull("La rimozione di un attrezzo esistente non deve restituire null", borsa.removeAttrezzo("piombo"));
		assertFalse("L'attrezzo non dovrebbe più essere presente", borsa.hasAttrezzo("piombo"));
	}
}
