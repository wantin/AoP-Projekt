package einsnull;

import einheitenkarten.SoeldnerTest;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	static Scanner input = new Scanner(System.in);
	
	//so sollten wir das spÃ¤ter leicht Ã¤ndern kÃ¶nnen.
	static void runde(Spieler links, Spieler rechts, Feld f) {
		ziehen(links, f);//der erste muss nicht Ã¼berprÃ¼ft werden, ob das spiel zuende ist, weil das in der while schleife schon passiert ist.
		if((!links.getHand().isEmpty() || !links.getTruppen().isEmpty()) && (!rechts.getHand().isEmpty() || !rechts.getTruppen().isEmpty())) {
			ziehen(rechts, f);
		}
	}
	
	//eigentliches Spielen
	static void ziehen(Spieler aktiv, Feld spielbrett){
		//auswÃ¤hlen
		//TODO: switch from console to GUI
		
		System.out.println("Hand von " + aktiv.getName());
		aktiv.printHand();
		System.out.println("Truppen von " + aktiv.getName());
		aktiv.printTruppen();
		System.out.println("Spielfeld:");
		spielbrett.print();
		
		System.out.println("Wollen Sie eine Handkarte ausspielen(1), oder eine Einheit bewegen/mit ihr angreifen(2)?");
		int auswahl = 0;
		do {
			//bevor ich die schleife hinzugefÃ¼gt hatte hat es einfach immer auswahl != 1 gehabt und deswegen den fehler bei der TruppenlÃ¤nge, obwohl es noch keine Truppen gibt.
			//System.out.println("Durchlauf"); viele.. mein schwacher Laptop ist dran Ã¼berhitzt XD
			//scheinbar war das schlieÃŸen des inputs das Problem
			if (input.hasNextInt()) {
				auswahl= input.nextInt();
			}
		}while(auswahl == 0); //ich weiÃŸ nicht ob diese Schleifen sinnvoll oder so sind. hatte ich zur Fehlerbehandlung. kommt aber am Ende eh alles Ã¼ber die GUI.
		if(auswahl == 1){
			System.out.println("Welche Handkarte wollen Sie spielen? [0, " + (aktiv.getHand().size()-1 ) + "]" );
			auswahl= input.nextInt();
			aktiv.getHand().get(auswahl).nutzen(spielbrett);
		}else {
			System.out.println("Welche Einheit wollen Sie bewegen? [0, " + (aktiv.getTruppen().size()-1 ) + "]" );
			auswahl= input.nextInt();
			aktiv.getTruppen().get(auswahl).nutzen(spielbrett);
		}		
	}
	
	
	/* TODO(?): Verschiedene Chancen bestimmte Karten zu erhalten, vielleicht irgendwas mathematisches mit 
	 * modulo und Runden? Vielleicht Case 1/2/3 ein Kartentyp, Case 4/5/6 ein anderer?
	 * 
	 * Methode zur Generierung zufälliger Einheiten, ein Case repräsentiert einen Kartentyp
	 * @param Der zugehörige Spieler für den die Einheit generiert werden soll, wichtig für Bewegungsrichtung links/rechts
	 * @return jeweilige zufällige Karte wird zurückgegegeben
	 */
	public Einheit generateEinheit(Spieler besitzer) {
		Random zufall = new Random();
		int zufZahl = zufall.nextInt(0); 	// Zahl muss manuell je nach Anzahl der existierenden Klassen in 'einheitenkarten' geändert werden
		switch (zufZahl) {					// case int AnzahlKarten: return new KartenTyp(besitzer);
			case 0:
				return new SoeldnerTest(besitzer);
			default:
				return null;
		}
	}
	
	public void kaufen(Spieler besitzer) {
		//ArrayList<Einheit> auswahl = new ArrayList<Einheit>();
		Einheit[] auswahl = new Einheit[3];
		ArrayList<Karte> hand = besitzer.getHand();
		System.out.println("Kaufprozess beginnen");
		System.out.println("Suchen Sie sich eine der folgenden Karten aus:");
		while(besitzer.getGold() > 100) {
			for(int i = 0; i <= 2; i++) {
				auswahl[i] = generateEinheit(besitzer);
				System.out.println("["+i+"] " + auswahl[i] + "("+auswahl[i].getPreis()+")");
			}
			int key = input.nextInt();
			switch (key) {
				case 0: 
					hand.add(auswahl[0]);
					besitzer.setGold(besitzer.getGold()-auswahl[0].getPreis());
				case 1: 
					hand.add(auswahl[1]);
					besitzer.setGold(besitzer.getGold()-auswahl[1].getPreis());
				case 2: 
					hand.add(auswahl[2]);
					besitzer.setGold(besitzer.getGold()-auswahl[2].getPreis());
				default: 
			}
		
		}
	}

	public static void main(String[] args) {
		
		Feld spielbrett = new Feld(6,6);
		Spieler links = new Spieler();links.setSeite("links");
		Spieler rechts = new Spieler();rechts.setSeite("rechts");
		
		//Spieler oder KI auswÃ¤hlen
		
		//Spieler benennen
		//TODO: switch from console to GUI
		
		/* das ist zu nervig, deswegen vorerst anders
		System.out.println("Bitte geben Sie den Namen des linken Spielers ein.");
		links.setName(input.next());
		System.out.println("Bitte geben Sie den Namen des rechten Spielers ein.");
		rechts.setName(input.next());
		*/
		links.setName("Eule");
		rechts.setName("Ratte");
		
		//Karten wÃ¤hlen
		//vorerst eine testkarte in die Hand jedes Spielers
		Karte testkarte = new SoeldnerTest(links);
		links.getHand().add(testkarte);
		
		Karte testkarte2 = new SoeldnerTest(rechts);
		rechts.getHand().add(testkarte2);
		
		//Karten ausspielen oder benutzen
		while((!links.getHand().isEmpty() || !links.getTruppen().isEmpty()) && (!rechts.getHand().isEmpty() || !rechts.getTruppen().isEmpty())){
			runde(links, rechts, spielbrett);
		}
		
		//Sieger bekanntgeben
		//TODO: switch from console to GUI
		if(!links.getHand().isEmpty() || !links.getTruppen().isEmpty()) {
			System.out.println(links.getName() + " hat gewonnen.");
		}else {
			if(!rechts.getHand().isEmpty() || !rechts.getTruppen().isEmpty()) {
				System.out.println(rechts.getName() + " hat gewonnen.");
			}else {
				System.out.println("Die Partie endet unentschieden.");
			}
		}
	}

}
