package diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {

	private StanzaBuia stanzaBuia;
	private Attrezzo lanterna;
	
	@Before
	public void setUp() {
		this.stanzaBuia = new StanzaBuia("Cantina", "Lanterna");
		this.lanterna = new Attrezzo("Lanterna", 1);
	}
	
	@Test
	public void testGetDescrizioneSenzaAttrezzoLuminoso() {
		String descrizione = this.stanzaBuia.getDescrizione();
		assertEquals("Qui c'è un buio pesto", descrizione);
	}

	@Test
	public void testGetDescrizioneConAttrezzoLuminoso() {
		this.stanzaBuia.addAttrezzo(this.lanterna);
		String descrizione = this.stanzaBuia.getDescrizione();
		assertNotEquals("Qui c'è un buio pesto", descrizione);
	}
	
	@Test
	public void testGetDescrizioneConAttrezzoNonLuminoso() {
		this.stanzaBuia.addAttrezzo(new Attrezzo("Osso", 3));
		assertEquals("Qui c'è un buio pesto", this.stanzaBuia.getDescrizione());
	}
}
