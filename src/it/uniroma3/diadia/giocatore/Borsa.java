package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.Configurazione;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Map<String, Attrezzo> attrezzi;
	private int pesoMax;
	
	public Borsa() {
		this(Configurazione.getPesoMax());
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<>();
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		int peso = attrezzo.getPeso() + this.getPeso();
		
		if (peso > this.getPesoMax()) {
			return false;
		}
		if (this.attrezzi.containsKey(attrezzo.getNome())) {
			return false;
		}
		else {
			this.attrezzi.put(attrezzo.getNome(), attrezzo);
			return true;
		}
	}
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}
	
	public int getPeso() {
		int pesoTotale = 0;
		for (Attrezzo a : this.attrezzi.values()) {
			pesoTotale += a.getPeso();
		}
		return pesoTotale;
	}
	
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.remove(nomeAttrezzo);
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		List<Attrezzo> contenutoOrdinatoPerPeso = new ArrayList<>(this.attrezzi.values());
		
		Collections.sort(contenutoOrdinatoPerPeso, new ComparatoreAttrezziPerPeso());
		return contenutoOrdinatoPerPeso;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
		SortedSet<Attrezzo> contenutoOrdinatoPerNome = new TreeSet<Attrezzo>(this.attrezzi.values());
		return contenutoOrdinatoPerNome;
	}
	
	public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		Map<Integer, Set<Attrezzo>> contenutoRaggruppatoPerPeso = new HashMap<>();
		Set<Attrezzo> tmp;
		for (Attrezzo attrezzo : this.attrezzi.values()) {
			if (contenutoRaggruppatoPerPeso.containsKey(attrezzo.getPeso())) {
				tmp = contenutoRaggruppatoPerPeso.get(attrezzo.getPeso());
				tmp.add(attrezzo);
			}
			else {
				tmp = new HashSet<>();
				tmp.add(attrezzo);
				contenutoRaggruppatoPerPeso.put(attrezzo.getPeso(), tmp);
			}
		}
		return contenutoRaggruppatoPerPeso;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		SortedSet<Attrezzo> setOrdinatoPerPeso = new TreeSet<>(new ComparatoreAttrezziPerPeso());
		setOrdinatoPerPeso.addAll(this.attrezzi.values());
		return setOrdinatoPerPeso;
	}
	
	public String toString() {
	    StringBuilder s = new StringBuilder();
	    if (!this.isEmpty()) {
	        s.append("Contenuto borsa (" + this.getPeso() + "kg/" + this.getPesoMax() + "kg): ");
	        s.append("\nLista ordinata per peso: " + this.getContenutoOrdinatoPerPeso().toString());
	        s.append("\nSet ordinato per nome: " + this.getContenutoOrdinatoPerNome().toString());
	        s.append("\nMappa raggruppata per peso: " + this.getContenutoRaggruppatoPerPeso().toString());
	    } else {
	        s.append("Borsa vuota");
	    }
	    return s.toString();
	}
}
