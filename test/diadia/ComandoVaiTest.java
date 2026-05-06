package diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.comandi.ComandoVai;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVaiTest {

	private Partita partita;
	private IO io;
	
	@Before
	public void setUp() {
		this.partita = new Partita();
		this.io = new IOConsole();
	}

	@Test
	public void testComandoVaiDirezioneNulla() {
		ComandoVai vai = new ComandoVai();
		vai.setIO(this.io);
		Stanza atrio = new Stanza("Atrio");
		vai.esegui(this.partita);
		assertEquals(atrio.getNome(), this.partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testComandoVaiDirezioneInesistente() {
		ComandoVai vai = new ComandoVai();
		vai.setIO(this.io);
		Stanza atrio = new Stanza("Atrio");
		vai.setParametro("nord-est");
		vai.esegui(this.partita);
		assertEquals(atrio.getNome(), this.partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testComandoVaiDirezioneEsistente() {
		ComandoVai vai = new ComandoVai();
		vai.setIO(this.io);
		Stanza biblioteca = new Stanza("Biblioteca");
		vai.setParametro("nord");
		vai.esegui(this.partita);
		assertEquals(biblioteca.getNome(), this.partita.getStanzaCorrente().getNome());
	}
}
