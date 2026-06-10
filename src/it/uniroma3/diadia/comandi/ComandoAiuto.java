package it.uniroma3.diadia.comandi;

import java.util.ServiceLoader;
import java.util.Set;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {
	
	@Override
	public void esegui(Partita partita) {
		for (Comando c: ServiceLoader.load(Comando.class)) {
			
		}
		Set<String> elenco = AbstractComando.getComandiDisponibili();
		StringBuilder output = new StringBuilder("Comandi disponibili: ");
		for (String nomeComando : elenco) {
			output.append(nomeComando).append(" ");
		}
		this.getIo().mostraMessaggio(output.toString());
	}

}
