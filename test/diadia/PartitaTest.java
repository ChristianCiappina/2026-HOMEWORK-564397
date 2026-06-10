package diadia;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.ComandoVai;

public class PartitaTest {

	private IO io;
	
	@Before
	public void setUp() {
		this.io = new IOConsole();
	}
	
	@Test
	public void testIsFinitaVinta() {
		Labirinto lab = Labirinto.newBuilder()
				.addStanzaIniziale("Partenza")
				.addStanzaVincente("Vincente")
				.addAdiacenza("Partenza", "Vincente", "nord")
				.getLabirinto();
		Partita partita = new Partita(lab);
		ComandoVai vai = new ComandoVai();
		vai.setParametro("nord");
		vai.setIO(this.io);
		vai.esegui(partita);

		assertTrue("La partita dovrebbe essere finita se il giocatore si trova nella stanza vincente", 
				partita.isFinita());
	}

	@Test
	public void testIsFinitaCfuTerminati() {
		Labirinto lab = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.getLabirinto();
		Partita partita = new Partita(lab);
		partita.getGiocatore().setCfu(0);

		assertTrue("La partita dovrebbe essere finita se i CFU scendono a 0", 
				partita.isFinita());
	}

	@Test
	public void testIsFinitaAppenaIniziata() {
		Labirinto lab = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.getLabirinto();
		Partita partita = new Partita(lab);
		assertFalse("La partita non dovrebbe risultare finita appena avviata", 
				partita.isFinita());
	}
}