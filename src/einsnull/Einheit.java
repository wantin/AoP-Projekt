package einsnull;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import einheitenkarten.*;


public class Einheit extends Karte {
	protected int staerke;
	protected int ruestung;
	protected ArrayList<int[]>  bewegung= new ArrayList<int[]> ();
	protected ArrayList<int[]>  angriff = new ArrayList< int[]>();
	protected String name;
	protected int[] position = {-1, -1};

static Scanner input = new Scanner(System.in);

	//Diese Funktion zeigt einem auf, wie man eine Karte(hier Einheit) nutzen kann und ruft dann die entsprechenden Funktionen auf (siehe unten)
	public boolean nutzen(Feld spielbrett) {
		if(position[0]==-1) { //prÃ¼ft ob die Karte noch auf der Hand ist.
			System.out.println("Diese Karte kÃ¶nnen Sie ausspielen. Geben Sie Zeile und Spalte eines freien Feldes in Ihrem Spielbereich an, auf welches Sie die Karte spielen wollen.");
			System.out.println("Geben Sie die Zeile an, oder -1 um abzubrechen");
			int x, y;
			x= input.nextInt();
			if(x == -1) return false;
			System.out.println("Geben Sie die Spalte an");
			y= input.nextInt();
			this.ausspielen(spielbrett, x, y); //keine Korrektur brauch korrekte x, y
			return true;
		}else {
			System.out.println("Diese Karte kÃ¶nnen Sie angreifen lassen oder bewegen.");
			System.out.println("MÃ¶gliche Bewegungen:");
			for (int i = 0; i < bewegung.size(); i++) {
				//check fuer Rand FeldgrÃ¶ÃŸe flexibel
				if(position[0]+bewegung.get(i)[0] < spielbrett.getAnzahlZeilen() && position[0]+bewegung.get(i)[0] >= 0 && position[1]+bewegung.get(i)[1] < spielbrett.getAnzahlSpalten() && position[1]+bewegung.get(i)[1] >= 0
						//es darf keine Einheit auf dem Feld sein
						&& !spielbrett.besetzt(position[0]+bewegung.get(i)[0], position[1]+bewegung.get(i)[1]) 
						) {
					System.out.println((position[0]+bewegung.get(i)[0]) + ", " + (position[1]+bewegung.get(i)[1]));
				}
			}
			System.out.println("MÃ¶gliche Angriffe:");
			for (int i = 0; i < angriff.size(); i++) {
				//check fuer Rand FeldgrÃ¶ÃŸe flexibel
				if(position[0]+angriff.get(i)[0] < spielbrett.getAnzahlZeilen() && position[0]+angriff.get(i)[0] >= 0 && position[1]+angriff.get(i)[1] < spielbrett.getAnzahlZeilen() && position[1]+angriff.get(i)[1] >= 0
						//es muss eine Einheit auf dem Feld sein
						&& spielbrett.besetzt(position[0]+angriff.get(i)[0], position[1]+angriff.get(i)[1]) 
						) {
					System.out.println((position[0]+bewegung.get(i)[0]) + ", " + (position[1]+bewegung.get(i)[1]));
				}
			}
			System.out.println("Bitte geben Sie die Zeile und Spalte des Feldes an, auf das Sie bewegen oder angreifen lassen wollen.");
			System.out.println("Geben Sie die Zeile an. Geben Sie -1 an, wenn Sie abbrechen wollen.");
			int x, y;
			x= input.nextInt();
			if(x == -1) return false;
			System.out.println("Geben Sie die Spalte an");
			y= input.nextInt();
			if(spielbrett.besetzt(x, y)) this.angreifen(spielbrett, x, y); //benÃ¶tigt korrekte x, y
			else this.bewegen(spielbrett, x, y);
			return true;
		}
	}	
	
	//die drei Funktionen, die nutzen aufruft
	public void ausspielen(Feld spielbrett, int zeile, int spalte){
		spielbrett.getInhalt(zeile, spalte).add(this);
		besitzer.getHand().remove(this);
		besitzer.getTruppen().add(this);
		position[0]= zeile;
		position[1]= spalte;
	}
	
	public void bewegen(Feld spielbrett, int zeile, int spalte) {
		spielbrett.getInhalt(zeile, spalte).add(this);
		spielbrett.getInhalt(position[0], position[1]).remove(this);
		position[0]= zeile;
		position[1]= spalte;
	}
	
