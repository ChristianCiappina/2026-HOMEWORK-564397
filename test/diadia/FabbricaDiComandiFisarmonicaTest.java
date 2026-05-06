package diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

public class FabbricaDiComandiFisarmonicaTest {

	private FabbricaDiComandi factory;
	
	@Before
	public void setUp() {
		this.factory = new FabbricaDiComandiFisarmonica();
	}
	
	@Test
	public void testCostruisciComandoVaiConParametro() {
		Comando comando = this.factory.costruisciComando("vai nord");
		assertEquals("vai", comando.getNome());
		assertEquals("nord", comando.getParametro());
	}

	@Test
	public void testCostruisciComandoPrendiConParametro() {
		Comando comando = this.factory.costruisciComando("prendi spada");
		assertEquals("prendi", comando.getNome());
		assertEquals("spada", comando.getParametro());
	}
	
	@Test
	public void testCostruisciComandoPosaConParametro() {
		Comando comando = this.factory.costruisciComando("posa spada");
		assertEquals("posa", comando.getNome());
		assertEquals("spada", comando.getParametro());
	}
	
	@Test
	public void testCostruisciComandoAiutoSenzaParametro() {
		Comando comando = this.factory.costruisciComando("aiuto");
		assertEquals("aiuto", comando.getNome());
		assertEquals(null, comando.getParametro());
	}
	
	@Test
	public void testCostruisciComandoNonValidoSenzaParametro() {
		Comando comando = this.factory.costruisciComando("ciao");
		assertEquals("non valido", comando.getNome());
		assertEquals(null, comando.getParametro());
	}
	
	@Test
	public void testCostruisciComandoGuardaSenzaParametro() {
		Comando comando = this.factory.costruisciComando("guarda");
		assertEquals("guarda", comando.getNome());
		assertEquals(null, comando.getParametro());
	}
}
