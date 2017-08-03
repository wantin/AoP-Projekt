package einsnull;

import java.util.Scanner;

public class Effekt extends Karte {
	private String name;
	
	static Scanner input = new Scanner(System.in);
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean nutzen(Feld spielbrett) {
		System.out.println("Wollen Sie die Karte " + name + " ausspielen (1), oder abbrechen (2)?");
		int eingabe = input.nextInt();
		if(eingabe == 1) {
			this.ausspielen();
			return true;
		}else {
			return false;
		}
		
	}
	
	//diese Funktion sollte im allgemeinen von den einzelnen Effekten überschrieben werden.
	void ausspielen() {
		System.out.println(besitzer.getName() + " hat " + name + " ausgespielt.");
	}

}
