package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {

	private Direzione direzioneBloccata;
	private String attrezzoSbloccante;
	
	public StanzaBloccata(String nome, Direzione direzioneBloccata, String attrezzoSbloccante) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoSbloccante = attrezzoSbloccante;
	}

	@Override
	public Stanza getStanzaAdiacente(Direzione dir) {
		if (dir.equals(this.direzioneBloccata) && !this.hasAttrezzo(this.attrezzoSbloccante)) {
			return this;
		}
		return super.getStanzaAdiacente(dir);
	}
	
	@Override
	public String getDescrizione() {
		if (this.hasAttrezzo(this.attrezzoSbloccante)) {
			return super.getDescrizione();
		}
		else {
			return "Qui la stanza è bloccata";
		}
	}
}
