package diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {

	private StanzaBloccata stanzaBloccata;
	private Stanza stanzaAdiacente;
	private Attrezzo chiave;
	
	@Before
	public void setUp() {
		this.stanzaAdiacente = new Stanza("Stanza a nord");
		this.stanzaBloccata = new StanzaBloccata("Stanza bloccata", "nord", "chiave");
		this.chiave = new Attrezzo("chiave", 1);
		this.stanzaBloccata.impostaStanzaAdiacente("nord", this.stanzaAdiacente);
	}
	
	@Test
	public void testGetStanzaAdiacenteDirezioneBloccataSenzaChiave() {
		Stanza stanzaRestituita = this.stanzaBloccata.getStanzaAdiacente("nord");
		assertEquals(this.stanzaBloccata, stanzaRestituita);
	}

	@Test
	public void testGetStanzaAdiacenteDirezioneSbloccataConChiave() {
		this.stanzaBloccata.addAttrezzo(this.chiave);
		Stanza stanzaRestituita = this.stanzaBloccata.getStanzaAdiacente("nord");
		assertEquals(this.stanzaAdiacente, stanzaRestituita);
	}
}
