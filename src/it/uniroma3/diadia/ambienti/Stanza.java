package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
*/

public class Stanza {
	
	private static final int NUMERO_MASSIMO_ATTREZZI = 10;
	private String descrizione;
	private List<Attrezzo> attrezzi;
	private Map<Direzione, Stanza> stanzeAdiacenti;
	private String nome;
	private AbstractPersonaggio personaggio;
    
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public Stanza(String nome) {
        this.stanzeAdiacenti = new HashMap<>();
        this.attrezzi = new ArrayList<>();
        this.descrizione = descrizione;
        this.nome = nome;
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(Direzione direzione, Stanza stanzaAdiacente) {
    	this.stanzeAdiacenti.put(direzione, stanzaAdiacente);
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public Stanza getStanzaAdiacente(Direzione direzione) {
        return this.stanzeAdiacenti.get(direzione);
	}

    /**
     * Restituisce la nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public List<Attrezzo> getAttrezzi() {
        return this.attrezzi; 
    }
    
    public void setPersonaggio(AbstractPersonaggio personaggio) {
    	this.personaggio = personaggio;
    }
    
    public AbstractPersonaggio getPersonaggio( ) {
    	return this.personaggio;
    }

    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
        if (attrezzo != null && this.attrezzi.size() < this.NUMERO_MASSIMO_ATTREZZI) {
        	if (this.hasAttrezzo(attrezzo.getNome())) {
        		return false;
        	}
        	return this.attrezzi.add(attrezzo);
        }
        return false;
    }

   /**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite: ");
    	for (Direzione direzione : this.stanzeAdiacenti.keySet())
    		if (direzione!=null)
    			risultato.append(" " + direzione);
    	risultato.append("\nAttrezzi nella stanza: ");
    	for (Attrezzo attrezzo : this.attrezzi) {
    		if (attrezzo != null) {
    			risultato.append(attrezzo.toString()+" ");
    		}
    	}
    	return risultato.toString();
    }

    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoCercato = new Attrezzo(nomeAttrezzo, 0);
		return this.attrezzi.contains(attrezzoCercato);
	}

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoFittizio = new Attrezzo(nomeAttrezzo, 0);
		int indice = this.attrezzi.indexOf(attrezzoFittizio);
		if (indice != -1) {
			return this.attrezzi.get(indice);
		}
		return null;
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoDaRimuovere = this.getAttrezzo(nomeAttrezzo);
		if (attrezzoDaRimuovere != null) {
			this.attrezzi.remove(attrezzoDaRimuovere);
		}
		return attrezzoDaRimuovere;
	}


	public Set<Direzione> getDirezioni() {
		return this.stanzeAdiacenti.keySet();
    }

	public Map<Direzione, Stanza> getMapStanzeAdiacenti() {
		return this.stanzeAdiacenti;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof Stanza)) {
			return false;
		}
		Stanza that = (Stanza)o;
		return this.getNome().equals(that.getNome());
	}
	
	@Override
	public int hashCode() {
		return this.getClass().hashCode() + this.getNome().hashCode();
	}
}