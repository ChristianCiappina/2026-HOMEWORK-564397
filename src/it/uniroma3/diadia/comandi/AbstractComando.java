package it.uniroma3.diadia.comandi;

import java.util.HashSet;
import java.util.Set;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando {
	private String parametro;
	private IO io;
	private static Set<String> comandiDisponibili = new HashSet<>();
	
	public AbstractComando() {
		comandiDisponibili.add(this.getNome());
	}
	
	public static Set<String> getComandiDisponibili() {
		return comandiDisponibili;
	}
	
	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	
	@Override
	public void setIO(IO io) {
		this.io = io;
	}
	
	@Override
	public String getParametro() {
		return this.parametro;
	}
	
	public IO getIo() {
		return this.io;
	}
	
	public abstract void esegui(Partita partita);
	
	@Override
	public String getNome() {
		String nomeClasse = this.getClass().getSimpleName();
		
		return nomeClasse.substring(7).toLowerCase();
	}
}
