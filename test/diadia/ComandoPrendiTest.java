package diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.comandi.ComandoPrendi;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPrendiTest {

	private Partita partita;
	private IO io;

	@Before
	public void setUp() {
		this.partita = new Partita();
		this.io = new IOConsole();
	}

	@Test
	public void testComandoPrendiAttrezzoInesistente() {
		ComandoPrendi prendi = new ComandoPrendi();
		prendi.setIO(this.io);
		Attrezzo spada = new Attrezzo("Spada", 5);
		prendi.setParametro("Spada");
		prendi.esegui(this.partita);
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("Spada"));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("Spada"));
	}

	@Test
	public void testComandoPrendiBorsaPiena() {
		ComandoPrendi prendi = new ComandoPrendi();
		prendi.setIO(this.io);
		Attrezzo spada = new Attrezzo("Spada", 5);
		this.partita.getStanzaCorrente().addAttrezzo(spada);
		Borsa borsa = this.partita.getGiocatore().getBorsa();
		for (int i=0; i<10; i++) {
			borsa.addAttrezzo(new Attrezzo("Spazzatura" + i, 1));
		}
		prendi.setParametro("Spada");
		prendi.esegui(this.partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("Spada"));
		assertFalse(borsa.hasAttrezzo("Spada"));
	}

	@Test
	public void testComandoPosaAttrezzoPosatoCorrettamenteNellaStanza() {
		ComandoPrendi prendi = new ComandoPrendi();
		prendi.setIO(this.io);
		Attrezzo spada = new Attrezzo("Spada", 5);
		this.partita.getStanzaCorrente().addAttrezzo(spada);
		Borsa borsa = this.partita.getGiocatore().getBorsa();
		prendi.setParametro("Spada");
		prendi.esegui(this.partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("Spada"));
		assertTrue(borsa.hasAttrezzo("Spada"));
	}
}
