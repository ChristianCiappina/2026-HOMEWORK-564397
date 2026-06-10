package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.List;

public class IOSimulator implements IO {

	private List<String> righeDaLeggere;
	private List<ComandoEseguito> cronologia;
	private ComandoEseguito comandoCorrente;
	
	public IOSimulator(List<String> righeDaLeggere) {
		this.righeDaLeggere = righeDaLeggere;
		this.cronologia = new ArrayList<>();
		this.comandoCorrente = null;
	}
	
	@Override
	public void mostraMessaggio(String messaggio) {
		if (this.comandoCorrente != null) {
			this.comandoCorrente.addMessaggio(messaggio);
		}
	}

	@Override
	public String leggiRiga() {
		if (!this.righeDaLeggere.isEmpty()) {
			String rigaLetta = this.righeDaLeggere.remove(0);
			ComandoEseguito comando = new ComandoEseguito(rigaLetta);
			this.comandoCorrente = comando;
			this.cronologia.add(comandoCorrente);
			return rigaLetta;
		}
		else {
			return null;
		}
	}
	
	public boolean hasMessaggio(String messaggioCercato) {
		for (ComandoEseguito comando : this.cronologia) {
			List<String> messaggi = comando.getMessaggi();
			for (String messaggio : messaggi) {
				if (messaggio.equals(messaggioCercato)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private class ComandoEseguito {
		private String comando;
		private List<String> messaggi;
		
		public ComandoEseguito(String comando) {
			this.comando = comando;
			this.messaggi = new ArrayList<>();
		}
		
		public void addMessaggio(String msg) {
			this.messaggi.add(msg);
		}
		
		public String getComando() {
			return this.comando;
		}
		
		public List<String> getMessaggi() {
			return this.messaggi;
		}
	}
}
