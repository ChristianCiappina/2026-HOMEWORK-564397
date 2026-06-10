package diadia;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class BorsaTest {

	private Borsa borsa;
	private Attrezzo attrezzoPesante;
	private Attrezzo piombo;
	
	@Before
	public void setUp() {
		this.borsa = new Borsa(50);
		this.attrezzoPesante = new Attrezzo("incudine", 51);
		this.piombo = new Attrezzo("piombo", 10);
        
	}
	
	@Test
	public void testAddAttrezzoTroppoPesante() {
		assertFalse("Non dovrebbe aggiungere un attrezzo che eccede il peso max", borsa.addAttrezzo(attrezzoPesante));
	}

	@Test
	public void testGetAttrezzoEsistente() {
		borsa.addAttrezzo(piombo);
		assertEquals(piombo, borsa.getAttrezzo("piombo"));
	}
	
	@Test
	public void testRemoveAttrezzo() {
		borsa.addAttrezzo(piombo);
		assertNotNull("La rimozione di un attrezzo esistente non deve restituire null", borsa.removeAttrezzo("piombo"));
		assertFalse("L'attrezzo non dovrebbe più essere presente", borsa.hasAttrezzo("piombo"));
	}
	
	@Test
	public void testGetSortedSetOrdinatoPerPeso_StessiPesiNomiDiversi() {
	    Attrezzo martello = new Attrezzo("martello", 10);
	    this.borsa.addAttrezzo(this.piombo);
	    this.borsa.addAttrezzo(martello);
	    SortedSet<Attrezzo> setOrdinato = borsa.getSortedSetOrdinatoPerPeso();
	    assertEquals(2, setOrdinato.size()); 
	    Iterator<Attrezzo> it = setOrdinato.iterator();
	    assertEquals("martello", it.next().getNome());
	    assertEquals("piombo", it.next().getNome());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPeso() {
		Attrezzo ps = new Attrezzo("ps", 5);
        Attrezzo piuma = new Attrezzo("piuma", 1);
        Attrezzo libro = new Attrezzo("libro", 5);

        this.borsa.addAttrezzo(this.piombo);
        this.borsa.addAttrezzo(ps);
        this.borsa.addAttrezzo(piuma);
        this.borsa.addAttrezzo(libro);
        
        List<Attrezzo> risultato = this.borsa.getContenutoOrdinatoPerPeso();
        
        assertEquals(4, risultato.size());
        assertEquals("piuma", risultato.get(0).getNome());
        assertEquals("libro", risultato.get(1).getNome());
        assertEquals("ps", risultato.get(2).getNome());
        assertEquals("piombo", risultato.get(3).getNome());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerNome() {
		Attrezzo ps = new Attrezzo("ps", 5);
        Attrezzo piuma = new Attrezzo("piuma", 1);
        Attrezzo libro = new Attrezzo("libro", 5);

        this.borsa.addAttrezzo(this.piombo);
        this.borsa.addAttrezzo(ps);
        this.borsa.addAttrezzo(piuma);
        this.borsa.addAttrezzo(libro);
        
        SortedSet<Attrezzo> risultato = this.borsa.getContenutoOrdinatoPerNome();
        Iterator<Attrezzo> itr = risultato.iterator();
        assertEquals(4, risultato.size());
        assertEquals("libro", itr.next().getNome());
        assertEquals("piombo", itr.next().getNome());
        assertEquals("piuma", itr.next().getNome());
        assertEquals("ps", itr.next().getNome());
	}
	
	@Test
	public void testGetContenutoRaggruppatoPerPeso() {
		Attrezzo ps = new Attrezzo("ps", 5);
        Attrezzo piuma = new Attrezzo("piuma", 1);
        Attrezzo libro = new Attrezzo("libro", 5);

        this.borsa.addAttrezzo(this.piombo);
        this.borsa.addAttrezzo(ps);
        this.borsa.addAttrezzo(piuma);
        this.borsa.addAttrezzo(libro);
        
        Map<Integer, Set<Attrezzo>> mappa = this.borsa.getContenutoRaggruppatoPerPeso();

        assertEquals(3, mappa.size());

        Set<Attrezzo> attrezziPeso1 = mappa.get(1);
        assertNotNull(attrezziPeso1);
        assertEquals(1, attrezziPeso1.size());
        assertTrue(attrezziPeso1.contains(piuma));

        Set<Attrezzo> attrezziPeso5 = mappa.get(5);
        assertNotNull(attrezziPeso5);
        assertEquals(2, attrezziPeso5.size());
        assertTrue(attrezziPeso5.contains(libro));
        assertTrue(attrezziPeso5.contains(ps));

        Set<Attrezzo> attrezziPeso10 = mappa.get(10);
        assertNotNull(attrezziPeso10);
        assertEquals(1, attrezziPeso10.size());
        assertTrue(attrezziPeso10.contains(this.piombo));
	}
	
	@Test
	public void testBorsaVuota() {
		List<Attrezzo> listaAttrezzi = this.borsa.getContenutoOrdinatoPerPeso();
		Map<Integer, Set<Attrezzo>> mappaAttrezzi = this.borsa.getContenutoRaggruppatoPerPeso();
		SortedSet<Attrezzo> setAttrezzi = this.borsa.getContenutoOrdinatoPerNome();
		assertEquals(0, listaAttrezzi.size());
		assertEquals(0, mappaAttrezzi.keySet().size());
		assertEquals(0, setAttrezzi.size());
	}
}
