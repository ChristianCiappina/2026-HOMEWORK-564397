package diadia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPosa;
import it.uniroma3.diadia.comandi.ComandoVai;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosaTest {
	
	private IO io;
	
	@Before
	public void setUp() {
		this.io = new IOConsole();
	}
	
	@Test
	public void testComandoPosaAttrezzoInesistente() {
		Labirinto lab = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.getLabirinto();
		Partita partita = new Partita(lab);
		ComandoPosa comando = new ComandoPosa();
		comando.setParametro("Spada");
		comando.setIO(io);
		comando.esegui(partita);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("Spada"));
	}
	
	@Test
	public void testComandoPosaStanzaPiena() {
		Labirinto lab = Labirinto.newBuilder()
				.addStanzaIniziale("Spada")
				.getLabirinto();
		Partita partita = new Partita(lab);
		Attrezzo spada = new Attrezzo("Spada", 5);
		Stanza stanzaCorrente = lab.getStanzaIniziale();
		for (int i=0; i<10; i++) {
			stanzaCorrente.addAttrezzo(new Attrezzo("Spazzatura" + i, 1));
		}
		Borsa borsa = partita.getGiocatore().getBorsa();
		borsa.addAttrezzo(spada);
		ComandoPosa comando = new ComandoPosa();
		comando.setParametro("Spada");
		comando.setIO(this.io);
		comando.esegui(partita);
		assertFalse(stanzaCorrente.hasAttrezzo("Spada"));
		assertTrue(borsa.hasAttrezzo("Spada"));
	}
	
	@Test
	public void testComandoPosaAttrezzoPosatoCorrettamenteNellaStanza() {
		Labirinto lab = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.getLabirinto();
		Partita partita = new Partita(lab);
		Borsa borsa = partita.getGiocatore().getBorsa();
		Attrezzo spada = new Attrezzo("Spada", 5);
		borsa.addAttrezzo(spada);
		ComandoPosa comando = new ComandoPosa();
		comando.setParametro("Spada");
		comando.setIO(this.io);
		comando.esegui(partita);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("Spada"));
		assertFalse(borsa.hasAttrezzo("Spada"));
	}
	
	@Test
	public void testPosaEritrovaAttrezzoInTrilocale() {
	    Labirinto lab = Labirinto.newBuilder()
	            .addStanzaIniziale("Atrio")
	            .addStanza("Corridoio")
	            .addStanza("Salotto")
	            .addAdiacenza("Atrio", "Corridoio", "nord")
	            .addAdiacenza("Corridoio", "Atrio", "sud")
	            .addAdiacenza("Corridoio", "Salotto", "est")
	            .addAdiacenza("Salotto", "Corridoio", "ovest")
	            .getLabirinto();
	            
	    Partita partita = new Partita(lab);
	    
	    Attrezzo osso = new Attrezzo("Osso", 2);
	    partita.getGiocatore().getBorsa().addAttrezzo(osso);
	    
	    ComandoVai vaiNord = new ComandoVai();
	    vaiNord.setParametro("nord");
	    vaiNord.setIO(this.io);
	    vaiNord.esegui(partita);
	    
	    ComandoPosa posa = new ComandoPosa();
	    posa.setParametro("Osso");
	    posa.setIO(this.io);
	    posa.esegui(partita);
	    
	    ComandoVai vaiEst = new ComandoVai();
	    vaiEst.setParametro("est");
	    vaiEst.setIO(this.io);
	    vaiEst.esegui(partita);
	    
	    ComandoVai vaiOvest = new ComandoVai();
	    vaiOvest.setParametro("ovest");
	    vaiOvest.setIO(this.io);
	    vaiOvest.esegui(partita);
	    
	    assertEquals("Corridoio", partita.getStanzaCorrente().getNome());
	    assertTrue(partita.getStanzaCorrente().hasAttrezzo("Osso"));
	}
}
