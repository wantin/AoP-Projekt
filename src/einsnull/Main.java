package einsnull;

import einheitenkarten.SoeldnerTest;
import java.util.Scanner;

public class Main {
	
	static Scanner input = new Scanner(System.in);
	
	//so sollten wir das später leicht ändern können.
	static void runde(Spieler links, Spieler rechts) {
		ziehen(links);
		ziehen(rechts);
	}
	
	//eigentliches Spielen
	static void ziehen(Spieler aktiv){
		//auswählen
		//TODO: switch from console to GUI
		System.out.println("Wollen Sie eine Handkarte ausspielen(1), oder eine Einheit bewegen(2)?");
		int auswahl = 0;
		do {
			//bevor ich die schleife hinzugefügt hatte hat es einfach immer auswahl != 1 gehabt und deswegen den fehler bei der Truppenlänge, obwohl es noch keine Truppen gibt.
			//System.out.println("Durchlauf"); viele.. mein schwacher Laptop ist dran überhitzt XD
			if (input.hasNextInt()) {
				auswahl= input.nextInt();
			}
		}while(auswahl == 0);
		if(auswahl == 1){
			System.out.println("Welche Handkarte wollen Sie spielen? (0 - " + (aktiv.getHand().length ) + ")" );
			auswahl= input.nextInt();
			aktiv.getHand()[auswahl].ausspielen();
		}else {
			System.out.println("Welche Einheit wollen Sie bewegen? (0 - " + (aktiv.getTruppen().length ) + ")" );
			auswahl= input.nextInt();
			aktiv.getTruppen()[auswahl].bewegen();
		}
		
	}

	public static void main(String[] args) {
		
		Spieler links = new Spieler();links.setSeite("links");
		Spieler rechts = new Spieler();rechts.setSeite("rechts");
		
		//Spieler oder KI auswählen
		
		//Spieler benennen
		//TODO: switch from console to GUI
		System.out.println("Bitte geben Sie den Namen des linken Spielers ein.");
		links.setName(input.next());
		System.out.println("Bitte geben Sie den Namen des rechten Spielers ein.");
		rechts.setName(input.next());
		
		//Karten wählen
		//vorerst eine testkarte in die Hand jedes Spielers
		Karte testkarte = new SoeldnerTest(links);
		Karte[] teststart= {testkarte};
		links.setHand(teststart);
		
		Karte testkarte2 = new SoeldnerTest(rechts);
		Karte[] teststart2= {testkarte2};
		rechts.setHand(teststart2);
		
		//Karten ausspielen oder benutzen
		while((links.getHand() != null || links.getTruppen() != null) && 
				(rechts.getHand() != null || rechts.getTruppen() != null)){
			runde(links, rechts);
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
	}

}
