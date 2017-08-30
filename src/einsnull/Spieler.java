package einsnull;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import effektkarten.Blitzschlag;
import effektkarten.GottesSegen;
import effektkarten.Saeuregranate;
import einheitenkarten.Bogenschuetzen;
import einheitenkarten.Ritter;
import einheitenkarten.Soeldner;
import einheitenkarten.Schildziege;

public class Spieler {
	
	private String name;
	private ArrayList<Karte> hand = new ArrayList<Karte>();
	private ArrayList<Einheit> truppen = new ArrayList<Einheit>();
	private int gold = 1000;
	private String seite;
	private int aktionsAuswahl0;
	private int aktionsAuswahlZeile= -1;
	private int aktionsAuswahlSpalte= -1;
	private boolean aktionAuswahlHand;
	
	static Scanner input = new Scanner(System.in);
	
	public void resetBereit() {
		for (int i = 0; i < truppen.size(); i++) {
			truppen.get(i).setBereit(1);
		}
	}
	
	//bewegt aus Main
	public void kaufen(GUI anzeige, Spieler anderer) {
		
		anzeige.kaufen(this, anderer);
		
		/* Ich lass das mal noch drin, bis ich kaufen bei der KI auch drin habe, aber ich glaube dann brauchen wir das wirklich nicht mehr.
		
		Karte[] auswahl = new Karte[3];
		int maxPreis = 50;
		int maxHand = 8;
		
		System.out.println("Kaufprozess beginnen. Sie haben " + gold + " Gold.");
		System.out.println("Suchen Sie sich eine der folgenden Karten aus:");
		
		loop:
		while(gold >= maxPreis) {
			for(int i = 0; i <= 2; i++) {
				auswahl[i] = generateEinheit();
				while (gold < auswahl[i].getPreis() && gold >= maxPreis) {
					auswahl[i] = generateEinheit(); // System.out.println(i + "Nicht genug Gold. Generiere neue Einheit."); // Nur zum Testen.
				}
				System.out.println("["+i+"] " + auswahl[i].getName() + "("+auswahl[i].getPreis()+"g)");
			}
			int key;
			do{
				key = input.nextInt();
				switch (key) {
					case 0: 
						hand.add(auswahl[0]);
						gold -= auswahl[0].getPreis();
						System.out.println(auswahl[key].getName() + " zur Hand hinzugef�gt.");
						System.out.println("Sie haben nun " + gold + " Gold.");
						break;
					case 1: 
						hand.add(auswahl[1]);
						gold -= auswahl[1].getPreis();
						System.out.println(auswahl[key].getName() + " zur Hand hinzugef�gt.");
						System.out.println("Sie haben nun " + gold + " Gold.");
						break;
					case 2: 
						hand.add(auswahl[2]);
						gold -= auswahl[2].getPreis();
						System.out.println(auswahl[key].getName() + " zur Hand hinzugef�gt.");
						System.out.println("Sie haben nun " + gold + " Gold.");
						break;
					default: 
						System.out.println("Bitte korrekte Auswahl treffen.");
					}
				System.out.println("Sie haben nun " + hand.size() + " Karten auf der Hand");
				if (hand.size() >= maxHand) {
					System.out.println("Maximale Handkartenanzahl von '" + maxHand + "' erreicht.");
					break loop;
				}
				}while (key < 0 || key > 2);
			}
			*/
	}
	
	/* TODO(?): Verschiedene Chancen bestimmte Karten zu erhalten, vielleicht irgendwas mathematisches mit 
	 * modulo und Runden? Vielleicht Case 1/2/3 ein Kartentyp, Case 4/5/6 ein anderer?
	 * 
	 * Methode zur Generierung zuf�lliger Einheiten, ein Case repr�sentiert einen Kartentyp
	 * @param Der zugeh�rige Spieler f�r den die Einheit generiert werden soll, wichtig f�r Bewegungsrichtung links/rechts
	 * @return jeweilige zuf�llige Karte wird zur�ckgegegeben
	 */
	public Karte generateEinheit() {
		Random zufall = new Random();
		int zufZahl = zufall.nextInt(7); 	// Zahl muss manuell je nach Anzahl der existierenden Klassen in 'einheitenkarten' ge�ndert werden
		switch (zufZahl) {					// case int AnzahlKarten: return new KartenTyp(this);
			case 0:
				return new Soeldner(this);
			case 1:
				return new Schildziege(this);
			case 2:
				return new Ritter(this);
			case 3:
				return new Bogenschuetzen(this);
			case 4:
				return new Blitzschlag(this);
			case 5:
				return new GottesSegen(this);
			case 6:
				return new Saeuregranate(this);
			default:
				return null; //sollte nicht vorkommen
		}
	}	
	
