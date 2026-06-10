package diadia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.personaggi.Mago;

public class MagoTest {

	private Mago mago;
	
	@Test
	public void testAgisciMagoConAttrezzo() {
		Labirinto lab = Labirinto.newBuilder()
				.addStanzaIniziale("Partenza")
				.getLabirinto();
		Partita partita = new Partita(lab);
		Attrezzo attrezzo = new Attrezzo("Spada", 5);
		this.mago = new Mago("Mago", "Sono un mago", attrezzo);
		partita.getStanzaCorrente().setPersonaggio(this.mago);
		this.mago.agisci(partita);
		assertEquals(1, partita.getStanzaCorrente().getAttrezzi().size());
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("Spada"));
	}

	@Test
	public void testAgisciMagoSenzaAttrezzo() {
		Labirinto lab = Labirinto.newBuilder()
				.addStanzaIniziale("Partenza")
				.getLabirinto();
		Partita partita = new Partita(lab);
		this.mago = new Mago("Mago", "Sono un mago", null);
		partita.getStanzaCorrente().setPersonaggio(this.mago);
		this.mago.agisci(partita);
		assertEquals(0, partita.getStanzaCorrente().getAttrezzi().size());
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("Spada"));
	}
}
