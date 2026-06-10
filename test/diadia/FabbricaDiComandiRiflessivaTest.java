package diadia;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

public class FabbricaDiComandiRiflessivaTest {

	private FabbricaDiComandi factory;
	
	@Before
	public void setUp() {
		this.factory = new FabbricaDiComandiRiflessiva();
	}
	
	@Test
	public void testCostruisciComandoVaiConParametro() throws Exception {
		Comando comando = this.factory.costruisciComando("vai nord");
		assertEquals("vai", comando.getNome());
		assertEquals("nord", comando.getParametro());
	}

	@Test
	public void testCostruisciComandoPrendiConParametro() throws Exception {
		Comando comando = this.factory.costruisciComando("prendi spada");
		assertEquals("prendi", comando.getNome());
		assertEquals("spada", comando.getParametro());
	}
	
	@Test
	public void testCostruisciComandoPosaConParametro() throws Exception {
		Comando comando = this.factory.costruisciComando("posa spada");
		assertEquals("posa", comando.getNome());
		assertEquals("spada", comando.getParametro());
	}
	
	@Test
	public void testCostruisciComandoAiutoSenzaParametro() throws Exception {
		Comando comando = this.factory.costruisciComando("aiuto");
		assertEquals("aiuto", comando.getNome());
		assertEquals(null, comando.getParametro());
	}
	
	@Test
	public void testCostruisciComandoNonValidoSenzaParametro() throws Exception {
		Comando comando = this.factory.costruisciComando("ciao");
		assertEquals("nonvalido", comando.getNome());
		assertEquals(null, comando.getParametro());
	}
	
	@Test
	public void testCostruisciComandoGuardaSenzaParametro() throws Exception {
		Comando comando = this.factory.costruisciComando("guarda");
		assertEquals("guarda", comando.getNome());
		assertEquals(null, comando.getParametro());
	}
}
