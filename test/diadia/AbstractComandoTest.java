package diadia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.comandi.AbstractComando;

public class AbstractComandoTest {

	private class FakeComando extends AbstractComando {

		@Override
		public void esegui(Partita partita) {
		}

		@Override
		public String getNome() {
			return "fake";
		}
		
	}

	private AbstractComando comandoAstratto;
	
	@Before
	public void setUp() {
		this.comandoAstratto = new FakeComando();
	}
	
	@Test
	public void testGetParametroInizialmenteNull() {
		assertNull("Appena creato, il parametro dovrebbe essere null", this.comandoAstratto.getParametro());
	}
	
	@Test
	public void testSetParametro() {
		this.comandoAstratto.setParametro("nord");
		assertEquals("nord", this.comandoAstratto.getParametro());
	}
	
	@Test
	public void testGetIoInizialmenteNull() {
		assertNull("Appena creato, l'io dovrebbe essere null", this.comandoAstratto.getIo());
	}
	
	@Test
	public void testSetIo() {
			IO io = new IOConsole();
			this.comandoAstratto.setIO(io);
			assertNotNull(this.comandoAstratto.getIo());
	}
}
