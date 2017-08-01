package einsnull;

import einheitenkarten.SoeldnerTest;
import java.util.Scanner;

public class Main {
	
	static void ziehen(Spieler links, Spieler rechts){
		//eigentliches Spielen
	}

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		Spieler links = new Spieler();
		Spieler rechts = new Spieler();
		
		//Spieler oder KI auswählen
		
		//Spieler benennen
		//TODO: switch from console to GUI
		System.out.println("Bitte geben Sie den Namen des linken Spielers ein.");
		links.setName(input.next());
		System.out.println("Bitte geben Sie den Namen des rechten Spielers ein.");
		rechts.setName(input.next());
		
		//Karten wählen
		
		//vorerst eine testkarte in die Hand jedes Spielers
		Karte testkarte = new SoeldnerTest();
		Karte[] teststart= {testkarte};
		
		links.setHand(teststart);
		
		//Karten ausspielen oder benutzen
		while((links.getHand() != null || links.getTruppen() != null) && 
				(rechts.getHand() != null || rechts.getTruppen() != null)){
			ziehen(links, rechts);
		}
		
		//Sieger bekanntgeben
		//TODO: switch from console to GUI
		if(links.getHand() != null || links.getTruppen() != null) {
			System.out.println(links.getName() + " hat gewonnen.");
		}else {
			if(rechts.getHand() != null || rechts.getTruppen() != null) {
				System.out.println(rechts.getName() + " hat gewonnen.");
			}else {
				System.out.println("Die Partie endet unentschieden.");
			}
		}
		
		input.close();

	}

}
