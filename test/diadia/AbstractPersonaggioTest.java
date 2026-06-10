package diadia;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.personaggi.FakePersonaggio;

public class AbstractPersonaggioTest {

	private FakePersonaggio fakePersonaggio;
	
	@Before
	public void setUp() {
		this.fakePersonaggio = new FakePersonaggio("Personaggio Fake", "Sono un personaggio fake.");
	}
	
	@Test
	public void testAgisciFakePersonaggio() {
		Labirinto lab = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.getLabirinto();
		Partita partita = new Partita(lab);
		assertEquals("done", this.fakePersonaggio.agisci(partita));
	}

}
