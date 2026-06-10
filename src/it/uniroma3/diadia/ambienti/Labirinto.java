package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.personaggi.Cane;
import it.uniroma3.personaggi.Mago;
import it.uniroma3.personaggi.Strega;

public class Labirinto {
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;

	private Labirinto() { }

	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}

	public Stanza getStanzaIniziale() { return stanzaIniziale; }
	public void setStanzaIniziale(Stanza stanzaIniziale) { this.stanzaIniziale = stanzaIniziale; }
	public Stanza getStanzaVincente() { return stanzaVincente; }
	public void setStanzaVincente(Stanza stanzaVincente) { this.stanzaVincente = stanzaVincente; }

	public static class LabirintoBuilder {
		private Labirinto labirinto;
		private Map<String, Stanza> nome2stanza;
		private Stanza ultimaStanzaAggiunta;

		public LabirintoBuilder() {
			this.labirinto = new Labirinto();
			this.nome2stanza = new HashMap<>();
		}

		public LabirintoBuilder addStanzaIniziale(String stanzaIniziale) {
			Stanza s = this.nome2stanza.get(stanzaIniziale);
			if (s == null) {
				s = new Stanza(stanzaIniziale);
				this.aggiungiAMappaEaggiornaUltima(s);
			}
			this.labirinto.setStanzaIniziale(s);
			return this;
		}

		public LabirintoBuilder addStanzaVincente(String stanzaVincente) {
			Stanza s = this.nome2stanza.get(stanzaVincente);
			if (s == null) {
				s = new Stanza(stanzaVincente);
				this.aggiungiAMappaEaggiornaUltima(s);
			}
			this.labirinto.setStanzaVincente(s);
			return this;
		}

		public LabirintoBuilder addStanza(String stanza) {
			Stanza s = new Stanza(stanza);
			this.aggiungiAMappaEaggiornaUltima(s);
			return this;
		}

		private void aggiungiAMappaEaggiornaUltima(Stanza s) {
			this.ultimaStanzaAggiunta = s;
			this.nome2stanza.put(s.getNome(), s);
		}

		public Labirinto getLabirinto() { return this.labirinto; }

		public LabirintoBuilder addAttrezzo(String nome, int peso) {
			Attrezzo attrezzo = new Attrezzo(nome, peso);
			if (this.ultimaStanzaAggiunta != null) this.ultimaStanzaAggiunta.addAttrezzo(attrezzo);
			return this;
		}

		public LabirintoBuilder addAttrezzo(String nome, int peso, String nomeStanza) {
			Attrezzo attrezzo = new Attrezzo(nome, peso);
			Stanza stanza = this.nome2stanza.get(nomeStanza);
			if (stanza != null) stanza.addAttrezzo(attrezzo);
			return this;
		}

		public LabirintoBuilder addAdiacenza(String stanzaCorrente, String stanzaAdiacente, String direzione) {
			Stanza corrente = this.nome2stanza.get(stanzaCorrente);
			Stanza adiacente = this.nome2stanza.get(stanzaAdiacente);
			if (corrente != null && adiacente != null) {
				try {
					Direzione dir = Direzione.valueOf(direzione.toUpperCase());
					corrente.impostaStanzaAdiacente(dir, adiacente);
				} catch (IllegalArgumentException e) {
					
				}
			}
			return this;
		}

		public LabirintoBuilder addStanzaMagica(String nome, int soglia) {
			Stanza stanzaMagica = new StanzaMagica(nome, soglia);
			this.aggiungiAMappaEaggiornaUltima(stanzaMagica);
			return this;
		}

		public LabirintoBuilder addStanzaBuia(String nome, String attrezzoPerVedere) {
			Stanza stanzaBuia = new StanzaBuia(nome, attrezzoPerVedere);
			this.aggiungiAMappaEaggiornaUltima(stanzaBuia);
			return this;
		}

		public LabirintoBuilder addStanzaBloccata(String nome, String direzioneBloccata, String attrezzoSbloccante) {
			Stanza stanzaBloccata = new StanzaBloccata(nome, Direzione.valueOf(direzioneBloccata.toUpperCase()), attrezzoSbloccante);
			this.aggiungiAMappaEaggiornaUltima(stanzaBloccata);
			return this;
		}

		public Map<String, Stanza> getListaStanze() { return this.nome2stanza; }

		public LabirintoBuilder addMago(String nome, String presentazione, String nomeAttrezzo, int pesoAttrezzo, String nomeStanza) {
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, pesoAttrezzo);
			Mago mago = new Mago(nome, presentazione, attrezzo);
			Stanza stanza = this.nome2stanza.get(nomeStanza);
			if (stanza != null) stanza.setPersonaggio(mago);
			return this;
		}

		public LabirintoBuilder addCane(String nome, String presentazione, String nomeStanza) {
			Cane cane = new Cane(nome, presentazione, "osso", new Attrezzo("Collare", 2));
			Stanza stanza = this.nome2stanza.get(nomeStanza);
			if (stanza != null) stanza.setPersonaggio(cane);
			return this;
		}

		public LabirintoBuilder addStrega(String nome, String presentazione, String nomeStanza) {
			Strega strega = new Strega(nome, presentazione);
			Stanza stanza = this.nome2stanza.get(nomeStanza);
			if (stanza != null) stanza.setPersonaggio(strega);
			return this;
		}
	}
}