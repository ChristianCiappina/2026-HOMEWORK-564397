package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando {

	private static final String MESSAGGIO_CON_CHI = "Chi vuoi salutare? Non c'è nessuno qui.";
	private String messaggio;
	
	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio;
		personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio != null) {
			this.messaggio = personaggio.saluta();
			this.getIo().mostraMessaggio(this.messaggio);
		}
		else {
			this.messaggio = MESSAGGIO_CON_CHI;
			this.getIo().mostraMessaggio(MESSAGGIO_CON_CHI);
		}
	}

	public String getMessaggio() {
		return this.messaggio;
	}
}
