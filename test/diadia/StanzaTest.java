package diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {

	private Stanza stanzaVuota;
	private Stanza stanzaPiena;
	private Attrezzo martello;
	
	@Before
	public void setUp() {
		this.stanzaVuota = new Stanza("Vuota");
		this.stanzaPiena = new Stanza("Piena");
		this.martello = new Attrezzo("Martello", 2);
		
		for (int i=0; i<10; i++) {
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
}
