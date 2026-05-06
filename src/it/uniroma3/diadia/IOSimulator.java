package it.uniroma3.diadia;

public class IOSimulator implements IO {

	private String[] righeDaLeggere;
	private int indiceProssimaRiga;
	
	private String[] messaggiStampati;
	private int indiceMessaggiStampati;
	
	public IOSimulator(String[] righeDaLeggere) {
		this.righeDaLeggere = righeDaLeggere;
		this.indiceProssimaRiga = 0;
		this.messaggiStampati = new String[100];
		this.indiceMessaggiStampati = 0;
	}
	
	@Override
	public void mostraMessaggio(String messaggio) {
		if (this.indiceMessaggiStampati < this.messaggiStampati.length) {
			this.messaggiStampati[this.indiceMessaggiStampati] = messaggio;
			this.indiceMessaggiStampati++;
		}
	}

	@Override
	public String leggiRiga() {
		if (this.indiceProssimaRiga < this.righeDaLeggere.length) {
			return this.righeDaLeggere[this.indiceProssimaRiga++];
		}
		return null;
	}

	public String[] getMessaggiStampati() {
		return this.messaggiStampati;
	}
	
	public boolean hasMessaggio(String messaggioCercato) {
		for (String messaggio : this.messaggiStampati) {
			if (messaggio != null && messaggio.equals(messaggioCercato)) {
				return true;
			}
		}
		return false;
	}
}