	//eigentliches Spielen
	//bewegt aus main
	void ziehen(Feld spielbrett, GUI anzeige){
		//auswählen
		//TODO: switch from console to GUI
		anzeige.optionenZeigenSpieler(this);
		
		while(aktionsAuswahl0==-1){
			if(aktionAuswahlHand){
				this.getHand().get(aktionsAuswahl0).nutzen(spielbrett);
			}else{
				this.getTruppen().get(aktionsAuswahl0).nutzen(spielbrett);
			}
		}
		/*alt
		System.out.println("Hand von " + this.getName());
		this.printHand();
		System.out.println("Truppen von " + this.getName());
		this.printTruppen();
		System.out.println("Spielfeld:");
		spielbrett.print();
		int auswahl = 0;
		boolean stop= false; //wird auf true gesetzt, wenn eine Aktion ausgeführt wurde.
		
		do {
			System.out.println("Wollen Sie eine Handkarte ausspielen(1), oder eine Einheit bewegen/mit ihr angreifen(2)?");
			auswahl= input.nextInt();
			if(auswahl == 1){
				System.out.println("Welche Handkarte wollen Sie spielen? [0, " + (this.getHand().size()-1 ) + "]" );
				auswahl= input.nextInt();
				stop= this.getHand().get(auswahl).nutzen(spielbrett);
			}else {
				System.out.println("Welche Einheit wollen Sie bewegen? [0, " + (this.getTruppen().size()-1 ) + "]" );
				auswahl= input.nextInt();
				stop= this.getTruppen().get(auswahl).nutzen(spielbrett);
			}
		}while(!stop);
		*/
	}
	
	//Kontrollausgabemethoden
	
	public void printTruppen() {
		for (int i = 0; i < truppen.size(); i++) {
			System.out.println(truppen.get(i).getName());
		}
	}
	
	public void printHand() {
		for (int i = 0; i < hand.size(); i++) {
			System.out.println(hand.get(i).getName());
		}
	}
	
	//Setter und Getter
	
	public String getSeite() {
		return seite;
	}

	public boolean isAktionAuswahlHand() {
		return aktionAuswahlHand;
	}

	public void setAktionAuswahlHand(boolean aktionAuswahlHand) {
		this.aktionAuswahlHand = aktionAuswahlHand;
	}

	public void setSeite(String seite) {
		this.seite = seite;
	}

	public int getAktionsAuswahl0() {
		return aktionsAuswahl0;
	}

	public void setAktionsAuswahl0(int aktionsAuswahl0) {
		this.aktionsAuswahl0 = aktionsAuswahl0;
	}

	public int getAktionsAuswahlZeile() {
		return aktionsAuswahlZeile;
	}

	public void setAktionsAuswahlZeile(int aktionsAuswahlZeile) {
		this.aktionsAuswahlZeile = aktionsAuswahlZeile;
	}

	public int getAktionsAuswahlSpalte() {
		return aktionsAuswahlSpalte;
	}

	public void setAktionsAuswahlSpalte(int aktionsAuswahlSpalte) {
		this.aktionsAuswahlSpalte = aktionsAuswahlSpalte;
	}

	public int getGold() {
		return gold;
	}
	
	public void setGold(int gold) {
		this.gold = gold;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Karte> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Karte> hand) {
		this.hand = hand;
	}

	public ArrayList<Einheit> getTruppen() {
		return truppen;
	}

	public void setTruppen(ArrayList<Einheit> truppen) {
		this.truppen = truppen;
	}

}
