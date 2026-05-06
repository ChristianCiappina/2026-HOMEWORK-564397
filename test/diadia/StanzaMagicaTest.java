package diadia;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {

	@Test
	public void testComportamentoStanzaNormale() {
		StanzaMagica stanza = new StanzaMagica("Stanza normale");
		Attrezzo martello = new Attrezzo("Martello", 5);
		stanza.addAttrezzo(martello);
		assertEquals("Martello", stanza.getAttrezzo("Martello").getNome());
		assertEquals(5, stanza.getAttrezzo("Martello").getPeso());
	}

	@Test
	public void testComportamentoStanzaMagicaConSogliaNonDefault() {
		StanzaMagica stanzaMagica = new StanzaMagica("Stanza magica con soglia non default", 2);
		Attrezzo martello = new Attrezzo("Martello", 5);
		Attrezzo spada = new Attrezzo("Spada", 10);
		Attrezzo scudo = new Attrezzo("Scudo", 15);
		stanzaMagica.addAttrezzo(martello);
		stanzaMagica.addAttrezzo(spada);
		stanzaMagica.addAttrezzo(scudo);
		assertEquals("oducS", stanzaMagica.getAttrezzo("oducS").getNome());
		assertEquals(30, stanzaMagica.getAttrezzo("oducS").getPeso());
	}
	
	@Test
	public void testComportamentoStanzaMagicaConSogliaDefault() {
		StanzaMagica stanzaMagica = new StanzaMagica("Stanza magica con soglia default");
		Attrezzo martello = new Attrezzo("Martello", 5);
		Attrezzo spada = new Attrezzo("Spada", 10);
		Attrezzo scudo = new Attrezzo("Scudo", 15);
		Attrezzo armatura = new Attrezzo("Armatura", 10);
		stanzaMagica.addAttrezzo(martello);
		stanzaMagica.addAttrezzo(spada);
		stanzaMagica.addAttrezzo(scudo);
		stanzaMagica.addAttrezzo(armatura);
		assertEquals("arutamrA", stanzaMagica.getAttrezzo("arutamrA").getNome());
		assertEquals(20, stanzaMagica.getAttrezzo("arutamrA").getPeso());
	}
}
