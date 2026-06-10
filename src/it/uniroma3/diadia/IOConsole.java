package it.uniroma3.diadia;

import java.util.Scanner;

public class IOConsole implements IO {
	
	private Scanner scannerDiLinee;
	
	public IOConsole(Scanner scanner) {
		this.scannerDiLinee = scanner;
	}
	
	public IOConsole() {
		this.scannerDiLinee = new Scanner(System.in);
	}
	
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	
	public String leggiRiga() {
		return this.scannerDiLinee.nextLine();
	}
}