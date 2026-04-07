package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;

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
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;

	private Giocatore giocatore;
	
	private IOConsole io;
	
	public DiaDia(IOConsole io) {
		this.io = io;
		this.partita = new Partita();
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
	 */
	private boolean processaIstruzione(String istruzione) {
	    if (istruzione == null || istruzione.trim().isEmpty()) {
	        return false;
	    }

	    Scanner scannerDiParole = new Scanner(istruzione);
	    String nomeComando = null;
	    String parametro = null;

	    if (scannerDiParole.hasNext()) {
	        nomeComando = scannerDiParole.next(); // Prima parola: il comando
	    }
	    if (scannerDiParole.hasNext()) {
	        parametro = scannerDiParole.next();   // Seconda parola: il parametro (es. nome attrezzo o direzione)
	    }
	    scannerDiParole.close();

	    if (nomeComando == null) {
	        return false;
	    }

	    // Smistamento dei comandi
	    if (nomeComando.equals("fine")) {
	        this.fine();
	        return true;
	    } else if (nomeComando.equals("vai")) {
	        this.vai(parametro);
	    } else if (nomeComando.equals("aiuto")) {
	        this.aiuto();
	    } else if (nomeComando.equals("prendi")) {
	        this.prendi(parametro);
	    } else if (nomeComando.equals("posa")) {
	        this.posa(parametro);
	    } else {
	        this.io.mostraMessaggio("Comando sconosciuto");
	    }

	    return false;
	}

	private void prendi(String nomeAttrezzo) {
	    Attrezzo a = this.partita.getStanzaCorrente().removeAttrezzo(nomeAttrezzo);
	    if (a != null) {
	        if (this.partita.getGiocatore().getBorsa().addAttrezzo(a)) {
	            this.io.mostraMessaggio("Hai preso " + nomeAttrezzo);
	        } else {
	            this.io.mostraMessaggio("Borsa piena! Rimetti l'attrezzo nella stanza.");
	            this.partita.getStanzaCorrente().addAttrezzo(a);
	        }
	    } else {
	        this.io.mostraMessaggio("Attrezzo non presente in stanza.");
	    }
	}
	
	private void posa(String nomeAttrezzo) {
	    Borsa borsa = this.partita.getGiocatore().getBorsa();
	    Attrezzo a = borsa.getAttrezzo(nomeAttrezzo);
	    
	    if (a != null) {
	        Stanza stanzaCorrente = this.partita.getStanzaCorrente();
	        if (stanzaCorrente.addAttrezzo(a)) {
	            borsa.removeAttrezzo(nomeAttrezzo);
	            this.io.mostraMessaggio("Hai posato: " + nomeAttrezzo);
	        } else {
	            this.io.mostraMessaggio("Non c'è spazio in questa stanza per posare l'oggetto!");
	        }
	    } else {
	        this.io.mostraMessaggio("Non hai questo attrezzo nella borsa.");
	    }
	}
	
	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
	    this.io.mostraMessaggio("Comandi disponibili: vai aiuto prendi posa fine"); 
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
	    if(direzione==null) {
	        this.io.mostraMessaggio("Dove vuoi andare ? Devi specificare una direzione");
	        return;
	    }
	    Stanza prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
	    if (prossimaStanza == null) {
	        this.io.mostraMessaggio("Direzione inesistente");
	    } else {
	        this.partita.setStanzaCorrente(prossimaStanza);
	        int cfu = this.partita.getGiocatore().getCfu();
	        this.partita.getGiocatore().setCfu(cfu - 1);
	    }
	    this.io.mostraMessaggio(this.partita.getStanzaCorrente().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
	    this.io.mostraMessaggio("Grazie di aver giocato!");
	}

	public static void main(String[] args) {
	    IOConsole io = new IOConsole();
	    DiaDia gioco = new DiaDia(io); 
	    gioco.gioca();
	}
}