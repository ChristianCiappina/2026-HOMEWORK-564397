package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
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

public class ComandoVai extends AbstractComando {

    @Override
    public void esegui(Partita partita) {
    	String parametro = this.getParametro();
    	
    	if (parametro == null) {
    		this.getIo().mostraMessaggio("Dove vuoi andare ? Devi specificare una direzione");
    		return;
    	}
    	
    	Direzione direzione;
    	
    	try {
    		direzione = Direzione.valueOf(parametro.toUpperCase());
    	} catch (IllegalArgumentException e) {
    		this.getIo().mostraMessaggio("Direzione inesistente");
    		return;
    	}
    	
    	Stanza stanzaCorrente = partita.getStanzaCorrente();
    	Stanza prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);;
    	
    	if (prossimaStanza == null) {
    		this.getIo().mostraMessaggio("Direzione inesistente");
    		return;
    	}
    	
    	partita.setStanzaCorrente(prossimaStanza);
    	this.getIo().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
    	partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
    }

}