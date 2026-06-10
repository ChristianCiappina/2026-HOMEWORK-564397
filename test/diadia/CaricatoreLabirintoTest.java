package diadia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import java.util.Map;

import org.junit.Test;

import it.uniroma3.diadia.ambienti.CaricatoreLabirinto;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.personaggi.Cane;
import it.uniroma3.personaggi.Mago;
import it.uniroma3.personaggi.Strega;

public class CaricatoreLabirintoTest {

	private final String monolocale = 
			"Stanze:\n" +
			"N10\n" +
			"Stanze Magiche:\n" +
			"Stanze Buie:\n" +
			"Stanze Bloccate:\n" +
			"Maghi:\n" +
			"Cani:\n" +
			"Streghe:\n" +
			"Estremi:\n" +
			"N10\n" +
			"N10\n" +
			"Attrezzi:\n" +
			"Uscite:\n";

	@Test
	public void testCaricatoreMonolocale() throws Exception {
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(monolocale));
		caricatore.carica();
		Labirinto labirinto = caricatore.getBuilder().getLabirinto();
		
		assertEquals("N10", labirinto.getStanzaIniziale().getNome());
		assertEquals("N10", labirinto.getStanzaVincente().getNome());
	}

	@Test
	public void testCaricatoreConAttrezzo() throws Exception {
		String monolocaleConAttrezzo = 
				"Stanze:\n" +
				"N10\n" +
				"Stanze Magiche:\n" +
				"Stanze Buie:\n" +
				"Stanze Bloccate:\n" +
				"Maghi:\n" +
				"Cani:\n" +
				"Streghe:\n" +
				"Estremi:\n" +
				"N10\n" +
				"N10\n" +
				"Attrezzi:\n" +
				"Osso 5 N10\n" + 
				"Uscite:\n";

		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(monolocaleConAttrezzo));
		caricatore.carica();
		Stanza n10 = caricatore.getBuilder().getListaStanze().get("N10");
		
		assertTrue(n10.hasAttrezzo("Osso"));
		assertEquals(5, n10.getAttrezzo("Osso").getPeso());
	}

	@Test
	public void testCaricatoreBilocale() throws Exception {
		String bilocale = 
				"Stanze:\n" +
				"N10\n" +
				"Biblioteca\n" +
				"Stanze Magiche:\n" +
				"Stanze Buie:\n" +
				"Stanze Bloccate:\n" +
				"Maghi:\n" +
				"Cani:\n" +
				"Streghe:\n" +
				"Estremi:\n" +
				"N10\n" +
				"Biblioteca\n" +
				"Attrezzi:\n" +
				"Uscite:\n" +
				"N10 nord Biblioteca\n" +
				"Biblioteca sud N10\n";

		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(bilocale));
		caricatore.carica();
		Map<String, Stanza> mappaStanze = caricatore.getBuilder().getListaStanze();
		Stanza n10 = mappaStanze.get("N10");
		Stanza biblioteca = mappaStanze.get("Biblioteca");
		
		assertEquals("Biblioteca", n10.getStanzaAdiacente(Direzione.NORD).getNome());
		assertEquals("N10", biblioteca.getStanzaAdiacente(Direzione.SUD).getNome());
	}

	@Test
	public void testCaricatoreConStanzaBuia() throws Exception {
		String labirintoConStanzaBuia = 
				"Stanze:\n" +
				"N10\n" +
				"Stanze Magiche:\n" +
				"Stanze Buie:\n" +
				"Cantina lanterna\n" +
				"Stanze Bloccate:\n" +
				"Maghi:\n" +
				"Cani:\n" +
				"Streghe:\n" +
				"Estremi:\n" +
				"N10\n" +
				"Cantina\n" +
				"Attrezzi:\n" +
				"Uscite:\n" +
				"N10 nord Cantina\n";

		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(labirintoConStanzaBuia));
		caricatore.carica();
		Stanza cantina = caricatore.getBuilder().getListaStanze().get("Cantina");
		
		assertEquals(StanzaBuia.class, cantina.getClass());
	}

	@Test
	public void testCaricatoreConStanzaMagica() throws Exception {
		String labirintoConStanzaMagica = 
				"Stanze:\n" +
				"N10\n" +
				"Stanze Magiche:\n" +
				"Laboratorio 3\n" +
				"Stanze Buie:\n" +
				"Stanze Bloccate:\n" +
				"Maghi:\n" +
				"Cani:\n" +
				"Streghe:\n" +
				"Estremi:\n" +
				"N10\n" +
				"Laboratorio\n" +
				"Attrezzi:\n" +
				"Uscite:\n";

		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(labirintoConStanzaMagica));
		caricatore.carica();
		Stanza lab = caricatore.getBuilder().getListaStanze().get("Laboratorio");
		
		assertEquals(StanzaMagica.class, lab.getClass());
	}

	@Test
	public void testCaricatoreConStanzaBloccata() throws Exception {
		String labirintoConStanzaBloccata = 
				"Stanze:\n" +
				"N10\n" +
				"Stanze Magiche:\n" +
				"Stanze Buie:\n" +
				"Stanze Bloccate:\n" +
				"Cella nord passepartout\n" +
				"Maghi:\n" +
				"Cani:\n" +
				"Streghe:\n" +
				"Estremi:\n" +
				"N10\n" +
				"Cella\n" +
				"Attrezzi:\n" +
				"Uscite:\n";

		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(labirintoConStanzaBloccata));
		caricatore.carica();
		Stanza cella = caricatore.getBuilder().getListaStanze().get("Cella");
		
		assertEquals(StanzaBloccata.class, cella.getClass());
	}

	@Test
	public void testCaricatoreConMago() throws Exception {
		String labirintoConMago = 
				"Stanze:\n" +
				"N10\n" +
				"Stanze Magiche:\n" +
				"Stanze Buie:\n" +
				"Stanze Bloccate:\n" +
				"Maghi:\n" +
				"Merlino Ciao_sono_Merlino bacchetta 2 N10\n" +
				"Cani:\n" +
				"Streghe:\n" +
				"Estremi:\n" +
				"N10\n" +
				"N10\n" +
				"Attrezzi:\n" +
				"Uscite:\n";

		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(labirintoConMago));
		caricatore.carica();
		Stanza n10 = caricatore.getBuilder().getListaStanze().get("N10");
		
		assertEquals("Merlino", n10.getPersonaggio().getNome());
		assertEquals(Mago.class, n10.getPersonaggio().getClass());
	}

	@Test
	public void testCaricatoreConCane() throws Exception {
		String labirintoConCane = 
				"Stanze:\n" +
				"N10\n" +
				"Stanze Magiche:\n" +
				"Stanze Buie:\n" +
				"Stanze Bloccate:\n" +
				"Maghi:\n" +
				"Cani:\n" +
				"Fido Wof_Wof N10\n" +
				"Streghe:\n" +
				"Estremi:\n" +
				"N10\n" +
				"N10\n" +
				"Attrezzi:\n" +
				"Uscite:\n";

		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(labirintoConCane));
		caricatore.carica();
		Stanza n10 = caricatore.getBuilder().getListaStanze().get("N10");
		
		assertEquals("Fido", n10.getPersonaggio().getNome());
		assertEquals(Cane.class, n10.getPersonaggio().getClass());
	}

	@Test
	public void testCaricatoreConStrega() throws Exception {
		String labirintoConStrega = 
				"Stanze:\n" +
				"N10\n" +
				"Stanze Magiche:\n" +
				"Stanze Buie:\n" +
				"Stanze Bloccate:\n" +
				"Maghi:\n" +
				"Cani:\n" +
				"Streghe:\n" +
				"Amelia Hihihihi_sono_una_strega N10\n" +
				"Estremi:\n" +
				"N10\n" +
				"N10\n" +
				"Attrezzi:\n" +
				"Uscite:\n";

		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(labirintoConStrega));
		caricatore.carica();
		Stanza n10 = caricatore.getBuilder().getListaStanze().get("N10");
		
		assertEquals("Amelia", n10.getPersonaggio().getNome());
		assertEquals(Strega.class, n10.getPersonaggio().getClass());
	}
}