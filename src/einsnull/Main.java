package einsnull;

import einheitenkarten.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	static Scanner input = new Scanner(System.in);
	
	//so sollten wir das sp√§ter leicht √§ndern k√∂nnen.
	static void runde(Spieler links, Spieler rechts, Feld f) {
		ziehen(links, f);//der erste muss nicht √ºberpr√ºft werden, ob das spiel zuende ist, weil das in der while schleife schon passiert ist.
		if((!links.getHand().isEmpty() || !links.getTruppen().isEmpty()) && (!rechts.getHand().isEmpty() || !rechts.getTruppen().isEmpty())) {
			ziehen(rechts, f);
		}
	}
	
	//eigentliches Spielen
	static void ziehen(Spieler aktiv, Feld spielbrett){
		//ausw√§hlen
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
			//bevor ich die schleife hinzugef√ºgt hatte hat es einfach immer auswahl != 1 gehabt und deswegen den fehler bei der Truppenl√§nge, obwohl es noch keine Truppen gibt.
			//System.out.println("Durchlauf"); viele.. mein schwacher Laptop ist dran √ºberhitzt XD
			//scheinbar war das schlie√üen des inputs das Problem
			if (input.hasNextInt()) {
				auswahl= input.nextInt();
			}
		}while(auswahl == 0); //ich wei√ü nicht ob diese Schleifen sinnvoll oder so sind. hatte ich zur Fehlerbehandlung. kommt aber am Ende eh alles √ºber die GUI.
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
	 * Methode zur Generierung zuf‰lliger Einheiten, ein Case repr‰sentiert einen Kartentyp
	 * @param Der zugehˆrige Spieler f¸r den die Einheit generiert werden soll, wichtig f¸r Bewegungsrichtung links/rechts
	 * @return jeweilige zuf‰llige Karte wird zur¸ckgegegeben
	 */
	public static Einheit generateEinheit(Spieler besitzer) {
		Random zufall = new Random();
		int zufZahl = zufall.nextInt(2); 	// Zahl muss manuell je nach Anzahl der existierenden Klassen in 'einheitenkarten' ge‰ndert werden
		switch (zufZahl) {					// case int AnzahlKarten: return new KartenTyp(besitzer);
			case 0:
				return new SoeldnerTest(besitzer);
			case 1:
				return new SoeldnerTest2(besitzer);
			default:
				return null;
		}
	}
	
	public static void kaufen(Spieler besitzer) {
		Einheit[] auswahl = new Einheit[3];
		ArrayList<Karte> hand = besitzer.getHand();
		System.out.println("Kaufprozess beginnen. Sie haben " + besitzer.getGold() + " Gold.");
		System.out.println("Suchen Sie sich eine der folgenden Karten aus:");
		while(besitzer.getGold() > 100) {
			for(int i = 0; i <= 2; i++) {
				auswahl[i] = generateEinheit(besitzer);
				System.out.println("["+i+"] " + auswahl[i].getName() + "("+auswahl[i].getPreis()+"g)");
			}
			int key;
			do{
				key = input.nextInt();
				switch (key) {
					case 0: 
						hand.add(auswahl[0]);
						besitzer.setGold(besitzer.getGold()-(auswahl[0]).getPreis());
						System.out.println(auswahl[key].getName() + " zur Hand hinzugef¸gt.");
						System.out.println("Sie haben nun " + besitzer.getGold() + " Gold.");
						break;
					case 1: 
						hand.add(auswahl[1]);
						besitzer.setGold(besitzer.getGold()-auswahl[1].getPreis());
						System.out.println(auswahl[key].getName() + " zur Hand hinzugef¸gt.");
						System.out.println("Sie haben nun " + besitzer.getGold() + " Gold.");
						break;
					case 2: 
						hand.add(auswahl[2]);
						besitzer.setGold(besitzer.getGold()-auswahl[2].getPreis());
						System.out.println(auswahl[key].getName() + " zur Hand hinzugef¸gt.");
						System.out.println("Sie haben nun " + besitzer.getGold() + " Gold.");
						break;
					default: 
						System.out.println("Bitte korrekte Auswahl treffen.");
					}
				}while (key < 0 || key > 2);
			}
	}
	

	public static void main(String[] args) {
		
		Feld spielbrett = new Feld(6,6);
		Spieler links = new Spieler();links.setSeite("links");
		Spieler rechts = new Spieler();rechts.setSeite("rechts");
		
		//Spieler oder KI ausw√§hlen
		
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
		
		/*
		 * Vorerst rausgenommen da jetzt Kaufmethode existiert
		 * /
		//Karten w√§hlen
		//vorerst eine testkarte in die Hand jedes Spielers
		Karte testkarte = new SoeldnerTest(links);
		links.getHand().add(testkarte);
		
		Karte testkarte2 = new SoeldnerTest(rechts);
		rechts.getHand().add(testkarte2);*/
		
		kaufen(links);
		kaufen(rechts);
		
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
