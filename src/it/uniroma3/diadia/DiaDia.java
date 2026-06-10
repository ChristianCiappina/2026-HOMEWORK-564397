package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	private Partita partita;

	private IO io;

	public DiaDia(IO io) {
		this.io = io;
		this.partita = new Partita();
	}

	public DiaDia(Labirinto labirinto, IO io) {
		this.io = io;
		this.partita = new Partita(labirinto);
	}

	public void gioca() {
		String istruzione;
		this.io.mostraMessaggio(MESSAGGIO_BENVENUTO); 

		do {
			istruzione = this.io.leggiRiga(); // 
		} while (!processaIstruzione(istruzione));
	}  


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 * @throws Exception 
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva();

		try {
			comandoDaEseguire = factory.costruisciComando(istruzione);
		}
		catch (Exception e) {
			this.io.mostraMessaggio("Ops! C'è stato un problema di sistema con questo comando.");
			return false;
		}

		comandoDaEseguire.setIO(this.io);

		comandoDaEseguire.esegui(this.partita);

		if (this.partita.vinta()) {
			this.io.mostraMessaggio("Hai vinto!");
		}

		if (this.partita.getGiocatore().getCfu() == 0) {
			this.io.mostraMessaggio("Hai esaurito i CFU...");
		}

		return this.partita.isFinita();
	}

	public static void main(String[] args) {

		try (java.util.Scanner scanner = new java.util.Scanner(System.in)) {

			IO io = new IOConsole(scanner);

			try {
				it.uniroma3.diadia.ambienti.CaricatoreLabirinto caricatore = 
						new it.uniroma3.diadia.ambienti.CaricatoreLabirinto(new java.io.FileReader("labirinto.txt"));
				caricatore.carica();

				it.uniroma3.diadia.ambienti.Labirinto labirinto = caricatore.getBuilder().getLabirinto();

				DiaDia gioco = new DiaDia(labirinto, io);
				gioco.gioca();

			} catch (java.io.FileNotFoundException e) {
				io.mostraMessaggio("Errore: Il file 'labirinto.txt' non è stato trovato!");
			}

		}
	}
}