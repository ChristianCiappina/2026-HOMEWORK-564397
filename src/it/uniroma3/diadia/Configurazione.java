package it.uniroma3.diadia;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configurazione {

	private static final String NOME_FILE = "diadia.properties";
	private static Properties prop = null;

	public static int getCFU() {
		if(prop == null) carica();
		return Integer.parseInt(prop.getProperty("cfu_iniziali"));
	}

	public static int getPesoMax() {
		if(prop == null) carica();
		return Integer.parseInt(prop.getProperty("peso_max_borsa"));
	}

	private static void carica() {
		prop = new Properties();
		try {
			InputStream input = Configurazione.class.getClassLoader().getResourceAsStream(NOME_FILE);
			prop.load(input);
		} catch (IOException | NullPointerException e) {
			System.err.println("Errore: File " + NOME_FILE + " non trovato nel classpath!");
			e.printStackTrace();
		}
	}
}