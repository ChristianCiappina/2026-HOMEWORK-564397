package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando {
	
	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio;
		personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio != null) {
			String nomeAttrezzo = this.getParametro();
			Attrezzo attrezzo = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
			if (attrezzo != null) {
				partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
				String risposta = personaggio.riceviRegalo(attrezzo, partita);
				this.getIo().mostraMessaggio(risposta);
			}
			else {
				this.getIo().mostraMessaggio("Non hai un attrezzo con questo nome nella borsa!");
			}
		}
		else {
			this.getIo().mostraMessaggio("Non c'è nessuno a cui regalare qualcosa qui.");
		}
	}

}
