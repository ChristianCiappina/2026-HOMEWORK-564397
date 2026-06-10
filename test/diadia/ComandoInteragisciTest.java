package diadia;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoInteragisci;
import it.uniroma3.personaggi.Cane;

public class ComandoInteragisciTest {

	private ComandoInteragisci comandoInteragisci;
	
	@Before
	public void setUp() {
		this.comandoInteragisci = new ComandoInteragisci();
		it.uniroma3.diadia.IO io = new it.uniroma3.diadia.IOConsole();
		this.comandoInteragisci.setIO(io);
			
	}
	
	@Test
	public void testEseguiConPersonaggio() {
		Labirinto lab = Labirinto.newBuilder()
				.addStanzaIniziale("Partenza")
				.getLabirinto();
		Partita partita = new Partita(lab);
		Cane cane = new Cane("Fido", "Bau Bau", "osso", new Attrezzo("Collare", 2));
		partita.getStanzaCorrente().setPersonaggio(cane);
		this.comandoInteragisci.esegui(partita);
		assertEquals("Bau... ti ho morso! Hai perso un CFU!", this.comandoInteragisci.getMessaggio());
	}

	@Test
	public void testEseguiSenzaPersonaggio() {
		Labirinto lab = Labirinto.newBuilder()
				.addStanzaIniziale("Partenza")
				.getLabirinto();
		Partita partita = new Partita(lab);
		this.comandoInteragisci.esegui(partita);
		assertEquals("Con chi dovrei interagire?...", this.comandoInteragisci.getMessaggio());
	}
}
