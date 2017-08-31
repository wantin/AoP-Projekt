package einsnull;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import einheitenkarten.*;

public class KI extends Spieler {
	
	String name = "Dösender Doktor";
	
	public void resetBereit() {
		for (int i = 0; i < truppen.size(); i++) {
			truppen.get(i).setBereit(1);
		}
	}
	
	@Override
	public void kaufen(GUI anzeige, Spieler anderer) {
		int maxPreis = 50;
		int maxHand = 8;
		System.out.println("KI Kaufprozess beginnen. KI hat " + gold + " Gold.");
		
		while(gold >= maxPreis && hand.size() < maxHand) {
			Einheit auswahl = generateEinheit();
			while (gold < auswahl.getPreis() && gold >= maxPreis) {
				auswahl = generateEinheit(); // System.out.println(i + "Nicht genug Gold. Generiere neue Einheit."); // Nur zum Testen.
			}
			hand.add(auswahl);
			this.setGold(this.getGold()-auswahl.getPreis());
			System.out.println("Es wurde " + auswahl.getName() + " gekauft. Sie haben nun " + hand.size() + " Karten auf der Hand und " + this.getGold() + "g");
			anzeige.aktualisierenHand(this);
		}
		//anzeige.aktualisierenHand(this);
		anzeige.kaufenVerstecken();
	}
	
	/* TODO(?): Verschiedene Chancen bestimmte Karten zu erhalten, vielleicht irgendwas mathematisches mit 
	 * modulo und Runden? Vielleicht Case 1/2/3 ein Kartentyp, Case 4/5/6 ein anderer?
	 * 
	 * Methode zur Generierung zufï¿½lliger Einheiten, ein Case reprï¿½sentiert einen Kartentyp
	 * @param Der zugehï¿½rige Spieler fï¿½r den die Einheit generiert werden soll, wichtig fï¿½r Bewegungsrichtung links/rechts
	 * @return jeweilige zufï¿½llige Karte wird zurï¿½ckgegegeben
	 */
	public Einheit generateEinheit() {
		Random zufall = new Random();
		int zufZahl = zufall.nextInt(4); 	// Zahl muss manuell je nach Anzahl der existierenden Klassen in 'einheitenkarten' geï¿½ndert werden
		switch (zufZahl) {					// case int AnzahlKarten: return new KartenTyp(this);
			case 0:
				return new Soeldner(this);
			case 1:
				return new Schildziege(this);
			case 2:
				return new Ritter(this);
			case 3:
				return new Bogenschuetzen(this);
			default:
				return null; //sollte nicht vorkommen
		}
	}	
	
