package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando {
	
	@Override
	public void esegui(Partita partita) {
		this.getIo().mostraMessaggio(partita.getStanzaCorrente().toString());
		this.getIo().mostraMessaggio(partita.toString());
		this.getIo().mostraMessaggio(partita.getGiocatore().getBorsa().toString());
	}

}
