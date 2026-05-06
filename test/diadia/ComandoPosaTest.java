package diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.comandi.ComandoPosa;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosaTest {

	private Partita partita;
	private IO io;
	
	@Before
	public void setUp() {
		this.partita = new Partita();
		this.io = new IOConsole();
	}
	
	@Test
	public void testComandoPosaAttrezzoInesistente() {
		ComandoPosa posa = new ComandoPosa();
		posa.setIO(this.io);
		Attrezzo spada = new Attrezzo("Spada", 5);
		posa.setParametro("Spada");
		posa.esegui(this.partita);
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("Spada"));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("Spada"));
	}
	
	@Test
	public void testComandoPosaStanzaPiena() {
		ComandoPosa posa = new ComandoPosa();
		posa.setIO(this.io);
		Attrezzo spada = new Attrezzo("Spada", 5);
		this.partita.getGiocatore().getBorsa().addAttrezzo(spada);
		Stanza stanzaCorrente = this.partita.getStanzaCorrente();
		for (int i=0; i<10; i++) {
			stanzaCorrente.addAttrezzo(new Attrezzo("Spazzatura" + i, 1));
		}
		posa.setParametro("Spada");
		posa.esegui(this.partita);
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("Spada"));
		assertFalse(stanzaCorrente.hasAttrezzo("Spada"));
	}
	
	@Test
	public void testComandoPosaAttrezzoPosatoCorrettamenteNellaStanza() {
		ComandoPosa posa = new ComandoPosa();
		posa.setIO(this.io);
		Attrezzo spada = new Attrezzo("Spada", 5);
		this.partita.getGiocatore().getBorsa().addAttrezzo(spada);
		Stanza stanzaCorrente = this.partita.getStanzaCorrente();
		posa.setParametro("Spada");
		posa.esegui(this.partita);
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("Spada"));
		assertTrue(stanzaCorrente.hasAttrezzo("Spada"));
	}
}
