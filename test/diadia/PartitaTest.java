package diadia;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class PartitaTest {

	private Partita partita;

	@Before
	public void setUp() {
		// Inizializza una nuova partita prima di ogni test
		this.partita = new Partita(); 
	}

	@Test
	public void testIsFinitaVinta() {
		Stanza vincente = this.partita.getLabirinto().getStanzaVincente();

		this.partita.setStanzaCorrente(vincente);

		assertTrue("La partita dovrebbe essere finita se il giocatore si trova nella stanza vincente", 
				this.partita.isFinita());
	}

	@Test
	public void testIsFinitaCfuTerminati() {
		this.partita.getGiocatore().setCfu(0);

		assertTrue("La partita dovrebbe essere finita se i CFU scendono a 0", 
				this.partita.isFinita());
	}

	@Test
	public void testIsFinitaAppenaIniziata() {
		assertFalse("La partita non dovrebbe risultare finita appena avviata", 
				this.partita.isFinita());
	}
}