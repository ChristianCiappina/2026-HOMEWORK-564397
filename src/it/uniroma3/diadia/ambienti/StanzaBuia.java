package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {

	private String attrezzoPerVedere;
	
	public StanzaBuia(String nome, String attrezzoPerVedere) {
		super(nome);
		this.attrezzoPerVedere = attrezzoPerVedere;
	}

	@Override
	public String getDescrizione() {
		if (this.hasAttrezzo(this.attrezzoPerVedere)) {
			return super.getDescrizione();
		}
		else {
			return "Qui c'è un buio pesto";
		}
	}
}
