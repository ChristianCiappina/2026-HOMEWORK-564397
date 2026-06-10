package it.uniroma3.diadia.ambienti;

import java.io.Reader;
import java.util.Scanner;

public class CaricatoreLabirinto {
	
	private final String NOME_STANZA_INIZIALE = "StanzaIniziale";
	private final String NOME_STANZA_VINCENTE = "StanzaVincente";

	private Scanner scannerDiLinee;
	private Labirinto.LabirintoBuilder builder;

	public CaricatoreLabirinto(Reader reader) {
		this.scannerDiLinee = new Scanner(reader);
		this.builder = Labirinto.newBuilder();
	}

	public void carica() {
		try {
			this.leggiStanze();
			this.leggiStanzeMagiche();
			this.leggiStanzeBuie();
			this.leggiStanzeBloccate();
			this.leggiMaghi();
			this.leggiCani();
			this.leggiStreghe();
			this.leggiEstremi();
			this.leggiAttrezzi();
			this.leggiUscite();
		} finally {
			this.scannerDiLinee.close();
		}
	}

	private void leggiStanze() {
		String riga = this.scannerDiLinee.nextLine();
		if (riga.equals("Stanze:")) {
			while (this.scannerDiLinee.hasNextLine()) {
				riga = this.scannerDiLinee.nextLine();
				if (riga.equals("Stanze Magiche:")) return;
				this.builder.addStanza(riga);
			}
		}
	}

	private void leggiStanzeMagiche() {
		while (this.scannerDiLinee.hasNextLine()) {
			String riga = this.scannerDiLinee.nextLine();
			if (riga.equals("Stanze Buie:")) return;
			
			Scanner scannerDiLinea = new Scanner(riga);
			String nomeStanza = scannerDiLinea.next();
			String sogliaStringa = scannerDiLinea.next();
			int soglia = Integer.parseInt(sogliaStringa);
			this.builder.addStanzaMagica(nomeStanza, soglia);
			scannerDiLinea.close();
		}
	}

	private void leggiStanzeBuie() {
		while (this.scannerDiLinee.hasNextLine()) {
			String riga = this.scannerDiLinee.nextLine();
			if (riga.equals("Stanze Bloccate:")) return;
			
			Scanner scannerDiLinea = new Scanner(riga);
			String nomeStanza = scannerDiLinea.next();
			String attrezzoPerVedere = scannerDiLinea.next();
			this.builder.addStanzaBuia(nomeStanza, attrezzoPerVedere);
			scannerDiLinea.close();
		}
	}

	private void leggiStanzeBloccate() {
		while (this.scannerDiLinee.hasNextLine()) {
			String riga = this.scannerDiLinee.nextLine();
			if (riga.equals("Maghi:")) return;
			
			Scanner scannerDiLinea = new Scanner(riga);
			String nomeStanza = scannerDiLinea.next();
			String direzione = scannerDiLinea.next();
			String attrezzoSbloccante = scannerDiLinea.next();
			this.builder.addStanzaBloccata(nomeStanza, direzione, attrezzoSbloccante);
			scannerDiLinea.close();
		}
	}
	
	private void leggiMaghi() {
		while (this.scannerDiLinee.hasNextLine()) {
			String riga = this.scannerDiLinee.nextLine();
			if (riga.equals("Cani:")) return;

			Scanner scannerDiLinea = new Scanner(riga);
			String nome = scannerDiLinea.next();
			String presentazione = scannerDiLinea.next();
			String nomeAttrezzo = scannerDiLinea.next();
			int pesoAttrezzo = Integer.parseInt(scannerDiLinea.next());
			String nomeStanza = scannerDiLinea.next();
			this.builder.addMago(nome, presentazione, nomeAttrezzo, pesoAttrezzo, nomeStanza);
			scannerDiLinea.close();
		}
	}
	
	private void leggiCani() {
		while (this.scannerDiLinee.hasNextLine()) {
			String riga = this.scannerDiLinee.nextLine();
			if (riga.equals("Streghe:")) return;

			Scanner scannerDiLinea = new Scanner(riga);
			String nome = scannerDiLinea.next();
			String presentazione = scannerDiLinea.next();
			String nomeStanza = scannerDiLinea.next();
			this.builder.addCane(nome, presentazione, nomeStanza);
			scannerDiLinea.close();
		}
	}
	
	private void leggiStreghe() {
		while (this.scannerDiLinee.hasNextLine()) {
			String riga = this.scannerDiLinee.nextLine();
			if (riga.equals("Estremi:")) return;

			Scanner scannerDiLinea = new Scanner(riga);
			String nome = scannerDiLinea.next();
			String presentazione = scannerDiLinea.next();
			String nomeStanza = scannerDiLinea.next();
			this.builder.addStrega(nome, presentazione, nomeStanza);
			scannerDiLinea.close();
		}
	}
	
	private void leggiEstremi() {
		String nomeStanzaIniziale = this.scannerDiLinee.nextLine();
		this.builder.addStanzaIniziale(nomeStanzaIniziale);
		String nomeStanzaVincente = this.scannerDiLinee.nextLine();
		this.builder.addStanzaVincente(nomeStanzaVincente);
	}
	
	private void leggiAttrezzi() {
		String riga = this.scannerDiLinee.nextLine();
		
		if (riga.equals("Attrezzi:")) {
			while (this.scannerDiLinee.hasNextLine()) {
				riga = this.scannerDiLinee.nextLine();
				if (riga.equals("Uscite:")) {
					return;
				}
				
				Scanner scannerDiLinea = new Scanner(riga);
				
				String nomeAttrezzo = scannerDiLinea.next();
				
				String pesoStringa = scannerDiLinea.next();
				int peso = Integer.parseInt(pesoStringa);
				
				String nomeStanza = scannerDiLinea.next();
				
				this.builder.addAttrezzo(nomeAttrezzo, peso, nomeStanza);
				
				scannerDiLinea.close();
			}
		}
	}

	private void leggiUscite() {
		while (this.scannerDiLinee.hasNextLine()) {
			String riga = this.scannerDiLinee.nextLine();
			
			Scanner scannerDiLinea = new Scanner(riga);
			
			String stanzaPartenza = scannerDiLinea.next();
			String direzione = scannerDiLinea.next();
			String stanzaDestinazione = scannerDiLinea.next();
			
			this.builder.addAdiacenza(stanzaPartenza, stanzaDestinazione, direzione);
			
			scannerDiLinea.close();
		}
	}
	
	public Labirinto.LabirintoBuilder getBuilder() {
		return this.builder;
	}
	
}
