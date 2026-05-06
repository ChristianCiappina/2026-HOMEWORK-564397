package diadia;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;

public class DiaDiaTest {

	@Test
	public void testPartitaBreve() {
		String[] comandiDaEseguire = {"fine"};
		IOSimulator simulatore = new IOSimulator(comandiDaEseguire);
		DiaDia gioco = new DiaDia(simulatore);
		gioco.gioca();
		assertTrue(simulatore.hasMessaggio("Grazie di aver giocato!"));
	}

}
