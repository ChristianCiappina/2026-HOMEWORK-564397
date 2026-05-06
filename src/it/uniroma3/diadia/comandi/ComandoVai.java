package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Questa classe modella un comando.
 * Un comando consiste al piu' di due parole:
 * il nome del comando ed un parametro
 * su cui si applica il comando.
 * (Ad es. alla riga digitata dall'utente "vai nord"
 *  corrisponde un comando di nome "vai" e parametro "nord").
 *
 * @author  docente di POO
 * @version base
 */

public class ComandoVai implements Comando {

	private final static String NOME = "vai";
    private String direzione;
    private IO io;

    @Override
    public void esegui(Partita partita) {
    	Stanza stanzaCorrente = partita.getStanzaCorrente();
    	Stanza prossimaStanza = null;
    	
    	if (this.direzione == null) {
    		this.io.mostraMessaggio("Dove vuoi andare ? Devi specificare una direzione");
    		return;
    	}
    	
    	prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
    	
    	if (prossimaStanza == null) {
    		this.io.mostraMessaggio("Direzione inesistente");
    		return;
    	}
    	
    	partita.setStanzaCorrente(prossimaStanza);
    	this.io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
    	partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
    }
    
    @Override
    public void setParametro(String parametro) {
    	this.direzione = parametro;
    }

	@Override
	public void setIO(IO io) {
		this.io = io;
	}

	@Override
	public String getNome() {
		return NOME;
	}

	@Override
	public String getParametro() {
		return this.direzione;
	}
}