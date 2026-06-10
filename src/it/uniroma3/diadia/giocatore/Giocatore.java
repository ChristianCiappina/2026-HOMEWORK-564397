package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Configurazione;

public class Giocatore {
    private int cfu;
    private Borsa borsa;

    public Giocatore() {
		this.cfu = Configurazione.getCFU();
		this.borsa = new Borsa(Configurazione.getPesoMax());
	}

    public int getCfu() {
        return cfu;
    }

    public void setCfu(int cfu) {
        this.cfu = cfu;
    }

    public Borsa getBorsa() {
        return borsa;
    }
}
