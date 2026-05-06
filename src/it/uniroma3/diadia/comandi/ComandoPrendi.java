package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {

	private final static String NOME = "prendi";
	private String nomeAttrezzo;
	private IO io;
	
	@Override
	public void setIO(IO io) {
		this.io = io;
	}
	
	@Override
	public void esegui(Partita partita) {
		Attrezzo a = partita.getStanzaCorrente().removeAttrezzo(this.nomeAttrezzo);
	    if (a != null) {
	        if (partita.getGiocatore().getBorsa().addAttrezzo(a)) {
	            this.io.mostraMessaggio("Hai preso " + nomeAttrezzo);
	        } else {
	            this.io.mostraMessaggio("Borsa piena! Rimetti l'attrezzo nella stanza.");
	            partita.getStanzaCorrente().addAttrezzo(a);
	        }
	    } else {
	        this.io.mostraMessaggio("Attrezzo non presente in stanza.");
	    }
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}

	@Override
	public String getNome() {
		return NOME;
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

}
