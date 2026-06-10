package it.uniroma3.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {

	private static final String MESSAGGIO_MORSO = "Bau... ti ho morso! Hai perso un CFU!";
	private static final String MESSAGGIO_REGALO_RICEVUTO = "Arf arf! Gnam!";
	private String ciboPreferito;
	private Attrezzo attrezzo;
	
	public Cane(String nome, String presentazione, String ciboPreferito, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.ciboPreferito = ciboPreferito;
		this.attrezzo = attrezzo;
	}

	@Override
	public String agisci(Partita partita) {
		int cfu = partita.getGiocatore().getCfu();
		partita.getGiocatore().setCfu(cfu-1);
		return MESSAGGIO_MORSO;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if (attrezzo.getNome().equals(this.ciboPreferito)) {
			Stanza stanzaCorrente = partita.getStanzaCorrente();
			stanzaCorrente.addAttrezzo(this.attrezzo);
			return MESSAGGIO_REGALO_RICEVUTO;
		}
		else {
			return this.agisci(partita);
		}
	}

}
