package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosa implements Comando {

	private final static String NOME = "posa";
	private String nomeAttrezzo;
	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		Borsa borsa = partita.getGiocatore().getBorsa();
	    Attrezzo a = borsa.getAttrezzo(nomeAttrezzo);
	    
	    if (a != null) {
	        Stanza stanzaCorrente = partita.getStanzaCorrente();
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

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
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
		return this.nomeAttrezzo;
	}

}
