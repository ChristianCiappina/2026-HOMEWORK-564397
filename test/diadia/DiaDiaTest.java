package diadia;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.Labirinto;

public class DiaDiaTest {

	@Test
	public void testPartitaBreve() throws Exception {
		List<String> comandiDaEseguire = new ArrayList<>(Arrays.asList("fine"));
		IOSimulator simulatore = new IOSimulator(comandiDaEseguire);
		DiaDia gioco = new DiaDia(simulatore);
		gioco.gioca();
		assertTrue(simulatore.hasMessaggio("Grazie di aver giocato!"));
	}

	@Test
	public void testPartitaVinta() {
		Labirinto lab = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.getLabirinto();
		List<String> listaComandi = new ArrayList<>();
		listaComandi.add("vai nord");
		IOSimulator io = new IOSimulator(listaComandi);
		DiaDia diaDia = new DiaDia(lab, io);
		diaDia.gioca();
		assertTrue(io.hasMessaggio("Hai vinto!"));
	}
	
	@Test
	public void testPartitaCfuEsauriti() {
		Labirinto lab = Labirinto.newBuilder()
	            .addStanzaIniziale("Atrio")
	            .addStanzaVincente("Biblioteca")
	            .addStanza("Palestra")
	            .addAdiacenza("Atrio", "Biblioteca", "nord")
	            .addAdiacenza("Atrio", "Palestra", "sud")
	            .addAdiacenza("Palestra", "Atrio", "nord")
	            .getLabirinto();
	            
	    List<String> comandi = new ArrayList<>();
	    for (int i=0; i<10; i++) {
	        comandi.add("vai sud");
	        comandi.add("vai nord");
	    }
	    
	    IOSimulator io = new IOSimulator(comandi);
	    DiaDia diaDia = new DiaDia(lab, io);
	    diaDia.gioca();
	    assertTrue(io.hasMessaggio("Hai esaurito i CFU..."));
	}
}
