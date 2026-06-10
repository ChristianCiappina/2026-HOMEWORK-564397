package it.uniroma3.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {

	private static final String MESSAGGIO_TRASFERIMENTO_IN_STANZA_PIENA_DI_ATTREZZI = "Ti ho trasferito in una stanza molto fornita!";
	private static final String MESSAGGIO_NESSUNA_STANZA_ADIACENTE = "Non ci sono uscite da qui, sei bloccato!";
	private static final String MESSAGGIO_TRASFERIMENTO_IN_STANZA_CON_MENO_ATTREZZI = "Sei un maleducato! Ti trasferisco in una stanza con pochi o nessun attrezzo!";
	private static final String MESSAGGIO_RISATA = "AHAHAH! Questo è mio ora! AHAHAH!";
	
	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza stanzaDestinazione = null;
		if (!this.haSalutato()) {
			int minAttrezzi = Integer.MAX_VALUE;
			for (Direzione direzione : stanzaCorrente.getDirezioni()) {
				Stanza stanzaAdiacente = stanzaCorrente.getStanzaAdiacente(direzione);
				if (stanzaAdiacente != null && stanzaAdiacente.getAttrezzi().size() < minAttrezzi) {
					stanzaDestinazione = stanzaAdiacente;
					minAttrezzi = stanzaAdiacente.getAttrezzi().size();
				}
			}
			msg = MESSAGGIO_TRASFERIMENTO_IN_STANZA_CON_MENO_ATTREZZI;
		}
		else {
			int maxAttrezzi = -1;
			for (Direzione direzione : stanzaCorrente.getDirezioni()) {
				Stanza stanzaAdiacente = stanzaCorrente.getStanzaAdiacente(direzione);
				if (stanzaAdiacente != null && stanzaAdiacente.getAttrezzi().size() > maxAttrezzi) {
					stanzaDestinazione = stanzaAdiacente;
					maxAttrezzi = stanzaAdiacente.getAttrezzi().size();
				}
			}
			msg = MESSAGGIO_TRASFERIMENTO_IN_STANZA_PIENA_DI_ATTREZZI;
		}
		if (stanzaDestinazione != null) {
			partita.setStanzaCorrente(stanzaDestinazione);
		}
		else {
			msg = MESSAGGIO_NESSUNA_STANZA_ADIACENTE;
		}
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return MESSAGGIO_RISATA;
	}

}
