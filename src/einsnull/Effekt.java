package einsnull;

import java.util.Scanner;

public class Effekt extends Karte {
	protected String name;
	protected Spieler besitzer;
	protected int preis;
	
	static Scanner input = new Scanner(System.in);
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getPreis() {
		return preis;
	}
	
	public boolean nutzen(Feld spielbrett) {
		System.out.println("Geben Sie das Feld der Einheit an, auf die Sie diese Karte anwenden wollen");
		System.out.println("Geben Sie die Zeile an, oder geben Sie -1 ein, um abzubrechen.");
		int x, y;
		x = input.nextInt();
		if(x == -1)	return false;
		System.out.println("Geben Sie die Spalte an.");
		y= input.nextInt();
		this.ausspielen(x,y, spielbrett);
		return true;
	}
	
	//diese Funktion sollte im allgemeinen von den einzelnen Effekten überschrieben werden.
	public void ausspielen(int x, int y, Feld spielbrett) {
		System.out.println(besitzer.getName() + " hat " + name + " ausgespielt.");
	}

}
