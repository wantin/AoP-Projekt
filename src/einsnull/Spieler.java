package einsnull;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import effektkarten.Blitzschlag;
import effektkarten.GottesSegen;
import effektkarten.Saeuregranate;
import einheitenkarten.Bogenschuetzen;
import einheitenkarten.Ritter;
import einheitenkarten.Soeldner;
import einheitenkarten.Schildziege;

public class Spieler {
	
	protected String name;
	protected ArrayList<Karte> hand = new ArrayList<Karte>();
	protected ArrayList<Einheit> truppen = new ArrayList<Einheit>();
	protected int gold = 1000;
	protected String seite;
	protected int aktionsAuswahl0 = -1;
	protected int aktionsAuswahlZeile= -1;
	protected int aktionsAuswahlSpalte= -1;
	protected boolean aktionAuswahlHand;
	protected Einheit aktionAuswahlEinheit = null;
	protected int auswahlPhase;
	protected boolean passen = false;
	
	public void resetBereit() {
		for (int i = 0; i < truppen.size(); i++) {
			truppen.get(i).setBereit(1);
		}
	}
	
	// diese Funktion ist hier, damit KI sie überschreiben kann
	public void kaufen(GUI anzeige, Spieler anderer) {
		anzeige.kaufen(this, anderer);
	}
	

	 // Methode zur Generierung zufälliger Einheiten, ein Case repräsentiert einen Kartentyp
	public Karte generateEinheit() {
		Random zufall = new Random();
		int zufZahl = zufall.nextInt(7); 	// Zahl muss manuell je nach Anzahl der existierenden Klassen in 'einheitenkarten' geändert werden
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
	
	// eigentliches Spielen
	void ziehen(Feld spielbrett, GUI anzeige){
		// reset
		passen = false;
		aktionsAuswahlZeile = -1;
		aktionsAuswahlSpalte = -1;
		aktionsAuswahl0 = -1;
		aktionAuswahlEinheit = null;
		auswahlPhase = 0;
		
		anzeige.optionenZeigenSpieler(this);
		
		boolean trupp = false;
		boolean ausspielen = false;
		boolean ziel = false;
		while(!((trupp || ausspielen) && ziel)){
			if (passen)
				// TODO noetig?! break/... geht net?
				return;
			trupp = (aktionAuswahlHand == false) && (aktionAuswahlEinheit != null);	//man hat eine Truppe ausgewählt
			ausspielen = (aktionAuswahlHand == true) && (aktionsAuswahl0 != -1);			//man hat eine Handkarte zum ausspielen gewählt
			ziel = (aktionsAuswahlSpalte != -1) && (aktionsAuswahlZeile != -1);		//man hat ein Ziel gewählt
			try {
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (ausspielen)
			hand.get(aktionsAuswahl0).nutzen(spielbrett);
		else	//da wir aus der schleife raus sind gilt: aus xor trupp
			aktionAuswahlEinheit.nutzen(spielbrett);
		
		anzeige.aktualisierenFeld(spielbrett);
		anzeige.aktualisierenHand(this);
	}
	
	
	// Kontrollausgabemethoden
	public void printTruppen() {
		for (int i = 0; i < truppen.size(); i++)
			System.out.println(truppen.get(i).getName());
	}
	
	public void printHand() {
		for (int i = 0; i < hand.size(); i++)
			System.out.println(hand.get(i).getName());
	}
	
	
	// Getter und Setter
	public String getSeite() {
		return seite;
	}
		
	public Einheit getAktionAuswahlEinheit() {
		return aktionAuswahlEinheit;
	}

	public boolean isPassen() {
		return passen;
	}

	public void setPassen(boolean passen) {
		this.passen = passen;
	}

	public int getAuswahlPhase() {
		return auswahlPhase;
	}

	public void setAuswahlPhase(int auswahlPhase) {
		this.auswahlPhase = auswahlPhase;
	}

	public void setAktionAuswahlEinheit(Einheit aktionAuswahlEinheit) {
		this.aktionAuswahlEinheit = aktionAuswahlEinheit;
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
