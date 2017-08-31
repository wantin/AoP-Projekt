package einsnull;

import java.util.Scanner;

public class Effekt extends Karte {
	protected String name;
	protected Spieler besitzer;
	protected int preis;
	protected String bildPfad;
	protected String art;
	
	static Scanner input = new Scanner(System.in);
	
	public boolean nutzen(Feld spielbrett) {
		int x = besitzer.getAktionsAuswahlZeile();
		int y= besitzer.getAktionsAuswahlSpalte();
		this.ausspielen(x,y, spielbrett);
		return true;
	}
	
	//diese Funktion sollte im allgemeinen von den einzelnen Effekten überschrieben werden.
	public void ausspielen(int x, int y, Feld spielbrett) {
		System.out.println(besitzer.getName() + " hat " + name + " ausgespielt.");
	}

	//Getters und Setters
	public String getBildPfad(){
		return bildPfad;
	}
	
	public String getArt() {
		return art;
	}

	public String getName() {
		return name;
	}
	
	public int getPreis() {
		return preis;
	}

	@Override
	Spieler getBesitzer() {
		return besitzer;
	}
}