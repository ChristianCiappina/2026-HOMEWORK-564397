package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando {
	
	@Override
	public void esegui(Partita partita) {
		String nomeAttrezzo = this.getParametro();
		
		Attrezzo a = partita.getStanzaCorrente().removeAttrezzo(nomeAttrezzo);
	    if (a != null) {
	        if (partita.getGiocatore().getBorsa().addAttrezzo(a)) {
	            this.getIo().mostraMessaggio("Hai preso " + nomeAttrezzo);
	        } else {
	            this.getIo().mostraMessaggio("Borsa piena! Rimetti l'attrezzo nella stanza.");
	            partita.getStanzaCorrente().addAttrezzo(a);
	        }
	    } else {
	        this.getIo().mostraMessaggio("Attrezzo non presente in stanza.");
	    }
	}

}
