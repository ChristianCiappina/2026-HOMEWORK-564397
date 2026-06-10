package diadia;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.personaggi.Strega;

public class StregaTest {

	private Strega strega;
	
	@Before
	public void setUp() {
		this.strega = new Strega("Strega", "Sono una strega permalosa.");
	}
	
	@Test
	public void testAgisciDopoAverSalutato() {
		Labirinto lab = Labirinto.newBuilder()
				.addStanzaIniziale("Partenza")
				.addStanza("Stanza Piena")
				.addAttrezzo("Spada", 5)
				.addStanza("Stanza Vuota")
				.addAdiacenza("Partenza", "Stanza Piena", "nord")
				.addAdiacenza("Partenza", "Stanza Vuota", "sud")
				.getLabirinto();
		Partita partita = new Partita(lab);
		partita.getStanzaCorrente().setPersonaggio(this.strega);
		this.strega.saluta();
		this.strega.agisci(partita);
		assertEquals("Stanza Piena", partita.getStanzaCorrente().getNome());
	}

	@Test
	public void testAgisciSenzaAverSalutato() {
		Labirinto lab = Labirinto.newBuilder()
				.addStanzaIniziale("Partenza")
				.addStanza("Stanza Piena")
				.addAttrezzo("Spada", 5)
				.addStanza("Stanza Vuota")
				.addAdiacenza("Partenza", "Stanza Piena", "nord")
				.addAdiacenza("Partenza", "Stanza Vuota", "sud")
				.getLabirinto();
		Partita partita = new Partita(lab);
		partita.getStanzaCorrente().setPersonaggio(this.strega);
		this.strega.agisci(partita);
		assertEquals("Stanza Vuota", partita.getStanzaCorrente().getNome());
	}
}
