package diadia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {

	private Stanza stanzaVuota;
	private Stanza stanzaPiena;
	private Attrezzo martello;
	private List<Attrezzo> attrezzi;
	private int numeroAttrezziMax;
	
	@Before
	public void setUp() {
		this.stanzaVuota = new Stanza("Vuota");
		this.stanzaPiena = new Stanza("Piena");
		this.martello = new Attrezzo("Martello", 2);
		this.attrezzi = new ArrayList<>();
		this.numeroAttrezziMax = 10;
		
		
		for (int i = 0; i < this.numeroAttrezziMax; i++) {
			this.stanzaPiena.addAttrezzo(new Attrezzo("attrezzo" + i, 1));
		}
	}
	
	@Test
	public void testAddAttrezzoStanzaVuota() {
		assertTrue("Dovrebbe essere possibile aggiungere un attrezzo a una stanza vuota", stanzaVuota.addAttrezzo(martello));
	}

	@Test
	public void testAddAttrezzoStanzaPiena() {
		assertFalse("Non dovrebbe essere possibile aggiungere oltre il limite massimo", stanzaPiena.addAttrezzo(martello));
	}
	
	@Test
	public void testHasAttrezzoPresente() {
		this.stanzaVuota.addAttrezzo(this.martello);
		assertTrue(this.stanzaVuota.hasAttrezzo("Martello"));
	}
	
	@Test
	public void testHasAttrezzoAssente() {
		assertFalse(this.stanzaVuota.hasAttrezzo("Martello"));
	}
	
	@Test
	public void testGetAttrezzoPresente() {
		this.stanzaVuota.addAttrezzo(this.martello);
		assertEquals(this.martello, this.stanzaVuota.getAttrezzo("Martello"));
	}
	
	@Test
	public void testGetAttrezzoAssente() {
		assertNull(this.stanzaVuota.getAttrezzo("Martello"));
	}
	
	@Test
	public void testRemoveAttrezzoPresente() {
		this.stanzaVuota.addAttrezzo(this.martello);
		assertEquals(this.martello, this.stanzaVuota.removeAttrezzo("Martello"));
		assertEquals(0, this.stanzaVuota.getAttrezzi().size());
	}
	
	@Test
	public void testRemoveAttrezzoAssente() {
		assertNull(this.stanzaVuota.removeAttrezzo("Martello"));
		assertEquals(0, this.stanzaVuota.getAttrezzi().size());
	}
}
