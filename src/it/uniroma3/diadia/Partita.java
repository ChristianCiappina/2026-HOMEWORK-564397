package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	private Labirinto labirinto;
	private Giocatore giocatore;
	private boolean finita;
	private Stanza stanzaCorrente;	
	
	public Partita() {
		this(Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.getLabirinto());
	}
	
	public Partita(Labirinto labirinto){
		this.labirinto = labirinto;
		this.giocatore = new Giocatore();
		this.finita = false;
		this.stanzaCorrente = this.labirinto.getStanzaIniziale();
	}

	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
		this.stanzaCorrente = this.labirinto.getStanzaIniziale();
	}
	
	public Labirinto getLabirinto() {
		return labirinto;
	}
	
	public Giocatore getGiocatore() {
		return giocatore;
	}
	
	public Stanza getStanzaCorrente() {
		return stanzaCorrente;
	}
	
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.stanzaCorrente == this.labirinto.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return this.finita || this.vinta() || (this.giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	@Override
	public String toString() {
		return "Partita [labirinto=" + this.getLabirinto() + ", giocatore=" + this.getGiocatore() + ", finita=" + this.isFinita()
				+ ", stanzaCorrente=" + this.getStanzaCorrente() + "]";
	}	
	
}
