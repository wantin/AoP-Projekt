package einsnull;

import java.util.ArrayList;
import java.util.Scanner;


public class Einheit extends Karte {
	protected int staerke;
	protected int ruestung;
	protected ArrayList<int[]>  bewegung= new ArrayList<int[]> ();
	protected ArrayList<int[]>  angriff = new ArrayList< int[]>();
	protected String name;
	protected int[] position = {-1, -1};
	protected int preis;
	int bereit;
	protected String bildPfad;
	protected String art = "einheit";

static Scanner input = new Scanner(System.in);

	//Diese Funktion zeigt einem auf, wie man eine Karte(hier Einheit) nutzen kann und ruft dann die entsprechenden Funktionen auf (siehe unten)
	public boolean nutzen(Feld spielbrett) {
		
		if(position[0]==-1) { //prüft ob die Karte noch auf der Hand ist.
			this.ausspielen(spielbrett, besitzer.getAktionsAuswahlZeile(), besitzer.getAktionsAuswahlSpalte()); //keine Korrektur brauch korrekte Zeile und Spalte
			return true;
		}else {
			if(bereit==0) { //das sollte vorher schon abgefangen werden, wird es derzeit noch nicht 
				return false;
			}
			if(spielbrett.besetzt(besitzer.getAktionsAuswahlZeile(), besitzer.getAktionsAuswahlSpalte())){ //braucht korrekte zeile und spalte
				this.angreifen(spielbrett, besitzer.getAktionsAuswahlZeile(), besitzer.getAktionsAuswahlSpalte());
			}
			else this.bewegen(spielbrett, besitzer.getAktionsAuswahlZeile(), besitzer.getAktionsAuswahlSpalte());
			return true;
		}
		
		/* alte version
		if(position[0]==-1) { //prüft ob die Karte noch auf der Hand ist.
			System.out.println("Diese Karte k�nnen Sie ausspielen. Geben Sie Zeile und Spalte eines freien Feldes in Ihrem Spielbereich an, auf welches Sie die Karte spielen wollen.");
			System.out.println("Geben Sie die Zeile an, oder -1 um abzubrechen");
			int x, y;
			x= input.nextInt();
			if(x == -1) return false;
			System.out.println("Geben Sie die Spalte an");
			y= input.nextInt();
			this.ausspielen(spielbrett, x, y); //keine Korrektur brauch korrekte x, y
			return true;
		}else {
			if(bereit==0) {
				System.out.println("Diese Einheit können Sie nicht benutzen, da sie sich schon bewegt hat");
				return false;
			}
			System.out.println("Diese Karte können Sie angreifen lassen oder bewegen.");
			System.out.println("Mögliche Bewegungen:");
			for (int i = 0; i < bewegung.size(); i++) {
				//check fuer Rand Feldgröße flexibel
				if(position[0]+bewegung.get(i)[0] < spielbrett.getAnzahlZeilen() && position[0]+bewegung.get(i)[0] >= 0 && position[1]+bewegung.get(i)[1] < spielbrett.getAnzahlSpalten() && position[1]+bewegung.get(i)[1] >= 0
						//es darf keine Einheit auf dem Feld sein
						&& !spielbrett.besetzt(position[0]+bewegung.get(i)[0], position[1]+bewegung.get(i)[1]) 
						) {
					System.out.println((position[0]+bewegung.get(i)[0]) + ", " + (position[1]+bewegung.get(i)[1]));
				}
			}
			System.out.println("Mögliche Angriffe:");
			for (int i = 0; i < angriff.size(); i++) {
				//check fuer Rand Feldgröße flexibel
				if(position[0]+angriff.get(i)[0] < spielbrett.getAnzahlZeilen() && position[0]+angriff.get(i)[0] >= 0 && position[1]+angriff.get(i)[1] < spielbrett.getAnzahlSpalten() && position[1]+angriff.get(i)[1] >= 0
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
			if(spielbrett.besetzt(x, y)) this.angreifen(spielbrett, x, y); //benötigt korrekte x, y
			else this.bewegen(spielbrett, x, y);
			return true;
		}
		*/
	}	
	
	//die drei Funktionen, die nutzen aufruft
	public void ausspielen(Feld spielbrett, int zeile, int spalte){
		spielbrett.getInhalt(zeile, spalte).add(this);
		besitzer.getHand().remove(this);
		besitzer.getTruppen().add(this);
		position[0]= zeile;
		position[1]= spalte;
		bereit=0;
	}
	
	public void bewegen(Feld spielbrett, int zeile, int spalte) {
		spielbrett.getInhalt(zeile, spalte).add(this);
		spielbrett.getInhalt(position[0], position[1]).remove(this);
		position[0]= zeile;
		position[1]= spalte;
		bereit--;
	}
	
	//zweigeteiltes Angreifen sollte spätere Effekte einfacher machen und sieht schöner aus
	// angegriffene Einheit: spielbrett.getInhalt(zeile, spalte).get(0)
	public void angreifen(Feld spielbrett, int zeile, int spalte) {
		spielbrett.getInhalt(zeile, spalte).get(0).verteidigen(spielbrett, this);
		bereit--;
	}
	
	public void verteidigen(Feld spielbrett, Einheit angreifer) { //wird von der verteidigenden Einheit aus aufgerufen
		if(angreifer.getStaerke()-ruestung < 1) { //check auf Schadenshöhe
			staerke--; //Mindestschaden
		}else { //regulärer Schaden
			staerke-= angreifer.getStaerke()-ruestung;
		}
		if(staerke<1) {
			besitzer.getTruppen().remove(this);
			spielbrett.getInhalt(position[0], position[1]).remove(this);
		}
	}
	
	//Setters und Getters
	public String getBildPfad(){
		return bildPfad;
	}
	
	public String getArt() {
		return art;
	}

	public int getBereit() {
		return bereit;
	}

	public void setBereit(int bereit) {
		this.bereit = bereit;
	}
	
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
	
	public int getStaerke() {
		return staerke;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setPreis(int preis){
		this.preis = preis;
	}
	
	public int getPreis() {
		return preis;
	}
}
