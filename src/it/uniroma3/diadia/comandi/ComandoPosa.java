package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosa extends AbstractComando {
	
	@Override
	public void esegui(Partita partita) {
		String nomeAttrezzo = this.getParametro();
		
		Borsa borsa = partita.getGiocatore().getBorsa();
	    Attrezzo a = borsa.getAttrezzo(nomeAttrezzo);
	    
	    if (a != null) {
	        Stanza stanzaCorrente = partita.getStanzaCorrente();
	        if (stanzaCorrente.addAttrezzo(a)) {
	            borsa.removeAttrezzo(nomeAttrezzo);
	            this.getIo().mostraMessaggio("Hai posato: " + nomeAttrezzo);
	        } else {
	            this.getIo().mostraMessaggio("Non c'è spazio in questa stanza per posare l'oggetto!");
	        }
	    } else {
	        this.getIo().mostraMessaggio("Non hai questo attrezzo nella borsa.");
	    }
	}

}