	//Wenn wir Einheiteneffekte haben wollen, wenn Einheiten angegriffen werden mÃ¼ssen wir das vielleicht noch anders machen.
	// angegriffene Einheit: spielbrett.getInhalt(zeile, spalte).get(0)
	public void angreifen(Feld spielbrett, int zeile, int spalte) {
		if( // check auf SchadenshÃ¶he
			staerke
			-spielbrett.getInhalt(zeile, spalte).get(0).getRuestung() < 1 
		) { //Minimalschaden
			spielbrett.getInhalt(zeile, spalte).get(0).setStaerke(spielbrett.getInhalt(zeile, spalte).get(0).getStaerke() - 1);
		}else { //regulÃ¤rer Schaden
			spielbrett.getInhalt(zeile, spalte).get(0).setStaerke( 
					+spielbrett.getInhalt(zeile, spalte).get(0).getStaerke()
					-staerke
					+spielbrett.getInhalt(zeile, spalte).get(0).getRuestung()
			);
		}
		if(spielbrett.getInhalt(zeile, spalte).get(0).getStaerke()<1) { //Einheit ist tot
			spielbrett.getInhalt(zeile, spalte).get(0).getBesitzer().getTruppen().remove(spielbrett.getInhalt(zeile, spalte).get(0));
			spielbrett.getInhalt(zeile, spalte).remove(0);
		}
	}
	
	/*vielleicht sollten wir das so zweiteilen
	public void verteidigen() {
		
	}
	*/
	
	//Setters und Getters
	
	public ArrayList<int[]> getAngriff() {
		return angriff;
	}
	public void setAngriff(ArrayList<int[]> angriff) {
		this.angriff = angriff;
	}
	
	public Spieler getBesitzer() {
		return besitzer;
	}

	public void setBesitzer(Spieler besitzer) {
		this.besitzer = besitzer;
	}
			
	public int getRuestung() {
		return ruestung;
	}

	public void setRuestung(int ruestung) {
		this.ruestung = ruestung;
	}

	public ArrayList<int[]> getBewegung() {
		return bewegung;
	}

	public void setBewegung(ArrayList<int[]> bewegung) {
		this.bewegung = bewegung;
	}

	public int[] getPosition() {
		return position;
	}

	public void setPosition(int[] position) {
		this.position = position;
	}

	public void setStaerke(int staerke) {
		this.staerke = staerke;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStaerke() {
		return staerke;
	}
	
	public String getName() {
		return name;
	}
	
	/* TODO(?): Verschiedene Chancen bestimmte Karten zu erhalten, vielleicht irgendwas mathematisches mit 
	 * modulo und Runden? Vielleicht Case 1/2/3 ein Kartentyp, Case 4/5/6 ein anderer?
	 * 
	 * Methode zur Generierung zufälliger Einheiten, ein Case repräsentiert einen Kartentyp
	 * @param Der zugehörige Spieler für den die Einheit generiert werden soll, wichtig für Bewegungsrichtung links/rechts
	 * @return jeweilige zufällige Karte wird zurückgegegeben
	 */
	public static Einheit generateEinheit(Spieler besitzer) {
		Random zufall = new Random();
		int zufZahl = zufall.nextInt(0); 	// Zahl muss manuell je nach Anzahl der existierenden Klassen in 'einheitenkarten' geändert werden
		switch (zufZahl) {					// case int AnzahlKarten: return new KartenTyp(besitzer);
		case 0:
			return new SoeldnerTest(besitzer);
		default:
			return null;
		}
	}
	
	/*(Dong:)
	 * Ist es vielleicht besser Einheit.java als Interface zu nehmen
	 * und die beiden nachfolgenden Methoden einzeln fï¿½r die jeweiligen
	 * Einheitenkarten zu definieren? Heiï¿½t getMoveable() wie eine Art
	 * Bauer zu definieren, wï¿½hrend getAttackable() alle in einem Umkreis
	 * von 2 Feldern angreifbar macht. Ich hoffe das war verstï¿½ndlich genug.
	 * 
	 * Auï¿½erdem brauchen wir glaube ich noch eine weitere Klasse um zu sagen
	 * wem die Einheiten gehï¿½ren. Also ob die Einheit wie beim Schach schwarz
	 * oder weiï¿½ ist. 
	 * 
	 */
	
	/* getMoveable() 
	 * Methode die ermittelt wohin sich eine Figur bewegen kann 
	 */
	
	/* getAttackable()
	 * Methode die ermittelt welche Figur angreifbar ist
	 */
}
