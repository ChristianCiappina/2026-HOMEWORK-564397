package diadia;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPrendi;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPrendiTest {

	private IO io;

	@Before
	public void setUp() {
		this.io = new IOConsole();
	}

	@Test
	public void testComandoPrendiAttrezzoInesistente() {
		Labirinto lab = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.getLabirinto();
		Partita partita = new Partita(lab);
		ComandoPrendi comando = new ComandoPrendi();
		comando.setParametro("Spada");
		comando.setIO(this.io);
		comando.esegui(partita);
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("Spada"));
	}

	@Test
	public void testComandoPrendiBorsaPiena() {
		Labirinto lab = Labirinto.newBuilder()
				.addStanzaIniziale("Armeria")
				.addAttrezzo("Spada", 5)
				.getLabirinto();
		Partita partita = new Partita(lab);
		Borsa borsa = partita.getGiocatore().getBorsa();
		for (int i=0; i<10; i++) {
		    borsa.addAttrezzo(new Attrezzo("Spazzatura" + i, 1));
		}
		ComandoPrendi comando = new ComandoPrendi();
		comando.setParametro("Spada");
		comando.setIO(this.io);
		comando.esegui(partita);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("Spada"));
		assertFalse(borsa.hasAttrezzo("Spada"));
	}

	@Test
	public void testComandoPrendiAttrezzoPresoCorrettamenteDallaStanza() {
		Labirinto lab = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("Spada", 5)
				.getLabirinto();
		Partita partita = new Partita(lab);
		Borsa borsa = partita.getGiocatore().getBorsa();
		ComandoPrendi comando = new ComandoPrendi();
		comando.setParametro("Spada");
		comando.setIO(this.io);
		comando.esegui(partita);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("Spada"));
		assertTrue(borsa.hasAttrezzo("Spada"));
	}
}
