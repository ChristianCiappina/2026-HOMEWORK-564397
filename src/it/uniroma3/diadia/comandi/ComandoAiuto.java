package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {

	private final static String NOME = "aiuto";
	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio("Comandi disponibili: vai aiuto prendi posa fine");
	}

	@Override
	public void setParametro(String parametro) {
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
		return null;
	}

}
