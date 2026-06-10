package diadia;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.personaggi.Cane;

public class CaneTest {

	private Cane cane;
	
	@Before
	public void setUp() {
		this.cane = new Cane("Fido", "Bau Bau", "osso", new Attrezzo("Collare", 2));
	}
	
	@Test
	public void testAgisciDiminuisceCfu() {
		Labirinto lab = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.getLabirinto();
		Partita partita = new Partita(lab);
		int cfuIniziali = partita.getGiocatore().getCfu();
		this.cane.agisci(partita);
		assertEquals(cfuIniziali-1, partita.getGiocatore().getCfu());
	}

}