	//eigentliches Spielen
	//bewegt aus main
	@Override
	void ziehen(Feld spielbrett, GUI anzeige){
		//reset
		aktionsAuswahlZeile= -1;
		aktionsAuswahlSpalte= -1;
		
		int auswahl = 0;
		Random zufall = new Random();
		boolean bereiteTruppen = false;
		boolean gespielt = false;
		
		if (!hand.isEmpty()) { //wenn Karten auf Hand, spiele sie
			do {
				aktionsAuswahlZeile = zufall.nextInt(5);
				aktionsAuswahlSpalte = zufall.nextInt(2)+4;
				auswahl = zufall.nextInt(this.getHand().size());
			}while(spielbrett.besetzt(this.getAktionsAuswahlZeile(), this.getAktionsAuswahlSpalte()));
			anzeige.optionenZeigenSpieler(this);
			try {
				TimeUnit.MILLISECONDS.sleep(1421); //deal with it
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.hand.get(auswahl).nutzen(spielbrett);
			System.out.println("Karten auf der Hand vorhanden. Eine Karte wird gespielt.");
			gespielt = true;
		} else { //ansonsten überprüfe ob bereite Truppen auf Feld
		//	System.out.println("Überprüfe ob bereite Truppen auf Feld");
			loop:
			for (int i = 0; i < this.getTruppen().size(); i++) { 
				if (truppen.get(i).getBereit() == 1) {
					bereiteTruppen = true;
					//System.out.println("Bereite Truppen auf Feld.");
					break loop;
				}
			}
		}
		
		if (bereiteTruppen == true && gespielt == false) { //wenn bereite Truppen auf Feld, benutze diese
			loop:
			//überprüfe ob angreifbare Einheit auf dem Feld, wenn ja greife an
			for (int i = 0; i < this.getTruppen().size(); i++) { 
				if (truppen.get(i).zeigeAngriff(spielbrett).size() > 0 && truppen.get(i).getBereit() == 1) { //überprüfe eine Einheit ob angreifbare Einheit in Reichweite hat
					System.out.println(truppen.get(i).getName() + " kann angreifen.");
					System.out.println(truppen.get(i).getName() + " Bereitschaft: " + truppen.get(i).getBereit());
					int zufZahl = zufall.nextInt(truppen.get(i).zeigeAngriff(spielbrett).size()); 
					ArrayList<int[]> moeglicheAngriffe = truppen.get(i).zeigeAngriff(spielbrett);
					System.out.println(truppen.get(i).zeigeAngriff(spielbrett).size() + " mögliche Angriffe: ");truppen.get(i).printAngriffe(spielbrett); 
					aktionsAuswahlZeile = moeglicheAngriffe.get(zufZahl)[0]; 
					aktionsAuswahlSpalte = moeglicheAngriffe.get(zufZahl)[1];
					System.out.println(truppen.get(i).getName() + " Angriff auf Zeile " + aktionsAuswahlZeile + " und Spalte " + aktionsAuswahlSpalte);
					anzeige.optionenZeigenEinheit(truppen.get(i), spielbrett);
					try {
						TimeUnit.MILLISECONDS.sleep(1421); //deal with it
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					truppen.get(i).nutzen(spielbrett);
					gespielt = true;
					break loop;
				} else System.out.println(truppen.get(i).getName() + " kann nicht angreifen. Probiere nächste Einheit.");
			} System.out.println("________________________________________________________________________");
		}
		if (bereiteTruppen == true && gespielt == false) {
			//wenn keine angreifbaren Einheiten, bewege Einheit
			System.out.println("Keine angreifbaren Einheiten auf dem Feld. Bewege eine Einheit.");
			do {
				auswahl = zufall.nextInt(this.getTruppen().size());
			} while (truppen.get(auswahl).getBereit() == 0);
			System.out.print(truppen.get(auswahl).getName() + " :");truppen.get(auswahl).printBewegungen(spielbrett);	System.out.println();System.out.println("________________________________________________________________________");
			ArrayList<int[]> moeglicheBewegungen = truppen.get(auswahl).zeigeBewegung(spielbrett);
			int zufZahl = 0;
			int abb = 0;
			do {
				abb++;
				zufZahl = zufall.nextInt(truppen.get(auswahl).zeigeBewegung(spielbrett).size());
				aktionsAuswahlZeile = moeglicheBewegungen.get(zufZahl)[0];
				aktionsAuswahlSpalte = moeglicheBewegungen.get(zufZahl)[1];
				System.out.println("Versuche Bewegung: (" + aktionsAuswahlZeile + "/" + aktionsAuswahlSpalte + ")");
			} while (moeglicheBewegungen.get(zufZahl)[1] > truppen.get(auswahl).getPosition()[1] && abb < 30);
			anzeige.optionenZeigenEinheit(truppen.get(auswahl), spielbrett);
			try {
				TimeUnit.MILLISECONDS.sleep(1421); //deal with it
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.truppen.get(auswahl).nutzen(spielbrett);
			gespielt = true;
		}
		anzeige.aktualisierenFeld(spielbrett);
		anzeige.aktualisierenHand(this);
	}
	
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

	public void setSeite(String seite) {
		this.seite = seite;
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

	public ArrayList<Karte> getHandKI() {
		return hand;
	}

	public void setHandKI(ArrayList<Karte> hand) {
		this.hand = hand;
	}

	public ArrayList<Einheit> getTruppen() {
		return truppen;
	}

	public void setTruppen(ArrayList<Einheit> truppen) {
		this.truppen = truppen;
	}

}
