package diadia;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.ComandoVai;

public class ComandoVaiTest {

	private IO io;
	
	@Before
	public void setUp() {
		this.io = new IOConsole();
	}

	@Test
	public void testComandoVaiDirezioneNulla() {
		Labirinto lab = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.getLabirinto();
		Partita partita = new Partita(lab);
		ComandoVai comando = new ComandoVai();
		comando.setParametro(null);
		comando.setIO(this.io);
		comando.esegui(partita);
		assertEquals("Atrio", partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testComandoVaiDirezioneInesistente() {
		Labirinto lab = Labirinto.newBuilder()
				.addStanzaIniziale("Sgabuzzino")
				.getLabirinto();
		Partita partita = new Partita(lab);
		ComandoVai comando = new ComandoVai();
		comando.setParametro("nord");
		comando.setIO(this.io);
		comando.esegui(partita);
		assertEquals("Sgabuzzino", partita.getStanzaCorrente().getNome());
		assertEquals(20, partita.getGiocatore().getCfu());
	}
	
	@Test
	public void testComandoVaiDirezioneEsistente() {
		Labirinto lab = Labirinto.newBuilder()
				.addStanzaIniziale("Salotto")
				.addStanza("Cucina")
				.addAdiacenza("Salotto", "Cucina", "sud")
				.getLabirinto();
		Partita partita = new Partita(lab);
		ComandoVai comando = new ComandoVai();
		comando.setParametro("sud");
		comando.setIO(this.io);
		comando.esegui(partita);
		assertEquals("Cucina", partita.getStanzaCorrente().getNome());
		assertEquals(19, partita.getGiocatore().getCfu());
	}
}
